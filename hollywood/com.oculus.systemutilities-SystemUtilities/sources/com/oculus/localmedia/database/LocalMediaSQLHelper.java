package com.oculus.localmedia.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.oculus.localmedia.database.LocalMediaContract;

public class LocalMediaSQLHelper extends SQLiteOpenHelper {
    public LocalMediaSQLHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context.getApplicationContext(), "localmedia", factory, 5);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalMediaContract.ExtrasTable.SQL_TABLE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS localmedia_metadata");
        onCreate(db);
    }
}
