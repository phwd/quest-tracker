package com.oculus.database.supplier;

import android.database.sqlite.SQLiteDatabase;

public abstract class SharedSQLiteSchemaPart {
    public final String name;
    public final int version;

    public void A01(SQLiteDatabase sQLiteDatabase) {
    }

    public void A02(SQLiteDatabase sQLiteDatabase) {
    }

    public abstract void A03(SQLiteDatabase sQLiteDatabase);

    public abstract void A04(SQLiteDatabase sQLiteDatabase, int i, int i2);

    public SharedSQLiteSchemaPart(String str, int i) {
        this.name = str;
        this.version = i;
    }
}
