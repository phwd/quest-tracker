package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.C03780oX;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Byte A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        byte b;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            b = r4.A0E();
        } else if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A08();
                }
                int A012 = C03780oX.A01(trim);
                if (A012 < -128 || A012 > 255) {
                    throw r5.A0G(trim, this._valueClass, "overflow, value can not be represented as 8-bit value");
                }
                b = (byte) A012;
            } catch (IllegalArgumentException unused) {
                throw r5.A0G(trim, this._valueClass, "not a valid Byte value");
            }
        } else if (A0i == EnumC03640oE.VALUE_NULL) {
            return (Byte) A08();
        } else {
            throw r5.A0C(this._valueClass, A0i);
        }
        return Byte.valueOf(b);
    }

    public NumberDeserializers$ByteDeserializer(Class<Byte> cls, Byte b) {
        super(cls, b);
    }
}
