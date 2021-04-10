package X;

import java.lang.annotation.Annotation;
import java.util.HashMap;

/* renamed from: X.rR  reason: case insensitive filesystem */
public final class C1052rR extends PE implements Comparable {
    public PL A00;
    public PL A01;
    public PL A02;
    public PL A03;
    public final String A04;
    public final String A05;
    public final boolean A06;
    public final AbstractC1020qp A07;

    public static C1045rK A00(C1052rR rRVar, int i, PL... plArr) {
        HashMap hashMap;
        HashMap hashMap2;
        C1045rK rKVar = ((AbstractC1044rJ) plArr[i].A01).A00;
        do {
            i++;
            if (i >= plArr.length) {
                return rKVar;
            }
        } while (plArr[i] == null);
        C1045rK A002 = A00(rRVar, i, plArr);
        if (rKVar == null || (hashMap = rKVar.A00) == null || hashMap.isEmpty()) {
            return A002;
        }
        if (A002 == null || (hashMap2 = A002.A00) == null || hashMap2.isEmpty()) {
            return rKVar;
        }
        HashMap hashMap3 = new HashMap();
        for (Annotation annotation : hashMap2.values()) {
            hashMap3.put(annotation.annotationType(), annotation);
        }
        for (Annotation annotation2 : rKVar.A00.values()) {
            hashMap3.put(annotation2.annotationType(), annotation2);
        }
        return new C1045rK(hashMap3);
    }

    public static PL A01(PL pl) {
        PL A042;
        if (pl == null) {
            return pl;
        }
        PL pl2 = pl.A00;
        if (pl2 == null) {
            A042 = null;
        } else {
            PL pl3 = pl2.A00;
            if (pl3 == null) {
                A042 = null;
            } else {
                PL pl4 = pl3.A00;
                if (pl4 == null) {
                    A042 = null;
                } else {
                    A042 = pl4.A04();
                }
                if (pl3.A04) {
                    A042 = PL.A01(pl3, A042);
                }
            }
            if (pl2.A04) {
                A042 = PL.A01(pl2, A042);
            }
        }
        if (pl.A04) {
            return PL.A01(pl, A042);
        }
        return A042;
    }

    public static PL A02(PL pl, PL pl2) {
        if (pl == null) {
            return pl2;
        }
        if (pl2 == null) {
            return pl;
        }
        PL pl3 = pl.A00;
        if (pl3 != null) {
            PL pl4 = pl3.A00;
            if (pl4 != null) {
                PL pl5 = pl4.A00;
                if (pl5 != null) {
                    pl2 = PL.A00(pl5, pl2);
                }
                pl2 = PL.A01(pl4, pl2);
            }
            pl2 = PL.A01(pl3, pl2);
        }
        return PL.A01(pl, pl2);
    }

    public static PL A03(C1052rR rRVar, PL pl, PL pl2) {
        while (pl != null) {
            String str = pl.A02;
            if (str != null && !str.equals(rRVar.A05)) {
                if (pl2 == null) {
                    pl2 = pl;
                } else {
                    String str2 = pl2.A02;
                    if (!str.equals(str2)) {
                        StringBuilder sb = new StringBuilder("Conflicting property name definitions: '");
                        sb.append(str2);
                        sb.append("' (for ");
                        sb.append(pl2.A01);
                        sb.append(") vs '");
                        sb.append(pl.A02);
                        sb.append("' (for ");
                        sb.append(pl.A01);
                        sb.append(")");
                        throw new IllegalStateException(sb.toString());
                    }
                }
            }
            pl = pl.A00;
        }
        return pl2;
    }

    public static final Object A04(C1052rR rRVar, PM pm) {
        PL pl;
        Object obj = null;
        if (rRVar.A07 == null) {
            return null;
        }
        if (rRVar.A06) {
            pl = rRVar.A02;
        } else {
            PL pl2 = rRVar.A00;
            if (pl2 != null && (obj = pm.A5f((AbstractC1044rJ) pl2.A01)) != null) {
                return obj;
            }
            pl = rRVar.A03;
        }
        if (pl != null && (obj = pm.A5f((AbstractC1044rJ) pl.A01)) != null) {
            return obj;
        }
        PL pl3 = rRVar.A01;
        if (pl3 != null) {
            return pm.A5f((AbstractC1044rJ) pl3.A01);
        }
        return obj;
    }

    public static boolean A05(PL pl) {
        while (pl != null) {
            if (pl.A02 != null && pl.A02.length() > 0) {
                return true;
            }
            pl = pl.A00;
        }
        return false;
    }

    public final void A0K(C1052rR rRVar) {
        this.A01 = A02(this.A01, rRVar.A01);
        this.A00 = A02(this.A00, rRVar.A00);
        this.A02 = A02(this.A02, rRVar.A02);
        this.A03 = A02(this.A03, rRVar.A03);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        C1052rR rRVar = (C1052rR) obj;
        if (this.A00 != null) {
            if (rRVar.A00 == null) {
                return -1;
            }
        } else if (rRVar.A00 != null) {
            return 1;
        }
        return A0D().compareTo(rRVar.A0D());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[Property '");
        sb.append(this.A05);
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

    public C1052rR(C1052rR rRVar, String str) {
        this.A04 = rRVar.A04;
        this.A05 = str;
        this.A07 = rRVar.A07;
        this.A01 = rRVar.A01;
        this.A00 = rRVar.A00;
        this.A02 = rRVar.A02;
        this.A03 = rRVar.A03;
        this.A06 = rRVar.A06;
    }

    public C1052rR(String str, AbstractC1020qp qpVar, boolean z) {
        this.A04 = str;
        this.A05 = str;
        this.A07 = qpVar;
        this.A06 = z;
    }
}
