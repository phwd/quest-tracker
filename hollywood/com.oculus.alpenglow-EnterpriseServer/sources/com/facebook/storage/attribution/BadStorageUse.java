package com.facebook.storage.attribution;

import X.EnumC05430jM;
import X.EnumC05440jO;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface BadStorageUse {
    EnumC05430jM location();

    EnumC05440jO reason();
}
