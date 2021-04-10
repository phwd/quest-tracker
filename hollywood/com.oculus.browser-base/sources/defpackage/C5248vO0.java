package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: vO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5248vO0 implements Iterable {
    public C4568rO0 F;
    public C4568rO0 G;
    public WeakHashMap H = new WeakHashMap();
    public int I = 0;

    public C4738sO0 a() {
        C4738sO0 so0 = new C4738sO0(this);
        this.H.put(so0, Boolean.FALSE);
        return so0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0048, code lost:
        if (r3.hasNext() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0050, code lost:
        if (((defpackage.AbstractC4908tO0) r7).hasNext() != false) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r7 != r6) goto L_0x0004
            return r0
        L_0x0004:
            boolean r1 = r7 instanceof defpackage.C5248vO0
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            vO0 r7 = (defpackage.C5248vO0) r7
            int r1 = r6.I
            int r3 = r7.I
            if (r1 == r3) goto L_0x0013
            return r2
        L_0x0013:
            java.util.Iterator r1 = r6.iterator()
            java.util.Iterator r7 = r7.iterator()
        L_0x001b:
            r3 = r1
            tO0 r3 = (defpackage.AbstractC4908tO0) r3
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0044
            r4 = r7
            tO0 r4 = (defpackage.AbstractC4908tO0) r4
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0044
            java.lang.Object r3 = r3.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r4.next()
            if (r3 != 0) goto L_0x003b
            if (r4 != 0) goto L_0x0043
        L_0x003b:
            if (r3 == 0) goto L_0x001b
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x001b
        L_0x0043:
            return r2
        L_0x0044:
            boolean r1 = r3.hasNext()
            if (r1 != 0) goto L_0x0053
            tO0 r7 = (defpackage.AbstractC4908tO0) r7
            boolean r7 = r7.hasNext()
            if (r7 != 0) goto L_0x0053
            goto L_0x0054
        L_0x0053:
            r0 = r2
        L_0x0054:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5248vO0.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (true) {
            AbstractC4908tO0 to0 = (AbstractC4908tO0) it;
            if (!to0.hasNext()) {
                return i;
            }
            i += ((Map.Entry) to0.next()).hashCode();
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        C4227pO0 po0 = new C4227pO0(this.F, this.G);
        this.H.put(po0, Boolean.FALSE);
        return po0;
    }

    public String toString() {
        StringBuilder i = AbstractC2531fV.i("[");
        Iterator it = iterator();
        while (true) {
            AbstractC4908tO0 to0 = (AbstractC4908tO0) it;
            if (to0.hasNext()) {
                i.append(((Map.Entry) to0.next()).toString());
                if (to0.hasNext()) {
                    i.append(", ");
                }
            } else {
                i.append("]");
                return i.toString();
            }
        }
    }
}
