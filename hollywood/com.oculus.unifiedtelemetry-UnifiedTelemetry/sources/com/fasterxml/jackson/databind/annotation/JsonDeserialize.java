package com.fasterxml.jackson.databind.annotation;

import X.AbstractC0213Vx;
import X.AbstractC0221Wg;
import X.AbstractC0420hV;
import X.MH;
import X.dY;
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
    Class<?> as() default dY.class;

    Class<?> builder() default dY.class;

    Class<?> contentAs() default dY.class;

    Class<? extends MH<?, ?>> contentConverter() default AbstractC0213Vx.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<? extends MH<?, ?>> converter() default AbstractC0213Vx.class;

    Class<?> keyAs() default dY.class;

    Class<? extends AbstractC0420hV> keyUsing() default AbstractC0221Wg.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
