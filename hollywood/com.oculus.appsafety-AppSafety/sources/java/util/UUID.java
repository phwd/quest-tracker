package java.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import sun.security.util.DerValue;

public final class UUID implements Serializable, Comparable<UUID> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long serialVersionUID = -4856846361193249489L;
    private final long leastSigBits;
    private final long mostSigBits;

    /* access modifiers changed from: private */
    public static class Holder {
        static final SecureRandom numberGenerator = new SecureRandom();

        private Holder() {
        }
    }

    private UUID(byte[] data) {
        long msb = 0;
        long lsb = 0;
        for (int i = 0; i < 8; i++) {
            msb = (msb << 8) | ((long) (data[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            lsb = (lsb << 8) | ((long) (data[i2] & 255));
        }
        this.mostSigBits = msb;
        this.leastSigBits = lsb;
    }

    public UUID(long mostSigBits2, long leastSigBits2) {
        this.mostSigBits = mostSigBits2;
        this.leastSigBits = leastSigBits2;
    }

    public static UUID randomUUID() {
        byte[] randomBytes = new byte[16];
        Holder.numberGenerator.nextBytes(randomBytes);
        randomBytes[6] = (byte) (randomBytes[6] & 15);
        randomBytes[6] = (byte) (randomBytes[6] | DerValue.TAG_APPLICATION);
        randomBytes[8] = (byte) (randomBytes[8] & 63);
        randomBytes[8] = (byte) (randomBytes[8] | 128);
        return new UUID(randomBytes);
    }

    public static UUID nameUUIDFromBytes(byte[] name) {
        try {
            byte[] md5Bytes = MessageDigest.getInstance("MD5").digest(name);
            md5Bytes[6] = (byte) (md5Bytes[6] & 15);
            md5Bytes[6] = (byte) (md5Bytes[6] | 48);
            md5Bytes[8] = (byte) (md5Bytes[8] & 63);
            md5Bytes[8] = (byte) (md5Bytes[8] | 128);
            return new UUID(md5Bytes);
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("MD5 not supported", nsae);
        }
    }

    public static UUID fromString(String name) {
        String[] components = name.split("-");
        if (components.length == 5) {
            for (int i = 0; i < 5; i++) {
                components[i] = "0x" + components[i];
            }
            return new UUID((((Long.decode(components[0]).longValue() << 16) | Long.decode(components[1]).longValue()) << 16) | Long.decode(components[2]).longValue(), (Long.decode(components[3]).longValue() << 48) | Long.decode(components[4]).longValue());
        }
        throw new IllegalArgumentException("Invalid UUID string: " + name);
    }

    public long getLeastSignificantBits() {
        return this.leastSigBits;
    }

    public long getMostSignificantBits() {
        return this.mostSigBits;
    }

    public int version() {
        return (int) ((this.mostSigBits >> 12) & 15);
    }

    public int variant() {
        long j = this.leastSigBits;
        return (int) ((j >> 63) & (j >>> ((int) (64 - (j >>> 62)))));
    }

    public long timestamp() {
        if (version() == 1) {
            long j = this.mostSigBits;
            return (j >>> 32) | ((4095 & j) << 48) | (((j >> 16) & 65535) << 32);
        }
        throw new UnsupportedOperationException("Not a time-based UUID");
    }

    public int clockSequence() {
        if (version() == 1) {
            return (int) ((this.leastSigBits & 4611404543450677248L) >>> 48);
        }
        throw new UnsupportedOperationException("Not a time-based UUID");
    }

    public long node() {
        if (version() == 1) {
            return this.leastSigBits & 281474976710655L;
        }
        throw new UnsupportedOperationException("Not a time-based UUID");
    }

    public String toString() {
        return digits(this.mostSigBits >> 32, 8) + "-" + digits(this.mostSigBits >> 16, 4) + "-" + digits(this.mostSigBits, 4) + "-" + digits(this.leastSigBits >> 48, 4) + "-" + digits(this.leastSigBits, 12);
    }

    private static String digits(long val, int digits) {
        long hi = 1 << (digits * 4);
        return Long.toHexString(((hi - 1) & val) | hi).substring(1);
    }

    public int hashCode() {
        long hilo = this.mostSigBits ^ this.leastSigBits;
        return ((int) (hilo >> 32)) ^ ((int) hilo);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UUID.class) {
            return false;
        }
        UUID id = (UUID) obj;
        if (this.mostSigBits == id.mostSigBits && this.leastSigBits == id.leastSigBits) {
            return true;
        }
        return false;
    }

    public int compareTo(UUID val) {
        long j = this.mostSigBits;
        long j2 = val.mostSigBits;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        long j3 = this.leastSigBits;
        long j4 = val.leastSigBits;
        if (j3 < j4) {
            return -1;
        }
        if (j3 > j4) {
            return 1;
        }
        return 0;
    }
}
