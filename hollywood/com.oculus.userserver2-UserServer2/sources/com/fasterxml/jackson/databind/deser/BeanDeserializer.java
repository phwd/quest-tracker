package com.fasterxml.jackson.databind.deser;

import X.A3;
import X.AbstractC0122Rd;
import X.AnonymousClass1Z;
import X.AnonymousClass6z;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.C00020k;
import X.Rl;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;

public class BeanDeserializer extends BeanDeserializerBase implements Serializable {
    public static final long serialVersionUID = 1;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        JsonDeserializer<Object> jsonDeserializer;
        B3 b3 = (B3) rn;
        AnonymousClass9p r2 = b3.A00;
        if (r2 == AnonymousClass9p.START_OBJECT) {
            if (this._vanillaProcessing) {
                rn.A04();
                throw null;
            }
            rn.A04();
        } else if (r2 != null) {
            switch (AnonymousClass1Z.A00[r2.ordinal()]) {
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
                    if (this._vanillaProcessing) {
                        throw null;
                    }
                    break;
                default:
                    throw null;
            }
        } else {
            throw null;
        }
        C00020k r02 = this._objectIdReader;
        if (r02 != null) {
            String str = r02.propertyName;
            if (!str.equals(rn.A08())) {
                A3 a3 = new A3(null);
                A3 a32 = null;
                while (true) {
                    AnonymousClass9p r1 = b3.A00;
                    AnonymousClass9p r03 = AnonymousClass9p.END_OBJECT;
                    if (r1 != r03) {
                        String A08 = rn.A08();
                        if (a32 != null) {
                            a32.A08(A08);
                            rn.A04();
                            a32.A07(rn);
                        } else if (str.equals(A08)) {
                            a32 = new A3(null);
                            a32.A08(A08);
                            rn.A04();
                            a32.A07(rn);
                            AnonymousClass6z r12 = new AnonymousClass6z(a3.A01, null);
                            while (r12.A04() != null) {
                                A3.A00(a32, r12);
                            }
                            a3 = null;
                        } else {
                            a3.A08(A08);
                            rn.A04();
                            a3.A07(rn);
                        }
                        rn.A04();
                    } else {
                        if (a32 == null) {
                            a32 = a3;
                        }
                        A3.A01(a32, r03);
                        Rl rl = a32.A00.A02;
                        if (rl != null) {
                            a32.A00 = rl;
                        }
                        AnonymousClass6z r04 = new AnonymousClass6z(a32.A01, null);
                        r04.A04();
                        A0A(r04, rd);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            }
        }
        A0A(rn, rd);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
