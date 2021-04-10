package X;

import java.io.Serializable;

/* renamed from: X.aC  reason: case insensitive filesystem */
public final class C0467aC implements Serializable {
    public final Object first;
    public final Object second;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0467aC)) {
            return false;
        }
        C0467aC aCVar = (C0467aC) obj;
        return C0514bB.A05(this.first, aCVar.first) && C0514bB.A05(this.second, aCVar.second);
    }

    public final int hashCode() {
        Object obj = this.first;
        int i = 0;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.second;
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(this.first);
        sb.append(", ");
        sb.append(this.second);
        sb.append(')');
        return sb.toString();
    }

    public C0467aC(Object obj, Object obj2) {
        this.first = obj;
        this.second = obj2;
    }
}
