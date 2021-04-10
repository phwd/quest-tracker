package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumSet;

public final class EnumSetDeserializer extends StdDeserializer<EnumSet<?>> implements AbstractC06520n2 {
    public static final long serialVersionUID = 3479455075597887177L;
    public final Class<Enum> _enumClass;
    public JsonDeserializer<Enum<?>> _enumDeserializer;
    public final AnonymousClass0aI _enumType;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX DEBUG: Type inference failed for r0v1. Raw type applied. Possible types: java.lang.Class<?>, java.lang.Class<java.lang.Enum> */
    public EnumSetDeserializer(AnonymousClass0aI r2, JsonDeserializer<?> jsonDeserializer) {
        super(EnumSet.class);
        this._enumType = r2;
        this._enumClass = r2._class;
        this._enumDeserializer = jsonDeserializer;
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonDeserializer<?> jsonDeserializer = this._enumDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r4.A09(this._enumType, r5);
        } else if (jsonDeserializer instanceof AbstractC06520n2) {
            jsonDeserializer = ((AbstractC06520n2) jsonDeserializer).A1w(r4, r5);
        }
        if (this._enumDeserializer == jsonDeserializer) {
            return this;
        }
        return new EnumSetDeserializer(this._enumType, jsonDeserializer);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final EnumSet<?> A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        Class<?> cls;
        if (r4.A0V()) {
            EnumSet<?> noneOf = EnumSet.noneOf(this._enumClass);
            while (true) {
                EnumC05930lf A0a = r4.A0a();
                if (A0a != EnumC05930lf.END_ARRAY) {
                    if (A0a == EnumC05930lf.VALUE_NULL) {
                        cls = this._enumClass;
                        break;
                    }
                    Enum<?> A09 = this._enumDeserializer.A09(r4, r5);
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
        throw r5.A0B(cls);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }
}
