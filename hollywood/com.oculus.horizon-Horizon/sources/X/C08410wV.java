package X;

import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0wV  reason: invalid class name and case insensitive filesystem */
public final class C08410wV {
    public int A00 = -1;
    public String A01;
    public String A02 = "";
    public String A03 = "";
    public String A04;
    public String A05;
    public List<String> A06;
    public final List<String> A07;

    /* JADX WARNING: Removed duplicated region for block: B:46:0x006d A[EDGE_INSN: B:46:0x006d->B:31:0x006d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String A00(java.lang.String r6, int r7, int r8) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08410wV.A00(java.lang.String, int, int):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c5, code lost:
        if (r13 != (r14 + 4)) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.InetAddress A01(java.lang.String r15, int r16, int r17) {
        /*
        // Method dump skipped, instructions count: 212
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08410wV.A01(java.lang.String, int, int):java.net.InetAddress");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:151:0x026f, code lost:
        if (r6 <= 65535) goto L_0x0272;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0099, code lost:
        if (r3 != ':') goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0138, code lost:
        if (r8.equalsIgnoreCase("%2e") != false) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x015c, code lost:
        if (r8.equalsIgnoreCase("%2e%2e") != false) goto L_0x015e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0112  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Integer A02(X.C08400wU r21, java.lang.String r22) {
        /*
        // Method dump skipped, instructions count: 726
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C08410wV.A02(X.0wU, java.lang.String):java.lang.Integer");
    }

    public final C08400wU A03() {
        String str;
        if (this.A05 == null) {
            str = "scheme == null";
        } else if (this.A04 != null) {
            return new C08400wU(this);
        } else {
            str = "host == null";
        }
        throw new IllegalStateException(str);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.A05;
        sb.append(str);
        sb.append("://");
        String str2 = this.A03;
        if (!str2.isEmpty() || !this.A02.isEmpty()) {
            sb.append(str2);
            String str3 = this.A02;
            if (!str3.isEmpty()) {
                sb.append(':');
                sb.append(str3);
            }
            sb.append('@');
        }
        String str4 = this.A04;
        if (str4.indexOf(58) != -1) {
            sb.append('[');
            sb.append(str4);
            sb.append(']');
        } else {
            sb.append(str4);
        }
        int i = this.A00;
        if (i == -1) {
            i = C08400wU.A01(str);
        }
        if (i != C08400wU.A01(str)) {
            sb.append(':');
            sb.append(i);
        }
        List<String> list = this.A07;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            sb.append('/');
            sb.append(list.get(i2));
        }
        List<String> list2 = this.A06;
        if (list2 != null) {
            sb.append('?');
            int size2 = list2.size();
            for (int i3 = 0; i3 < size2; i3 += 2) {
                String str5 = list2.get(i3);
                String str6 = list2.get(i3 + 1);
                if (i3 > 0) {
                    sb.append('&');
                }
                sb.append(str5);
                if (str6 != null) {
                    sb.append('=');
                    sb.append(str6);
                }
            }
        }
        String str7 = this.A01;
        if (str7 != null) {
            sb.append('#');
            sb.append(str7);
        }
        return sb.toString();
    }

    public C08410wV() {
        ArrayList arrayList = new ArrayList();
        this.A07 = arrayList;
        arrayList.add("");
    }
}
