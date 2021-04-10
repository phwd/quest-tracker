package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLSocket;

/* renamed from: X.bY  reason: case insensitive filesystem */
public final class C0535bY {
    public static final C0535bY A04 = new C0535bY(new C0534bX(false));
    public static final C0535bY A05;
    public static final C0535bY A06;
    public static final C0531bU[] A07;
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
                    if (!C0561by.A09(strArr2[i], str)) {
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
        C0531bU[] bUVarArr = {C0531bU.A0b, C0531bU.A0n, C0531bU.A0e, C0531bU.A0q, C0531bU.A0f, C0531bU.A0r, C0531bU.A0Z, C0531bU.A0l, C0531bU.A0c, C0531bU.A0o, C0531bU.A1c, C0531bU.A1f, C0531bU.A1a, C0531bU.A1d, C0531bU.A1Z};
        A07 = bUVarArr;
        C0534bX bXVar = new C0534bX(true);
        if (bXVar.A03) {
            String[] strArr = new String[15];
            int i = 0;
            do {
                strArr[i] = bUVarArr[i].A00;
                i++;
            } while (i < 15);
            bXVar.A00(strArr);
            EnumC0557bu buVar = EnumC0557bu.TLS_1_0;
            EnumC0557bu[] buVarArr = {EnumC0557bu.TLS_1_3, EnumC0557bu.TLS_1_2, EnumC0557bu.TLS_1_1, buVar};
            if (bXVar.A03) {
                String[] strArr2 = new String[4];
                int i2 = 0;
                do {
                    strArr2[i2] = buVarArr[i2].javaName;
                    i2++;
                } while (i2 < 4);
                bXVar.A01(strArr2);
                if (bXVar.A03) {
                    bXVar.A02 = true;
                    C0535bY bYVar = new C0535bY(bXVar);
                    A06 = bYVar;
                    C0534bX bXVar2 = new C0534bX(bYVar);
                    EnumC0557bu[] buVarArr2 = {buVar};
                    if (bXVar2.A03) {
                        bXVar2.A01(buVarArr2[0].javaName);
                        if (bXVar2.A03) {
                            bXVar2.A02 = true;
                            A05 = new C0535bY(bXVar2);
                            return;
                        }
                        throw new IllegalStateException("no TLS extensions for cleartext connections");
                    }
                    throw new IllegalStateException("no TLS versions for cleartext connections");
                }
                throw new IllegalStateException("no TLS extensions for cleartext connections");
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        throw new IllegalStateException("no cipher suites for cleartext connections");
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
        if (obj instanceof C0535bY) {
            if (obj != this) {
                C0535bY bYVar = (C0535bY) obj;
                boolean z = this.A01;
                if (z != bYVar.A01 || (z && (!Arrays.equals(this.A02, bYVar.A02) || !Arrays.equals(this.A03, bYVar.A03) || this.A00 != bYVar.A00))) {
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
                arrayList.add(C0531bU.A00(str3));
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
                arrayList2.add(EnumC0557bu.forJavaName(str4));
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

    public C0535bY(C0534bX bXVar) {
        this.A01 = bXVar.A03;
        this.A02 = bXVar.A00;
        this.A03 = bXVar.A01;
        this.A00 = bXVar.A02;
    }
}
