package X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* renamed from: X.ds  reason: case insensitive filesystem */
public final class C0369ds {
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

    public final C0370dt A01() {
        C0370dt dtVar = new C0370dt();
        Collections.addAll(dtVar.A00, this.A00);
        return dtVar;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0369ds) || !Arrays.equals(((C0369ds) obj).A00, this.A00)) {
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

    public C0369ds(C0370dt dtVar) {
        List<String> list = dtVar.A00;
        this.A00 = (String[]) list.toArray(new String[list.size()]);
    }
}
