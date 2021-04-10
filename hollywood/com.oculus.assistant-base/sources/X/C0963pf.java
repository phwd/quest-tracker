package X;

import java.util.ArrayList;
import java.util.Arrays;

/* renamed from: X.pf  reason: case insensitive filesystem */
public class C0963pf {
    public final Object[] A00;

    public C0963pf(Object[] objArr) {
        C0514bB.A02(objArr, "columns");
        this.A00 = objArr;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0963pf)) {
            return false;
        }
        return DH.A02(this.A00, ((C0963pf) obj).A00);
    }

    public final int hashCode() {
        return Arrays.deepHashCode(this.A00);
    }

    public final String toString() {
        Object[] objArr = this.A00;
        if (objArr == null) {
            return "null";
        }
        int length = objArr.length;
        if (length > 429496729) {
            length = 429496729;
        }
        StringBuilder sb = new StringBuilder((length * 5) + 2);
        DH.A01(objArr, sb, new ArrayList());
        String obj = sb.toString();
        C0514bB.A01(obj, "StringBuilder(capacity).…builderAction).toString()");
        return obj;
    }
}
