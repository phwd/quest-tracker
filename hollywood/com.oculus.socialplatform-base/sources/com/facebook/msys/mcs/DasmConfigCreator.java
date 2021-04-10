package com.facebook.msys.mcs;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class DasmConfigCreator {
    @DoNotStrip
    public final NativeHolder mNativeHolder = initNativeHolder();

    public abstract NativeHolder initNativeHolder();
}
