package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.0GS  reason: invalid class name */
public final class AnonymousClass0GS extends AnonymousClass0g5 implements Comparable<AnonymousClass0GS> {
    public C05790lr<AnonymousClass0GW> A00;
    public C05790lr<AnonymousClass0GX> A01;
    public C05790lr<AnonymousClass07O> A02;
    public C05790lr<AnonymousClass07O> A03;
    public final String A04;
    public final boolean A05;
    public final AbstractC04040gi A06;
    public final String A07;

    /* JADX WARN: Incorrect args count in method signature: (I[LX/0lr<+LX/0g9;>;)LX/0g8; */
    public static C03800g8 A00(AnonymousClass0GS r5, int i, C05790lr... r7) {
        HashMap<Class<? extends Annotation>, Annotation> hashMap;
        HashMap<Class<? extends Annotation>, Annotation> hashMap2;
        C03800g8 r4 = r7[i].A01.A00;
        do {
            i++;
            if (i >= r7.length) {
                return r4;
            }
        } while (r7[i] == null);
        C03800g8 A002 = A00(r5, i, r7);
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
        return new C03800g8(hashMap3);
    }

    public static <T> C05790lr<T> A01(C05790lr<T> r4) {
        C05790lr<T> A042;
        if (r4 == null) {
            return r4;
        }
        C05790lr<T> r3 = r4.A00;
        if (r3 == null) {
            A042 = null;
        } else {
            C05790lr<T> r2 = r3.A00;
            if (r2 == null) {
                A042 = null;
            } else {
                C05790lr<T> r0 = r2.A00;
                if (r0 == null) {
                    A042 = null;
                } else {
                    A042 = r0.A04();
                }
                if (r2.A04) {
                    A042 = C05790lr.A01(r2, A042);
                }
            }
            if (r3.A04) {
                A042 = C05790lr.A01(r3, A042);
            }
        }
        if (r4.A04) {
            return C05790lr.A01(r4, A042);
        }
        return A042;
    }

    public static <T> C05790lr<T> A02(C05790lr<T> r3, C05790lr<T> r4) {
        if (r3 == null) {
            return r4;
        }
        if (r4 == null) {
            return r3;
        }
        C05790lr<T> r2 = r3.A00;
        if (r2 != null) {
            C05790lr<T> r1 = r2.A00;
            if (r1 != null) {
                C05790lr<T> r0 = r1.A00;
                if (r0 != null) {
                    r4 = C05790lr.A00(r0, r4);
                }
                r4 = C05790lr.A01(r1, r4);
            }
            r4 = C05790lr.A01(r2, r4);
        }
        return C05790lr.A01(r3, r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0lr<+LX/0g9;>;LX/0lr<+LX/0g9;>;)LX/0lr<+LX/0g9;>; */
    public static C05790lr A03(AnonymousClass0GS r3, C05790lr r4, C05790lr r5) {
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

    private final <T> T A04(AbstractC05800ls<T> r3) {
        C05790lr<AnonymousClass07O> r0;
        T t = null;
        if (this.A06 == null) {
            return null;
        }
        if (this.A05) {
            r0 = this.A02;
        } else {
            C05790lr<AnonymousClass0GW> r02 = this.A00;
            if (r02 != null && (t = r3.AA4(r02.A01)) != null) {
                return t;
            }
            r0 = this.A03;
        }
        if (r0 != null && (t = r3.AA4(r0.A01)) != null) {
            return t;
        }
        C05790lr<AnonymousClass0GX> r03 = this.A01;
        if (r03 != null) {
            return r3.AA4(r03.A01);
        }
        return t;
    }

    public static <T> boolean A05(C05790lr<T> r1) {
        while (r1 != null) {
            if (r1.A02 != null && r1.A02.length() > 0) {
                return true;
            }
            r1 = r1.A00;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final C05170kT A06() {
        return (C05170kT) A04(new C03760g3(this));
    }

    @Override // X.AnonymousClass0g5
    public final C05280kg A07() {
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

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0GX A08() {
        C05790lr<AnonymousClass0GX> r0 = this.A01;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C05790lr<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Multiple fields representing property \"", A0D(), "\": ", AnonymousClass006.A07(t.A0O().getName(), "#", t.A0K()), " vs ", AnonymousClass006.A07(t2.A0O().getName(), "#", t2.A0K())));
        }
        return t;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass07O A0A() {
        C05790lr<AnonymousClass07O> r0 = this.A02;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C05790lr<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Conflicting getter definitions for property \"", A0D(), "\": ", t.A0Y(), " vs ", t2.A0Y()));
        }
        return t;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass07O A0B() {
        C05790lr<AnonymousClass07O> r0 = this.A03;
        if (r0 == null) {
            return null;
        }
        T t = r0.A01;
        for (C05790lr<T> r4 = r0.A00; r4 != null; r4 = r4.A00) {
            T t2 = r4.A01;
            Class<?> A0O = t.A0O();
            Class<?> A0O2 = t2.A0O();
            if (A0O != A0O2) {
                if (A0O.isAssignableFrom(A0O2)) {
                    t = t2;
                } else if (A0O2.isAssignableFrom(A0O)) {
                }
            }
            throw new IllegalArgumentException(AnonymousClass006.A0A("Conflicting setter definitions for property \"", A0D(), "\": ", t.A0Y(), " vs ", t2.A0Y()));
        }
        return t;
    }

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0GW A0C() {
        C05790lr r3 = this.A00;
        if (r3 == null) {
            return null;
        }
        do {
            T t = r3.A01;
            if (t._owner instanceof AnonymousClass07P) {
                return t;
            }
            r3 = r3.A00;
        } while (r3 != null);
        return r3.A01;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0E() {
        Boolean bool = (Boolean) A04(new C03750g2(this));
        if (bool == null || !bool.booleanValue()) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0F() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0G() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0H() {
        if (this.A02 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final boolean A0I() {
        if (this.A03 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0g5
    public final Class<?>[] A0J() {
        return (Class[]) A04(new C03770g4(this));
    }

    public final void A0K(AnonymousClass0GS r3) {
        this.A01 = A02(this.A01, r3.A01);
        this.A00 = A02(this.A00, r3.A00);
        this.A02 = A02(this.A02, r3.A02);
        this.A03 = A02(this.A03, r3.A03);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(AnonymousClass0GS r3) {
        AnonymousClass0GS r32 = r3;
        if (this.A00 != null) {
            if (r32.A00 == null) {
                return -1;
            }
        } else if (r32.A00 != null) {
            return 1;
        }
        return A0D().compareTo(r32.A0D());
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

    @Override // X.AnonymousClass0g5
    public final AnonymousClass0g9 A09() {
        AnonymousClass0GW A0C = A0C();
        if (A0C != null) {
            return A0C;
        }
        AnonymousClass07O A0B = A0B();
        if (A0B == null) {
            return A08();
        }
        return A0B;
    }

    @Override // X.AnonymousClass0g5
    public final String A0D() {
        return this.A07;
    }

    public AnonymousClass0GS(AnonymousClass0GS r2, String str) {
        this.A04 = r2.A04;
        this.A07 = str;
        this.A06 = r2.A06;
        this.A01 = r2.A01;
        this.A00 = r2.A00;
        this.A02 = r2.A02;
        this.A03 = r2.A03;
        this.A05 = r2.A05;
    }

    public AnonymousClass0GS(String str, AbstractC04040gi r3) {
        this.A04 = str;
        this.A07 = str;
        this.A06 = r3;
        this.A05 = false;
    }
}
