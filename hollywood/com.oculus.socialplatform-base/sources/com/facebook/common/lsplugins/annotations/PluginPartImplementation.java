package com.facebook.common.lsplugins.annotations;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.CustomTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(localCustomTemplates = {@CustomTemplate(metadataTransformers = {"com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartImplementationValidator"}, metadataWriter = "com.facebook.metagen.generator.custom.lsplugins.LocalPluginPartImplementationMetadataWriter")}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface PluginPartImplementation {
    String finderKeyString() default "";

    Class<?> interfacePart();

    Class<?> killSwitch();

    Class<?> stateType() default Void.class;
}
