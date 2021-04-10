package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02640aV;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.C02650aW;
import X.C06930oQ;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import java.io.IOException;

public final class UnwrappingBeanSerializer extends BeanSerializerBase {
    public final AbstractC07200ov A00;

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A07() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0E() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> A09(AbstractC07200ov r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        if (this.A03 != null) {
            A0J(obj, r4, r5, false);
        } else if (this.A04 != null) {
            A0H();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else {
            A0I(obj, r4, r5);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0F(C06930oQ r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0G(String[] strArr) {
        return new UnwrappingBeanSerializer(this, strArr);
    }

    public final String toString() {
        return AnonymousClass006.A05("UnwrappingBeanSerializer for ", A0C().getName());
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, C06930oQ r3) {
        super(unwrappingBeanSerializer, r3);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, String[] strArr) {
        super(unwrappingBeanSerializer, strArr);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase, AbstractC07200ov r2) {
        super(beanSerializerBase, r2);
        this.A00 = r2;
    }
}
