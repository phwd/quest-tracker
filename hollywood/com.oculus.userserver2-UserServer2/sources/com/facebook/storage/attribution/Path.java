package com.facebook.storage.attribution;

import X.QH;
import X.QI;

public @interface Path {
    QI infra() default QI.ANDROID_CONTEXT;

    QH location();

    String nameOrPattern();
}
