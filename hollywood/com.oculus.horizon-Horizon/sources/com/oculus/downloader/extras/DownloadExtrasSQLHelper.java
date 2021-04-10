package com.oculus.downloader.extras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.oculus.downloader.extras.contract.ExtrasDatabase;

public class DownloadExtrasSQLHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String OCULUS_DOWNLOADER = "oculus-downloader";

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(ExtrasDatabase.SQL_TABLE_CREATE);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(ExtrasDatabase.SQL_TABLE_DROP_IF_EXIST);
        onCreate(sQLiteDatabase);
    }

    public DownloadExtrasSQLHelper(Context context) {
        super(context.getApplicationContext(), OCULUS_DOWNLOADER, (SQLiteDatabase.CursorFactory) null, 2);
    }
}
