package com.facebook.msys.mca;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.concurrent.NotThreadSafe;

@DoNotStrip
@NotThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class System {
    @DoNotStrip
    public final NativeHolder mNativeHolder = initNativeHolder();

    @DoNotStrip
    public static native NativeHolder initNativeHolder();

    static {
        AnonymousClass1NZ.A00();
    }
}
