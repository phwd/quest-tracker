package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.impl.PropertyValue;
import java.io.IOException;

public final class PropertyValueBuffer {
    private PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    private Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    private int _paramsNeeded;
    protected final JsonParser _parser;

    public PropertyValueBuffer(JsonParser jsonParser, DeserializationContext deserializationContext, int i, ObjectIdReader objectIdReader) {
        this._parser = jsonParser;
        this._context = deserializationContext;
        this._paramsNeeded = i;
        this._objectIdReader = objectIdReader;
        this._creatorParameters = new Object[i];
    }

    public void inject(SettableBeanProperty[] settableBeanPropertyArr) {
        int length = settableBeanPropertyArr.length;
        for (int i = 0; i < length; i++) {
            SettableBeanProperty settableBeanProperty = settableBeanPropertyArr[i];
            if (settableBeanProperty != null) {
                this._creatorParameters[i] = this._context.findInjectableValue(settableBeanProperty.getInjectableValueId(), settableBeanProperty, null);
            }
        }
    }

    /* access modifiers changed from: protected */
    public final Object[] getParameters(Object[] objArr) {
        Object obj;
        if (objArr != null) {
            int length = this._creatorParameters.length;
            for (int i = 0; i < length; i++) {
                Object[] objArr2 = this._creatorParameters;
                if (objArr2[i] == null && (obj = objArr[i]) != null) {
                    objArr2[i] = obj;
                }
            }
        }
        return this._creatorParameters;
    }

    public boolean readIdProperty(String str) throws IOException {
        ObjectIdReader objectIdReader = this._objectIdReader;
        if (objectIdReader == null || !str.equals(objectIdReader.propertyName)) {
            return false;
        }
        this._idValue = this._objectIdReader.deserializer.deserialize(this._parser, this._context);
        return true;
    }

    public Object handleIdValue(DeserializationContext deserializationContext, Object obj) throws IOException {
        Object obj2;
        ObjectIdReader objectIdReader = this._objectIdReader;
        if (!(objectIdReader == null || (obj2 = this._idValue) == null)) {
            deserializationContext.findObjectId(obj2, objectIdReader.generator).bindItem(obj);
            SettableBeanProperty settableBeanProperty = this._objectIdReader.idProperty;
            if (settableBeanProperty != null) {
                return settableBeanProperty.setAndReturn(obj, this._idValue);
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public PropertyValue buffered() {
        return this._buffered;
    }

    public boolean assignParameter(int i, Object obj) {
        this._creatorParameters[i] = obj;
        int i2 = this._paramsNeeded - 1;
        this._paramsNeeded = i2;
        return i2 <= 0;
    }

    public void bufferProperty(SettableBeanProperty settableBeanProperty, Object obj) {
        this._buffered = new PropertyValue.Regular(this._buffered, obj, settableBeanProperty);
    }

    public void bufferAnyProperty(SettableAnyProperty settableAnyProperty, String str, Object obj) {
        this._buffered = new PropertyValue.Any(this._buffered, obj, settableAnyProperty, str);
    }

    public void bufferMapProperty(Object obj, Object obj2) {
        this._buffered = new PropertyValue.Map(this._buffered, obj2, obj);
    }
}
