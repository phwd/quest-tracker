package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC0278Pa;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.O5;
import X.fF;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ArraySerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

@JacksonStdImpl
public class StringArraySerializer extends ArraySerializerBase implements AbstractC0278Pa {
    public static final StringArraySerializer A01 = new StringArraySerializer();
    public static final AbstractC1024qt A02 = new fF(String.class);
    public final JsonSerializer A00;

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1044rJ A2e;
        Object A0C;
        if (o5 == null || (A2e = o5.A2e()) == null || (A0C = r2Var._config.A01().A0C(A2e)) == null || (jsonSerializer = r2Var.A09(A2e, A0C)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer A022 = StdSerializer.A02(r2Var, o5, jsonSerializer);
        if (A022 == null) {
            A022 = r2Var.A0A(String.class, o5);
        } else if (A022 instanceof AbstractC0278Pa) {
            A022 = ((AbstractC0278Pa) A022).A1Y(r2Var, o5);
        }
        if (StdSerializer.A05(A022)) {
            A022 = null;
        }
        if (A022 == this.A00) {
            return this;
        }
        return new StringArraySerializer(this, o5, A022);
    }

    public StringArraySerializer() {
        super(String[].class, (O5) null);
        this.A00 = null;
    }

    public StringArraySerializer(StringArraySerializer stringArraySerializer, O5 o5, JsonSerializer jsonSerializer) {
        super(stringArraySerializer, o5);
        this.A00 = jsonSerializer;
    }
}
