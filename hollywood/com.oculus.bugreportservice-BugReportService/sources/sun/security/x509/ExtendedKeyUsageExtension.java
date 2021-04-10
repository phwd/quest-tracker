package sun.security.x509;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public class ExtendedKeyUsageExtension extends Extension implements CertAttrSet {
    private static final int[] OCSPSigningOidData = {1, 3, 6, 1, 5, 5, 7, 3, 9};
    private static final int[] anyExtendedKeyUsageOidData = {2, 5, 29, 37, 0};
    private static final int[] clientAuthOidData = {1, 3, 6, 1, 5, 5, 7, 3, 2};
    private static final int[] codeSigningOidData = {1, 3, 6, 1, 5, 5, 7, 3, 3};
    private static final int[] emailProtectionOidData = {1, 3, 6, 1, 5, 5, 7, 3, 4};
    private static final int[] ipsecEndSystemOidData = {1, 3, 6, 1, 5, 5, 7, 3, 5};
    private static final int[] ipsecTunnelOidData = {1, 3, 6, 1, 5, 5, 7, 3, 6};
    private static final int[] ipsecUserOidData = {1, 3, 6, 1, 5, 5, 7, 3, 7};
    private static final Map map = new HashMap();
    private static final int[] serverAuthOidData = {1, 3, 6, 1, 5, 5, 7, 3, 1};
    private static final int[] timeStampingOidData = {1, 3, 6, 1, 5, 5, 7, 3, 8};
    private Vector keyUsages;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "ExtendedKeyUsage";
    }

    static {
        map.put(ObjectIdentifier.newInternal(anyExtendedKeyUsageOidData), "anyExtendedKeyUsage");
        map.put(ObjectIdentifier.newInternal(serverAuthOidData), "serverAuth");
        map.put(ObjectIdentifier.newInternal(clientAuthOidData), "clientAuth");
        map.put(ObjectIdentifier.newInternal(codeSigningOidData), "codeSigning");
        map.put(ObjectIdentifier.newInternal(emailProtectionOidData), "emailProtection");
        map.put(ObjectIdentifier.newInternal(ipsecEndSystemOidData), "ipsecEndSystem");
        map.put(ObjectIdentifier.newInternal(ipsecTunnelOidData), "ipsecTunnel");
        map.put(ObjectIdentifier.newInternal(ipsecUserOidData), "ipsecUser");
        map.put(ObjectIdentifier.newInternal(timeStampingOidData), "timeStamping");
        map.put(ObjectIdentifier.newInternal(OCSPSigningOidData), "OCSPSigning");
    }

    private void encodeThis() {
        Vector vector = this.keyUsages;
        if (vector == null || vector.isEmpty()) {
            this.extensionValue = null;
            return;
        }
        DerOutputStream derOutputStream = new DerOutputStream();
        DerOutputStream derOutputStream2 = new DerOutputStream();
        for (int i = 0; i < this.keyUsages.size(); i++) {
            derOutputStream2.putOID((ObjectIdentifier) this.keyUsages.elementAt(i));
        }
        derOutputStream.write((byte) 48, derOutputStream2);
        this.extensionValue = derOutputStream.toByteArray();
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        String str;
        Vector vector = this.keyUsages;
        if (vector == null) {
            return "";
        }
        boolean z = true;
        Iterator it = vector.iterator();
        String str2 = "  ";
        while (it.hasNext()) {
            ObjectIdentifier objectIdentifier = (ObjectIdentifier) it.next();
            if (!z) {
                str2 = str2 + "\n  ";
            }
            String str3 = (String) map.get(objectIdentifier);
            if (str3 != null) {
                str = str2 + str3;
            } else {
                str = str2 + objectIdentifier.toString();
            }
            str2 = str;
            z = false;
        }
        return super.toString() + "ExtendedKeyUsages [\n" + str2 + "\n]\n";
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.ExtendedKeyUsage_Id;
            this.critical = false;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
