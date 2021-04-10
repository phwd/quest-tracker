package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;

/* access modifiers changed from: package-private */
@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public abstract class GwtFluentFutureCatchingSpecialization<V> implements ListenableFuture<V> {
    GwtFluentFutureCatchingSpecialization() {
    }
}
