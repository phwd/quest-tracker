package okio;

import X.AnonymousClass006;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

public final class GzipSink implements Sink {
    public boolean closed;
    public final CRC32 crc = new CRC32();
    public final Deflater deflater;
    public final DeflaterSink deflaterSink;
    public final BufferedSink sink;

    private void updateCrc(Buffer buffer, long j) {
        Segment segment = buffer.head;
        while (j > 0) {
            int i = segment.limit;
            int i2 = segment.pos;
            int min = (int) Math.min(j, (long) (i - i2));
            this.crc.update(segment.data, i2, min);
            j -= (long) min;
            segment = segment.next;
        }
    }

    private void writeFooter() throws IOException {
        this.sink.writeIntLe((int) this.crc.getValue());
        this.sink.writeIntLe((int) this.deflater.getBytesRead());
    }

    private void writeHeader() {
        Buffer buffer = this.sink.buffer();
        buffer.writeShort(8075);
        buffer.writeByte(8);
        buffer.writeByte(0);
        buffer.writeInt(0);
        buffer.writeByte(0);
        buffer.writeByte(0);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
    public void close() throws IOException {
        if (!this.closed) {
            Throwable th = null;
            try {
                this.deflaterSink.finishDeflate();
                writeFooter();
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.deflater.end();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            try {
                this.sink.close();
            } catch (Throwable th4) {
                if (th == null) {
                    th = th4;
                }
            }
            this.closed = true;
            if (th != null) {
                throw th;
            }
        }
    }

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        this.deflaterSink.flush();
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
        } else if (j != 0) {
            updateCrc(buffer, j);
            this.deflaterSink.write(buffer, j);
        }
    }

    public GzipSink(Sink sink2) {
        if (sink2 != null) {
            Deflater deflater2 = new Deflater(-1, true);
            this.deflater = deflater2;
            RealBufferedSink realBufferedSink = new RealBufferedSink(sink2);
            this.sink = realBufferedSink;
            this.deflaterSink = new DeflaterSink((BufferedSink) realBufferedSink, deflater2);
            writeHeader();
            return;
        }
        throw new IllegalArgumentException("sink == null");
    }

    public Deflater deflater() {
        return this.deflater;
    }
}
