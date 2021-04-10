package X;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

/* renamed from: X.bT  reason: case insensitive filesystem */
public final class C0530bT {
    public static final C0530bT A02 = new C0530bT(new LinkedHashSet(new ArrayList()), null);
    public final Set A00;
    public final AbstractC0596cf A01;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (!(obj instanceof C0530bT)) {
                return false;
            }
            C0530bT bTVar = (C0530bT) obj;
            if (!C0561by.A09(this.A01, bTVar.A01) || !this.A00.equals(bTVar.A00)) {
                return false;
            }
        }
        return true;
    }

    public static String A00(Certificate certificate) {
        if (certificate instanceof X509Certificate) {
            return AnonymousClass08.A04("sha256/", C0603cm.A03(certificate.getPublicKey().getEncoded()).A0A().A06());
        }
        throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
    }

    public final int hashCode() {
        int i;
        AbstractC0596cf cfVar = this.A01;
        if (cfVar != null) {
            i = cfVar.hashCode();
        } else {
            i = 0;
        }
        return (i * 31) + this.A00.hashCode();
    }

    public C0530bT(Set set, AbstractC0596cf cfVar) {
        this.A00 = set;
        this.A01 = cfVar;
    }
}
