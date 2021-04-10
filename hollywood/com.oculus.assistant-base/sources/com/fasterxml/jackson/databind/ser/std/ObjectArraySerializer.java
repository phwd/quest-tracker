package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC0292Po;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.C1062rc;
import X.O5;
import X.PU;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;

@JacksonStdImpl
public class ObjectArraySerializer extends ArraySerializerBase implements AbstractC0278Pa {
    public JsonSerializer A00;
    public AbstractC0292Po A01;
    public final AbstractC1024qt A02;
    public final PU A03;
    public final boolean A04;

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1044rJ A2e;
        Object A0C;
        PU pu = this.A03;
        if (pu != null) {
            pu = pu.A00(o5);
        }
        if (o5 == null || (A2e = o5.A2e()) == null || (A0C = r2Var._config.A01().A0C(A2e)) == null || (jsonSerializer = r2Var.A09(A2e, A0C)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer A022 = StdSerializer.A02(r2Var, o5, jsonSerializer);
        if (A022 == null) {
            AbstractC1024qt qtVar = this.A02;
            if (qtVar != null && (this.A04 || ContainerSerializer.A00(r2Var, o5))) {
                A022 = r2Var.A08(qtVar, o5);
            }
        } else if (A022 instanceof AbstractC0278Pa) {
            A022 = ((AbstractC0278Pa) A022).A1Y(r2Var, o5);
        }
        if (((ArraySerializerBase) this).A00 == o5 && A022 == this.A00 && pu == pu) {
            return this;
        }
        return new ObjectArraySerializer(this, o5, pu, A022);
    }

    public ObjectArraySerializer(AbstractC1024qt qtVar, boolean z, PU pu, JsonSerializer jsonSerializer) {
        super(Object[].class, (O5) null);
        this.A02 = qtVar;
        this.A04 = z;
        this.A03 = pu;
        this.A01 = C1062rc.A00;
        this.A00 = jsonSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, O5 o5, PU pu, JsonSerializer jsonSerializer) {
        super(objectArraySerializer, o5);
        this.A02 = objectArraySerializer.A02;
        this.A03 = pu;
        this.A04 = objectArraySerializer.A04;
        this.A01 = objectArraySerializer.A01;
        this.A00 = jsonSerializer;
    }
}
