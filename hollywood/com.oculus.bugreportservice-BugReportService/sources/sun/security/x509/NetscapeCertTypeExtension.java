package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.ObjectIdentifier;

public class NetscapeCertTypeExtension extends Extension implements CertAttrSet {
    private static final int[] CertType_data = {2, 16, 840, 1, 113730, 1, 1};
    public static ObjectIdentifier NetscapeCertType_Id;
    private static final Vector mAttributeNames = new Vector();
    private static MapEntry[] mMapData = {new MapEntry("ssl_client", 0), new MapEntry("ssl_server", 1), new MapEntry("s_mime", 2), new MapEntry("object_signing", 3), new MapEntry("ssl_ca", 5), new MapEntry("s_mime_ca", 6), new MapEntry("object_signing_ca", 7)};
    private boolean[] bitString = new boolean[0];

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "NetscapeCertType";
    }

    static {
        try {
            NetscapeCertType_Id = new ObjectIdentifier(CertType_data);
        } catch (IOException unused) {
        }
        for (MapEntry mapEntry : mMapData) {
            mAttributeNames.add(mapEntry.mName);
        }
    }

    private static class MapEntry {
        String mName;
        int mPosition;

        MapEntry(String str, int i) {
            this.mName = str;
            this.mPosition = i;
        }
    }

    private void encodeThis() {
        DerOutputStream derOutputStream = new DerOutputStream();
        derOutputStream.putTruncatedUnalignedBitString(new BitArray(this.bitString));
        this.extensionValue = derOutputStream.toByteArray();
    }

    private boolean isSet(int i) {
        boolean[] zArr = this.bitString;
        return i < zArr.length && zArr[i];
    }

    public NetscapeCertTypeExtension() {
        this.extensionId = NetscapeCertType_Id;
        this.critical = true;
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("NetscapeCertType [\n");
        if (isSet(0)) {
            sb.append("   SSL client\n");
        }
        if (isSet(1)) {
            sb.append("   SSL server\n");
        }
        if (isSet(2)) {
            sb.append("   S/MIME\n");
        }
        if (isSet(3)) {
            sb.append("   Object Signing\n");
        }
        if (isSet(5)) {
            sb.append("   SSL CA\n");
        }
        if (isSet(6)) {
            sb.append("   S/MIME CA\n");
        }
        if (isSet(7)) {
            sb.append("   Object Signing CA");
        }
        sb.append("]\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = NetscapeCertType_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }
}
