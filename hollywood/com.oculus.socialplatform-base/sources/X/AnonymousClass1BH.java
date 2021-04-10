package X;

import android.util.SparseArray;

/* renamed from: X.1BH  reason: invalid class name */
public class AnonymousClass1BH {
    public int A00 = 0;
    public SparseArray<AnonymousClass1BI> A01 = new SparseArray<>();

    public static AnonymousClass1BI A00(AnonymousClass1BH r1, int i) {
        SparseArray<AnonymousClass1BI> sparseArray = r1.A01;
        AnonymousClass1BI r0 = sparseArray.get(i);
        if (r0 != null) {
            return r0;
        }
        AnonymousClass1BI r02 = new AnonymousClass1BI();
        sparseArray.put(i, r02);
        return r02;
    }
}
