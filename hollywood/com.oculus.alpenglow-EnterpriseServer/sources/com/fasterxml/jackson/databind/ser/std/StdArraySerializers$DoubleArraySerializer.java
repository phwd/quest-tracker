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
public final class StdArraySerializers$DoubleArraySerializer extends ArraySerializerBase<double[]> {
    public static final AnonymousClass0aI A00 = new AnonymousClass0C9(Double.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r1) {
        return this;
    }

    public StdArraySerializers$DoubleArraySerializer() {
        super(double[].class, (AbstractC02580aL) null);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        double[] dArr = (double[]) obj;
        if (dArr == null || dArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((double[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(double[] dArr, AbstractC02640aV r6, AnonymousClass0a8 r7) throws IOException, C02650aW {
        for (double d : dArr) {
            r6.A0H(d);
        }
    }
}
