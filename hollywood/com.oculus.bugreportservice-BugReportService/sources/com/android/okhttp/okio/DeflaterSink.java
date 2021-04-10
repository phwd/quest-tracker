package com.android.okhttp.okio;

import java.util.zip.Deflater;

public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;

    public DeflaterSink(Sink sink2, Deflater deflater2) {
        this(Okio.buffer(sink2), deflater2);
    }

    DeflaterSink(BufferedSink bufferedSink, Deflater deflater2) {
        if (bufferedSink == null) {
            throw new IllegalArgumentException("source == null");
        } else if (deflater2 != null) {
            this.sink = bufferedSink;
            this.deflater = deflater2;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    @Override // com.android.okhttp.okio.Sink
    public void write(Buffer buffer, long j) {
        Util.checkOffsetAndCount(buffer.size, 0, j);
        while (j > 0) {
            Segment segment = buffer.head;
            int min = (int) Math.min(j, (long) (segment.limit - segment.pos));
            this.deflater.setInput(segment.data, segment.pos, min);
            deflate(false);
            long j2 = (long) min;
            buffer.size -= j2;
            segment.pos += min;
            if (segment.pos == segment.limit) {
                buffer.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j -= j2;
        }
    }

    private void deflate(boolean z) {
        Segment writableSegment;
        int i;
        Buffer buffer = this.sink.buffer();
        while (true) {
            writableSegment = buffer.writableSegment(1);
            if (z) {
                Deflater deflater2 = this.deflater;
                byte[] bArr = writableSegment.data;
                int i2 = writableSegment.limit;
                i = deflater2.deflate(bArr, i2, 8192 - i2, 2);
            } else {
                Deflater deflater3 = this.deflater;
                byte[] bArr2 = writableSegment.data;
                int i3 = writableSegment.limit;
                i = deflater3.deflate(bArr2, i3, 8192 - i3);
            }
            if (i > 0) {
                writableSegment.limit += i;
                buffer.size += (long) i;
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

    @Override // com.android.okhttp.okio.Sink
    public void flush() {
        deflate(true);
        this.sink.flush();
    }

    /* access modifiers changed from: package-private */
    public void finishDeflate() {
        this.deflater.finish();
        deflate(false);
    }

    @Override // com.android.okhttp.okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        Throwable th;
        if (!this.closed) {
            try {
                finishDeflate();
                th = null;
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
                Util.sneakyRethrow(th);
                throw null;
            }
        }
    }

    @Override // com.android.okhttp.okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "DeflaterSink(" + this.sink + ")";
    }
}
