package sun.security.x509;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class CertificatePolicySet {
    private final Vector<CertificatePolicyId> ids;

    public CertificatePolicySet(Vector<CertificatePolicyId> ids2) {
        this.ids = ids2;
    }

    public CertificatePolicySet(DerInputStream in) throws IOException {
        DerValue[] seq;
        this.ids = new Vector<>();
        for (DerValue derValue : in.getSequence(5)) {
            this.ids.addElement(new CertificatePolicyId(derValue));
        }
    }

    public String toString() {
        return "CertificatePolicySet:[\n" + this.ids.toString() + "]\n";
    }

    public void encode(DerOutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        for (int i = 0; i < this.ids.size(); i++) {
            this.ids.elementAt(i).encode(tmp);
        }
        out.write((byte) 48, tmp);
    }

    public List<CertificatePolicyId> getCertPolicyIds() {
        return Collections.unmodifiableList(this.ids);
    }
}
