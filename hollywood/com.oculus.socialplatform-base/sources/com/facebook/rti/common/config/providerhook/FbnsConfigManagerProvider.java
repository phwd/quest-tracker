package com.facebook.rti.common.config.providerhook;

import X.AnonymousClass1I1;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.mustache.MustacheCodeGenerator", globalTemplateNames = {"GeneratedFbnsConfigManagerProvider.mustache"}, nestedAnnotations = {ProvideFbnsConfigManager.class}, targets = {MetadataAnnotationTarget.CLASS})
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface FbnsConfigManagerProvider {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ProvideFbnsConfigManager {
    }

    AnonymousClass1I1 type();
}
