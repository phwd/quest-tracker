package com.facebook.msys.util;

import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;

@DoNotStrip
public interface Provider<T> {
    @DoNotStrip
    @Nullable
    T get();
}
