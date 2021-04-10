package com.facebook.annotationprocessors.transformer.api;

public @interface Forwarder {
    String processor();

    String to();
}
