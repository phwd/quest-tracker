package com.facebook.common.plugins;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.LocalTemplate;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.PluginsGenerator", globalTemplateNames = {"PluginsManager.mustache", "SocketIDs.mustache"}, localTemplates = {@LocalTemplate(inputName = "PluginOrSocket.mustache", outputNameFormat = "{{className}}"), @LocalTemplate(inputName = "SocketInterface.mustache", outputNameFormat = "{{socketInterfaceSimpleName}}")}, targets = {MetadataAnnotationTarget.INTERFACE, MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface Plugins {
}
