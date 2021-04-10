package X;

import android.database.Cursor;

/* renamed from: X.dS  reason: case insensitive filesystem */
public final class C0628dS extends AbstractC1403yh implements AbstractC0507au {
    public static final C0628dS A00 = new C0628dS();

    public C0628dS() {
        super(1);
    }

    @Override // X.AbstractC0507au
    public final Object A3N(Object obj) {
        Cursor cursor = (Cursor) obj;
        C0514bB.A02(cursor, "it");
        return new C0629dT(cursor);
    }
}
