package com.facebook.msys.mca;

import X.AnonymousClass1NZ;
import X.AnonymousClass1YZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class NativeMailboxCallback<T> implements AnonymousClass1YZ<T> {
    @DoNotStrip
    public NativeHolder mNativeHolder;

    @Override // X.AnonymousClass1YZ
    @DoNotStrip
    public native void onCompletion(Object obj);

    static {
        AnonymousClass1NZ.A00();
    }
}
