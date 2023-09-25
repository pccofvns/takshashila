package com.pccofvns.ts.mapper;

import com.pccofvns.ts.model.User;
import com.pccofvns.ts.user.service.UserService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected UserService userService;
    public abstract User sourceToDestination(com.pccofvns.ts.domain.tables.pojos.User source);
    public abstract com.pccofvns.ts.domain.tables.pojos.User destinationToSource(User destination);
}
