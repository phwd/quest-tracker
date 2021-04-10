package com.oculus.database.supplier;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.List;

@SuppressLint({"BadSuperClassSQLiteOpenHelper.SharedSQLiteOpenHelper"})
public class SharedSQLiteOpenHelper extends SQLiteOpenHelper {
    private final SharedSQLiteDbHelper mSharedSQLiteDbHelper;

    public SharedSQLiteOpenHelper(Context context, String str, List<? extends SharedSQLiteSchemaPart> list, int i, DatabaseErrorHandler databaseErrorHandler) {
        super(context, str, null, 200, databaseErrorHandler);
        this.mSharedSQLiteDbHelper = new SharedSQLiteDbHelper(list, i, context);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.mSharedSQLiteDbHelper.onCreate(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.mSharedSQLiteDbHelper.onUpgrade(sQLiteDatabase, i, i2);
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        this.mSharedSQLiteDbHelper.onOpen(sQLiteDatabase);
    }
}
