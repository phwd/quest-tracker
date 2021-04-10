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
import java.util.Collection;
import java.util.Iterator;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase implements AbstractC0278Pa {
    public static final StringCollectionSerializer A01 = new StringCollectionSerializer(null);
    public final JsonSerializer A00;

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
        return new StringCollectionSerializer(A02);
    }

    public static void A00(StringCollectionSerializer stringCollectionSerializer, Collection collection, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        JsonSerializer jsonSerializer = stringCollectionSerializer.A00;
        for (Object obj : collection) {
            if (obj == null) {
                try {
                    r2Var.A0D(qgVar);
                } catch (Exception e) {
                    StdSerializer.A03(r2Var, e, collection, 0);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                jsonSerializer.A0B(obj, qgVar, r2Var);
            }
        }
    }

    public static final void A01(StringCollectionSerializer stringCollectionSerializer, Collection collection, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        if (stringCollectionSerializer.A00 != null) {
            A00(stringCollectionSerializer, collection, qgVar, r2Var);
            return;
        }
        int i = 0;
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                try {
                    r2Var.A0D(qgVar);
                } catch (Exception e) {
                    StdSerializer.A03(r2Var, e, collection, i);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                qgVar.A0P(str);
            }
            i++;
        }
    }

    public StringCollectionSerializer() {
        this(null);
    }

    public StringCollectionSerializer(JsonSerializer jsonSerializer) {
        super(Collection.class);
        this.A00 = jsonSerializer;
    }
}
