package com.pccofvns.ts.user.security.service;

import com.pccofvns.ts.user.security.TokenService;
import com.pccofvns.ts.user.security.UserAuthenticationService;
import com.pccofvns.ts.user.security.domain.UserAuthDetails;
import com.pccofvns.ts.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationService implements UserAuthenticationService {

    @NonNull
    TokenService tokenService;
    @NonNull
    UserService userService;
    @Override
    public Optional<String> login(String username, String password) {
        return userService
                .findByUsername(username)
                .filter(user -> BCrypt.checkpw(password, user.getPassword()))
                .map(user -> tokenService.expiring(Map.of("username", username, "authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toUnmodifiableSet()))));
    }

    @Override
    public Optional<UserAuthDetails> findByToken(String token) {
        return Optional
                .of(tokenService.verify(token))
                .map(map -> map.get("username"))
                .flatMap(userService::findByUsername);
    }

    @Override
    public void logout(UserAuthDetails user) {

    }
}
