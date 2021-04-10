package X;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public abstract class h7 {
    public static final h7 A00;

    static {
        h7 ke;
        if (hU.A00 < 9) {
            ke = new KF();
        } else {
            ke = new KE();
        }
        A00 = ke;
    }

    public final void A00(AccessibleObject accessibleObject) {
        Field field;
        if (!(this instanceof KE)) {
            accessibleObject.setAccessible(true);
            return;
        }
        KE ke = (KE) this;
        Object obj = ke.A00;
        if (!(obj == null || (field = ke.A01) == null)) {
            try {
                KE.A02.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(obj, accessibleObject, Long.valueOf(((Long) KE.A02.getMethod("objectFieldOffset", Field.class).invoke(obj, field)).longValue()), true);
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
            throw new M4(sb.toString(), e);
        }
    }
}
