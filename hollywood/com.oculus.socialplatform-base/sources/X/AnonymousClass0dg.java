package X;

import java.io.IOException;
import java.lang.reflect.Type;

/* renamed from: X.0dg  reason: invalid class name */
public final class AnonymousClass0dg<T> extends AnonymousClass13Y<T> {
    public final AnonymousClass13N A00;
    public final AnonymousClass13Y<T> A01;
    public final Type A02;

    @Override // X.AnonymousClass13Y
    public final T A02(AnonymousClass14I r2) throws IOException {
        return this.A01.A02(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass13Y
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(X.AnonymousClass14L r6, T r7) throws java.io.IOException {
        /*
            r5 = this;
            X.13Y<T> r4 = r5.A01
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
            X.13N r1 = r5.A00
            X.14H r0 = new X.14H
            r0.<init>(r2)
            X.13Y r4 = r1.A03(r0)
            boolean r0 = r4 instanceof X.C01370dp
            if (r0 == 0) goto L_0x002e
            boolean r0 = r3 instanceof X.C01370dp
            if (r0 != 0) goto L_0x002e
            r4 = r3
        L_0x002e:
            r4.A03(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0dg.A03(X.14L, java.lang.Object):void");
    }

    public AnonymousClass0dg(AnonymousClass13N r1, AnonymousClass13Y<T> r2, Type type) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = type;
    }
}
