package X;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* renamed from: X.0Gc  reason: invalid class name and case insensitive filesystem */
public class C01310Gc extends SQLiteOpenHelper {
    public boolean A00;
    public final AnonymousClass0GR A01;
    public final C03350c9[] A02;

    public final synchronized AnonymousClass0GQ A01() {
        AnonymousClass0GQ r0;
        this.A00 = false;
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        if (this.A00) {
            close();
            r0 = A01();
        } else {
            r0 = A00(writableDatabase);
        }
        return r0;
    }

    @Override // java.lang.AutoCloseable
    public final synchronized void close() {
        super.close();
        this.A02[0] = null;
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.A00 = true;
        this.A01.A04(A00(sQLiteDatabase), i, i2);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        this.A00 = true;
        this.A01.A05(A00(sQLiteDatabase), i, i2);
    }

    public C01310Gc(Context context, String str, C03350c9[] r9, AnonymousClass0GR r10) {
        super(context, str, null, r10.A00, new C01300Gb(r10, r9));
        this.A01 = r10;
        this.A02 = r9;
    }

    private final C03350c9 A00(SQLiteDatabase sQLiteDatabase) {
        C03350c9[] r2 = this.A02;
        C03350c9 r0 = r2[0];
        if (r0 == null || r0.A00 != sQLiteDatabase) {
            r2[0] = new C03350c9(sQLiteDatabase);
        }
        return r2[0];
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        this.A01.A03(A00(sQLiteDatabase));
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        if (!this.A00) {
            this.A01.A02(A00(sQLiteDatabase));
        }
    }

    public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
        A00(sQLiteDatabase);
    }
}
