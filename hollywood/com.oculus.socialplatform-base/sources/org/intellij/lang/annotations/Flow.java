package org.intellij.lang.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface Flow {
    String source() default "The method argument (if parameter was annotated) or this container (if instance method was annotated)";

    String target() default "This container (if the parameter was annotated) or the return value (if instance method was annotated)";
}
