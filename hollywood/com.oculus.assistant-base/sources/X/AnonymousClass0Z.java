package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0Z  reason: invalid class name */
public final class AnonymousClass0Z extends AbstractC0280Pc {
    public final List A00 = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0Z) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.OA
    public final String toString() {
        List list = this.A00;
        StringBuilder sb = new StringBuilder((list.size() << 4) + 16);
        sb.append('[');
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(list.get(i).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass0Z(PW pw) {
        super(pw);
    }

    @Override // X.OB
    public final void A4y(AbstractC1012qg qgVar, AbstractC1031r2 r2Var, PU pu) {
        pu.A01(this, qgVar);
        for (OA oa : this.A00) {
            ((AbstractC1058rY) oa).A4x(qgVar, r2Var);
        }
        pu.A04(this, qgVar);
    }
}
