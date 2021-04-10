package com.fasterxml.jackson.databind.deser;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.C0273Ou;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;
import java.util.Map;

public class AbstractDeserializer extends JsonDeserializer implements Serializable {
    public static final long serialVersionUID = -3010349050434697698L;
    public final boolean _acceptBoolean;
    public final boolean _acceptDouble;
    public final boolean _acceptInt;
    public final boolean _acceptString;
    public final Map _backRefProperties;
    public final AbstractC1024qt _baseType;
    public final C0273Ou _objectIdReader;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        throw qrVar.A0B(this._baseType._class, "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0023, code lost:
        if (r3.isAssignableFrom(java.lang.Boolean.class) != false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r3.isAssignableFrom(java.lang.Integer.class) != false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AbstractDeserializer(X.C0262Oa r5, X.O4 r6, java.util.Map r7) {
        /*
            r4 = this;
            r4.<init>()
            X.qt r1 = r6.A00
            r4._baseType = r1
            X.Ou r0 = r5.A03
            r4._objectIdReader = r0
            r4._backRefProperties = r7
            java.lang.Class r3 = r1._class
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.AbstractDeserializer.<init>(X.Oa, X.O4, java.util.Map):void");
    }
}
