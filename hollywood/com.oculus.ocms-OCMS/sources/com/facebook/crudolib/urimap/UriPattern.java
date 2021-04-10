package com.facebook.crudolib.urimap;

import com.facebook.common.componentmap.ComponentMapType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface UriPattern {
    ComponentMapType componentType() default ComponentMapType.NONE;

    String pattern();

    UriScheme scheme() default UriScheme.FBINTERNAL;

    String template() default "";
}
