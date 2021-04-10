package com.fasterxml.jackson.databind.annotation;

import X.AbstractC06340mv;
import X.AnonymousClass0fm;
import X.C05340ku;
import X.EnumC05320ko;
import X.EnumC05330kp;
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
    Class<?> as() default C05340ku.class;

    Class<?> contentAs() default C05340ku.class;

    Class<? extends AbstractC06340mv<?, ?>> contentConverter() default AnonymousClass0fm.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Class<? extends AbstractC06340mv<?, ?>> converter() default AnonymousClass0fm.class;

    @Deprecated
    EnumC05320ko include() default EnumC05320ko.ALWAYS;

    Class<?> keyAs() default C05340ku.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    EnumC05330kp typing() default EnumC05330kp.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
