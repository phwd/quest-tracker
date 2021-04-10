package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase;
import java.io.IOException;
import java.util.Iterator;

@JacksonStdImpl
public final class IteratorSerializer extends AsArraySerializerBase<Iterator<?>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        Iterator it = (Iterator) obj;
        if (it == null || !it.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer<?> A0E(AnonymousClass0o6 r5) {
        return new IteratorSerializer(this.A01, this.A05, r5, this.A03);
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<Iterator<?>> A0I(AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer jsonSerializer) {
        return new IteratorSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A0J(Iterator<?> it, AbstractC02640aV r8, AnonymousClass0a8 r9) throws IOException, C02650aW {
        Iterator<?> it2 = it;
        if (it2.hasNext()) {
            AnonymousClass0o6 r5 = this.A02;
            Class<?> cls = null;
            JsonSerializer<Object> jsonSerializer = null;
            do {
                Object next = it2.next();
                if (next == null) {
                    r9.A0D(r8);
                } else {
                    Class<?> cls2 = next.getClass();
                    if (cls2 != cls) {
                        jsonSerializer = r9.A0B(cls2, this.A03);
                        cls = cls2;
                    }
                    if (r5 == null) {
                        jsonSerializer.A0D(next, r8, r9);
                    } else {
                        jsonSerializer.A0A(next, r8, r9, r5);
                    }
                }
            } while (it2.hasNext());
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        return false;
    }

    public IteratorSerializer(AnonymousClass0aI r8, boolean z, AnonymousClass0o6 r10, AbstractC02580aL r11) {
        super(Iterator.class, r8, z, r10, r11, null);
    }

    public IteratorSerializer(IteratorSerializer iteratorSerializer, AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer<?> jsonSerializer) {
        super(iteratorSerializer, r2, r3, jsonSerializer);
    }
}
