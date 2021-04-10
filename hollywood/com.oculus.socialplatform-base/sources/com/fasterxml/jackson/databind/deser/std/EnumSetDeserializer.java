package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04520qa;
import X.C02180iD;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements AbstractC04230pb {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class<Enum> _enumClass;
    public JsonDeserializer<Enum<?>> _enumDeserializer;
    public final AbstractC02190iF _enumType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<java.lang.Enum> */
    public EnumSetDeserializer(AbstractC02190iF r2, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumType = r2;
        this._enumClass = r2._class;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r4, AbstractC02220iI r5) throws C02180iD {
        JsonDeserializer<?> jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r4.A09(this._enumType, r5);
        } else if (jsonDeserializer instanceof AbstractC04230pb) {
            jsonDeserializer = ((AbstractC04230pb) jsonDeserializer).A2O(r4, r5);
        }
        if (this._enumDeserializer == jsonDeserializer) {
            return this;
        }
        return new EnumSetDeserializer(this._enumType, jsonDeserializer);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet<?> A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        if (r4.A0K()) {
            EnumSet<?> noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                EnumC03640oE A0j = r4.A0j();
                if (A0j == EnumC03640oE.END_ARRAY) {
                    return noneOf;
                }
                if (A0j != EnumC03640oE.VALUE_NULL) {
                    Enum<?> A0A = this._enumDeserializer.A0A(r4, r5);
                    if (A0A != null) {
                        noneOf.add(A0A);
                    }
                } else {
                    throw r5.A0B(this._enumClass);
                }
            }
        } else {
            throw r5.A0B(EnumSet.class);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }
}
