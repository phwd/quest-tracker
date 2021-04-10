package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass09;
import X.AnonymousClass0S;
import X.AnonymousClass0T;
import X.AnonymousClass0W;
import X.AnonymousClass0X;
import X.AnonymousClass0Y;
import X.AnonymousClass0Z;
import X.C00396o;
import X.C00406p;
import X.C00416q;
import X.C00436s;
import X.C00466v;
import X.C1025qv;
import X.EnumC1023qs;
import X.NX;
import X.OA;
import X.P0;
import X.PW;
import com.oculus.aidl.OVRServiceInterface;
import java.math.BigDecimal;

public abstract class BaseNodeDeserializer extends StdDeserializer {
    public BaseNodeDeserializer() {
        super(OA.class);
    }

    public final OA A0N(AbstractC1014qi qiVar, AbstractC1022qr qrVar, PW pw) {
        switch (P0.A00[qiVar.A0U().ordinal()]) {
            case 1:
            case 5:
                return A0P(qiVar, qrVar, pw);
            case 2:
                return A0O(qiVar, qrVar, pw);
            case 3:
                return AnonymousClass0S.A00(qiVar.A0p());
            case 4:
            default:
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            case 6:
                Object A0Z = qiVar.A0Z();
                if (A0Z == null) {
                    return AnonymousClass0W.A00;
                }
                if (A0Z.getClass() != byte[].class) {
                    return new AnonymousClass0T(A0Z);
                }
                byte[] bArr = (byte[]) A0Z;
                if (bArr == null) {
                    return null;
                }
                if (bArr.length == 0) {
                    return AnonymousClass0Y.A01;
                }
                return new AnonymousClass0Y(bArr);
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                Integer A0X = qiVar.A0X();
                if (A0X == AnonymousClass09.A0C || qrVar.A0O(EnumC1023qs.USE_BIG_INTEGER_FOR_INTS)) {
                    return new C00466v(qiVar.A0d());
                }
                if (A0X != AnonymousClass09.A00) {
                    return new C00396o(qiVar.A0O());
                }
                int A0J = qiVar.A0J();
                if (A0J > 10 || A0J < -1) {
                    return new C00406p(A0J);
                }
                return C00406p.A01[A0J - -1];
            case 8:
                if (qiVar.A0X() != AnonymousClass09.A0L && !qrVar.A0O(EnumC1023qs.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return new C00416q(qiVar.A0F());
                }
                BigDecimal A0c = qiVar.A0c();
                if (pw._cfgBigDecimalExact) {
                    return new C00436s(A0c);
                }
                if (A0c.compareTo(BigDecimal.ZERO) == 0) {
                    return C00436s.A01;
                }
                return new C00436s(A0c.stripTrailingZeros());
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                return AnonymousClass0X.A02;
            case 10:
                return AnonymousClass0X.A01;
            case OVRServiceInterface.Stub.TRANSACTION_getInstalledVRApplications /*{ENCODED_INT: 11}*/:
                return AnonymousClass0W.A00;
        }
    }

    public final AnonymousClass0Z A0O(AbstractC1014qi qiVar, AbstractC1022qr qrVar, PW pw) {
        OA A0P;
        AnonymousClass0Z r2 = new AnonymousClass0Z(pw);
        while (true) {
            NX A0o = qiVar.A0o();
            if (A0o != null) {
                int i = P0.A00[A0o.ordinal()];
                if (i == 1) {
                    A0P = A0P(qiVar, qrVar, pw);
                } else if (i != 2) {
                    if (i == 3) {
                        A0P = AnonymousClass0S.A00(qiVar.A0p());
                    } else if (i == 4) {
                        return r2;
                    } else {
                        A0P = A0N(qiVar, qrVar, pw);
                    }
                    if (A0P == null) {
                        A0P = AnonymousClass0W.A00;
                    }
                } else {
                    A0P = A0O(qiVar, qrVar, pw);
                }
                r2.A00.add(A0P);
            } else {
                throw C1025qv.A00(null, "Unexpected end-of-input when binding data into ArrayNode");
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002c, code lost:
        if (r1 == 3) goto L_0x003c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002e, code lost:
        r1 = A0N(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r1 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = X.AnonymousClass0W.A00;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0036, code lost:
        r3.A00.put(r2, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003c, code lost:
        r1 = X.AnonymousClass0S.A00(r5.A0p());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0045, code lost:
        r1 = A0O(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        r1 = A0P(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004f, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:1:0x000b, code lost:
        if (r1 == X.NX.START_OBJECT) goto L_0x000d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r5.A0o();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        if (r1 != X.NX.FIELD_NAME) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r5.A0b();
        r1 = X.P0.A00[r5.A0o().ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0026, code lost:
        if (r1 == 1) goto L_0x004a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0029, code lost:
        if (r1 == 2) goto L_0x0045;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AnonymousClass0U A0P(X.AbstractC1014qi r5, X.AbstractC1022qr r6, X.PW r7) {
        /*
            r4 = this;
            X.0U r3 = new X.0U
            r3.<init>(r7)
            X.NX r1 = r5.A0U()
            X.NX r0 = X.NX.START_OBJECT
            if (r1 != r0) goto L_0x0011
        L_0x000d:
            X.NX r1 = r5.A0o()
        L_0x0011:
            X.NX r0 = X.NX.FIELD_NAME
            if (r1 != r0) goto L_0x004f
            java.lang.String r2 = r5.A0b()
            int[] r1 = X.P0.A00
            X.NX r0 = r5.A0o()
            int r0 = r0.ordinal()
            r1 = r1[r0]
            r0 = 1
            if (r1 == r0) goto L_0x004a
            r0 = 2
            if (r1 == r0) goto L_0x0045
            r0 = 3
            if (r1 == r0) goto L_0x003c
            X.OA r1 = r4.A0N(r5, r6, r7)
        L_0x0032:
            if (r1 != 0) goto L_0x0036
            X.0W r1 = X.AnonymousClass0W.A00
        L_0x0036:
            java.util.Map r0 = r3.A00
            r0.put(r2, r1)
            goto L_0x000d
        L_0x003c:
            java.lang.String r0 = r5.A0p()
            X.0S r1 = X.AnonymousClass0S.A00(r0)
            goto L_0x0032
        L_0x0045:
            X.0Z r1 = r4.A0O(r5, r6, r7)
            goto L_0x0032
        L_0x004a:
            X.0U r1 = r4.A0P(r5, r6, r7)
            goto L_0x0032
        L_0x004f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.BaseNodeDeserializer.A0P(X.qi, X.qr, X.PW):X.0U");
    }
}
