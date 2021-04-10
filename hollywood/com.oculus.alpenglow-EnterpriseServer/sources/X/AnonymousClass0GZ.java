package X;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

/* renamed from: X.0GZ  reason: invalid class name */
public class AnonymousClass0GZ implements SQLiteDatabase.CursorFactory {
    public final /* synthetic */ AnonymousClass0GX A00;
    public final /* synthetic */ C03350c9 A01;

    public AnonymousClass0GZ(C03350c9 r1, AnonymousClass0GX r2) {
        this.A01 = r1;
        this.A00 = r2;
    }

    public final Cursor newCursor(SQLiteDatabase sQLiteDatabase, SQLiteCursorDriver sQLiteCursorDriver, String str, SQLiteQuery sQLiteQuery) {
        this.A00.A1X(new AnonymousClass0c6(sQLiteQuery));
        return new SQLiteCursor(sQLiteCursorDriver, str, sQLiteQuery);
    }
}
