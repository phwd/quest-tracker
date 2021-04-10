package com.facebook.inject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectorModule {
    @Deprecated
    boolean autoRequire() default true;

    boolean isRoot() default false;

    boolean nonTransform() default false;

    Class<?>[] requireModules() default {};
}
