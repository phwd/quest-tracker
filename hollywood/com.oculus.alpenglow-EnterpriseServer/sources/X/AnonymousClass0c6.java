package X;

import android.database.sqlite.SQLiteProgram;

/* renamed from: X.0c6  reason: invalid class name */
public class AnonymousClass0c6 implements AnonymousClass0GW {
    public final SQLiteProgram A00;

    @Override // X.AnonymousClass0GW
    public final void A1L(int i, byte[] bArr) {
        this.A00.bindBlob(i, bArr);
    }

    @Override // X.AnonymousClass0GW
    public final void A1P(int i, double d) {
        this.A00.bindDouble(i, d);
    }

    @Override // X.AnonymousClass0GW
    public final void A1Q(int i, long j) {
        this.A00.bindLong(i, j);
    }

    @Override // X.AnonymousClass0GW
    public final void A1U(int i) {
        this.A00.bindNull(i);
    }

    @Override // X.AnonymousClass0GW
    public final void A1W(int i, String str) {
        this.A00.bindString(i, str);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.A00.close();
    }

    public AnonymousClass0c6(SQLiteProgram sQLiteProgram) {
        this.A00 = sQLiteProgram;
    }
}
