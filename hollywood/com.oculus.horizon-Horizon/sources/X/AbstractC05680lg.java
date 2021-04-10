package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/* renamed from: X.0lg  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC05680lg {
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
