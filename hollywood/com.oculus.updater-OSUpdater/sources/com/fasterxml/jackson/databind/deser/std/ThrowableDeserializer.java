package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.BeanDeserializer;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;

public class ThrowableDeserializer extends BeanDeserializer {
    private static final long serialVersionUID = 1;

    protected ThrowableDeserializer(BeanDeserializer beanDeserializer, NameTransformer nameTransformer) {
        super(beanDeserializer, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public JsonDeserializer<Object> unwrappingDeserializer(NameTransformer nameTransformer) {
        if (getClass() != ThrowableDeserializer.class) {
            return this;
        }
        return new ThrowableDeserializer(this, nameTransformer);
    }

    @Override // com.fasterxml.jackson.databind.deser.BeanDeserializerBase, com.fasterxml.jackson.databind.deser.BeanDeserializer
    public Object deserializeFromObject(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        Object obj;
        if (this._propertyBasedCreator != null) {
            return _deserializeUsingPropertyBased(jsonParser, deserializationContext);
        }
        if (this._delegateDeserializer != null) {
            return this._valueInstantiator.createUsingDelegate(deserializationContext, this._delegateDeserializer.deserialize(jsonParser, deserializationContext));
        }
        if (!this._beanType.isAbstract()) {
            boolean canCreateFromString = this._valueInstantiator.canCreateFromString();
            boolean canCreateUsingDefault = this._valueInstantiator.canCreateUsingDefault();
            if (canCreateFromString || canCreateUsingDefault) {
                Object obj2 = null;
                Object[] objArr = null;
                int i = 0;
                while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
                    String currentName = jsonParser.getCurrentName();
                    SettableBeanProperty find = this._beanProperties.find(currentName);
                    jsonParser.nextToken();
                    if (find != null) {
                        if (obj2 != null) {
                            find.deserializeAndSet(jsonParser, deserializationContext, obj2);
                        } else {
                            if (objArr == null) {
                                int size = this._beanProperties.size();
                                objArr = new Object[(size + size)];
                            }
                            int i2 = i + 1;
                            objArr[i] = find;
                            i = i2 + 1;
                            objArr[i2] = find.deserialize(jsonParser, deserializationContext);
                        }
                    } else if ("message".equals(currentName) && canCreateFromString) {
                        obj2 = this._valueInstantiator.createFromString(deserializationContext, jsonParser.getText());
                        if (objArr != null) {
                            for (int i3 = 0; i3 < i; i3 += 2) {
                                ((SettableBeanProperty) objArr[i3]).set(obj2, objArr[i3 + 1]);
                            }
                            objArr = null;
                        }
                    } else if (this._ignorableProps != null && this._ignorableProps.contains(currentName)) {
                        jsonParser.skipChildren();
                    } else if (this._anySetter != null) {
                        this._anySetter.deserializeAndSet(jsonParser, deserializationContext, obj2, currentName);
                    } else {
                        handleUnknownProperty(jsonParser, deserializationContext, obj2, currentName);
                    }
                    jsonParser.nextToken();
                }
                if (obj2 == null) {
                    if (canCreateFromString) {
                        obj = this._valueInstantiator.createFromString(deserializationContext, null);
                    } else {
                        obj = this._valueInstantiator.createUsingDefault(deserializationContext);
                    }
                    obj2 = obj;
                    if (objArr != null) {
                        for (int i4 = 0; i4 < i; i4 += 2) {
                            ((SettableBeanProperty) objArr[i4]).set(obj2, objArr[i4 + 1]);
                        }
                    }
                }
                return obj2;
            }
            throw new JsonMappingException("Can not deserialize Throwable of type " + this._beanType + " without having a default contructor, a single-String-arg constructor; or explicit @JsonCreator");
        }
        throw JsonMappingException.from(jsonParser, "Can not instantiate abstract type " + this._beanType + " (need to add/enable type information?)");
    }
}
