package com.fasterxml.jackson.databind.deser;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0HD;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C05400l0;
import X.C05530lQ;
import X.EnumC04820ji;
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
    public final Map<String, AnonymousClass0HD> _backRefProperties;
    public final AbstractC04000gb _baseType;
    public final C05530lQ _objectIdReader;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final boolean A0E() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        throw r4.A08(this._baseType._class, "abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r4, AbstractC04020gg r5, AnonymousClass0m9 r6) throws IOException, AnonymousClass0jg {
        Object A0e;
        EnumC04820ji A0a;
        C05530lQ r1 = this._objectIdReader;
        if (r1 == null || (A0a = r4.A0a()) == null || !A0a.isScalarValue()) {
            int i = C05400l0.A00[r4.A0a().ordinal()];
            if (i == 1) {
                if (this._acceptString) {
                    A0e = r4.A0e();
                }
                return r6.A09(r4, r5);
            } else if (i == 2) {
                if (this._acceptInt) {
                    A0e = Integer.valueOf(r4.A0M());
                }
                return r6.A09(r4, r5);
            } else if (i == 3) {
                if (this._acceptDouble) {
                    A0e = Double.valueOf(r4.A0K());
                }
                return r6.A09(r4, r5);
            } else if (i != 4) {
                if (i == 5 && this._acceptBoolean) {
                    A0e = Boolean.FALSE;
                }
                return r6.A09(r4, r5);
            } else {
                if (this._acceptBoolean) {
                    A0e = Boolean.TRUE;
                }
                return r6.A09(r4, r5);
            }
            if (A0e != null) {
                return A0e;
            }
            return r6.A09(r4, r5);
        }
        Object A09 = r1.deserializer.A09(r4, r5);
        Object obj = r5.A0B(A09, this._objectIdReader.generator).A00;
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
    public AbstractDeserializer(X.AnonymousClass0l3 r5, X.AbstractC05180kU r6, java.util.Map<java.lang.String, X.AnonymousClass0HD> r7) {
        /*
            r4 = this;
            r4.<init>()
            X.0gb r1 = r6.A00
            r4._baseType = r1
            X.0lQ r0 = r5.A03
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
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.AbstractDeserializer.<init>(X.0l3, X.0kU, java.util.Map):void");
    }
}
