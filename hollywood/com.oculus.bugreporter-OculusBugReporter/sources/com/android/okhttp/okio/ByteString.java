package com.android.okhttp.okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class ByteString implements Serializable, Comparable<ByteString> {
    public static final ByteString EMPTY = of(new byte[0]);
    static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final long serialVersionUID = 1;
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

    public static ByteString of(byte[] data2, int offset, int byteCount) {
        if (data2 != null) {
            Util.checkOffsetAndCount((long) data2.length, (long) offset, (long) byteCount);
            byte[] copy = new byte[byteCount];
            System.arraycopy(data2, offset, copy, 0, byteCount);
            return new ByteString(copy);
        }
        throw new IllegalArgumentException("data == null");
    }

    public static ByteString encodeUtf8(String s) {
        if (s != null) {
            ByteString byteString = new ByteString(s.getBytes(Util.UTF_8));
            byteString.utf8 = s;
            return byteString;
        }
        throw new IllegalArgumentException("s == null");
    }

    public String utf8() {
        String result = this.utf8;
        if (result != null) {
            return result;
        }
        String str = new String(this.data, Util.UTF_8);
        this.utf8 = str;
        return str;
    }

    public String base64() {
        return Base64.encode(this.data);
    }

    public ByteString md5() {
        return digest("MD5");
    }

    public ByteString sha256() {
        return digest("SHA-256");
    }

    private ByteString digest(String digest) {
        try {
            return of(MessageDigest.getInstance(digest).digest(this.data));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    public String base64Url() {
        return Base64.encodeUrl(this.data);
    }

    public static ByteString decodeBase64(String base64) {
        if (base64 != null) {
            byte[] decoded = Base64.decode(base64);
            if (decoded != null) {
                return new ByteString(decoded);
            }
            return null;
        }
        throw new IllegalArgumentException("base64 == null");
    }

    public String hex() {
        byte[] bArr = this.data;
        char[] result = new char[(bArr.length * 2)];
        int c = 0;
        for (byte b : bArr) {
            int c2 = c + 1;
            char[] cArr = HEX_DIGITS;
            result[c] = cArr[(b >> 4) & 15];
            c = c2 + 1;
            result[c2] = cArr[b & 15];
        }
        return new String(result);
    }

    public static ByteString decodeHex(String hex) {
        if (hex == null) {
            throw new IllegalArgumentException("hex == null");
        } else if (hex.length() % 2 == 0) {
            byte[] result = new byte[(hex.length() / 2)];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) ((decodeHexDigit(hex.charAt(i * 2)) << 4) + decodeHexDigit(hex.charAt((i * 2) + 1)));
            }
            return of(result);
        } else {
            throw new IllegalArgumentException("Unexpected hex string: " + hex);
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

    public static ByteString read(InputStream in, int byteCount) throws IOException {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        } else if (byteCount >= 0) {
            byte[] result = new byte[byteCount];
            int offset = 0;
            while (offset < byteCount) {
                int read = in.read(result, offset, byteCount - offset);
                if (read != -1) {
                    offset += read;
                } else {
                    throw new EOFException();
                }
            }
            return new ByteString(result);
        } else {
            throw new IllegalArgumentException("byteCount < 0: " + byteCount);
        }
    }

    public ByteString toAsciiLowercase() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte c = bArr[i];
            if (c < 65 || c > 90) {
                i++;
            } else {
                byte[] lowercase = (byte[]) bArr.clone();
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
    }

    public ByteString toAsciiUppercase() {
        int i = 0;
        while (true) {
            byte[] bArr = this.data;
            if (i >= bArr.length) {
                return this;
            }
            byte c = bArr[i];
            if (c < 97 || c > 122) {
                i++;
            } else {
                byte[] lowercase = (byte[]) bArr.clone();
                lowercase[i] = (byte) (c - 32);
                for (int i2 = i + 1; i2 < lowercase.length; i2++) {
                    byte c2 = lowercase[i2];
                    if (c2 >= 97 && c2 <= 122) {
                        lowercase[i2] = (byte) (c2 - 32);
                    }
                }
                return new ByteString(lowercase);
            }
        }
    }

    public ByteString substring(int beginIndex) {
        return substring(beginIndex, this.data.length);
    }

    public ByteString substring(int beginIndex, int endIndex) {
        if (beginIndex >= 0) {
            byte[] bArr = this.data;
            if (endIndex <= bArr.length) {
                int subLen = endIndex - beginIndex;
                if (subLen < 0) {
                    throw new IllegalArgumentException("endIndex < beginIndex");
                } else if (beginIndex == 0 && endIndex == bArr.length) {
                    return this;
                } else {
                    byte[] copy = new byte[subLen];
                    System.arraycopy(this.data, beginIndex, copy, 0, subLen);
                    return new ByteString(copy);
                }
            } else {
                throw new IllegalArgumentException("endIndex > length(" + this.data.length + ")");
            }
        } else {
            throw new IllegalArgumentException("beginIndex < 0");
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

    public void write(OutputStream out) throws IOException {
        if (out != null) {
            out.write(this.data);
            return;
        }
        throw new IllegalArgumentException("out == null");
    }

    /* access modifiers changed from: package-private */
    public void write(Buffer buffer) {
        byte[] bArr = this.data;
        buffer.write(bArr, 0, bArr.length);
    }

    public boolean rangeEquals(int offset, ByteString other, int otherOffset, int byteCount) {
        return other.rangeEquals(otherOffset, this.data, offset, byteCount);
    }

    public boolean rangeEquals(int offset, byte[] other, int otherOffset, int byteCount) {
        byte[] bArr = this.data;
        return offset <= bArr.length - byteCount && otherOffset <= other.length - byteCount && Util.arrayRangeEquals(bArr, offset, other, otherOffset, byteCount);
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof ByteString) {
            int size = ((ByteString) o).size();
            byte[] bArr = this.data;
            if (size == bArr.length && ((ByteString) o).rangeEquals(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int result = this.hashCode;
        if (result != 0) {
            return result;
        }
        int hashCode2 = Arrays.hashCode(this.data);
        this.hashCode = hashCode2;
        return hashCode2;
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
        return sizeA < sizeB ? -1 : 1;
    }

    public String toString() {
        byte[] bArr = this.data;
        if (bArr.length == 0) {
            return "ByteString[size=0]";
        }
        if (bArr.length <= 16) {
            return String.format("ByteString[size=%s data=%s]", Integer.valueOf(bArr.length), hex());
        }
        return String.format("ByteString[size=%s md5=%s]", Integer.valueOf(bArr.length), md5().hex());
    }

    private void readObject(ObjectInputStream in) throws IOException {
        ByteString byteString = read(in, in.readInt());
        try {
            Field field = ByteString.class.getDeclaredField("data");
            field.setAccessible(true);
            field.set(this, byteString.data);
        } catch (NoSuchFieldException e) {
            throw new AssertionError();
        } catch (IllegalAccessException e2) {
            throw new AssertionError();
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.data.length);
        out.write(this.data);
    }
}
