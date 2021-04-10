package com.oculus.autoupdates.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.oculus.autoupdates.database.AutoUpdatesDBContract;

public class AutoUpdatesDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "autoupdates.db";
    private static final int VERSION = 2;

    public AutoUpdatesDBHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(AutoUpdatesDBContract.AutoUpdatesDBTable.SQL_CREATE);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS autoupdates");
        onCreate(sQLiteDatabase);
    }
}
