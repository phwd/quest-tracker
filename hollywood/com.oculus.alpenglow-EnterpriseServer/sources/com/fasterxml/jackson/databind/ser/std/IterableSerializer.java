package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public final class IterableSerializer extends AsArraySerializerBase<Iterable<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        Iterable iterable = (Iterable) obj;
        if (iterable == null || !iterable.iterator().hasNext()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r5) {
        return new IterableSerializer(this.A01, this.A05, r5, this.A03);
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Iterable<?>> A0I(AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer jsonSerializer) {
        return new IterableSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A0J(Iterable<?> iterable, AbstractC02640aV r9, AnonymousClass0a8 r10) throws IOException, C02650aW {
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            AnonymousClass0o6 r5 = this.A02;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer = null;
            do {
                Object next = it.next();
                if (next == null) {
                    r10.A0D(r9);
                } else {
                    Class<?> cls2 = next.getClass();
                    if (cls2 != cls) {
                        jsonSerializer = r10.A0B(cls2, this.A03);
                        cls = cls2;
                    }
                    if (r5 == null) {
                        jsonSerializer.A0D(next, r9, r10);
                    } else {
                        jsonSerializer.A0A(next, r9, r10, r5);
                    }
                }
            } while (it.hasNext());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        return false;
    }

    public IterableSerializer(AnonymousClass0aI r8, boolean z, AnonymousClass0o6 r10, AbstractC02580aL r11) {
        super(Iterable.class, r8, z, r10, r11, null);
    }

    public IterableSerializer(IterableSerializer iterableSerializer, AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer<?> jsonSerializer) {
        super(iterableSerializer, r2, r3, jsonSerializer);
    }
}
