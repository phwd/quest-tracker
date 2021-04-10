package X;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: X.8o  reason: invalid class name and case insensitive filesystem */
public final class C00628o extends SQLiteOpenHelper {
    public final String A00;
    public final String A01;
    public final String A02;
    public final String A03;

    public C00628o(String str, Context context) {
        super(context, "federated_learning_store.db", (SQLiteDatabase.CursorFactory) null, 1);
        String A04 = AnonymousClass08.A04(str, "_keyboard_logs");
        this.A01 = A04;
        StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        sb.append(A04);
        sb.append(" (");
        sb.append("_id");
        sb.append(" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        sb.append("timestamp");
        sb.append(" TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,");
        sb.append("input_text");
        sb.append(" TEXT NOT NULL,");
        sb.append("surface");
        sb.append(" TEXT NOT NULL)");
        this.A02 = sb.toString();
        this.A03 = AnonymousClass08.A04("DROP TABLE IF EXISTS ", A04);
        this.A00 = AnonymousClass08.A04("SELECT input_text FROM ", A04);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.A02);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL(this.A03);
        onCreate(sQLiteDatabase);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        onUpgrade(sQLiteDatabase, i2, i);
    }
}
