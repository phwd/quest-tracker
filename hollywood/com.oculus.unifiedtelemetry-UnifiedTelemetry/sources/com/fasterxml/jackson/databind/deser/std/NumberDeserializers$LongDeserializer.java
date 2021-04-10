package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.p2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Long A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        long j;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(ww.A0M());
        }
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) p2.A01(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        throw wn.A0D(trim, this._valueClass, "not a valid Long value");
                    }
                }
                return Long.valueOf(j);
            }
        } else if (A0Z != EnumC0470q2.VALUE_NULL) {
            throw wn.A09(this._valueClass, A0Z);
        }
        return (Long) A08();
    }

    public NumberDeserializers$LongDeserializer(Class<Long> cls, Long l) {
        super(cls, l);
    }
}
