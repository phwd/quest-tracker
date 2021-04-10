package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r5) {
        return new IterableSerializer(this.A01, this.A05, r5, this.A03);
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Iterable<?>> A07(AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer jsonSerializer) {
        return new IterableSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A08(Iterable<?> iterable, AbstractC02300iS r9, AbstractC02120i3 r10) throws IOException, C02310iT {
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            AbstractC04550qd r5 = this.A02;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer = null;
            do {
                Object next = it.next();
                if (next == null) {
                    r10.A0E(r9);
                } else {
                    Class<?> cls2 = next.getClass();
                    if (cls2 != cls) {
                        jsonSerializer = r10.A0B(cls2, this.A03);
                        cls = cls2;
                    }
                    if (r5 == null) {
                        jsonSerializer.serialize(next, r9, r10);
                    } else {
                        jsonSerializer.serializeWithType(next, r9, r10, r5);
                    }
                }
            } while (it.hasNext());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        Iterable iterable = (Iterable) obj;
        if (iterable == null || !iterable.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        return false;
    }

    public IterableSerializer(AbstractC02190iF r8, boolean z, AbstractC04550qd r10, AbstractC02220iI r11) {
        super(Iterable.class, r8, z, r10, r11, null);
    }

    public IterableSerializer(IterableSerializer iterableSerializer, AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer<?> jsonSerializer) {
        super(iterableSerializer, r2, r3, jsonSerializer);
    }
}
