package com.facebook.crudolib.urimap;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator")
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    AccessScope accessScope() default AccessScope.SAME_APP;

    @Deprecated
    UriCallerScope callerScope() default UriCallerScope.PUBLIC;
}
