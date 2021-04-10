package java.util.zip;

import java.io.IOException;
import java.io.OutputStream;

public class GZIPOutputStream extends DeflaterOutputStream {
    private static final int GZIP_MAGIC = 35615;
    private static final int TRAILER_SIZE = 8;
    protected CRC32 crc;

    public GZIPOutputStream(OutputStream out, int size) throws IOException {
        this(out, size, false);
    }

    public GZIPOutputStream(OutputStream out, int size, boolean syncFlush) throws IOException {
        super(out, new Deflater(-1, true), size, syncFlush);
        this.crc = new CRC32();
        this.usesDefaultDeflater = true;
        writeHeader();
        this.crc.reset();
    }

    public GZIPOutputStream(OutputStream out) throws IOException {
        this(out, 512, false);
    }

    public GZIPOutputStream(OutputStream out, boolean syncFlush) throws IOException {
        this(out, 512, syncFlush);
    }

    @Override // java.io.OutputStream, java.io.FilterOutputStream, java.util.zip.DeflaterOutputStream
    public synchronized void write(byte[] buf, int off, int len) throws IOException {
        super.write(buf, off, len);
        this.crc.update(buf, off, len);
    }

    @Override // java.util.zip.DeflaterOutputStream
    public void finish() throws IOException {
        if (!this.def.finished()) {
            this.def.finish();
            while (!this.def.finished()) {
                int len = this.def.deflate(this.buf, 0, this.buf.length);
                if (this.def.finished() && len <= this.buf.length - 8) {
                    writeTrailer(this.buf, len);
                    this.out.write(this.buf, 0, len + 8);
                    return;
                } else if (len > 0) {
                    this.out.write(this.buf, 0, len);
                }
            }
            byte[] trailer = new byte[8];
            writeTrailer(trailer, 0);
            this.out.write(trailer);
        }
    }

    private void writeHeader() throws IOException {
        this.out.write(new byte[]{31, -117, 8, 0, 0, 0, 0, 0, 0, 0});
    }

    private void writeTrailer(byte[] buf, int offset) throws IOException {
        writeInt((int) this.crc.getValue(), buf, offset);
        writeInt(this.def.getTotalIn(), buf, offset + 4);
    }

    private void writeInt(int i, byte[] buf, int offset) throws IOException {
        writeShort(i & 65535, buf, offset);
        writeShort(65535 & (i >> 16), buf, offset + 2);
    }

    private void writeShort(int s, byte[] buf, int offset) throws IOException {
        buf[offset] = (byte) (s & 255);
        buf[offset + 1] = (byte) ((s >> 8) & 255);
    }
}
