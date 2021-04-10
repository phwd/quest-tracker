package javax.annotation;

import X.eO;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;

@Nonnegative(when = eO.UNKNOWN)
@TypeQualifierNickname
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Signed {
}
