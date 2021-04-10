package com.fasterxml.jackson.databind.annotation;

import X.AnonymousClass6c;
import X.AnonymousClass80;
import X.C00093c;
import X.Qt;
import X.RR;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonDeserialize {
    Class<?> as() default C00093c.class;

    Class<?> builder() default C00093c.class;

    Class<?> contentAs() default C00093c.class;

    Class<? extends AnonymousClass80<?, ?>> contentConverter() default Qt.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<? extends AnonymousClass80<?, ?>> converter() default Qt.class;

    Class<?> keyAs() default C00093c.class;

    Class<? extends AnonymousClass6c> keyUsing() default RR.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
