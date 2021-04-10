package okio;

import javax.annotation.Nullable;

public final class SegmentPool {
    public static final long MAX_SIZE = 65536;
    public static long byteCount;
    @Nullable
    public static Segment next;

    public static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        } else if (!segment.shared) {
            synchronized (SegmentPool.class) {
                long j = byteCount + 8192;
                if (j <= MAX_SIZE) {
                    byteCount = j;
                    segment.next = next;
                    segment.limit = 0;
                    segment.pos = 0;
                    next = segment;
                }
            }
        }
    }

    public static Segment take() {
        synchronized (SegmentPool.class) {
            Segment segment = next;
            if (segment == null) {
                return new Segment();
            }
            next = segment.next;
            segment.next = null;
            byteCount -= 8192;
            return segment;
        }
    }
}
