package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.0Ot  reason: invalid class name */
public final class AnonymousClass0Ot extends AbstractC01960hi implements Comparable<AnonymousClass0Ot> {
    public AnonymousClass0qL<AnonymousClass0Ox> A00;
    public AnonymousClass0qL<AnonymousClass0Oy> A01;
    public AnonymousClass0qL<AnonymousClass0Cr> A02;
    public AnonymousClass0qL<AnonymousClass0Cr> A03;
    public final String A04;
    public final boolean A05;
    public final AbstractC02230iJ A06;
    public final String A07;

    /* JADX WARN: Incorrect args count in method signature: (I[LX/0qL<+LX/0hm;>;)LX/0hl; */
    public static AnonymousClass0hl A00(AnonymousClass0Ot r5, int i, AnonymousClass0qL... r7) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        HashMap<Class<? extends Annotation>, Annotation> hashMap2;
        AnonymousClass0hl r4 = r7[i].A01.A00;
        do {
            i++;
            if (i >= r7.length) {
                return r4;
            }
        } while (r7[i] == null);
        AnonymousClass0hl A002 = A00(r5, i, r7);
        if (r4 == null || (hashMap = r4.A00) == null || hashMap.isEmpty()) {
            return A002;
        }
        if (A002 == null || (hashMap2 = A002.A00) == null || hashMap2.isEmpty()) {
            return r4;
        }
        HashMap hashMap3 = new HashMap();
        for (Annotation annotation : hashMap2.values()) {
            hashMap3.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : r4.A00.values()) {
            hashMap3.put(annotation2.annotationType(), annotation2);
        }
        return new AnonymousClass0hl(hashMap3);
    }

    public static <T> AnonymousClass0qL<T> A01(AnonymousClass0qL<T> r4) {
        AnonymousClass0qL<T> A042;
        if (r4 == null) {
            return r4;
        }
        AnonymousClass0qL<T> r3 = r4.A00;
        if (r3 == null) {
            A042 = null;
        } else {
            AnonymousClass0qL<T> r2 = r3.A00;
            if (r2 == null) {
                A042 = null;
            } else {
                AnonymousClass0qL<T> r0 = r2.A00;
                if (r0 == null) {
                    A042 = null;
                } else {
                    A042 = r0.A04();
                }
                if (r2.A04) {
                    A042 = AnonymousClass0qL.A01(r2, A042);
                }
            }
            if (r3.A04) {
                A042 = AnonymousClass0qL.A01(r3, A042);
            }
        }
        if (r4.A04) {
            return AnonymousClass0qL.A01(r4, A042);
        }
        return A042;
    }

    public static <T> AnonymousClass0qL<T> A02(AnonymousClass0qL<T> r3, AnonymousClass0qL<T> r4) {
        if (r3 == null) {
            return r4;
        }
        if (r4 == null) {
            return r3;
        }
        AnonymousClass0qL<T> r2 = r3.A00;
        if (r2 != null) {
            AnonymousClass0qL<T> r1 = r2.A00;
            if (r1 != null) {
                AnonymousClass0qL<T> r0 = r1.A00;
                if (r0 != null) {
                    r4 = AnonymousClass0qL.A00(r0, r4);
                }
                r4 = AnonymousClass0qL.A01(r1, r4);
            }
            r4 = AnonymousClass0qL.A01(r2, r4);
        }
        return AnonymousClass0qL.A01(r3, r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0qL<+LX/0hm;>;LX/0qL<+LX/0hm;>;)LX/0qL<+LX/0hm;>; */
    public static AnonymousClass0qL A03(AnonymousClass0Ot r3, AnonymousClass0qL r4, AnonymousClass0qL r5) {
        while (r4 != null) {
            String str = r4.A02;
            if (str != null && !str.equals(r3.A07)) {
                if (r5 == null) {
                    r5 = r4;
                } else {
                    String str2 = r5.A02;
                    if (!str.equals(str2)) {
                        StringBuilder sb = new StringBuilder("Conflicting property name definitions: '");
                        sb.append(str2);
                        sb.append("' (for ");
                        sb.append((Object) r5.A01);
                        sb.append(") vs '");
                        sb.append(r4.A02);
                        sb.append("' (for ");
                        sb.append((Object) r4.A01);
                        sb.append(")");
                        throw new IllegalStateException(sb.toString());
                    }
                }
            }
            r4 = r4.A00;
        }
        return r5;
    }

    private final <T> T A04(AnonymousClass0qM<T> r3) {
        AnonymousClass0qL<AnonymousClass0Cr> r0;
        T t = null;
        if (this.A06 == null) {
            return null;
        }
        if (this.A05) {
            r0 = this.A02;
        } else {
            AnonymousClass0qL<AnonymousClass0Ox> r02 = this.A00;
            if (r02 != null && (t = r3.AB7(r02.A01)) != null) {
                return t;
            }
            r0 = this.A03;
        }
        if (r0 != null && (t = r3.AB7(r0.A01)) != null) {
            return t;
        }
        AnonymousClass0qL<AnonymousClass0Oy> r03 = this.A01;
        if (r03 != null) {
            return r3.AB7(r03.A01);
        }
        return t;
    }

    public static <T> boolean A05(AnonymousClass0qL<T> r1) {
        while (r1 != null) {
            if (r1.A02 != null && r1.A02.length() > 0) {
                return true;
            }
            r1 = r1.A00;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final C04000oy A06() {
        return (C04000oy) A04(new C01940hg(this));
    }

    @Override // X.AbstractC01960hi
    public final C04070pB A07() {
        if (this.A05) {
            A09();
            return null;
        }
        A0A();
        return null;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Oy A08() {
        AnonymousClass0qL<AnonymousClass0Oy> r0 = this.A01;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (AnonymousClass0qL<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0D("Multiple fields representing property \"", A0E(), "\": ", t.A0S(), " vs ", t2.A0S()));
        }
        return t;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Cr A0B() {
        AnonymousClass0qL<AnonymousClass0Cr> r0 = this.A02;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (AnonymousClass0qL<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0D("Conflicting getter definitions for property \"", A0E(), "\": ", t.A0b(), " vs ", t2.A0b()));
        }
        return t;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Cr A0C() {
        AnonymousClass0qL<AnonymousClass0Cr> r0 = this.A03;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (AnonymousClass0qL<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0D("Conflicting setter definitions for property \"", A0E(), "\": ", t.A0b(), " vs ", t2.A0b()));
        }
        return t;
    }

    @Override // X.AbstractC01960hi
    public final AnonymousClass0Ox A0D() {
        AnonymousClass0qL r3 = this.A00;
        if (r3 == null) {
            return null;
        }
        do {
            T t = r3.A01;
            if (t._owner instanceof AnonymousClass0Cs) {
                return t;
            }
            r3 = r3.A00;
        } while (r3 != null);
        return r3.A01;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0F() {
        Boolean bool = (Boolean) A04(new C01920he(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0G() {
        Boolean bool = (Boolean) A04(new C01930hf(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0H() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0I() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0J() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0K() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final boolean A0L() {
        if (A05(this.A01) || A05(this.A02) || A05(this.A03) || A05(this.A00)) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC01960hi
    public final Class<?>[] A0M() {
        return (Class[]) A04(new C01950hh(this));
    }

    public final void A0N(AnonymousClass0Ot r3) {
        this.A01 = A02(this.A01, r3.A01);
        this.A00 = A02(this.A00, r3.A00);
        this.A02 = A02(this.A02, r3.A02);
        this.A03 = A02(this.A03, r3.A03);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(AnonymousClass0Ot r3) {
        AnonymousClass0Ot r32 = r3;
        if (this.A00 != null) {
            if (r32.A00 == null) {
                return -1;
            }
        } else if (r32.A00 != null) {
            return 1;
        }
        return A0E().compareTo(r32.A0E());
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

    @Override // X.AbstractC01960hi
    public final AbstractC01990hm A09() {
        AnonymousClass0Cr A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AbstractC01960hi
    public final AbstractC01990hm A0A() {
        AnonymousClass0Ox A0D = A0D();
        if (A0D != null) {
            return A0D;
        }
        AnonymousClass0Cr A0C = A0C();
        if (A0C == null) {
            return A08();
        }
        return A0C;
    }

    @Override // X.AbstractC01960hi
    public final String A0E() {
        return this.A07;
    }

    public AnonymousClass0Ot(AnonymousClass0Ot r2, String str) {
        this.A04 = r2.A04;
        this.A07 = str;
        this.A06 = r2.A06;
        this.A01 = r2.A01;
        this.A00 = r2.A00;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
        this.A05 = r2.A05;
    }

    public AnonymousClass0Ot(String str, AbstractC02230iJ r2, boolean z) {
        this.A04 = str;
        this.A07 = str;
        this.A06 = r2;
        this.A05 = z;
    }
}
