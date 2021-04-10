package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02310iT;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public class IteratorSerializer extends AsArraySerializerBase<Iterator<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A03(AbstractC04550qd r5) {
        return new IteratorSerializer(this.A01, this.A05, r5, this.A03);
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Iterator<?>> A07(AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer jsonSerializer) {
        return new IteratorSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0iS, X.0i3] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A08(Iterator<?> it, AbstractC02300iS r8, AbstractC02120i3 r9) throws IOException, C02310iT {
        Iterator<?> it2 = it;
        if (it2.hasNext()) {
            AbstractC04550qd r5 = this.A02;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer = null;
            do {
                Object next = it2.next();
                if (next == null) {
                    r9.A0E(r8);
                } else {
                    Class<?> cls2 = next.getClass();
                    if (cls2 != cls) {
                        jsonSerializer = r9.A0B(cls2, this.A03);
                        cls = cls2;
                    }
                    if (r5 == null) {
                        jsonSerializer.serialize(next, r8, r9);
                    } else {
                        jsonSerializer.serializeWithType(next, r8, r9, r5);
                    }
                }
            } while (it2.hasNext());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        Iterator it = (Iterator) obj;
        if (it == null || !it.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A04(Object obj) {
        return false;
    }

    public IteratorSerializer(AbstractC02190iF r8, boolean z, AbstractC04550qd r10, AbstractC02220iI r11) {
        super(Iterator.class, r8, z, r10, r11, null);
    }

    public IteratorSerializer(IteratorSerializer iteratorSerializer, AbstractC02220iI r2, AbstractC04550qd r3, JsonSerializer<?> jsonSerializer) {
        super(iteratorSerializer, r2, r3, jsonSerializer);
    }
}
