package com.facebook.crudolib.urimap;

import X.AnonymousClass0Jc;
import X.AnonymousClass0NF;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    AnonymousClass0Jc componentType() default AnonymousClass0Jc.NONE;

    String pattern();

    AnonymousClass0NF scheme() default AnonymousClass0NF.FBINTERNAL;

    String template() default "";
}
