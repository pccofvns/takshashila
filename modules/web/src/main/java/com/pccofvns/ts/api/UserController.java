package com.pccofvns.ts.api;

import com.pccofvns.ts.model.User;
import com.pccofvns.ts.model.UserResponse;
import com.pccofvns.ts.service.UserManagementFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController implements UserApi{

    @Autowired
    private UserManagementFacade userManagementFacade;

    @Override
    @PreAuthorize("hasAuthority('PERM_VIEW_USERS')")
    public UserResponse getUserById(Long userId, String lang) {
        Optional<User> byId = userManagementFacade.findById(userId);
        UserResponse response = new UserResponse();
        response.setData(byId.orElse(null));
        return response;
    }
}
