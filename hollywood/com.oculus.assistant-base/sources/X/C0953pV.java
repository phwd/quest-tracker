package X;

import android.database.sqlite.SQLiteDatabase;
import android.os.Trace;
import java.util.ArrayList;

/* renamed from: X.pV  reason: case insensitive filesystem */
public final class C0953pV implements K4 {
    public final C0836jg A00;
    public final /* synthetic */ C0954pW A01;

    public C0953pV(C0954pW pWVar, C0836jg jgVar) {
        C0514bB.A02(jgVar, "delegate");
        this.A01 = pWVar;
        this.A00 = jgVar;
    }

    @Override // X.K4
    public final void A3o(String[] strArr, String str) {
        C0514bB.A02(strArr, "keys");
        C0514bB.A02(str, "operationName");
        C0836jg jgVar = this.A00;
        W2 w2 = new W2(strArr, str);
        D4 d4 = C0836jg.A00(jgVar).A03.A00;
        String[] strArr2 = w2.A00;
        if (strArr2.length <= 1) {
            String str2 = strArr2[0];
            ArrayList arrayList = d4.A00;
            if (!arrayList.contains(str2)) {
                arrayList.add(str2);
            }
            d4.A01.add(w2);
            return;
        }
        throw new C0466aB(AnonymousClass08.A04("An operation is not implemented: ", "LegacyChangeManager desperately needs to be rebuilt :("));
    }

    @Override // X.K4
    public final void A52() {
        C0836jg jgVar = this.A00;
        jgVar.A01.setTransactionSuccessful();
        jgVar.A00 = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        C0836jg jgVar = this.A00;
        try {
            SQLiteDatabase sQLiteDatabase = jgVar.A01;
            sQLiteDatabase.endTransaction();
            try {
                boolean inTransaction = sQLiteDatabase.inTransaction();
                C0136Cw A002 = C0836jg.A00(jgVar);
                boolean z = false;
                if (jgVar.A00) {
                    z = true;
                }
                int A003 = A002.A00(z);
                if (inTransaction && A003 <= 0) {
                    C0139Dd.A0Q("DirectTransaction", "push/popTransaction state imbalance: newDepth=%d", Integer.valueOf(A003));
                }
            } finally {
                Trace.endSection();
            }
        } catch (Throwable th) {
            boolean inTransaction2 = jgVar.A01.inTransaction();
            int A004 = C0836jg.A00(jgVar).A00(false);
            if (inTransaction2 && A004 <= 0) {
                C0139Dd.A0Q("DirectTransaction", "push/popTransaction state imbalance: newDepth=%d", Integer.valueOf(A004));
            }
            throw th;
        } finally {
            Trace.endSection();
        }
    }
}
