package com.pccofvns.ts.user.service;

import com.pccofvns.ts.user.repository.UserRepository;
import com.pccofvns.ts.user.security.domain.UserAuthDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.pccofvns.ts.domain.tables.Permission.PERMISSION;
import static com.pccofvns.ts.domain.tables.Role.ROLE;
import static com.pccofvns.ts.domain.tables.RolePermissionX.ROLE_PERMISSION_X;
import static com.pccofvns.ts.domain.tables.User.USER;
import static com.pccofvns.ts.domain.tables.UserRoleX.USER_ROLE_X;

@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private DSLContext context;

    public Iterable<? extends com.pccofvns.ts.domain.tables.pojos.User> findAll() {
        return userRepository.findAll();
    }

    public com.pccofvns.ts.domain.tables.pojos.User save(com.pccofvns.ts.domain.tables.pojos.User newTakshashilaUser) {
        return userRepository.save(newTakshashilaUser);
    }

    public Optional<com.pccofvns.ts.domain.tables.pojos.User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserAuthDetails> findByUsername(final String username) {
        Optional<com.pccofvns.ts.domain.tables.pojos.User> byUsername = userRepository.findByUsername(username);
        byUsername.ifPresentOrElse(user -> log.info("Found user with username: {}", username), () -> log.info("Could not find user {}", username));
        if(byUsername.isPresent()){
            Result<Record1<String>> result = context.select(PERMISSION.NAME)
                    .from(USER)
                    .join(USER_ROLE_X).on(USER_ROLE_X.USER_ID.eq(USER.ID))
                    .join(ROLE).on(ROLE.ID.eq(USER_ROLE_X.ROLE_ID))
                    .join(ROLE_PERMISSION_X).on(ROLE_PERMISSION_X.ROLE_ID.eq(ROLE.ID))
                    .join(PERMISSION).on(PERMISSION.ID.eq(ROLE_PERMISSION_X.PERMISSION_ID))
                    .fetch();
            Set<String> permissions = result.stream().map(rec -> rec.get(PERMISSION.NAME)).collect(Collectors.toUnmodifiableSet());
            return Optional.of(new UserAuthDetails(byUsername.get(), permissions));
        }
        return Optional.empty();
    }

    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}
