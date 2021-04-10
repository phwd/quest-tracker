package com.facebook.crudolib.urimap;

import X.AnonymousClass0Lz;
import X.EnumC00600Hp;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    EnumC00600Hp componentType() default EnumC00600Hp.NONE;

    String pattern();

    AnonymousClass0Lz scheme() default AnonymousClass0Lz.FBINTERNAL;

    String template() default "";
}
