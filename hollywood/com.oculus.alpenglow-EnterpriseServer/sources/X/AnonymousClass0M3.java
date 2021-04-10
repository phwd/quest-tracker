package X;

import android.database.sqlite.SQLiteStatement;

/* renamed from: X.0M3  reason: invalid class name */
public final class AnonymousClass0M3 extends AnonymousClass0c6 implements AbstractC03360cA {
    public final SQLiteStatement A00;

    @Override // X.AbstractC03360cA
    public final long A2R() {
        return this.A00.executeInsert();
    }

    @Override // X.AbstractC03360cA
    public final int A2S() {
        return this.A00.executeUpdateDelete();
    }

    public AnonymousClass0M3(SQLiteStatement sQLiteStatement) {
        super(sQLiteStatement);
        this.A00 = sQLiteStatement;
    }
}
