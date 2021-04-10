package X;

public abstract class PR {
    public abstract Object A06(AbstractC1014qi qiVar, AbstractC1022qr qrVar);

    public static Object A01(AbstractC1014qi qiVar, AbstractC1024qt qtVar) {
        Class cls = qtVar._class;
        NX A0U = qiVar.A0U();
        if (A0U == null) {
            return null;
        }
        int i = PQ.A00[A0U.ordinal()];
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
                    return Double.valueOf(qiVar.A0F());
                } else {
                    return null;
                }
            } else if (cls.isAssignableFrom(Integer.class)) {
                return Integer.valueOf(qiVar.A0J());
            } else {
                return null;
            }
        } else if (cls.isAssignableFrom(String.class)) {
            return qiVar.A0p();
        } else {
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: X.0f */
    /* JADX WARN: Multi-variable type inference failed */
    public final PR A03(O5 o5) {
        AnonymousClass0c r1;
        if (!(this instanceof C0317Qp)) {
            RU ru = (RU) this;
            if (ru instanceof AnonymousClass0c) {
                AnonymousClass0c r12 = (AnonymousClass0c) ru;
                O5 o52 = r12._property;
                r1 = r12;
                if (o5 != o52) {
                    return new AnonymousClass0c(r12, o5);
                }
            } else if (!(ru instanceof C00020f)) {
                O5 o53 = ru._property;
                r1 = ru;
                if (o5 != o53) {
                    return new RU(ru, o5);
                }
            } else {
                C00020f r13 = (C00020f) ru;
                O5 o54 = r13._property;
                r1 = r13;
                if (o5 != o54) {
                    return new C00020f(r13, o5);
                }
            }
            return r1;
        }
        C0317Qp qp = (C0317Qp) this;
        if (o5 != qp._property) {
            return new C0317Qp(qp, o5);
        }
        return qp;
    }

    public final Object A04(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (!(this instanceof C0317Qp)) {
            return RU.A02((RU) this, qiVar, qrVar);
        }
        return C0317Qp.A00((C0317Qp) this, qiVar, qrVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
        if (r3 != null) goto L_0x0050;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object A05(X.AbstractC1014qi r7, X.AbstractC1022qr r8) {
        /*
        // Method dump skipped, instructions count: 143
        */
        throw new UnsupportedOperationException("Method not decompiled: X.PR.A05(X.qi, X.qr):java.lang.Object");
    }
}
