package com.facebook.crudolib.urimap;

import X.AnonymousClass0N9;
import X.AnonymousClass0NA;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator", globalTemplateNames = {"DfaUriMap.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    AnonymousClass0N9 accessScope() default AnonymousClass0N9.SAME_APP;

    @Deprecated
    AnonymousClass0NA callerScope() default AnonymousClass0NA.PUBLIC;
}
