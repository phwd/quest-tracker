package sun.security.x509;

import java.net.URI;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class URIName implements GeneralNameInterface {
    private String host;
    private DNSName hostDNS;
    private IPAddressName hostIP;
    private URI uri;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 6;
    }

    public URIName(DerValue derValue) {
        derValue.getIA5String();
        throw null;
    }

    public static URIName nameConstraint(DerValue derValue) {
        derValue.getIA5String();
        throw null;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putIA5String(this.uri.toASCIIString());
    }

    public String toString() {
        return "URIName: " + this.uri.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof URIName)) {
            return false;
        }
        return this.uri.equals(((URIName) obj).getURI());
    }

    public URI getURI() {
        return this.uri;
    }

    public String getName() {
        return this.uri.toString();
    }

    public String getHost() {
        return this.host;
    }

    public Object getHostObject() {
        IPAddressName iPAddressName = this.hostIP;
        if (iPAddressName != null) {
            return iPAddressName;
        }
        return this.hostDNS;
    }

    public int hashCode() {
        return this.uri.hashCode();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        boolean z = false;
        if (generalNameInterface == null || generalNameInterface.getType() != 6) {
            return -1;
        }
        URIName uRIName = (URIName) generalNameInterface;
        String host2 = uRIName.getHost();
        if (host2.equalsIgnoreCase(this.host)) {
            return 0;
        }
        Object hostObject = uRIName.getHostObject();
        if (this.hostDNS == null || !(hostObject instanceof DNSName)) {
            return 3;
        }
        boolean z2 = this.host.charAt(0) == '.';
        if (host2.charAt(0) == '.') {
            z = true;
        }
        int constrains = this.hostDNS.constrains((DNSName) hostObject);
        if (!z2 && !z && (constrains == 2 || constrains == 1)) {
            constrains = 3;
        }
        if (z2 == z || constrains != 0) {
            return constrains;
        }
        return z2 ? 2 : 1;
    }
}
