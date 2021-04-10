package X;

import java.util.Map;

/* renamed from: X.rY  reason: case insensitive filesystem */
public abstract class AbstractC1058rY extends OA implements OB {
    @Override // X.OB
    public final void A4x(AbstractC1012qg qgVar, AbstractC1031r2 r2Var) {
        if (!(this instanceof AnonymousClass0S)) {
            if (this instanceof AnonymousClass0T) {
                Object obj = ((AnonymousClass0T) this).A00;
                if (obj != null) {
                    qgVar.A0L(obj);
                    return;
                }
            } else if (!(this instanceof AnonymousClass0W)) {
                if (this instanceof AnonymousClass0X) {
                    qgVar.A0T(((AnonymousClass0X) this).A00);
                    return;
                } else if (this instanceof AnonymousClass0Y) {
                    NP np = r2Var._config._base._defaultBase64;
                    byte[] bArr = ((AnonymousClass0Y) this).A00;
                    qgVar.A0I(np, bArr, 0, bArr.length);
                    return;
                } else if (this instanceof C00396o) {
                    qgVar.A0H(((C00396o) this).A00);
                    return;
                } else if (this instanceof C00406p) {
                    qgVar.A0G(((C00406p) this).A00);
                    return;
                } else if (this instanceof C00416q) {
                    qgVar.A0E(((C00416q) this).A00);
                    return;
                } else if (this instanceof C00436s) {
                    C00436s r2 = (C00436s) this;
                    if (!r2Var._config.A06(EnumC1030r1.WRITE_BIGDECIMAL_AS_PLAIN) || (qgVar instanceof JD)) {
                        qgVar.A0Q(r2.A00);
                        return;
                    } else {
                        qgVar.A0N(r2.A00.toPlainString());
                        return;
                    }
                } else if (this instanceof C00466v) {
                    qgVar.A0R(((C00466v) this).A00);
                    return;
                } else if (!(this instanceof AnonymousClass0U)) {
                    qgVar.A0B();
                    for (OA oa : ((AnonymousClass0Z) this).A00) {
                        ((AbstractC1058rY) oa).A4x(qgVar, r2Var);
                    }
                    qgVar.A08();
                    return;
                } else {
                    qgVar.A0C();
                    for (Map.Entry entry : ((AnonymousClass0U) this).A00.entrySet()) {
                        qgVar.A0M((String) entry.getKey());
                        ((AbstractC1058rY) entry.getValue()).A4x(qgVar, r2Var);
                    }
                    qgVar.A09();
                    return;
                }
            }
            r2Var.A0D(qgVar);
            return;
        }
        String str = ((AnonymousClass0S) this).A00;
        if (str == null) {
            qgVar.A0A();
        } else {
            qgVar.A0P(str);
        }
    }
}
