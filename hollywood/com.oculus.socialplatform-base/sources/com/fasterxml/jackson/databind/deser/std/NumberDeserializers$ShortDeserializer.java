package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.C03780oX;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Short> {
    public static final NumberDeserializers$ShortDeserializer A00 = new NumberDeserializers$ShortDeserializer(Short.class, 0);
    public static final NumberDeserializers$ShortDeserializer A01 = new NumberDeserializers$ShortDeserializer(Short.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Short A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        short s;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_NUMBER_INT || A0i == EnumC03640oE.VALUE_NUMBER_FLOAT) {
            s = r4.A0I();
        } else if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) A08();
                }
                int A012 = C03780oX.A01(trim);
                if (A012 < -32768 || A012 > 32767) {
                    throw r5.A0G(trim, this._valueClass, "overflow, value can not be represented as 16-bit value");
                }
                s = (short) A012;
            } catch (IllegalArgumentException unused) {
                throw r5.A0G(trim, this._valueClass, "not a valid Short value");
            }
        } else if (A0i == EnumC03640oE.VALUE_NULL) {
            return (Short) A08();
        } else {
            throw r5.A0C(this._valueClass, A0i);
        }
        return Short.valueOf(s);
    }

    public NumberDeserializers$ShortDeserializer(Class<Short> cls, Short sh) {
        super(cls, sh);
    }
}
