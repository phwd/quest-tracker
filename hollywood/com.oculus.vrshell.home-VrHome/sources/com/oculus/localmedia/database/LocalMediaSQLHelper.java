package com.oculus.localmedia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.oculus.localmedia.database.LocalMediaContract;

public class LocalMediaSQLHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    public static final String LOCAL_MEDIA = "localmedia";

    public LocalMediaSQLHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context.getApplicationContext(), LOCAL_MEDIA, factory, 5);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalMediaContract.ExtrasTable.SQL_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LocalMediaContract.ExtrasTable.SQL_TABLE_DROP_IF_EXIST);
        onCreate(db);
    }
}
