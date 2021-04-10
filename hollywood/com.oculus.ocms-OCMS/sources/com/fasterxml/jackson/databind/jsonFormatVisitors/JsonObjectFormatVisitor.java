package com.fasterxml.jackson.databind.jsonFormatVisitors;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializerProvider;

public interface JsonObjectFormatVisitor extends JsonFormatVisitorWithSerializerProvider {
    void optionalProperty(BeanProperty beanProperty) throws JsonMappingException;

    @Deprecated
    void optionalProperty(String str) throws JsonMappingException;

    void optionalProperty(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;

    void property(BeanProperty beanProperty) throws JsonMappingException;

    @Deprecated
    void property(String str) throws JsonMappingException;

    void property(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException;

    public static class Base implements JsonObjectFormatVisitor {
        protected SerializerProvider _provider;

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        public void optionalProperty(BeanProperty beanProperty) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        @Deprecated
        public void optionalProperty(String str) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        public void optionalProperty(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        public void property(BeanProperty beanProperty) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        @Deprecated
        public void property(String str) throws JsonMappingException {
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor
        public void property(String str, JsonFormatVisitable jsonFormatVisitable, JavaType javaType) throws JsonMappingException {
        }

        public Base() {
        }

        public Base(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider
        public SerializerProvider getProvider() {
            return this._provider;
        }

        @Override // com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWithSerializerProvider
        public void setProvider(SerializerProvider serializerProvider) {
            this._provider = serializerProvider;
        }
    }
}
