package X;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

public abstract class Wp implements q8, Serializable {
    @Deprecated
    public pN A02(WJ wj) {
        return null;
    }

    public jn A03(WJ wj) {
        return null;
    }

    public C0417hQ A06(WK wk) {
        return null;
    }

    public C0410hE A07(WK wk) {
        return null;
    }

    public VN A08(VV vv) {
        return null;
    }

    public VN A09(VV vv, VN vn) {
        return vn;
    }

    public VI<?> A0A(WK wk, VI<?> vi) {
        return vi;
    }

    public V2<?> A0B(WZ<?> wz, WK wk, AbstractC0224Wl wl) {
        return null;
    }

    public V2<?> A0C(WZ<?> wz, WJ wj, AbstractC0224Wl wl) {
        return null;
    }

    public V2<?> A0D(WZ<?> wz, WJ wj, AbstractC0224Wl wl) {
        return null;
    }

    public KI A0E(WJ wj) {
        return null;
    }

    public Boolean A0F(WK wk) {
        return null;
    }

    public Boolean A0G(WK wk) {
        return null;
    }

    public Boolean A0H(WK wk) {
        return null;
    }

    public Boolean A0I(WJ wj) {
        return null;
    }

    public Class<?> A0J(VV vv, AbstractC0224Wl wl) {
        return null;
    }

    public Class<?> A0K(VV vv, AbstractC0224Wl wl) {
        return null;
    }

    public Class<?> A0L(VV vv, AbstractC0224Wl wl) {
        return null;
    }

    public Class<?> A0M(WK wk) {
        return null;
    }

    public Object A0N(VV vv) {
        return null;
    }

    public Object A0O(VV vv) {
        return null;
    }

    public Object A0P(VV vv) {
        return null;
    }

    public Object A0Q(VV vv) {
        return null;
    }

    public Object A0R(WK wk) {
        return null;
    }

    public Object A0S(WK wk) {
        return null;
    }

    public Object A0T(WJ wj) {
        return null;
    }

    public Object A0U(WJ wj) {
        return null;
    }

    public String A0V(WK wk) {
        return null;
    }

    @Deprecated
    public String A0W(CD cd) {
        return null;
    }

    @Deprecated
    public String A0X(CD cd) {
        return null;
    }

    @Deprecated
    public String A0Y(AnonymousClass7P r2) {
        return null;
    }

    @Deprecated
    public String A0Z(AnonymousClass7P r2) {
        return null;
    }

    @Deprecated
    public String A0a(CC cc) {
        return null;
    }

    public List<V7> A0b(VV vv) {
        return null;
    }

    public boolean A0c(VV vv) {
        return false;
    }

    public boolean A0d(WJ wj) {
        return false;
    }

    public boolean A0e(AnonymousClass7P r2) {
        return false;
    }

    public boolean A0f(AnonymousClass7P r2) {
        return false;
    }

    public boolean A0g(AnonymousClass7P r2) {
        return false;
    }

    public boolean A0h(Annotation annotation) {
        return false;
    }

    public Class<?>[] A0i(VV vv) {
        return null;
    }

    public String[] A0j(VV vv) {
        return null;
    }

    public String[] A0k(WK wk) {
        return null;
    }

    public pN A01(VV vv) {
        if (vv instanceof WJ) {
            return A02((WJ) vv);
        }
        return null;
    }

    public C0417hQ A04(VV vv) {
        String A0a;
        if (vv instanceof CD) {
            A0a = A0W((CD) vv);
        } else if (vv instanceof AnonymousClass7P) {
            A0a = A0Y((AnonymousClass7P) vv);
        } else {
            if (vv instanceof CC) {
                A0a = A0a((CC) vv);
            }
            return null;
        }
        if (A0a != null) {
            if (A0a.length() == 0) {
                return C0417hQ.A01;
            }
            return new C0417hQ(A0a);
        }
        return null;
    }

    public C0417hQ A05(VV vv) {
        String A0Z;
        if (vv instanceof CD) {
            A0Z = A0X((CD) vv);
        } else {
            if (vv instanceof AnonymousClass7P) {
                A0Z = A0Z((AnonymousClass7P) vv);
            }
            return null;
        }
        if (A0Z != null) {
            if (A0Z.length() == 0) {
                return C0417hQ.A01;
            }
            return new C0417hQ(A0Z);
        }
        return null;
    }
}
