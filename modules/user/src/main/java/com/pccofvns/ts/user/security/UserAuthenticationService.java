package com.pccofvns.ts.user.security;

import com.pccofvns.ts.user.security.domain.*;

import java.util.*;

public interface UserAuthenticationService {
    /**
     * Logs in with the given {@code username} and {@code password}.
     *
     * @param username
     * @param password
     * @return an {@link Optional} of a user when login succeeds
     */
    Optional<String> login(String username, String password);

    /**
     * Finds a user by its token.
     *
     * @param token user token
     * @return
     */
    Optional<UserAuthDetails> findByToken(String token);

    /**
     * Logs out the given input {@code user}.
     *
     * @param user the user to logout
     */
    void logout(UserAuthDetails user);
}
