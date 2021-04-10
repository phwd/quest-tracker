package X;

import com.squareup.okhttp.HttpUrl;
import java.util.ArrayList;
import java.util.List;

/* renamed from: X.0lm  reason: invalid class name and case insensitive filesystem */
public final class C06000lm {
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
        throw new UnsupportedOperationException("Method not decompiled: X.C06000lm.A00(java.lang.String, int, int):java.lang.String");
    }

    public final void A04(String str) {
        List<String> list;
        if (str != null) {
            list = C05890la.A04(C05890la.A02(str, 0, str.length(), HttpUrl.QUERY_ENCODE_SET, true, false, true, true));
        } else {
            list = null;
        }
        this.A06 = list;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c5, code lost:
        if (r13 != (r14 + 4)) goto L_0x0095;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.net.InetAddress A01(java.lang.String r15, int r16, int r17) {
        /*
        // Method dump skipped, instructions count: 212
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06000lm.A01(java.lang.String, int, int):java.net.InetAddress");
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:57)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:15)
        */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0276 A[EDGE_INSN: B:193:0x0276->B:159:0x0276 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0103  */
    public final java.lang.Integer A02(X.C05890la r21, java.lang.String r22) {
        /*
        // Method dump skipped, instructions count: 708
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C06000lm.A02(X.0la, java.lang.String):java.lang.Integer");
    }

    public final C05890la A03() {
        String str;
        if (this.A05 == null) {
            str = "scheme == null";
        } else if (this.A04 != null) {
            return new C05890la(this);
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
            i = C05890la.A01(str);
        }
        if (i != C05890la.A01(str)) {
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

    public C06000lm() {
        ArrayList arrayList = new ArrayList();
        this.A07 = arrayList;
        arrayList.add("");
    }
}
