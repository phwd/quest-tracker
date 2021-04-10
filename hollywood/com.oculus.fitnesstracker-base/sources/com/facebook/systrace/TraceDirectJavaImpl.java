package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import com.facebook.infer.annotation.Nullsafe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/* access modifiers changed from: package-private */
@SuppressLint({"BadMethodUse-android.util.Log.v", "BadMethodUse-android.util.Log.d", "BadMethodUse-android.util.Log.i", "BadMethodUse-android.util.Log.w", "BadMethodUse-android.util.Log.e"})
@Nullsafe(Nullsafe.Mode.LOCAL)
public final class TraceDirectJavaImpl {
    private static final String TAG = TraceDirect.class.getSimpleName();
    private static FileChannel sTraceChannel;
    private static FileOutputStream sTraceOutputStream;

    static {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/sys/kernel/debug/tracing/trace_marker");
            sTraceOutputStream = fileOutputStream;
            sTraceChannel = fileOutputStream.getChannel();
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
                if (bytes.length > 0) {
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
        traceRaw(SystraceBuilder.create('B').appendInt(Process.myPid()).appendString(str).toString());
    }

    public static void endSection() {
        traceRaw("E");
    }

    public static void traceMetadata(String str, String str2, int i) {
        traceRaw(SystraceBuilder.create('M').appendInt(Process.myPid()).appendString(str).appendInt(i).appendString(str2).toString());
    }

    public static void traceInstant(String str, String str2, char c) {
        SystraceBuilder appendString = SystraceBuilder.create('I').appendInt(Process.myPid()).appendString(str2);
        appendString.buf.append('|');
        appendString.buf.append(SystraceBuilder.sanitizeChar(c));
        traceRaw(appendString.appendString(str).toString());
    }

    /* access modifiers changed from: package-private */
    public static class SystraceBuilder {
        final StringBuilder buf = new StringBuilder(1024);

        static char sanitizeChar(char c) {
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

        public final SystraceBuilder appendString(String str) {
            this.buf.append('|');
            appendRawString(str);
            return this;
        }

        private SystraceBuilder appendRawString(String str) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                this.buf.append(sanitizeChar(str.charAt(i)));
            }
            return this;
        }

        public final SystraceBuilder appendInt(int i) {
            this.buf.append('|');
            this.buf.append(i);
            return this;
        }

        public final String toString() {
            return this.buf.toString();
        }
    }
}
