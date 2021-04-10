package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.04L  reason: invalid class name */
public final class AnonymousClass04L extends AnonymousClass0Ck<AnonymousClass04L> {
    public final Map<String, AbstractC02170iC> A00 = new LinkedHashMap();

    @Override // X.AbstractC02170iC
    public final Iterator<AbstractC02170iC> A01() {
        return this.A00.values().iterator();
    }

    public final void A03(String str, String str2) {
        if (str2 == null) {
            this.A00.put(str, AnonymousClass04S.A00);
        } else {
            this.A00.put(str, AnonymousClass04F.A00(str2));
        }
    }

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass04L) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC02170iC
    public final String toString() {
        Map<String, AbstractC02170iC> map = this.A00;
        StringBuilder sb = new StringBuilder((map.size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry<String, AbstractC02170iC> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append('\"');
            C03730oR.A00(sb, entry.getKey());
            sb.append('\"');
            sb.append(':');
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass04L(C01850hC r2) {
        super(r2);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C03620oC {
        r4.A0I();
        for (Map.Entry<String, AbstractC02170iC> entry : this.A00.entrySet()) {
            r4.A0R(entry.getKey());
            ((AnonymousClass0Oh) entry.getValue()).A9c(r4, r5);
        }
        r4.A0F();
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9d(AbstractC02300iS r4, AbstractC02120i3 r5, AbstractC04550qd r6) throws IOException, C03620oC {
        r6.A02(this, r4);
        for (Map.Entry<String, AbstractC02170iC> entry : this.A00.entrySet()) {
            r4.A0R(entry.getKey());
            ((AnonymousClass0Oh) entry.getValue()).A9c(r4, r5);
        }
        r6.A05(this, r4);
    }
}
