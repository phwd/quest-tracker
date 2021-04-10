package X;

import com.google.gson.InstanceCreator;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HZ {
    public HX A00 = UC.IDENTITY;
    public OS A01 = OS.DEFAULT;
    public Tj A02 = Tj.A02;
    public final List<AbstractC0132Os> A03 = new ArrayList();
    public final List<AbstractC0132Os> A04 = new ArrayList();
    public final Map<Type, InstanceCreator<?>> A05 = new HashMap();

    public final HY A00() {
        List<AbstractC0132Os> list = this.A03;
        int size = list.size();
        List<AbstractC0132Os> list2 = this.A04;
        ArrayList arrayList = new ArrayList(size + list2.size() + 3);
        arrayList.addAll(list);
        Collections.reverse(arrayList);
        ArrayList arrayList2 = new ArrayList(list2);
        Collections.reverse(arrayList2);
        arrayList.addAll(arrayList2);
        return new HY(this.A02, this.A00, this.A05, this.A01, list, list2, arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000b, code lost:
        if ((r7 instanceof X.AbstractC0131Ob) != false) goto L_0x000d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(java.lang.reflect.Type r6, java.lang.Object r7) {
        /*
            r5 = this;
            boolean r2 = r7 instanceof X.OO
            if (r2 != 0) goto L_0x000d
            boolean r0 = r7 instanceof X.Lj
            if (r0 != 0) goto L_0x000d
            boolean r1 = r7 instanceof X.AbstractC0131Ob
            r0 = 0
            if (r1 == 0) goto L_0x000e
        L_0x000d:
            r0 = 1
        L_0x000e:
            X.Rn.A00(r0)
            if (r2 != 0) goto L_0x0017
            boolean r0 = r7 instanceof X.Lj
            if (r0 == 0) goto L_0x002f
        L_0x0017:
            X.lQ r4 = new X.lQ
            r4.<init>(r6)
            java.util.List<X.Os> r3 = r5.A03
            java.lang.reflect.Type r1 = r4.type
            java.lang.Class<? super T> r0 = r4.rawType
            r2 = 0
            if (r1 != r0) goto L_0x0026
            r2 = 1
        L_0x0026:
            r1 = 0
            X.Sz r0 = new X.Sz
            r0.<init>(r7, r4, r2, r1)
            r3.add(r0)
        L_0x002f:
            boolean r0 = r7 instanceof X.AbstractC0131Ob
            if (r0 == 0) goto L_0x0044
            java.util.List<X.Os> r2 = r5.A03
            X.lQ r1 = new X.lQ
            r1.<init>(r6)
            X.Ob r7 = (X.AbstractC0131Ob) r7
            X.SY r0 = new X.SY
            r0.<init>(r1, r7)
            r2.add(r0)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.HZ.A01(java.lang.reflect.Type, java.lang.Object):void");
    }
}
