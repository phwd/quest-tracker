package com.facebook.storage.attribution;

import X.EnumC04270hF;
import X.EnumC04280hG;

public @interface Path {
    EnumC04270hF infra() default EnumC04270hF.ANDROID_CONTEXT;

    EnumC04280hG location();

    String nameOrPattern();
}
