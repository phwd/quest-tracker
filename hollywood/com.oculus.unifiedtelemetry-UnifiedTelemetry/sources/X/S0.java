package X;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public final class S0 extends AbstractC0448kW {
    public static Class A02;
    public final Object A00;
    public final Field A01;

    @Override // X.AbstractC0448kW
    public final void A00(AccessibleObject accessibleObject) {
        Field field;
        Object obj = this.A00;
        if (!(obj == null || (field = this.A01) == null)) {
            try {
                A02.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(obj, accessibleObject, Long.valueOf(((Long) A02.getMethod("objectFieldOffset", Field.class).invoke(obj, field)).longValue()), true);
                return;
            } catch (Exception unused) {
            }
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e) {
            StringBuilder sb = new StringBuilder("Gson couldn't modify fields for ");
            sb.append(accessibleObject);
            sb.append("\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.");
            throw new U4(sb.toString(), e);
        }
    }

    public S0() {
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
