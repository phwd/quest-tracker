package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C06120lz;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Byte A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        byte b;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            b = r4.A02();
        } else if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r4.A0P().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A08();
                }
                int A002 = C06120lz.A00(trim);
                if (A002 < -128 || A002 > 255) {
                    throw r5.A0G(trim, this._valueClass, "overflow, value can not be represented as 8-bit value");
                }
                b = (byte) A002;
            } catch (IllegalArgumentException unused) {
                throw r5.A0G(trim, this._valueClass, "not a valid Byte value");
            }
        } else if (A0G == EnumC05930lf.VALUE_NULL) {
            return (Byte) A08();
        } else {
            throw r5.A0C(this._valueClass, A0G);
        }
        return Byte.valueOf(b);
    }

    public NumberDeserializers$ByteDeserializer(Class<Byte> cls, Byte b) {
        super(cls, b);
    }
}
