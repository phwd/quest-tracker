package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;

public class ValueInjector extends BeanProperty.Std {
    protected final Object _valueId;

    public Object findValue(DeserializationContext deserializationContext, Object obj) {
        return deserializationContext.findInjectableValue(this._valueId, this, obj);
    }

    public void inject(DeserializationContext deserializationContext, Object obj) throws IOException {
        this._member.setValue(obj, findValue(deserializationContext, obj));
    }
}
