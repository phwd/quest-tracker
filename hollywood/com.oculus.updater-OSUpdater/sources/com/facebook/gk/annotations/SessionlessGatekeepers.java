package com.facebook.gk.annotations;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.annotationprocessors.gatekeepers.GatekeeperMetagenCodeGenerator")
@Retention(RetentionPolicy.SOURCE)
public @interface SessionlessGatekeepers {
}
