package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Float A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        float A0S;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            A0S = r4.A0S();
        } else {
            if (A0i == EnumC03640oE.VALUE_STRING) {
                String trim = r4.A0m().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0S = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0S = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0S = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        throw r5.A0G(trim, this._valueClass, "not a valid Float value");
                    }
                }
            } else if (A0i != EnumC03640oE.VALUE_NULL) {
                throw r5.A0C(this._valueClass, A0i);
            }
            return (Float) A08();
        }
        return Float.valueOf(A0S);
    }

    public NumberDeserializers$FloatDeserializer(Class<Float> cls, Float f) {
        super(cls, f);
    }
}
