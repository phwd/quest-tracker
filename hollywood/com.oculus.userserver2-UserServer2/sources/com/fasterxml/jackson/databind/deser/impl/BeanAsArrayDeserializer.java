package com.fasterxml.jackson.databind.deser.impl;

import X.AQ;
import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.RW;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import java.io.IOException;

public final class BeanAsArrayDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final BeanDeserializerBase _delegate;
    public final AQ[] _orderedProperties;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        StringBuilder sb;
        String str;
        if (((B3) rn).A00 != AnonymousClass9p.START_ARRAY) {
            throw null;
        } else if (this._vanillaProcessing) {
            throw null;
        } else if (this._nonStandardCreation) {
            JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
            if (jsonDeserializer != null) {
                jsonDeserializer.A03(rn, rd);
                throw null;
            } else if (this._propertyBasedCreator != null) {
                A09(rn, rd);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                if (this._beanType.A00()) {
                    sb = new StringBuilder("Can not instantiate abstract type ");
                    sb.append(this._beanType);
                    str = " (need to add/enable type information?)";
                } else {
                    sb = new StringBuilder("No suitable constructor found for type ");
                    sb.append(this._beanType);
                    str = ": can not instantiate from JSON object (need to add/enable type information?)";
                }
                sb.append(str);
                throw RW.A00(rn, sb.toString());
            }
        } else {
            throw null;
        }
    }
}
