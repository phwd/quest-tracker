package X;

import android.database.Cursor;

/* renamed from: X.dM  reason: case insensitive filesystem */
public final class C0622dM extends AbstractC1403yh implements AbstractC0507au {
    public static final C0622dM A00 = new C0622dM();

    public C0622dM() {
        super(1);
    }

    @Override // X.AbstractC0507au
    public final Object A3N(Object obj) {
        Cursor cursor = (Cursor) obj;
        C0514bB.A02(cursor, "it");
        return new C0623dN(cursor);
    }
}
