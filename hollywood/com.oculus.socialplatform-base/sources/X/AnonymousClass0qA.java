package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/* renamed from: X.0qA  reason: invalid class name */
public abstract class AnonymousClass0qA {
    public abstract Class<?> A0K();

    public abstract String A0L();

    public abstract <A extends Annotation> A A0M(Class<A> cls);

    public abstract AnnotatedElement A0N();

    public abstract Type A0O();

    public AbstractC02190iF A0I(AnonymousClass0r8 r3) {
        return r3.A03.A09(A0O(), r3);
    }

    public final <A extends Annotation> boolean A0J(Class<A> cls) {
        if (A0M(cls) != null) {
            return true;
        }
        return false;
    }
}
