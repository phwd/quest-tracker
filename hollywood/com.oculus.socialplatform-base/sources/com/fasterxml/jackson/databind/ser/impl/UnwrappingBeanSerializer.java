package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.C02310iT;
import X.C04660qw;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import java.io.IOException;

public class UnwrappingBeanSerializer extends BeanSerializerBase {
    public final AbstractC04870rR A00;

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A04() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isUnwrappingSerializer() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A05(C04660qw r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A06(String[] strArr) {
        return new UnwrappingBeanSerializer(this, strArr);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        if (this.A03 != null) {
            A03(obj, r4, r5, false);
        } else if (this.A04 != null) {
            A01();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            A02(obj, r4, r5);
        }
    }

    public final String toString() {
        return AnonymousClass006.A07("UnwrappingBeanSerializer for ", handledType().getName());
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> unwrappingSerializer(AbstractC04870rR r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, C04660qw r3) {
        super(unwrappingBeanSerializer, r3);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, String[] strArr) {
        super(unwrappingBeanSerializer, strArr);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase, AbstractC04870rR r2) {
        super(beanSerializerBase, r2);
        this.A00 = r2;
    }
}
