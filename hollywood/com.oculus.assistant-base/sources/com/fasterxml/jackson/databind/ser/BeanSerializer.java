package com.fasterxml.jackson.databind.ser;

import X.AbstractC1024qt;
import X.AnonymousClass08;
import X.C0289Pl;
import X.C1060ra;
import X.PY;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

public class BeanSerializer extends BeanSerializerBase {
    public final String toString() {
        return AnonymousClass08.A04("BeanSerializer for ", A08().getName());
    }

    public BeanSerializer(AbstractC1024qt qtVar, PY py, C1060ra[] raVarArr, C1060ra[] raVarArr2) {
        super(qtVar, py, raVarArr, raVarArr2);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, C0289Pl pl) {
        super(beanSerializerBase, pl);
    }

    public BeanSerializer(BeanSerializerBase beanSerializerBase, String[] strArr) {
        super(beanSerializerBase, strArr);
    }
}
