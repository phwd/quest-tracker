package com.fasterxml.jackson.datatype.guava.ser;

import X.AbstractC0278Pa;
import X.AbstractC1012qg;
import X.AbstractC1031r2;
import X.O5;
import X.PU;
import X.UB;
import X.UK;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MultimapSerializer extends JsonSerializer implements AbstractC0278Pa {
    public final O5 A00;
    public final JsonSerializer A01;
    public final JsonSerializer A02;
    public final PU A03;

    @Override // X.AbstractC0278Pa
    public final JsonSerializer A1Y(AbstractC1031r2 r2Var, O5 o5) {
        JsonSerializer jsonSerializer = this.A02;
        if (jsonSerializer == null) {
            throw new NullPointerException("getContentType");
        }
        if (jsonSerializer instanceof AbstractC0278Pa) {
            jsonSerializer = ((AbstractC0278Pa) jsonSerializer).A1Y(r2Var, o5);
        }
        JsonSerializer jsonSerializer2 = this.A01;
        if (jsonSerializer2 == null) {
            throw new NullPointerException("getKeyType");
        }
        if (jsonSerializer2 instanceof AbstractC0278Pa) {
            jsonSerializer2 = ((AbstractC0278Pa) jsonSerializer2).A1Y(r2Var, o5);
        }
        PU pu = this.A03;
        if (pu != null) {
            pu = pu.A00(o5);
        }
        return new MultimapSerializer(o5, jsonSerializer2, pu, jsonSerializer);
    }

    public MultimapSerializer(O5 o5, JsonSerializer jsonSerializer, PU pu, JsonSerializer jsonSerializer2) {
        this.A00 = o5;
        this.A01 = jsonSerializer;
        this.A03 = pu;
        this.A02 = jsonSerializer2;
    }

    public static final void A00(MultimapSerializer multimapSerializer, UK uk, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        ArrayList arrayList;
        for (Map.Entry entry : uk.A1G().entrySet()) {
            JsonSerializer jsonSerializer = multimapSerializer.A01;
            if (jsonSerializer == null) {
                jsonSerializer = r2Var.A07(r2Var.A05().A09(String.class, null), multimapSerializer.A00);
            }
            jsonSerializer.A0B(entry.getKey(), qgVar, r2Var);
            JsonSerializer jsonSerializer2 = multimapSerializer.A02;
            if (jsonSerializer2 != null) {
                qgVar.A0B();
                for (Object obj : (Collection) entry.getValue()) {
                    jsonSerializer2.A0B(obj, qgVar, r2Var);
                }
                qgVar.A08();
            } else {
                Iterable iterable = (Iterable) entry.getValue();
                if (iterable != null) {
                    if (iterable instanceof Collection) {
                        arrayList = new ArrayList((Collection) iterable);
                    } else {
                        Iterator it = iterable.iterator();
                        arrayList = new ArrayList();
                        UB.A01(arrayList, it);
                    }
                    r2Var.A0B(arrayList.getClass(), true, null).A0B(arrayList, qgVar, r2Var);
                } else {
                    throw null;
                }
            }
        }
    }
}
