package X;

import java.io.Serializable;
import java.lang.reflect.Field;

/* renamed from: X.0nd  reason: invalid class name and case insensitive filesystem */
public final class C06660nd implements Serializable {
    public static final long serialVersionUID = 1;
    public Class<?> clazz;
    public String name;

    public C06660nd(Field field) {
        this.clazz = field.getDeclaringClass();
        this.name = field.getName();
    }
}
