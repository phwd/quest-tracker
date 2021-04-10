package com.fasterxml.jackson.databind.deser;

import X.AbstractC0073Cr;
import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.C0204Vm;
import X.C0328cv;
import X.EnumC0470q2;
import X.V4;
import X.q0;
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
    public final Map<String, AbstractC0073Cr> _backRefProperties;
    public final AbstractC0224Wl _baseType;
    public final C0204Vm _objectIdReader;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        throw wn.A0A(this._baseType._class, "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        Object A0d;
        EnumC0470q2 A0Z;
        C0204Vm vm = this._objectIdReader;
        if (vm == null || (A0Z = ww.A0Z()) == null || !A0Z.isScalarValue()) {
            int i = C0328cv.A00[ww.A0Z().ordinal()];
            if (i == 1) {
                if (this._acceptString) {
                    A0d = ww.A0d();
                }
                return v4.A09(ww, wn);
            } else if (i == 2) {
                if (this._acceptInt) {
                    A0d = Integer.valueOf(ww.A0L());
                }
                return v4.A09(ww, wn);
            } else if (i == 3) {
                if (this._acceptDouble) {
                    A0d = Double.valueOf(ww.A0J());
                }
                return v4.A09(ww, wn);
            } else if (i != 4) {
                if (i == 5 && this._acceptBoolean) {
                    A0d = Boolean.FALSE;
                }
                return v4.A09(ww, wn);
            } else {
                if (this._acceptBoolean) {
                    A0d = Boolean.TRUE;
                }
                return v4.A09(ww, wn);
            }
            if (A0d != null) {
                return A0d;
            }
            return v4.A09(ww, wn);
        }
        Object A09 = vm.deserializer.A09(ww, wn);
        Object obj = wn.A0F(A09, this._objectIdReader.generator).A00;
        if (obj != null) {
            return obj;
        }
        StringBuilder sb = new StringBuilder("Could not resolve Object Id [");
        sb.append(A09);
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
    public AbstractDeserializer(X.C0295aT r5, X.jm r6, java.util.Map<java.lang.String, X.AbstractC0073Cr> r7) {
        /*
            r4 = this;
            r4.<init>()
            X.Wl r1 = r6.A00
            r4._baseType = r1
            X.Vm r0 = r5.A03
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.AbstractDeserializer.<init>(X.aT, X.jm, java.util.Map):void");
    }
}
