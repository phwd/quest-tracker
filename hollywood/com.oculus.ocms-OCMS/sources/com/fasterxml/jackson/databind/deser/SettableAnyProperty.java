package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import java.io.IOException;
import java.lang.reflect.Method;

public final class SettableAnyProperty {
    protected final BeanProperty _property;
    protected final Method _setter;
    protected final JavaType _type;
    protected JsonDeserializer<Object> _valueDeserializer;

    public SettableAnyProperty(BeanProperty beanProperty, AnnotatedMethod annotatedMethod, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this(beanProperty, annotatedMethod.getAnnotated(), javaType, jsonDeserializer);
    }

    public SettableAnyProperty(BeanProperty beanProperty, Method method, JavaType javaType, JsonDeserializer<Object> jsonDeserializer) {
        this._property = beanProperty;
        this._type = javaType;
        this._setter = method;
        this._valueDeserializer = jsonDeserializer;
    }

    public SettableAnyProperty withValueDeserializer(JsonDeserializer<Object> jsonDeserializer) {
        return new SettableAnyProperty(this._property, this._setter, this._type, jsonDeserializer);
    }

    public BeanProperty getProperty() {
        return this._property;
    }

    public boolean hasValueDeserializer() {
        return this._valueDeserializer != null;
    }

    public JavaType getType() {
        return this._type;
    }

    public final void deserializeAndSet(JsonParser jsonParser, DeserializationContext deserializationContext, Object obj, String str) throws IOException, JsonProcessingException {
        set(obj, str, deserialize(jsonParser, deserializationContext));
    }

    public final Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser.getCurrentToken() == JsonToken.VALUE_NULL) {
            return null;
        }
        return this._valueDeserializer.deserialize(jsonParser, deserializationContext);
    }

    public final void set(Object obj, String str, Object obj2) throws IOException {
        try {
            this._setter.invoke(obj, str, obj2);
        } catch (Exception e) {
            _throwAsIOE(e, str, obj2);
        }
    }

    /* access modifiers changed from: protected */
    public void _throwAsIOE(Exception exc, String str, Object obj) throws IOException {
        String str2;
        if (exc instanceof IllegalArgumentException) {
            if (obj == null) {
                str2 = "[NULL]";
            } else {
                str2 = obj.getClass().getName();
            }
            StringBuilder sb = new StringBuilder("Problem deserializing \"any\" property '");
            sb.append(str);
            sb.append("' of class " + getClassName() + " (expected type: ");
            sb.append(this._type);
            sb.append("; actual type: ");
            sb.append(str2);
            sb.append(")");
            String message = exc.getMessage();
            if (message != null) {
                sb.append(", problem: ");
                sb.append(message);
            } else {
                sb.append(" (no error message provided)");
            }
            throw new JsonMappingException(sb.toString(), null, exc);
        } else if (!(exc instanceof IOException)) {
            boolean z = exc instanceof RuntimeException;
            Exception exc2 = exc;
            if (!z) {
                while (exc2.getCause() != null) {
                    exc2 = exc2.getCause();
                }
                throw new JsonMappingException(exc2.getMessage(), null, exc2);
            }
            throw ((RuntimeException) exc);
        } else {
            throw ((IOException) exc);
        }
    }

    private String getClassName() {
        return this._setter.getDeclaringClass().getName();
    }

    public String toString() {
        return "[any property on class " + getClassName() + "]";
    }
}
