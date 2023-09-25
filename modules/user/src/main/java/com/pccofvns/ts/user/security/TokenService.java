package com.pccofvns.ts.user.security;

import java.util.*;

public interface TokenService {

    String permanent(Map<String, Object> attributes);

    String expiring(Map<String, Object> attributes);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> untrusted(String token);

    /**
     * Checks the validity of the given credentials.
     *
     * @param token
     * @return attributes if verified
     */
    Map<String, String> verify(String token);
}
