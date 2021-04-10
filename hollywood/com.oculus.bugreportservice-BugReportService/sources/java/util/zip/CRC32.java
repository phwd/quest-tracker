package java.util.zip;

public class CRC32 implements Checksum {
    private int crc;

    private static native int updateBytes(int i, byte[] bArr, int i2, int i3);

    public void update(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i < 0 || i2 < 0 || i > bArr.length - i2) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            this.crc = updateBytes(this.crc, bArr, i, i2);
        }
    }

    public void reset() {
        this.crc = 0;
    }

    public long getValue() {
        return ((long) this.crc) & 4294967295L;
    }
}
