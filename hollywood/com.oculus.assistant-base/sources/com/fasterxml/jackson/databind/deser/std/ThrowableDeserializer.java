package com.fasterxml.jackson.databind.deser.std;

import X.QC;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;

public class ThrowableDeserializer extends BeanDeserializer {
    public static final long serialVersionUID = 1;

    public ThrowableDeserializer(BeanDeserializer beanDeserializer) {
        super(beanDeserializer);
        this._vanillaProcessing = false;
    }

    public ThrowableDeserializer(BeanDeserializer beanDeserializer, QC qc) {
        super(beanDeserializer, qc);
    }
}
