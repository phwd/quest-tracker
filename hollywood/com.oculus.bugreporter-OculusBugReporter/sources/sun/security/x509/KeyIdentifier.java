package sun.security.x509;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Arrays;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class KeyIdentifier {
    private byte[] octetString;

    public KeyIdentifier(byte[] octetString2) {
        this.octetString = (byte[]) octetString2.clone();
    }

    public KeyIdentifier(DerValue val) throws IOException {
        this.octetString = val.getOctetString();
    }

    public KeyIdentifier(PublicKey pubKey) throws IOException {
        DerValue algAndKey = new DerValue(pubKey.getEncoded());
        if (algAndKey.tag == 48) {
            AlgorithmId.parse(algAndKey.data.getDerValue());
            byte[] key = algAndKey.data.getUnalignedBitString().toByteArray();
            try {
                MessageDigest md = MessageDigest.getInstance("SHA1");
                md.update(key);
                this.octetString = md.digest();
            } catch (NoSuchAlgorithmException e) {
                throw new IOException("SHA1 not supported");
            }
        } else {
            throw new IOException("PublicKey value is not a valid X.509 public key");
        }
    }

    public byte[] getIdentifier() {
        return (byte[]) this.octetString.clone();
    }

    public String toString() {
        HexDumpEncoder encoder = new HexDumpEncoder();
        return ("KeyIdentifier [\n" + encoder.encodeBuffer(this.octetString)) + "]\n";
    }

    /* access modifiers changed from: package-private */
    public void encode(DerOutputStream out) throws IOException {
        out.putOctetString(this.octetString);
    }

    public int hashCode() {
        int retval = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.octetString;
            if (i >= bArr.length) {
                return retval;
            }
            retval += bArr[i] * i;
            i++;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeyIdentifier)) {
            return false;
        }
        return Arrays.equals(this.octetString, ((KeyIdentifier) other).octetString);
    }
}
