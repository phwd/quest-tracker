package X;

import java.io.Serializable;
import java.lang.reflect.Method;

/* renamed from: X.6M  reason: invalid class name */
public final class AnonymousClass6M implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;
    public String name;

    public AnonymousClass6M(Method method) {
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.args = method.getParameterTypes();
    }
}
