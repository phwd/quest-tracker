package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLSocket;

/* renamed from: X.dz  reason: case insensitive filesystem */
public final class C0376dz {
    public static final C0376dz A04 = new C0376dz(new e0(false));
    public static final C0376dz A05;
    public static final C0376dz A06;
    public static final e3[] A07;
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
                    if (!dZ.A09(strArr2[i], str)) {
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
        e3[] e3VarArr = {e3.A0b, e3.A0n, e3.A0e, e3.A0q, e3.A0f, e3.A0r, e3.A0Z, e3.A0l, e3.A0c, e3.A0o, e3.A1c, e3.A1f, e3.A1a, e3.A1d, e3.A1Z};
        A07 = e3VarArr;
        e0 e0Var = new e0(true);
        e0Var.A02(e3VarArr);
        EnumC0356dd ddVar = EnumC0356dd.TLS_1_3;
        EnumC0356dd ddVar2 = EnumC0356dd.TLS_1_2;
        EnumC0356dd ddVar3 = EnumC0356dd.TLS_1_1;
        EnumC0356dd ddVar4 = EnumC0356dd.TLS_1_0;
        e0Var.A03(ddVar, ddVar2, ddVar3, ddVar4);
        if (e0Var.A03) {
            e0Var.A02 = true;
            C0376dz dzVar = new C0376dz(e0Var);
            A06 = dzVar;
            e0 e0Var2 = new e0(dzVar);
            e0Var2.A03(ddVar4);
            if (e0Var2.A03) {
                e0Var2.A02 = true;
                A05 = new C0376dz(e0Var2);
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
        if (obj instanceof C0376dz) {
            if (obj != this) {
                C0376dz dzVar = (C0376dz) obj;
                boolean z = this.A01;
                if (z != dzVar.A01 || (z && (!Arrays.equals(this.A02, dzVar.A02) || !Arrays.equals(this.A03, dzVar.A03) || this.A00 != dzVar.A00))) {
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
                arrayList.add(e3.A00(str3));
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
                arrayList2.add(EnumC0356dd.forJavaName(str4));
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

    public C0376dz(e0 e0Var) {
        this.A01 = e0Var.A03;
        this.A02 = e0Var.A00;
        this.A03 = e0Var.A01;
        this.A00 = e0Var.A02;
    }
}
