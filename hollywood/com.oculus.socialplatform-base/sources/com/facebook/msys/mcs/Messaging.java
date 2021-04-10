package com.facebook.msys.mcs;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.proguard.annotations.DoNotStrip;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Deprecated
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public class Messaging {
    @DoNotStrip
    public static native boolean setContactSyncParamsNative(SqliteHolder sqliteHolder, @Nullable Integer num, @Nullable Integer num2);

    @DoNotStrip
    public static native boolean setMailboxSyncParamsNative(SqliteHolder sqliteHolder, @Nullable Integer num, @Nullable Integer num2, @Nullable Integer num3, @Nullable Integer num4, @Nullable Float f, @Nullable Integer num5, @Nullable Integer num6, @Nullable String str);

    static {
        AnonymousClass1NZ.A00();
    }
}
