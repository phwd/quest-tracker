package X;

import java.io.IOException;

/* renamed from: X.0m9  reason: invalid class name */
public abstract class AnonymousClass0m9 {
    public abstract EnumC04710jK A03();

    public abstract AnonymousClass0m9 A04(AbstractC04030gh v);

    public abstract AbstractC05940mA A05();

    public abstract Class<?> A06();

    public abstract Object A07(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public abstract Object A08(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public abstract Object A09(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public abstract Object A0A(AbstractC04100gp v, AbstractC04020gg v2) throws IOException, AnonymousClass0jg;

    public abstract String A0B();

    public static Object A02(AbstractC04100gp r4, AbstractC04000gb r5) throws IOException, AnonymousClass0jg {
        Class<?> cls = r5._class;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == null) {
            return null;
        }
        int i = C05930m8.A00[A0a.ordinal()];
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
                    return Double.valueOf(r4.A0K());
                } else {
                    return null;
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(r4.A0M());
            } else {
                return null;
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return r4.A0e();
        } else {
            return null;
        }
    }
}
