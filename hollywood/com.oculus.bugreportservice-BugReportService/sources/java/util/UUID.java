package java.util;

import java.io.Serializable;
import java.security.SecureRandom;

public final class UUID implements Serializable, Comparable {
    private static final long serialVersionUID = -4856846361193249489L;
    private final long leastSigBits;
    private final long mostSigBits;

    private static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();
    }

    private UUID(byte[] bArr) {
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 << 8) | ((long) (bArr[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            j = (j << 8) | ((long) (bArr[i2] & 255));
        }
        this.mostSigBits = j2;
        this.leastSigBits = j;
    }

    public static UUID randomUUID() {
        byte[] bArr = new byte[16];
        Holder.numberGenerator.nextBytes(bArr);
        bArr[6] = (byte) (bArr[6] & 15);
        bArr[6] = (byte) (bArr[6] | 64);
        bArr[8] = (byte) (bArr[8] & 63);
        bArr[8] = (byte) (bArr[8] | 128);
        return new UUID(bArr);
    }

    public String toString() {
        new StringBuilder();
        digits(this.mostSigBits >> 32, 8);
        throw null;
    }

    private static String digits(long j, int i) {
        long j2 = 1 << (i * 4);
        Long.toHexString((j & (j2 - 1)) | j2);
        throw null;
    }

    public int hashCode() {
        long j = this.mostSigBits ^ this.leastSigBits;
        return ((int) (j >> 32)) ^ ((int) j);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UUID.class) {
            return false;
        }
        UUID uuid = (UUID) obj;
        if (this.mostSigBits == uuid.mostSigBits && this.leastSigBits == uuid.leastSigBits) {
            return true;
        }
        return false;
    }

    public int compareTo(UUID uuid) {
        long j = this.mostSigBits;
        long j2 = uuid.mostSigBits;
        if (j >= j2) {
            if (j > j2) {
                return 1;
            }
            long j3 = this.leastSigBits;
            long j4 = uuid.leastSigBits;
            if (j3 >= j4) {
                if (j3 > j4) {
                    return 1;
                }
                return 0;
            }
        }
        return -1;
    }
}
