package X;

import java.io.IOException;
import java.lang.reflect.Type;

/* renamed from: X.Sx  reason: case insensitive filesystem */
public final class C0162Sx<T> extends AbstractC0131Ob<T> {
    public final HY A00;
    public final AbstractC0131Ob<T> A01;
    public final Type A02;

    @Override // X.AbstractC0131Ob
    public final T A02(lk lkVar) throws IOException {
        return this.A01.A02(lkVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AbstractC0131Ob
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(X.mm r6, T r7) throws java.io.IOException {
        /*
            r5 = this;
            X.Ob<T> r4 = r5.A01
            r3 = r4
            java.lang.reflect.Type r1 = r5.A02
            r2 = r1
            if (r7 == 0) goto L_0x0018
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            if (r1 == r0) goto L_0x0014
            boolean r0 = r1 instanceof java.lang.reflect.TypeVariable
            if (r0 != 0) goto L_0x0014
            boolean r0 = r1 instanceof java.lang.Class
            if (r0 == 0) goto L_0x0018
        L_0x0014:
            java.lang.Class r2 = r7.getClass()
        L_0x0018:
            if (r2 == r1) goto L_0x002e
            X.HY r1 = r5.A00
            X.lQ r0 = new X.lQ
            r0.<init>(r2)
            X.Ob r4 = r1.A04(r0)
            boolean r0 = r4 instanceof X.T6
            if (r0 == 0) goto L_0x002e
            boolean r0 = r3 instanceof X.T6
            if (r0 != 0) goto L_0x002e
            r4 = r3
        L_0x002e:
            r4.A03(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0162Sx.A03(X.mm, java.lang.Object):void");
    }

    public C0162Sx(HY hy, AbstractC0131Ob<T> ob, Type type) {
        this.A00 = hy;
        this.A01 = ob;
        this.A02 = type;
    }
}
