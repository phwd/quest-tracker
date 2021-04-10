package com.facebook.msys.mdm;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseExtensionsRegister {
    @DoNotStrip
    public static native void registerDatabaseExtensionsNative(SqliteHolder sqliteHolder);

    static {
        AnonymousClass1NZ.A00();
    }
}
