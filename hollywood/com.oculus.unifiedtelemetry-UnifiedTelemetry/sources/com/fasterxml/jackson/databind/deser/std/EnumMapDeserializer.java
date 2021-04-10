package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.C0223Wj;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements Zy {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class<?> _enumClass;
    public JsonDeserializer<Enum<?>> _keyDeserializer;
    public final AbstractC0224Wl _mapType;
    public JsonDeserializer<Object> _valueDeserializer;
    public final V4 _valueTypeDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(AbstractC0224Wl wl, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, V4 v4) {
        super(EnumMap.class);
        this._mapType = wl;
        this._enumClass = wl.A04()._class;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = v4;
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<Object> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = wn.A06(this._mapType.A04(), wo);
        }
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = wn.A06(this._mapType.A03(), wo);
        } else if (jsonDeserializer2 instanceof Zy) {
            jsonDeserializer2 = ((Zy) jsonDeserializer2).A1g(wn, wo);
        }
        V4 v4 = this._valueTypeDeserializer;
        if (v4 != null) {
            v4 = v4.A04(wo);
        }
        if (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && v4 == v4) {
            return this;
        }
        return new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, v4);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumMap<?, ?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (ww.A0Z() == EnumC0470q2.START_OBJECT) {
            EnumMap<?, ?> enumMap = new EnumMap<>(this._enumClass);
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            V4 v4 = this._valueTypeDeserializer;
            while (ww.A0a() != EnumC0470q2.END_OBJECT) {
                Enum<?> A09 = this._keyDeserializer.A09(ww, wn);
                Object obj = null;
                r2 = null;
                String str = null;
                if (A09 != null) {
                    if (ww.A0a() != EnumC0470q2.VALUE_NULL) {
                        if (v4 == null) {
                            obj = jsonDeserializer.A09(ww, wn);
                        } else {
                            obj = jsonDeserializer.A0C(ww, wn, v4);
                        }
                    }
                    enumMap.put((Object) A09, (Object) obj);
                } else if (!wn.A0L(EnumC0225Wm.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    try {
                        if (ww.A0g()) {
                            str = ww.A0d();
                        }
                    } catch (Exception unused) {
                    }
                    throw wn.A0D(str, this._enumClass, "value not one of declared Enum instance names");
                } else {
                    ww.A0a();
                    ww.A0Y();
                }
            }
            return enumMap;
        }
        throw wn.A08(EnumMap.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A09(ww, wn);
    }
}
