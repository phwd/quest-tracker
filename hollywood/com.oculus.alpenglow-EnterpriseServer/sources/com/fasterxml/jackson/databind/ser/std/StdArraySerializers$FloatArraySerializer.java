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
public final class StdArraySerializers$FloatArraySerializer extends StdArraySerializers$TypedPrimitiveArraySerializer<float[]> {
    public static final AnonymousClass0aI A00 = new AnonymousClass0C9(Float.TYPE);

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        float[] fArr = (float[]) obj;
        if (fArr == null || fArr.length == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r3) {
        return new StdArraySerializers$FloatArraySerializer(this, ((ArraySerializerBase) this).A00, r3);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((float[]) obj).length != 1) {
            return false;
        }
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public final void A0G(Object obj, AbstractC02640aV r7, AnonymousClass0a8 r8) throws IOException, C02650aW {
        float[] fArr = (float[]) obj;
        AnonymousClass0o6 r4 = ((StdArraySerializers$TypedPrimitiveArraySerializer) this).A00;
        int i = 0;
        if (r4 != null) {
            int length = fArr.length;
            while (i < length) {
                r4.A07(null, r7, Float.TYPE);
                r7.A0I(fArr[i]);
                r4.A06(null, r7);
                i++;
            }
            return;
        }
        int length2 = fArr.length;
        while (i < length2) {
            r7.A0I(fArr[i]);
            i++;
        }
    }

    public StdArraySerializers$FloatArraySerializer() {
        super(float[].class);
    }

    public StdArraySerializers$FloatArraySerializer(StdArraySerializers$FloatArraySerializer stdArraySerializers$FloatArraySerializer, AbstractC02580aL r2, AnonymousClass0o6 r3) {
        super(stdArraySerializers$FloatArraySerializer, r2, r3);
    }
}
