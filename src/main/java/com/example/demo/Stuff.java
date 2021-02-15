package com.example.demo;

import lombok.Value;

@Value
public class Stuff {
    String name;
    SubStuff subStuff;

    @Value
    public static class SubStuff {
        String subName;
    }
}