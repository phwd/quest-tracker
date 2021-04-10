package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.NX;
import X.Nj;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Long A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        long j;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_NUMBER_INT || A0U == NX.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(qiVar.A0O());
        }
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) Nj.A01(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        qrVar.A0L(this._valueClass);
                        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                    }
                }
                return Long.valueOf(j);
            }
        } else if (A0U != NX.VALUE_NULL) {
            throw qrVar.A0A(this._valueClass, A0U);
        }
        return (Long) A08();
    }

    public NumberDeserializers$LongDeserializer(Class cls, Long l) {
        super(cls, l);
    }
}
