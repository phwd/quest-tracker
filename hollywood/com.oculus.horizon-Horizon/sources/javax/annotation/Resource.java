package javax.annotation;

import X.EnumC08720xg;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {
    EnumC08720xg authenticationType() default EnumC08720xg.CONTAINER;

    String description() default "";

    String lookup() default "";

    String mappedName() default "";

    String name() default "";

    Class type() default Object.class;
}
