package com.pccofvns.ts.user.security.service;

import com.pccofvns.ts.user.security.*;
import io.jsonwebtoken.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;
import java.util.function.*;

import static com.pccofvns.ts.utils.DateUtils.*;
import static com.pccofvns.ts.utils.StringUtils.*;
import static io.jsonwebtoken.SignatureAlgorithm.*;
import static lombok.AccessLevel.*;

@FieldDefaults(level = PRIVATE)
@Service
public class JwtTokenService implements TokenService {

    private static final String DOT = ".";

    @Value("${jwt.issuer:takshashila}")
    String issuer;
    @Value("${jwt.expiration-sec:86400}")
    int expirationSec;
    @Value("${jwt.clock-skew-sec:300}")
    int clockSkewSec;
    @Value("${jwt.secret:takshashila}")
    String secretKey;


    @Override
    public String permanent(Map<String, Object> attributes) {
        return newToken(attributes, 0);
    }

    @Override
    public String expiring(Map<String, Object> attributes) {
        return newToken(attributes, expirationSec);
    }

    private String newToken(final Map<String, Object> attributes, final int expiresInSec) {
        final LocalDateTime now = LocalDateTime.now();
        return  Jwts.builder()
                .setIssuer(issuer)
                .addClaims(Map.of("username", String.valueOf(attributes.get("username"))))
                .setIssuedAt(toDate(now))
                .setExpiration(toDate(now.plusSeconds(expiresInSec)))
                .signWith(HS256, secretKey)
                .compact();
    }

    @Override
    public Map<String, String> untrusted(String token) {
        final JwtParser parser = Jwts
                .parser()
                .requireIssuer(issuer)
                .setAllowedClockSkewSeconds(clockSkewSec);

        // See: https://github.com/jwtk/jjwt/issues/135
        final String withoutSignature = substringBeforeLast(token, DOT) + DOT;
        return parseClaims(() -> parser.parseClaimsJwt(withoutSignature).getBody());
    }

    @Override
    public Map<String, String> verify(String token) {
        final JwtParser parser = Jwts
                .parserBuilder()
                .requireIssuer(issuer)
                .setAllowedClockSkewSeconds(clockSkewSec)
                .setSigningKey(secretKey)
                .build();
        return parseClaims(() -> parser.parseClaimsJws(token).getBody());
    }

    private static Map<String, String> parseClaims(final Supplier<Claims> toClaims) {
        try {
            final Claims claims = toClaims.get();
            final Map<String, String> map = new HashMap<>();
            for (final Map.Entry<String, Object> e: claims.entrySet()) {
                map.put(e.getKey(), String.valueOf(e.getValue()));
            }
            return map;
        } catch (final IllegalArgumentException | JwtException e) {
            return Collections.emptyMap();
        }
    }
}
