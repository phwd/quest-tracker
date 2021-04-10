package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.p2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Short> {
    public static final NumberDeserializers$ShortDeserializer A00 = new NumberDeserializers$ShortDeserializer(Short.class, 0);
    public static final NumberDeserializers$ShortDeserializer A01 = new NumberDeserializers$ShortDeserializer(Short.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Short A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        short s;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            s = ww.A0E();
        } else if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) A08();
                }
                int A012 = p2.A01(trim);
                if (A012 < -32768 || A012 > 32767) {
                    throw wn.A0D(trim, this._valueClass, "overflow, value can not be represented as 16-bit value");
                }
                s = (short) A012;
            } catch (IllegalArgumentException unused) {
                throw wn.A0D(trim, this._valueClass, "not a valid Short value");
            }
        } else if (A0Z == EnumC0470q2.VALUE_NULL) {
            return (Short) A08();
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
        return Short.valueOf(s);
    }

    public NumberDeserializers$ShortDeserializer(Class<Short> cls, Short sh) {
        super(cls, sh);
    }
}
