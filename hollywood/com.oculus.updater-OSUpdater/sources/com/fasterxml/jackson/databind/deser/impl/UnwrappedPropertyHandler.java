package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnwrappedPropertyHandler {
    protected final List<SettableBeanProperty> _properties;

    public UnwrappedPropertyHandler() {
        this._properties = new ArrayList();
    }

    protected UnwrappedPropertyHandler(List<SettableBeanProperty> list) {
        this._properties = list;
    }

    public void addProperty(SettableBeanProperty settableBeanProperty) {
        this._properties.add(settableBeanProperty);
    }

    public UnwrappedPropertyHandler renameAll(NameTransformer nameTransformer) {
        JsonDeserializer<Object> unwrappingDeserializer;
        ArrayList arrayList = new ArrayList(this._properties.size());
        for (SettableBeanProperty settableBeanProperty : this._properties) {
            SettableBeanProperty withName = settableBeanProperty.withName(nameTransformer.transform(settableBeanProperty.getName()));
            JsonDeserializer<Object> valueDeserializer = withName.getValueDeserializer();
            if (!(valueDeserializer == null || (unwrappingDeserializer = valueDeserializer.unwrappingDeserializer(nameTransformer)) == valueDeserializer)) {
                withName = withName.withValueDeserializer(unwrappingDeserializer);
            }
            arrayList.add(withName);
        }
        return new UnwrappedPropertyHandler(arrayList);
    }

    public Object processUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, TokenBuffer tokenBuffer) throws IOException, JsonProcessingException {
        int size = this._properties.size();
        for (int i = 0; i < size; i++) {
            JsonParser asParser = tokenBuffer.asParser();
            asParser.nextToken();
            this._properties.get(i).deserializeAndSet(asParser, deserializationContext, obj);
        }
        return obj;
    }
}
