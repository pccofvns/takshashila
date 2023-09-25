package com.pccofvns.ts.user.repository.impl;

import com.pccofvns.ts.domain.tables.Role;
import com.pccofvns.ts.domain.tables.pojos.UserRoleX;
import com.pccofvns.ts.user.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

import static com.pccofvns.ts.domain.tables.Role.ROLE;
import static com.pccofvns.ts.domain.tables.UserRoleX.USER_ROLE_X;

@RequiredArgsConstructor
@Repository
public class UserRoleRepo implements UserRoleRepository {

    private final DSLContext context;
    @Override
    public List<UserRoleX> findByUserId(Long id) {
        Result<Record> records = context.select().from(USER_ROLE_X).join(ROLE).on(ROLE.ID.eq(USER_ROLE_X.ROLE_ID)).where(USER_ROLE_X.USER_ID.eq(id)).fetch();
        return Collections.emptyList();
    }
}
