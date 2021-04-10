package X;

import java.io.Serializable;
import java.lang.reflect.Method;

/* renamed from: X.0qD  reason: invalid class name */
public final class AnonymousClass0qD implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;
    public String name;

    public AnonymousClass0qD(Method method) {
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.args = method.getParameterTypes();
    }
}
