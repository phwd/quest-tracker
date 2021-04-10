package sun.security.x509;

import java.io.OutputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import sun.security.util.DerOutputStream;

public class PolicyMappingsExtension extends Extension implements CertAttrSet {
    private List maps = Collections.emptyList();

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "PolicyMappings";
    }

    private void encodeThis() {
        List list = this.maps;
        if (list == null || list.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        Iterator it = this.maps.iterator();
        if (!it.hasNext()) {
            derOutputStream.write((byte) 48, derOutputStream2);
            this.extensionValue = derOutputStream.toByteArray();
            return;
        }
        ((CertificatePolicyMap) it.next()).encode(derOutputStream2);
        throw null;
    }

    public PolicyMappingsExtension() {
        this.extensionId = PKIXExtensions.KeyUsage_Id;
        this.critical = false;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        if (this.maps == null) {
            return "";
        }
        return super.toString() + "PolicyMappings [\n" + this.maps.toString() + "]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.PolicyMappings_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
