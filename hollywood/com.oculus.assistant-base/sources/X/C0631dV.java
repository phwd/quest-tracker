package X;

import android.database.Cursor;

/* renamed from: X.dV  reason: case insensitive filesystem */
public final class C0631dV extends AbstractC1403yh implements AbstractC0507au {
    public static final C0631dV A00 = new C0631dV();

    public C0631dV() {
        super(1);
    }

    @Override // X.AbstractC0507au
    public final Object A3N(Object obj) {
        Cursor cursor = (Cursor) obj;
        C0514bB.A02(cursor, "it");
        return new C0632dW(cursor);
    }
}
