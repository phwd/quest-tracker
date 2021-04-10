package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    private final Float A00(Rn rn) throws IOException, AnonymousClass9r {
        float floatValue;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            floatValue = rn.A06().floatValue();
        } else {
            if (r1 == AnonymousClass9p.VALUE_STRING) {
                String trim = rn.A09().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                floatValue = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            floatValue = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        floatValue = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        throw null;
                    }
                }
            } else if (r1 != AnonymousClass9p.VALUE_NULL) {
                throw null;
            }
            return (Float) A02();
        }
        return Float.valueOf(floatValue);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }

    public NumberDeserializers$FloatDeserializer(Class<Float> cls, Float f) {
        super(cls, f);
    }
}
