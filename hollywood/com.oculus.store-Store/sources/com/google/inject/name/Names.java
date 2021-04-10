package com.google.inject.name;

public class Names {
    private Names() {
    }

    public static Named named(String name) {
        return new NamedImpl(name);
    }
}
