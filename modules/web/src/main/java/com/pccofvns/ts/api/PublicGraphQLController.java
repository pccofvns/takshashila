package com.pccofvns.ts.api;

import com.pccofvns.ts.model.Picklist;
import com.pccofvns.ts.model.PicklistResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PublicGraphQLController {

    @QueryMapping
    public PicklistResponse getPicklistByKey(@Argument String picklist, @Argument String lang){
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
