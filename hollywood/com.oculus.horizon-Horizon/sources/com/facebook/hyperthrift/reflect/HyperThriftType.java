package com.facebook.hyperthrift.reflect;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.mustache.MustacheCodeGenerator", globalTemplateNames = {"GeneratedHyperThriftClassLookup.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface HyperThriftType {
}
