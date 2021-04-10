package com.facebook.metagen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
public @interface CodeGeneratorMetadataAnnotation {
    public static final String GENERATOR = "generator";
    public static final String GLOBAL_CUSTOM_TEMPLATES = "globalCustomTemplates";
    public static final String GLOBAL_TEMPLATES = "globalTemplates";
    public static final String GLOBAL_TEMPLATE_NAMES = "globalTemplateNames";
    public static final String LOCAL_CUSTOM_TEMPLATES = "localCustomTemplates";
    public static final String LOCAL_TEMPLATES = "localTemplates";
    public static final String NESTED_ANNOTATIONS = "nestedAnnotations";
    public static final String TARGETS = "targets";

    String generator() default "";

    CustomTemplate[] globalCustomTemplates() default {};

    String[] globalTemplateNames() default {};

    GlobalTemplate[] globalTemplates() default {};

    CustomTemplate[] localCustomTemplates() default {};

    LocalTemplate[] localTemplates() default {};

    Class<?>[] nestedAnnotations() default {};

    MetadataAnnotationTarget[] targets();
}
