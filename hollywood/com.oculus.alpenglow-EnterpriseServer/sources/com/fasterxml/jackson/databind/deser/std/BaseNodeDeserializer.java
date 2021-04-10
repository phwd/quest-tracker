package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass007;
import X.AnonymousClass05T;
import X.AnonymousClass05U;
import X.AnonymousClass05W;
import X.AnonymousClass07e;
import X.AnonymousClass07k;
import X.AnonymousClass07o;
import X.AnonymousClass07p;
import X.AnonymousClass0Zc;
import X.AnonymousClass0aF;
import X.AnonymousClass0aG;
import X.AnonymousClass0aT;
import X.AnonymousClass0nR;
import X.C006005u;
import X.C006105v;
import X.C007007d;
import X.C007307n;
import X.C05910ld;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class BaseNodeDeserializer extends StdDeserializer<AnonymousClass0aF> {
    public BaseNodeDeserializer() {
        super(AnonymousClass0aF.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A08() {
        return AnonymousClass07k.A00;
    }

    public final AnonymousClass0aF A0P(AnonymousClass0aT r4, AbstractC02570aK r5, AnonymousClass0Zc r6) throws IOException, C05910ld {
        switch (AnonymousClass0nR.A00[r4.A0G().ordinal()]) {
            case 1:
            case 5:
                return A0R(r4, r5, r6);
            case 2:
                return A0Q(r4, r5, r6);
            case 3:
                return C007007d.A00(r4.A0P());
            case 4:
            default:
                throw r5.A0B(this._valueClass);
            case 6:
                Object A0M = r4.A0M();
                if (A0M == null) {
                    return AnonymousClass07k.A00;
                }
                if (A0M.getClass() != byte[].class) {
                    return new AnonymousClass07e(A0M);
                }
                byte[] bArr = (byte[]) A0M;
                if (bArr == null) {
                    return null;
                }
                if (bArr.length == 0) {
                    return AnonymousClass07o.A01;
                }
                return new AnonymousClass07o(bArr);
            case 7:
                Integer A0J = r4.A0J();
                if (A0J == AnonymousClass007.A0C || r5.A0O(EnumC02560aJ.USE_BIG_INTEGER_FOR_INTS)) {
                    return new C006105v(r4.A0S());
                }
                if (A0J != AnonymousClass007.A00) {
                    return new AnonymousClass05T(r4.A0B());
                }
                int A06 = r4.A06();
                if (A06 > 10 || A06 < -1) {
                    return new AnonymousClass05U(A06);
                }
                return AnonymousClass05U.A01[A06 - -1];
            case 8:
                if (r4.A0J() != AnonymousClass007.A0F && !r5.A0O(EnumC02560aJ.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return new AnonymousClass05W(r4.A03());
                }
                BigDecimal A0R = r4.A0R();
                if (r6._cfgBigDecimalExact) {
                    return new C006005u(A0R);
                }
                if (A0R.compareTo(BigDecimal.ZERO) == 0) {
                    return C006005u.A01;
                }
                return new C006005u(A0R.stripTrailingZeros());
            case 9:
                return C007307n.A02;
            case 10:
                return C007307n.A01;
            case 11:
                return AnonymousClass07k.A00;
        }
    }

    public final AnonymousClass07p A0Q(AnonymousClass0aT r4, AbstractC02570aK r5, AnonymousClass0Zc r6) throws IOException, C05910ld {
        AnonymousClass0aF A0R;
        AnonymousClass07p r2 = new AnonymousClass07p(r6);
        while (true) {
            EnumC05930lf A0a = r4.A0a();
            if (A0a != null) {
                int i = AnonymousClass0nR.A00[A0a.ordinal()];
                if (i == 1) {
                    A0R = A0R(r4, r5, r6);
                } else if (i != 2) {
                    if (i == 3) {
                        A0R = C007007d.A00(r4.A0P());
                    } else if (i == 4) {
                        return r2;
                    } else {
                        A0R = A0P(r4, r5, r6);
                    }
                    if (A0R == null) {
                        A0R = AnonymousClass07k.A00;
                    }
                } else {
                    A0R = A0Q(r4, r5, r6);
                }
                r2.A00.add(A0R);
            } else {
                throw AnonymousClass0aG.A00(r5.A00, "Unexpected end-of-input when binding data into ArrayNode");
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
        r1 = X.AnonymousClass07k.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r3.A00.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r1 = X.C007007d.A00(r5.A0P());
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
        if (r1 == X.EnumC05930lf.START_OBJECT) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r5.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        if (r1 != X.EnumC05930lf.FIELD_NAME) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r5.A0O();
        r1 = X.AnonymousClass0nR.A00[r5.A0a().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r1 == 1) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1 == 2) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C007107f A0R(X.AnonymousClass0aT r5, X.AbstractC02570aK r6, X.AnonymousClass0Zc r7) throws java.io.IOException, X.C05910ld {
        /*
            r4 = this;
            X.07f r3 = new X.07f
            r3.<init>(r7)
            X.0lf r1 = r5.A0G()
            X.0lf r0 = X.EnumC05930lf.START_OBJECT
            if (r1 != r0) goto L_0x0011
        L_0x000d:
            X.0lf r1 = r5.A0a()
        L_0x0011:
            X.0lf r0 = X.EnumC05930lf.FIELD_NAME
            if (r1 != r0) goto L_0x004f
            java.lang.String r2 = r5.A0O()
            int[] r1 = X.AnonymousClass0nR.A00
            X.0lf r0 = r5.A0a()
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x004a
            r0 = 2
            if (r1 == r0) goto L_0x0045
            r0 = 3
            if (r1 == r0) goto L_0x003c
            X.0aF r1 = r4.A0P(r5, r6, r7)
        L_0x0032:
            if (r1 != 0) goto L_0x0036
            X.07k r1 = X.AnonymousClass07k.A00
        L_0x0036:
            java.util.Map<java.lang.String, X.0aF> r0 = r3.A00
            r0.put(r2, r1)
            goto L_0x000d
        L_0x003c:
            java.lang.String r0 = r5.A0P()
            X.07d r1 = X.C007007d.A00(r0)
            goto L_0x0032
        L_0x0045:
            X.07p r1 = r4.A0Q(r5, r6, r7)
            goto L_0x0032
        L_0x004a:
            X.07f r1 = r4.A0R(r5, r6, r7)
            goto L_0x0032
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.A0R(X.0aT, X.0aK, X.0Zc):X.07f");
    }
}
