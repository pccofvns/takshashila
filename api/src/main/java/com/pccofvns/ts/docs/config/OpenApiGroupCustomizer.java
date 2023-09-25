package com.pccofvns.ts.docs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.http.HttpHeaders;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class OpenApiGroupCustomizer implements OpenApiCustomizer {

    public static final String CONTENT_TYPE = "application/json";
    private final String version;
    private final String port;

    public OpenApiGroupCustomizer(String version, String port) {
        this.version = version;
        this.port = port;
    }

    @Override
    public void customise(OpenAPI openApi) {
        //overwriteOpenApiDefinitionsFromYaml(openApi, api);
        updateVersion(openApi);
        updatePort(openApi);
        openApi.addSecurityItem(new SecurityRequirement().addList("ApiKeyAuth"))
                .getComponents().addSecuritySchemes("ApiKeyAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.APIKEY)
                        .in(SecurityScheme.In.HEADER)
                        .name(HttpHeaders.AUTHORIZATION));
    }

    private static void overwriteOpenApiDefinitionsFromYaml(OpenAPI openApi, OpenAPI api) {
        Map<String, Schema> declaredSchemas = api.getComponents().getSchemas();
        Map<String, Schema> calculatedSchemas = openApi.getComponents().getSchemas();
        declaredSchemas.forEach(calculatedSchemas::putIfAbsent);
        copySubSchemaKeyWords(declaredSchemas, calculatedSchemas);
        copyApiOperationExamples(openApi, api);
    }

    private static void copySubSchemaKeyWords(Map<String, Schema> declaredSchemas, Map<String, Schema> calculatedSchemas) {
        declaredSchemas.forEach((name, schema) -> {
            Schema calculatedSchema = calculatedSchemas.get(name);
            if(schema != null && calculatedSchema != null) {
                List oneOf = schema.getOneOf();
                if(oneOf != null && !oneOf.isEmpty()
                        && (calculatedSchema.getOneOf() == null
                        || calculatedSchema.getOneOf().isEmpty())){
                    calculatedSchema.oneOf(oneOf);
                }
                List<Schema> anyOf = schema.getAnyOf();
                if(anyOf != null && !anyOf.isEmpty()
                        && (calculatedSchema.getAnyOf() == null
                        || calculatedSchema.getAnyOf().isEmpty())){
                    calculatedSchema.anyOf(anyOf);
                }
            }
        });
    }

    private static void copyApiOperationExamples(OpenAPI openApi, OpenAPI api) {
        Paths declaredPaths = api.getPaths();
        Paths calculatedPaths = openApi.getPaths();
        declaredPaths.forEach((name, path) -> {
            PathItem pathItem = calculatedPaths.get(name);
            if(pathItem != null){
                copyExamples(pathItem.getPost(), path.getPost());
                copyExamples(pathItem.getGet(), path.getGet());
                copyExamples(pathItem.getPatch(), path.getPatch());
            }
        });
    }

    private static void copyExamples(Operation calculatedPath, Operation declaredPath) {
        if(calculatedPath != null){
            ApiResponse declaredSuccessResponse = declaredPath.getResponses().get("200");
            ApiResponse calculatedSuccessResponse = calculatedPath.getResponses().get("200");
            if(calculatedSuccessResponse != null && declaredSuccessResponse != null
                    && calculatedSuccessResponse.getContent() != null && declaredSuccessResponse.getContent() != null
                    && calculatedSuccessResponse.getContent().get(CONTENT_TYPE) != null && declaredSuccessResponse.getContent().get(CONTENT_TYPE) != null){
                Map<String, Example> examples = declaredSuccessResponse.getContent().get(CONTENT_TYPE).getExamples();
                if(examples != null && examples.size() > 1) {
                    calculatedSuccessResponse.getContent().get(CONTENT_TYPE).setExamples(examples);
                }
            }
            if(declaredPath.getRequestBody() != null && declaredPath.getRequestBody().getContent() != null && declaredPath.getRequestBody().getContent().get(CONTENT_TYPE) != null && declaredPath.getRequestBody().getContent().get(CONTENT_TYPE).getExamples() != null && declaredPath.getRequestBody().getContent().get(CONTENT_TYPE).getExamples().size() > 1){
                calculatedPath.getRequestBody().getContent().get(CONTENT_TYPE).setExamples(declaredPath.getRequestBody().getContent().get(CONTENT_TYPE).getExamples());
            }
        }
    }

    private void updateVersion(OpenAPI openApi) {
        openApi.getInfo().setVersion(version);
    }

    private void updatePort(OpenAPI openApi) {
        List<Server> servers = openApi.getServers();
        servers.forEach(server -> {
            try {
                URL originalURL = new URL(server.getUrl());
                URL modifiedURL = new URL(originalURL.getProtocol(), originalURL.getHost(), Integer.parseInt(port), originalURL.getFile());
                server.setUrl(modifiedURL.toString());
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
