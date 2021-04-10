package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import X.Nj;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Byte A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        byte b;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            b = qiVar.A0E();
        } else if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A08();
                }
                int A012 = Nj.A01(trim);
                if (A012 < -128 || A012 > 255) {
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                b = (byte) A012;
            } catch (IllegalArgumentException unused) {
                qrVar.A0L(this._valueClass);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (A0U == NX.VALUE_NULL) {
            return (Byte) A08();
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return Byte.valueOf(b);
    }

    public NumberDeserializers$ByteDeserializer(Class cls, Byte b) {
        super(cls, b);
    }
}
