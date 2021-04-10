package com.facebook.msys.mcd;

import X.AnonymousClass1NZ;
import android.annotation.SuppressLint;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.msys.mci.Database;
import com.facebook.msys.mci.DatabaseConnectionSettings;
import com.facebook.msys.mci.SqliteHolder;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class DatabaseOpenCallback extends Database.OpenCallback {
    @DoNotStrip
    private native boolean onConfigNative(SqliteHolder sqliteHolder, int i, boolean z, long j);

    static {
        AnonymousClass1NZ.A00();
    }

    @Override // com.facebook.msys.mci.Database.OpenCallback
    @DoNotStrip
    public final boolean onConfig(SqliteHolder sqliteHolder, int i, boolean z, long j, DatabaseConnectionSettings databaseConnectionSettings) {
        boolean onConfig = super.onConfig(sqliteHolder, i, z, j, databaseConnectionSettings);
        if (!onConfigNative(sqliteHolder, i, z, j) || !onConfig) {
            return false;
        }
        return true;
    }
}
