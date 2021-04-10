package com.fasterxml.jackson.datatype.guava.ser;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.google.common.base.Optional;
import java.util.List;

public class GuavaBeanSerializerModifier extends BeanSerializerModifier {
    @Override // com.fasterxml.jackson.databind.ser.BeanSerializerModifier
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BeanDescription beanDescription, List<BeanPropertyWriter> list) {
        for (int i = 0; i < list.size(); i++) {
            BeanPropertyWriter beanPropertyWriter = list.get(i);
            if (Optional.class.isAssignableFrom(beanPropertyWriter.getPropertyType())) {
                list.set(i, new GuavaOptionalBeanPropertyWriter(beanPropertyWriter));
            }
        }
        return list;
    }
}
