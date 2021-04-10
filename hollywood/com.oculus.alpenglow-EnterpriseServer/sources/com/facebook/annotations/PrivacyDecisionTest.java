package com.facebook.annotations;

import android.annotation.SuppressLint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@SuppressLint({"JavatestsIncorrectFolder"})
public @interface PrivacyDecisionTest {
    String value();
}
