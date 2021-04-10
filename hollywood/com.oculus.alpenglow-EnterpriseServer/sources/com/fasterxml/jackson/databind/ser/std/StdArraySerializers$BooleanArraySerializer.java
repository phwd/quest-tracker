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
public final class StdArraySerializers$BooleanArraySerializer extends ArraySerializerBase<boolean[]> {
    public static final AnonymousClass0aI A00 = new AnonymousClass0C9(Boolean.class);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r1) {
        return this;
    }

    public StdArraySerializers$BooleanArraySerializer() {
        super(boolean[].class, (AbstractC02580aL) null);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        boolean[] zArr = (boolean[]) obj;
        if (zArr == null || zArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((boolean[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(boolean[] zArr, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        for (boolean z : zArr) {
            r5.A0W(z);
        }
    }
}
