package sun.reflect.misc;

import java.lang.reflect.Modifier;
import sun.reflect.Reflection;

public final class ReflectUtil {
    public static void ensureMemberAccess(Class cls, Class cls2, Object obj, int i) {
        if (obj != null || !Modifier.isProtected(i)) {
            Reflection.ensureMemberAccess(cls, cls2, obj, i);
            return;
        }
        int i2 = (i & -5) | 1;
        Reflection.ensureMemberAccess(cls, cls2, obj, i2);
        try {
            Reflection.ensureMemberAccess(cls, cls2, obj, i2 & -2);
        } catch (IllegalAccessException e) {
            if (!isSubclassOf(cls, cls2)) {
                throw e;
            }
        }
    }

    private static boolean isSubclassOf(Class cls, Class cls2) {
        while (cls != null) {
            if (cls == cls2) {
                return true;
            }
            cls = cls.getSuperclass();
        }
        return false;
    }
}
