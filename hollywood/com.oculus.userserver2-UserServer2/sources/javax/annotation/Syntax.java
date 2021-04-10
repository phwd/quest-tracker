package javax.annotation;

import X.Y7;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;

@TypeQualifier(applicableTo = CharSequence.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Syntax {
    String value();

    Y7 when() default Y7.ALWAYS;
}
