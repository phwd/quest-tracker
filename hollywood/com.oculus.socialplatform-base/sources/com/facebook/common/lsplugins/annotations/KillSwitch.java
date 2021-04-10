package com.facebook.common.lsplugins.annotations;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.CustomTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(localCustomTemplates = {@CustomTemplate(fileGenerators = {"com.facebook.metagen.generator.custom.lsplugins.PluginKillSwitchFileGenerator"}, metadataWriter = "com.facebook.metagen.generator.json.JsonMetadataWriter")}, nestedAnnotations = {IsNeeded.class, PluginInjectProp.class}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface KillSwitch {
}
