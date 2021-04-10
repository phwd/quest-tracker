package X;

import java.lang.reflect.Field;

/* renamed from: X.0tV  reason: invalid class name */
public final class AnonymousClass0tV<T> {
    public final Field A00;

    public final void A00(T t, Object obj) {
        try {
            this.A00.set(t, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public AnonymousClass0tV(Field field) {
        this.A00 = field;
        field.setAccessible(true);
    }
}
