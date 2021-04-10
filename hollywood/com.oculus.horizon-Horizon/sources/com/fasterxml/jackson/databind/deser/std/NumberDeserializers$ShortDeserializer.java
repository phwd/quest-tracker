package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0k2;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ShortDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Short> {
    public static final NumberDeserializers$ShortDeserializer A00 = new NumberDeserializers$ShortDeserializer(Short.class, 0);
    public static final NumberDeserializers$ShortDeserializer A01 = new NumberDeserializers$ShortDeserializer(Short.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Short A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        short s;
        Throwable th;
        EnumC04820ji A0a = r3.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            s = r3.A0E();
        } else if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r3.A0e().trim();
            try {
                if (trim.length() == 0) {
                    return (Short) A08();
                }
                int A012 = AnonymousClass0k2.A01(trim);
                if (A012 < -32768 || A012 > 32767) {
                    th = null;
                    try {
                        throw th;
                    } catch (Exception unused) {
                        throw th;
                    }
                } else {
                    s = (short) A012;
                }
            } catch (IllegalArgumentException unused2) {
                th = null;
            }
        } else if (A0a == EnumC04820ji.VALUE_NULL) {
            return (Short) A08();
        } else {
            throw r4.A07(this._valueClass, A0a);
        }
        return Short.valueOf(s);
    }

    public NumberDeserializers$ShortDeserializer(Class<Short> cls, Short sh) {
        super(cls, sh);
    }
}
