package X;

import java.io.Serializable;
import java.lang.reflect.Method;

public final class PC implements Serializable {
    public static final long serialVersionUID = 1;
    public Class[] args;
    public Class clazz;
    public String name;

    public PC(Method method) {
        this.clazz = method.getDeclaringClass();
        this.name = method.getName();
        this.args = method.getParameterTypes();
    }
}
