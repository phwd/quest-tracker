package X;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* renamed from: X.0gC  reason: invalid class name and case insensitive filesystem */
public final class C03830gC implements AnonymousClass0lD, Serializable {
    public static final long serialVersionUID = 923268084968181479L;

    @Override // X.AnonymousClass0lD
    public final AbstractC05240kb A2k(AbstractC04000gb r4, AnonymousClass08X r5, AbstractC05180kU r6) throws C03990gZ {
        Class cls = r4._class;
        if (cls == String.class || cls == Object.class) {
            if (cls == String.class) {
                return C00780Gh.A01;
            }
            if (cls == Object.class) {
                return C00780Gh.A00;
            }
            return new C00780Gh(cls);
        } else if (cls == UUID.class) {
            return new C00770Gf();
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
                return new C00830Gp();
            }
            if (cls == Long.class) {
                return new AnonymousClass0Gl();
            }
            if (cls == Date.class) {
                return new AnonymousClass0Gz();
            }
            if (cls == Calendar.class) {
                return new AnonymousClass0H2();
            }
            if (cls == Boolean.class) {
                return new AnonymousClass0H4();
            }
            if (cls == Byte.class) {
                return new AnonymousClass0H3();
            }
            if (cls == Character.class) {
                return new AnonymousClass0H1();
            }
            if (cls == Short.class) {
                return new C00810Gk();
            }
            if (cls == Float.class) {
                return new C00870Gw();
            }
            if (cls == Double.class) {
                return new AnonymousClass0Gy();
            }
            if (cls == Locale.class) {
                return new C00820Gm();
            }
            return null;
        }
    }
}
