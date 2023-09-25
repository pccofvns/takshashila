package com.pccofvns.ts.api;

import com.pccofvns.ts.model.Picklist;
import com.pccofvns.ts.model.PicklistResponse;
import com.pccofvns.ts.model.User;
import com.pccofvns.ts.service.UserManagementFacade;
import com.pccofvns.ts.user.security.UserAuthenticationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublicController implements PublicApi{

    @NonNull
    @Autowired
    private UserAuthenticationService authenticationService;
    @NonNull
    @Autowired
    private UserManagementFacade userManagementFacade;

    @Override
    public String register(String username, String password) {
        userManagementFacade.save(new User().username(username).password(password));
        return login(username, password);
    }

    @Override
    public String login(String username, String password) {
        return authenticationService.login(username, password).orElseThrow(() -> new RuntimeException("invalid login and/or password"));
    }

    @Override
    public PicklistResponse getPicklistByKey(String picklist, String lang) {
        PicklistResponse response = new PicklistResponse();
        Picklist p1 = new Picklist();
        p1.key("ENGLISH").value("English");
        Picklist p2 = new Picklist();
        p2.key("HINDI").value("Hindi");
        List<Picklist> picklists = List.of(p1, p2);
        response.picklists(picklists);
        return response;
    }
}
