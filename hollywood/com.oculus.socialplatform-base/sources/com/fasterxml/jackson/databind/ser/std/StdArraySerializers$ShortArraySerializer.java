package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AnonymousClass0C7;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;

@JacksonStdImpl
public final class StdArraySerializers$ShortArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer<short[]> {
    public static final AbstractC02190iF A00 = new AnonymousClass0C7(Short.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r3) {
        return new StdArraySerializers$ShortArraySerializer(this, ((ArraySerializerBase) this).A00, r3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((short[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A05(Object obj, AbstractC02300iS r7, AbstractC02120i3 r8) throws IOException, C02310iT {
        short[] sArr = (short[]) obj;
        AbstractC04550qd r4 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
        int i = 0;
        if (r4 != null) {
            int length = sArr.length;
            while (i < length) {
                r4.A07(null, r7, Short.TYPE);
                r7.A0X(sArr[i]);
                r4.A06(null, r7);
                i++;
            }
            return;
        }
        int length2 = sArr.length;
        while (i < length2) {
            r7.A0M(sArr[i]);
            i++;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectArrayFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        short[] sArr = (short[]) obj;
        if (sArr == null || sArr.length == 0) {
            return true;
        }
        return false;
    }

    public StdArraySerializers$ShortArraySerializer() {
        super(short[].class);
    }

    public StdArraySerializers$ShortArraySerializer(StdArraySerializers$ShortArraySerializer stdArraySerializers$ShortArraySerializer, AbstractC02220iI r2, AbstractC04550qd r3) {
        super(stdArraySerializers$ShortArraySerializer, r2, r3);
    }
}
