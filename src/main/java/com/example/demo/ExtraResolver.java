package com.example.demo;

import graphql.kickstart.tools.GraphQLResolver;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
public class ExtraResolver implements GraphQLResolver<Stuff> {

    @PreAuthorize("hasAuthority('write')")
    @Nullable
    public ExtraStuff extraStuff(Stuff stuff) {
        return new ExtraStuff("extra name for " + stuff.getName());
    }
}
