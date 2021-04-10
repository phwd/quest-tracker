package com.fasterxml.jackson.databind.deser;

import X.AbstractC01680Ku;
import X.AbstractC02570aK;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0nL;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C06470mw;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public final class AbstractDeserializer extends JsonDeserializer<Object> implements Serializable {
    public static final long serialVersionUID = -3010349050434697698L;
    public final boolean _acceptBoolean;
    public final boolean _acceptDouble;
    public final boolean _acceptInt;
    public final boolean _acceptString;
    public final Map<String, AbstractC01680Ku> _backRefProperties;
    public final AnonymousClass0aI _baseType;
    public final AnonymousClass0nL _objectIdReader;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        throw r4.A0D(this._baseType._class, "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r4, AbstractC02570aK r5, AnonymousClass0o3 r6) throws IOException, C05910ld {
        Object A0P;
        EnumC05930lf A0G;
        AnonymousClass0nL r1 = this._objectIdReader;
        if (r1 == null || (A0G = r4.A0G()) == null || !A0G.isScalarValue()) {
            int i = C06470mw.A00[r4.A0G().ordinal()];
            if (i == 1) {
                if (this._acceptString) {
                    A0P = r4.A0P();
                }
                return r6.A09(r4, r5);
            } else if (i == 2) {
                if (this._acceptInt) {
                    A0P = Integer.valueOf(r4.A06());
                }
                return r6.A09(r4, r5);
            } else if (i == 3) {
                if (this._acceptDouble) {
                    A0P = Double.valueOf(r4.A03());
                }
                return r6.A09(r4, r5);
            } else if (i != 4) {
                if (i == 5 && this._acceptBoolean) {
                    A0P = Boolean.FALSE;
                }
                return r6.A09(r4, r5);
            } else {
                if (this._acceptBoolean) {
                    A0P = Boolean.TRUE;
                }
                return r6.A09(r4, r5);
            }
            if (A0P != null) {
                return A0P;
            }
            return r6.A09(r4, r5);
        }
        Object A09 = r1.deserializer.A09(r4, r5);
        Object obj = r5.A0I(A09, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Could not resolve Object Id [" + A09 + "] -- unresolved forward-reference?");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0023, code lost:
        if (r3.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r3.isAssignableFrom(java.lang.Integer.class) != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AbstractDeserializer(X.C06490mz r5, X.AbstractC06260mR r6, java.util.Map<java.lang.String, X.AbstractC01680Ku> r7) {
        /*
            r4 = this;
            r4.<init>()
            X.0aI r1 = r6.A00
            r4._baseType = r1
            X.0nL r0 = r5.A03
            r4._objectIdReader = r0
            r4._backRefProperties = r7
            java.lang.Class<?> r3 = r1._class
            java.lang.Class<java.lang.String> r0 = java.lang.String.class
            boolean r0 = r3.isAssignableFrom(r0)
            r4._acceptString = r0
            java.lang.Class r0 = java.lang.Boolean.TYPE
            r2 = 0
            if (r3 == r0) goto L_0x0025
            java.lang.Class<java.lang.Boolean> r0 = java.lang.Boolean.class
            boolean r1 = r3.isAssignableFrom(r0)
            r0 = 0
            if (r1 == 0) goto L_0x0026
        L_0x0025:
            r0 = 1
        L_0x0026:
            r4._acceptBoolean = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            if (r3 == r0) goto L_0x0035
            java.lang.Class<java.lang.Integer> r0 = java.lang.Integer.class
            boolean r1 = r3.isAssignableFrom(r0)
            r0 = 0
            if (r1 == 0) goto L_0x0036
        L_0x0035:
            r0 = 1
        L_0x0036:
            r4._acceptInt = r0
            java.lang.Class r0 = java.lang.Double.TYPE
            if (r3 == r0) goto L_0x0044
            java.lang.Class<java.lang.Double> r0 = java.lang.Double.class
            boolean r0 = r3.isAssignableFrom(r0)
            if (r0 == 0) goto L_0x0045
        L_0x0044:
            r2 = 1
        L_0x0045:
            r4._acceptDouble = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.AbstractDeserializer.<init>(X.0mz, X.0mR, java.util.Map):void");
    }
}
