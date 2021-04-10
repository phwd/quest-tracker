package okio;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString EMPTY = of(new byte[0]);
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    final byte[] data;
    transient int hashCode;
    transient String utf8;

    ByteString(byte[] data2) {
        this.data = data2;
    }

    public static ByteString of(byte... data2) {
        if (data2 != null) {
            return new ByteString((byte[]) data2.clone());
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString encodeUtf8(String s) {
        if (s == null) {
            throw new IllegalArgumentException("s == null");
        }
        ByteString byteString = new ByteString(s.getBytes(Util.UTF_8));
        byteString.utf8 = s;
        return byteString;
    }

    public String utf8() {
        String result = this.utf8;
        if (result != null) {
            return result;
        }
        String result2 = new String(this.data, Util.UTF_8);
        this.utf8 = result2;
        return result2;
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public ByteString sha1() {
        return digest("SHA-1");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    private ByteString digest(String algorithm) {
        try {
            return of(MessageDigest.getInstance(algorithm).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public String hex() {
        char[] result = new char[(this.data.length * 2)];
        byte[] bArr = this.data;
        int c = 0;
        for (byte b : bArr) {
            int c2 = c + 1;
            result[c] = HEX_DIGITS[(b >> 4) & 15];
            c = c2 + 1;
            result[c2] = HEX_DIGITS[b & 15];
        }
        return new String(result);
    }

    public static ByteString decodeHex(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + hex);
        } else {
            byte[] result = new byte[(hex.length() / 2)];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) ((decodeHexDigit(hex.charAt(i * 2)) << 4) + decodeHexDigit(hex.charAt((i * 2) + 1)));
            }
            return of(result);
        }
    }

    private static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public ByteString toAsciiLowercase() {
        for (int i = 0; i < this.data.length; i++) {
            byte c = this.data[i];
            if (c >= 65 && c <= 90) {
                byte[] lowercase = (byte[]) this.data.clone();
                lowercase[i] = (byte) (c + 32);
                for (int i2 = i + 1; i2 < lowercase.length; i2++) {
                    byte c2 = lowercase[i2];
                    if (c2 >= 65 && c2 <= 90) {
                        lowercase[i2] = (byte) (c2 + 32);
                    }
                }
                return new ByteString(lowercase);
            }
        }
        return this;
    }

    public ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        } else if (endIndex > this.data.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
        } else {
            int subLen = endIndex - beginIndex;
            if (subLen < 0) {
                throw new IllegalArgumentException("endIndex < beginIndex");
            } else if (beginIndex == 0 && endIndex == this.data.length) {
                return this;
            } else {
                byte[] copy = new byte[subLen];
                System.arraycopy(this.data, beginIndex, copy, 0, subLen);
                return new ByteString(copy);
            }
        }
    }

    public byte getByte(int pos) {
        return this.data[pos];
    }

    public int size() {
        return this.data.length;
    }

    public byte[] toByteArray() {
        return (byte[]) this.data.clone();
    }

    /* access modifiers changed from: package-private */
    public void write(Buffer buffer) {
        buffer.write(this.data, 0, this.data.length);
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        return other.rangeEquals(otherOffset, this.data, offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        return offset >= 0 && offset <= this.data.length - byteCount && otherOffset >= 0 && otherOffset <= other.length - byteCount && Util.arrayRangeEquals(this.data, offset, other, otherOffset, byteCount);
    }

    public final boolean startsWith(ByteString prefix) {
        return rangeEquals(0, prefix, 0, prefix.size());
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ByteString) || ((ByteString) o).size() != this.data.length || !((ByteString) o).rangeEquals(0, this.data, 0, this.data.length)) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        int result = this.hashCode;
        if (result != 0) {
            return result;
        }
        int result2 = Arrays.hashCode(this.data);
        this.hashCode = result2;
        return result2;
    }

    public int compareTo(ByteString byteString) {
        int sizeA = size();
        int sizeB = byteString.size();
        int size = Math.min(sizeA, sizeB);
        for (int i = 0; i < size; i++) {
            int byteA = getByte(i) & 255;
            int byteB = byteString.getByte(i) & 255;
            if (byteA != byteB) {
                return byteA < byteB ? -1 : 1;
            }
        }
        if (sizeA == sizeB) {
            return 0;
        }
        return sizeA >= sizeB ? 1 : -1;
    }

    public String toString() {
        if (this.data.length == 0) {
            return "[size=0]";
        }
        String text = utf8();
        int i = codePointIndexToCharIndex(text, 64);
        if (i != -1) {
            String safeText = text.substring(0, i).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (i < text.length()) {
                return "[size=" + this.data.length + " text=" + safeText + "…]";
            }
            return "[text=" + safeText + "]";
        } else if (this.data.length <= 64) {
            return "[hex=" + hex() + "]";
        } else {
            return "[size=" + this.data.length + " hex=" + substring(0, 64).hex() + "…]";
        }
    }

    static int codePointIndexToCharIndex(String s, int codePointCount) {
        int i = 0;
        int j = 0;
        int length = s.length();
        while (i < length) {
            if (j == codePointCount) {
                return i;
            }
            int c = s.codePointAt(i);
            if ((Character.isISOControl(c) && c != 10 && c != 13) || c == 65533) {
                return -1;
            }
            j++;
            i += Character.charCount(c);
        }
        return s.length();
    }
}
