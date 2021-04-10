package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C06120lz;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Long A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        long j;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_NUMBER_INT || A0G == EnumC05930lf.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(r4.A0B());
        }
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r4.A0P().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) C06120lz.A00(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        throw r5.A0G(trim, this._valueClass, "not a valid Long value");
                    }
                }
                return Long.valueOf(j);
            }
        } else if (A0G != EnumC05930lf.VALUE_NULL) {
            throw r5.A0C(this._valueClass, A0G);
        }
        return (Long) A08();
    }

    public NumberDeserializers$LongDeserializer(Class<Long> cls, Long l) {
        super(cls, l);
    }
}
