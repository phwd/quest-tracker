package X;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;

public final class e4 {
    public static final e4 A02 = new e4(new LinkedHashSet(new e6().A00), null);
    public final Set<e5> A00;
    public final cq A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof e4)) {
                return false;
            }
            e4 e4Var = (e4) obj;
            if (!dZ.A09(this.A01, e4Var.A01) || !this.A00.equals(e4Var.A00)) {
                return false;
            }
        }
        return true;
    }

    public static String A00(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass06.A04("sha256/", ci.A05(certificate.getPublicKey().getEncoded()).A0C().A08());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public final int hashCode() {
        int i;
        cq cqVar = this.A01;
        if (cqVar != null) {
            i = cqVar.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.A00.hashCode();
    }

    public e4(Set<e5> set, cq cqVar) {
        this.A00 = set;
        this.A01 = cqVar;
    }
}
