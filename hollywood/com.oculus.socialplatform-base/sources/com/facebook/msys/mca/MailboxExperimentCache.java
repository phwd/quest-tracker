package com.facebook.msys.mca;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.simplejni.NativeHolder;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MailboxExperimentCache {
    @DoNotStrip
    public final NativeHolder mNativeHolder = initNativeHolder();

    @DoNotStrip
    public static native NativeHolder initNativeHolder();

    public native void onInit(SqliteHolder sqliteHolder);

    static {
        AnonymousClass1NZ.A00();
    }
}
