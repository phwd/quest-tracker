package X;

import android.database.Cursor;
import java.util.HashMap;

/* renamed from: X.Zr  reason: case insensitive filesystem */
public final class C0461Zr {
    public static final HashMap A00 = new HashMap();

    public static boolean A00(Cursor cursor, int i) {
        return i >= 0 && cursor.getInt(i) > 0;
    }
}
