package X;

import android.database.sqlite.SQLiteDatabase;
import java.util.WeakHashMap;

/* renamed from: X.jg  reason: case insensitive filesystem */
public final class C0836jg {
    public static final ThreadLocal A07 = new C0134Cu();
    public boolean A00;
    public final SQLiteDatabase A01;
    public final C0835jf A02 = new C0835jf(this);
    public final Cz A03;
    public final D1 A04;
    public final DN A05 = new DN();
    public final DN A06 = new DN();

    public static C0136Cw A00(C0836jg jgVar) {
        SQLiteDatabase sQLiteDatabase = jgVar.A01;
        WeakHashMap weakHashMap = ((C0137Cx) A07.get()).A00;
        C0136Cw cw = (C0136Cw) weakHashMap.get(sQLiteDatabase);
        if (cw != null) {
            return cw;
        }
        C0136Cw cw2 = new C0136Cw();
        weakHashMap.put(sQLiteDatabase, cw2);
        return cw2;
    }

    public C0836jg(D1 d1, Cz cz) {
        this.A04 = d1;
        this.A03 = cz;
        this.A01 = d1.A2D();
    }
}
