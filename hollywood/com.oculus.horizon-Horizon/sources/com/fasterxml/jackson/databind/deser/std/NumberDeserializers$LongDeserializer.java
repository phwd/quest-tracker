package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0k2;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Long A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        long j;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_NUMBER_INT || A0a == EnumC04820ji.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(r4.A0N());
        }
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r4.A0e().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) AnonymousClass0k2.A01(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        throw null;
                    } catch (Exception unused2) {
                        throw null;
                    }
                }
                return Long.valueOf(j);
            }
        } else if (A0a != EnumC04820ji.VALUE_NULL) {
            throw r5.A07(this._valueClass, A0a);
        }
        return (Long) A08();
    }

    public NumberDeserializers$LongDeserializer(Class<Long> cls, Long l) {
        super(cls, l);
    }
}
