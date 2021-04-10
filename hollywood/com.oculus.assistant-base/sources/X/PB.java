package X;

import java.io.Serializable;
import java.lang.reflect.Field;

public final class PB implements Serializable {
    public static final long serialVersionUID = 1;
    public Class clazz;
    public String name;

    public PB(Field field) {
        this.clazz = field.getDeclaringClass();
        this.name = field.getName();
    }
}
