package X;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* renamed from: X.0wK  reason: invalid class name and case insensitive filesystem */
public class C08300wK implements PrivilegedExceptionAction<Unsafe> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.security.PrivilegedExceptionAction
    public final Unsafe run() throws Exception {
        Field[] declaredFields = Unsafe.class.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object obj = field.get(null);
            if (Unsafe.class.isInstance(obj)) {
                return Unsafe.class.cast(obj);
            }
        }
        throw new NoSuchFieldError("the Unsafe");
    }
}
