package okio;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class DeflaterSink implements Sink {
    public boolean closed;
    public final Deflater deflater;
    public final BufferedSink sink;

    @Override // okio.Sink, java.io.Flushable
    public void flush() throws IOException {
        deflate(true);
        this.sink.flush();
    }

    @Override // okio.Sink
    public void write(Buffer buffer, long j) throws IOException {
        long j2 = j;
        Util.checkOffsetAndCount(buffer.size, 0, j2);
        while (j2 > 0) {
            Segment segment = buffer.head;
            int i = segment.limit;
            int i2 = segment.pos;
            int min = (int) Math.min(j2, (long) (i - i2));
            this.deflater.setInput(segment.data, i2, min);
            deflate(false);
            long j3 = (long) min;
            buffer.size -= j3;
            int i3 = segment.pos + min;
            segment.pos = i3;
            if (i3 == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j2 -= j3;
        }
    }

    @IgnoreJRERequirement
    private void deflate(boolean z) throws IOException {
        Segment writableSegment;
        int deflate;
        Buffer buffer = this.sink.buffer();
        while (true) {
            writableSegment = buffer.writableSegment(1);
            Deflater deflater2 = this.deflater;
            byte[] bArr = writableSegment.data;
            int i = writableSegment.limit;
            int i2 = 8192 - i;
            if (z) {
                deflate = deflater2.deflate(bArr, i, i2, 2);
            } else {
                deflate = deflater2.deflate(bArr, i, i2);
            }
            if (deflate > 0) {
                writableSegment.limit += deflate;
                buffer.size += (long) deflate;
                this.sink.emitCompleteSegments();
            } else if (this.deflater.needsInput()) {
                break;
            }
        }
        if (writableSegment.pos == writableSegment.limit) {
            buffer.head = writableSegment.pop();
            SegmentPool.recycle(writableSegment);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, okio.Sink
    public void close() throws IOException {
        if (!this.closed) {
            Throwable th = null;
            try {
                finishDeflate();
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

    public void finishDeflate() throws IOException {
        this.deflater.finish();
        deflate(false);
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DeflaterSink(");
        sb.append(this.sink);
        sb.append(")");
        return sb.toString();
    }

    public DeflaterSink(BufferedSink bufferedSink, Deflater deflater2) {
        if (bufferedSink == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater2 != null) {
            this.sink = bufferedSink;
            this.deflater = deflater2;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    public DeflaterSink(Sink sink2, Deflater deflater2) {
        this((BufferedSink) new RealBufferedSink(sink2), deflater2);
    }
}
