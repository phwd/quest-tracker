package okio;

import com.facebook.systrace.Systrace;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class SegmentPool {
    static final long MAX_SIZE = 65536;
    static long byteCount;
    @Nullable
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
            byteCount -= Systrace.TRACE_TAG_REACT_JAVA_BRIDGE;
            return result;
        }
    }

    static void recycle(Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        } else if (!segment.shared) {
            synchronized (SegmentPool.class) {
                if (byteCount + Systrace.TRACE_TAG_REACT_JAVA_BRIDGE <= 65536) {
                    byteCount += Systrace.TRACE_TAG_REACT_JAVA_BRIDGE;
                    segment.next = next;
                    segment.limit = 0;
                    segment.pos = 0;
                    next = segment;
                }
            }
        }
    }
}
