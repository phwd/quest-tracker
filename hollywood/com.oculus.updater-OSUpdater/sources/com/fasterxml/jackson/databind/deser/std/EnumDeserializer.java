package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import java.io.IOException;
import java.lang.reflect.Method;

public class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    private static final long serialVersionUID = -5893263645879532318L;
    protected final EnumResolver<?> _resolver;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public boolean isCachable() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING || currentToken == JsonToken.FIELD_NAME) {
            String text = jsonParser.getText();
            Enum<?> findEnum = this._resolver.findEnum(text);
            if (findEnum == null) {
                if (deserializationContext.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && (text.length() == 0 || text.trim().length() == 0)) {
                    return null;
                }
                if (!deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    throw deserializationContext.weirdStringException(text, this._resolver.getEnumClass(), "value not one of declared Enum instance names");
                }
            }
            return findEnum;
        } else if (currentToken != JsonToken.VALUE_NUMBER_INT) {
            throw deserializationContext.mappingException(this._resolver.getEnumClass());
        } else if (!deserializationContext.isEnabled(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS)) {
            int intValue = jsonParser.getIntValue();
            Enum<?> r0 = this._resolver.getEnum(intValue);
            if (r0 != null || deserializationContext.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return r0;
            }
            Integer valueOf = Integer.valueOf(intValue);
            Class<?> enumClass = this._resolver.getEnumClass();
            throw deserializationContext.weirdNumberException(valueOf, enumClass, "index value outside legal index range [0.." + this._resolver.lastValidIndex() + "]");
        } else {
            throw deserializationContext.mappingException("Not allowed to deserialize Enum value out of JSON number (disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow)");
        }
    }

    protected static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        private static final long serialVersionUID = -7775129435872564122L;
        protected final Class<?> _enumClass;
        protected final Method _factory;
        protected final Class<?> _inputType;

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            Object obj;
            Class<?> cls = this._inputType;
            if (cls == null) {
                obj = jsonParser.getText();
            } else if (cls == Integer.class) {
                obj = Integer.valueOf(jsonParser.getValueAsInt());
            } else if (cls == Long.class) {
                obj = Long.valueOf(jsonParser.getValueAsLong());
            } else {
                throw deserializationContext.mappingException(this._enumClass);
            }
            try {
                return this._factory.invoke(this._enumClass, obj);
            } catch (Exception e) {
                ClassUtil.unwrapAndThrowAsIAE(e);
                return null;
            }
        }
    }
}
