package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$FloatDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$FloatDeserializer A00 = new NumberDeserializers$FloatDeserializer(Float.class, Float.valueOf(0.0f));
    public static final NumberDeserializers$FloatDeserializer A01 = new NumberDeserializers$FloatDeserializer(Float.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Float A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        float A0I;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            A0I = qiVar.A0I();
        } else {
            if (A0U == NX.VALUE_STRING) {
                String trim = qiVar.A0p().trim();
                if (trim.length() != 0) {
                    char charAt = trim.charAt(0);
                    if (charAt != '-') {
                        if (charAt != 'I') {
                            if (charAt == 'N' && "NaN".equals(trim)) {
                                A0I = Float.NaN;
                            }
                        } else if ("Infinity".equals(trim) || "INF".equals(trim)) {
                            A0I = Float.POSITIVE_INFINITY;
                        }
                    } else if ("-Infinity".equals(trim) || "-INF".equals(trim)) {
                        A0I = Float.NEGATIVE_INFINITY;
                    }
                    try {
                        return Float.valueOf(Float.parseFloat(trim));
                    } catch (IllegalArgumentException unused) {
                        qrVar.A0L(this._valueClass);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
            } else if (A0U != NX.VALUE_NULL) {
                throw qrVar.A0A(this._valueClass, A0U);
            }
            return (Float) A08();
        }
        return Float.valueOf(A0I);
    }

    public NumberDeserializers$FloatDeserializer(Class cls, Float f) {
        super(cls, f);
    }
}
