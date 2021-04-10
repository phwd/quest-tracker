package com.fasterxml.jackson.databind.annotation;

import X.AbstractC01760gy;
import X.AnonymousClass0iA;
import X.AnonymousClass0p6;
import X.AnonymousClass0rJ;
import X.C04130pP;
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
    Class<?> as() default C04130pP.class;

    Class<?> builder() default C04130pP.class;

    Class<?> contentAs() default C04130pP.class;

    Class<? extends AnonymousClass0rJ<?, ?>> contentConverter() default AbstractC01760gy.class;

    Class<? extends JsonDeserializer<?>> contentUsing() default JsonDeserializer.None.class;

    Class<? extends AnonymousClass0rJ<?, ?>> converter() default AbstractC01760gy.class;

    Class<?> keyAs() default C04130pP.class;

    Class<? extends AnonymousClass0p6> keyUsing() default AnonymousClass0iA.class;

    Class<? extends JsonDeserializer<?>> using() default JsonDeserializer.None.class;
}
