package com.facebook.cameracore.util;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface Reference<T> {
    @DoNotStrip
    T get();

    @DoNotStrip
    void release();
}
