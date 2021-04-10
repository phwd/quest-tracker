package X;

import java.io.Serializable;
import java.lang.reflect.Field;

/* renamed from: X.0qC  reason: invalid class name */
public final class AnonymousClass0qC implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?> clazz;
    public String name;

    public AnonymousClass0qC(Field field) {
        this.clazz = field.getDeclaringClass();
        this.name = field.getName();
    }
}
