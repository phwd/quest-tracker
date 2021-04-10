package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.C0223Wj;
import X.EnumC0470q2;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements Zy {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class<Enum> _enumClass;
    public JsonDeserializer<Enum<?>> _enumDeserializer;
    public final AbstractC0224Wl _enumType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<java.lang.Enum> */
    public EnumSetDeserializer(AbstractC0224Wl wl, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumType = wl;
        this._enumClass = wl._class;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<?> jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = wn.A06(this._enumType, wo);
        } else if (jsonDeserializer instanceof Zy) {
            jsonDeserializer = ((Zy) jsonDeserializer).A1g(wn, wo);
        }
        if (this._enumDeserializer == jsonDeserializer) {
            return this;
        }
        return new EnumSetDeserializer(this._enumType, jsonDeserializer);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet<?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Class<?> cls;
        if (ww.A0F()) {
            EnumSet<?> noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                EnumC0470q2 A0a = ww.A0a();
                if (A0a != EnumC0470q2.END_ARRAY) {
                    if (A0a == EnumC0470q2.VALUE_NULL) {
                        cls = this._enumClass;
                        break;
                    }
                    Enum<?> A09 = this._enumDeserializer.A09(ww, wn);
                    if (A09 != null) {
                        noneOf.add(A09);
                    }
                } else {
                    return noneOf;
                }
            }
        } else {
            cls = EnumSet.class;
        }
        throw wn.A08(cls);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }
}
