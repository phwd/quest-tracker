package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass06;
import X.AnonymousClass6z;
import X.AnonymousClass7s;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.AnonymousClass9u;
import X.B3;
import X.C0124Ro;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class NumberDeserializers$ByteDeserializer extends NumberDeserializers$PrimitiveOrWrapperDeserializer<Byte> {
    public static final NumberDeserializers$ByteDeserializer A00 = new NumberDeserializers$ByteDeserializer(Byte.TYPE, (byte) 0);
    public static final NumberDeserializers$ByteDeserializer A01 = new NumberDeserializers$ByteDeserializer(Byte.class, null);
    public static final long serialVersionUID = 1;

    private final Byte A00(Rn rn) throws IOException, AnonymousClass9r {
        int i;
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_NUMBER_INT || r1 == AnonymousClass9p.VALUE_NUMBER_FLOAT) {
            i = rn.A00();
            if (i < -128 || i > 255) {
                String A04 = AnonymousClass06.A04("Numeric value (", rn.A09(), ") out of range of Java byte");
                AnonymousClass9u r12 = ((AnonymousClass6z) rn).A01;
                if (r12 == null) {
                    r12 = AnonymousClass9u.A01;
                }
                throw new C0124Ro(A04, r12);
            }
        } else if (r1 == AnonymousClass9p.VALUE_STRING) {
            String trim = rn.A09().trim();
            try {
                if (trim.length() == 0) {
                    return (Byte) A02();
                }
                i = AnonymousClass7s.A00(trim);
                if (i < -128) {
                    throw null;
                } else if (i > 255) {
                    throw null;
                }
            } catch (IllegalArgumentException unused) {
                throw null;
            }
        } else if (r1 == AnonymousClass9p.VALUE_NULL) {
            return (Byte) A02();
        } else {
            throw null;
        }
        return Byte.valueOf((byte) i);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        return A00(rn);
    }

    public NumberDeserializers$ByteDeserializer(Class<Byte> cls, Byte b) {
        super(cls, b);
    }
}
