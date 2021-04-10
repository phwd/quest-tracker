package com.facebook.soloader.annotation;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.GlobalTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.mustache.MustacheCodeGenerator", globalTemplates = {@GlobalTemplate(inputName = "soloader_referenced_sonames.mustache", outputName = "soloader_referenced_sonames.txt", templatePackage = "com.facebook.soloader.annotation")}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface SoLoaderLibrary {
    String[] value();
}
