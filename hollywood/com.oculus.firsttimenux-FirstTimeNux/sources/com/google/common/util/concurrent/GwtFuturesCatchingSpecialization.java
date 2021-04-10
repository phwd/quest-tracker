package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
abstract class GwtFuturesCatchingSpecialization {
    GwtFuturesCatchingSpecialization() {
    }
}
