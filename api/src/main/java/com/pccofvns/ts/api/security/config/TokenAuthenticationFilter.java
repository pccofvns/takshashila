package com.pccofvns.ts.api.security.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.experimental.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.util.matcher.*;

import java.io.*;

import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;
import static org.apache.commons.lang3.StringUtils.removeStart;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String BEARER =  "Bearer";

    TokenAuthenticationFilter(final RequestMatcher requiresAuth) {
        super(requiresAuth);
    }

    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
            final String param = ofNullable(request.getHeader(AUTHORIZATION))
                    .orElse(request.getParameter("t"));

            final String token = ofNullable(param)
                    .map(value -> removeStart(value, BEARER))
                    .map(String::trim)
                    .orElseThrow(() -> new BadCredentialsException("Missing Authentication Token"));

            final Authentication auth = new UsernamePasswordAuthenticationToken(token, token);
            return getAuthenticationManager().authenticate(auth);
    }

    @Override
    protected void successfulAuthentication(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain chain,
            final Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
