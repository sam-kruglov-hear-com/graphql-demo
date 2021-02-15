package com.example.demo;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class QueryResolver implements GraphQLQueryResolver {

    public Stuff getStuff(String id) {
        return new Stuff("stuff #" + id, new Stuff.SubStuff("substuff"));
    }

    @PreAuthorize("isAuthenticated()")
    public Stuff getAuthenticatedStuff(String id) {
        return getStuff(id);
    }

    @PreAuthorize("hasAuthority('read')")
    public Stuff getReadOnlyStuff(String id) {
        return getStuff(id);
    }

    @PreAuthorize("hasAuthority('write')")
    public Stuff getWriteStuff(String id) {
        return getStuff(id);
    }

}