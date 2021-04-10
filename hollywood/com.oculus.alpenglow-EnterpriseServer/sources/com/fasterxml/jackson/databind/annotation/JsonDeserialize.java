package com.fasterxml.jackson.databind.annotation;

import X.AbstractC02550aD;
import X.AbstractC07140on;
import X.AnonymousClass0ZO;
import X.AnonymousClass0mY;
import X.C06410mq;
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
    Class<?> as() default C06410mq.class;

    Class<?> builder() default C06410mq.class;

    Class<?> contentAs() default C06410mq.class;

    Class<? extends AbstractC07140on<?, ?>> contentConverter() default AnonymousClass0ZO.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<? extends AbstractC07140on<?, ?>> converter() default AnonymousClass0ZO.class;

    Class<?> keyAs() default C06410mq.class;

    Class<? extends AnonymousClass0mY> keyUsing() default AbstractC02550aD.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
