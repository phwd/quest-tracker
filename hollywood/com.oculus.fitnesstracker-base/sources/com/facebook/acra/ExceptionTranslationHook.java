package com.facebook.acra;

public abstract class ExceptionTranslationHook {
    volatile ExceptionTranslationHook next;

    public abstract Throwable translate$467bebb6() throws Exception;
}
