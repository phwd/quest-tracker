package X;

import java.io.Serializable;
import java.lang.reflect.Constructor;

public final class PA implements Serializable {
    public static final long serialVersionUID = 1;
    public Class[] args;
    public Class clazz;

    public PA(Constructor constructor) {
        this.clazz = constructor.getDeclaringClass();
        this.args = constructor.getParameterTypes();
    }
}
