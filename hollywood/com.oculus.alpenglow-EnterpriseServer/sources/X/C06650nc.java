package X;

import java.io.Serializable;
import java.lang.reflect.Constructor;

/* renamed from: X.0nc  reason: invalid class name and case insensitive filesystem */
public final class C06650nc implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;

    public C06650nc(Constructor<?> constructor) {
        this.clazz = constructor.getDeclaringClass();
        this.args = constructor.getParameterTypes();
    }
}
