package com.fasterxml.jackson.databind.annotation;

import X.AbstractC01760gy;
import X.AnonymousClass0rJ;
import X.C04130pP;
import X.EnumC04110pJ;
import X.EnumC04120pK;
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
    Class<?> as() default C04130pP.class;

    Class<?> contentAs() default C04130pP.class;

    Class<? extends AnonymousClass0rJ<?, ?>> contentConverter() default AbstractC01760gy.class;

    Class<? extends JsonSerializer<?>> contentUsing() default JsonSerializer.None.class;

    Class<? extends AnonymousClass0rJ<?, ?>> converter() default AbstractC01760gy.class;

    @Deprecated
    EnumC04110pJ include() default EnumC04110pJ.ALWAYS;

    Class<?> keyAs() default C04130pP.class;

    Class<? extends JsonSerializer<?>> keyUsing() default JsonSerializer.None.class;

    EnumC04120pK typing() default EnumC04120pK.DYNAMIC;

    Class<? extends JsonSerializer<?>> using() default JsonSerializer.None.class;
}
