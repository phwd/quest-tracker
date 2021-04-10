package com.fasterxml.jackson.databind.ser;

import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.AnonymousClass0Og;
import X.C02310iT;
import X.C04580qi;
import X.C04660qw;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.impl.BeanAsArraySerializer;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import java.io.IOException;

public class BeanSerializer extends BeanSerializerBase {
    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A04() {
        if (this.A03 == null && this.A02 == null && this.A04 == null) {
            return new BeanAsArraySerializer(this);
        }
        return this;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A05(C04660qw r2) {
        return new BeanSerializer(this, r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A06(String[] strArr) {
        return new BeanSerializer(this, strArr);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        if (this.A03 != null) {
            A03(obj, r4, r5, true);
            return;
        }
        r4.A0I();
        if (this.A04 != null) {
            A01();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        A02(obj, r4, r5);
        r4.A0F();
    }

    public final String toString() {
        return AnonymousClass006.A07("BeanSerializer for ", handledType().getName());
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> unwrappingSerializer(AbstractC04870rR r2) {
        return new UnwrappingBeanSerializer(this, r2);
    }

    public BeanSerializer(AbstractC02190iF r1, C04580qi r2, AnonymousClass0Og[] r3, AnonymousClass0Og[] r4) {
        super(r1, r2, r3, r4);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, C04660qw r2) {
        super(beanSerializerBase, r2);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
    }
}
