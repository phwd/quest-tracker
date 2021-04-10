package com.facebook.crudolib.urimap;

import X.EnumC0128Mh;
import X.Mi;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator", globalTemplateNames = {"DfaUriMap.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    EnumC0128Mh accessScope() default EnumC0128Mh.SAME_APP;

    @Deprecated
    Mi callerScope() default Mi.PUBLIC;
}
