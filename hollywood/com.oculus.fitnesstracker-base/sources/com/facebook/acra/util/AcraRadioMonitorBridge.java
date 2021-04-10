package com.facebook.acra.util;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;

@Nullsafe(Nullsafe.Mode.LOCAL)
public final class AcraRadioMonitorBridge {
    private static volatile AcraRadioListener mListener;

    public static OutputStream createOutputDecorator(OutputStream outputStream) {
        return mListener == null ? outputStream : new OutputStreamDecorator(outputStream, mListener);
    }

    /* access modifiers changed from: package-private */
    public static class OutputStreamDecorator extends OutputStream {
        private final OutputStream mDecoratedStream;
        private final AcraRadioListener mListener;

        OutputStreamDecorator(OutputStream outputStream, AcraRadioListener acraRadioListener) {
            this.mDecoratedStream = outputStream;
            this.mListener = acraRadioListener;
        }

        @Override // java.io.OutputStream
        public final void write(int i) throws IOException {
            SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(i);
            } finally {
                SystemClock.elapsedRealtime();
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public final void flush() throws IOException {
            SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.flush();
            } finally {
                SystemClock.elapsedRealtime();
            }
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
            SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.close();
            } finally {
                SystemClock.elapsedRealtime();
            }
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr) throws IOException {
            SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(bArr);
            } finally {
                SystemClock.elapsedRealtime();
            }
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.write(bArr, i, i2);
            } finally {
                SystemClock.elapsedRealtime();
            }
        }
    }
}
