package com.facebook.common.lsplugins.config;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.mustache.MustacheCodeGenerator", globalTemplateNames = {"PluginsConfig.mustache"}, nestedAnnotations = {ProvideGlobalPartInterfaceLogger.class, ProvideKillSwitchOverrider.class}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface PluginsConfigProvider {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ProvideGlobalPartInterfaceLogger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface ProvideKillSwitchOverrider {
    }
}
