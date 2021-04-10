package X;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public final class VU implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?>[] args;
    public Class<?> clazz;

    public VU(Constructor<?> constructor) {
        this.clazz = constructor.getDeclaringClass();
        this.args = constructor.getParameterTypes();
    }
}
