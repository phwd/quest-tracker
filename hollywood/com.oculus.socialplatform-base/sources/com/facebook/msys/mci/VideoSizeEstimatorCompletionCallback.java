package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class VideoSizeEstimatorCompletionCallback {
    @DoNotStrip
    public NativeHolder mNativeHolder;

    private native void failureNative(Throwable th);

    private native void successNative(long j);

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public VideoSizeEstimatorCompletionCallback(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
