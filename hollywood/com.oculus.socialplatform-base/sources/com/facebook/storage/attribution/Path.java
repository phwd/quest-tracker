package com.facebook.storage.attribution;

import X.EnumC03220ll;
import X.EnumC03230lm;

public @interface Path {
    EnumC03220ll infra() default EnumC03220ll.ANDROID_CONTEXT;

    EnumC03230lm location();

    String nameOrPattern();
}
