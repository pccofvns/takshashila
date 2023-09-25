package com.pccofvns.ts.docs.config;

import com.pccofvns.ts.service.PicklistService;
import com.pccofvns.ts.domain.validation.PicklistConstraint;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.PropertyCustomizer;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.Links;

import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    private final PicklistService picklistService;

    @Bean
    public OpenApiCustomizer openApiCustomizer(@Value("${info.app.version}") final String appVersion,
                                     @Value("${server.port}") final String port) {
        return new OpenApiGroupCustomizer(appVersion, port);
    }

    @Bean
    public PropertyCustomizer propertyCustomizer() {
        return (schema, type) -> {
            if (type != null) {
                Annotation[] annotations = type.getCtxAnnotations();
                if (annotations != null) {
                    for (Annotation ann : annotations) {
                        if (ann instanceof PicklistConstraint spd) {
                            String name = spd.name();
                            String description = schema.getDescription();
                            Map<String, String> picklistsForPicklistNameAndLocale = picklistService.getPicklistsForPicklistNameAndLocale(name, Locale.ENGLISH);
                            if (picklistsForPicklistNameAndLocale != null && !picklistsForPicklistNameAndLocale.isEmpty()) {
                                description = (description == null ? "" : description) + " Picklist: " + name + ". Allowed values: " +
                                        picklistsForPicklistNameAndLocale.keySet()
                                                .stream().filter(Objects::nonNull)
                                                .map(key -> "`" + key.strip() + "`")
                                                .collect(Collectors.joining(","));
                                schema.setDescription(description);
                            }
                        }
                    }
                }
            }
            return schema;
        };
    }

    @EventListener(ApplicationReadyEvent.class)
    public void configureAfterStartup() {
        SpringDocUtils.getConfig().addResponseTypeToIgnore(Links.class);
    }
}
