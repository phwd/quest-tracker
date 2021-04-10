package X;

import java.io.IOException;

/* renamed from: X.0qa  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC04520qa {
    public abstract EnumC03570nq A03();

    public abstract AbstractC04520qa A04(AbstractC02220iI v);

    public abstract AbstractC04530qb A05();

    public abstract Class<?> A06();

    public abstract Object A07(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public abstract Object A08(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public abstract Object A09(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public abstract Object A0A(AbstractC02280iQ v, AbstractC02210iH v2) throws IOException, C03620oC;

    public abstract String A0B();

    public static Object A02(AbstractC02280iQ r4, AbstractC02190iF r5) throws IOException, C03620oC {
        Class<?> cls = r5._class;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == null) {
            return null;
        }
        int i = AnonymousClass0qZ.A00[A0i.ordinal()];
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
                    return Double.valueOf(r4.A0R());
                } else {
                    return null;
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(r4.A0T());
            } else {
                return null;
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return r4.A0m();
        } else {
            return null;
        }
    }
}
