package com.facebook.metagen;

public @interface LocalTemplate {
    public static final String INPUT_NAME = "inputName";
    public static final String OUTPUT_NAME_FORMAT = "outputNameFormat";

    String inputName();

    String outputNameFormat();
}
