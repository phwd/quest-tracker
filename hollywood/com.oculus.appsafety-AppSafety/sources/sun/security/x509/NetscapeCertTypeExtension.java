package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.xml.datatype.DatatypeConstants;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class NetscapeCertTypeExtension extends Extension implements CertAttrSet<String> {
    private static final int[] CertType_data = {2, 16, DatatypeConstants.MIN_TIMEZONE_OFFSET, 1, 113730, 1, 1};
    public static final String IDENT = "x509.info.extensions.NetscapeCertType";
    public static final String NAME = "NetscapeCertType";
    public static ObjectIdentifier NetscapeCertType_Id = null;
    public static final String OBJECT_SIGNING = "object_signing";
    public static final String OBJECT_SIGNING_CA = "object_signing_ca";
    public static final String SSL_CA = "ssl_ca";
    public static final String SSL_CLIENT = "ssl_client";
    public static final String SSL_SERVER = "ssl_server";
    public static final String S_MIME = "s_mime";
    public static final String S_MIME_CA = "s_mime_ca";
    private static final Vector<String> mAttributeNames = new Vector<>();
    private static MapEntry[] mMapData = {new MapEntry(SSL_CLIENT, 0), new MapEntry(SSL_SERVER, 1), new MapEntry(S_MIME, 2), new MapEntry(OBJECT_SIGNING, 3), new MapEntry(SSL_CA, 5), new MapEntry(S_MIME_CA, 6), new MapEntry(OBJECT_SIGNING_CA, 7)};
    private boolean[] bitString;

    static {
        try {
            NetscapeCertType_Id = new ObjectIdentifier(CertType_data);
        } catch (IOException e) {
        }
        for (MapEntry entry : mMapData) {
            mAttributeNames.add(entry.mName);
        }
    }

    /* access modifiers changed from: private */
    public static class MapEntry {
        String mName;
        int mPosition;

        MapEntry(String name, int position) {
            this.mName = name;
            this.mPosition = position;
        }
    }

    private static int getPosition(String name) throws IOException {
        int i = 0;
        while (true) {
            MapEntry[] mapEntryArr = mMapData;
            if (i >= mapEntryArr.length) {
                throw new IOException("Attribute name [" + name + "] not recognized by CertAttrSet:NetscapeCertType.");
            } else if (name.equalsIgnoreCase(mapEntryArr[i].mName)) {
                return mMapData[i].mPosition;
            } else {
                i++;
            }
        }
    }

    private void encodeThis() throws IOException {
        DerOutputStream os = new DerOutputStream();
        os.putTruncatedUnalignedBitString(new BitArray(this.bitString));
        this.extensionValue = os.toByteArray();
    }

    private boolean isSet(int position) {
        boolean[] zArr = this.bitString;
        return position < zArr.length && zArr[position];
    }

    private void set(int position, boolean val) {
        boolean[] zArr = this.bitString;
        if (position >= zArr.length) {
            boolean[] tmp = new boolean[(position + 1)];
            System.arraycopy((Object) zArr, 0, (Object) tmp, 0, zArr.length);
            this.bitString = tmp;
        }
        this.bitString[position] = val;
    }

    public NetscapeCertTypeExtension(byte[] bitString2) throws IOException {
        this.bitString = new BitArray(bitString2.length * 8, bitString2).toBooleanArray();
        this.extensionId = NetscapeCertType_Id;
        this.critical = true;
        encodeThis();
    }

    public NetscapeCertTypeExtension(boolean[] bitString2) throws IOException {
        this.bitString = bitString2;
        this.extensionId = NetscapeCertType_Id;
        this.critical = true;
        encodeThis();
    }

    public NetscapeCertTypeExtension(Boolean critical, Object value) throws IOException {
        this.extensionId = NetscapeCertType_Id;
        this.critical = critical.booleanValue();
        this.extensionValue = (byte[]) value;
        this.bitString = new DerValue(this.extensionValue).getUnalignedBitString().toBooleanArray();
    }

    public NetscapeCertTypeExtension() {
        this.extensionId = NetscapeCertType_Id;
        this.critical = true;
        this.bitString = new boolean[0];
    }

    @Override // sun.security.x509.CertAttrSet
    public void set(String name, Object obj) throws IOException {
        if (obj instanceof Boolean) {
            set(getPosition(name), ((Boolean) obj).booleanValue());
            encodeThis();
            return;
        }
        throw new IOException("Attribute must be of type Boolean.");
    }

    @Override // sun.security.x509.CertAttrSet
    public Boolean get(String name) throws IOException {
        return Boolean.valueOf(isSet(getPosition(name)));
    }

    @Override // sun.security.x509.CertAttrSet
    public void delete(String name) throws IOException {
        set(getPosition(name), false);
        encodeThis();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
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

    @Override // sun.security.x509.CertAttrSet, java.security.cert.Extension, sun.security.x509.Extension
    public void encode(OutputStream out) throws IOException {
        DerOutputStream tmp = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = NetscapeCertType_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(tmp);
        out.write(tmp.toByteArray());
    }

    @Override // sun.security.x509.CertAttrSet
    public Enumeration<String> getElements() {
        return mAttributeNames.elements();
    }

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return NAME;
    }

    public boolean[] getKeyUsageMappedBits() {
        KeyUsageExtension keyUsage = new KeyUsageExtension();
        Boolean val = Boolean.TRUE;
        try {
            if (isSet(getPosition(SSL_CLIENT)) || isSet(getPosition(S_MIME)) || isSet(getPosition(OBJECT_SIGNING))) {
                keyUsage.set(KeyUsageExtension.DIGITAL_SIGNATURE, val);
            }
            if (isSet(getPosition(SSL_SERVER))) {
                keyUsage.set(KeyUsageExtension.KEY_ENCIPHERMENT, val);
            }
            if (isSet(getPosition(SSL_CA)) || isSet(getPosition(S_MIME_CA)) || isSet(getPosition(OBJECT_SIGNING_CA))) {
                keyUsage.set(KeyUsageExtension.KEY_CERTSIGN, val);
            }
        } catch (IOException e) {
        }
        return keyUsage.getBits();
    }
}
