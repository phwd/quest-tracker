package X;

import java.security.cert.X509Certificate;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;

/* renamed from: X.tA  reason: case insensitive filesystem */
public final class C1112tA extends AbstractC0599ci {
    public final Map A00 = new LinkedHashMap();

    public final boolean equals(Object obj) {
        return obj == this || ((obj instanceof C1112tA) && ((C1112tA) obj).A00.equals(this.A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public C1112tA(X509Certificate... x509CertificateArr) {
        for (X509Certificate x509Certificate : x509CertificateArr) {
            X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
            Set set = (Set) this.A00.get(subjectX500Principal);
            if (set == null) {
                set = new LinkedHashSet(1);
                this.A00.put(subjectX500Principal, set);
            }
            set.add(x509Certificate);
        }
    }
}
