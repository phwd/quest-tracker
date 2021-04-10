package java.lang;

public final class Byte extends Number implements Comparable {
    private static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final Class TYPE = Class.getPrimitiveClass("byte");
    private static final char[] UPPER_CASE_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final long serialVersionUID = -7183698231559129828L;
    private final byte value;

    public static int compare(byte b, byte b2) {
        return b - b2;
    }

    public static int toUnsignedInt(byte b) {
        return b & 255;
    }

    /* access modifiers changed from: private */
    public static class ByteCache {
        static final Byte[] cache = new Byte[256];

        static {
            int i = 0;
            while (true) {
                Byte[] bArr = cache;
                if (i < bArr.length) {
                    bArr[i] = new Byte((byte) (i - 128));
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static Byte valueOf(byte b) {
        return ByteCache.cache[b + 128];
    }

    public Byte(byte b) {
        this.value = b;
    }

    @Override // java.lang.Number
    public byte byteValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public int intValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) this.value;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) this.value;
    }

    public String toString() {
        return Integer.toString(this.value);
    }

    public int hashCode() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Byte) || this.value != ((Byte) obj).byteValue()) {
            return false;
        }
        return true;
    }

    public int compareTo(Byte b) {
        return compare(this.value, b.value);
    }
}
