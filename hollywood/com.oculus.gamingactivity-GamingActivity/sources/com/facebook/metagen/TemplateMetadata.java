package com.facebook.metagen;

public @interface TemplateMetadata {
    public static final String NAME = "name";
    public static final String VALUE = "value";

    String name();

    String value();
}
