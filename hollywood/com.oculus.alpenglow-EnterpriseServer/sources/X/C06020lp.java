package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0lp  reason: invalid class name and case insensitive filesystem */
public final class C06020lp {
    public final String[] A00;

    public final String A00(String str) {
        String[] strArr = this.A00;
        int length = strArr.length;
        do {
            length -= 2;
            if (length < 0) {
                return null;
            }
        } while (!str.equalsIgnoreCase(strArr[length]));
        return strArr[length + 1];
    }

    public final C06030lq A01() {
        C06030lq r2 = new C06030lq();
        Collections.addAll(r2.A00, this.A00);
        return r2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C06020lp) || !Arrays.equals(((C06020lp) obj).A00, this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.A00);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        String[] strArr = this.A00;
        int length = strArr.length >> 1;
        for (int i = 0; i < length; i++) {
            int i2 = i << 1;
            sb.append(strArr[i2]);
            sb.append(": ");
            sb.append(strArr[i2 + 1]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public C06020lp(C06030lq r3) {
        List<String> list = r3.A00;
        this.A00 = (String[]) list.toArray(new String[list.size()]);
    }
}
