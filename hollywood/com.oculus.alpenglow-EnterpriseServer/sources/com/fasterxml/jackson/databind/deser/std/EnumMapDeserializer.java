package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements AbstractC06520n2 {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class<?> _enumClass;
    public JsonDeserializer<Enum<?>> _keyDeserializer;
    public final AnonymousClass0aI _mapType;
    public JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0o3 _valueTypeDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(AnonymousClass0aI r2, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AnonymousClass0o3 r5) {
        super(EnumMap.class);
        this._mapType = r2;
        this._enumClass = r2.A05()._class;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = r5;
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r6, AbstractC02580aL r7) throws AnonymousClass0aG {
        JsonDeserializer<Object> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r6.A09(this._mapType.A05(), r7);
        }
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = r6.A09(this._mapType.A04(), r7);
        } else if (jsonDeserializer2 instanceof AbstractC06520n2) {
            jsonDeserializer2 = ((AbstractC06520n2) jsonDeserializer2).A1w(r6, r7);
        }
        AnonymousClass0o3 r1 = this._valueTypeDeserializer;
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
    public final EnumMap<?, ?> A09(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        if (r8.A0G() == EnumC05930lf.START_OBJECT) {
            EnumMap<?, ?> enumMap = new EnumMap<>(this._enumClass);
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0o3 r4 = this._valueTypeDeserializer;
            while (r8.A0a() != EnumC05930lf.END_OBJECT) {
                Enum<?> A09 = this._keyDeserializer.A09(r8, r9);
                Object obj = null;
                r2 = null;
                String str = null;
                if (A09 != null) {
                    if (r8.A0a() != EnumC05930lf.VALUE_NULL) {
                        if (r4 == null) {
                            obj = jsonDeserializer.A09(r8, r9);
                        } else {
                            obj = jsonDeserializer.A0C(r8, r9, r4);
                        }
                    }
                    enumMap.put((Object) A09, (Object) obj);
                } else if (!r9.A0O(EnumC02560aJ.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    try {
                        if (r8.A0W()) {
                            str = r8.A0P();
                        }
                    } catch (Exception unused) {
                    }
                    throw r9.A0G(str, this._enumClass, "value not one of declared Enum instance names");
                } else {
                    r8.A0a();
                    r8.A0F();
                }
            }
            return enumMap;
        }
        throw r9.A0B(EnumMap.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A09(r2, r3);
    }
}
