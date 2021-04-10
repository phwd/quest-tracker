package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass7s;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$LongDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Long> {
    public static final NumberDeserializers$LongDeserializer A00 = new NumberDeserializers$LongDeserializer(Long.class, 0L);
    public static final NumberDeserializers$LongDeserializer A01 = new NumberDeserializers$LongDeserializer(Long.TYPE, null);
    public static final long serialVersionUID = 1;

    private final Long A00(Rn rn) throws IOException, AnonymousClass9r {
        long j;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            return Long.valueOf(rn.A06().longValue());
        }
        if (r1 == AnonymousClass9p.VALUE_STRING) {
            String trim = rn.A09().trim();
            int length = trim.length();
            if (length != 0) {
                if (length <= 9) {
                    j = (long) AnonymousClass7s.A00(trim);
                } else {
                    try {
                        j = Long.parseLong(trim);
                    } catch (IllegalArgumentException unused) {
                        throw null;
                    }
                }
                return Long.valueOf(j);
            }
        } else if (r1 != AnonymousClass9p.VALUE_NULL) {
            throw null;
        }
        return (Long) A02();
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }

    public NumberDeserializers$LongDeserializer(Class<Long> cls, Long l) {
        super(cls, l);
    }
}
