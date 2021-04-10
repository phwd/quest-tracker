package java.security.cert;

import java.util.Collection;
import java.util.Collections;

public class CollectionCertStoreParameters implements CertStoreParameters {
    private Collection<?> coll;

    public CollectionCertStoreParameters(Collection<?> collection) {
        if (collection != null) {
            this.coll = collection;
            return;
        }
        throw new NullPointerException();
    }

    public CollectionCertStoreParameters() {
        this.coll = Collections.EMPTY_SET;
    }

    public Collection<?> getCollection() {
        return this.coll;
    }

    @Override // java.security.cert.CertStoreParameters
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString(), e);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("CollectionCertStoreParameters: [\n");
        sb.append("  collection: " + ((Object) this.coll) + "\n");
        sb.append("]");
        return sb.toString();
    }
}
