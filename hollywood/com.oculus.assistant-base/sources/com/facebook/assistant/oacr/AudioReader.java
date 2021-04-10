package com.facebook.assistant.oacr;

import X.AbstractRunnableC0124Bu;
import X.C0139Dd;
import android.os.Process;
import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class AudioReader {
    public static final String AUDIOREADER_THREAD = "AudioReader-Thread";
    public static final int MAX_FRAME_SIZE = 1024;
    public static final String TAG = "AudioReader";
    public final int mAudioBufferSize;
    public final AtomicLong mAudioDurationMsSentUp = new AtomicLong(0);
    public final AtomicLong mByteRead = new AtomicLong(0);
    public final Callback mCallback;
    public final AtomicBoolean mClosed = new AtomicBoolean(false);
    public final InputStream mInputStream;
    public final WritableByteChannel mOacrStreamChannel;
    public final Runnable mRunnable;
    public final int mSamplingRate;
    public long mStartTime;
    public volatile boolean mStop = false;
    public final Thread mThread;

    public interface Callback {
        void onComplete();

        void onError(Exception exc);
    }

    public void stop() {
        this.mStop = true;
    }

    public void join() {
        this.mThread.join();
    }

    public void start() {
        this.mThread.start();
        C0139Dd.A09(TAG, "start AudioReader thread");
        this.mStartTime = SystemClock.elapsedRealtime();
    }

    public AudioReader(InputStream inputStream, WritableByteChannel writableByteChannel, int i, int i2, Callback callback) {
        AnonymousClass1 r2 = new AbstractRunnableC0124Bu(AudioReader.class, "ReaderThread") {
            /* class com.facebook.assistant.oacr.AudioReader.AnonymousClass1 */

            public void run() {
                AudioReader audioReader;
                int read;
                Process.setThreadPriority(-16);
                C0139Dd.A09(AudioReader.TAG, "running audio reader");
                ByteBuffer allocateDirect = ByteBuffer.allocateDirect(AudioReader.this.mAudioBufferSize);
                try {
                    byte[] bArr = new byte[AudioReader.this.mAudioBufferSize];
                    while (!AudioReader.this.mStop && (read = AudioReader.this.mInputStream.read(bArr)) >= 0) {
                        AudioReader.this.mByteRead.addAndGet((long) read);
                        AudioReader audioReader2 = AudioReader.this;
                        audioReader2.mAudioDurationMsSentUp.addAndGet((long) (((read * 1000) >> 1) / audioReader2.mSamplingRate));
                        allocateDirect.clear();
                        allocateDirect.put(bArr, 0, read);
                        allocateDirect.flip();
                        AudioReader.this.mOacrStreamChannel.write(allocateDirect);
                    }
                    AudioReader.this.mOacrStreamChannel.close();
                    AudioReader.this.mClosed.set(true);
                    AudioReader.this.mCallback.onComplete();
                    if (!AudioReader.this.mClosed.get()) {
                        try {
                            audioReader = AudioReader.this;
                            audioReader.mOacrStreamChannel.close();
                        } catch (IOException unused) {
                        }
                    }
                } catch (IOException e) {
                    C0139Dd.A0S(AudioReader.TAG, e, "exception during audio read");
                    AudioReader.this.mCallback.onError(e);
                    if (!AudioReader.this.mClosed.get()) {
                        audioReader = AudioReader.this;
                    }
                } catch (Throwable th) {
                    if (!AudioReader.this.mClosed.get()) {
                        try {
                            AudioReader.this.mOacrStreamChannel.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            }
        };
        this.mRunnable = r2;
        this.mInputStream = inputStream;
        this.mOacrStreamChannel = writableByteChannel;
        this.mSamplingRate = i;
        this.mCallback = callback;
        this.mThread = new Thread(r2, AUDIOREADER_THREAD);
        this.mAudioBufferSize = i2 == 0 ? 1024 : i2;
    }

    public static /* synthetic */ String access$000() {
        return TAG;
    }

    public AtomicLong getAudioDurationMsSentUp() {
        return this.mAudioDurationMsSentUp;
    }

    public final AtomicLong getBytesRead() {
        return this.mByteRead;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public boolean isStopped() {
        return this.mStop;
    }
}
