package com.facebook.metagen.inject;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.mustache.MustacheCodeGenerator", globalTemplateNames = {"MetagennedDIModules.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
public @interface AddToMetagenModule {
}
