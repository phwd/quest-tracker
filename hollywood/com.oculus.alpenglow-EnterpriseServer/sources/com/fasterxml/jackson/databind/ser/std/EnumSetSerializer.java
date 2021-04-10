package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Iterator;

public final class EnumSetSerializer extends AsArraySerializerBase<EnumSet<? extends Enum<?>>> {
    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        EnumSet enumSet = (EnumSet) obj;
        if (enumSet == null || enumSet.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final boolean A0F(Object obj) {
        if (((EnumSet) obj).size() != 1) {
            return false;
        }
        return true;
    }

    /* Return type fixed from 'com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase' to match base method */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final AsArraySerializerBase<EnumSet<? extends Enum<?>>> A0I(AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer jsonSerializer) {
        return new EnumSetSerializer(this, r2, r3, jsonSerializer);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, X.0aV, X.0a8] */
    @Override // com.fasterxml.jackson.databind.ser.std.AsArraySerializerBase
    public final void A0J(EnumSet<? extends Enum<?>> enumSet, AbstractC02640aV r6, AnonymousClass0a8 r7) throws IOException, C02650aW {
        JsonSerializer<Object> jsonSerializer = this.A04;
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            Enum r2 = (Enum) it.next();
            if (jsonSerializer == null) {
                jsonSerializer = r7.A0B(r2.getDeclaringClass(), this.A03);
            }
            jsonSerializer.A0D(r2, r6, r7);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public final ContainerSerializer A0E(AnonymousClass0o6 r1) {
        return this;
    }

    public EnumSetSerializer(AnonymousClass0aI r8) {
        super(EnumSet.class, r8, true, null, null, null);
    }

    public EnumSetSerializer(EnumSetSerializer enumSetSerializer, AbstractC02580aL r2, AnonymousClass0o6 r3, JsonSerializer<?> jsonSerializer) {
        super(enumSetSerializer, r2, r3, jsonSerializer);
    }
}
