package com.facebook.infer.annotation;

import X.EnumC08620wx;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import kotlin.annotations.jvm.UnderMigration;

@Target({ElementType.TYPE})
@UnderMigration(status = EnumC08620wx.STRICT)
@Retention(RetentionPolicy.CLASS)
@TypeQualifierDefault({ElementType.METHOD, ElementType.PARAMETER})
@Nonnull
public @interface Nullsafe {

    public enum Mode {
        LOCAL,
        STRICT
    }

    public @interface TrustList {
        boolean trustAll() default false;

        Class[] value();
    }

    TrustList trustOnly() default @TrustList(trustAll = true, value = {});

    Mode value();
}
