package sun.security.x509;

import java.io.IOException;
import sun.security.util.BitArray;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class UniqueIdentity {
    private BitArray id;

    public UniqueIdentity(BitArray id2) {
        this.id = id2;
    }

    public UniqueIdentity(byte[] id2) {
        this.id = new BitArray(id2.length * 8, id2);
    }

    public UniqueIdentity(DerInputStream in) throws IOException {
        this.id = in.getDerValue().getUnalignedBitString(true);
    }

    public UniqueIdentity(DerValue derVal) throws IOException {
        this.id = derVal.getUnalignedBitString(true);
    }

    public String toString() {
        return "UniqueIdentity:" + this.id.toString() + "\n";
    }

    public void encode(DerOutputStream out, byte tag) throws IOException {
        byte[] bytes = this.id.toByteArray();
        int excessBits = (bytes.length * 8) - this.id.length();
        out.write(tag);
        out.putLength(bytes.length + 1);
        out.write(excessBits);
        out.write(bytes);
    }

    public boolean[] getId() {
        BitArray bitArray = this.id;
        if (bitArray == null) {
            return null;
        }
        return bitArray.toBooleanArray();
    }
}
