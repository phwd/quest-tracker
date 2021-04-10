package com.facebook.debug.tracer;

import android.os.Build;
import android.util.SparseArray;
import com.facebook.common.time.Clock;
import com.facebook.debug.log.BLog;
import com.facebook.debug.tracer.TraceEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* access modifiers changed from: package-private */
public final class ThreadTrace {
    static final int HARD_TRACE_EVENT_LIMIT_SIZE = 2000;
    static int INVALID_TRACE_ID = -1;
    static final long MAX_TIME_LIMIT_NS = 180000000000L;
    static final int SOFT_TRACE_EVENT_LIMIT_SIZE = 1500;
    private static final String TAG = "ThreadTrace";
    private static final long mDefaultThresholdNanos = 3000000;
    private static final Comparator<TraceEvent> sComparatorByElapsedNanos = new Comparator<TraceEvent>() {
        /* class com.facebook.debug.tracer.ThreadTrace.AnonymousClass2 */

        public int compare(TraceEvent e1, TraceEvent e2) {
            long d1 = e1.getEventTimeNanos() - e1.getStartTimeNanos();
            long d2 = e2.getEventTimeNanos() - e2.getStartTimeNanos();
            if (d1 < d2) {
                return -1;
            }
            if (d1 > d2) {
                return 1;
            }
            return 0;
        }
    };
    private static final AtomicInteger sIdGenerator = new AtomicInteger(1);
    static final ThreadLocal<ThreadTrace> sThreadLocalState = new ThreadLocal<ThreadTrace>() {
        /* class com.facebook.debug.tracer.ThreadTrace.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public ThreadTrace initialValue() {
            return new ThreadTrace();
        }
    };
    private TraceEvent[] mEvents = new TraceEvent[100];
    private int mEventsSize;
    private int mNullCount;
    private final SparseArray<TraceEvent> mOutstandingEvents = new SparseArray<>();

    ThreadTrace() {
    }

    /* access modifiers changed from: package-private */
    public int startTracer(String comment, boolean async) {
        return startTracer(comment, null, async);
    }

    /* access modifiers changed from: package-private */
    public int startTracer(String comment, Object[] args, boolean async) {
        pruneTracers();
        int id = getNextId();
        TraceEvent event = TraceEvent.getStartEvent(id, comment, args, async);
        addInternal(event);
        this.mOutstandingEvents.append(id, event);
        return event.getId();
    }

    /* access modifiers changed from: package-private */
    public void adjustStartTime(int id, long timeOffsetMs) {
        TraceEvent startEvent = this.mOutstandingEvents.get(id);
        if (startEvent != null) {
            startEvent.setEventTimeNanos(startEvent.getEventTimeNanos() + (1000000 * timeOffsetMs));
        }
    }

    /* access modifiers changed from: package-private */
    public long stopTracer(int id, long silenceThresholdMsParam, boolean useSilenceThreshold) {
        long silenceThresholdNanos;
        long nowNanos = TracerClock.nanoTime();
        if (!useSilenceThreshold) {
            silenceThresholdNanos = mDefaultThresholdNanos;
        } else {
            silenceThresholdNanos = silenceThresholdMsParam * 1000000;
        }
        int index = this.mOutstandingEvents.indexOfKey(id);
        if (index < 0) {
            return -1;
        }
        TraceEvent startEvent = this.mOutstandingEvents.valueAt(index);
        if (Build.VERSION.SDK_INT >= 11) {
            this.mOutstandingEvents.removeAt(index);
        } else {
            this.mOutstandingEvents.remove(id);
        }
        long elapsedNanos = nowNanos - startEvent.getEventTimeNanos();
        if (elapsedNanos < silenceThresholdNanos) {
            for (int i = this.mEventsSize - 1; i >= 0; i--) {
                if (this.mEvents[i] == startEvent) {
                    this.mEvents[i] = null;
                    this.mNullCount++;
                    startEvent.release();
                    return elapsedNanos;
                }
            }
            return elapsedNanos;
        }
        addInternal(TraceEvent.getStopEvent(startEvent));
        return elapsedNanos;
    }

    public TraceEvent getEvent(int id) {
        return this.mOutstandingEvents.get(id);
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                if (traceEvent.getEventType() == TraceEvent.Type.START) {
                    this.mOutstandingEvents.remove(traceEvent.getId());
                }
                traceEvent.release();
            }
        }
        this.mEventsSize = 0;
        this.mNullCount = 0;
        this.mOutstandingEvents.clear();
    }

    /* access modifiers changed from: package-private */
    public void addComment(String comment, Object[] args) {
        addInternal(TraceEvent.getCommentEvent(getNextId(), comment, args));
    }

    /* access modifiers changed from: package-private */
    public void addSpawn(String comment) {
        addInternal(TraceEvent.getSpawnEvent(getNextId(), comment));
    }

    /* access modifiers changed from: package-private */
    public int getLastTraceId() {
        for (int i = this.mEventsSize - 1; i >= 0; i--) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                return traceEvent.getId();
            }
        }
        return INVALID_TRACE_ID;
    }

    /* access modifiers changed from: package-private */
    public long getLastEventTimeNanos() {
        for (int i = this.mEventsSize - 1; i >= 0; i--) {
            TraceEvent traceEvent = this.mEvents[i];
            if (traceEvent != null) {
                return traceEvent.getEventTimeNanos();
            }
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public List<TraceEvent> getEvents(int traceId) {
        removeNulls();
        int startIndex = getStartTraceIndex(traceId);
        if (startIndex == -1) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(Arrays.asList(this.mEvents).subList(startIndex, getStopTraceIndex(traceId) + 1));
    }

    /* access modifiers changed from: package-private */
    public List<TraceEvent> getAllEvents() {
        removeNulls();
        return Collections.unmodifiableList(Arrays.asList(this.mEvents).subList(0, this.mEventsSize));
    }

    /* access modifiers changed from: package-private */
    public void logTrace(int traceId, int logLevel, String tag) {
        TraceFormatter.logTrace(traceId, logLevel, tag, Arrays.asList(this.mEvents).subList(0, this.mEventsSize), this.mOutstandingEvents);
    }

    /* access modifiers changed from: package-private */
    public void pruneTracers() {
        long start = TracerClock.realNanoTime();
        try {
            if (getTracerCount() > HARD_TRACE_EVENT_LIMIT_SIZE) {
                int pruned = 0 + pruneOldStoppedTracers() + pruneFineGrainedTracers(SOFT_TRACE_EVENT_LIMIT_SIZE);
                if (getTracerCount() > SOFT_TRACE_EVENT_LIMIT_SIZE) {
                    pruned += pruneComments(SOFT_TRACE_EVENT_LIMIT_SIZE);
                }
                if (getTracerCount() > HARD_TRACE_EVENT_LIMIT_SIZE) {
                    BLog.wtf(TAG, "Resetting because hit couldn't get under hard limit after normal pruning");
                    reset();
                } else if (getTracerCount() > SOFT_TRACE_EVENT_LIMIT_SIZE) {
                    BLog.w(TAG, "Couldn't get under soft limit after normal pruning");
                }
                long finished = TracerClock.realNanoTime();
                if (finished - start > 1000000 || pruned > 0) {
                    BLog.d(TAG, "Pruned %d events on thread 0x%x in %d ms", Integer.valueOf(pruned), Long.valueOf(Thread.currentThread().getId()), Long.valueOf((finished - start) / 1000000));
                }
            }
        } finally {
            long finished2 = TracerClock.realNanoTime();
            if (finished2 - start > 1000000 || 0 > 0) {
                BLog.d(TAG, "Pruned %d events on thread 0x%x in %d ms", (Object) 0, (Object) Long.valueOf(Thread.currentThread().getId()), (Object) Long.valueOf((finished2 - start) / 1000000));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int pruneOldStoppedTracers() {
        long now = TracerClock.nanoTime();
        int syncNestingLevel = 0;
        int lastTruncationPoint = 0;
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent event = this.mEvents[i];
            if (event != null) {
                TraceEvent.Type eventType = event.getEventType();
                if (eventType == TraceEvent.Type.START) {
                    syncNestingLevel++;
                } else if (eventType == TraceEvent.Type.STOP) {
                    syncNestingLevel--;
                }
                if (now - event.getEventTimeNanos() < MAX_TIME_LIMIT_NS) {
                    break;
                } else if (syncNestingLevel == 0) {
                    lastTruncationPoint = i;
                }
            }
        }
        int removed = 0;
        if (lastTruncationPoint > 0) {
            for (int i2 = 0; i2 < lastTruncationPoint + 1; i2++) {
                TraceEvent traceEvent = this.mEvents[i2];
                if (traceEvent != null) {
                    traceEvent.release();
                    this.mEvents[i2] = null;
                    this.mNullCount++;
                    removed++;
                }
            }
        }
        BLog.d(TAG, "Pruned %d old events", Integer.valueOf(removed));
        return removed;
    }

    /* access modifiers changed from: package-private */
    public int pruneFineGrainedTracers(int maxEvents) {
        long nanosThreshold = calculateMaxEventsNanosThreshold(maxEvents);
        if (nanosThreshold < 0) {
            return 0;
        }
        return pruneFineGrainedTracersAtNanosThreshold(nanosThreshold);
    }

    /* access modifiers changed from: package-private */
    public long calculateMaxEventsNanosThreshold(int maxEvents) {
        if (getTracerCount() <= maxEvents) {
            return -1;
        }
        PriorityQueue<TraceEvent> priorityQueue = new PriorityQueue<>(this.mEventsSize / 2, sComparatorByElapsedNanos);
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent event = this.mEvents[i];
            if (event != null && event.getEventType() == TraceEvent.Type.STOP) {
                priorityQueue.add(event);
            }
        }
        int countToDelete = getTracerCount() - maxEvents;
        Iterator<TraceEvent> iter = priorityQueue.iterator();
        long nanosThreshold = 0;
        while (iter.hasNext() && countToDelete > 0) {
            TraceEvent next = iter.next();
            nanosThreshold = next.getEventTimeNanos() - next.getStartTimeNanos();
            countToDelete -= 2;
        }
        return countToDelete > 0 ? Clock.MAX_TIME : nanosThreshold;
    }

    /* access modifiers changed from: package-private */
    public int pruneFineGrainedTracersAtNanosThreshold(long thresholdNanos) {
        int pruned = 0;
        int[] stack = new int[10];
        int stackSize = 0;
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent event = this.mEvents[i];
            if (event != null) {
                if (event.getEventType() == TraceEvent.Type.START) {
                    if (stackSize >= stack.length) {
                        stack = Arrays.copyOf(stack, ((stack.length * 3) / 2) + 1);
                    }
                    stack[stackSize] = i;
                    stackSize++;
                } else if (event.getEventType() == TraceEvent.Type.STOP) {
                    int startIndex = stack[stackSize - 1];
                    stackSize--;
                    if (event.getEventTimeNanos() - event.getStartTimeNanos() <= thresholdNanos) {
                        TraceEvent startEvent = this.mEvents[startIndex];
                        event.release();
                        startEvent.release();
                        this.mEvents[i] = null;
                        this.mEvents[startIndex] = null;
                        this.mNullCount += 2;
                        pruned += 2;
                    }
                }
            }
        }
        BLog.d(TAG, "Pruned %d fine grain events", Integer.valueOf(pruned));
        return pruned;
    }

    /* access modifiers changed from: package-private */
    public int pruneComments(int maxEvents) {
        int pruned = 0;
        int toDelete = getTracerCount() - maxEvents;
        for (int i = 0; i < this.mEventsSize && toDelete > 0; i++) {
            TraceEvent event = this.mEvents[i];
            if (event != null && event.getEventType() == TraceEvent.Type.COMMENT) {
                event.release();
                this.mEvents[i] = null;
                this.mNullCount++;
                pruned++;
                toDelete--;
            }
        }
        BLog.d(TAG, "Pruned %d comments", Integer.valueOf(pruned));
        return pruned;
    }

    private void removeNulls() {
        int toIndex = 0;
        for (int fromIndex = 0; fromIndex < this.mEventsSize; fromIndex++) {
            TraceEvent traceEvent = this.mEvents[fromIndex];
            if (traceEvent != null) {
                this.mEvents[toIndex] = traceEvent;
                toIndex++;
            }
        }
        this.mEventsSize = toIndex;
        this.mNullCount = 0;
    }

    private int getStartTraceIndex(int traceId) {
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent e = this.mEvents[i];
            if (e != null && e.getEventType().isStartLikeEvent() && e.getId() == traceId) {
                return i;
            }
        }
        return -1;
    }

    private int getStopTraceIndex(int traceId) {
        for (int i = 0; i < this.mEventsSize; i++) {
            TraceEvent e = this.mEvents[i];
            if (e != null && e.getEventType().isStopLikeEvent() && e.getId() == traceId) {
                return i;
            }
        }
        return this.mEventsSize - 1;
    }

    private int getTracerCount() {
        return this.mEventsSize - this.mNullCount;
    }

    private void addInternal(TraceEvent event) {
        if (this.mNullCount * 8 > this.mEventsSize) {
            removeNulls();
        }
        if (this.mEventsSize >= this.mEvents.length) {
            this.mEvents = (TraceEvent[]) Arrays.copyOf(this.mEvents, (this.mEvents.length * 2) + 1);
        }
        TraceEvent[] traceEventArr = this.mEvents;
        int i = this.mEventsSize;
        this.mEventsSize = i + 1;
        traceEventArr[i] = event;
    }

    private static int getNextId() {
        int id = sIdGenerator.incrementAndGet();
        while (true) {
            if (id != INVALID_TRACE_ID && id != 0) {
                return id;
            }
            id = sIdGenerator.incrementAndGet();
        }
    }
}
