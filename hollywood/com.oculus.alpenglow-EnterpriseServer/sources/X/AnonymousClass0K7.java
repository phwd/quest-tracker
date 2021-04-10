package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.0K7  reason: invalid class name */
public final class AnonymousClass0K7 extends AbstractC02410Zn implements Comparable<AnonymousClass0K7> {
    public C06740nm<AnonymousClass0KB> A00;
    public C06740nm<AnonymousClass0KC> A01;
    public C06740nm<AnonymousClass0EC> A02;
    public C06740nm<AnonymousClass0EC> A03;
    public final String A04;
    public final boolean A05;
    public final AbstractC02590aM A06;
    public final String A07;

    /* JADX WARN: Incorrect args count in method signature: (I[LX/0nm<+LX/0Zr;>;)LX/0Zq; */
    public static C02440Zq A00(AnonymousClass0K7 r5, int i, C06740nm... r7) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        HashMap<Class<? extends Annotation>, Annotation> hashMap2;
        C02440Zq r4 = r7[i].A01.A00;
        do {
            i++;
            if (i >= r7.length) {
                return r4;
            }
        } while (r7[i] == null);
        C02440Zq A002 = A00(r5, i, r7);
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
        return new C02440Zq(hashMap3);
    }

    public static <T> C06740nm<T> A01(C06740nm<T> r4) {
        C06740nm<T> A042;
        if (r4 == null) {
            return r4;
        }
        C06740nm<T> r3 = r4.A00;
        if (r3 == null) {
            A042 = null;
        } else {
            C06740nm<T> r2 = r3.A00;
            if (r2 == null) {
                A042 = null;
            } else {
                C06740nm<T> r0 = r2.A00;
                if (r0 == null) {
                    A042 = null;
                } else {
                    A042 = r0.A04();
                }
                if (r2.A04) {
                    A042 = C06740nm.A01(r2, A042);
                }
            }
            if (r3.A04) {
                A042 = C06740nm.A01(r3, A042);
            }
        }
        if (r4.A04) {
            return C06740nm.A01(r4, A042);
        }
        return A042;
    }

    public static <T> C06740nm<T> A02(C06740nm<T> r3, C06740nm<T> r4) {
        if (r3 == null) {
            return r4;
        }
        if (r4 == null) {
            return r3;
        }
        C06740nm<T> r2 = r3.A00;
        if (r2 != null) {
            C06740nm<T> r1 = r2.A00;
            if (r1 != null) {
                C06740nm<T> r0 = r1.A00;
                if (r0 != null) {
                    r4 = C06740nm.A00(r0, r4);
                }
                r4 = C06740nm.A01(r1, r4);
            }
            r4 = C06740nm.A01(r2, r4);
        }
        return C06740nm.A01(r3, r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0nm<+LX/0Zr;>;LX/0nm<+LX/0Zr;>;)LX/0nm<+LX/0Zr;>; */
    public static C06740nm A03(AnonymousClass0K7 r3, C06740nm r4, C06740nm r5) {
        while (r4 != null) {
            String str = r4.A02;
            if (str != null && !str.equals(r3.A07)) {
                if (r5 == null) {
                    r5 = r4;
                } else {
                    String str2 = r5.A02;
                    if (!str.equals(str2)) {
                        throw new IllegalStateException("Conflicting property name definitions: '" + str2 + "' (for " + ((Object) r5.A01) + ") vs '" + r4.A02 + "' (for " + ((Object) r4.A01) + ")");
                    }
                }
            }
            r4 = r4.A00;
        }
        return r5;
    }

    private final <T> T A04(AbstractC06750nn<T> r3) {
        C06740nm<AnonymousClass0EC> r0;
        T t = null;
        if (this.A06 == null) {
            return null;
        }
        if (this.A05) {
            r0 = this.A02;
        } else {
            C06740nm<AnonymousClass0KB> r02 = this.A00;
            if (r02 != null && (t = r3.A8t(r02.A01)) != null) {
                return t;
            }
            r0 = this.A03;
        }
        if (r0 != null && (t = r3.A8t(r0.A01)) != null) {
            return t;
        }
        C06740nm<AnonymousClass0KC> r03 = this.A01;
        if (r03 != null) {
            return r3.A8t(r03.A01);
        }
        return t;
    }

    public static <T> boolean A05(C06740nm<T> r1) {
        while (r1 != null) {
            if (r1.A02 != null && r1.A02.length() > 0) {
                return true;
            }
            r1 = r1.A00;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final C06250mQ A06() {
        return (C06250mQ) A04(new C02390Zl(this));
    }

    @Override // X.AbstractC02410Zn
    public final C06350mc A07() {
        if (this.A05) {
            A09();
            return null;
        }
        A0A();
        return null;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0KC A08() {
        C06740nm<AnonymousClass0KC> r0 = this.A01;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C06740nm<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Multiple fields representing property \"", A0E(), "\": ", t.A0S(), " vs ", t2.A0S()));
        }
        return t;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0EC A0B() {
        C06740nm<AnonymousClass0EC> r0 = this.A02;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C06740nm<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Conflicting getter definitions for property \"", A0E(), "\": ", t.A0b(), " vs ", t2.A0b()));
        }
        return t;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0EC A0C() {
        C06740nm<AnonymousClass0EC> r0 = this.A03;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C06740nm<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0P = t.A0P();
            Class<?> A0P2 = t2.A0P();
            if (A0P != A0P2) {
                if (A0P.isAssignableFrom(A0P2)) {
                    t = t2;
                } else if (A0P2.isAssignableFrom(A0P)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Conflicting setter definitions for property \"", A0E(), "\": ", t.A0b(), " vs ", t2.A0b()));
        }
        return t;
    }

    @Override // X.AbstractC02410Zn
    public final AnonymousClass0KB A0D() {
        C06740nm r3 = this.A00;
        if (r3 == null) {
            return null;
        }
        do {
            T t = r3.A01;
            if (t._owner instanceof AnonymousClass0EJ) {
                return t;
            }
            r3 = r3.A00;
        } while (r3 != null);
        return r3.A01;
    }

    @Override // X.AbstractC02410Zn
    public final String A0E() {
        return this.A07;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0F() {
        Boolean bool = (Boolean) A04(new C02370Zj(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0G() {
        Boolean bool = (Boolean) A04(new C02380Zk(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0H() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0I() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0J() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0K() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final boolean A0L() {
        if (A05(this.A01) || A05(this.A02) || A05(this.A03) || A05(this.A00)) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC02410Zn
    public final Class<?>[] A0M() {
        return (Class[]) A04(new C02400Zm(this));
    }

    public final void A0N(AnonymousClass0K7 r3) {
        this.A01 = A02(this.A01, r3.A01);
        this.A00 = A02(this.A00, r3.A00);
        this.A02 = A02(this.A02, r3.A02);
        this.A03 = A02(this.A03, r3.A03);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(AnonymousClass0K7 r3) {
        AnonymousClass0K7 r32 = r3;
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
        return "[Property '" + this.A07 + "'; ctors: " + this.A00 + ", field(s): " + this.A01 + ", getter(s): " + this.A02 + ", setter(s): " + this.A03 + "]";
    }

    @Override // X.AbstractC02410Zn
    public final AbstractC02450Zr A09() {
        AnonymousClass0EC A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AbstractC02410Zn
    public final AbstractC02450Zr A0A() {
        AnonymousClass0KB A0D = A0D();
        if (A0D != null) {
            return A0D;
        }
        AnonymousClass0EC A0C = A0C();
        if (A0C == null) {
            return A08();
        }
        return A0C;
    }

    public AnonymousClass0K7(AnonymousClass0K7 r2, String str) {
        this.A04 = r2.A04;
        this.A07 = str;
        this.A06 = r2.A06;
        this.A01 = r2.A01;
        this.A00 = r2.A00;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
        this.A05 = r2.A05;
    }

    public AnonymousClass0K7(String str, AbstractC02590aM r2, boolean z) {
        this.A04 = str;
        this.A07 = str;
        this.A06 = r2;
        this.A05 = z;
    }
}
