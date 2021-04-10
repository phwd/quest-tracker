package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C06120lz;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Short> {
    public static final NumberDeserializers$ShortDeserializer A00 = new NumberDeserializers$ShortDeserializer(Short.class, 0);
    public static final NumberDeserializers$ShortDeserializer A01 = new NumberDeserializers$ShortDeserializer(Short.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Short A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        short s;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            s = r4.A0T();
        } else if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r4.A0P().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) A08();
                }
                int A002 = C06120lz.A00(trim);
                if (A002 < -32768 || A002 > 32767) {
                    throw r5.A0G(trim, this._valueClass, "overflow, value can not be represented as 16-bit value");
                }
                s = (short) A002;
            } catch (IllegalArgumentException unused) {
                throw r5.A0G(trim, this._valueClass, "not a valid Short value");
            }
        } else if (A0G == EnumC05930lf.VALUE_NULL) {
            return (Short) A08();
        } else {
            throw r5.A0C(this._valueClass, A0G);
        }
        return Short.valueOf(s);
    }

    public NumberDeserializers$ShortDeserializer(Class<Short> cls, Short sh) {
        super(cls, sh);
    }
}
