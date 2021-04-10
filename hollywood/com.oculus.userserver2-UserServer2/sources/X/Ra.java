package X;

import java.lang.reflect.Field;

public final class Ra<T> {
    public final Field A00;

    public final void A00(T t, Object obj) {
        try {
            this.A00.set(t, obj);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public Ra(Field field) {
        this.A00 = field;
        field.setAccessible(true);
    }
}
