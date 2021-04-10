package com.facebook.crudolib.urimap;

import X.AnonymousClass0NB;
import X.AnonymousClass0NC;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator", globalTemplateNames = {"DfaUriMap.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    AnonymousClass0NB accessScope() default AnonymousClass0NB.SAME_APP;

    @Deprecated
    AnonymousClass0NC callerScope() default AnonymousClass0NC.PUBLIC;
}
