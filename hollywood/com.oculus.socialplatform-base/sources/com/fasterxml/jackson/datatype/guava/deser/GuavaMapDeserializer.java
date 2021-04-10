package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.AnonymousClass03D;
import X.AnonymousClass0p6;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public abstract class GuavaMapDeserializer<T> extends JsonDeserializer<T> implements AbstractC04230pb {
    public JsonDeserializer<?> A00;
    public AnonymousClass0p6 A01;
    public final AbstractC04520qa A02;
    public final AnonymousClass03D A03;

    public abstract GuavaMapDeserializer<T> A0F(AnonymousClass0p6 v, AbstractC04520qa v2, JsonDeserializer<?> jsonDeserializer);

    public abstract T A0G(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0017, code lost:
        if (r2 == null) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        if (r1 != null) goto L_0x0025;
     */
    @Override // X.AbstractC04230pb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A2O(X.AbstractC02210iH r5, X.AbstractC02220iI r6) throws X.C02180iD {
        /*
            r4 = this;
            X.0p6 r3 = r4.A01
            com.fasterxml.jackson.databind.JsonDeserializer<?> r2 = r4.A00
            X.0qa r1 = r4.A02
            if (r3 == 0) goto L_0x000d
            if (r2 == 0) goto L_0x0019
            if (r1 != 0) goto L_0x0025
            return r4
        L_0x000d:
            X.03D r0 = r4.A03
            X.0iF r0 = r0.A05()
            X.0p6 r3 = r5.A0H(r0)
            if (r2 != 0) goto L_0x0023
        L_0x0019:
            X.03D r0 = r4.A03
            X.0iF r0 = r0.A04()
            com.fasterxml.jackson.databind.JsonDeserializer r2 = r5.A09(r0, r6)
        L_0x0023:
            if (r1 == 0) goto L_0x0029
        L_0x0025:
            X.0qa r1 = r1.A04(r6)
        L_0x0029:
            com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer r0 = r4.A0F(r3, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.datatype.guava.deser.GuavaMapDeserializer.A2O(X.0iH, X.0iI):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public GuavaMapDeserializer(AnonymousClass03D r1, AnonymousClass0p6 r2, AbstractC04520qa r3, JsonDeserializer<?> jsonDeserializer) {
        this.A03 = r1;
        this.A01 = r2;
        this.A02 = r3;
        this.A00 = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        EnumC03640oE A0i = r3.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            EnumC03640oE A0j = r3.A0j();
            if (!(A0j == EnumC03640oE.FIELD_NAME || A0j == EnumC03640oE.END_OBJECT)) {
                throw r4.A0B(this.A03._class);
            }
        } else if (A0i != EnumC03640oE.FIELD_NAME) {
            throw r4.A0B(this.A03._class);
        }
        return A0G(r3, r4);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }
}
