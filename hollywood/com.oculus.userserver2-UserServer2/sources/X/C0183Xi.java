package X;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: X.Xi  reason: case insensitive filesystem */
public final class C0183Xi {
    public static final C0183Xi A02 = new C0183Xi(new LinkedHashSet(new C0185Xk().A00), null);
    public final Set<C0184Xj> A00;
    public final WU A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C0183Xi)) {
                return false;
            }
            C0183Xi xi = (C0183Xi) obj;
            if (!XD.A09(this.A01, xi.A01) || !this.A00.equals(xi.A00)) {
                return false;
            }
        }
        return true;
    }

    public static String A00(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass06.A03("sha256/", WM.A05(certificate.getPublicKey().getEncoded()).A0C().A08());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public final int hashCode() {
        int i;
        WU wu = this.A01;
        if (wu != null) {
            i = wu.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.A00.hashCode();
    }

    public C0183Xi(Set<C0184Xj> set, WU wu) {
        this.A00 = set;
        this.A01 = wu;
    }
}
