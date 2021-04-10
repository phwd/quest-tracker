package X;

import java.util.ArrayList;

/* renamed from: X.1qj  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC11161qj {
    public final boolean A01() {
        if (this instanceof C10971qM) {
            C10971qM r4 = (C10971qM) this;
            int i = 0;
            while (true) {
                ArrayList<AbstractC11161qj> arrayList = r4.A0C;
                if (i >= arrayList.size()) {
                    return false;
                }
                if (arrayList.get(i).A01()) {
                    return true;
                }
                i++;
            }
        } else if (!(this instanceof AnonymousClass1qU)) {
            return false;
        } else {
            AnonymousClass1qU r1 = (AnonymousClass1qU) this;
            if (r1.A09.A00() || r1.A0A.A00()) {
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(int[] r6) {
        /*
            r5 = this;
            boolean r0 = r5 instanceof X.C10971qM
            if (r0 != 0) goto L_0x0045
            boolean r0 = r5 instanceof X.AnonymousClass1qU
            if (r0 != 0) goto L_0x000a
            r0 = 0
            return r0
        L_0x000a:
            r4 = r5
            X.1qU r4 = (X.AnonymousClass1qU) r4
            X.04i r2 = r4.A09
            boolean r0 = r2.A00()
            if (r0 == 0) goto L_0x0043
            android.content.res.ColorStateList r1 = r2.A01
            int r0 = r1.getDefaultColor()
            int r1 = r1.getColorForState(r6, r0)
            int r0 = r2.A00
            if (r1 == r0) goto L_0x0043
            r3 = 1
            r2.A00 = r1
        L_0x0026:
            X.04i r2 = r4.A0A
            boolean r0 = r2.A00()
            if (r0 == 0) goto L_0x0041
            android.content.res.ColorStateList r1 = r2.A01
            int r0 = r1.getDefaultColor()
            int r1 = r1.getColorForState(r6, r0)
            int r0 = r2.A00
            if (r1 == r0) goto L_0x0041
            r0 = 1
            r2.A00 = r1
        L_0x003f:
            r0 = r0 | r3
            return r0
        L_0x0041:
            r0 = 0
            goto L_0x003f
        L_0x0043:
            r3 = 0
            goto L_0x0026
        L_0x0045:
            r4 = r5
            X.1qM r4 = (X.C10971qM) r4
            r3 = 0
            r2 = 0
        L_0x004a:
            java.util.ArrayList<X.1qj> r1 = r4.A0C
            int r0 = r1.size()
            if (r3 >= r0) goto L_0x0060
            java.lang.Object r0 = r1.get(r3)
            X.1qj r0 = (X.AbstractC11161qj) r0
            boolean r0 = r0.A02(r6)
            r2 = r2 | r0
            int r3 = r3 + 1
            goto L_0x004a
        L_0x0060:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC11161qj.A02(int[]):boolean");
    }
}
