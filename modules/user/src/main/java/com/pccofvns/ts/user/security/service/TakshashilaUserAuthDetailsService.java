package com.pccofvns.ts.user.security.service;

import com.pccofvns.ts.user.security.domain.UserAuthDetails;
import com.pccofvns.ts.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@RequiredArgsConstructor
public class TakshashilaUserAuthDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAuthDetails> byUsername = userService.findByUsername(username);
        return byUsername.orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
    }
}
