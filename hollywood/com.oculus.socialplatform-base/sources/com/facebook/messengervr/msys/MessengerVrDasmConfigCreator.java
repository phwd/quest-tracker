package com.facebook.messengervr.msys;

import X.AnonymousClass0l0;
import com.facebook.msys.mcs.DasmConfigCreator;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;

@DoNotStrip
public class MessengerVrDasmConfigCreator extends DasmConfigCreator {
    @Nullable
    public static MessengerVrDasmConfigCreator sInstance;

    @Override // com.facebook.msys.mcs.DasmConfigCreator
    public native NativeHolder initNativeHolder();

    static {
        AnonymousClass0l0.A06("MessengerVrDasmConfigCreator-jni");
    }
}
