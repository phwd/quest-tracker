package com.oculus.localmedia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.oculus.localmedia.database.LocalMediaContract;

public class LocalMediaSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 5;
    public static final String LOCAL_MEDIA = "localmedia";

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(LocalMediaContract.ExtrasTable.SQL_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(LocalMediaContract.ExtrasTable.SQL_TABLE_DROP_IF_EXIST);
        onCreate(sQLiteDatabase);
    }

    public LocalMediaSQLHelper(Context context, SQLiteDatabase.CursorFactory cursorFactory) {
        super(context.getApplicationContext(), LOCAL_MEDIA, cursorFactory, 5);
    }
}
