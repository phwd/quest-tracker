package com.facebook.storage.attribution;

import X.EnumC05420jL;
import X.EnumC05430jM;

public @interface Path {
    EnumC05420jL infra() default EnumC05420jL.ANDROID_CONTEXT;

    EnumC05430jM location();

    String nameOrPattern();
}
