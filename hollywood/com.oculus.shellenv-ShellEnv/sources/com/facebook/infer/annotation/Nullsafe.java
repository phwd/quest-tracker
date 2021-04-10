package com.facebook.infer.annotation;

public @interface Nullsafe {

    public enum Mode {
        LOCAL,
        STRICT
    }

    public @interface TrustList {
        boolean trustAll();

        Class[] value();
    }

    TrustList trustOnly();

    Mode value();
}
