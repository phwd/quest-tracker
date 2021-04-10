package X;

import java.io.IOException;
import java.lang.reflect.Type;

/* renamed from: X.0VA  reason: invalid class name */
public final class AnonymousClass0VA<T> extends AnonymousClass0yl<T> {
    public final C08780ya A00;
    public final AnonymousClass0yl<T> A01;
    public final Type A02;

    @Override // X.AnonymousClass0yl
    public final T A02(C09120zR r2) throws IOException {
        return this.A01.A02(r2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.reflect.Type] */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass0yl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(X.C09130zU r6, T r7) throws java.io.IOException {
        /*
            r5 = this;
            X.0yl<T> r4 = r5.A01
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
            X.0ya r1 = r5.A00
            X.0zQ r0 = new X.0zQ
            r0.<init>(r2)
            X.0yl r4 = r1.A04(r0)
            boolean r0 = r4 instanceof X.AnonymousClass0WQ
            if (r0 == 0) goto L_0x002e
            boolean r0 = r3 instanceof X.AnonymousClass0WQ
            if (r0 != 0) goto L_0x002e
            r4 = r3
        L_0x002e:
            r4.A03(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0VA.A03(X.0zU, java.lang.Object):void");
    }

    public AnonymousClass0VA(C08780ya r1, AnonymousClass0yl<T> r2, Type type) {
        this.A00 = r1;
        this.A01 = r2;
        this.A02 = type;
    }
}
