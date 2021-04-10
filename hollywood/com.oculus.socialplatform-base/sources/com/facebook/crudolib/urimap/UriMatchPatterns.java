package com.facebook.crudolib.urimap;

import X.AnonymousClass0Lt;
import X.EnumC00870Lu;
import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator", globalTemplateNames = {"DfaUriMap.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    AnonymousClass0Lt accessScope() default AnonymousClass0Lt.SAME_APP;

    @Deprecated
    EnumC00870Lu callerScope() default EnumC00870Lu.PUBLIC;
}
