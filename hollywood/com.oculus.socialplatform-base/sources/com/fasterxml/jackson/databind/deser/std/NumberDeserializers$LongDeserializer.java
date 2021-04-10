package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.C03780oX;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Long A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        long j;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(r4.A0U());
        }
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) C03780oX.A01(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        throw r5.A0G(trim, this._valueClass, "not a valid Long value");
                    }
                }
                return Long.valueOf(j);
            }
        } else if (A0i != EnumC03640oE.VALUE_NULL) {
            throw r5.A0C(this._valueClass, A0i);
        }
        return (Long) A08();
    }

    public NumberDeserializers$LongDeserializer(Class<Long> cls, Long l) {
        super(cls, l);
    }
}
