package X;

import android.database.Cursor;

/* renamed from: X.dT  reason: case insensitive filesystem */
public final class C0629dT extends C0963pf {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0629dT(Cursor cursor) {
        super(new Object[]{Integer.valueOf(cursor.getInt(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5)});
        C0514bB.A02(cursor, "cursor");
    }
}
