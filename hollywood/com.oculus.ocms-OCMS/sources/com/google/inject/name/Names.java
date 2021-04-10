package com.google.inject.name;

public class Names {
    private Names() {
    }

    public static Named named(String str) {
        return new NamedImpl(str);
    }
}
