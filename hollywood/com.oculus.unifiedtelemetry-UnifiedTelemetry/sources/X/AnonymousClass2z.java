package X;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.2z  reason: invalid class name */
public final class AnonymousClass2z extends AnonymousClass7J<AnonymousClass2z> {
    public final Map<String, AbstractC0222Wi> A00 = new LinkedHashMap();

    @Override // X.AbstractC0222Wi
    public final Iterator<AbstractC0222Wi> A02() {
        return this.A00.values().iterator();
    }

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass2z) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC0222Wi
    public final String toString() {
        Map<String, AbstractC0222Wi> map = this.A00;
        StringBuilder sb = new StringBuilder((map.size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry<String, AbstractC0222Wi> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append('\"');
            C0450md.A00(sb, entry.getKey());
            sb.append('\"');
            sb.append(':');
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass2z(W6 w6) {
        super(w6);
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A06;
    }
}
