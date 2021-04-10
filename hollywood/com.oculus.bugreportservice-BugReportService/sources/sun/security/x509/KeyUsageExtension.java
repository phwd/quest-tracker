package sun.security.x509;

import java.io.IOException;
import java.io.OutputStream;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;

public class KeyUsageExtension extends Extension implements CertAttrSet {
    private boolean[] bitString;

    @Override // sun.security.x509.CertAttrSet
    public String getName() {
        return "KeyUsage";
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

    public KeyUsageExtension(boolean[] zArr) {
        this.bitString = zArr;
        this.extensionId = PKIXExtensions.KeyUsage_Id;
        this.critical = true;
        encodeThis();
    }

    public KeyUsageExtension() {
        this.extensionId = PKIXExtensions.KeyUsage_Id;
        this.critical = true;
        this.bitString = new boolean[0];
    }

    public Boolean get(String str) {
        if (str.equalsIgnoreCase("digital_signature")) {
            return Boolean.valueOf(isSet(0));
        }
        if (str.equalsIgnoreCase("non_repudiation")) {
            return Boolean.valueOf(isSet(1));
        }
        if (str.equalsIgnoreCase("key_encipherment")) {
            return Boolean.valueOf(isSet(2));
        }
        if (str.equalsIgnoreCase("data_encipherment")) {
            return Boolean.valueOf(isSet(3));
        }
        if (str.equalsIgnoreCase("key_agreement")) {
            return Boolean.valueOf(isSet(4));
        }
        if (str.equalsIgnoreCase("key_certsign")) {
            return Boolean.valueOf(isSet(5));
        }
        if (str.equalsIgnoreCase("crl_sign")) {
            return Boolean.valueOf(isSet(6));
        }
        if (str.equalsIgnoreCase("encipher_only")) {
            return Boolean.valueOf(isSet(7));
        }
        if (str.equalsIgnoreCase("decipher_only")) {
            return Boolean.valueOf(isSet(8));
        }
        throw new IOException("Attribute name not recognized by CertAttrSet:KeyUsage.");
    }

    @Override // sun.security.x509.Extension
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("KeyUsage [\n");
        if (isSet(0)) {
            sb.append("  DigitalSignature\n");
        }
        if (isSet(1)) {
            sb.append("  Non_repudiation\n");
        }
        if (isSet(2)) {
            sb.append("  Key_Encipherment\n");
        }
        if (isSet(3)) {
            sb.append("  Data_Encipherment\n");
        }
        if (isSet(4)) {
            sb.append("  Key_Agreement\n");
        }
        if (isSet(5)) {
            sb.append("  Key_CertSign\n");
        }
        if (isSet(6)) {
            sb.append("  Crl_Sign\n");
        }
        if (isSet(7)) {
            sb.append("  Encipher_Only\n");
        }
        if (isSet(8)) {
            sb.append("  Decipher_Only\n");
        }
        sb.append("]\n");
        return sb.toString();
    }

    @Override // sun.security.x509.CertAttrSet, sun.security.x509.Extension
    public void encode(OutputStream outputStream) {
        DerOutputStream derOutputStream = new DerOutputStream();
        if (this.extensionValue == null) {
            this.extensionId = PKIXExtensions.KeyUsage_Id;
            this.critical = true;
            encodeThis();
        }
        super.encode(derOutputStream);
        outputStream.write(derOutputStream.toByteArray());
    }

    public boolean[] getBits() {
        return (boolean[]) this.bitString.clone();
    }
}
