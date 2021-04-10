package X;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: X.9k  reason: invalid class name and case insensitive filesystem */
public final class C00809k extends SQLiteOpenHelper {
    public C00809k(Context context) {
        super(context, "interaction_log.db", (SQLiteDatabase.CursorFactory) null, 1);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS entries (_id INTEGER PRIMARY KEY,timestamp default current_timestamp,event TEXT,event_data TEXT,interaction TEXT)");
        sQLiteDatabase.execSQL("CREATE TRIGGER IF NOT EXISTS trigger_entry_limit AFTER INSERT ON entries BEGIN delete from entries where _id =(select min(_id) from entries ) and (select count(*) from entries )=500; END;");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS entries");
        sQLiteDatabase.execSQL("DROP TRIGGER IF EXISTS trigger_entry_limit");
        onCreate(sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i, i2);
    }
}
