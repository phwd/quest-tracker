package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Float> {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Float A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        float A0L;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            A0L = r4.A0L();
        } else {
            if (A0a == EnumC04820ji.VALUE_STRING) {
                String trim = r4.A0e().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0L = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0L = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0L = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        throw null;
                    } catch (Exception unused2) {
                        throw null;
                    }
                }
            } else if (A0a != EnumC04820ji.VALUE_NULL) {
                throw r5.A07(this._valueClass, A0a);
            }
            return (Float) A08();
        }
        return Float.valueOf(A0L);
    }

    public NumberDeserializers$FloatDeserializer(Class<Float> cls, Float f) {
        super(cls, f);
    }
}
