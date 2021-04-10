package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02640aV;
import X.AbstractC07200ov;
import X.AnonymousClass006;
import X.AnonymousClass0Jw;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C06290mV;
import X.C06930oQ;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public final class BeanAsArraySerializer extends BeanSerializerBase {
    public final BeanSerializerBase A00;

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0E() {
        return this;
    }

    private final void A00(Object obj, AbstractC02640aV r8, AnonymousClass0a8 r9) throws IOException, C02650aW {
        String str = "[anySetter]";
        AnonymousClass0Jw[] r5 = this.A05;
        if (r5 == null || r9._serializationView == null) {
            r5 = this.A06;
        }
        try {
            for (AnonymousClass0Jw r0 : r5) {
                if (r0 == null) {
                    r8.A0D();
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
            AnonymousClass0aG r1 = new AnonymousClass0aG("Infinite recursion (StackOverflowError)", e2);
            if (0 != r5.length) {
                str = r5[0].A06.getValue();
            }
            r1.A04(new C06290mV(obj, str));
            throw r1;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final JsonSerializer<Object> A09(AbstractC07200ov r2) {
        return this.A00.A09(r2);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final void A0A(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C02650aW {
        this.A00.A0A(obj, r3, r4, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.BeanSerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        if (r5._config.A06(AnonymousClass0a9.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
            AnonymousClass0Jw[] r1 = this.A05;
            if (r1 == null || r5._serializationView == null) {
                r1 = this.A06;
            }
            if (r1.length == 1) {
                A00(obj, r4, r5);
                return;
            }
        }
        r4.A0E();
        A00(obj, r4, r5);
        r4.A0B();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0F(C06930oQ r2) {
        return this.A00.A0F(r2);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.BeanSerializerBase
    public final BeanSerializerBase A0G(String[] strArr) {
        return new BeanAsArraySerializer(this, strArr);
    }

    public final String toString() {
        return AnonymousClass006.A05("BeanAsArraySerializer for ", A0C().getName());
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase, (C06930oQ) null);
        this.A00 = beanSerializerBase;
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
        this.A00 = beanSerializerBase;
    }
}
