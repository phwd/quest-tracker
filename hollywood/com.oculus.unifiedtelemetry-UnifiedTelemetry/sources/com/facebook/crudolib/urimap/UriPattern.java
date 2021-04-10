package com.facebook.crudolib.urimap;

import X.Il;
import X.Mn;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    Il componentType() default Il.NONE;

    String pattern();

    Mn scheme() default Mn.FBINTERNAL;

    String template() default "";
}
