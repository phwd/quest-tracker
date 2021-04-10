package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

public abstract class VV {
    public abstract Class<?> A0J();

    public abstract String A0K();

    public abstract <A extends Annotation> A A0L(Class<A> cls);

    public abstract AnnotatedElement A0M();

    public abstract Type A0N();

    public final <A extends Annotation> boolean A0I(Class<A> cls) {
        if (A0L(cls) != null) {
            return true;
        }
        return false;
    }
}
