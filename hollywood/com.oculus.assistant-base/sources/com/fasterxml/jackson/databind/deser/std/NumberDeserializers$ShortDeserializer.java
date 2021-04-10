package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import X.Nj;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$ShortDeserializer A00 = new NumberDeserializers$ShortDeserializer(Short.class, 0);
    public static final NumberDeserializers$ShortDeserializer A01 = new NumberDeserializers$ShortDeserializer(Short.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Short A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        short s;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            s = qiVar.A0e();
        } else if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) A08();
                }
                int A012 = Nj.A01(trim);
                if (A012 < -32768 || A012 > 32767) {
                    qrVar.A0L(this._valueClass);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
                s = (short) A012;
            } catch (IllegalArgumentException unused) {
                qrVar.A0L(this._valueClass);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        } else if (A0U == NX.VALUE_NULL) {
            return (Short) A08();
        } else {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return Short.valueOf(s);
    }

    public NumberDeserializers$ShortDeserializer(Class cls, Short sh) {
        super(cls, sh);
    }
}
