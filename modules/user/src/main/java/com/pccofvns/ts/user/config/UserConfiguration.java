package com.pccofvns.ts.user.config;

import com.pccofvns.ts.user.repository.UserRepository;
import com.pccofvns.ts.user.security.service.TakshashilaUserAuthDetailsService;
import com.pccofvns.ts.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new TakshashilaUserAuthDetailsService(userService);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }
}
