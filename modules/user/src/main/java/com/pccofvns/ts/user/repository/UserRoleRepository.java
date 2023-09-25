package com.pccofvns.ts.user.repository;

import com.pccofvns.ts.domain.tables.pojos.User;
import com.pccofvns.ts.domain.tables.pojos.UserRoleX;

import java.util.List;
import java.util.Optional;

public interface UserRoleRepository {

    List<UserRoleX> findByUserId(Long id);
}
