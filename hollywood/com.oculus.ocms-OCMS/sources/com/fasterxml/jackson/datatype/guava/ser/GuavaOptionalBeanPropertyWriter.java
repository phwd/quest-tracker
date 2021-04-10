package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.google.common.base.Optional;

public class GuavaOptionalBeanPropertyWriter extends BeanPropertyWriter {
    protected GuavaOptionalBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    @Override // com.fasterxml.jackson.databind.ser.BeanPropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        if ((get(obj) != null && !Optional.absent().equals(get(obj))) || this._nullSerializer != null) {
            super.serializeAsField(obj, jsonGenerator, serializerProvider);
        }
    }
}
