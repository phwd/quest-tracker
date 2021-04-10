package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C00313p;
import X.C1165uK;
import X.NX;
import X.OD;
import X.PR;
import X.u7;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.NaturalOrdering;

public abstract class GuavaMapDeserializer extends JsonDeserializer implements AbstractC0264Od {
    public JsonDeserializer A00;
    public OD A01;
    public final PR A02;
    public final C00313p A03;

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r3 == null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        if (r2 != null) goto L_0x0025;
     */
    @Override // X.AbstractC0264Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer A1X(X.AbstractC1022qr r6, X.O5 r7) {
        /*
            r5 = this;
            X.OD r4 = r5.A01
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r5.A00
            X.PR r2 = r5.A02
            if (r4 == 0) goto L_0x000d
            if (r3 == 0) goto L_0x0019
            if (r2 != 0) goto L_0x0025
            return r5
        L_0x000d:
            X.3p r0 = r5.A03
            X.qt r0 = r0.A05()
            X.OD r4 = r6.A0D(r0)
            if (r3 != 0) goto L_0x0023
        L_0x0019:
            X.3p r0 = r5.A03
            X.qt r0 = r0.A04()
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r6.A08(r0, r7)
        L_0x0023:
            if (r2 == 0) goto L_0x0029
        L_0x0025:
            X.PR r2 = r2.A03(r7)
        L_0x0029:
            boolean r0 = r5 instanceof com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer
            if (r0 != 0) goto L_0x0041
            boolean r0 = r5 instanceof com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer
            if (r0 != 0) goto L_0x0039
            X.3p r0 = r5.A03
            com.fasterxml.jackson.datatype.guava.deser.ImmutableBiMapDeserializer r1 = new com.fasterxml.jackson.datatype.guava.deser.ImmutableBiMapDeserializer
            r1.<init>(r0, r4, r2, r3)
            return r1
        L_0x0039:
            X.3p r0 = r5.A03
            com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer r1 = new com.fasterxml.jackson.datatype.guava.deser.ImmutableMapDeserializer
            r1.<init>(r0, r4, r2, r3)
            return r1
        L_0x0041:
            X.3p r0 = r5.A03
            com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer r1 = new com.fasterxml.jackson.datatype.guava.deser.ImmutableSortedMapDeserializer
            r1.<init>(r0, r4, r2, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer.A1X(X.qr, X.O5):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public GuavaMapDeserializer(C00313p r1, OD od, PR pr, JsonDeserializer jsonDeserializer) {
        this.A03 = r1;
        this.A01 = od;
        this.A02 = pr;
        this.A00 = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        u7 u7Var;
        Object A09;
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            NX A0o = qiVar.A0o();
            if (!(A0o == NX.FIELD_NAME || A0o == NX.END_OBJECT)) {
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (A0U != NX.FIELD_NAME) {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        GuavaImmutableMapDeserializer guavaImmutableMapDeserializer = (GuavaImmutableMapDeserializer) this;
        OD od = guavaImmutableMapDeserializer.A01;
        JsonDeserializer jsonDeserializer = guavaImmutableMapDeserializer.A00;
        PR pr = guavaImmutableMapDeserializer.A02;
        if (guavaImmutableMapDeserializer instanceof ImmutableSortedMapDeserializer) {
            u7Var = new C1165uK(NaturalOrdering.A00);
        } else if (!(guavaImmutableMapDeserializer instanceof ImmutableMapDeserializer)) {
            u7Var = new u7();
        } else {
            u7Var = new ImmutableMap.Builder(4);
        }
        while (qiVar.A0U() == NX.FIELD_NAME) {
            String A0b = qiVar.A0b();
            String str = A0b;
            if (od != null) {
                str = od.A00(A0b, qrVar);
            }
            if (qiVar.A0o() == NX.VALUE_NULL) {
                A09 = null;
            } else if (pr == null) {
                A09 = jsonDeserializer.A0C(qiVar, qrVar);
            } else {
                A09 = jsonDeserializer.A09(qiVar, qrVar, pr);
            }
            u7Var.put(str, A09);
            qiVar.A0o();
        }
        return u7Var.build();
    }
}
