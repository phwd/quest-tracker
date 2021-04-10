package X;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* renamed from: X.0Zu  reason: invalid class name and case insensitive filesystem */
public final class C02480Zu implements AnonymousClass0n8, Serializable {
    public static final long serialVersionUID = 923268084968181479L;

    @Override // X.AnonymousClass0n8
    public final AnonymousClass0mY A2g(AnonymousClass0aI r4, C01260Fu r5, AbstractC06260mR r6) throws AnonymousClass0aG {
        Class cls = r4._class;
        if (cls == String.class || cls == Object.class) {
            if (cls == String.class) {
                return AnonymousClass0KL.A01;
            }
            if (cls == Object.class) {
                return AnonymousClass0KL.A00;
            }
            return new AnonymousClass0KL(cls);
        } else if (cls == UUID.class) {
            return new AnonymousClass0KK();
        } else {
            if (cls.isPrimitive()) {
                if (cls == Integer.TYPE) {
                    cls = Integer.class;
                } else if (cls == Long.TYPE) {
                    cls = Long.class;
                } else if (cls == Boolean.TYPE) {
                    cls = Boolean.class;
                } else if (cls == Double.TYPE) {
                    cls = Double.class;
                } else if (cls == Float.TYPE) {
                    cls = Float.class;
                } else if (cls == Byte.TYPE) {
                    cls = Byte.class;
                } else if (cls == Short.TYPE) {
                    cls = Short.class;
                } else if (cls == Character.TYPE) {
                    cls = Character.class;
                } else {
                    throw new IllegalArgumentException(AnonymousClass006.A07("Class ", cls.getName(), " is not a primitive type"));
                }
            }
            if (cls == Integer.class) {
                return new AnonymousClass0KS();
            }
            if (cls == Long.class) {
                return new AnonymousClass0KP();
            }
            if (cls == Date.class) {
                return new AnonymousClass0KW();
            }
            if (cls == Calendar.class) {
                return new AnonymousClass0KY();
            }
            if (cls == Boolean.class) {
                return new C01620Ka();
            }
            if (cls == Byte.class) {
                return new AnonymousClass0KZ();
            }
            if (cls == Character.class) {
                return new AnonymousClass0KX();
            }
            if (cls == Short.class) {
                return new AnonymousClass0KO();
            }
            if (cls == Float.class) {
                return new AnonymousClass0KT();
            }
            if (cls == Double.class) {
                return new AnonymousClass0KV();
            }
            if (cls == Locale.class) {
                return new AnonymousClass0KR();
            }
            return null;
        }
    }
}
