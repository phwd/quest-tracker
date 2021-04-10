package okio;

import X.AnonymousClass006;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public final class InflaterSource implements Source {
    public int bufferBytesHeldByInflater;
    public boolean closed;
    public final Inflater inflater;
    public final BufferedSource source;

    private void releaseInflatedBytes() throws IOException {
        int i = this.bufferBytesHeldByInflater;
        if (i != 0) {
            int remaining = i - this.inflater.getRemaining();
            this.bufferBytesHeldByInflater -= remaining;
            this.source.skip((long) remaining);
        }
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (!this.closed) {
            this.inflater.end();
            this.closed = true;
            this.source.close();
        }
    }

    @Override // okio.Source
    public long read(Buffer buffer, long j) throws IOException {
        boolean refill;
        if (j < 0) {
            throw new IllegalArgumentException(AnonymousClass006.A06("byteCount < 0: ", j));
        } else if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (j == 0) {
            return 0;
        } else {
            do {
                refill = refill();
                try {
                    Segment writableSegment = buffer.writableSegment(1);
                    int i = writableSegment.limit;
                    int inflate = this.inflater.inflate(writableSegment.data, i, (int) Math.min(j, (long) (8192 - i)));
                    if (inflate > 0) {
                        writableSegment.limit += inflate;
                        long j2 = (long) inflate;
                        buffer.size += j2;
                        return j2;
                    } else if (this.inflater.finished() || this.inflater.needsDictionary()) {
                        releaseInflatedBytes();
                        if (writableSegment.pos != writableSegment.limit) {
                            return -1;
                        }
                        buffer.head = writableSegment.pop();
                        SegmentPool.recycle(writableSegment);
                        return -1;
                    }
                } catch (DataFormatException e) {
                    throw new IOException(e);
                }
            } while (!refill);
            throw new EOFException("source exhausted prematurely");
        }
    }

    public boolean refill() throws IOException {
        if (!this.inflater.needsInput()) {
            return false;
        }
        releaseInflatedBytes();
        if (this.inflater.getRemaining() != 0) {
            throw new IllegalStateException("?");
        } else if (this.source.exhausted()) {
            return true;
        } else {
            Segment segment = this.source.buffer().head;
            int i = segment.limit;
            int i2 = segment.pos;
            int i3 = i - i2;
            this.bufferBytesHeldByInflater = i3;
            this.inflater.setInput(segment.data, i2, i3);
            return false;
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public InflaterSource(BufferedSource bufferedSource, Inflater inflater2) {
        if (bufferedSource == null) {
            throw new IllegalArgumentException("source == null");
        } else if (inflater2 != null) {
            this.source = bufferedSource;
            this.inflater = inflater2;
        } else {
            throw new IllegalArgumentException("inflater == null");
        }
    }

    public InflaterSource(Source source2, Inflater inflater2) {
        this(Okio.buffer(source2), inflater2);
    }
}
