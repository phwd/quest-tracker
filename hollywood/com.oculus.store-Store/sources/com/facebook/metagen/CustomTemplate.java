package com.facebook.metagen;

public @interface CustomTemplate {
    public static final String FILE_GENERATORS = "fileGenerators";
    public static final String FILE_NAME_FORMAT = "fileNameFormat";
    public static final String FILE_TEMPLATE = "fileTemplate";
    public static final String METADATA_TRANSFORMERS = "metadataTransformers";
    public static final String METADATA_WRITER = "metadataWriter";

    String[] fileGenerators() default {};

    String fileNameFormat() default "";

    String fileTemplate() default "";

    String[] metadataTransformers() default {};

    String metadataWriter();
}
