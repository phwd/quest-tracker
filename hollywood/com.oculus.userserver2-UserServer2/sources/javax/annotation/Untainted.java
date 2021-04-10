package javax.annotation;

import X.Y7;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;

@TypeQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Untainted {
    Y7 when() default Y7.ALWAYS;
}
