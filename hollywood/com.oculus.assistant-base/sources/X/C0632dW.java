package X;

import android.database.Cursor;

/* renamed from: X.dW  reason: case insensitive filesystem */
public final class C0632dW extends C0963pf {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0632dW(Cursor cursor) {
        super(new Object[]{cursor.getString(0), cursor.getString(1)});
        C0514bB.A02(cursor, "cursor");
    }
}
