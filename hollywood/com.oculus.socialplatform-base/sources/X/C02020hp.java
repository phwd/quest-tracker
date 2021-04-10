package X;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* renamed from: X.0hp  reason: invalid class name and case insensitive filesystem */
public final class C02020hp implements AbstractC04270ph, Serializable {
    public static final long serialVersionUID = 923268084968181479L;

    @Override // X.AbstractC04270ph
    public final AnonymousClass0p6 A35(AbstractC02190iF r4, AnonymousClass0HU r5, AbstractC04010oz r6) throws C02180iD {
        Class cls = r4._class;
        if (cls == String.class || cls == Object.class) {
            if (cls == String.class) {
                return AnonymousClass0PC.A01;
            }
            if (cls == Object.class) {
                return AnonymousClass0PC.A00;
            }
            return new AnonymousClass0PC(cls);
        } else if (cls == UUID.class) {
            return new AnonymousClass0PB();
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
                    throw new IllegalArgumentException(AnonymousClass006.A09("Class ", cls.getName(), " is not a primitive type"));
                }
            }
            if (cls == Integer.class) {
                return new C01030Pj();
            }
            if (cls == Long.class) {
                return new C01010Pc();
            }
            if (cls == Date.class) {
                return new AnonymousClass0Pp();
            }
            if (cls == Calendar.class) {
                return new AnonymousClass0Qu();
            }
            if (cls == Boolean.class) {
                return new AnonymousClass0RI();
            }
            if (cls == Byte.class) {
                return new AnonymousClass0Qy();
            }
            if (cls == Character.class) {
                return new AnonymousClass0Qi();
            }
            if (cls == Short.class) {
                return new AnonymousClass0PX();
            }
            if (cls == Float.class) {
                return new C01040Pk();
            }
            if (cls == Double.class) {
                return new AnonymousClass0Pn();
            }
            if (cls == Locale.class) {
                return new C01020Pd();
            }
            return null;
        }
    }
}
