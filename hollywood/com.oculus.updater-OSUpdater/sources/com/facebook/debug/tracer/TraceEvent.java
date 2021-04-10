package com.facebook.debug.tracer;

import android.os.SystemClock;
import com.facebook.common.objectpool.ObjectPool;
import com.facebook.common.objectpool.ObjectPoolBuilder;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.debug.log.BLog;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public class TraceEvent {
    private static final ObjectPool<TraceEvent> sEventPool = new ObjectPoolBuilder(TraceEvent.class, AwakeTimeSinceBootClock.get()).setAllocator(new ObjectPool.BasicAllocator<TraceEvent>(TraceEvent.class) {
        /* class com.facebook.debug.tracer.TraceEvent.AnonymousClass1 */

        @Override // com.facebook.common.objectpool.ObjectPool.BasicAllocator, com.facebook.common.objectpool.ObjectPool.Allocator
        public TraceEvent create() {
            return new TraceEvent();
        }

        public void onRelease(TraceEvent traceEvent) {
            traceEvent.mComment = null;
            traceEvent.mFormatArgs = null;
        }
    }).build();
    private String mComment;
    private long mEventTimeNanos;
    private Type mEventType;
    @Nullable
    private Object[] mFormatArgs;
    private int mId;
    private long mStartTimeNanos;
    private long mThreadEventTimeMs;
    private long mThreadStartTimeMs;

    public enum Type {
        START,
        STOP,
        START_ASYNC,
        STOP_ASYNC,
        COMMENT,
        SPAWN;

        public boolean isStartLikeEvent() {
            return this == START || this == START_ASYNC;
        }

        public boolean isStopLikeEvent() {
            return this == STOP || this == STOP_ASYNC;
        }
    }

    private TraceEvent() {
    }

    static TraceEvent getStartEvent(int i, String str, Object[] objArr, boolean z) {
        return get(z ? Type.START_ASYNC : Type.START, i, str, objArr, -1, -1);
    }

    static TraceEvent getStopEvent(TraceEvent traceEvent) {
        return get(traceEvent.getEventType() == Type.START ? Type.STOP : Type.STOP_ASYNC, traceEvent.mId, traceEvent.mComment, traceEvent.mFormatArgs, traceEvent.mEventTimeNanos, traceEvent.mThreadEventTimeMs);
    }

    private static TraceEvent get(Type type, int i, String str, @Nullable Object[] objArr, long j, long j2) {
        TraceEvent allocate = sEventPool.allocate();
        allocate.mEventType = type;
        allocate.mId = i;
        allocate.mFormatArgs = objArr;
        allocate.mComment = str;
        allocate.mThreadEventTimeMs = SystemClock.currentThreadTimeMillis();
        allocate.mEventTimeNanos = TracerClock.nanoTime();
        allocate.mStartTimeNanos = j;
        allocate.mThreadStartTimeMs = j2;
        return allocate;
    }

    /* access modifiers changed from: package-private */
    public void release() {
        sEventPool.release(this);
    }

    public int getId() {
        return this.mId;
    }

    public Type getEventType() {
        return this.mEventType;
    }

    public String getFormattedComment() {
        Object[] objArr = this.mFormatArgs;
        if (objArr != null) {
            try {
                this.mComment = StringFormatUtil.formatStrLocaleSafe(this.mComment, objArr);
                this.mFormatArgs = null;
            } catch (IllegalFormatException e) {
                BLog.wtf("TraceEvent", "Bad format string", e);
                this.mFormatArgs = null;
            }
        }
        return this.mComment;
    }

    public long getStartTimeNanos() {
        return this.mStartTimeNanos;
    }

    public long getEventTimeNanos() {
        return this.mEventTimeNanos;
    }

    public String toString() {
        return getFormattedComment();
    }
}
