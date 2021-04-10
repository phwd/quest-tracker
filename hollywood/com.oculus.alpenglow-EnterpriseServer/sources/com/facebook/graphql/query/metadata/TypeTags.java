package com.facebook.graphql.query.metadata;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.GraphQLTypeTagCodeGenerator", globalTemplateNames = {"TypeTagResolver.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.SOURCE)
public @interface TypeTags {
}
