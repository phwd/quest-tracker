package com.facebook.msys.util;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class NotificationScope {
    @DoNotStrip
    public final McfReferenceHolder mMcfReference = new McfReferenceHolder();
    @DoNotStrip
    public final NativeHolder mNativeHolder = initNativeHolder(this);

    @DoNotStrip
    public static native NativeHolder initNativeHolder(NotificationScope notificationScope);

    static {
        AnonymousClass1NZ.A00();
    }
}
