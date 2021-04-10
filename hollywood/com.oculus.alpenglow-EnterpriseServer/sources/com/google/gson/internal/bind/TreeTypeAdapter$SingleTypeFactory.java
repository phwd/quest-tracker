package com.google.gson.internal.bind;

import X.AbstractC008109t;
import X.AnonymousClass08D;
import X.AnonymousClass0BN;
import X.AnonymousClass0Bd;
import X.AnonymousClass0C3;
import X.AnonymousClass0Fe;
import X.AnonymousClass0Wg;

public final class TreeTypeAdapter$SingleTypeFactory implements AnonymousClass0C3 {
    public final AbstractC008109t<?> A00;
    public final AnonymousClass0BN<?> A01;
    public final AnonymousClass0Fe<?> A02;
    public final boolean A03;

    @Override // X.AnonymousClass0C3
    public final <T> AnonymousClass0Bd<T> A1v(AnonymousClass08D r6, AnonymousClass0Fe<T> r7) {
        AnonymousClass0Fe<?> r1 = this.A02;
        if (r1 == null) {
            throw null;
        } else if (r1.equals(r7) || (this.A03 && r1.A02 == r7.A01)) {
            return new AnonymousClass0Wg(this.A01, this.A00, r6, r7, this);
        } else {
            return null;
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/Object;LX/0Fe<*>;ZLjava/lang/Class<*>;)V */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        if (r2 != null) goto L_0x001c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public TreeTypeAdapter$SingleTypeFactory(java.lang.Object r4, X.AnonymousClass0Fe r5, boolean r6) {
        /*
            r3 = this;
            r3.<init>()
            boolean r0 = r4 instanceof X.AnonymousClass0BN
            r2 = 0
            r1 = r2
            if (r0 == 0) goto L_0x000c
            r1 = r4
            X.0BN r1 = (X.AnonymousClass0BN) r1
        L_0x000c:
            r3.A01 = r1
            boolean r0 = r4 instanceof X.AbstractC008109t
            if (r0 == 0) goto L_0x0015
            r2 = r4
            X.09t r2 = (X.AbstractC008109t) r2
        L_0x0015:
            r3.A00 = r2
            if (r1 != 0) goto L_0x001c
            r0 = 0
            if (r2 == 0) goto L_0x001d
        L_0x001c:
            r0 = 1
        L_0x001d:
            X.AnonymousClass0CT.A00(r0)
            r3.A02 = r5
            r3.A03 = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.TreeTypeAdapter$SingleTypeFactory.<init>(java.lang.Object, X.0Fe, boolean):void");
    }
}
