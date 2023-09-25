package com.pccofvns.ts.user.security.service;

import lombok.extern.slf4j.*;
import org.springframework.context.event.EventListener;
import org.springframework.lang.*;
import org.springframework.security.access.event.*;
import org.springframework.security.authentication.event.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@Slf4j
public class SecurityLogger {
    @EventListener
    public void authenticated(final @NonNull AuthenticationSuccessEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Successful login - [username: \"{}\"]", principal);
    }
    @EventListener
    public void authenticationFailure(final @NonNull AbstractAuthenticationFailureEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Unsuccessful login - [username: \"{}\"]", principal);
    }
    @EventListener
    public void authorizationFailure(final @NonNull AuthorizationFailureEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        final String message = event.getAccessDeniedException().getMessage();
        log.error("Unauthorized access - [username: \"{}\", message: \"{}\"]", principal, Optional.ofNullable(message).orElse("<null>"));
    }
    @EventListener
    public void logoutSuccess(final @NonNull LogoutSuccessEvent event) {
        final Object principal = event.getAuthentication().getPrincipal();
        log.info("Successful logout - [username: \"{}\"]", principal);
    }
}
