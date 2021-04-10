package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import com.facebook.common.exceptionhandler.ExceptionHandlerManager;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.zstd.AbstractZstdOutputStream;
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

    private static void traceRaw(String line) {
        int count;
        if (sTraceChannel != null) {
            try {
                byte[] lineBytes = line.getBytes(Charset.forName("UTF-8"));
                if (lineBytes.length >= 1) {
                    do {
                        count = sTraceChannel.write(ByteBuffer.wrap(lineBytes));
                    } while (count == 0);
                    if (count != lineBytes.length) {
                        Log.e(TAG, "Partial write of systrace line.");
                    }
                }
            } catch (UnsupportedEncodingException ex) {
                Log.e(TAG, "Failed to encode raw systrace line to UTF-8.", ex);
            } catch (IOException e) {
                Log.e(TAG, "Failed to write systrace line.", e);
            }
        }
    }

    public static void beginSection(String sectionName) {
        traceRaw(SystraceBuilder.create('B').appendProcessId().appendString(sectionName).toString());
    }

    public static void beginSectionWithArgs(String sectionName, String[] args, int argsLength) {
        traceRaw(SystraceBuilder.create('B').appendProcessId().appendString(sectionName).appendArgs(args, argsLength).toString());
    }

    public static void endSection() {
        traceRaw("E");
    }

    public static void endSectionWithArgs(String[] args, int argsLength) {
        traceRaw(SystraceBuilder.create('E').appendSeparator().appendSeparator().appendArgs(args, argsLength).toString());
    }

    public static void traceCounter(String counterName, int counterValue) {
        traceRaw(SystraceBuilder.create('C').appendProcessId().appendString(counterName).appendInt(counterValue).toString());
    }

    public static void asyncTraceBegin(String sectionName, int cookie, long deltaNanos) {
        SystraceBuilder builder = SystraceBuilder.create('S').appendProcessId().appendString(sectionName);
        builder.appendRawString("<0>");
        if (deltaNanos != 0) {
            builder.appendRawString("<T");
            builder.appendRawString(Long.toString(deltaNanos));
            builder.appendRawString(">");
        }
        builder.appendInt(cookie);
        traceRaw(builder.toString());
    }

    public static void asyncTraceStageBegin(String sectionName, int cookie, long deltaNanos, String stageName) {
        SystraceBuilder builder = SystraceBuilder.create('T').appendProcessId().appendString(sectionName);
        if (deltaNanos != 0) {
            builder.appendRawString("<T");
            builder.appendRawString(Long.toString(deltaNanos));
            builder.appendRawString(">");
        }
        builder.appendInt(cookie);
        builder.appendString(stageName);
        traceRaw(builder.toString());
    }

    public static void asyncTraceEnd(String sectionName, int cookie, long deltaNanos) {
        SystraceBuilder builder = SystraceBuilder.create('F').appendProcessId().appendString(sectionName);
        if (deltaNanos != 0) {
            builder.appendRawString("<T");
            builder.appendRawString(Long.toString(deltaNanos));
            builder.appendRawString(">");
        }
        builder.appendInt(cookie);
        traceRaw(builder.toString());
    }

    public static void asyncTraceCancel(String sectionName, int cookie) {
        traceRaw(SystraceBuilder.create('F').appendProcessId().appendString(sectionName).appendRawString("<X>").appendInt(cookie).toString());
    }

    public static void asyncTraceRename(String oldSectionName, String newSectionName, int cookie) {
        traceRaw(SystraceBuilder.create('F').appendProcessId().appendString(oldSectionName).appendRawString("<M>").appendInt(cookie).appendString(newSectionName).toString());
    }

    public static void startAsyncFlow(String sectionName, int cookie) {
        traceRaw(SystraceBuilder.create('s').appendProcessId().appendString(sectionName).appendInt(cookie).toString());
    }

    public static void stepAsyncFlow(String sectionName, int cookie) {
        traceRaw(SystraceBuilder.create('t').appendProcessId().appendString(sectionName).appendInt(cookie).toString());
    }

    public static void endAsyncFlow(String sectionName, int cookie) {
        traceRaw(SystraceBuilder.create('f').appendProcessId().appendString(sectionName).appendInt(cookie).toString());
    }

    public static void traceMetadata(String name, String value, int tid) {
        traceRaw(SystraceBuilder.create('M').appendProcessId().appendString(name).appendInt(tid).appendString(value).toString());
    }

    public static void traceInstant(String category, String title, char scope) {
        traceRaw(SystraceBuilder.create('I').appendProcessId().appendString(title).appendChar(scope).appendString(category).toString());
    }

    /* access modifiers changed from: private */
    public static class SystraceBuilder {
        private static final char ARG_SEPARATOR = '|';
        private static final int FBSYSTRACE_MAX_MESSAGE_LENGTH = 1024;
        private final StringBuilder buf = new StringBuilder(1024);

        public static SystraceBuilder create(char phase) {
            return new SystraceBuilder(phase);
        }

        private SystraceBuilder(char phase) {
            this.buf.append(phase);
        }

        public SystraceBuilder appendSeparator() {
            this.buf.append(ARG_SEPARATOR);
            return this;
        }

        public SystraceBuilder appendString(String arg) {
            this.buf.append(ARG_SEPARATOR);
            appendRawString(arg);
            return this;
        }

        private static char sanitizeChar(char c) {
            switch (c) {
                case 0:
                case '\t':
                case ExceptionHandlerManager.PRIORITY_EARLY:
                case AbstractZstdOutputStream.DEFAULT_COMPRESSION_LEVEL /*{ENCODED_INT: 13}*/:
                case ';':
                case '|':
                    return ' ';
                default:
                    return c;
            }
        }

        public SystraceBuilder appendRawString(String arg) {
            int argLength = arg.length();
            for (int ii = 0; ii < argLength; ii++) {
                this.buf.append(sanitizeChar(arg.charAt(ii)));
            }
            return this;
        }

        public SystraceBuilder appendInt(int arg) {
            this.buf.append(ARG_SEPARATOR);
            this.buf.append(arg);
            return this;
        }

        public SystraceBuilder appendChar(char arg) {
            this.buf.append(ARG_SEPARATOR);
            this.buf.append(sanitizeChar(arg));
            return this;
        }

        public SystraceBuilder appendArgs(String[] args, int argsLength) {
            this.buf.append(ARG_SEPARATOR);
            for (int ii = 1; ii < argsLength; ii += 2) {
                String key = args[ii - 1];
                String value = args[ii];
                this.buf.append(key);
                this.buf.append('=');
                this.buf.append(value);
                if (ii < argsLength - 1) {
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
