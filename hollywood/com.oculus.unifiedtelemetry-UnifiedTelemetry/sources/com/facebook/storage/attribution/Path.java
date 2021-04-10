package com.facebook.storage.attribution;

import X.mP;
import X.mQ;

public @interface Path {
    mP infra() default mP.ANDROID_CONTEXT;

    mQ location();

    String nameOrPattern();
}
