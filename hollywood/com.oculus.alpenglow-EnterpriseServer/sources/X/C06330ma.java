package X;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: X.0ma  reason: invalid class name and case insensitive filesystem */
public final class C06330ma {
    public static final C06330ma A02 = new C06330ma(new LinkedHashSet(new C06370me().A00), null);
    public final Set<C06340mb> A00;
    public final AbstractC04660hF A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C06330ma)) {
                return false;
            }
            C06330ma r4 = (C06330ma) obj;
            if (!C05570jz.A09(this.A01, r4.A01) || !this.A00.equals(r4.A00)) {
                return false;
            }
        }
        return true;
    }

    public static String A00(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass006.A05("sha256/", C04610h7.A05(((X509Certificate) certificate).getPublicKey().getEncoded()).A0C().A08());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public final int hashCode() {
        int i;
        AbstractC04660hF r0 = this.A01;
        if (r0 != null) {
            i = r0.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.A00.hashCode();
    }

    public C06330ma(Set<C06340mb> set, AbstractC04660hF r2) {
        this.A00 = set;
        this.A01 = r2;
    }
}
