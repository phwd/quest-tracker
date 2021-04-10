package com.facebook.msys.mci;

import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseConnectionSettings {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    public native void config(int i, boolean z, int i2, boolean z2, boolean z3, int i3, boolean z4, boolean z5, boolean z6, int i4);

    static {
        AnonymousClass1NX.A00();
    }

    @DoNotStrip
    public DatabaseConnectionSettings(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
