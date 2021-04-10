package X;

import android.database.Cursor;

public final class D2 {
    public final D1 A00;

    public D2(D1 d1) {
        this.A00 = d1;
    }

    public final Cursor A00(D0 d0) {
        Object[] A2j = d0.A2j();
        if (A2j[5] != null) {
            return this.A00.A2D().rawQuery((String) A2j[5], (String[]) A2j[6]);
        }
        return this.A00.A2D().query((String) A2j[0], (String[]) A2j[1], (String) A2j[2], (String[]) A2j[3], null, null, (String) A2j[4]);
    }
}
