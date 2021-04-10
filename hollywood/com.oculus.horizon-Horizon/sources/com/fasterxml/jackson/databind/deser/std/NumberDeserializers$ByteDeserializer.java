package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0k2;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Byte A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        byte b;
        Throwable th;
        EnumC04820ji A0a = r3.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            b = r3.A0B();
        } else if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r3.A0e().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A08();
                }
                int A012 = AnonymousClass0k2.A01(trim);
                if (A012 < -128 || A012 > 255) {
                    th = null;
                    try {
                        throw th;
                    } catch (Exception unused) {
                        throw th;
                    }
                } else {
                    b = (byte) A012;
                }
            } catch (IllegalArgumentException unused2) {
                th = null;
            }
        } else if (A0a == EnumC04820ji.VALUE_NULL) {
            return (Byte) A08();
        } else {
            throw r4.A07(this._valueClass, A0a);
        }
        return Byte.valueOf(b);
    }

    public NumberDeserializers$ByteDeserializer(Class<Byte> cls, Byte b) {
        super(cls, b);
    }
}
