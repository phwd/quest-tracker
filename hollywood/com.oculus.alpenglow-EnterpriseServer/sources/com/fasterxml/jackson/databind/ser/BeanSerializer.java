package com.fasterxml.jackson.databind.ser;

import X.AbstractC02640aV;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0Jw;
import X.AnonymousClass0a8;
import X.AnonymousClass0aI;
import X.AnonymousClass0oC;
import X.C02650aW;
import X.C06930oQ;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import java.io.IOException;

public final class BeanSerializer extends BeanSerializerBase {
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> A09(AbstractC07200ov r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        if (this.A03 != null) {
            A0J(obj, r4, r5, true);
            return;
        }
        r4.A0F();
        if (this.A04 != null) {
            A0H();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        A0I(obj, r4, r5);
        r4.A0C();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0E() {
        if (this.A03 == null && this.A02 == null && this.A04 == null) {
            return new BeanAsArraySerializer(this);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0F(C06930oQ r2) {
        return new BeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0G(String[] strArr) {
        return new BeanSerializer(this, strArr);
    }

    public final String toString() {
        return AnonymousClass006.A05("BeanSerializer for ", A0C().getName());
    }

    public BeanSerializer(AnonymousClass0aI r1, AnonymousClass0oC r2, AnonymousClass0Jw[] r3, AnonymousClass0Jw[] r4) {
        super(r1, r2, r3, r4);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, C06930oQ r2) {
        super(beanSerializerBase, r2);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
    }
}
