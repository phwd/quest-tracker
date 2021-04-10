package okio;

import javax.annotation.Nullable;

public final class Segment {
    public static final int SHARE_MINIMUM = 1024;
    public static final int SIZE = 8192;
    public final byte[] data;
    public int limit;
    public Segment next;
    public boolean owner;
    public int pos;
    public Segment prev;
    public boolean shared;

    public Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public void compact() {
        int i;
        Segment segment = this.prev;
        if (segment == this) {
            throw new IllegalStateException();
        } else if (segment.owner) {
            int i2 = this.limit - this.pos;
            int i3 = 8192 - segment.limit;
            if (segment.shared) {
                i = 0;
            } else {
                i = segment.pos;
            }
            if (i2 <= i3 + i) {
                writeTo(segment, i2);
                pop();
                SegmentPool.recycle(this);
            }
        }
    }

    @Nullable
    public Segment pop() {
        Segment segment = this.next;
        if (segment == this) {
            segment = null;
        }
        Segment segment2 = this.prev;
        segment2.next = segment;
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
        Segment take;
        if (i <= 0 || i > this.limit - this.pos) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            take = sharedCopy();
        } else {
            take = SegmentPool.take();
            System.arraycopy(this.data, this.pos, take.data, 0, i);
        }
        take.limit = take.pos + i;
        this.pos += i;
        this.prev.push(take);
        return take;
    }

    public Segment unsharedCopy() {
        return new Segment((byte[]) this.data.clone(), this.pos, this.limit, false, true);
    }

    public void writeTo(Segment segment, int i) {
        if (segment.owner) {
            int i2 = segment.limit;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!segment.shared) {
                    int i4 = segment.pos;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = segment.data;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        i2 = segment.limit - segment.pos;
                        segment.limit = i2;
                        segment.pos = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.data, this.pos, segment.data, i2, i);
            segment.limit += i;
            this.pos += i;
            return;
        }
        throw new IllegalArgumentException();
    }

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.data = bArr;
        this.pos = i;
        this.limit = i2;
        this.shared = z;
        this.owner = z2;
    }
}
