package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC03980gY;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass007;
import X.AnonymousClass00N;
import X.AnonymousClass00O;
import X.AnonymousClass00P;
import X.AnonymousClass00S;
import X.AnonymousClass00T;
import X.AnonymousClass030;
import X.AnonymousClass031;
import X.AnonymousClass039;
import X.AnonymousClass03A;
import X.AnonymousClass03B;
import X.AnonymousClass03C;
import X.AnonymousClass0jg;
import X.C03700fv;
import X.C03990gZ;
import X.C05590lW;
import X.EnumC04010gf;
import X.EnumC04820ji;
import java.io.IOException;
import java.math.BigDecimal;

public abstract class BaseNodeDeserializer extends StdDeserializer<AbstractC03980gY> {
    public BaseNodeDeserializer() {
        super(AbstractC03980gY.class);
    }

    public final AbstractC03980gY A0P(AbstractC04100gp r4, AbstractC04020gg r5, C03700fv r6) throws IOException, AnonymousClass0jg {
        switch (C05590lW.A00[r4.A0a().ordinal()]) {
            case 1:
            case 5:
                return A0R(r4, r5, r6);
            case 2:
                return A0Q(r4, r5, r6);
            case 3:
                return AnonymousClass030.A00(r4.A0e());
            case 4:
            default:
                throw null;
            case 6:
                Object A0S = r4.A0S();
                if (A0S == null) {
                    return AnonymousClass039.A00;
                }
                if (A0S.getClass() != byte[].class) {
                    return new AnonymousClass031(A0S);
                }
                byte[] bArr = (byte[]) A0S;
                if (bArr == null) {
                    return null;
                }
                if (bArr.length == 0) {
                    return AnonymousClass03B.A01;
                }
                return new AnonymousClass03B(bArr);
            case 7:
                Integer A0Q = r4.A0Q();
                if (A0Q == AnonymousClass007.A0C || r5.A0I(EnumC04010gf.USE_BIG_INTEGER_FOR_INTS)) {
                    return new AnonymousClass00T(r4.A0U());
                }
                if (A0Q != AnonymousClass007.A00) {
                    return new AnonymousClass00N(r4.A0N());
                }
                int A0M = r4.A0M();
                if (A0M > 10 || A0M < -1) {
                    return new AnonymousClass00O(A0M);
                }
                return AnonymousClass00O.A01[A0M - -1];
            case 8:
                if (r4.A0Q() != AnonymousClass007.A0F && !r5.A0I(EnumC04010gf.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return new AnonymousClass00P(r4.A0K());
                }
                BigDecimal A0T = r4.A0T();
                if (r6._cfgBigDecimalExact) {
                    return new AnonymousClass00S(A0T);
                }
                if (A0T.compareTo(BigDecimal.ZERO) == 0) {
                    return AnonymousClass00S.A01;
                }
                return new AnonymousClass00S(A0T.stripTrailingZeros());
            case 9:
                return AnonymousClass03A.A02;
            case 10:
                return AnonymousClass03A.A01;
            case 11:
                return AnonymousClass039.A00;
        }
    }

    public final AnonymousClass03C A0Q(AbstractC04100gp r4, AbstractC04020gg r5, C03700fv r6) throws IOException, AnonymousClass0jg {
        AbstractC03980gY A0R;
        AnonymousClass03C r2 = new AnonymousClass03C(r6);
        while (true) {
            EnumC04820ji A0b = r4.A0b();
            if (A0b != null) {
                int i = C05590lW.A00[A0b.ordinal()];
                if (i == 1) {
                    A0R = A0R(r4, r5, r6);
                } else if (i != 2) {
                    if (i == 3) {
                        A0R = AnonymousClass030.A00(r4.A0e());
                    } else if (i == 4) {
                        return r2;
                    } else {
                        A0R = A0P(r4, r5, r6);
                    }
                    if (A0R == null) {
                        A0R = AnonymousClass039.A00;
                    }
                } else {
                    A0R = A0Q(r4, r5, r6);
                }
                r2.A00.add(A0R);
            } else {
                throw C03990gZ.A00(null, "Unexpected end-of-input when binding data into ArrayNode");
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
        r1 = X.AnonymousClass039.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r3.A00.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r1 = X.AnonymousClass030.A00(r5.A0e());
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
        if (r1 == X.EnumC04820ji.START_OBJECT) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r5.A0b();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        if (r1 != X.EnumC04820ji.FIELD_NAME) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r5.A0d();
        r1 = X.C05590lW.A00[r5.A0b().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r1 == 1) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1 == 2) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass033 A0R(X.AbstractC04100gp r5, X.AbstractC04020gg r6, X.C03700fv r7) throws java.io.IOException, X.AnonymousClass0jg {
        /*
            r4 = this;
            X.033 r3 = new X.033
            r3.<init>(r7)
            X.0ji r1 = r5.A0a()
            X.0ji r0 = X.EnumC04820ji.START_OBJECT
            if (r1 != r0) goto L_0x0011
        L_0x000d:
            X.0ji r1 = r5.A0b()
        L_0x0011:
            X.0ji r0 = X.EnumC04820ji.FIELD_NAME
            if (r1 != r0) goto L_0x004f
            java.lang.String r2 = r5.A0d()
            int[] r1 = X.C05590lW.A00
            X.0ji r0 = r5.A0b()
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x004a
            r0 = 2
            if (r1 == r0) goto L_0x0045
            r0 = 3
            if (r1 == r0) goto L_0x003c
            X.0gY r1 = r4.A0P(r5, r6, r7)
        L_0x0032:
            if (r1 != 0) goto L_0x0036
            X.039 r1 = X.AnonymousClass039.A00
        L_0x0036:
            java.util.Map<java.lang.String, X.0gY> r0 = r3.A00
            r0.put(r2, r1)
            goto L_0x000d
        L_0x003c:
            java.lang.String r0 = r5.A0e()
            X.030 r1 = X.AnonymousClass030.A00(r0)
            goto L_0x0032
        L_0x0045:
            X.03C r1 = r4.A0Q(r5, r6, r7)
            goto L_0x0032
        L_0x004a:
            X.033 r1 = r4.A0R(r5, r6, r7)
            goto L_0x0032
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.A0R(X.0gp, X.0gg, X.0fv):X.033");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A08() {
        return AnonymousClass039.A00;
    }
}
