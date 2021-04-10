package com.facebook.crudolib.urimap;

import com.facebook.metagen.CodeGeneratorMetadataAnnotation;
import com.facebook.metagen.MetadataAnnotationTarget;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@CodeGeneratorMetadataAnnotation(generator = "com.facebook.metagen.generator.custom.DfaUriMapCodeGenerator", globalTemplateNames = {"DfaUriMap.mustache"}, targets = {MetadataAnnotationTarget.CLASS})
@Retention(RetentionPolicy.CLASS)
public @interface UriMatchPatterns {
    String[] accessDomains() default {};

    AccessScope accessScope() default AccessScope.SAME_APP;

    boolean allowUnmatchedQueryParams() default false;

    @Deprecated
    UriCallerScope callerScope() default UriCallerScope.PUBLIC;

    UriMapProduct[] enabledProducts() default {};

    int fragment() default 248;

    Class<?>[] fromRoute() default {};

    boolean isPlaceholderRoute() default false;

    UriPattern[] value() default {};
}
