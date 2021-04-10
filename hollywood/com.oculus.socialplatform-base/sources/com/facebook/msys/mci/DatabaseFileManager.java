package com.facebook.msys.mci;

import X.AnonymousClass1NX;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseFileManager {
    @DoNotStrip
    public static native void maybeDecryptDatabaseNative(Database database);

    static {
        AnonymousClass1NX.A00();
    }
}
