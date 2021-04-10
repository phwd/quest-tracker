package com.oculus.database.supplier;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.google.common.base.Preconditions;

public abstract class SharedSQLiteSchemaPart {
    private final String name;
    private final int version;

    public abstract void clearAllData(SQLiteDatabase sQLiteDatabase);

    /* access modifiers changed from: protected */
    public void onAppUpgrade(SQLiteDatabase sQLiteDatabase) {
    }

    /* access modifiers changed from: protected */
    public abstract void onCreate(SQLiteDatabase sQLiteDatabase);

    /* access modifiers changed from: protected */
    public void onOpenAfterTransaction(SQLiteDatabase sQLiteDatabase) {
    }

    /* access modifiers changed from: protected */
    public abstract void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2);

    protected SharedSQLiteSchemaPart(String str, int i) {
        Preconditions.checkArgument(i < 1 ? false : true, "Version must be positive");
        this.name = str;
        this.version = i;
    }

    public final String getName() {
        return this.name;
    }

    public final int getVersion() {
        return this.version;
    }

    @VisibleForTesting
    public final void create(SQLiteDatabase sQLiteDatabase) {
        onCreate(sQLiteDatabase);
    }

    @VisibleForTesting
    public final void upgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }
}
