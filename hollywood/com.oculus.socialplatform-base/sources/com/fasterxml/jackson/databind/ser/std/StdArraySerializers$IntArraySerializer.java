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
public final class StdArraySerializers$IntArraySerializer extends ArraySerializerBase<int[]> {
    public static final AbstractC02190iF A00 = new AnonymousClass0C7(Integer.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r1) {
        return this;
    }

    public StdArraySerializers$IntArraySerializer() {
        super(int[].class, (AbstractC02220iI) null);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        if (((int[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A05(int[] iArr, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        for (int i : iArr) {
            r5.A0M(i);
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
        int[] iArr = (int[]) obj;
        if (iArr == null || iArr.length == 0) {
            return true;
        }
        return false;
    }
}
