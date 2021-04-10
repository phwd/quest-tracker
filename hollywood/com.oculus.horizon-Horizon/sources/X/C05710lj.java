package X;

import java.io.Serializable;
import java.lang.reflect.Method;

/* renamed from: X.0lj  reason: invalid class name and case insensitive filesystem */
public final class C05710lj implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;
    public String name;

    public C05710lj(Method method) {
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.args = method.getParameterTypes();
    }
}
