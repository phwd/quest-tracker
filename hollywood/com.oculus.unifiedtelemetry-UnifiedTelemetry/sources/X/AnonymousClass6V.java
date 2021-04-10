package X;

import java.lang.reflect.Field;

/* renamed from: X.6V  reason: invalid class name */
public final class AnonymousClass6V<T> {
    public final Field A00;

    public final void A00(T t, Object obj) {
        try {
            this.A00.set(t, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public AnonymousClass6V(Field field) {
        this.A00 = field;
        field.setAccessible(true);
    }
}
