package sun.security.x509;

import java.io.IOException;
import java.math.BigInteger;
import sun.security.util.Debug;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class SerialNumber {
    private BigInteger serialNum;

    private void construct(DerValue derValue) {
        this.serialNum = derValue.getBigInteger();
        if (derValue.data.available() != 0) {
            throw new IOException("Excess SerialNumber data");
        }
    }

    public SerialNumber(DerValue derValue) {
        construct(derValue);
    }

    public String toString() {
        return "SerialNumber: [" + Debug.toHexString(this.serialNum) + "]";
    }

    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putInteger(this.serialNum);
    }

    public BigInteger getNumber() {
        return this.serialNum;
    }
}
