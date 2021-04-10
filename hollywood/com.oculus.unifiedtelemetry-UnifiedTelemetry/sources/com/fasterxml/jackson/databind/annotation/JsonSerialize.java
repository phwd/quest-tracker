package com.fasterxml.jackson.databind.annotation;

import X.AbstractC0213Vx;
import X.EnumC0384gi;
import X.MH;
import X.dY;
import X.gj;
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
    Class<?> as() default dY.class;

    Class<?> contentAs() default dY.class;

    Class<? extends MH<?, ?>> contentConverter() default AbstractC0213Vx.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Class<? extends MH<?, ?>> converter() default AbstractC0213Vx.class;

    @Deprecated
    gj include() default gj.ALWAYS;

    Class<?> keyAs() default dY.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    EnumC0384gi typing() default EnumC0384gi.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
