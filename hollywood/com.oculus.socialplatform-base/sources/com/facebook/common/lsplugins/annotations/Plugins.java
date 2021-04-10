package com.facebook.common.lsplugins.annotations;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.CustomTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(globalCustomTemplates = {@CustomTemplate(fileGenerators = {"com.facebook.metagen.generator.custom.lsplugins.AllThePluginPartsFileGenerator"}, metadataWriter = "com.facebook.metagen.generator.custom.lsplugins.AllThePluginPartsMetadataWriter")}, localCustomTemplates = {@CustomTemplate(metadataTransformers = {"com.facebook.metagen.generator.custom.lsplugins.AllThePluginPartsMetadataValidator"}, metadataWriter = "com.facebook.metagen.generator.custom.lsplugins.AllThePluginPartsMetadataWriter")}, nestedAnnotations = {KillSwitch.class, PluginPartInterface.class, PluginInjectProp.class, PluginInterfaceProp.class, PluginPartImplementation.class, State.class, IsNeeded.class}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface Plugins {
}
