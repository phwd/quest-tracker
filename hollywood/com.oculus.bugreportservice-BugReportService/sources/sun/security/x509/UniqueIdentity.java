package sun.security.x509;

import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class UniqueIdentity {
    private BitArray id;

    public UniqueIdentity(DerValue derValue) {
        this.id = derValue.getUnalignedBitString(true);
    }

    public String toString() {
        new StringBuilder().append("UniqueIdentity:");
        this.id.toString();
        throw null;
    }

    public void encode(DerOutputStream derOutputStream, byte b) {
        byte[] byteArray = this.id.toByteArray();
        int length = (byteArray.length * 8) - this.id.length();
        derOutputStream.write(b);
        derOutputStream.putLength(byteArray.length + 1);
        derOutputStream.write(length);
        derOutputStream.write(byteArray);
    }
}
