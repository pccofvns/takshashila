package com.pccofvns.ts.user.repository.impl;

import com.pccofvns.ts.domain.tables.pojos.User;
import com.pccofvns.ts.domain.tables.records.UserRecord;
import com.pccofvns.ts.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.pccofvns.ts.domain.tables.User.USER;

@RequiredArgsConstructor
@Repository
public class UserRepo implements UserRepository {

    private final DSLContext context;

    @Override
    public Optional<User> findByUsername(String username) {
        User user = context.selectFrom(USER).where(USER.USERNAME.eq(username)).fetchOneInto(User.class);

        return Optional.ofNullable(user);
    }

    @Override
    public boolean deleteById(Long id) {
      return  context.deleteFrom(USER).where(USER.ID.eq(id)).execute() == 1;
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = context.selectFrom(USER).where(USER.ID.eq(id)).fetchOneInto(User.class);
        return Optional.ofNullable(user);
    }

    @Override
    public User save(User newUser) {
        UserRecord userRecord = context.insertInto(USER)
                .set(USER.USERNAME, newUser.username())
                .set(USER.CREDENTIALS_NON_EXPIRED, true)
                .set(USER.ENABLED, true)
                .set(USER.NON_EXPIRED, true)
                .set(USER.NON_LOCKED, true)
                .set(USER.PASSWORD, newUser.password())
                .returning(USER.ID).fetchOne();
        if(userRecord != null){
            return new User(userRecord.getId(), userRecord.getUsername(), userRecord.getPassword(), userRecord.getCredentialsNonExpired(), userRecord.getNonExpired(), userRecord.getNonLocked(), userRecord.getEnabled());
        }
        return null;
    }


    @Override
    public Iterable<? extends User> findAll() {
        return context.selectFrom(USER).fetchInto(User.class);
    }
}
