package com.facebook.storage.attribution;

public @interface Path {
    boolean forceOwnership() default false;

    Infra infra() default Infra.ANDROID_CONTEXT;

    Location location();

    String nameOrPattern();
}
