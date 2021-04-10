package sun.security.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import sun.misc.IOUtils;

public class DerValue {
    protected DerInputBuffer buffer;
    public final DerInputStream data;
    private int length;
    private byte[] originalEncodedForm;
    public byte tag;

    public static byte createTag(byte b, boolean z, byte b2) {
        byte b3 = (byte) (b | b2);
        return z ? (byte) (b3 | 32) : b3;
    }

    public static boolean isPrintableStringChar(char c) {
        if ((c < 'a' || c > 'z') && ((c < 'A' || c > 'Z') && !((c >= '0' && c <= '9') || c == ' ' || c == ':' || c == '=' || c == '?'))) {
            switch (c) {
                case '\'':
                case '(':
                case ')':
                    break;
                default:
                    switch (c) {
                        case '+':
                        case ',':
                        case '-':
                        case '.':
                        case '/':
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    public boolean isContextSpecific() {
        return (this.tag & 192) == 128;
    }

    public boolean isContextSpecific(byte b) {
        if (isContextSpecific() && (this.tag & 31) == b) {
            return true;
        }
        return false;
    }

    public boolean isConstructed() {
        return (this.tag & 32) == 32;
    }

    public boolean isConstructed(byte b) {
        if (isConstructed() && (this.tag & 31) == b) {
            return true;
        }
        return false;
    }

    public DerValue(byte b, byte[] bArr) {
        this.tag = b;
        this.buffer = new DerInputBuffer((byte[]) bArr.clone());
        this.length = bArr.length;
        this.data = new DerInputStream(this.buffer);
        this.data.mark(Integer.MAX_VALUE);
    }

    DerValue(DerInputBuffer derInputBuffer, boolean z) {
        int pos = derInputBuffer.getPos();
        this.tag = (byte) derInputBuffer.read();
        byte read = (byte) derInputBuffer.read();
        this.length = DerInputStream.getLength(read, derInputBuffer);
        if (this.length == -1) {
            DerInputBuffer dup = derInputBuffer.dup();
            int available = dup.available();
            byte[] bArr = new byte[(available + 2)];
            bArr[0] = this.tag;
            bArr[1] = read;
            DataInputStream dataInputStream = new DataInputStream(dup);
            dataInputStream.readFully(bArr, 2, available);
            dataInputStream.close();
            DerInputBuffer derInputBuffer2 = new DerInputBuffer(new DerIndefLenConverter().convert(bArr));
            if (this.tag == derInputBuffer2.read()) {
                this.length = DerInputStream.getLength(derInputBuffer2);
                this.buffer = derInputBuffer2.dup();
                this.buffer.truncate(this.length);
                this.data = new DerInputStream(this.buffer);
                derInputBuffer.skip((long) (this.length + 2));
            } else {
                throw new IOException("Indefinite length encoding not supported");
            }
        } else {
            this.buffer = derInputBuffer.dup();
            this.buffer.truncate(this.length);
            this.data = new DerInputStream(this.buffer);
            derInputBuffer.skip((long) this.length);
        }
        if (z) {
            this.originalEncodedForm = derInputBuffer.getSlice(pos, derInputBuffer.getPos() - pos);
        }
    }

    public DerValue(byte[] bArr) {
        this.data = init(true, new ByteArrayInputStream(bArr));
    }

    public DerValue(InputStream inputStream) {
        this.data = init(false, inputStream);
    }

    private DerInputStream init(boolean z, InputStream inputStream) {
        this.tag = (byte) inputStream.read();
        byte read = (byte) inputStream.read();
        this.length = DerInputStream.getLength(read, inputStream);
        if (this.length == -1) {
            int available = inputStream.available();
            byte[] bArr = new byte[(available + 2)];
            bArr[0] = this.tag;
            bArr[1] = read;
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(bArr, 2, available);
            dataInputStream.close();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new DerIndefLenConverter().convert(bArr));
            if (this.tag == byteArrayInputStream.read()) {
                this.length = DerInputStream.getLength(byteArrayInputStream);
                inputStream = byteArrayInputStream;
            } else {
                throw new IOException("Indefinite length encoding not supported");
            }
        }
        if (!z || inputStream.available() == this.length) {
            this.buffer = new DerInputBuffer(IOUtils.readFully(inputStream, this.length, true));
            return new DerInputStream(this.buffer);
        }
        throw new IOException("extra data given to DerValue constructor");
    }

    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.write(this.tag);
        derOutputStream.putLength(this.length);
        int i = this.length;
        if (i > 0) {
            byte[] bArr = new byte[i];
            synchronized (this.data) {
                this.buffer.reset();
                if (this.buffer.read(bArr) == this.length) {
                    derOutputStream.write(bArr);
                } else {
                    throw new IOException("short DER value read (encode)");
                }
            }
        }
    }

    public final DerInputStream getData() {
        return this.data;
    }

    public final byte getTag() {
        return this.tag;
    }

    public boolean getBoolean() {
        if (this.tag != 1) {
            throw new IOException("DerValue.getBoolean, not a BOOLEAN " + ((int) this.tag));
        } else if (this.length != 1) {
            throw new IOException("DerValue.getBoolean, invalid length " + this.length);
        } else if (this.buffer.read() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public ObjectIdentifier getOID() {
        if (this.tag == 6) {
            return new ObjectIdentifier(this.buffer);
        }
        throw new IOException("DerValue.getOID, not an OID " + ((int) this.tag));
    }

    private byte[] append(byte[] bArr, byte[] bArr2) {
        if (bArr == null) {
            return bArr2;
        }
        byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    public byte[] getOctetString() {
        if (this.tag == 4 || isConstructed((byte) 4)) {
            int i = this.length;
            byte[] bArr = new byte[i];
            if (i == 0) {
                return bArr;
            }
            if (this.buffer.read(bArr) == this.length) {
                if (isConstructed()) {
                    DerInputStream derInputStream = new DerInputStream(bArr);
                    bArr = null;
                    while (derInputStream.available() != 0) {
                        bArr = append(bArr, derInputStream.getOctetString());
                    }
                }
                return bArr;
            }
            throw new IOException("short read on DerValue buffer");
        }
        throw new IOException("DerValue.getOctetString, not an Octet String: " + ((int) this.tag));
    }

    public int getInteger() {
        if (this.tag == 2) {
            return this.buffer.getInteger(this.data.available());
        }
        throw new IOException("DerValue.getInteger, not an int " + ((int) this.tag));
    }

    public BigInteger getBigInteger() {
        if (this.tag == 2) {
            return this.buffer.getBigInteger(this.data.available(), false);
        }
        throw new IOException("DerValue.getBigInteger, not an int " + ((int) this.tag));
    }

    public byte[] getBitString() {
        if (this.tag == 3) {
            return this.buffer.getBitString();
        }
        throw new IOException("DerValue.getBitString, not a bit string " + ((int) this.tag));
    }

    public String getAsString() {
        byte b = this.tag;
        if (b == 12) {
            getUTF8String();
            throw null;
        } else if (b == 19) {
            getPrintableString();
            throw null;
        } else if (b == 20) {
            getT61String();
            throw null;
        } else if (b == 22) {
            getIA5String();
            throw null;
        } else if (b == 30) {
            getBMPString();
            throw null;
        } else if (b != 27) {
            return null;
        } else {
            getGeneralString();
            throw null;
        }
    }

    public BitArray getUnalignedBitString(boolean z) {
        if (z || this.tag == 3) {
            return this.buffer.getUnalignedBitString();
        }
        throw new IOException("DerValue.getBitString, not a bit string " + ((int) this.tag));
    }

    public byte[] getDataBytes() {
        byte[] bArr = new byte[this.length];
        synchronized (this.data) {
            this.data.reset();
            this.data.getBytes(bArr);
        }
        return bArr;
    }

    public String getPrintableString() {
        if (this.tag != 19) {
            throw new IOException("DerValue.getPrintableString, not a string " + ((int) this.tag));
        }
        new String(getDataBytes(), "ASCII");
        throw null;
    }

    public String getT61String() {
        if (this.tag != 20) {
            throw new IOException("DerValue.getT61String, not T61 " + ((int) this.tag));
        }
        new String(getDataBytes(), "ISO-8859-1");
        throw null;
    }

    public String getIA5String() {
        if (this.tag != 22) {
            throw new IOException("DerValue.getIA5String, not IA5 " + ((int) this.tag));
        }
        new String(getDataBytes(), "ASCII");
        throw null;
    }

    public String getBMPString() {
        if (this.tag != 30) {
            throw new IOException("DerValue.getBMPString, not BMP " + ((int) this.tag));
        }
        new String(getDataBytes(), "UnicodeBigUnmarked");
        throw null;
    }

    public String getUTF8String() {
        if (this.tag != 12) {
            throw new IOException("DerValue.getUTF8String, not UTF-8 " + ((int) this.tag));
        }
        new String(getDataBytes(), "UTF8");
        throw null;
    }

    public String getGeneralString() {
        if (this.tag != 27) {
            throw new IOException("DerValue.getGeneralString, not GeneralString " + ((int) this.tag));
        }
        new String(getDataBytes(), "ASCII");
        throw null;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DerValue) {
            return equals((DerValue) obj);
        }
        return false;
    }

    public boolean equals(DerValue derValue) {
        if (this == derValue) {
            return true;
        }
        if (this.tag != derValue.tag) {
            return false;
        }
        DerInputStream derInputStream = this.data;
        if (derInputStream == derValue.data) {
            return true;
        }
        if (System.identityHashCode(derInputStream) > System.identityHashCode(derValue.data)) {
            return doEquals(this, derValue);
        }
        return doEquals(derValue, this);
    }

    private static boolean doEquals(DerValue derValue, DerValue derValue2) {
        boolean equals;
        synchronized (derValue.data) {
            synchronized (derValue2.data) {
                derValue.data.reset();
                derValue2.data.reset();
                equals = derValue.buffer.equals(derValue2.buffer);
            }
        }
        return equals;
    }

    public String toString() {
        try {
            String asString = getAsString();
            if (asString != null) {
                return "\"" + asString + "\"";
            } else if (this.tag == 5) {
                return "[DerValue, null]";
            } else {
                if (this.tag == 6) {
                    return "OID." + getOID();
                }
                return "[DerValue, tag = " + ((int) this.tag) + ", length = " + this.length + "]";
            }
        } catch (IOException unused) {
            throw new IllegalArgumentException("misformatted DER value");
        }
    }

    public byte[] getOriginalEncodedForm() {
        byte[] bArr = this.originalEncodedForm;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public byte[] toByteArray() {
        DerOutputStream derOutputStream = new DerOutputStream();
        encode(derOutputStream);
        this.data.reset();
        return derOutputStream.toByteArray();
    }

    public DerInputStream toDerInputStream() {
        byte b = this.tag;
        if (b == 48 || b == 49) {
            return new DerInputStream(this.buffer);
        }
        throw new IOException("toDerInputStream rejects tag type " + ((int) this.tag));
    }

    public int length() {
        return this.length;
    }

    public void resetTag(byte b) {
        this.tag = b;
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
