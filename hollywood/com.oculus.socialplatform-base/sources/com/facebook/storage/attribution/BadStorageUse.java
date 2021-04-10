package com.facebook.storage.attribution;

import X.EnumC03230lm;
import X.EnumC03240lo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface BadStorageUse {
    EnumC03230lm location();

    EnumC03240lo reason();
}
