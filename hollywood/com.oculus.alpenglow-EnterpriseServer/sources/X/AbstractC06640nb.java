package X;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/* renamed from: X.0nb  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC06640nb {
    public abstract Class<?> A0K();

    public abstract String A0L();

    public abstract <A extends Annotation> A A0M(Class<A> cls);

    public abstract AnnotatedElement A0N();

    public abstract Type A0O();

    public AnonymousClass0aI A0I(C07030oc r3) {
        return r3.A03.A09(A0O(), r3);
    }

    public final <A extends Annotation> boolean A0J(Class<A> cls) {
        if (A0M(cls) != null) {
            return true;
        }
        return false;
    }
}
