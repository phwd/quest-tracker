package com.facebook.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScoped {
    boolean enableScopeValidation() default true;

    boolean useObjectLock() default false;
}
