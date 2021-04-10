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

    static void logTrace(int startTraceId, int logLevel, String tag, List<TraceEvent> events, SparseArray<TraceEvent> outstandingEvents) {
        if (BLog.isLoggable(logLevel)) {
            String trace = getFormattedTrace(startTraceId, events, outstandingEvents);
            if (TextUtils.isEmpty(trace)) {
                return;
            }
            if (trace.length() > MAX_LOG_BYTES_LENGTH) {
                String[] lines = trace.split("\n");
                int index = 0;
                int traceIndex = 0;
                StringBuilder sb = new StringBuilder();
                appendThreadTraceTitle(sb, 0, null, null);
                int byteCount = sb.length();
                while (index < lines.length) {
                    String previousLine = index != 0 ? lines[index - 1] : null;
                    String line = lines[index];
                    if (byteCount == 0 || line.length() + byteCount < MAX_LOG_BYTES_LENGTH) {
                        sb.append(line);
                        sb.append("\n");
                        byteCount += line.length() + 1;
                        index++;
                    } else {
                        BLog.log(logLevel, tag, sb.toString());
                        sb.setLength(0);
                        traceIndex++;
                        appendThreadTraceTitle(sb, traceIndex, previousLine, line);
                        byteCount = sb.length();
                    }
                }
                if (byteCount > 0) {
                    BLog.log(logLevel, tag, sb.toString());
                    return;
                }
                return;
            }
            StringBuilder sb2 = new StringBuilder();
            appendThreadTraceTitle(sb2);
            sb2.append(trace);
            BLog.log(logLevel, tag, sb2.toString());
        }
    }

    static String getFormattedTrace(int startTraceId, List<TraceEvent> events, SparseArray<TraceEvent> outstandingEvents) {
        StringBuilder sb = new StringBuilder();
        long etime = -1;
        long nowNanos = TracerClock.nanoTime();
        int indents = 0;
        boolean foundFirstTraceId = false;
        long startTime = -1;
        for (int i = 0; i < events.size(); i++) {
            TraceEvent e = events.get(i);
            if (e != null) {
                if (!foundFirstTraceId) {
                    if (e.getId() == startTraceId) {
                        foundFirstTraceId = true;
                        startTime = e.getEventTimeNanos();
                    }
                }
                TraceEvent.Type eventType = e.getEventType();
                if (eventType != TraceEvent.Type.SPAWN) {
                    if (eventType == TraceEvent.Type.STOP || eventType == TraceEvent.Type.STOP_ASYNC) {
                        if (indents == 0) {
                            BLog.e(TAG, "Trace contains a stop event without a corresponding start: " + events);
                        } else {
                            indents--;
                        }
                    }
                    sb.append(" ");
                    e.appendTraceString(sb, startTime, etime, indents);
                    sb.append(" ");
                    etime = e.getEventTimeNanos();
                    sb.append("\n");
                    if (eventType == TraceEvent.Type.START || eventType == TraceEvent.Type.START_ASYNC) {
                        indents++;
                    }
                }
            }
        }
        if (outstandingEvents.size() != 0) {
            sb.append(" Unstopped timers:\n");
            int size = outstandingEvents.size();
            for (int i2 = 0; i2 < size; i2++) {
                TraceEvent startEvent = outstandingEvents.valueAt(i2);
                long eventTimeNanos = startEvent.getEventTimeNanos();
                sb.append("  ");
                sb.append(startEvent);
                sb.append(" (");
                sb.append((nowNanos - eventTimeNanos) / 1000000);
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

    private static void appendThreadTraceTitle(StringBuilder sb, int index, @Nullable String prevLine, @Nullable String nextLine) {
        sb.append("Thread trace:(").append(index).append(")");
        if (index < 10) {
            sb.append("    ");
        } else if (index < 100) {
            sb.append("   ");
        } else if (index < 1000) {
            sb.append("  ");
        }
        sb.append("                 ");
        appendContiguousLineConnectors(sb, prevLine, nextLine);
        sb.append("\n");
        sb.append(" .                   TOTAL   THREAD  ");
        appendContiguousLineConnectors(sb, prevLine, nextLine);
        sb.append("\n");
    }

    static void appendContiguousLineConnectors(StringBuilder sb, @Nullable String prevLine, @Nullable String nextLine) {
        if (!(prevLine == null || nextLine == null)) {
            int inConnectorsPrev = 0;
            int inConnectorsNext = 0;
            int maxLength = Math.max(prevLine.length(), nextLine.length());
            for (int i = 0; i < maxLength; i++) {
                char cPrev = 0;
                char cNext = 0;
                if (i < prevLine.length()) {
                    cPrev = prevLine.charAt(i);
                }
                if (i < nextLine.length()) {
                    cNext = nextLine.charAt(i);
                }
                if (inConnectorsPrev == 0 && cPrev == '|') {
                    inConnectorsPrev = 1;
                } else if (inConnectorsPrev == 1 && !Character.isWhitespace(cPrev) && cPrev != '|') {
                    inConnectorsPrev = 2;
                }
                if (inConnectorsNext == 0 && cNext == '|') {
                    inConnectorsNext = 1;
                } else if (inConnectorsNext == 1 && !Character.isWhitespace(cNext) && cNext != '|') {
                    inConnectorsNext = 2;
                }
                if (inConnectorsPrev != 2 || inConnectorsNext != 2) {
                    if (inConnectorsPrev == 1) {
                        sb.append(cPrev);
                    } else if (inConnectorsNext == 1) {
                        sb.append(cNext);
                    }
                } else {
                    return;
                }
            }
        }
    }
}
