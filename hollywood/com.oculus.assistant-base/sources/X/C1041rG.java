package X;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/* renamed from: X.rG  reason: case insensitive filesystem */
public final class C1041rG implements Oh, Serializable {
    public static final long serialVersionUID = 923268084968181479L;

    @Override // X.Oh
    public final OD A20(AbstractC1024qt qtVar, AnonymousClass2I r5, O4 o4) {
        Class cls = qtVar._class;
        if (cls == String.class || cls == Object.class) {
            if (cls == String.class) {
                return TC.A01;
            }
            if (cls == Object.class) {
                return TC.A00;
            }
            return new TC(cls);
        } else if (cls == UUID.class) {
            return new TB();
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
                    throw new IllegalArgumentException(AnonymousClass08.A05("Class ", cls.getName(), " is not a primitive type"));
                }
            }
            if (cls == Integer.class) {
                return new TI();
            }
            if (cls == Long.class) {
                return new TG();
            }
            if (cls == Date.class) {
                return new TM();
            }
            if (cls == Calendar.class) {
                return new TO();
            }
            if (cls == Boolean.class) {
                return new TQ();
            }
            if (cls == Byte.class) {
                return new TP();
            }
            if (cls == Character.class) {
                return new TN();
            }
            if (cls == Short.class) {
                return new TF();
            }
            if (cls == Float.class) {
                return new TJ();
            }
            if (cls == Double.class) {
                return new TL();
            }
            if (cls == Locale.class) {
                return new TH();
            }
            return null;
        }
    }
}
