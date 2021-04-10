package com.android.okhttp.okio;

/* access modifiers changed from: package-private */
public final class Segment {
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

    Segment(Segment segment) {
        this(segment.data, segment.pos, segment.limit);
        segment.shared = true;
    }

    Segment(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.owner = false;
        this.shared = true;
    }

    public Segment pop() {
        Segment segment = this.next;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.prev;
        segment2.next = this.next;
        this.next.prev = segment2;
        this.next = null;
        this.prev = null;
        return segment;
    }

    public Segment push(Segment segment) {
        segment.prev = this;
        segment.next = this.next;
        this.next.prev = segment;
        this.next = segment;
        return segment;
    }

    public Segment split(int i) {
        if (i <= 0 || i > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        Segment segment = new Segment(this);
        segment.limit = segment.pos + i;
        this.pos += i;
        this.prev.push(segment);
        return segment;
    }

    public void compact() {
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException();
        } else if (segment.owner) {
            int i = this.limit - this.pos;
            if (i <= (8192 - segment.limit) + (segment.shared ? 0 : segment.pos)) {
                writeTo(this.prev, i);
                pop();
                SegmentPool.recycle(this);
            }
        }
    }

    public void writeTo(Segment segment, int i) {
        if (segment.owner) {
            int i2 = segment.limit;
            if (i2 + i > 8192) {
                if (!segment.shared) {
                    int i3 = segment.pos;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = segment.data;
                        System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
                        segment.limit -= segment.pos;
                        segment.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.data, this.pos, segment.data, segment.limit, i);
            segment.limit += i;
            this.pos += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
