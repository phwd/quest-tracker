package com.facebook.privacy.datacollection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivacyDecision {
    public static final String INTERNAL_ONLY_PENDING_PRIVACY_REVIEW = "internal_only_pending_privacy_review";

    String gatingDescription() default "";

    String value();
}
