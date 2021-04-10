package X;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/* renamed from: X.0qB  reason: invalid class name */
public final class AnonymousClass0qB implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;

    public AnonymousClass0qB(Constructor<?> constructor) {
        this.clazz = constructor.getDeclaringClass();
        this.args = constructor.getParameterTypes();
    }
}
