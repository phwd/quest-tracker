package com.android.okhttp.okio;

/* access modifiers changed from: package-private */
public final class Segment {
    static final int SIZE = 8192;
    final byte[] data;
    int limit;
    Segment next;
    boolean owner;
    int pos;
    Segment prev;
    boolean shared;

    Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    Segment(Segment shareFrom) {
        this(shareFrom.data, shareFrom.pos, shareFrom.limit);
        shareFrom.shared = true;
    }

    Segment(byte[] data2, int pos2, int limit2) {
        this.data = data2;
        this.pos = pos2;
        this.limit = limit2;
        this.owner = false;
        this.shared = true;
    }

    public Segment pop() {
        Segment result = this.next;
        if (result == this) {
            result = null;
        }
        Segment segment = this.prev;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = null;
        this.prev = null;
        return result;
    }

    public Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public Segment split(int byteCount) {
        if (byteCount <= 0 || byteCount > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        Segment prefix = new Segment(this);
        prefix.limit = prefix.pos + byteCount;
        this.pos += byteCount;
        this.prev.push(prefix);
        return prefix;
    }

    public void compact() {
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException();
        } else if (segment.owner) {
            int byteCount = this.limit - this.pos;
            if (byteCount <= (8192 - segment.limit) + (segment.shared ? 0 : segment.pos)) {
                writeTo(this.prev, byteCount);
                pop();
                SegmentPool.recycle(this);
            }
        }
    }

    public void writeTo(Segment sink, int byteCount) {
        if (sink.owner) {
            int i = sink.limit;
            if (i + byteCount > 8192) {
                if (!sink.shared) {
                    int i2 = sink.pos;
                    if ((i + byteCount) - i2 <= 8192) {
                        byte[] bArr = sink.data;
                        System.arraycopy(bArr, i2, bArr, 0, i - i2);
                        sink.limit -= sink.pos;
                        sink.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.data, this.pos, sink.data, sink.limit, byteCount);
            sink.limit += byteCount;
            this.pos += byteCount;
            return;
        }
        throw new IllegalArgumentException();
    }
}
