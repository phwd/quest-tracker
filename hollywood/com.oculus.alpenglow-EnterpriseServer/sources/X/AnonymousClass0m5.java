package X;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0m5  reason: invalid class name */
public final class AnonymousClass0m5 {
    public static final AnonymousClass0m5 A04 = new AnonymousClass0m5(new AnonymousClass0m6(false));
    public static final AnonymousClass0m5 A05;
    public static final AnonymousClass0m5 A06;
    public static final C06320mZ[] A07;
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
                    if (!C05570jz.A09(strArr2[i], str)) {
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
        C06320mZ[] r2 = {C06320mZ.A0b, C06320mZ.A0n, C06320mZ.A0e, C06320mZ.A0q, C06320mZ.A0f, C06320mZ.A0r, C06320mZ.A0Z, C06320mZ.A0l, C06320mZ.A0c, C06320mZ.A0o, C06320mZ.A1c, C06320mZ.A1f, C06320mZ.A1a, C06320mZ.A1d, C06320mZ.A1Z};
        A07 = r2;
        AnonymousClass0m6 r6 = new AnonymousClass0m6(true);
        r6.A02(r2);
        EnumC05630kA r5 = EnumC05630kA.TLS_1_3;
        EnumC05630kA r3 = EnumC05630kA.TLS_1_2;
        EnumC05630kA r22 = EnumC05630kA.TLS_1_1;
        EnumC05630kA r4 = EnumC05630kA.TLS_1_0;
        r6.A03(r5, r3, r22, r4);
        if (r6.A03) {
            r6.A02 = true;
            AnonymousClass0m5 r23 = new AnonymousClass0m5(r6);
            A06 = r23;
            AnonymousClass0m6 r32 = new AnonymousClass0m6(r23);
            r32.A03(r4);
            if (r32.A03) {
                r32.A02 = true;
                A05 = new AnonymousClass0m5(r32);
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
        if (obj instanceof AnonymousClass0m5) {
            if (obj != this) {
                AnonymousClass0m5 r5 = (AnonymousClass0m5) obj;
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
                arrayList.add(C06320mZ.A00(str3));
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
                arrayList2.add(EnumC05630kA.forJavaName(str4));
            }
            str2 = Collections.unmodifiableList(arrayList2).toString();
        }
        return "ConnectionSpec(cipherSuites=" + str + ", tlsVersions=" + str2 + ", supportsTlsExtensions=" + this.A00 + ")";
    }

    public AnonymousClass0m5(AnonymousClass0m6 r2) {
        this.A01 = r2.A03;
        this.A02 = r2.A00;
        this.A03 = r2.A01;
        this.A00 = r2.A02;
    }
}
