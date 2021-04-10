package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC0282Pe;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AnonymousClass08;
import X.O5;
import com.fasterxml.jackson.databind.JsonSerializer;

public class StdDelegatingSerializer extends StdSerializer implements AbstractC0278Pa, AbstractC0282Pe {
    public final AbstractC1024qt A00;
    public final JsonSerializer A01;

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1024qt qtVar;
        JsonSerializer jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            qtVar = this.A00;
            if (qtVar == null) {
                throw new NullPointerException("getOutputType");
            }
            jsonSerializer = r2Var.A08(qtVar, o5);
            Class<?> cls = getClass();
            if (cls != StdDelegatingSerializer.class) {
                throw new IllegalStateException(AnonymousClass08.A05("Sub-class ", cls.getName(), " must override 'withDelegate'"));
            }
        } else if (!(jsonSerializer2 instanceof AbstractC0278Pa) || (jsonSerializer = ((AbstractC0278Pa) jsonSerializer2).A1Y(r2Var, o5)) == jsonSerializer2) {
            return this;
        } else {
            qtVar = this.A00;
            Class<?> cls2 = getClass();
            if (cls2 != StdDelegatingSerializer.class) {
                throw new IllegalStateException(AnonymousClass08.A05("Sub-class ", cls2.getName(), " must override 'withDelegate'"));
            }
        }
        return new StdDelegatingSerializer(qtVar, jsonSerializer);
    }

    @Override // X.AbstractC0282Pe
    public final void A4t(AbstractC1031r2 r2Var) {
        JsonSerializer jsonSerializer = this.A01;
        if (jsonSerializer != null && (jsonSerializer instanceof AbstractC0282Pe)) {
            ((AbstractC0282Pe) jsonSerializer).A4t(r2Var);
        }
    }

    public StdDelegatingSerializer(AbstractC1024qt qtVar, JsonSerializer jsonSerializer) {
        super(qtVar);
        this.A00 = qtVar;
        this.A01 = jsonSerializer;
    }
}
