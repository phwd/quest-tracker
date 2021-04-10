package com.fasterxml.jackson.databind.deser;

import X.AbstractC0122Rd;
import X.AnonymousClass1W;
import X.AnonymousClass7B;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.C00020k;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass7B _buildMethod;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        JsonDeserializer<Object> jsonDeserializer;
        AnonymousClass9p r2 = ((B3) rn).A00;
        if (r2 == AnonymousClass9p.START_OBJECT) {
            rn.A04();
            if (this._vanillaProcessing) {
                throw null;
            }
        } else {
            switch (AnonymousClass1W.A00[r2.ordinal()]) {
                case 1:
                    C00020k r0 = this._objectIdReader;
                    if (r0 != null) {
                        jsonDeserializer = r0.deserializer;
                    } else {
                        jsonDeserializer = this._delegateDeserializer;
                        if (jsonDeserializer == null) {
                            rn.A09();
                            throw null;
                        }
                    }
                    jsonDeserializer.A03(rn, rd);
                    throw null;
                case 2:
                    A0C(rn, rd);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                case 3:
                    A0B(rn, rd);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                case 4:
                    return rn.A07();
                case 5:
                case 6:
                    jsonDeserializer = this._delegateDeserializer;
                    if (jsonDeserializer == null) {
                        throw null;
                    }
                    jsonDeserializer.A03(rn, rd);
                    throw null;
                case 7:
                    JsonDeserializer<Object> jsonDeserializer2 = this._delegateDeserializer;
                    if (jsonDeserializer2 != null) {
                        try {
                            jsonDeserializer2.A03(rn, rd);
                            throw null;
                        } catch (Exception e) {
                            A0E(e, rd);
                            throw null;
                        }
                    } else {
                        throw null;
                    }
                case 8:
                case 9:
                    break;
                default:
                    throw null;
            }
        }
        A0A(rn, rd);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
