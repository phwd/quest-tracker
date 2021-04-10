package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class XW {
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

    public final XX A01() {
        XX xx = new XX();
        Collections.addAll(xx.A00, this.A00);
        return xx;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof XW) || !Arrays.equals(((XW) obj).A00, this.A00)) {
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

    public XW(XX xx) {
        List<String> list = xx.A00;
        this.A00 = (String[]) list.toArray(new String[list.size()]);
    }
}
