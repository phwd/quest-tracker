package X;

import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.0U  reason: invalid class name */
public final class AnonymousClass0U extends AbstractC0280Pc {
    public final Map A00 = new LinkedHashMap();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass0U) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.OA
    public final String toString() {
        Map map = this.A00;
        StringBuilder sb = new StringBuilder((map.size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append('\"');
            C0245Ne.A00(sb, (String) entry.getKey());
            sb.append('\"');
            sb.append(':');
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass0U(PW pw) {
        super(pw);
    }

    @Override // X.OB
    public final void A4y(AbstractC1012qg qgVar, AbstractC1031r2 r2Var, PU pu) {
        pu.A02(this, qgVar);
        for (Map.Entry entry : this.A00.entrySet()) {
            qgVar.A0M((String) entry.getKey());
            ((AbstractC1058rY) entry.getValue()).A4x(qgVar, r2Var);
        }
        pu.A05(this, qgVar);
    }
}
