package javax.annotation;

import X.eO;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.meta.TypeQualifierNickname;

@TypeQualifierNickname
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Untainted(when = eO.MAYBE)
public @interface Tainted {
}
