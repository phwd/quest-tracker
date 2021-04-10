package com.facebook.messengervr.msys;

import X.AnonymousClass0l0;
import android.annotation.SuppressLint;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class MessengerVrDatabaseSchemaDeployer {
    @DoNotStrip
    public static native int deployCrossDatabaseSchema(SqliteHolder sqliteHolder);

    @DoNotStrip
    public static native int deployInMemorySchema(SqliteHolder sqliteHolder);

    @DoNotStrip
    public static native int deployPersistentSchema(SqliteHolder sqliteHolder);

    static {
        AnonymousClass0l0.A06("messengervrDatabaseSchemaDeployer-jni");
    }
}
