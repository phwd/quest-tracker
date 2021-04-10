package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.C02180iD;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements AbstractC04230pb {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class<?> _enumClass;
    public JsonDeserializer<Enum<?>> _keyDeserializer;
    public final AbstractC02190iF _mapType;
    public JsonDeserializer<Object> _valueDeserializer;
    public final AbstractC04520qa _valueTypeDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(AbstractC02190iF r2, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AbstractC04520qa r5) {
        super(EnumMap.class);
        this._mapType = r2;
        this._enumClass = r2.A05()._class;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = r5;
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r6, AbstractC02220iI r7) throws C02180iD {
        JsonDeserializer<Object> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r6.A09(this._mapType.A05(), r7);
        }
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = r6.A09(this._mapType.A04(), r7);
        } else if (jsonDeserializer2 instanceof AbstractC04230pb) {
            jsonDeserializer2 = ((AbstractC04230pb) jsonDeserializer2).A2O(r6, r7);
        }
        AbstractC04520qa r1 = this._valueTypeDeserializer;
        if (r1 != null) {
            r1 = r1.A04(r7);
        }
        if (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && r1 == r1) {
            return this;
        }
        return new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, r1);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumMap<?, ?> A0A(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        if (r8.A0i() == EnumC03640oE.START_OBJECT) {
            EnumMap<?, ?> enumMap = new EnumMap<>(this._enumClass);
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AbstractC04520qa r4 = this._valueTypeDeserializer;
            while (r8.A0j() != EnumC03640oE.END_OBJECT) {
                Enum<?> A0A = this._keyDeserializer.A0A(r8, r9);
                Object obj = null;
                r2 = null;
                String str = null;
                if (A0A != null) {
                    if (r8.A0j() != EnumC03640oE.VALUE_NULL) {
                        if (r4 == null) {
                            obj = jsonDeserializer.A0A(r8, r9);
                        } else {
                            obj = jsonDeserializer.A0B(r8, r9, r4);
                        }
                    }
                    enumMap.put((Object) A0A, (Object) obj);
                } else if (!r9.A0P(EnumC02200iG.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    try {
                        if (r8.A0o()) {
                            str = r8.A0m();
                        }
                    } catch (Exception unused) {
                    }
                    throw r9.A0G(str, this._enumClass, "value not one of declared Enum instance names");
                } else {
                    r8.A0j();
                    r8.A0h();
                }
            }
            return enumMap;
        }
        throw r9.A0B(EnumMap.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A09(r2, r3);
    }
}
