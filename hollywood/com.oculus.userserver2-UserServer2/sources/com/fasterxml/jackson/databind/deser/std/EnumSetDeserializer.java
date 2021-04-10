package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements AnonymousClass1V {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class<Enum> _enumClass;
    public final JsonDeserializer<Enum<?>> _enumDeserializer;
    public final RZ _enumType;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet<?> A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 == AnonymousClass9p.START_ARRAY) {
            EnumSet<?> noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                AnonymousClass9p A04 = rn.A04();
                if (A04 == AnonymousClass9p.END_ARRAY) {
                    return noneOf;
                }
                if (A04 != AnonymousClass9p.VALUE_NULL) {
                    Enum<?> A03 = this._enumDeserializer.A03(rn, rd);
                    if (A03 != null) {
                        noneOf.add(A03);
                    }
                } else {
                    throw null;
                }
            }
        } else {
            throw null;
        }
    }
}
