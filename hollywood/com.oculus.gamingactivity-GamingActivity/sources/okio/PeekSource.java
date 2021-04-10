package okio;

import java.io.IOException;

final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment = this.buffer.head;
    private long pos;
    private final BufferedSource upstream;

    PeekSource(BufferedSource upstream2) {
        this.upstream = upstream2;
        this.buffer = upstream2.buffer();
        this.expectedPos = this.expectedSegment != null ? this.expectedSegment.pos : -1;
    }

    @Override // okio.Source
    public long read(Buffer sink, long byteCount) throws IOException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        } else if (this.expectedSegment == null || (this.expectedSegment == this.buffer.head && this.expectedPos == this.buffer.head.pos)) {
            this.upstream.request(this.pos + byteCount);
            if (this.expectedSegment == null && this.buffer.head != null) {
                this.expectedSegment = this.buffer.head;
                this.expectedPos = this.buffer.head.pos;
            }
            long toCopy = Math.min(byteCount, this.buffer.size - this.pos);
            if (toCopy <= 0) {
                return -1;
            }
            this.buffer.copyTo(sink, this.pos, toCopy);
            this.pos += toCopy;
            return toCopy;
        } else {
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.upstream.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.closed = true;
    }
}
