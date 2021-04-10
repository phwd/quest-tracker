package X;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.KeyPair;

/* renamed from: X.0XU  reason: invalid class name */
public final class AnonymousClass0XU {
    public static final BigInteger A01 = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853948");
    public static final BigInteger A02 = new BigInteger("41058363725152142129326129780047268409114441015993725554835256314039467401291");
    public static final BigInteger A03 = new BigInteger("115792089210356248762697446949407573530086143415290314195533631308867097853951");
    public static final BigInteger A04 = BigInteger.valueOf(3);
    public static final BigInteger A05 = BigInteger.valueOf(2);
    public KeyPair A00;

    public static final byte[] A00(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        ByteBuffer allocate = ByteBuffer.allocate(32);
        int length = byteArray.length;
        int min = Math.min(length, 32);
        allocate.position(32 - min);
        int i = 0;
        if (length > 32) {
            i = 1;
        }
        allocate.put(byteArray, i, min);
        return allocate.array();
    }
}
