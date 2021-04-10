package X;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;

/* renamed from: X.1hQ  reason: invalid class name */
public class AnonymousClass1hQ extends InputStream implements DataInput {
    public static final ByteOrder A04 = ByteOrder.BIG_ENDIAN;
    public static final ByteOrder A05 = ByteOrder.LITTLE_ENDIAN;
    public int A00 = 0;
    public DataInputStream A01;
    public ByteOrder A02;
    public final int A03;

    public AnonymousClass1hQ(InputStream inputStream) throws IOException {
        ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
        this.A02 = byteOrder;
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        this.A01 = dataInputStream;
        int available = dataInputStream.available();
        this.A03 = available;
        this.A01.mark(available);
        this.A02 = byteOrder;
    }

    public final void A00(long j) throws IOException {
        long j2 = (long) this.A00;
        if (j2 > j) {
            this.A00 = 0;
            this.A01.reset();
            this.A01.mark(this.A03);
        } else {
            j -= j2;
        }
        int i = (int) j;
        if (skipBytes(i) != i) {
            throw new IOException("Couldn't seek up to the byteCount");
        }
    }

    @Override // java.io.InputStream
    public final int available() throws IOException {
        return this.A01.available();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() throws IOException {
        this.A00++;
        return this.A01.readBoolean();
    }

    @Override // java.io.DataInput
    public final byte readByte() throws IOException {
        int i = this.A00 + 1;
        this.A00 = i;
        if (i <= this.A03) {
            int read = this.A01.read();
            if (read >= 0) {
                return (byte) read;
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final char readChar() throws IOException {
        this.A00 += 2;
        return this.A01.readChar();
    }

    @Override // java.io.DataInput
    public final int readInt() throws IOException {
        int i = this.A00 + 4;
        this.A00 = i;
        if (i <= this.A03) {
            int read = this.A01.read();
            int read2 = this.A01.read();
            int read3 = this.A01.read();
            int read4 = this.A01.read();
            if ((read | read2 | read3 | read4) >= 0) {
                ByteOrder byteOrder = this.A02;
                if (byteOrder == A05) {
                    return (read4 << 24) + (read3 << 16) + (read2 << 8) + read;
                }
                if (byteOrder == A04) {
                    return (read << 24) + (read2 << 16) + (read3 << 8) + read4;
                }
                StringBuilder sb = new StringBuilder("Invalid byte order: ");
                sb.append(byteOrder);
                throw new IOException(sb.toString());
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final String readLine() throws IOException {
        return null;
    }

    @Override // java.io.DataInput
    public final long readLong() throws IOException {
        int i = this.A00 + 8;
        this.A00 = i;
        if (i <= this.A03) {
            int read = this.A01.read();
            int read2 = this.A01.read();
            int read3 = this.A01.read();
            int read4 = this.A01.read();
            int read5 = this.A01.read();
            int read6 = this.A01.read();
            int read7 = this.A01.read();
            int read8 = this.A01.read();
            if ((read | read2 | read3 | read4 | read5 | read6 | read7 | read8) >= 0) {
                ByteOrder byteOrder = this.A02;
                if (byteOrder == A05) {
                    return (((long) read8) << 56) + (((long) read7) << 48) + (((long) read6) << 40) + (((long) read5) << 32) + (((long) read4) << 24) + (((long) read3) << 16) + (((long) read2) << 8) + ((long) read);
                }
                if (byteOrder == A04) {
                    return (((long) read) << 56) + (((long) read2) << 48) + (((long) read3) << 40) + (((long) read4) << 32) + (((long) read5) << 24) + (((long) read6) << 16) + (((long) read7) << 8) + ((long) read8);
                }
                StringBuilder sb = new StringBuilder("Invalid byte order: ");
                sb.append(byteOrder);
                throw new IOException(sb.toString());
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final short readShort() throws IOException {
        int i = this.A00 + 2;
        this.A00 = i;
        if (i <= this.A03) {
            int read = this.A01.read();
            int read2 = this.A01.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.A02;
                if (byteOrder == A05) {
                    return (short) ((read2 << 8) + read);
                }
                if (byteOrder == A04) {
                    return (short) ((read << 8) + read2);
                }
                StringBuilder sb = new StringBuilder("Invalid byte order: ");
                sb.append(byteOrder);
                throw new IOException(sb.toString());
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final String readUTF() throws IOException {
        this.A00 += 2;
        return this.A01.readUTF();
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() throws IOException {
        this.A00++;
        return this.A01.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() throws IOException {
        int i = this.A00 + 2;
        this.A00 = i;
        if (i <= this.A03) {
            int read = this.A01.read();
            int read2 = this.A01.read();
            if ((read | read2) >= 0) {
                ByteOrder byteOrder = this.A02;
                if (byteOrder == A05) {
                    return (read2 << 8) + read;
                }
                if (byteOrder == A04) {
                    return (read << 8) + read2;
                }
                StringBuilder sb = new StringBuilder("Invalid byte order: ");
                sb.append(byteOrder);
                throw new IOException(sb.toString());
            }
            throw new EOFException();
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) throws IOException {
        int min = Math.min(i, this.A03 - this.A00);
        int i2 = 0;
        while (i2 < min) {
            i2 += this.A01.skipBytes(min - i2);
        }
        this.A00 += i2;
        return i2;
    }

    @Override // java.io.DataInput
    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        this.A00++;
        return this.A01.read();
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.A01.read(bArr, i, i2);
        this.A00 += read;
        return read;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) throws IOException {
        int i = this.A00;
        int length = bArr.length;
        int i2 = i + length;
        this.A00 = i2;
        if (i2 > this.A03) {
            throw new EOFException();
        } else if (this.A01.read(bArr, 0, length) != length) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.A00 + i2;
        this.A00 = i3;
        if (i3 > this.A03) {
            throw new EOFException();
        } else if (this.A01.read(bArr, i, i2) != i2) {
            throw new IOException("Couldn't read up to the length of buffer");
        }
    }
}
