package com.facebook.gk.annotations;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.GlobalTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@CodeGeneratorMetadataAnnotation(generator = "com.facebook.annotationprocessors.gatekeepers.GatekeeperMetagenCodeGenerator", globalTemplates = {@GlobalTemplate(inputName = "GK.mustache", outputName = "SessionlessGK", templatePackage = "com.facebook.annotationprocessors.gatekeepers"), @GlobalTemplate(inputName = "GKNames.mustache", outputName = "SessionlessGKNames", templatePackage = "com.facebook.annotationprocessors.gatekeepers"), @GlobalTemplate(inputName = "GKMeta.mustache", outputName = "SessionlessGKMeta", templatePackage = "com.facebook.annotationprocessors.gatekeepers"), @GlobalTemplate(inputName = "gatekeeper_list.mustache", outputName = "sessionless_gatekeeper_list.txt", templatePackage = "com.facebook.annotationprocessors.gatekeepers")}, targets = {MetadataAnnotationTarget.CLASS, MetadataAnnotationTarget.INTERFACE})
@Retention(RetentionPolicy.SOURCE)
public @interface SessionlessGatekeepers {
}
