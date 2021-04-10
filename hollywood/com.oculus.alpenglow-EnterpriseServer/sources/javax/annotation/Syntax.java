package javax.annotation;

import X.EnumC07320pB;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifier;

@TypeQualifier(applicableTo = CharSequence.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Syntax {
    String value();

    EnumC07320pB when() default EnumC07320pB.ALWAYS;
}
