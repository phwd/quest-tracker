package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Float A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        float A04;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            A04 = r4.A04();
        } else {
            if (A0G == EnumC05930lf.VALUE_STRING) {
                String trim = r4.A0P().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A04 = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A04 = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A04 = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        throw r5.A0G(trim, this._valueClass, "not a valid Float value");
                    }
                }
            } else if (A0G != EnumC05930lf.VALUE_NULL) {
                throw r5.A0C(this._valueClass, A0G);
            }
            return (Float) A08();
        }
        return Float.valueOf(A04);
    }

    public NumberDeserializers$FloatDeserializer(Class<Float> cls, Float f) {
        super(cls, f);
    }
}
