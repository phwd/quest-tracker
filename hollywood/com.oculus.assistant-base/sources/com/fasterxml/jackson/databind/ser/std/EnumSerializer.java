package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC1031r2;
import X.C0244Mv;
import X.EnumC0243Mu;
import X.O5;
import X.Q9;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer implements AbstractC0278Pa {
    public final Q9 A00;
    public final Boolean A01;

    public static Boolean A00(Class cls, C0244Mv mv, boolean z) {
        EnumC0243Mu mu;
        String str;
        if (mv == null || (mu = mv.A00) == null || mu == EnumC0243Mu.ANY || mu == EnumC0243Mu.SCALAR) {
            return null;
        }
        if (mu == EnumC0243Mu.STRING) {
            return Boolean.FALSE;
        }
        if (mu.isNumeric()) {
            return Boolean.TRUE;
        }
        StringBuilder sb = new StringBuilder("Unsupported serialization shape (");
        sb.append(mu);
        sb.append(") for Enum ");
        sb.append(cls.getName());
        sb.append(", not supported as ");
        if (z) {
            str = "class";
        } else {
            str = "property";
        }
        sb.append(str);
        sb.append(" annotation");
        throw new IllegalArgumentException(sb.toString());
    }

    public EnumSerializer(Q9 q9, Boolean bool) {
        super(Enum.class, false);
        this.A00 = q9;
        this.A01 = bool;
    }

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        C0244Mv A012;
        Boolean A002;
        if (o5 == null || (A012 = r2Var._config.A01().A01(o5.A2e())) == null || (A002 = A00(o5.A34()._class, A012, false)) == this.A01) {
            return this;
        }
        return new EnumSerializer(this.A00, A002);
    }
}
