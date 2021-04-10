package com.facebook.systrace;

import android.annotation.SuppressLint;
import android.os.Process;
import android.util.Log;
import com.oculus.os.Version;
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
public class TraceDirectJavaImpl {
    private static final String TAG = TraceDirect.class.getSimpleName();
    @Nullable
    private static FileChannel sTraceChannel;
    @Nullable
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

    public static void endSection() {
        traceRaw("E");
    }

    public static void traceMetadata(String name, String value, int tid) {
        traceRaw(SystraceBuilder.create('M').appendProcessId().appendString(name).appendInt(tid).appendString(value).toString());
    }

    public static void traceInstant(String category, String title, char scope) {
        traceRaw(SystraceBuilder.create('I').appendProcessId().appendString(title).appendChar(scope).appendString(category).toString());
    }

    /* access modifiers changed from: private */
    public static class SystraceBuilder {
        private final StringBuilder buf = new StringBuilder(1024);

        public static SystraceBuilder create(char phase) {
            return new SystraceBuilder(phase);
        }

        private SystraceBuilder(char phase) {
            this.buf.append(phase);
        }

        public SystraceBuilder appendString(String arg) {
            this.buf.append('|');
            appendRawString(arg);
            return this;
        }

        private static char sanitizeChar(char c) {
            switch (c) {
                case 0:
                case '\t':
                case '\n':
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                case Version.VERSION_59 /*{ENCODED_INT: 59}*/:
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
            this.buf.append('|');
            this.buf.append(arg);
            return this;
        }

        public SystraceBuilder appendChar(char arg) {
            this.buf.append('|');
            this.buf.append(sanitizeChar(arg));
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
