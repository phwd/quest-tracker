package java.util.zip;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;

public class GZIPInputStream extends InflaterInputStream {
    private static final int FCOMMENT = 16;
    private static final int FEXTRA = 4;
    private static final int FHCRC = 2;
    private static final int FNAME = 8;
    private static final int FTEXT = 1;
    public static final int GZIP_MAGIC = 35615;
    private boolean closed;
    protected CRC32 crc;
    protected boolean eos;
    private byte[] tmpbuf;

    private void ensureOpen() throws IOException {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    public GZIPInputStream(InputStream in, int size) throws IOException {
        super(in, new Inflater(true), size);
        this.crc = new CRC32();
        this.closed = false;
        this.tmpbuf = new byte[128];
        try {
            readHeader(in);
        } catch (Exception e) {
            this.inf.end();
            throw e;
        }
    }

    public GZIPInputStream(InputStream in) throws IOException {
        this(in, 512);
    }

    @Override // java.io.FilterInputStream, java.util.zip.InflaterInputStream, java.io.InputStream
    public int read(byte[] buf, int off, int len) throws IOException {
        ensureOpen();
        if (this.eos) {
            return -1;
        }
        int n = super.read(buf, off, len);
        if (n != -1) {
            this.crc.update(buf, off, n);
        } else if (!readTrailer()) {
            return read(buf, off, len);
        } else {
            this.eos = true;
        }
        return n;
    }

    @Override // java.io.FilterInputStream, java.util.zip.InflaterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() throws IOException {
        if (!this.closed) {
            super.close();
            this.eos = true;
            this.closed = true;
        }
    }

    private int readHeader(InputStream this_in) throws IOException {
        CheckedInputStream in = new CheckedInputStream(this_in, this.crc);
        this.crc.reset();
        if (readUShort(in) != 35615) {
            throw new ZipException("Not in GZIP format");
        } else if (readUByte(in) == 8) {
            int flg = readUByte(in);
            skipBytes(in, 6);
            int n = 10;
            if ((flg & 4) == 4) {
                int m = readUShort(in);
                skipBytes(in, m);
                n = 10 + m + 2;
            }
            if ((flg & 8) == 8) {
                do {
                    n++;
                } while (readUByte(in) != 0);
            }
            if ((flg & 16) == 16) {
                do {
                    n++;
                } while (readUByte(in) != 0);
            }
            if ((flg & 2) == 2) {
                if (readUShort(in) == (((int) this.crc.getValue()) & 65535)) {
                    n += 2;
                } else {
                    throw new ZipException("Corrupt GZIP header");
                }
            }
            this.crc.reset();
            return n;
        } else {
            throw new ZipException("Unsupported compression method");
        }
    }

    private boolean readTrailer() throws IOException {
        InputStream in = this.in;
        int n = this.inf.getRemaining();
        if (n > 0) {
            in = new SequenceInputStream(new ByteArrayInputStream(this.buf, this.len - n, n), new FilterInputStream(in) {
                /* class java.util.zip.GZIPInputStream.AnonymousClass1 */

                @Override // java.io.FilterInputStream, java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
                public void close() throws IOException {
                }
            });
        }
        if (readUInt(in) != this.crc.getValue() || readUInt(in) != (this.inf.getBytesWritten() & 4294967295L)) {
            throw new ZipException("Corrupt GZIP trailer");
        } else if (this.in.available() <= 0 && n <= 26) {
            return true;
        } else {
            try {
                int m = 8 + readHeader(in);
                this.inf.reset();
                if (n <= m) {
                    return false;
                }
                this.inf.setInput(this.buf, (this.len - n) + m, n - m);
                return false;
            } catch (IOException e) {
                return true;
            }
        }
    }

    private long readUInt(InputStream in) throws IOException {
        return (((long) readUShort(in)) << 16) | ((long) readUShort(in));
    }

    private int readUShort(InputStream in) throws IOException {
        return (readUByte(in) << 8) | readUByte(in);
    }

    private int readUByte(InputStream in) throws IOException {
        int b = in.read();
        if (b == -1) {
            throw new EOFException();
        } else if (b >= -1 && b <= 255) {
            return b;
        } else {
            throw new IOException(this.in.getClass().getName() + ".read() returned value out of range -1..255: " + b);
        }
    }

    private void skipBytes(InputStream in, int n) throws IOException {
        while (n > 0) {
            byte[] bArr = this.tmpbuf;
            int len = in.read(bArr, 0, n < bArr.length ? n : bArr.length);
            if (len != -1) {
                n -= len;
            } else {
                throw new EOFException();
            }
        }
    }
}
