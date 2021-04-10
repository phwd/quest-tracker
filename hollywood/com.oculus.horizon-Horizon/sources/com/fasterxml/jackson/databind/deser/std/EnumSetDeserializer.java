package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements AbstractC05430l6 {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class<Enum> _enumClass;
    public JsonDeserializer<Enum<?>> _enumDeserializer;
    public final AbstractC04000gb _enumType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<java.lang.Enum> */
    public EnumSetDeserializer(AbstractC04000gb r2, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumType = r2;
        this._enumClass = r2._class;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r4, AbstractC04030gh r5) throws C03990gZ {
        JsonDeserializer<?> jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r4.A05(this._enumType, r5);
        } else if (jsonDeserializer instanceof AbstractC05430l6) {
            jsonDeserializer = ((AbstractC05430l6) jsonDeserializer).A21(r4, r5);
        }
        if (this._enumDeserializer == jsonDeserializer) {
            return this;
        }
        return new EnumSetDeserializer(this._enumType, jsonDeserializer);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet<?> A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        if (r4.A0G()) {
            EnumSet<?> noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                EnumC04820ji A0b = r4.A0b();
                if (A0b == EnumC04820ji.END_ARRAY) {
                    return noneOf;
                }
                if (A0b != EnumC04820ji.VALUE_NULL) {
                    Enum<?> A09 = this._enumDeserializer.A09(r4, r5);
                    if (A09 != null) {
                        noneOf.add(A09);
                    }
                } else {
                    throw null;
                }
            }
        } else {
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }
}
