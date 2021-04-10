package com.facebook.common.lsplugins.annotations;

import X.AnonymousClass1IJ;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.CustomTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(localCustomTemplates = {@CustomTemplate(fileGenerators = {"com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartInterfaceInterfaceFileGenerator", "com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartInterfaceImplementationFileGenerator", "com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartInterfaceTestHelperFileGenerator"}, metadataWriter = "com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartInterfaceMetadataWriter")}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface PluginPartInterface {
    AnonymousClass1IJ finderKeyType() default AnonymousClass1IJ.INTEGER;
}
