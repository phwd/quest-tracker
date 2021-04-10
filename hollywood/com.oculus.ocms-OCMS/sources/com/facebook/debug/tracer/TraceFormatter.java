package com.facebook.debug.tracer;

import android.text.TextUtils;
import android.util.SparseArray;
import com.facebook.debug.log.BLog;
import com.facebook.debug.tracer.TraceEvent;
import java.util.List;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class TraceFormatter {
    private static final int AFTER_PIPES = 2;
    private static final int BEFORE_PIPES = 0;
    private static final int IN_PIPES = 1;
    private static final int MAX_LOG_BYTES_LENGTH = 4000;
    private static final Class<?> TAG = TraceFormatter.class;

    TraceFormatter() {
    }

    static void logTrace(int i, int i2, String str, List<TraceEvent> list, SparseArray<TraceEvent> sparseArray) {
        if (BLog.isLoggable(i2)) {
            String formattedTrace = getFormattedTrace(i, list, sparseArray);
            if (TextUtils.isEmpty(formattedTrace)) {
                return;
            }
            if (formattedTrace.length() > MAX_LOG_BYTES_LENGTH) {
                String[] split = formattedTrace.split("\n");
                StringBuilder sb = new StringBuilder();
                appendThreadTraceTitle(sb, 0, null, null);
                int length = sb.length();
                int i3 = 0;
                int i4 = 0;
                while (i3 < split.length) {
                    String str2 = i3 != 0 ? split[i3 - 1] : null;
                    String str3 = split[i3];
                    if (length == 0 || str3.length() + length < MAX_LOG_BYTES_LENGTH) {
                        sb.append(str3);
                        sb.append("\n");
                        length += str3.length() + 1;
                        i3++;
                    } else {
                        BLog.log(i2, str, sb.toString());
                        sb.setLength(0);
                        i4++;
                        appendThreadTraceTitle(sb, i4, str2, str3);
                        length = sb.length();
                    }
                }
                if (length > 0) {
                    BLog.log(i2, str, sb.toString());
                    return;
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            appendThreadTraceTitle(sb2);
            sb2.append(formattedTrace);
            BLog.log(i2, str, sb2.toString());
        }
    }

    static String getFormattedTrace(int i, List<TraceEvent> list, SparseArray<TraceEvent> sparseArray) {
        boolean z;
        long j;
        List<TraceEvent> list2 = list;
        StringBuilder sb = new StringBuilder();
        long nanoTime = TracerClock.nanoTime();
        long j2 = -1;
        long j3 = -1;
        boolean z2 = false;
        int i2 = 0;
        int i3 = 0;
        while (i3 < list.size()) {
            TraceEvent traceEvent = list2.get(i3);
            if (traceEvent != null) {
                if (z2) {
                    z = z2;
                    j = j2;
                } else if (traceEvent.getId() == i) {
                    j = traceEvent.getEventTimeNanos();
                    z = true;
                }
                TraceEvent.Type eventType = traceEvent.getEventType();
                if (eventType == TraceEvent.Type.SPAWN) {
                    j2 = j;
                    z2 = z;
                } else {
                    if (eventType == TraceEvent.Type.STOP || eventType == TraceEvent.Type.STOP_ASYNC) {
                        if (i2 == 0) {
                            BLog.e(TAG, "Trace contains a stop event without a corresponding start: " + list2);
                        } else {
                            i2--;
                        }
                    }
                    int i4 = i2;
                    sb.append(" ");
                    traceEvent.appendTraceString(sb, j, j3, i4);
                    sb.append(" ");
                    j3 = traceEvent.getEventTimeNanos();
                    sb.append("\n");
                    if (eventType == TraceEvent.Type.START || eventType == TraceEvent.Type.START_ASYNC) {
                        i4++;
                    }
                    j2 = j;
                    z2 = z;
                    i2 = i4;
                }
            }
            i3++;
            list2 = list;
        }
        if (sparseArray.size() != 0) {
            sb.append(" Unstopped timers:\n");
            int size = sparseArray.size();
            for (int i5 = 0; i5 < size; i5++) {
                TraceEvent valueAt = sparseArray.valueAt(i5);
                long eventTimeNanos = valueAt.getEventTimeNanos();
                sb.append("  ");
                sb.append(valueAt);
                sb.append(" (");
                sb.append((nanoTime - eventTimeNanos) / 1000000);
                sb.append(" ms, started at ");
                sb.append(TraceEvent.formatTime(eventTimeNanos / 1000000));
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    private static void appendThreadTraceTitle(StringBuilder sb) {
        appendThreadTraceTitle(sb, 0, null, null);
    }

    private static void appendThreadTraceTitle(StringBuilder sb, int i, @Nullable String str, @Nullable String str2) {
        sb.append("Thread trace:(");
        sb.append(i);
        sb.append(")");
        if (i < 10) {
            sb.append("    ");
        } else if (i < 100) {
            sb.append("   ");
        } else if (i < 1000) {
            sb.append("  ");
        }
        sb.append("                 ");
        appendContiguousLineConnectors(sb, str, str2);
        sb.append("\n");
        sb.append(" .                   TOTAL   THREAD  ");
        appendContiguousLineConnectors(sb, str, str2);
        sb.append("\n");
    }

    static void appendContiguousLineConnectors(StringBuilder sb, @Nullable String str, @Nullable String str2) {
        if (str != null && str2 != null) {
            int max = Math.max(str.length(), str2.length());
            int i = 0;
            char c = 0;
            char c2 = 0;
            while (i < max) {
                char charAt = i < str.length() ? str.charAt(i) : 0;
                char charAt2 = i < str2.length() ? str2.charAt(i) : 0;
                if (c == 0 && charAt == '|') {
                    c = 1;
                } else if (c == 1 && !Character.isWhitespace(charAt) && charAt != '|') {
                    c = 2;
                }
                if (c2 == 0 && charAt2 == '|') {
                    c2 = 1;
                } else if (c2 == 1 && !Character.isWhitespace(charAt2) && charAt2 != '|') {
                    c2 = 2;
                }
                if (c != 2 || c2 != 2) {
                    if (c == 1) {
                        sb.append(charAt);
                    } else if (c2 == 1) {
                        sb.append(charAt2);
                    }
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
