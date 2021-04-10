package javax.annotation;

import X.Y7;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;

@TypeQualifierNickname
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Untainted(when = Y7.ALWAYS)
public @interface Detainted {
}
