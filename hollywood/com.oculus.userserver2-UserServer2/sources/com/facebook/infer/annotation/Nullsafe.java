package com.facebook.infer.annotation;

import X.EnumC0191Xt;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.annotation.Nonnull;
import javax.annotation.meta.TypeQualifierDefault;
import kotlin.annotations.jvm.UnderMigration;

@Target({ElementType.TYPE})
@UnderMigration(status = EnumC0191Xt.STRICT)
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
