package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AnonymousClass0C9;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$LongArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer<long[]> {
    public static final AnonymousClass0aI A00 = new AnonymousClass0C9(Long.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        long[] jArr = (long[]) obj;
        if (jArr == null || jArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r3) {
        return new StdArraySerializers$LongArraySerializer(this, ((ArraySerializerBase) this).A00, r3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((long[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(Object obj, AbstractC02640aV r8, AnonymousClass0a8 r9) throws IOException, C02650aW {
        long[] jArr = (long[]) obj;
        AnonymousClass0o6 r5 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
        int i = 0;
        if (r5 != null) {
            int length = jArr.length;
            while (i < length) {
                r5.A07(null, r8, Long.TYPE);
                r8.A0K(jArr[i]);
                r5.A06(null, r8);
                i++;
            }
            return;
        }
        int length2 = jArr.length;
        while (i < length2) {
            r8.A0K(jArr[i]);
            i++;
        }
    }

    public StdArraySerializers$LongArraySerializer() {
        super(long[].class);
    }

    public StdArraySerializers$LongArraySerializer(StdArraySerializers$LongArraySerializer stdArraySerializers$LongArraySerializer, AbstractC02580aL r2, AnonymousClass0o6 r3) {
        super(stdArraySerializers$LongArraySerializer, r2, r3);
    }
}
