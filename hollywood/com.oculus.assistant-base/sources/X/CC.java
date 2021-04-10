package X;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public final /* synthetic */ class CC extends C0218Kk implements AbstractC0496aj {
    public CC(KC kc) {
        super(0, kc, KC.class, "doExecuteRawQuery", "doExecuteRawQuery()Landroid/database/Cursor;");
    }

    @Override // X.AbstractC0496aj
    public final Object A3M() {
        String str;
        KA ka = ((KC) this.receiver).A00;
        int[] iArr = ka.A03;
        int length = iArr.length;
        String[] strArr = new String[length];
        ka.A01();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            Object obj = ka.A04[iArr[i]];
            if (obj != null) {
                str = obj.toString();
            } else {
                str = null;
            }
            strArr[i2] = str;
            i++;
            i2 = i3;
        }
        Cursor rawQuery = ((SQLiteDatabase) ((K5) ka.A00.A00.getValue()).A02.A01.get()).rawQuery(ka.A02, strArr);
        C0514bB.A01(rawQuery, "cursor");
        rawQuery.getCount();
        return rawQuery;
    }
}
