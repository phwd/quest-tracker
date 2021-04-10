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
public final class StdArraySerializers$ShortArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer<short[]> {
    public static final AnonymousClass0aI A00 = new AnonymousClass0C9(Short.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        short[] sArr = (short[]) obj;
        if (sArr == null || sArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r3) {
        return new StdArraySerializers$ShortArraySerializer(this, ((ArraySerializerBase) this).A00, r3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((short[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(Object obj, AbstractC02640aV r7, AnonymousClass0a8 r8) throws IOException, C02650aW {
        short[] sArr = (short[]) obj;
        AnonymousClass0o6 r4 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
        int i = 0;
        if (r4 != null) {
            int length = sArr.length;
            while (i < length) {
                r4.A07(null, r7, Short.TYPE);
                r7.A0V(sArr[i]);
                r4.A06(null, r7);
                i++;
            }
            return;
        }
        int length2 = sArr.length;
        while (i < length2) {
            r7.A0J(sArr[i]);
            i++;
        }
    }

    public StdArraySerializers$ShortArraySerializer() {
        super(short[].class);
    }

    public StdArraySerializers$ShortArraySerializer(StdArraySerializers$ShortArraySerializer stdArraySerializers$ShortArraySerializer, AbstractC02580aL r2, AnonymousClass0o6 r3) {
        super(stdArraySerializers$ShortArraySerializer, r2, r3);
    }
}
