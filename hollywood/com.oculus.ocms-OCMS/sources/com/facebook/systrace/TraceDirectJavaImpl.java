package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.stetho.common.Utf8Charset;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TraceDirectJavaImpl {
    private static final String TAG = TraceDirect.class.getSimpleName();
    @Nullable
    private static FileChannel sTraceChannel;
    @Nullable
    private static FileOutputStream sTraceOutputStream;

    TraceDirectJavaImpl() {
    }

    static {
        try {
            sTraceOutputStream = new FileOutputStream("/sys/kernel/debug/tracing/trace_marker");
            sTraceChannel = sTraceOutputStream.getChannel();
        } catch (FileNotFoundException e) {
            Log.e(TAG, "Failed to open trace_marker file.", e);
            sTraceOutputStream = null;
            sTraceChannel = null;
        }
    }

    private static void traceRaw(String str) {
        int write;
        if (sTraceChannel != null) {
            try {
                byte[] bytes = str.getBytes(Charset.forName(Utf8Charset.NAME));
                if (bytes.length >= 1) {
                    do {
                        write = sTraceChannel.write(ByteBuffer.wrap(bytes));
                    } while (write == 0);
                    if (write != bytes.length) {
                        Log.e(TAG, "Partial write of systrace line.");
                    }
                }
            } catch (UnsupportedEncodingException e) {
                Log.e(TAG, "Failed to encode raw systrace line to UTF-8.", e);
            } catch (IOException e2) {
                Log.e(TAG, "Failed to write systrace line.", e2);
            }
        }
    }

    public static void beginSection(String str) {
        traceRaw(SystraceBuilder.create('B').appendProcessId().appendString(str).toString());
    }

    public static void beginSectionWithArgs(String str, String[] strArr, int i) {
        traceRaw(SystraceBuilder.create('B').appendProcessId().appendString(str).appendArgs(strArr, i).toString());
    }

    public static void endSection() {
        traceRaw("E");
    }

    public static void endSectionWithArgs(String[] strArr, int i) {
        traceRaw(SystraceBuilder.create('E').appendSeparator().appendSeparator().appendArgs(strArr, i).toString());
    }

    public static void traceCounter(String str, int i) {
        traceRaw(SystraceBuilder.create('C').appendProcessId().appendString(str).appendInt(i).toString());
    }

    public static void asyncTraceBegin(String str, int i, long j) {
        SystraceBuilder appendString = SystraceBuilder.create('S').appendProcessId().appendString(str);
        appendString.appendRawString("<0>");
        if (j != 0) {
            appendString.appendRawString("<T");
            appendString.appendRawString(Long.toString(j));
            appendString.appendRawString(">");
        }
        appendString.appendInt(i);
        traceRaw(appendString.toString());
    }

    public static void asyncTraceStageBegin(String str, int i, long j, String str2) {
        SystraceBuilder appendString = SystraceBuilder.create('T').appendProcessId().appendString(str);
        if (j != 0) {
            appendString.appendRawString("<T");
            appendString.appendRawString(Long.toString(j));
            appendString.appendRawString(">");
        }
        appendString.appendInt(i);
        appendString.appendString(str2);
        traceRaw(appendString.toString());
    }

    public static void asyncTraceEnd(String str, int i, long j) {
        SystraceBuilder appendString = SystraceBuilder.create('F').appendProcessId().appendString(str);
        if (j != 0) {
            appendString.appendRawString("<T");
            appendString.appendRawString(Long.toString(j));
            appendString.appendRawString(">");
        }
        appendString.appendInt(i);
        traceRaw(appendString.toString());
    }

    public static void asyncTraceCancel(String str, int i) {
        traceRaw(SystraceBuilder.create('F').appendProcessId().appendString(str).appendRawString("<X>").appendInt(i).toString());
    }

    public static void asyncTraceRename(String str, String str2, int i) {
        traceRaw(SystraceBuilder.create('F').appendProcessId().appendString(str).appendRawString("<M>").appendInt(i).appendString(str2).toString());
    }

    public static void startAsyncFlow(String str, int i) {
        traceRaw(SystraceBuilder.create('s').appendProcessId().appendString(str).appendInt(i).toString());
    }

    public static void stepAsyncFlow(String str, int i) {
        traceRaw(SystraceBuilder.create('t').appendProcessId().appendString(str).appendInt(i).toString());
    }

    public static void endAsyncFlow(String str, int i) {
        traceRaw(SystraceBuilder.create('f').appendProcessId().appendString(str).appendInt(i).toString());
    }

    public static void traceMetadata(String str, String str2, int i) {
        traceRaw(SystraceBuilder.create('M').appendProcessId().appendString(str).appendInt(i).appendString(str2).toString());
    }

    public static void traceInstant(String str, String str2, char c) {
        traceRaw(SystraceBuilder.create('I').appendProcessId().appendString(str2).appendChar(c).appendString(str).toString());
    }

    /* access modifiers changed from: private */
    public static class SystraceBuilder {
        private static final char ARG_SEPARATOR = '|';
        private static final int FBSYSTRACE_MAX_MESSAGE_LENGTH = 1024;
        private final StringBuilder buf = new StringBuilder(1024);

        private static char sanitizeChar(char c) {
            if (c == 0 || c == '\r' || c == ';' || c == '|' || c == '\t' || c == '\n') {
                return ' ';
            }
            return c;
        }

        public static SystraceBuilder create(char c) {
            return new SystraceBuilder(c);
        }

        private SystraceBuilder(char c) {
            this.buf.append(c);
        }

        public SystraceBuilder appendSeparator() {
            this.buf.append(ARG_SEPARATOR);
            return this;
        }

        public SystraceBuilder appendString(String str) {
            this.buf.append(ARG_SEPARATOR);
            appendRawString(str);
            return this;
        }

        public SystraceBuilder appendRawString(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                this.buf.append(sanitizeChar(str.charAt(i)));
            }
            return this;
        }

        public SystraceBuilder appendInt(int i) {
            this.buf.append(ARG_SEPARATOR);
            this.buf.append(i);
            return this;
        }

        public SystraceBuilder appendChar(char c) {
            this.buf.append(ARG_SEPARATOR);
            this.buf.append(sanitizeChar(c));
            return this;
        }

        public SystraceBuilder appendArgs(String[] strArr, int i) {
            this.buf.append(ARG_SEPARATOR);
            for (int i2 = 1; i2 < i; i2 += 2) {
                String str = strArr[i2 - 1];
                String str2 = strArr[i2];
                this.buf.append(str);
                this.buf.append('=');
                this.buf.append(str2);
                if (i2 < i - 1) {
                    this.buf.append(';');
                }
            }
            return this;
        }

        public SystraceBuilder appendProcessId() {
            return appendInt(Process.myPid());
        }

        public String toString() {
            return this.buf.toString();
        }
    }
}
