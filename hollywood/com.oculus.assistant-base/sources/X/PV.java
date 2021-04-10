package X;

public abstract class PV extends AbstractC1058rY {
    @Override // X.OA
    public String toString() {
        int i;
        if (this instanceof AnonymousClass0S) {
            return ((AnonymousClass0S) this).A00;
        }
        if (this instanceof AnonymousClass0T) {
            Object obj = ((AnonymousClass0T) this).A00;
            if (obj != null) {
                return obj.toString();
            }
            return "null";
        } else if (this instanceof AnonymousClass0W) {
            return "null";
        } else {
            if (!(this instanceof AnonymousClass0X)) {
                if (this instanceof AnonymousClass0Y) {
                    return NQ.A01.A02(((AnonymousClass0Y) this).A00, false);
                }
                if (this instanceof C00396o) {
                    long j = ((C00396o) this).A00;
                    if (j > 2147483647L || j < -2147483648L) {
                        return Long.toString(j);
                    }
                    i = (int) j;
                } else if (this instanceof C00406p) {
                    i = ((C00406p) this).A00;
                } else if (this instanceof C00416q) {
                    return Double.toString(((C00416q) this).A00);
                } else {
                    if (!(this instanceof C00436s)) {
                        return ((C00466v) this).A00.toString();
                    }
                    return ((C00436s) this).A00.toString();
                }
                String[] strArr = Nk.A02;
                if (i < strArr.length) {
                    if (i >= 0) {
                        return strArr[i];
                    }
                    int i2 = (-i) - 1;
                    String[] strArr2 = Nk.A03;
                    if (i2 < strArr2.length) {
                        return strArr2[i2];
                    }
                }
                return Integer.toString(i);
            } else if (((AnonymousClass0X) this).A00) {
                return "true";
            } else {
                return "false";
            }
        }
    }

    @Override // X.OB
    public final void A4y(AbstractC1012qg qgVar, AbstractC1031r2 r2Var, PU pu) {
        pu.A03(this, qgVar);
        A4x(qgVar, r2Var);
        pu.A06(this, qgVar);
    }
}
