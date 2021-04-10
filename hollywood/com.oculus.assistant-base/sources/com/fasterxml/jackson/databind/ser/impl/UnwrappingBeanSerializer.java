package com.fasterxml.jackson.databind.ser.impl;

import X.AnonymousClass08;
import X.C0289Pl;
import X.QC;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

public class UnwrappingBeanSerializer extends BeanSerializerBase {
    public final QC A00;

    public final String toString() {
        return AnonymousClass08.A04("UnwrappingBeanSerializer for ", A08().getName());
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, C0289Pl pl) {
        super(unwrappingBeanSerializer, pl);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(UnwrappingBeanSerializer unwrappingBeanSerializer, String[] strArr) {
        super(unwrappingBeanSerializer, strArr);
        this.A00 = unwrappingBeanSerializer.A00;
    }

    public UnwrappingBeanSerializer(BeanSerializerBase beanSerializerBase, QC qc) {
        super(beanSerializerBase, qc);
        this.A00 = qc;
    }
}
