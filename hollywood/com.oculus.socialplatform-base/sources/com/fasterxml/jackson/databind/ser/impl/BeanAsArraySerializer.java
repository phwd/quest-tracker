package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04870rR;
import X.AnonymousClass006;
import X.AnonymousClass0Og;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
import X.C04030p3;
import X.C04660qw;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class BeanAsArraySerializer extends BeanSerializerBase {
    public final BeanSerializerBase A00;

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A04() {
        return this;
    }

    private final void A00(Object obj, AbstractC02300iS r8, AbstractC02120i3 r9) throws IOException, C02310iT {
        String str = "[anySetter]";
        AnonymousClass0Og[] r5 = this.A05;
        if (r5 == null || r9._serializationView == null) {
            r5 = this.A06;
        }
        try {
            for (AnonymousClass0Og r0 : r5) {
                if (r0 == null) {
                    r8.A0G();
                } else {
                    r0.A05(obj, r8, r9);
                }
            }
        } catch (Exception e) {
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            StdSerializer.A05(r9, e, obj, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (StackOverflowError e2) {
            C02180iD r1 = new C02180iD("Infinite recursion (StackOverflowError)", e2);
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            r1.A04(new C04030p3(obj, str));
            throw r1;
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A05(C04660qw r2) {
        return this.A00.A05(r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A06(String[] strArr) {
        return new BeanAsArraySerializer(this, strArr);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        if (r5._config.A06(AnonymousClass0i4.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            AnonymousClass0Og[] r1 = this.A05;
            if (r1 == null || r5._serializationView == null) {
                r1 = this.A06;
            }
            if (r1.length == 1) {
                A00(obj, r4, r5);
                return;
            }
        }
        r4.A0H();
        A00(obj, r4, r5);
        r4.A0E();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final void serializeWithType(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C02310iT {
        this.A00.serializeWithType(obj, r3, r4, r5);
    }

    public final String toString() {
        return AnonymousClass006.A07("BeanAsArraySerializer for ", handledType().getName());
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> unwrappingSerializer(AbstractC04870rR r2) {
        return this.A00.unwrappingSerializer(r2);
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase, (C04660qw) null);
        this.A00 = beanSerializerBase;
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
        this.A00 = beanSerializerBase;
    }
}
