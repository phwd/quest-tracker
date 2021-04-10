package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC0278Pa;
import X.AbstractC1012qg;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.O5;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase implements AbstractC0278Pa {
    public static final IndexedStringListSerializer A01 = new IndexedStringListSerializer(null);
    public final JsonSerializer A00;

    public static final void A00(IndexedStringListSerializer indexedStringListSerializer, List list, AbstractC1012qg qgVar, AbstractC1031r2 r2Var, int i) {
        try {
            JsonSerializer jsonSerializer = indexedStringListSerializer.A00;
            for (int i2 = 0; i2 < i; i2++) {
                String str = (String) list.get(i2);
                if (str == null) {
                    r2Var.A0D(qgVar);
                } else {
                    jsonSerializer.A0B(str, qgVar, r2Var);
                }
            }
        } catch (Exception e) {
            StdSerializer.A03(r2Var, e, list, 0);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final void A01(List list, AbstractC1012qg qgVar, AbstractC1031r2 r2Var, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                String str = (String) list.get(i2);
                if (str == null) {
                    r2Var.A0D(qgVar);
                } else {
                    qgVar.A0P(str);
                }
            } catch (Exception e) {
                StdSerializer.A03(r2Var, e, list, i2);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1044rJ A2e;
        Object A0C;
        if (o5 == null || (A2e = o5.A2e()) == null || (A0C = r2Var._config.A01().A0C(A2e)) == null || (jsonSerializer = r2Var.A09(A2e, A0C)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer A02 = StdSerializer.A02(r2Var, o5, jsonSerializer);
        if (A02 == null) {
            A02 = r2Var.A0A(String.class, o5);
        } else if (A02 instanceof AbstractC0278Pa) {
            A02 = ((AbstractC0278Pa) A02).A1Y(r2Var, o5);
        }
        if (StdSerializer.A05(A02)) {
            A02 = null;
        }
        if (A02 == this.A00) {
            return this;
        }
        return new IndexedStringListSerializer(A02);
    }

    public IndexedStringListSerializer() {
        this(null);
    }

    public IndexedStringListSerializer(JsonSerializer jsonSerializer) {
        super(List.class);
        this.A00 = jsonSerializer;
    }
}
