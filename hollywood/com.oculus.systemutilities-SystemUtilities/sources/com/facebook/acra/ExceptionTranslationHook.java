package com.facebook.acra;

import java.util.Map;

public abstract class ExceptionTranslationHook {
    volatile ExceptionTranslationHook next;

    public abstract Throwable translate(Throwable th, Map<String, String> map) throws Exception;
}
