package com.facebook.common.lsplugins.annotations;

import X.AnonymousClass1GP;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
public @interface PluginInterfaceMethod {
    AnonymousClass1GP type() default AnonymousClass1GP.CALL_AND_RETURN_ALL;
}
