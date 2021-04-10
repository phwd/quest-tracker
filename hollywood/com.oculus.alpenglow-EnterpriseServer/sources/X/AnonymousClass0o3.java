package X;

import java.io.IOException;

/* renamed from: X.0o3  reason: invalid class name */
public abstract class AnonymousClass0o3 {
    public abstract EnumC05770lI A03();

    public abstract AnonymousClass0o3 A04(AbstractC02580aL v);

    public abstract AnonymousClass0o4 A05();

    public abstract Class<?> A06();

    public abstract Object A07(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public abstract Object A08(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public abstract Object A09(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public abstract Object A0A(AnonymousClass0aT v, AbstractC02570aK v2) throws IOException, C05910ld;

    public abstract String A0B();

    public static Object A02(AnonymousClass0aT r4, AnonymousClass0aI r5) throws IOException, C05910ld {
        Class<?> cls = r5._class;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == null) {
            return null;
        }
        int i = AnonymousClass0o2.A00[A0G.ordinal()];
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
                    return Double.valueOf(r4.A03());
                } else {
                    return null;
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(r4.A06());
            } else {
                return null;
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return r4.A0P();
        } else {
            return null;
        }
    }
}
