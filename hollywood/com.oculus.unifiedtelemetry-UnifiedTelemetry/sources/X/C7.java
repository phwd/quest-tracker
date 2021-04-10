package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

public final class C7 extends WF implements Comparable<C7> {
    public VL<CC> A00;
    public VL<CD> A01;
    public VL<AnonymousClass7P> A02;
    public VL<AnonymousClass7P> A03;
    public final String A04;
    public final boolean A05;
    public final Wp A06;
    public final String A07;

    /* JADX WARN: Incorrect args count in method signature: (I[LX/VL<+LX/WJ;>;)LX/WI; */
    public static WI A00(C7 c7, int i, VL... vlArr) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        HashMap<Class<? extends Annotation>, Annotation> hashMap2;
        WI wi = vlArr[i].A01.A00;
        do {
            i++;
            if (i >= vlArr.length) {
                return wi;
            }
        } while (vlArr[i] == null);
        WI A002 = A00(c7, i, vlArr);
        if (wi == null || (hashMap = wi.A00) == null || hashMap.isEmpty()) {
            return A002;
        }
        if (A002 == null || (hashMap2 = A002.A00) == null || hashMap2.isEmpty()) {
            return wi;
        }
        HashMap hashMap3 = new HashMap();
        for (Annotation annotation : hashMap2.values()) {
            hashMap3.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : wi.A00.values()) {
            hashMap3.put(annotation2.annotationType(), annotation2);
        }
        return new WI(hashMap3);
    }

    public static <T> VL<T> A01(VL<T> vl) {
        VL<T> A042;
        if (vl == null) {
            return vl;
        }
        VL<T> vl2 = vl.A00;
        if (vl2 == null) {
            A042 = null;
        } else {
            VL<T> vl3 = vl2.A00;
            if (vl3 == null) {
                A042 = null;
            } else {
                VL<T> vl4 = vl3.A00;
                if (vl4 == null) {
                    A042 = null;
                } else {
                    A042 = vl4.A04();
                }
                if (vl3.A04) {
                    A042 = VL.A01(vl3, A042);
                }
            }
            if (vl2.A04) {
                A042 = VL.A01(vl2, A042);
            }
        }
        if (vl.A04) {
            return VL.A01(vl, A042);
        }
        return A042;
    }

    public static <T> VL<T> A02(VL<T> vl, VL<T> vl2) {
        if (vl == null) {
            return vl2;
        }
        if (vl2 == null) {
            return vl;
        }
        VL<T> vl3 = vl.A00;
        if (vl3 != null) {
            VL<T> vl4 = vl3.A00;
            if (vl4 != null) {
                VL<T> vl5 = vl4.A00;
                if (vl5 != null) {
                    vl2 = VL.A00(vl5, vl2);
                }
                vl2 = VL.A01(vl4, vl2);
            }
            vl2 = VL.A01(vl3, vl2);
        }
        return VL.A01(vl, vl2);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/VL<+LX/WJ;>;LX/VL<+LX/WJ;>;)LX/VL<+LX/WJ;>; */
    public static VL A03(C7 c7, VL vl, VL vl2) {
        while (vl != null) {
            String str = vl.A02;
            if (str != null && !str.equals(c7.A07)) {
                if (vl2 == null) {
                    vl2 = vl;
                } else {
                    String str2 = vl2.A02;
                    if (!str.equals(str2)) {
                        StringBuilder sb = new StringBuilder("Conflicting property name definitions: '");
                        sb.append(str2);
                        sb.append("' (for ");
                        sb.append((Object) vl2.A01);
                        sb.append(") vs '");
                        sb.append(vl.A02);
                        sb.append("' (for ");
                        sb.append((Object) vl.A01);
                        sb.append(")");
                        throw new IllegalStateException(sb.toString());
                    }
                }
            }
            vl = vl.A00;
        }
        return vl2;
    }

    private final <T> T A04(VK<T> vk) {
        VL<AnonymousClass7P> vl;
        T t = null;
        if (this.A06 == null) {
            return null;
        }
        if (this.A05) {
            vl = this.A02;
        } else {
            VL<CC> vl2 = this.A00;
            if (vl2 != null && (t = vk.A5k(vl2.A01)) != null) {
                return t;
            }
            vl = this.A03;
        }
        if (vl != null && (t = vk.A5k(vl.A01)) != null) {
            return t;
        }
        VL<CD> vl3 = this.A01;
        if (vl3 != null) {
            return vk.A5k(vl3.A01);
        }
        return t;
    }

    public static <T> boolean A05(VL<T> vl) {
        while (vl != null) {
            if (vl.A02 != null && vl.A02.length() > 0) {
                return true;
            }
            vl = vl.A00;
        }
        return false;
    }

    @Override // X.WF
    public final jn A06() {
        return (jn) A04(new WD(this));
    }

    @Override // X.WF
    public final C0417hQ A07() {
        if (!this.A05) {
            A09();
            return null;
        } else if (A0A() != null) {
            return null;
        } else {
            A08();
            return null;
        }
    }

    @Override // X.WF
    public final CD A08() {
        VL<CD> vl = this.A01;
        if (vl == null) {
            return null;
        }
        T t = vl.A01;
        for (VL<T> vl2 = vl.A00; vl2 != null; vl2 = vl2.A00) {
            T t2 = vl2.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass06.A08("Multiple fields representing property \"", A0D(), "\": ", AnonymousClass06.A05(t.A0O().getName(), "#", t.A0K()), " vs ", AnonymousClass06.A05(t2.A0O().getName(), "#", t2.A0K())));
        }
        return t;
    }

    @Override // X.WF
    public final AnonymousClass7P A0A() {
        VL<AnonymousClass7P> vl = this.A02;
        if (vl == null) {
            return null;
        }
        T t = vl.A01;
        for (VL<T> vl2 = vl.A00; vl2 != null; vl2 = vl2.A00) {
            T t2 = vl2.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass06.A08("Conflicting getter definitions for property \"", A0D(), "\": ", t.A0Y(), " vs ", t2.A0Y()));
        }
        return t;
    }

    @Override // X.WF
    public final AnonymousClass7P A0B() {
        VL<AnonymousClass7P> vl = this.A03;
        if (vl == null) {
            return null;
        }
        T t = vl.A01;
        for (VL<T> vl2 = vl.A00; vl2 != null; vl2 = vl2.A00) {
            T t2 = vl2.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass06.A08("Conflicting setter definitions for property \"", A0D(), "\": ", t.A0Y(), " vs ", t2.A0Y()));
        }
        return t;
    }

    @Override // X.WF
    public final CC A0C() {
        VL vl = this.A00;
        if (vl == null) {
            return null;
        }
        do {
            T t = vl.A01;
            if (t._owner instanceof AnonymousClass7Q) {
                return t;
            }
            vl = vl.A00;
        } while (vl != null);
        return vl.A01;
    }

    @Override // X.WF
    public final boolean A0E() {
        Boolean bool = (Boolean) A04(new WC(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.WF
    public final boolean A0F() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final boolean A0G() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final boolean A0H() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final boolean A0I() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.WF
    public final Class<?>[] A0J() {
        return (Class[]) A04(new WE(this));
    }

    public final void A0K(C7 c7) {
        this.A01 = A02(this.A01, c7.A01);
        this.A00 = A02(this.A00, c7.A00);
        this.A02 = A02(this.A02, c7.A02);
        this.A03 = A02(this.A03, c7.A03);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(C7 c7) {
        C7 c72 = c7;
        if (this.A00 != null) {
            if (c72.A00 == null) {
                return -1;
            }
        } else if (c72.A00 != null) {
            return 1;
        }
        return A0D().compareTo(c72.A0D());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[Property '");
        sb.append(this.A07);
        sb.append("'; ctors: ");
        sb.append(this.A00);
        sb.append(", field(s): ");
        sb.append(this.A01);
        sb.append(", getter(s): ");
        sb.append(this.A02);
        sb.append(", setter(s): ");
        sb.append(this.A03);
        sb.append("]");
        return sb.toString();
    }

    @Override // X.WF
    public final WJ A09() {
        CC A0C = A0C();
        if (A0C != null) {
            return A0C;
        }
        AnonymousClass7P A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.WF
    public final String A0D() {
        return this.A07;
    }

    public C7(C7 c7, String str) {
        this.A04 = c7.A04;
        this.A07 = str;
        this.A06 = c7.A06;
        this.A01 = c7.A01;
        this.A00 = c7.A00;
        this.A02 = c7.A02;
        this.A03 = c7.A03;
        this.A05 = c7.A05;
    }

    public C7(String str, Wp wp) {
        this.A04 = str;
        this.A07 = str;
        this.A06 = wp;
        this.A05 = false;
    }
}
