package X;

import java.lang.reflect.Field;

public final class UT {
    public final Field A00;

    public final void A00(Object obj, Object obj2) {
        try {
            this.A00.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        }
    }

    public UT(Field field) {
        this.A00 = field;
        field.setAccessible(true);
    }
}
