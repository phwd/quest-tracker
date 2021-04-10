package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC0278Pa;
import X.AbstractC1012qg;
import X.AbstractC1024qt;
import X.AbstractC1031r2;
import X.AbstractC1044rJ;
import X.C1015qj;
import X.EnumC1030r1;
import X.O5;
import X.PU;
import X.Q9;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import java.lang.reflect.Modifier;
import java.util.EnumMap;
import java.util.Map;

@JacksonStdImpl
public class EnumMapSerializer extends ContainerSerializer implements AbstractC0278Pa {
    public final O5 A00;
    public final AbstractC1024qt A01;
    public final JsonSerializer A02;
    public final PU A03;
    public final Q9 A04;
    public final boolean A05;

    public static final void A01(EnumMapSerializer enumMapSerializer, EnumMap enumMap, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        JsonSerializer jsonSerializer = enumMapSerializer.A02;
        if (jsonSerializer != null) {
            Q9 q9 = enumMapSerializer.A04;
            boolean z = !r2Var._config.A06(EnumC1030r1.WRITE_NULL_MAP_VALUES);
            PU pu = enumMapSerializer.A03;
            for (Map.Entry entry : enumMap.entrySet()) {
                Object value = entry.getValue();
                if (!z || value != null) {
                    Enum r2 = (Enum) entry.getKey();
                    if (q9 == null) {
                        q9 = ((EnumSerializer) ((StdSerializer) r2Var.A0A(r2.getDeclaringClass(), enumMapSerializer.A00))).A00;
                    }
                    qgVar.A0J((C1015qj) q9.A00.get(r2));
                    if (value == null) {
                        r2Var.A0D(qgVar);
                    } else if (pu == null) {
                        try {
                            jsonSerializer.A0B(value, qgVar, r2Var);
                        } catch (Exception e) {
                            StdSerializer.A04(r2Var, e, enumMap, ((Enum) entry.getKey()).name());
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        jsonSerializer.A09(value, qgVar, r2Var, pu);
                    }
                }
            }
            return;
        }
        Q9 q92 = enumMapSerializer.A04;
        boolean z2 = !r2Var._config.A06(EnumC1030r1.WRITE_NULL_MAP_VALUES);
        PU pu2 = enumMapSerializer.A03;
        Class<?> cls = null;
        JsonSerializer jsonSerializer2 = null;
        for (Map.Entry entry2 : enumMap.entrySet()) {
            Object value2 = entry2.getValue();
            if (!z2 || value2 != null) {
                Enum r7 = (Enum) entry2.getKey();
                if (q92 == null) {
                    q92 = ((EnumSerializer) ((StdSerializer) r2Var.A0A(r7.getDeclaringClass(), enumMapSerializer.A00))).A00;
                }
                qgVar.A0J((C1015qj) q92.A00.get(r7));
                if (value2 == null) {
                    r2Var.A0D(qgVar);
                } else {
                    Class<?> cls2 = value2.getClass();
                    if (cls2 != cls) {
                        jsonSerializer2 = r2Var.A0A(cls2, enumMapSerializer.A00);
                        cls = cls2;
                    }
                    if (pu2 == null) {
                        try {
                            jsonSerializer2.A0B(value2, qgVar, r2Var);
                        } catch (Exception e2) {
                            StdSerializer.A04(r2Var, e2, enumMap, ((Enum) entry2.getKey()).name());
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        jsonSerializer2.A09(value2, qgVar, r2Var, pu2);
                    }
                }
            }
        }
    }

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer;
        AbstractC1044rJ A2e;
        Object A0C;
        if (o5 == null || (A2e = o5.A2e()) == null || (A0C = r2Var._config.A01().A0C(A2e)) == null || (jsonSerializer = r2Var.A09(A2e, A0C)) == null) {
            jsonSerializer = this.A02;
        }
        JsonSerializer A022 = StdSerializer.A02(r2Var, o5, jsonSerializer);
        if (A022 == null) {
            if (this.A05) {
                JsonSerializer A08 = r2Var.A08(this.A01, o5);
                if (this.A00 == o5 && A08 == this.A02) {
                    return this;
                }
                return new EnumMapSerializer(this, o5, A08);
            }
        } else if (this.A02 instanceof AbstractC0278Pa) {
            A022 = ((AbstractC0278Pa) A022).A1Y(r2Var, o5);
        }
        JsonSerializer jsonSerializer2 = this.A02;
        if (A022 == jsonSerializer2) {
            return this;
        }
        if (this.A00 == o5 && A022 == jsonSerializer2) {
            return this;
        }
        return new EnumMapSerializer(this, o5, A022);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(AbstractC1024qt qtVar, boolean z, Q9 q9, PU pu, JsonSerializer jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        this.A00 = null;
        if (z || (qtVar != null && Modifier.isFinal(qtVar._class.getModifiers()))) {
            z2 = true;
        }
        this.A05 = z2;
        this.A01 = qtVar;
        this.A04 = q9;
        this.A03 = pu;
        this.A02 = jsonSerializer;
    }

    public EnumMapSerializer(EnumMapSerializer enumMapSerializer, O5 o5, JsonSerializer jsonSerializer) {
        super(enumMapSerializer);
        this.A00 = o5;
        this.A05 = enumMapSerializer.A05;
        this.A01 = enumMapSerializer.A01;
        this.A04 = enumMapSerializer.A04;
        this.A03 = enumMapSerializer.A03;
        this.A02 = jsonSerializer;
    }
}
