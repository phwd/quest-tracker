package X;

import java.io.IOException;

public abstract class V4 {
    public abstract EnumC0463pg A03();

    public abstract V4 A04(AbstractC0227Wo wo);

    public abstract V3 A05();

    public abstract Class<?> A06();

    public abstract Object A07(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public abstract Object A08(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public abstract Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public abstract Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0;

    public abstract String A0B();

    public static Object A02(AbstractC0232Ww ww, AbstractC0224Wl wl) throws IOException, q0 {
        Class<?> cls = wl._class;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == null) {
            return null;
        }
        int i = V5.A00[A0Z.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5 || !cls.isAssignableFrom(Boolean.class)) {
                            return null;
                        }
                        return Boolean.FALSE;
                    } else if (cls.isAssignableFrom(Boolean.class)) {
                        return Boolean.TRUE;
                    } else {
                        return null;
                    }
                } else if (cls.isAssignableFrom(Double.class)) {
                    return Double.valueOf(ww.A0J());
                } else {
                    return null;
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(ww.A0L());
            } else {
                return null;
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return ww.A0d();
        } else {
            return null;
        }
    }
}
