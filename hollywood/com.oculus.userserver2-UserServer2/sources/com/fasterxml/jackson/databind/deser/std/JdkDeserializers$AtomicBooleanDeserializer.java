package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9r;
import X.Rn;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        if (r4.A00() != 0) goto L_0x0024;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.concurrent.atomic.AtomicBoolean A00(X.Rn r4) throws java.io.IOException, X.AnonymousClass9r {
        /*
            r3 = this;
            r0 = r4
            X.B3 r0 = (X.B3) r0
            X.9p r1 = r0.A00
            X.9p r0 = X.AnonymousClass9p.VALUE_TRUE
            r2 = 1
            if (r1 == r0) goto L_0x0024
            X.9p r0 = X.AnonymousClass9p.VALUE_FALSE
            if (r1 == r0) goto L_0x0051
            X.9p r0 = X.AnonymousClass9p.VALUE_NULL
            if (r1 == r0) goto L_0x0051
            X.9p r0 = X.AnonymousClass9p.VALUE_NUMBER_INT
            if (r1 != r0) goto L_0x002f
            java.lang.Integer r1 = r4.A05()
            java.lang.Integer r0 = X.AnonymousClass07.A00
            if (r1 != r0) goto L_0x002a
            int r0 = r4.A00()
            if (r0 == 0) goto L_0x0051
        L_0x0024:
            java.util.concurrent.atomic.AtomicBoolean r0 = new java.util.concurrent.atomic.AtomicBoolean
            r0.<init>(r2)
            return r0
        L_0x002a:
            boolean r2 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A01(r4)
            goto L_0x0024
        L_0x002f:
            X.9p r0 = X.AnonymousClass9p.VALUE_STRING
            if (r1 != r0) goto L_0x0053
            java.lang.String r0 = r4.A09()
            java.lang.String r1 = r0.trim()
            java.lang.String r0 = "true"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0024
            java.lang.String r0 = "false"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x0051
            int r0 = r1.length()
            if (r0 != 0) goto L_0x0053
        L_0x0051:
            r2 = 0
            goto L_0x0024
        L_0x0053:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.JdkDeserializers$AtomicBooleanDeserializer.A00(X.Rn):java.util.concurrent.atomic.AtomicBoolean");
    }

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }
}
