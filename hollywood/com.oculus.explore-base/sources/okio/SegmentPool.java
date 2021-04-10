package okio;

/* access modifiers changed from: package-private */
public final class SegmentPool {
    static long byteCount;
    static Segment next;

    private SegmentPool() {
    }

    static Segment take() {
        synchronized (SegmentPool.class) {
            if (next == null) {
                return new Segment();
            }
            Segment result = next;
            next = result.next;
            result.next = null;
            byteCount -= 8192;
            return result;
        }
    }

    static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        } else if (!segment.shared) {
            synchronized (SegmentPool.class) {
                if (byteCount + 8192 <= 65536) {
                    byteCount += 8192;
                    segment.next = next;
                    segment.limit = 0;
                    segment.pos = 0;
                    next = segment;
                }
            }
        }
    }
}
