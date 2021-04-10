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
public final class StdArraySerializers$LongArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer<long[]> {
    public static final AbstractC02190iF A00 = new AnonymousClass0C7(Long.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r3) {
        return new StdArraySerializers$LongArraySerializer(this, ((ArraySerializerBase) this).A00, r3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((long[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A05(Object obj, AbstractC02300iS r8, AbstractC02120i3 r9) throws IOException, C02310iT {
        long[] jArr = (long[]) obj;
        AbstractC04550qd r5 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
        int i = 0;
        if (r5 != null) {
            int length = jArr.length;
            while (i < length) {
                r5.A07(null, r8, Long.TYPE);
                r8.A0N(jArr[i]);
                r5.A06(null, r8);
                i++;
            }
            return;
        }
        int length2 = jArr.length;
        while (i < length2) {
            r8.A0N(jArr[i]);
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
        long[] jArr = (long[]) obj;
        if (jArr == null || jArr.length == 0) {
            return true;
        }
        return false;
    }

    public StdArraySerializers$LongArraySerializer() {
        super(long[].class);
    }

    public StdArraySerializers$LongArraySerializer(StdArraySerializers$LongArraySerializer stdArraySerializers$LongArraySerializer, AbstractC02220iI r2, AbstractC04550qd r3) {
        super(stdArraySerializers$LongArraySerializer, r2, r3);
    }
}
