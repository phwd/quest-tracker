package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0222Wi;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass07;
import X.AnonymousClass0L;
import X.AnonymousClass0M;
import X.AnonymousClass0N;
import X.AnonymousClass0O;
import X.AnonymousClass0P;
import X.AnonymousClass2x;
import X.AnonymousClass2y;
import X.AnonymousClass31;
import X.AnonymousClass32;
import X.AnonymousClass37;
import X.AnonymousClass38;
import X.C0198Ve;
import X.C0223Wj;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.W6;
import X.q0;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class BaseNodeDeserializer extends StdDeserializer<AbstractC0222Wi> {
    public BaseNodeDeserializer() {
        super(AbstractC0222Wi.class);
    }

    public final AbstractC0222Wi A0P(AbstractC0232Ww ww, AbstractC0226Wn wn, W6 w6) throws IOException, q0 {
        switch (C0198Ve.A00[ww.A0Z().ordinal()]) {
            case 1:
            case 5:
                return A0R(ww, wn, w6);
            case 2:
                return A0Q(ww, wn, w6);
            case 3:
                return AnonymousClass2x.A00(ww.A0d());
            case 4:
            default:
                throw wn.A08(this._valueClass);
            case 6:
                Object A0R = ww.A0R();
                if (A0R == null) {
                    return AnonymousClass31.A00;
                }
                if (A0R.getClass() != byte[].class) {
                    return new AnonymousClass2y(A0R);
                }
                byte[] bArr = (byte[]) A0R;
                if (bArr == null) {
                    return null;
                }
                if (bArr.length == 0) {
                    return AnonymousClass37.A01;
                }
                return new AnonymousClass37(bArr);
            case 7:
                Integer A0P = ww.A0P();
                if (A0P == AnonymousClass07.A02 || wn.A0L(EnumC0225Wm.USE_BIG_INTEGER_FOR_INTS)) {
                    return new AnonymousClass0P(ww.A0T());
                }
                if (A0P != AnonymousClass07.A00) {
                    return new AnonymousClass0L(ww.A0M());
                }
                int A0L = ww.A0L();
                if (A0L > 10 || A0L < -1) {
                    return new AnonymousClass0M(A0L);
                }
                return AnonymousClass0M.A01[A0L - -1];
            case 8:
                if (ww.A0P() != AnonymousClass07.A05 && !wn.A0L(EnumC0225Wm.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return new AnonymousClass0N(ww.A0J());
                }
                BigDecimal A0S = ww.A0S();
                if (w6._cfgBigDecimalExact) {
                    return new AnonymousClass0O(A0S);
                }
                if (A0S.compareTo(BigDecimal.ZERO) == 0) {
                    return AnonymousClass0O.A01;
                }
                return new AnonymousClass0O(A0S.stripTrailingZeros());
            case 9:
                return AnonymousClass32.A02;
            case 10:
                return AnonymousClass32.A01;
            case 11:
                return AnonymousClass31.A00;
        }
    }

    public final AnonymousClass38 A0Q(AbstractC0232Ww ww, AbstractC0226Wn wn, W6 w6) throws IOException, q0 {
        AbstractC0222Wi A0R;
        AnonymousClass38 r2 = new AnonymousClass38(w6);
        while (true) {
            EnumC0470q2 A0a = ww.A0a();
            if (A0a != null) {
                int i = C0198Ve.A00[A0a.ordinal()];
                if (i == 1) {
                    A0R = A0R(ww, wn, w6);
                } else if (i != 2) {
                    if (i == 3) {
                        A0R = AnonymousClass2x.A00(ww.A0d());
                    } else if (i == 4) {
                        return r2;
                    } else {
                        A0R = A0P(ww, wn, w6);
                    }
                    if (A0R == null) {
                        A0R = AnonymousClass31.A00;
                    }
                } else {
                    A0R = A0Q(ww, wn, w6);
                }
                r2.A00.add(A0R);
            } else {
                throw C0223Wj.A00(wn.A00, "Unexpected end-of-input when binding data into ArrayNode");
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
        r1 = X.AnonymousClass31.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r3.A00.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r1 = X.AnonymousClass2x.A00(r5.A0d());
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
        if (r1 == X.EnumC0470q2.START_OBJECT) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r5.A0a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        if (r1 != X.EnumC0470q2.FIELD_NAME) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r5.A0c();
        r1 = X.C0198Ve.A00[r5.A0a().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r1 == 1) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1 == 2) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass2z A0R(X.AbstractC0232Ww r5, X.AbstractC0226Wn r6, X.W6 r7) throws java.io.IOException, X.q0 {
        /*
            r4 = this;
            X.2z r3 = new X.2z
            r3.<init>(r7)
            X.q2 r1 = r5.A0Z()
            X.q2 r0 = X.EnumC0470q2.START_OBJECT
            if (r1 != r0) goto L_0x0011
        L_0x000d:
            X.q2 r1 = r5.A0a()
        L_0x0011:
            X.q2 r0 = X.EnumC0470q2.FIELD_NAME
            if (r1 != r0) goto L_0x004f
            java.lang.String r2 = r5.A0c()
            int[] r1 = X.C0198Ve.A00
            X.q2 r0 = r5.A0a()
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x004a
            r0 = 2
            if (r1 == r0) goto L_0x0045
            r0 = 3
            if (r1 == r0) goto L_0x003c
            X.Wi r1 = r4.A0P(r5, r6, r7)
        L_0x0032:
            if (r1 != 0) goto L_0x0036
            X.31 r1 = X.AnonymousClass31.A00
        L_0x0036:
            java.util.Map<java.lang.String, X.Wi> r0 = r3.A00
            r0.put(r2, r1)
            goto L_0x000d
        L_0x003c:
            java.lang.String r0 = r5.A0d()
            X.2x r1 = X.AnonymousClass2x.A00(r0)
            goto L_0x0032
        L_0x0045:
            X.38 r1 = r4.A0Q(r5, r6, r7)
            goto L_0x0032
        L_0x004a:
            X.2z r1 = r4.A0R(r5, r6, r7)
            goto L_0x0032
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.A0R(X.Ww, X.Wn, X.W6):X.2z");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A08() {
        return AnonymousClass31.A00;
    }
}
