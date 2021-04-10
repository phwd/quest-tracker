package X;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public final class KE extends h7 {
    public static Class A02;
    public final Object A00;
    public final Field A01;

    public KE() {
        Field field;
        Object obj = null;
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            A02 = cls;
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            obj = declaredField.get(null);
        } catch (Exception unused) {
        }
        this.A00 = obj;
        try {
            field = AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused2) {
            field = null;
        }
        this.A01 = field;
    }
}
