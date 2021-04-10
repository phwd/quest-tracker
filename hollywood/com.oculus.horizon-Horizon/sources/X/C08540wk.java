package X;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: X.0wk  reason: invalid class name and case insensitive filesystem */
public final class C08540wk {
    public static final C08540wk A02 = new C08540wk(new LinkedHashSet(new C08560wm().A00), null);
    public final Set<C08550wl> A00;
    public final AbstractC07780vL A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C08540wk)) {
                return false;
            }
            C08540wk r4 = (C08540wk) obj;
            if (!C08160w5.A09(this.A01, r4.A01) || !this.A00.equals(r4.A00)) {
                return false;
            }
        }
        return true;
    }

    public static String A00(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass006.A05("sha256/", C07700vD.A05(certificate.getPublicKey().getEncoded()).A0C().A08());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public final int hashCode() {
        int i;
        AbstractC07780vL r0 = this.A01;
        if (r0 != null) {
            i = r0.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.A00.hashCode();
    }

    public C08540wk(Set<C08550wl> set, AbstractC07780vL r2) {
        this.A00 = set;
        this.A01 = r2;
    }
}
