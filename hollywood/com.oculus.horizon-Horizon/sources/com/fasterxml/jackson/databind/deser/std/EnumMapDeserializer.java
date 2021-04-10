package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public final class EnumMapDeserializer extends StdDeserializer<EnumMap<?, ?>> implements AbstractC05430l6 {
    public static final long serialVersionUID = 1518773374647478964L;
    public final Class<?> _enumClass;
    public JsonDeserializer<Enum<?>> _keyDeserializer;
    public final AbstractC04000gb _mapType;
    public JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0m9 _valueTypeDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public EnumMapDeserializer(AbstractC04000gb r2, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AnonymousClass0m9 r5) {
        super(EnumMap.class);
        this._mapType = r2;
        this._enumClass = r2.A04()._class;
        this._keyDeserializer = jsonDeserializer;
        this._valueDeserializer = jsonDeserializer2;
        this._valueTypeDeserializer = r5;
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r6, AbstractC04030gh r7) throws C03990gZ {
        JsonDeserializer<Object> jsonDeserializer = this._keyDeserializer;
        if (jsonDeserializer == null) {
            jsonDeserializer = r6.A05(this._mapType.A04(), r7);
        }
        JsonDeserializer<?> jsonDeserializer2 = this._valueDeserializer;
        if (jsonDeserializer2 == null) {
            jsonDeserializer2 = r6.A05(this._mapType.A03(), r7);
        } else if (jsonDeserializer2 instanceof AbstractC05430l6) {
            jsonDeserializer2 = ((AbstractC05430l6) jsonDeserializer2).A21(r6, r7);
        }
        AnonymousClass0m9 r1 = this._valueTypeDeserializer;
        if (r1 != null) {
            r1 = r1.A04(r7);
        }
        if (jsonDeserializer == this._keyDeserializer && jsonDeserializer2 == this._valueDeserializer && r1 == r1) {
            return this;
        }
        return new EnumMapDeserializer(this._mapType, jsonDeserializer, jsonDeserializer2, r1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)|20|21) */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0057, code lost:
        throw null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0056 */
    /* renamed from: A00 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.EnumMap<?, ?> A09(X.AbstractC04100gp r8, X.AbstractC04020gg r9) throws java.io.IOException, X.AnonymousClass0jg {
        /*
            r7 = this;
            X.0ji r1 = r8.A0a()
            X.0ji r0 = X.EnumC04820ji.START_OBJECT
            if (r1 != r0) goto L_0x0059
            java.lang.Class<?> r0 = r7._enumClass
            java.util.EnumMap r6 = new java.util.EnumMap
            r6.<init>(r0)
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r5 = r7._valueDeserializer
            X.0m9 r4 = r7._valueTypeDeserializer
        L_0x0013:
            X.0ji r1 = r8.A0b()
            X.0ji r0 = X.EnumC04820ji.END_OBJECT
            if (r1 == r0) goto L_0x0058
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Enum<?>> r0 = r7._keyDeserializer
            java.lang.Object r3 = r0.A09(r8, r9)
            java.lang.Enum r3 = (java.lang.Enum) r3
            r2 = 0
            if (r3 != 0) goto L_0x0036
            X.0gf r0 = X.EnumC04010gf.READ_UNKNOWN_ENUM_VALUES_AS_NULL
            boolean r0 = r9.A0I(r0)
            if (r0 != 0) goto L_0x002f
            goto L_0x004d
        L_0x002f:
            r8.A0b()
            r8.A0Z()
            goto L_0x0013
        L_0x0036:
            X.0ji r1 = r8.A0b()
            X.0ji r0 = X.EnumC04820ji.VALUE_NULL
            if (r1 == r0) goto L_0x0044
            if (r4 != 0) goto L_0x0048
            java.lang.Object r2 = r5.A09(r8, r9)
        L_0x0044:
            r6.put(r3, r2)
            goto L_0x0013
        L_0x0048:
            java.lang.Object r2 = r5.A0C(r8, r9, r4)
            goto L_0x0044
        L_0x004d:
            boolean r0 = r8.A0g()     // Catch:{ Exception -> 0x0056 }
            if (r0 == 0) goto L_0x0056
            r8.A0e()     // Catch:{ Exception -> 0x0056 }
        L_0x0056:
            throw r2     // Catch:{ Exception -> 0x0057 }
        L_0x0057:
            throw r2
        L_0x0058:
            return r6
        L_0x0059:
            r0 = 0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer.A09(X.0gp, X.0gg):java.util.EnumMap");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A09(r2, r3);
    }
}
