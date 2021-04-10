package com.facebook.metagen;

public @interface GlobalTemplate {
    public static final String INPUT_NAME = "inputName";
    public static final String OUTPUT_NAME = "outputName";
    public static final String TEMPLATE_METADATA = "templateMetadata";
    public static final String TEMPLATE_PACKAGE = "templatePackage";

    String inputName();

    String outputName();

    TemplateMetadata[] templateMetadata() default {};

    String templatePackage();
}
