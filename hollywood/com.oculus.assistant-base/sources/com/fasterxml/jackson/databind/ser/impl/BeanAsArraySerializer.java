package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC1012qg;
import X.AbstractC1031r2;
import X.AnonymousClass08;
import X.C0289Pl;
import X.C1025qv;
import X.C1060ra;
import X.O9;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class BeanAsArraySerializer extends BeanSerializerBase {
    public final BeanSerializerBase A00;

    public static final void A00(BeanAsArraySerializer beanAsArraySerializer, Object obj, AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        String str = "[anySetter]";
        C1060ra[] raVarArr = beanAsArraySerializer.A05;
        if (raVarArr == null || r2Var._serializationView == null) {
            raVarArr = beanAsArraySerializer.A06;
        }
        try {
            for (C1060ra raVar : raVarArr) {
                if (raVar == null) {
                    qgVar.A0A();
                } else {
                    raVar.A05(obj, qgVar, r2Var);
                }
            }
        } catch (Exception e) {
            if (0 != raVarArr.length) {
                str = raVarArr[0].A06.getValue();
            }
            StdSerializer.A04(r2Var, e, obj, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } catch (StackOverflowError e2) {
            C1025qv qvVar = new C1025qv("Infinite recursion (StackOverflowError)", e2);
            if (0 != raVarArr.length) {
                str = raVarArr[0].A06.getValue();
            }
            qvVar.A03(new O9(obj, str));
            throw qvVar;
        }
    }

    public final String toString() {
        return AnonymousClass08.A04("BeanAsArraySerializer for ", A08().getName());
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase) {
        super(beanSerializerBase, (C0289Pl) null);
        this.A00 = beanSerializerBase;
    }

    public BeanAsArraySerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
        this.A00 = beanSerializerBase;
    }
}
