package X;

public abstract class PE {
    public final OE A06() {
        if (this instanceof C1078ru) {
            return null;
        }
        C1052rR rRVar = (C1052rR) this;
        if (rRVar.A06) {
            rRVar.A08();
            return null;
        }
        rRVar.A09();
        return null;
    }

    public final St A07() {
        if (!(this instanceof C1078ru)) {
            C1052rR rRVar = (C1052rR) this;
            PL pl = rRVar.A01;
            if (pl == null) {
                return null;
            }
            St st = (St) pl.A01;
            for (PL pl2 = pl.A00; pl2 != null; pl2 = pl2.A00) {
                St st2 = (St) pl2.A01;
                Class<?> A0P = st.A0P();
                Class<?> A0P2 = st2.A0P();
                if (A0P != A0P2) {
                    if (A0P.isAssignableFrom(A0P2)) {
                        st = st2;
                    } else if (A0P2.isAssignableFrom(A0P)) {
                    }
                }
                throw new IllegalArgumentException(AnonymousClass08.A08("Multiple fields representing property \"", rRVar.A0D(), "\": ", st.A0S(), " vs ", st2.A0S()));
            }
            return st;
        }
        AbstractC1044rJ rJVar = ((C1078ru) this).A00;
        if (rJVar instanceof St) {
            return (St) rJVar;
        }
        return null;
    }

    public final AbstractC1044rJ A08() {
        boolean z = this instanceof C1078ru;
        AnonymousClass0q A0A = A0A();
        if (A0A == null) {
            return A07();
        }
        return A0A;
    }

    public final AbstractC1044rJ A09() {
        boolean z = this instanceof C1078ru;
        Ss A0C = A0C();
        if (A0C != null) {
            return A0C;
        }
        AnonymousClass0q A0B = A0B();
        if (A0B == null) {
            return A07();
        }
        return A0B;
    }

    public final AnonymousClass0q A0A() {
        if (!(this instanceof C1078ru)) {
            C1052rR rRVar = (C1052rR) this;
            PL pl = rRVar.A02;
            if (pl == null) {
                return null;
            }
            AnonymousClass0q r5 = (AnonymousClass0q) pl.A01;
            for (PL pl2 = pl.A00; pl2 != null; pl2 = pl2.A00) {
                AnonymousClass0q r3 = (AnonymousClass0q) pl2.A01;
                Class<?> A0P = r5.A0P();
                Class<?> A0P2 = r3.A0P();
                if (A0P != A0P2) {
                    if (A0P.isAssignableFrom(A0P2)) {
                        r5 = r3;
                    } else if (A0P2.isAssignableFrom(A0P)) {
                    }
                }
                throw new IllegalArgumentException(AnonymousClass08.A08("Conflicting getter definitions for property \"", rRVar.A0D(), "\": ", r5.A0Z(), " vs ", r3.A0Z()));
            }
            return r5;
        }
        AbstractC1044rJ rJVar = ((C1078ru) this).A00;
        if (rJVar instanceof AnonymousClass0q) {
            AnonymousClass0q r1 = (AnonymousClass0q) rJVar;
            if (r1.A0X() != 0) {
                return null;
            }
            return r1;
        }
        return null;
    }

    public final AnonymousClass0q A0B() {
        if (!(this instanceof C1078ru)) {
            C1052rR rRVar = (C1052rR) this;
            PL pl = rRVar.A03;
            if (pl == null) {
                return null;
            }
            AnonymousClass0q r5 = (AnonymousClass0q) pl.A01;
            for (PL pl2 = pl.A00; pl2 != null; pl2 = pl2.A00) {
                AnonymousClass0q r3 = (AnonymousClass0q) pl2.A01;
                Class<?> A0P = r5.A0P();
                Class<?> A0P2 = r3.A0P();
                if (A0P != A0P2) {
                    if (A0P.isAssignableFrom(A0P2)) {
                        r5 = r3;
                    } else if (A0P2.isAssignableFrom(A0P)) {
                    }
                }
                throw new IllegalArgumentException(AnonymousClass08.A08("Conflicting setter definitions for property \"", rRVar.A0D(), "\": ", r5.A0Z(), " vs ", r3.A0Z()));
            }
            return r5;
        }
        AbstractC1044rJ rJVar = ((C1078ru) this).A00;
        if (rJVar instanceof AnonymousClass0q) {
            AnonymousClass0q r2 = (AnonymousClass0q) rJVar;
            if (r2.A0X() != 1) {
                return null;
            }
            return r2;
        }
        return null;
    }

    public final Ss A0C() {
        if (!(this instanceof C1078ru)) {
            PL pl = ((C1052rR) this).A00;
            if (pl != null) {
                do {
                    Ss ss = (Ss) pl.A01;
                    if (ss._owner instanceof C00090s) {
                        return ss;
                    }
                    pl = pl.A00;
                } while (pl != null);
                return (Ss) pl.A01;
            }
        } else {
            AbstractC1044rJ rJVar = ((C1078ru) this).A00;
            if (rJVar instanceof Ss) {
                return (Ss) rJVar;
            }
        }
        return null;
    }

    public final String A0D() {
        if (!(this instanceof C1078ru)) {
            return ((C1052rR) this).A05;
        }
        return ((C1078ru) this).A01;
    }

    public final boolean A0E() {
        if (this instanceof C1078ru) {
            return ((C1078ru) this).A00 instanceof Ss;
        }
        if (((C1052rR) this).A00 != null) {
            return true;
        }
        return false;
    }

    public final boolean A0F() {
        if (this instanceof C1078ru) {
            return ((C1078ru) this).A00 instanceof St;
        }
        if (((C1052rR) this).A01 != null) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0G() {
        /*
            r2 = this;
            boolean r0 = r2 instanceof X.C1078ru
            if (r0 != 0) goto L_0x000e
            r0 = r2
            X.rR r0 = (X.C1052rR) r0
            X.PL r1 = r0.A02
            r0 = 0
            if (r1 == 0) goto L_0x000d
        L_0x000c:
            r0 = 1
        L_0x000d:
            return r0
        L_0x000e:
            X.0q r1 = r2.A0A()
            r0 = 0
            if (r1 == 0) goto L_0x000d
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PE.A0G():boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:4:0x000c A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A0H() {
        /*
            r2 = this;
            boolean r0 = r2 instanceof X.C1078ru
            if (r0 != 0) goto L_0x000e
            r0 = r2
            X.rR r0 = (X.C1052rR) r0
            X.PL r1 = r0.A03
            r0 = 0
            if (r1 == 0) goto L_0x000d
        L_0x000c:
            r0 = 1
        L_0x000d:
            return r0
        L_0x000e:
            X.0q r1 = r2.A0B()
            r0 = 0
            if (r1 == 0) goto L_0x000d
            goto L_0x000c
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PE.A0H():boolean");
    }

    public final boolean A0I() {
        if (this instanceof C1078ru) {
            return false;
        }
        C1052rR rRVar = (C1052rR) this;
        if (C1052rR.A05(rRVar.A01) || C1052rR.A05(rRVar.A02) || C1052rR.A05(rRVar.A03) || C1052rR.A05(rRVar.A00)) {
            return true;
        }
        return false;
    }

    public final boolean A0J() {
        if (this instanceof C1052rR) {
            C1052rR rRVar = (C1052rR) this;
            Boolean bool = (Boolean) C1052rR.A04(rRVar, new C1051rQ(rRVar));
            if (bool == null || !bool.booleanValue()) {
                return false;
            }
            return true;
        }
        return false;
    }
}
