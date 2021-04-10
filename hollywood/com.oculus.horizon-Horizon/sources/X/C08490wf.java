package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0wf  reason: invalid class name and case insensitive filesystem */
public final class C08490wf {
    public static final C08490wf A04 = new C08490wf(new C08500wg(false));
    public static final C08490wf A05;
    public static final C08490wf A06;
    public static final C08530wj[] A07;
    public final boolean A00;
    public final boolean A01;
    public final String[] A02;
    public final String[] A03;

    public static boolean A00(String[] strArr, String[] strArr2) {
        int length;
        if (!(strArr2 == null || (r5 = strArr.length) == 0 || (length = strArr2.length) == 0)) {
            for (String str : strArr) {
                int i = 0;
                while (true) {
                    if (!C08160w5.A09(strArr2[i], str)) {
                        i++;
                        if (i >= length) {
                            break;
                        }
                    } else if (i != -1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static {
        C08530wj[] r6 = {C08530wj.A0b, C08530wj.A0n, C08530wj.A0e, C08530wj.A0q, C08530wj.A0f, C08530wj.A0r, C08530wj.A0Z, C08530wj.A0l, C08530wj.A0c, C08530wj.A0o, C08530wj.A1c, C08530wj.A1f, C08530wj.A1a, C08530wj.A1d, C08530wj.A1Z};
        A07 = r6;
        C08500wg r3 = new C08500wg(true);
        r3.A02(r6);
        EnumC08190w9 r2 = EnumC08190w9.TLS_1_0;
        r3.A03(EnumC08190w9.TLS_1_3, EnumC08190w9.TLS_1_2, EnumC08190w9.TLS_1_1, r2);
        if (r3.A03) {
            r3.A02 = true;
            C08490wf r0 = new C08490wf(r3);
            A06 = r0;
            C08500wg r1 = new C08500wg(r0);
            r1.A03(r2);
            if (r1.A03) {
                r1.A02 = true;
                A05 = new C08490wf(r1);
                return;
            }
        }
        throw new IllegalStateException("no TLS extensions for cleartext connections");
    }

    public final boolean A01(SSLSocket sSLSocket) {
        String[] strArr;
        if (!this.A01 || ((strArr = this.A03) != null && !A00(strArr, sSLSocket.getEnabledProtocols()))) {
            return false;
        }
        String[] strArr2 = this.A02;
        if (strArr2 == null || A00(strArr2, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C08490wf) {
            if (obj != this) {
                C08490wf r5 = (C08490wf) obj;
                boolean z = this.A01;
                if (z != r5.A01 || (z && (!Arrays.equals(this.A02, r5.A02) || !Arrays.equals(this.A03, r5.A03) || this.A00 != r5.A00))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        if (this.A01) {
            return ((((527 + Arrays.hashCode(this.A02)) * 31) + Arrays.hashCode(this.A03)) * 31) + (!this.A00 ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        String str;
        if (!this.A01) {
            return "ConnectionSpec()";
        }
        String[] strArr = this.A02;
        String str2 = "[all enabled]";
        if (strArr != null) {
            int length = strArr.length;
            ArrayList arrayList = new ArrayList(length);
            for (String str3 : strArr) {
                arrayList.add(C08530wj.A00(str3));
            }
            str = Collections.unmodifiableList(arrayList).toString();
        } else {
            str = str2;
        }
        String[] strArr2 = this.A03;
        if (strArr2 != null) {
            int length2 = strArr2.length;
            ArrayList arrayList2 = new ArrayList(length2);
            for (String str4 : strArr2) {
                arrayList2.add(EnumC08190w9.forJavaName(str4));
            }
            str2 = Collections.unmodifiableList(arrayList2).toString();
        }
        StringBuilder sb = new StringBuilder("ConnectionSpec(cipherSuites=");
        sb.append(str);
        sb.append(", tlsVersions=");
        sb.append(str2);
        sb.append(", supportsTlsExtensions=");
        sb.append(this.A00);
        sb.append(")");
        return sb.toString();
    }

    public C08490wf(C08500wg r2) {
        this.A01 = r2.A03;
        this.A02 = r2.A00;
        this.A03 = r2.A01;
        this.A00 = r2.A02;
    }
}
