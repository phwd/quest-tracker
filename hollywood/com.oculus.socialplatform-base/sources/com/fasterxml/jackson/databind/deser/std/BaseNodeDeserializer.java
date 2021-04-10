package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02170iC;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass007;
import X.AnonymousClass00r;
import X.AnonymousClass00s;
import X.AnonymousClass00t;
import X.AnonymousClass00u;
import X.AnonymousClass04F;
import X.AnonymousClass04K;
import X.AnonymousClass04S;
import X.AnonymousClass04V;
import X.AnonymousClass04W;
import X.AnonymousClass04X;
import X.AnonymousClass0q0;
import X.C000200q;
import X.C01850hC;
import X.C02180iD;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class BaseNodeDeserializer extends StdDeserializer<AbstractC02170iC> {
    public BaseNodeDeserializer() {
        super(AbstractC02170iC.class);
    }

    public final AbstractC02170iC A0P(AbstractC02280iQ r4, AbstractC02210iH r5, C01850hC r6) throws IOException, C03620oC {
        switch (AnonymousClass0q0.A00[r4.A0i().ordinal()]) {
            case 1:
            case 5:
                return A0R(r4, r5, r6);
            case 2:
                return A0Q(r4, r5, r6);
            case 3:
                return AnonymousClass04F.A00(r4.A0m());
            case 4:
            default:
                throw r5.A0B(this._valueClass);
            case 6:
                Object A0Z = r4.A0Z();
                if (A0Z == null) {
                    return AnonymousClass04S.A00;
                }
                if (A0Z.getClass() != byte[].class) {
                    return new AnonymousClass04K(A0Z);
                }
                byte[] bArr = (byte[]) A0Z;
                if (bArr == null) {
                    return null;
                }
                if (bArr.length == 0) {
                    return AnonymousClass04W.A01;
                }
                return new AnonymousClass04W(bArr);
            case 7:
                Integer A0X = r4.A0X();
                if (A0X == AnonymousClass007.A03 || r5.A0P(EnumC02200iG.USE_BIG_INTEGER_FOR_INTS)) {
                    return new AnonymousClass00u(r4.A0b());
                }
                if (A0X != AnonymousClass007.A00) {
                    return new C000200q(r4.A0U());
                }
                int A0T = r4.A0T();
                if (A0T > 10 || A0T < -1) {
                    return new AnonymousClass00r(A0T);
                }
                return AnonymousClass00r.A01[A0T - -1];
            case 8:
                if (r4.A0X() != AnonymousClass007.A06 && !r5.A0P(EnumC02200iG.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return new AnonymousClass00s(r4.A0R());
                }
                BigDecimal A0a = r4.A0a();
                if (r6._cfgBigDecimalExact) {
                    return new AnonymousClass00t(A0a);
                }
                if (A0a.compareTo(BigDecimal.ZERO) == 0) {
                    return AnonymousClass00t.A01;
                }
                return new AnonymousClass00t(A0a.stripTrailingZeros());
            case 9:
                return AnonymousClass04V.A02;
            case 10:
                return AnonymousClass04V.A01;
            case 11:
                return AnonymousClass04S.A00;
        }
    }

    public final AnonymousClass04X A0Q(AbstractC02280iQ r4, AbstractC02210iH r5, C01850hC r6) throws IOException, C03620oC {
        AbstractC02170iC A0R;
        AnonymousClass04X r2 = new AnonymousClass04X(r6);
        while (true) {
            EnumC03640oE A0j = r4.A0j();
            if (A0j != null) {
                int i = AnonymousClass0q0.A00[A0j.ordinal()];
                if (i == 1) {
                    A0R = A0R(r4, r5, r6);
                } else if (i != 2) {
                    if (i == 3) {
                        A0R = AnonymousClass04F.A00(r4.A0m());
                    } else if (i == 4) {
                        return r2;
                    } else {
                        A0R = A0P(r4, r5, r6);
                    }
                    if (A0R == null) {
                        A0R = AnonymousClass04S.A00;
                    }
                } else {
                    A0R = A0Q(r4, r5, r6);
                }
                r2.A00.add(A0R);
            } else {
                throw C02180iD.A00(r5.A00, "Unexpected end-of-input when binding data into ArrayNode");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (r1 == 3) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r1 = A0P(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r1 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = X.AnonymousClass04S.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r3.A00.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r1 = X.AnonymousClass04F.A00(r5.A0m());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        r1 = A0Q(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        r1 = A0R(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x000b, code lost:
        if (r1 == X.EnumC03640oE.START_OBJECT) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r5.A0j();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        if (r1 != X.EnumC03640oE.FIELD_NAME) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r5.A0l();
        r1 = X.AnonymousClass0q0.A00[r5.A0j().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r1 == 1) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1 == 2) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass04L A0R(X.AbstractC02280iQ r5, X.AbstractC02210iH r6, X.C01850hC r7) throws java.io.IOException, X.C03620oC {
        /*
            r4 = this;
            X.04L r3 = new X.04L
            r3.<init>(r7)
            X.0oE r1 = r5.A0i()
            X.0oE r0 = X.EnumC03640oE.START_OBJECT
            if (r1 != r0) goto L_0x0011
        L_0x000d:
            X.0oE r1 = r5.A0j()
        L_0x0011:
            X.0oE r0 = X.EnumC03640oE.FIELD_NAME
            if (r1 != r0) goto L_0x004f
            java.lang.String r2 = r5.A0l()
            int[] r1 = X.AnonymousClass0q0.A00
            X.0oE r0 = r5.A0j()
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x004a
            r0 = 2
            if (r1 == r0) goto L_0x0045
            r0 = 3
            if (r1 == r0) goto L_0x003c
            X.0iC r1 = r4.A0P(r5, r6, r7)
        L_0x0032:
            if (r1 != 0) goto L_0x0036
            X.04S r1 = X.AnonymousClass04S.A00
        L_0x0036:
            java.util.Map<java.lang.String, X.0iC> r0 = r3.A00
            r0.put(r2, r1)
            goto L_0x000d
        L_0x003c:
            java.lang.String r0 = r5.A0m()
            X.04F r1 = X.AnonymousClass04F.A00(r0)
            goto L_0x0032
        L_0x0045:
            X.04X r1 = r4.A0Q(r5, r6, r7)
            goto L_0x0032
        L_0x004a:
            X.04L r1 = r4.A0R(r5, r6, r7)
            goto L_0x0032
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.A0R(X.0iQ, X.0iH, X.0hC):X.04L");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A08() {
        return AnonymousClass04S.A00;
    }
}
