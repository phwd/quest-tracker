package com.facebook.msys.mci;

import X.AnonymousClass1Nh;
import android.annotation.SuppressLint;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;
import java.util.Set;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@SuppressLint({"MissingNativeLoadLibrary"})
public class PrivacyContext {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    @DoNotStrip
    public static native PrivacyContext newPrivacyContext(Set<String> set);

    @DoNotStrip
    public native Set<String> getKeys();

    static {
        AnonymousClass1Nh.A00();
    }

    @DoNotStrip
    public PrivacyContext(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
