package X;

public abstract class PU {
    public final PU A00(O5 o5) {
        if (this instanceof C0313Qk) {
            AbstractC1057rX rXVar = (AbstractC1057rX) this;
            if (rXVar.A00 != o5) {
                return new C0313Qk(rXVar.A01, o5);
            }
            return rXVar;
        } else if (!(this instanceof R7)) {
            R8 r8 = (R8) this;
            boolean z = r8 instanceof C00000b;
            if (z || z) {
                C00000b r2 = (C00000b) r8;
                if (((AbstractC1057rX) r2).A00 != o5) {
                    return new C00000b(r2.A01, o5, r2.A00);
                }
                return r2;
            } else if (r8.A00 != o5) {
                return new R8(r8.A01, o5);
            } else {
                return r8;
            }
        } else {
            R7 r7 = (R7) this;
            if (((AbstractC1057rX) r7).A00 != o5) {
                return new R7(r7.A01, o5, r7.A00);
            }
            return r7;
        }
    }

    public final void A01(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A0C();
            qgVar.A0M(((AbstractC1057rX) this).A01.A3F(obj));
        } else if (!(this instanceof R7)) {
            qgVar.A0B();
            qgVar.A0P(((AbstractC1057rX) this).A01.A3F(obj));
        }
        qgVar.A0B();
    }

    public final void A02(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A0C();
            qgVar.A0M(((AbstractC1057rX) this).A01.A3F(obj));
        } else if (!(this instanceof R7)) {
            R8 r8 = (R8) this;
            if (!(r8 instanceof C00000b)) {
                qgVar.A0B();
                qgVar.A0P(r8.A01.A3F(obj));
            } else {
                C00000b r2 = (C00000b) r8;
                qgVar.A0C();
                String str = r2.A00;
                String A3F = r2.A01.A3F(obj);
                qgVar.A0M(str);
                qgVar.A0P(A3F);
                return;
            }
        }
        qgVar.A0C();
    }

    public final void A03(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A0C();
            qgVar.A0M(((AbstractC1057rX) this).A01.A3F(obj));
        } else if (!(this instanceof R7)) {
            qgVar.A0B();
            qgVar.A0P(((AbstractC1057rX) this).A01.A3F(obj));
        }
    }

    public final void A04(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A08();
            qgVar.A09();
        } else if (!(this instanceof R7)) {
            qgVar.A08();
            qgVar.A08();
        } else {
            R7 r7 = (R7) this;
            String A3F = r7.A01.A3F(obj);
            qgVar.A08();
            qgVar.A0M(r7.A00);
            qgVar.A0P(A3F);
        }
    }

    public final void A05(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A09();
        } else if (this instanceof R7) {
            R7 r7 = (R7) this;
            String A3F = r7.A01.A3F(obj);
            qgVar.A09();
            qgVar.A0M(r7.A00);
            qgVar.A0P(A3F);
            return;
        } else if (!(((R8) this) instanceof C00000b)) {
            qgVar.A09();
            qgVar.A08();
            return;
        }
        qgVar.A09();
    }

    public final void A06(Object obj, AbstractC1012qg qgVar) {
        if (this instanceof C0313Qk) {
            qgVar.A09();
        } else if (!(this instanceof R7)) {
            qgVar.A08();
        } else {
            R7 r7 = (R7) this;
            String A3F = r7.A01.A3F(obj);
            qgVar.A0M(r7.A00);
            qgVar.A0P(A3F);
        }
    }

    public final void A07(Object obj, AbstractC1012qg qgVar, Class cls) {
        if (this instanceof C0313Qk) {
            qgVar.A0C();
            qgVar.A0M(((AbstractC1057rX) this).A01.A3G(obj, cls));
        } else if (this instanceof R7) {
        } else {
            if (!(this instanceof R8)) {
                A03(obj, qgVar);
                return;
            }
            qgVar.A0B();
            qgVar.A0P(((AbstractC1057rX) this).A01.A3G(obj, cls));
        }
    }

    public final void A08(Object obj, AbstractC1012qg qgVar, String str) {
        if (this instanceof C0313Qk) {
            qgVar.A0C();
            qgVar.A0M(str);
        } else if (!(this instanceof R7)) {
            R8 r8 = (R8) this;
            if (!(r8 instanceof C00000b)) {
                qgVar.A0B();
                qgVar.A0P(str);
            } else {
                qgVar.A0C();
                qgVar.A0M(((C00000b) r8).A00);
                qgVar.A0P(str);
                return;
            }
        }
        qgVar.A0C();
    }

    public final void A09(Object obj, AbstractC1012qg qgVar, String str) {
        if (this instanceof C0313Qk) {
            A05(obj, qgVar);
        } else if (!(this instanceof R7)) {
            R8 r8 = (R8) this;
            if (!(r8 instanceof C00000b)) {
                r8.A05(obj, qgVar);
            } else {
                qgVar.A09();
            }
        } else {
            qgVar.A09();
            qgVar.A0M(((R7) this).A00);
            qgVar.A0P(str);
        }
    }
}
