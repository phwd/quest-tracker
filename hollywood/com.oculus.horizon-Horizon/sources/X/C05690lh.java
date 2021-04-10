package X;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/* renamed from: X.0lh  reason: invalid class name and case insensitive filesystem */
public final class C05690lh implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;

    public C05690lh(Constructor<?> constructor) {
        this.clazz = constructor.getDeclaringClass();
        this.args = constructor.getParameterTypes();
    }
}
