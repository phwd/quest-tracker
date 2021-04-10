package com.fasterxml.jackson.databind.annotation;

import X.AbstractC03970gW;
import X.AbstractC05240kb;
import X.AbstractC06340mv;
import X.AnonymousClass0fm;
import X.C05340ku;
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
    Class<?> as() default C05340ku.class;

    Class<?> builder() default C05340ku.class;

    Class<?> contentAs() default C05340ku.class;

    Class<? extends AbstractC06340mv<?, ?>> contentConverter() default AnonymousClass0fm.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<? extends AbstractC06340mv<?, ?>> converter() default AnonymousClass0fm.class;

    Class<?> keyAs() default C05340ku.class;

    Class<? extends AbstractC05240kb> keyUsing() default AbstractC03970gW.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
