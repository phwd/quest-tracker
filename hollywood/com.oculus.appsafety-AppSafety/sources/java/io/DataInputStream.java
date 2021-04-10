package java.io;

import java.nio.ByteOrder;
import libcore.io.Memory;
import sun.security.util.DerValue;

public class DataInputStream extends FilterInputStream implements DataInput {
    private byte[] bytearr = new byte[80];
    private char[] chararr = new char[80];
    private char[] lineBuffer;
    private byte[] readBuffer = new byte[8];

    public DataInputStream(InputStream in) {
        super(in);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] b) throws IOException {
        return this.in.read(b, 0, b.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] b, int off, int len) throws IOException {
        return this.in.read(b, off, len);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] b) throws IOException {
        readFully(b, 0, b.length);
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] b, int off, int len) throws IOException {
        if (len >= 0) {
            int n = 0;
            while (n < len) {
                int count = this.in.read(b, off + n, len - n);
                if (count >= 0) {
                    n += count;
                } else {
                    throw new EOFException();
                }
            }
            return;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.io.DataInput
    public final int skipBytes(int n) throws IOException {
        int total = 0;
        while (total < n) {
            int cur = (int) this.in.skip((long) (n - total));
            if (cur <= 0) {
                break;
            }
            total += cur;
        }
        return total;
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws IOException {
        int ch = this.in.read();
        if (ch >= 0) {
            return ch != 0;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        int ch = this.in.read();
        if (ch >= 0) {
            return (byte) ch;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        int ch = this.in.read();
        if (ch >= 0) {
            return ch;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        readFully(this.readBuffer, 0, 2);
        return Memory.peekShort(this.readBuffer, 0, ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        readFully(this.readBuffer, 0, 2);
        return Memory.peekShort(this.readBuffer, 0, ByteOrder.BIG_ENDIAN) & 65535;
    }

    @Override // java.io.DataInput
    public final char readChar() throws IOException {
        readFully(this.readBuffer, 0, 2);
        return (char) Memory.peekShort(this.readBuffer, 0, ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        readFully(this.readBuffer, 0, 4);
        return Memory.peekInt(this.readBuffer, 0, ByteOrder.BIG_ENDIAN);
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        readFully(this.readBuffer, 0, 8);
        byte[] bArr = this.readBuffer;
        return (((long) bArr[0]) << 56) + (((long) (bArr[1] & 255)) << 48) + (((long) (bArr[2] & 255)) << 40) + (((long) (bArr[3] & 255)) << 32) + (((long) (bArr[4] & 255)) << 24) + ((long) ((bArr[5] & 255) << 16)) + ((long) ((bArr[6] & 255) << 8)) + ((long) ((bArr[7] & 255) << 0));
    }

    @Override // java.io.DataInput
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    /* JADX INFO: Multiple debug info for r3v6 int: [D('room' int), D('offset' int)] */
    @Override // java.io.DataInput
    @Deprecated
    public final String readLine() throws IOException {
        int c;
        char[] buf = this.lineBuffer;
        if (buf == null) {
            char[] cArr = new char[128];
            this.lineBuffer = cArr;
            buf = cArr;
        }
        int room = buf.length;
        int offset = 0;
        while (true) {
            c = this.in.read();
            if (c == -1 || c == 10) {
                break;
            } else if (c != 13) {
                room--;
                if (room < 0) {
                    buf = new char[(offset + 128)];
                    System.arraycopy((Object) this.lineBuffer, 0, (Object) buf, 0, offset);
                    this.lineBuffer = buf;
                    room = (buf.length - offset) - 1;
                }
                buf[offset] = (char) c;
                offset++;
            } else {
                int c2 = this.in.read();
                if (c2 != 10 && c2 != -1) {
                    if (!(this.in instanceof PushbackInputStream)) {
                        this.in = new PushbackInputStream(this.in);
                    }
                    ((PushbackInputStream) this.in).unread(c2);
                }
            }
        }
        if (c == -1 && offset == 0) {
            return null;
        }
        return String.copyValueOf(buf, 0, offset);
    }

    @Override // java.io.DataInput
    public final String readUTF() throws IOException {
        return readUTF(this);
    }

    public static final String readUTF(DataInput in) throws IOException {
        char[] chararr2;
        byte[] bytearr2;
        int utflen = in.readUnsignedShort();
        if (in instanceof DataInputStream) {
            DataInputStream dis = (DataInputStream) in;
            if (dis.bytearr.length < utflen) {
                dis.bytearr = new byte[(utflen * 2)];
                dis.chararr = new char[(utflen * 2)];
            }
            chararr2 = dis.chararr;
            bytearr2 = dis.bytearr;
        } else {
            bytearr2 = new byte[utflen];
            chararr2 = new char[utflen];
        }
        int count = 0;
        int chararr_count = 0;
        in.readFully(bytearr2, 0, utflen);
        while (count < utflen) {
            int c = bytearr2[count] & 255;
            if (c > 127) {
                break;
            }
            count++;
            chararr2[chararr_count] = (char) c;
            chararr_count++;
        }
        while (count < utflen) {
            int c2 = bytearr2[count] & 255;
            switch (c2 >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    count++;
                    chararr2[chararr_count] = (char) c2;
                    chararr_count++;
                    break;
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    throw new UTFDataFormatException("malformed input around byte " + count);
                case 12:
                case 13:
                    count += 2;
                    if (count <= utflen) {
                        byte b = bytearr2[count - 1];
                        if ((b & DerValue.TAG_PRIVATE) == 128) {
                            chararr2[chararr_count] = (char) (((c2 & 31) << 6) | (b & 63));
                            chararr_count++;
                            break;
                        } else {
                            throw new UTFDataFormatException("malformed input around byte " + count);
                        }
                    } else {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
                case 14:
                    count += 3;
                    if (count <= utflen) {
                        byte b2 = bytearr2[count - 2];
                        byte b3 = bytearr2[count - 1];
                        if ((b2 & DerValue.TAG_PRIVATE) == 128 && (b3 & DerValue.TAG_PRIVATE) == 128) {
                            chararr2[chararr_count] = (char) (((c2 & 15) << 12) | ((b2 & 63) << 6) | ((b3 & 63) << 0));
                            chararr_count++;
                            break;
                        } else {
                            StringBuilder sb = new StringBuilder();
                            sb.append("malformed input around byte ");
                            sb.append(count - 1);
                            throw new UTFDataFormatException(sb.toString());
                        }
                    } else {
                        throw new UTFDataFormatException("malformed input: partial character at end");
                    }
            }
        }
        return new String(chararr2, 0, chararr_count);
    }
}
