package com.pccofvns.ts.api.security;

import jakarta.servlet.http.*;
import org.springframework.security.web.*;

import java.io.*;

public class NoRedirectStrategy implements RedirectStrategy {

    @Override
    public void sendRedirect(final HttpServletRequest request, final HttpServletResponse response, final String url) throws IOException {
        // No redirect is required with pure REST
    }
}
