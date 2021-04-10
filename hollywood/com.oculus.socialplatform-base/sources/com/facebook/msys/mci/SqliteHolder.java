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
public class SqliteHolder {
    @DoNotStrip
    public final NativeHolder mNativeHolder;

    static {
        AnonymousClass1NX.A00();
    }

    @DoNotStrip
    public SqliteHolder(NativeHolder nativeHolder) {
        this.mNativeHolder = nativeHolder;
    }
}
