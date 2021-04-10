package com.facebook.acra.util;

import android.os.SystemClock;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AcraRadioMonitorBridge {
    @Nullable
    public static volatile AcraRadioListener mListener;

    public static class OutputStreamDecorator extends OutputStream {
        public final OutputStream mDecoratedStream;
        public final AcraRadioListener mListener;

        public OutputStreamDecorator(OutputStream outputStream, AcraRadioListener acraRadioListener) {
            this.mDecoratedStream = outputStream;
            this.mListener = acraRadioListener;
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

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            try {
                this.mDecoratedStream.flush();
            } finally {
                this.mListener.onRadioActive(elapsedRealtime, SystemClock.elapsedRealtime(), 0);
            }
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

    public static OutputStream createOutputDecorator(OutputStream outputStream) {
        if (mListener != null) {
            return new OutputStreamDecorator(outputStream, mListener);
        }
        return outputStream;
    }

    public static void setRadioListener(AcraRadioListener acraRadioListener) {
        mListener = acraRadioListener;
    }
}
