package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.p2;
import X.q0;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Byte A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        byte b;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_NUMBER_INT || A0Z == EnumC0470q2.VALUE_NUMBER_FLOAT) {
            b = ww.A0B();
        } else if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A08();
                }
                int A012 = p2.A01(trim);
                if (A012 < -128 || A012 > 255) {
                    throw wn.A0D(trim, this._valueClass, "overflow, value can not be represented as 8-bit value");
                }
                b = (byte) A012;
            } catch (IllegalArgumentException unused) {
                throw wn.A0D(trim, this._valueClass, "not a valid Byte value");
            }
        } else if (A0Z == EnumC0470q2.VALUE_NULL) {
            return (Byte) A08();
        } else {
            throw wn.A09(this._valueClass, A0Z);
        }
        return Byte.valueOf(b);
    }

    public NumberDeserializers$ByteDeserializer(Class<Byte> cls, Byte b) {
        super(cls, b);
    }
}
