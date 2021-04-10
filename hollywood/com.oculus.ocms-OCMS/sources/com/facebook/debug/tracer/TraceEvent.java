package com.facebook.debug.tracer;

import android.os.SystemClock;
import com.facebook.acra.config.StartupBlockingConfig;
import com.facebook.common.objectpool.ObjectPool;
import com.facebook.common.objectpool.ObjectPoolBuilder;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.debug.log.BLog;
import com.facebook.ultralight.names.UltralightNames;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public class TraceEvent {
    private static final String TAG = "TraceEvent";
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

    static TraceEvent getSpawnEvent(int i, String str) {
        return get(Type.SPAWN, i, str, null, -1, -1);
    }

    static TraceEvent getCommentEvent(int i, String str, Object[] objArr) {
        return get(Type.COMMENT, i, str, objArr, -1, -1);
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

    public String getEventTypeString() {
        return this.mEventType.toString();
    }

    public String getFormattedComment() {
        Object[] objArr = this.mFormatArgs;
        if (objArr != null) {
            try {
                this.mComment = StringFormatUtil.formatStrLocaleSafe(this.mComment, objArr);
                this.mFormatArgs = null;
            } catch (IllegalFormatException e) {
                BLog.wtf(TAG, "Bad format string", e);
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

    /* access modifiers changed from: package-private */
    public void setEventTimeNanos(long j) {
        this.mEventTimeNanos = j;
    }

    public long getThreadEventTimeMs() {
        return this.mThreadEventTimeMs;
    }

    /* access modifiers changed from: package-private */
    public void appendTraceString(StringBuilder sb, long j, long j2, int i) {
        if (j2 == -1) {
            sb.append("-----");
        } else {
            sb.append(longToPaddedString((this.mEventTimeNanos - j2) / 1000000));
        }
        sb.append(" ");
        sb.append(formatTime((this.mEventTimeNanos - j) / 1000000));
        if (this.mEventType == Type.START) {
            sb.append(" Start    ...     ...   ");
        } else if (this.mEventType == Type.START_ASYNC) {
            sb.append(" AStart   ...     ...   ");
        } else if (this.mEventType == Type.STOP || this.mEventType == Type.STOP_ASYNC) {
            sb.append(" Done ");
            sb.append(longToPaddedString((this.mEventTimeNanos - this.mStartTimeNanos) / 1000000));
            sb.append(" ms ");
            sb.append(longToPaddedString(this.mThreadEventTimeMs - this.mThreadStartTimeMs));
            sb.append(" ms ");
        } else if (this.mEventType != Type.SPAWN) {
            sb.append(" Comment  ...     ...   ");
        }
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("|  ");
        }
        sb.append(getFormattedComment());
    }

    public String toString() {
        return getFormattedComment();
    }

    static String longToPaddedString(long j) {
        StringBuilder sb = new StringBuilder(10);
        long round = (long) Math.round((float) j);
        if (round < 10) {
            sb.append("____");
        } else if (round < 100) {
            sb.append("___");
        } else if (round < 1000) {
            sb.append("__");
        } else if (round < StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
            sb.append(UltralightNames.FQN_SEPARATOR);
        }
        sb.append(round);
        return sb.toString();
    }

    static String formatTime(long j) {
        long round = (long) Math.round((float) j);
        long max = Math.max(0L, (round / 1000) % 60);
        long max2 = Math.max(round % 1000, 0L);
        return Long.toString(max + 100).substring(1, 3) + '.' + Long.toString(max2 + 1000).substring(1, 4);
    }
}
