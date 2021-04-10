package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass1V;
import X.AnonymousClass7F;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RZ;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements AnonymousClass1V {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class<?> _enumClass;
    public final JsonDeserializer<Enum<?>> _keyDeserializer;
    public final RZ _mapType;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass7F _valueTypeDeserializer;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumMap<?, ?> A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 == AnonymousClass9p.START_OBJECT) {
            EnumMap<?, ?> enumMap = new EnumMap<>(this._enumClass);
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            while (rn.A04() != AnonymousClass9p.END_OBJECT) {
                Enum<?> A03 = this._keyDeserializer.A03(rn, rd);
                Object obj = null;
                if (A03 == null) {
                    throw null;
                }
                if (rn.A04() != AnonymousClass9p.VALUE_NULL) {
                    obj = jsonDeserializer.A03(rn, rd);
                }
                enumMap.put((Object) A03, (Object) obj);
            }
            return enumMap;
        }
        throw null;
    }
}
