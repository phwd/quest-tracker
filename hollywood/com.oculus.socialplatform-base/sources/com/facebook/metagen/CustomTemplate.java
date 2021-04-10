package com.facebook.metagen;

public @interface CustomTemplate {
    String fileNameFormat() default "";

    String fileTemplate() default "";

    String metadataWriter();
}
