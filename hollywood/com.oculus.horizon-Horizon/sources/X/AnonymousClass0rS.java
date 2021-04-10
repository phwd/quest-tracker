package X;

import java.lang.reflect.Field;

/* renamed from: X.0rS  reason: invalid class name */
public final class AnonymousClass0rS<T> {
    public final Field A00;

    public final void A00(T t, Object obj) {
        try {
            this.A00.set(t, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public AnonymousClass0rS(Field field) {
        this.A00 = field;
        field.setAccessible(true);
    }
}
