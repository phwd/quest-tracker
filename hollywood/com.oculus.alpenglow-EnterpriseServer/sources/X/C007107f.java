package X;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.07f  reason: invalid class name and case insensitive filesystem */
public final class C007107f extends AnonymousClass0E3<C007107f> {
    public final Map<String, AnonymousClass0aF> A00 = new LinkedHashMap();

    @Override // X.AnonymousClass0aF
    public final Iterator<AnonymousClass0aF> A01() {
        return this.A00.values().iterator();
    }

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((C007107f) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass0aF
    public final String toString() {
        Map<String, AnonymousClass0aF> map = this.A00;
        StringBuilder sb = new StringBuilder((map.size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry<String, AnonymousClass0aF> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append('\"');
            C06060lt.A00(sb, entry.getKey());
            sb.append('\"');
            sb.append(':');
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public C007107f(AnonymousClass0Zc r2) {
        super(r2);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C05910ld {
        r4.A0F();
        for (Map.Entry<String, AnonymousClass0aF> entry : this.A00.entrySet()) {
            r4.A0P(entry.getKey());
            ((AnonymousClass0Jx) entry.getValue()).A7h(r4, r5);
        }
        r4.A0C();
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7i(AbstractC02640aV r4, AnonymousClass0a8 r5, AnonymousClass0o6 r6) throws IOException, C05910ld {
        r6.A02(this, r4);
        for (Map.Entry<String, AnonymousClass0aF> entry : this.A00.entrySet()) {
            r4.A0P(entry.getKey());
            ((AnonymousClass0Jx) entry.getValue()).A7h(r4, r5);
        }
        r6.A05(this, r4);
    }
}
