package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: X.0wX  reason: invalid class name */
public final class AnonymousClass0wX {
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

    public final C08420wY A01() {
        C08420wY r2 = new C08420wY();
        Collections.addAll(r2.A00, this.A00);
        return r2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0wX) || !Arrays.equals(((AnonymousClass0wX) obj).A00, this.A00)) {
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

    public AnonymousClass0wX(C08420wY r3) {
        List<String> list = r3.A00;
        this.A00 = (String[]) list.toArray(new String[list.size()]);
    }
}
