package com.facebook.storage.attribution;

public @interface Path {
    Infra infra() default Infra.ANDROID_CONTEXT;

    Location location();

    String nameOrPattern();
}
