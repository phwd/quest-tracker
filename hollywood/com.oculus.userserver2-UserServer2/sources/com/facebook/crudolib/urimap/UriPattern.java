package com.facebook.crudolib.urimap;

import X.J8;
import X.Md;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    J8 componentType() default J8.NONE;

    String pattern();

    Md scheme() default Md.FBINTERNAL;

    String template() default "";
}
