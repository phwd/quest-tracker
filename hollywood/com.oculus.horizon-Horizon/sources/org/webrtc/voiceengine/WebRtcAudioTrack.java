package org.webrtc.voiceengine;

import X.AnonymousClass006;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioTrack;
import java.nio.ByteBuffer;
import org.webrtc.Logging;

public class WebRtcAudioTrack {
    public static final int BITS_PER_SAMPLE = 16;
    public static final int BUFFERS_PER_SECOND = 100;
    public static final int CALLBACK_BUFFER_SIZE_MS = 10;
    public static final boolean DEBUG = false;
    public static final int STREAM_TYPE = 3;
    public static final String TAG = "WebRtcAudioTrack";
    public final AudioManager audioManager;
    public AudioTrackThread audioThread = null;
    public AudioTrack audioTrack = null;
    public ByteBuffer byteBuffer;
    public final Context context;
    public final long nativeAudioTrack;

    public class AudioTrackThread extends Thread {
        public volatile boolean keepAlive = true;

        @TargetApi(21)
        private int writeOnLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer, i, 0);
        }

        public void joinThread() {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }

        public AudioTrackThread(String str) {
            super(str);
        }

        public void run() {
            Logging.d(WebRtcAudioTrack.TAG, AnonymousClass006.A05("AudioTrackThread", WebRtcAudioUtils.getThreadInfo()));
            try {
                WebRtcAudioTrack.this.audioTrack.play();
                boolean z = true;
                boolean z2 = false;
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() == 3) {
                    z2 = true;
                }
                WebRtcAudioTrack.assertTrue(z2);
                int capacity = WebRtcAudioTrack.this.byteBuffer.capacity();
                while (this.keepAlive) {
                    WebRtcAudioTrack webRtcAudioTrack = WebRtcAudioTrack.this;
                    webRtcAudioTrack.nativeGetPlayoutData(capacity, webRtcAudioTrack.nativeAudioTrack);
                    boolean z3 = false;
                    if (capacity <= WebRtcAudioTrack.this.byteBuffer.remaining()) {
                        z3 = true;
                    }
                    WebRtcAudioTrack.assertTrue(z3);
                    WebRtcAudioTrack webRtcAudioTrack2 = WebRtcAudioTrack.this;
                    int writeOnLollipop = writeOnLollipop(webRtcAudioTrack2.audioTrack, webRtcAudioTrack2.byteBuffer, capacity);
                    if (writeOnLollipop != capacity) {
                        Logging.e(WebRtcAudioTrack.TAG, AnonymousClass006.A01("AudioTrack.write failed: ", writeOnLollipop));
                        if (writeOnLollipop == -3) {
                            this.keepAlive = false;
                        }
                    }
                    WebRtcAudioTrack.this.byteBuffer.rewind();
                }
                try {
                    WebRtcAudioTrack.this.audioTrack.stop();
                } catch (IllegalStateException e) {
                    Logging.e(WebRtcAudioTrack.TAG, AnonymousClass006.A05("AudioTrack.stop failed: ", e.getMessage()));
                }
                if (WebRtcAudioTrack.this.audioTrack.getPlayState() != 1) {
                    z = false;
                }
                WebRtcAudioTrack.assertTrue(z);
                WebRtcAudioTrack.this.audioTrack.flush();
            } catch (IllegalStateException e2) {
                Logging.e(WebRtcAudioTrack.TAG, AnonymousClass006.A05("AudioTrack.play failed: ", e2.getMessage()));
            }
        }

        private int writePreLollipop(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
            return audioTrack.write(byteBuffer.array(), byteBuffer.arrayOffset(), i);
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer2, long j);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeGetPlayoutData(int i, long j);

    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private int getStreamMaxVolume() {
        Logging.d(TAG, "getStreamMaxVolume");
        boolean z = false;
        if (this.audioManager != null) {
            z = true;
        }
        assertTrue(z);
        return this.audioManager.getStreamMaxVolume(3);
    }

    private int getStreamVolume() {
        Logging.d(TAG, "getStreamVolume");
        boolean z = false;
        if (this.audioManager != null) {
            z = true;
        }
        assertTrue(z);
        return this.audioManager.getStreamVolume(3);
    }

    private void initPlayout(int i, int i2) {
        StringBuilder sb = new StringBuilder("initPlayout(sampleRate=");
        sb.append(i);
        sb.append(", channels=");
        sb.append(i2);
        sb.append(")");
        Logging.d(TAG, sb.toString());
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect((i2 << 1) * (i / 100));
        this.byteBuffer = allocateDirect;
        Logging.d(TAG, AnonymousClass006.A01("byteBuffer.capacity: ", allocateDirect.capacity()));
        nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioTrack);
        int minBufferSize = AudioTrack.getMinBufferSize(i, 4, 2);
        Logging.d(TAG, AnonymousClass006.A01("AudioTrack.getMinBufferSize: ", minBufferSize));
        boolean z = false;
        boolean z2 = false;
        if (this.audioTrack == null) {
            z2 = true;
        }
        assertTrue(z2);
        boolean z3 = false;
        if (this.byteBuffer.capacity() < minBufferSize) {
            z3 = true;
        }
        assertTrue(z3);
        try {
            AudioTrack audioTrack2 = new AudioTrack(3, i, 4, 2, minBufferSize, 1);
            this.audioTrack = audioTrack2;
            boolean z4 = false;
            if (audioTrack2.getState() == 1) {
                z4 = true;
            }
            assertTrue(z4);
            boolean z5 = false;
            if (this.audioTrack.getPlayState() == 1) {
                z5 = true;
            }
            assertTrue(z5);
            if (this.audioTrack.getStreamType() == 3) {
                z = true;
            }
            assertTrue(z);
            Logging.d(TAG, AnonymousClass006.A01("AudioTrack stream type: ", this.audioTrack.getStreamType()));
        } catch (IllegalArgumentException e) {
            Logging.d(TAG, e.getMessage());
        }
    }

    @TargetApi(21)
    private boolean isVolumeFixed() {
        return this.audioManager.isVolumeFixed();
    }

    private boolean setStreamVolume(int i) {
        Logging.d(TAG, AnonymousClass006.A02("setStreamVolume(", i, ")"));
        boolean z = false;
        if (this.audioManager != null) {
            z = true;
        }
        assertTrue(z);
        if (this.audioManager.isVolumeFixed()) {
            Logging.e(TAG, "The device implements a fixed volume policy.");
            return false;
        }
        this.audioManager.setStreamVolume(3, i, 0);
        return true;
    }

    private boolean startPlayout() {
        Logging.d(TAG, "startPlayout");
        boolean z = false;
        boolean z2 = false;
        if (this.audioTrack != null) {
            z2 = true;
        }
        assertTrue(z2);
        if (this.audioThread == null) {
            z = true;
        }
        assertTrue(z);
        AudioTrackThread audioTrackThread = new AudioTrackThread("AudioTrackJavaThread");
        this.audioThread = audioTrackThread;
        audioTrackThread.start();
        return true;
    }

    private boolean stopPlayout() {
        Logging.d(TAG, "stopPlayout");
        boolean z = false;
        if (this.audioThread != null) {
            z = true;
        }
        assertTrue(z);
        this.audioThread.joinThread();
        this.audioThread = null;
        AudioTrack audioTrack2 = this.audioTrack;
        if (audioTrack2 != null) {
            audioTrack2.release();
            this.audioTrack = null;
        }
        return true;
    }

    public WebRtcAudioTrack(Context context2, long j) {
        Logging.d(TAG, AnonymousClass006.A05("ctor", WebRtcAudioUtils.getThreadInfo()));
        this.context = context2;
        this.nativeAudioTrack = j;
        this.audioManager = (AudioManager) context2.getSystemService("audio");
    }
}
