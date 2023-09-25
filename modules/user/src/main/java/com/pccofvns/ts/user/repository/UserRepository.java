package com.pccofvns.ts.user.repository;

import java.util.Optional;

public interface UserRepository {

    Optional<com.pccofvns.ts.domain.tables.pojos.User> findByUsername(String username);

    boolean deleteById(Long id);

    Optional<com.pccofvns.ts.domain.tables.pojos.User> findById(Long id);

    com.pccofvns.ts.domain.tables.pojos.User save(com.pccofvns.ts.domain.tables.pojos.User newUser);

    Iterable<? extends com.pccofvns.ts.domain.tables.pojos.User> findAll();
}
