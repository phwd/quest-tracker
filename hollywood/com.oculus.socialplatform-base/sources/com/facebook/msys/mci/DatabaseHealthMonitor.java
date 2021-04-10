package com.facebook.msys.mci;

import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import javax.annotation.Nullable;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseHealthMonitor {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static class FatalErrorCallback {
        @DoNotStrip
        public void onError(Throwable th) {
        }
    }

    @DoNotStrip
    private native void fixAllNative();

    @DoNotStrip
    public static native NativeHolder initNativeHolder(String str, String str2, @Nullable FatalErrorCallback fatalErrorCallback);

    static {
        AnonymousClass1NX.A00();
    }

    @DoNotStrip
    public DatabaseHealthMonitor(String str, String str2, @Nullable FatalErrorCallback fatalErrorCallback) {
        this.mNativeHolder = initNativeHolder(str, str2, fatalErrorCallback);
    }

    public void fixAll() {
        if (Execution.getExecutionContext() == 2) {
            fixAllNative();
            return;
        }
        throw new RuntimeException("fixAll must be called in disk IO thread");
    }
}
