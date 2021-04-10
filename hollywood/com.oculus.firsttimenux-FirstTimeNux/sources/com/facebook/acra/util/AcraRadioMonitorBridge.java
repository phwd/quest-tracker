package com.facebook.acra.util;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AcraRadioMonitorBridge {
    @Nullable
    private static volatile AcraRadioListener mListener;

    public static void setRadioListener(AcraRadioListener listener) {
        mListener = listener;
    }

    public static OutputStream createOutputDecorator(OutputStream outputStream) {
        return mListener == null ? outputStream : new OutputStreamDecorator(outputStream, mListener);
    }

    /* access modifiers changed from: private */
    public static class OutputStreamDecorator extends OutputStream {
        private final OutputStream mDecoratedStream;
        private final AcraRadioListener mListener;

        OutputStreamDecorator(OutputStream outputStream, AcraRadioListener listener) {
            this.mDecoratedStream = outputStream;
            this.mListener = listener;
        }

        @Override // java.io.OutputStream
        public void write(int oneByte) throws IOException {
            long startTime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(oneByte);
            } finally {
                this.mListener.onRadioActive(startTime, SystemClock.elapsedRealtime(), 1);
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            long startTime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.flush();
            } finally {
                this.mListener.onRadioActive(startTime, SystemClock.elapsedRealtime(), 0);
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long startTime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.close();
            } finally {
                this.mListener.onRadioActive(startTime, SystemClock.elapsedRealtime(), 0);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] buffer) throws IOException {
            long startTime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(buffer);
            } finally {
                this.mListener.onRadioActive(startTime, SystemClock.elapsedRealtime(), buffer.length);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] buffer, int offset, int count) throws IOException {
            long startTime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(buffer, offset, count);
            } finally {
                this.mListener.onRadioActive(startTime, SystemClock.elapsedRealtime(), count);
            }
        }
    }
}
