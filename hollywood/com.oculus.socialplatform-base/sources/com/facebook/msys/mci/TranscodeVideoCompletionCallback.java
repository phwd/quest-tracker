package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TranscodeVideoCompletionCallback {
    @DoNotStrip
    public NativeHolder mNativeHolder;

    private native void failureNative(Throwable th);

    private native void successNative(@Nullable String str, double d, double d2, double d3, double d4, double d5, double d6, double d7);

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public TranscodeVideoCompletionCallback(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
