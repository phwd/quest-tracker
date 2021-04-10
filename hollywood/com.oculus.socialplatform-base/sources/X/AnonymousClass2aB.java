package X;

import java.util.ArrayList;

/* renamed from: X.2aB  reason: invalid class name */
public final class AnonymousClass2aB {
    public C14932ab A00;
    public AnonymousClass2aH A01 = new AnonymousClass2aH();
    public final ArrayList<AnonymousClass2ac> A02 = new ArrayList<>();

    public static void A00(AnonymousClass2aB r3, C14932ab r4, int i, int i2) {
        int i3 = r4.A0M;
        int i4 = r4.A0L;
        r4.A0M = 0;
        r4.A0L = 0;
        r4.A0E(i);
        r4.A0D(i2);
        if (i3 < 0) {
            i3 = 0;
        }
        r4.A0M = i3;
        if (i4 < 0) {
            i4 = 0;
        }
        r4.A0L = i4;
        r3.A00.A0V();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003b, code lost:
        if (r9.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0031, code lost:
        if (r9.A01 <= com.oculus.vrshell.panels.AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean A01(X.AnonymousClass2aB r7, X.AnonymousClass2aK r8, X.AnonymousClass2ac r9, int r10) {
        /*
        // Method dump skipped, instructions count: 120
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass2aB.A01(X.2aB, X.2aK, X.2ac, int):boolean");
    }

    public final void A02(C14932ab r9) {
        ArrayList<AnonymousClass2ac> arrayList = this.A02;
        arrayList.clear();
        ArrayList<AnonymousClass2ac> arrayList2 = ((AnonymousClass2b2) r9).A00;
        int size = arrayList2.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass2ac r3 = arrayList2.get(i);
            Integer num = r3.A0w[0];
            Integer num2 = AnonymousClass007.A03;
            if (num == num2 || r3.A0w[1] == num2) {
                arrayList.add(r3);
            }
        }
        r9.A0A.A05 = true;
    }

    public AnonymousClass2aB(C14932ab r2) {
        this.A00 = r2;
    }
}
