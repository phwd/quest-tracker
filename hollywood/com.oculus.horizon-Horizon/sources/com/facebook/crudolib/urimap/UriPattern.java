package com.facebook.crudolib.urimap;

import X.AnonymousClass0NH;
import X.EnumC01040Ji;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    EnumC01040Ji componentType() default EnumC01040Ji.NONE;

    String pattern();

    AnonymousClass0NH scheme() default AnonymousClass0NH.FBINTERNAL;

    String template() default "";
}
