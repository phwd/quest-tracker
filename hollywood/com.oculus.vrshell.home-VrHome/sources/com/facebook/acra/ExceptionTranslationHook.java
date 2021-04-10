package com.facebook.acra;

import java.util.Map;
import javax.annotation.Nullable;

public abstract class ExceptionTranslationHook {
    volatile ExceptionTranslationHook next;

    @Nullable
    public abstract Throwable translate(Throwable th, Map<String, String> map) throws Exception;
}
