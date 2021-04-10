package X;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import java.io.IOException;
import java.util.List;

/* renamed from: X.0c9  reason: invalid class name and case insensitive filesystem */
public final class C03350c9 implements AnonymousClass0GQ {
    public static final String[] A01 = {"", " OR ROLLBACK ", " OR ABORT ", " OR FAIL ", " OR IGNORE ", " OR REPLACE "};
    public static final String[] A02 = new String[0];
    public final SQLiteDatabase A00;

    @Override // X.AnonymousClass0GQ
    public final void A1H() {
        this.A00.beginTransaction();
    }

    @Override // X.AnonymousClass0GQ
    public final AbstractC03360cA A1l(String str) {
        return new AnonymousClass0M3(this.A00.compileStatement(str));
    }

    @Override // X.AnonymousClass0GQ
    public final void A2K() {
        this.A00.endTransaction();
    }

    @Override // X.AnonymousClass0GQ
    public final void A2Q(String str) throws SQLException {
        this.A00.execSQL(str);
    }

    @Override // X.AnonymousClass0GQ
    public final List<Pair<String, String>> A30() {
        return this.A00.getAttachedDbs();
    }

    @Override // X.AnonymousClass0GQ
    public final boolean A58() {
        return this.A00.inTransaction();
    }

    @Override // X.AnonymousClass0GQ
    public final Cursor A73(AnonymousClass0GX r6) {
        return this.A00.rawQueryWithFactory(new AnonymousClass0GZ(this, r6), r6.A4W(), A02, null);
    }

    @Override // X.AnonymousClass0GQ
    public final Cursor A74(String str) {
        return A73(new C03370cB(str));
    }

    @Override // X.AnonymousClass0GQ
    public final void A8D() {
        this.A00.setTransactionSuccessful();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.A00.close();
    }

    @Override // X.AnonymousClass0GQ
    public final String getPath() {
        return this.A00.getPath();
    }

    @Override // X.AnonymousClass0GQ
    public final boolean isOpen() {
        return this.A00.isOpen();
    }

    public C03350c9(SQLiteDatabase sQLiteDatabase) {
        this.A00 = sQLiteDatabase;
    }
}
