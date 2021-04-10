package javax.annotation;

import X.EnumC08670xH;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;

@TypeQualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Untainted {
    EnumC08670xH when() default EnumC08670xH.ALWAYS;
}
