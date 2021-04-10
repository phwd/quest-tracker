package com.fasterxml.jackson.databind.deser;

import X.AbstractC01170Rz;
import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.C03620oC;
import X.C04190pV;
import X.C04390pu;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer extends JsonDeserializer<Object> implements Serializable {
    public static final long serialVersionUID = -3010349050434697698L;
    public final boolean _acceptBoolean;
    public final boolean _acceptDouble;
    public final boolean _acceptInt;
    public final boolean _acceptString;
    public final Map<String, AbstractC01170Rz> _backRefProperties;
    public final AbstractC02190iF _baseType;
    public final C04390pu _objectIdReader;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        throw r4.A0D(this._baseType._class, "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r4, AbstractC02210iH r5, AbstractC04520qa r6) throws IOException, C03620oC {
        Object A0m;
        EnumC03640oE A0i;
        C04390pu r1 = this._objectIdReader;
        if (r1 == null || (A0i = r4.A0i()) == null || !A0i.isScalarValue()) {
            int i = C04190pV.A00[r4.A0i().ordinal()];
            if (i == 1) {
                if (this._acceptString) {
                    A0m = r4.A0m();
                }
                return r6.A09(r4, r5);
            } else if (i == 2) {
                if (this._acceptInt) {
                    A0m = Integer.valueOf(r4.A0T());
                }
                return r6.A09(r4, r5);
            } else if (i == 3) {
                if (this._acceptDouble) {
                    A0m = Double.valueOf(r4.A0R());
                }
                return r6.A09(r4, r5);
            } else if (i != 4) {
                if (i == 5 && this._acceptBoolean) {
                    A0m = Boolean.FALSE;
                }
                return r6.A09(r4, r5);
            } else {
                if (this._acceptBoolean) {
                    A0m = Boolean.TRUE;
                }
                return r6.A09(r4, r5);
            }
            if (A0m != null) {
                return A0m;
            }
            return r6.A09(r4, r5);
        }
        Object A0A = r1.deserializer.A0A(r4, r5);
        Object obj = r5.A0J(A0A, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
        sb.append(A0A);
        sb.append("] -- unresolved forward-reference?");
        throw new IllegalStateException(sb.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0023, code lost:
        if (r3.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r3.isAssignableFrom(java.lang.Integer.class) != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AbstractDeserializer(X.C04210pY r5, X.AbstractC04010oz r6, java.util.Map<java.lang.String, X.AbstractC01170Rz> r7) {
        /*
            r4 = this;
            r4.<init>()
            X.0iF r1 = r6.A00
            r4._baseType = r1
            X.0pu r0 = r5.A03
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.AbstractDeserializer.<init>(X.0pY, X.0oz, java.util.Map):void");
    }
}
