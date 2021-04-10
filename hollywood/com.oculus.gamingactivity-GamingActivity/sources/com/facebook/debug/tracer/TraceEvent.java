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

        public void onRelease(TraceEvent obj) {
            obj.mComment = null;
            obj.mFormatArgs = null;
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

    static TraceEvent getSpawnEvent(int id, String comment) {
        return get(Type.SPAWN, id, comment, null, -1, -1);
    }

    static TraceEvent getCommentEvent(int id, String comment, Object[] args) {
        return get(Type.COMMENT, id, comment, args, -1, -1);
    }

    static TraceEvent getStartEvent(int id, String comment, Object[] args, boolean async) {
        return get(async ? Type.START_ASYNC : Type.START, id, comment, args, -1, -1);
    }

    static TraceEvent getStopEvent(TraceEvent startEvent) {
        return get(startEvent.getEventType() == Type.START ? Type.STOP : Type.STOP_ASYNC, startEvent.mId, startEvent.mComment, startEvent.mFormatArgs, startEvent.mEventTimeNanos, startEvent.mThreadEventTimeMs);
    }

    private static TraceEvent get(Type eventType, int id, String comment, @Nullable Object[] formatArgs, long startTimeNanos, long threadStartTimeMillis) {
        TraceEvent evt = sEventPool.allocate();
        evt.mEventType = eventType;
        evt.mId = id;
        evt.mFormatArgs = formatArgs;
        evt.mComment = comment;
        evt.mThreadEventTimeMs = SystemClock.currentThreadTimeMillis();
        evt.mEventTimeNanos = TracerClock.nanoTime();
        evt.mStartTimeNanos = startTimeNanos;
        evt.mThreadStartTimeMs = threadStartTimeMillis;
        return evt;
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
        if (this.mFormatArgs != null) {
            try {
                this.mComment = StringFormatUtil.formatStrLocaleSafe(this.mComment, this.mFormatArgs);
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
    public void setEventTimeNanos(long eventTimeNanos) {
        this.mEventTimeNanos = eventTimeNanos;
    }

    public long getThreadEventTimeMs() {
        return this.mThreadEventTimeMs;
    }

    /* access modifiers changed from: package-private */
    public void appendTraceString(StringBuilder sb, long traceStartTime, long prevTime, int indents) {
        if (prevTime == -1) {
            sb.append("-----");
        } else {
            sb.append(longToPaddedString((this.mEventTimeNanos - prevTime) / 1000000));
        }
        sb.append(" ");
        sb.append(formatTime((this.mEventTimeNanos - traceStartTime) / 1000000));
        if (this.mEventType == Type.START) {
            sb.append(" Start    ...     ...   ");
        } else if (this.mEventType == Type.START_ASYNC) {
            sb.append(" AStart   ...     ...   ");
        } else if (this.mEventType == Type.STOP || this.mEventType == Type.STOP_ASYNC) {
            sb.append(" Done ");
            long deltaTimeNanos = this.mEventTimeNanos - this.mStartTimeNanos;
            long threadDeltaMs = this.mThreadEventTimeMs - this.mThreadStartTimeMs;
            sb.append(longToPaddedString(deltaTimeNanos / 1000000));
            sb.append(" ms ");
            sb.append(longToPaddedString(threadDeltaMs));
            sb.append(" ms ");
        } else if (this.mEventType != Type.SPAWN) {
            sb.append(" Comment  ...     ...   ");
        }
        for (int i = 0; i < indents; i++) {
            sb.append("|  ");
        }
        sb.append(getFormattedComment());
    }

    public String toString() {
        return getFormattedComment();
    }

    static String longToPaddedString(long v) {
        StringBuilder sb = new StringBuilder(10);
        long v2 = (long) Math.round((float) v);
        if (v2 < 10) {
            sb.append("____");
        } else if (v2 < 100) {
            sb.append("___");
        } else if (v2 < 1000) {
            sb.append("__");
        } else if (v2 < StartupBlockingConfig.BLOCKING_UPLOAD_MAX_WAIT_MILLIS) {
            sb.append(UltralightNames.FQN_SEPARATOR);
        }
        sb.append(v2);
        return sb.toString();
    }

    static String formatTime(long time) {
        long time2 = (long) Math.round((float) time);
        return Long.toString(100 + Math.max(0L, (time2 / 1000) % 60)).substring(1, 3) + '.' + Long.toString(1000 + Math.max(time2 % 1000, 0L)).substring(1, 4);
    }
}
