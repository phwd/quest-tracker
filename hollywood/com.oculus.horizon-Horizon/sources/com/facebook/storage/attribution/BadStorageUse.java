package com.facebook.storage.attribution;

import X.EnumC04280hG;
import X.EnumC04290hI;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface BadStorageUse {
    EnumC04280hG location();

    EnumC04290hI reason();
}
