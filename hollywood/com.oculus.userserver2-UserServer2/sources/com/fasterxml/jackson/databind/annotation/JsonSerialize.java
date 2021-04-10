package com.fasterxml.jackson.databind.annotation;

import X.AnonymousClass3o;
import X.AnonymousClass80;
import X.C00093c;
import X.EnumC00103h;
import X.Qt;
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
    Class<?> as() default C00093c.class;

    Class<?> contentAs() default C00093c.class;

    Class<? extends AnonymousClass80<?, ?>> contentConverter() default Qt.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Class<? extends AnonymousClass80<?, ?>> converter() default Qt.class;

    @Deprecated
    AnonymousClass3o include() default AnonymousClass3o.ALWAYS;

    Class<?> keyAs() default C00093c.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    EnumC00103h typing() default EnumC00103h.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
