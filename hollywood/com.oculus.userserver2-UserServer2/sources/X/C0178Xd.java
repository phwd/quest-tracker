package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLSocket;

/* renamed from: X.Xd  reason: case insensitive filesystem */
public final class C0178Xd {
    public static final C0178Xd A04 = new C0178Xd(new C0179Xe(false));
    public static final C0178Xd A05;
    public static final C0178Xd A06;
    public static final C0182Xh[] A07;
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
                    if (!XD.A09(strArr2[i], str)) {
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
        C0182Xh[] xhArr = {C0182Xh.A0b, C0182Xh.A0n, C0182Xh.A0e, C0182Xh.A0q, C0182Xh.A0f, C0182Xh.A0r, C0182Xh.A0Z, C0182Xh.A0l, C0182Xh.A0c, C0182Xh.A0o, C0182Xh.A1c, C0182Xh.A1f, C0182Xh.A1a, C0182Xh.A1d, C0182Xh.A1Z};
        A07 = xhArr;
        C0179Xe xe = new C0179Xe(true);
        xe.A02(xhArr);
        XH xh = XH.TLS_1_3;
        XH xh2 = XH.TLS_1_2;
        XH xh3 = XH.TLS_1_1;
        XH xh4 = XH.TLS_1_0;
        xe.A03(xh, xh2, xh3, xh4);
        if (xe.A03) {
            xe.A02 = true;
            C0178Xd xd = new C0178Xd(xe);
            A06 = xd;
            C0179Xe xe2 = new C0179Xe(xd);
            xe2.A03(xh4);
            if (xe2.A03) {
                xe2.A02 = true;
                A05 = new C0178Xd(xe2);
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
        if (obj instanceof C0178Xd) {
            if (obj != this) {
                C0178Xd xd = (C0178Xd) obj;
                boolean z = this.A01;
                if (z != xd.A01 || (z && (!Arrays.equals(this.A02, xd.A02) || !Arrays.equals(this.A03, xd.A03) || this.A00 != xd.A00))) {
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
                arrayList.add(C0182Xh.A00(str3));
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
                arrayList2.add(XH.forJavaName(str4));
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

    public C0178Xd(C0179Xe xe) {
        this.A01 = xe.A03;
        this.A02 = xe.A00;
        this.A03 = xe.A01;
        this.A00 = xe.A02;
    }
}
