package X;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: X.2ad  reason: invalid class name and case insensitive filesystem */
public final class C14942ad extends AbstractC14992aj {
    public int A00;
    public ArrayList<AbstractC14992aj> A01;

    /* JADX WARNING: Code restructure failed: missing block: B:161:0x024b, code lost:
        if (r19 != false) goto L_0x020f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x02d8, code lost:
        if (r19 != false) goto L_0x029e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x0371, code lost:
        if (r19 != false) goto L_0x033b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0240  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0253  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x02d0  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x02d3  */
    /* JADX WARNING: Removed duplicated region for block: B:206:0x02e3  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0355  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x0369  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0379  */
    @Override // X.AbstractC14992aj, X.AnonymousClass2bJ
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAs(X.AnonymousClass2bJ r23) {
        /*
        // Method dump skipped, instructions count: 902
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C14942ad.AAs(X.2bJ):void");
    }

    public final String toString() {
        String str;
        if (super.A01 == 0) {
            str = "horizontal : ";
        } else {
            str = "vertical : ";
        }
        String A07 = AnonymousClass006.A07("ChainRun ", str);
        Iterator<AbstractC14992aj> it = this.A01.iterator();
        while (it.hasNext()) {
            AbstractC14992aj next = it.next();
            String A072 = AnonymousClass006.A07(A07, "<");
            StringBuilder sb = new StringBuilder();
            sb.append(A072);
            sb.append(next);
            A07 = AnonymousClass006.A07(sb.toString(), "> ");
        }
        return A07;
    }

    public C14942ad(AnonymousClass2ac r5, int i) {
        super(r5);
        AnonymousClass2ac r1;
        AbstractC14992aj r0;
        int i2;
        ArrayList<AbstractC14992aj> arrayList = new ArrayList<>();
        this.A01 = arrayList;
        super.A01 = i;
        AnonymousClass2ac r2 = this.A02;
        AnonymousClass2ac A09 = r2.A09(i);
        while (true) {
            r1 = r2;
            r2 = A09;
            if (A09 == null) {
                break;
            }
            A09 = A09.A09(i);
        }
        this.A02 = r1;
        do {
            if (i == 0) {
                r0 = r1.A0g;
            } else if (i == 1) {
                r0 = r1.A0h;
            } else {
                r0 = null;
            }
            arrayList.add(r0);
            r1 = r1.A08(i);
        } while (r1 != null);
        Iterator<AbstractC14992aj> it = arrayList.iterator();
        while (it.hasNext()) {
            AbstractC14992aj next = it.next();
            int i3 = super.A01;
            if (i3 == 0) {
                next.A02.A0e = this;
            } else if (i3 == 1) {
                next.A02.A0f = this;
            }
        }
        int i4 = super.A01;
        if (i4 == 0 && ((C14932ab) this.A02.A0d).A0E) {
            ArrayList<AbstractC14992aj> arrayList2 = this.A01;
            if (arrayList2.size() > 1) {
                this.A02 = arrayList2.get(arrayList2.size() - 1).A02;
            }
        }
        AnonymousClass2ac r02 = this.A02;
        if (i4 == 0) {
            i2 = r02.A0B;
        } else {
            i2 = r02.A0O;
        }
        this.A00 = i2;
    }
}
