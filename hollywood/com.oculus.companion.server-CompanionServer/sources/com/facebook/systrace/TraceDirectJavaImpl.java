package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/* access modifiers changed from: package-private */
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
public class TraceDirectJavaImpl {
    private static final String TAG = TraceDirect.class.getSimpleName();
    private static FileChannel sTraceChannel;
    private static FileOutputStream sTraceOutputStream;

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
                byte[] bytes = str.getBytes(Charset.forName("UTF-8"));
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
        SystraceBuilder create = SystraceBuilder.create('B');
        create.appendProcessId();
        create.appendString(str);
        traceRaw(create.toString());
    }

    public static void endSection() {
        traceRaw("E");
    }

    public static void traceMetadata(String str, String str2, int i) {
        SystraceBuilder create = SystraceBuilder.create('M');
        create.appendProcessId();
        create.appendString(str);
        create.appendInt(i);
        create.appendString(str2);
        traceRaw(create.toString());
    }

    /* access modifiers changed from: private */
    public static class SystraceBuilder {
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

        public SystraceBuilder appendString(String str) {
            this.buf.append('|');
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
            this.buf.append('|');
            this.buf.append(i);
            return this;
        }

        public SystraceBuilder appendProcessId() {
            appendInt(Process.myPid());
            return this;
        }

        public String toString() {
            return this.buf.toString();
        }
    }
}
