package com.facebook.inject;

import android.content.Context;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public interface Injector {
    @Deprecated
    <T> T getInstance(Key<T> key, Context context);

    <T extends Scope> T getScope(Class<? extends Annotation> cls);
}
