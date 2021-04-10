package java.security.cert;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class PKIXRevocationChecker extends PKIXCertPathChecker {
    private List<Extension> ocspExtensions = Collections.emptyList();
    private URI ocspResponder;
    private X509Certificate ocspResponderCert;
    private Map<X509Certificate, byte[]> ocspResponses = Collections.emptyMap();
    private Set<Option> options = Collections.emptySet();

    public enum Option {
        ONLY_END_ENTITY,
        PREFER_CRLS,
        NO_FALLBACK,
        SOFT_FAIL
    }

    public abstract List<CertPathValidatorException> getSoftFailExceptions();

    protected PKIXRevocationChecker() {
    }

    public void setOcspResponder(URI uri) {
        this.ocspResponder = uri;
    }

    public URI getOcspResponder() {
        return this.ocspResponder;
    }

    public void setOcspResponderCert(X509Certificate cert) {
        this.ocspResponderCert = cert;
    }

    public X509Certificate getOcspResponderCert() {
        return this.ocspResponderCert;
    }

    public void setOcspExtensions(List<Extension> extensions) {
        List<Extension> list;
        if (extensions == null) {
            list = Collections.emptyList();
        } else {
            list = new ArrayList<>(extensions);
        }
        this.ocspExtensions = list;
    }

    public List<Extension> getOcspExtensions() {
        return Collections.unmodifiableList(this.ocspExtensions);
    }

    public void setOcspResponses(Map<X509Certificate, byte[]> responses) {
        if (responses == null) {
            this.ocspResponses = Collections.emptyMap();
            return;
        }
        Map<X509Certificate, byte[]> copy = new HashMap<>(responses.size());
        for (Map.Entry<X509Certificate, byte[]> e : responses.entrySet()) {
            copy.put(e.getKey(), (byte[]) e.getValue().clone());
        }
        this.ocspResponses = copy;
    }

    public Map<X509Certificate, byte[]> getOcspResponses() {
        Map<X509Certificate, byte[]> copy = new HashMap<>(this.ocspResponses.size());
        for (Map.Entry<X509Certificate, byte[]> e : this.ocspResponses.entrySet()) {
            copy.put(e.getKey(), (byte[]) e.getValue().clone());
        }
        return copy;
    }

    public void setOptions(Set<Option> options2) {
        Set<Option> set;
        if (options2 == null) {
            set = Collections.emptySet();
        } else {
            set = new HashSet<>(options2);
        }
        this.options = set;
    }

    public Set<Option> getOptions() {
        return Collections.unmodifiableSet(this.options);
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public PKIXRevocationChecker clone() {
        PKIXRevocationChecker copy = (PKIXRevocationChecker) super.clone();
        copy.ocspExtensions = new ArrayList(this.ocspExtensions);
        copy.ocspResponses = new HashMap(this.ocspResponses);
        for (Map.Entry<X509Certificate, byte[]> entry : copy.ocspResponses.entrySet()) {
            entry.setValue((byte[]) entry.getValue().clone());
        }
        copy.options = new HashSet(this.options);
        return copy;
    }
}
