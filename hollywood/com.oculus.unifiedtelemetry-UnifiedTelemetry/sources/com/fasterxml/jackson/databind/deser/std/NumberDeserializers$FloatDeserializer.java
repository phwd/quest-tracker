package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Float A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        float A0K;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            A0K = ww.A0K();
        } else {
            if (A0Z == EnumC0470q2.VALUE_STRING) {
                String trim = ww.A0d().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0K = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0K = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0K = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        throw wn.A0D(trim, this._valueClass, "not a valid Float value");
                    }
                }
            } else if (A0Z != EnumC0470q2.VALUE_NULL) {
                throw wn.A09(this._valueClass, A0Z);
            }
            return (Float) A08();
        }
        return Float.valueOf(A0K);
    }

    public NumberDeserializers$FloatDeserializer(Class<Float> cls, Float f) {
        super(cls, f);
    }
}
