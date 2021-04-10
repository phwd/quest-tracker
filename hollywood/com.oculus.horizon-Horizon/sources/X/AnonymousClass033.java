package X;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* renamed from: X.033  reason: invalid class name */
public final class AnonymousClass033 extends AnonymousClass07H<AnonymousClass033> {
    public final Map<String, AbstractC03980gY> A00 = new LinkedHashMap();

    @Override // X.AbstractC03980gY
    public final Iterator<AbstractC03980gY> A01() {
        return this.A00.values().iterator();
    }

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass033) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC03980gY
    public final String toString() {
        Map<String, AbstractC03980gY> map = this.A00;
        StringBuilder sb = new StringBuilder((map.size() << 4) + 32);
        sb.append("{");
        int i = 0;
        for (Map.Entry<String, AbstractC03980gY> entry : map.entrySet()) {
            if (i > 0) {
                sb.append(",");
            }
            i++;
            sb.append('\"');
            C04950jw.A00(sb, entry.getKey());
            sb.append('\"');
            sb.append(':');
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }

    public AnonymousClass033(C03700fv r2) {
        super(r2);
    }
}
