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

    public static void setRadioListener(AcraRadioListener acraRadioListener) {
        mListener = acraRadioListener;
    }

    public static OutputStream createOutputDecorator(OutputStream outputStream) {
        return mListener == null ? outputStream : new OutputStreamDecorator(outputStream, mListener);
    }

    /* access modifiers changed from: private */
    public static class OutputStreamDecorator extends OutputStream {
        private final OutputStream mDecoratedStream;
        private final AcraRadioListener mListener;

        OutputStreamDecorator(OutputStream outputStream, AcraRadioListener acraRadioListener) {
            this.mDecoratedStream = outputStream;
            this.mListener = acraRadioListener;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(i);
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), 1);
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.flush();
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), 0);
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.close();
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), 0);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(bArr);
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), bArr.length);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(bArr, i, i2);
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), i2);
            }
        }
    }
}
