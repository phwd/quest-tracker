package X;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* renamed from: X.0M1  reason: invalid class name */
public final class AnonymousClass0M1 extends AbstractC07750vI {
    public final Map<X500Principal, Set<X509Certificate>> A00 = new LinkedHashMap();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof AnonymousClass0M1) && ((AnonymousClass0M1) obj).A00.equals(this.A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass0M1(X509Certificate... x509CertificateArr) {
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Set<X509Certificate> set = this.A00.get(subjectX500Principal);
            if (set == null) {
                set = new LinkedHashSet<>(1);
                this.A00.put(subjectX500Principal, set);
            }
            set.add(x509Certificate);
        }
    }

    @Override // X.AbstractC07750vI
    public final X509Certificate A00(X509Certificate x509Certificate) {
        Set<X509Certificate> set = this.A00.get(x509Certificate.getIssuerX500Principal());
        if (set != null) {
            for (X509Certificate x509Certificate2 : set) {
                try {
                    x509Certificate.verify(x509Certificate2.getPublicKey());
                    return x509Certificate2;
                } catch (Exception unused) {
                }
            }
        }
        return null;
    }
}
