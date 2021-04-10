package X;

import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.util.WeakHashMap;

public final class KB {
    public final KA A00;

    public KB(KA ka) {
        C0514bB.A02(ka, "base");
        this.A00 = ka;
    }

    public static final SQLiteStatement A00(KB kb) {
        KA ka = kb.A00;
        SQLiteStatement compileStatement = ((SQLiteDatabase) ((K5) ka.A00.A00.getValue()).A02.A01.get()).compileStatement(ka.A02);
        ka.A01();
        int i = 0;
        for (int i2 : ka.A03) {
            i++;
            DatabaseUtils.bindObjectToProgram(compileStatement, i, ka.A04[i2]);
        }
        C0514bB.A01(compileStatement, "statement");
        return compileStatement;
    }

    public static final void A01(KB kb) {
        KA ka = kb.A00;
        C0954pW pWVar = ((K5) ka.A00.A00.getValue()).A01;
        SQLiteDatabase A2D = pWVar.A01.A2D();
        WeakHashMap weakHashMap = ((C0137Cx) C0836jg.A07.get()).A00;
        C0136Cw cw = (C0136Cw) weakHashMap.get(A2D);
        if (cw == null) {
            cw = new C0136Cw();
            weakHashMap.put(A2D, cw);
        }
        int i = cw.A00;
        boolean z = false;
        if (i > 0) {
            z = true;
        }
        C0836jg jgVar = cw.A01;
        boolean z2 = false;
        if (jgVar != null) {
            z2 = true;
        }
        if (z != z2) {
            C0139Dd.A0Q("DirectTransaction", "Inconsistent transaction state: depth=%d, transaction=%s", Integer.valueOf(i), String.valueOf(jgVar));
        } else if (jgVar != null) {
            new C0953pV(pWVar, jgVar).A3o(ka.A05, ka.A01);
            return;
        }
        throw new IllegalStateException("Not currently inside a transaction");
    }
}
