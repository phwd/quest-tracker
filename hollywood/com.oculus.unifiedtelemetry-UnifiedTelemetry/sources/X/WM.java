package X;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public final class WM implements AbstractC0265Yp, Serializable {
    public static final long serialVersionUID = 923268084968181479L;

    @Override // X.AbstractC0265Yp
    public final AbstractC0420hV A27(AbstractC0224Wl wl, AnonymousClass8M r5, jm jmVar) throws C0223Wj {
        Class cls = wl._class;
        if (cls == String.class || cls == Object.class) {
            if (cls == String.class) {
                return CL.A01;
            }
            if (cls == Object.class) {
                return CL.A00;
            }
            return new CL(cls);
        } else if (cls == UUID.class) {
            return new CK();
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
                    throw new IllegalArgumentException(AnonymousClass06.A05("Class ", cls.getName(), " is not a primitive type"));
                }
            }
            if (cls == Integer.class) {
                return new CX();
            }
            if (cls == Long.class) {
                return new CU();
            }
            if (cls == Date.class) {
                return new C0070Cd();
            }
            if (cls == Calendar.class) {
                return new C0071Cf();
            }
            if (cls == Boolean.class) {
                return new Ch();
            }
            if (cls == Byte.class) {
                return new C0072Cg();
            }
            if (cls == Character.class) {
                return new Ce();
            }
            if (cls == Short.class) {
                return new CO();
            }
            if (cls == Float.class) {
                return new CY();
            }
            if (cls == Double.class) {
                return new C0069Cc();
            }
            if (cls == Locale.class) {
                return new CV();
            }
            return null;
        }
    }
}
