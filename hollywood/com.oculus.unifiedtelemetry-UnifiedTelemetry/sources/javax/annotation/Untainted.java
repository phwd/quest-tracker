package javax.annotation;

import X.eO;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;

@TypeQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Untainted {
    eO when() default eO.ALWAYS;
}
