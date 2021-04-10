package com.fasterxml.jackson.databind.annotation;

import X.AbstractC07140on;
import X.AnonymousClass0ZO;
import X.C06410mq;
import X.EnumC06390mk;
import X.EnumC06400ml;
import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.databind.JsonSerializer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@JacksonAnnotation
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerialize {
    Class<?> as() default C06410mq.class;

    Class<?> contentAs() default C06410mq.class;

    Class<? extends AbstractC07140on<?, ?>> contentConverter() default AnonymousClass0ZO.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Class<? extends AbstractC07140on<?, ?>> converter() default AnonymousClass0ZO.class;

    @Deprecated
    EnumC06390mk include() default EnumC06390mk.ALWAYS;

    Class<?> keyAs() default C06410mq.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    EnumC06400ml typing() default EnumC06400ml.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
