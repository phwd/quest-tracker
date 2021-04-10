package org.webrtc.voiceengine;

import X.AnonymousClass006;
import android.content.Context;
import android.media.AudioRecord;
import android.os.Process;
import java.nio.ByteBuffer;
import org.webrtc.Logging;

public class WebRtcAudioRecord {
    public static final int BITS_PER_SAMPLE = 16;
    public static final int BUFFERS_PER_SECOND = 100;
    public static final int BUFFER_SIZE_FACTOR = 2;
    public static final int CALLBACK_BUFFER_SIZE_MS = 10;
    public static final boolean DEBUG = false;
    public static final String TAG = "WebRtcAudioRecord";
    public AudioRecord audioRecord = null;
    public AudioRecordThread audioThread = null;
    public ByteBuffer byteBuffer;
    public final Context context;
    public WebRtcAudioEffects effects = null;
    public final long nativeAudioRecord;

    public class AudioRecordThread extends Thread {
        public volatile boolean keepAlive = true;

        public void joinThread() {
            this.keepAlive = false;
            while (isAlive()) {
                try {
                    join();
                } catch (InterruptedException unused) {
                }
            }
        }

        public AudioRecordThread(String str) {
            super(str);
        }

        public void run() {
            Process.setThreadPriority(-19);
            Logging.d(WebRtcAudioRecord.TAG, AnonymousClass006.A05("AudioRecordThread", WebRtcAudioUtils.getThreadInfo()));
            boolean z = false;
            if (WebRtcAudioRecord.this.audioRecord.getRecordingState() == 3) {
                z = true;
            }
            WebRtcAudioRecord.assertTrue(z);
            System.nanoTime();
            while (this.keepAlive) {
                WebRtcAudioRecord webRtcAudioRecord = WebRtcAudioRecord.this;
                AudioRecord audioRecord = webRtcAudioRecord.audioRecord;
                ByteBuffer byteBuffer = webRtcAudioRecord.byteBuffer;
                int read = audioRecord.read(byteBuffer, byteBuffer.capacity());
                if (read == WebRtcAudioRecord.this.byteBuffer.capacity()) {
                    WebRtcAudioRecord webRtcAudioRecord2 = WebRtcAudioRecord.this;
                    webRtcAudioRecord2.nativeDataIsRecorded(read, webRtcAudioRecord2.nativeAudioRecord);
                } else {
                    Logging.e(WebRtcAudioRecord.TAG, AnonymousClass006.A01("AudioRecord.read failed: ", read));
                    if (read == -3) {
                        this.keepAlive = false;
                    }
                }
            }
            try {
                WebRtcAudioRecord.this.audioRecord.stop();
            } catch (IllegalStateException e) {
                Logging.e(WebRtcAudioRecord.TAG, AnonymousClass006.A05("AudioRecord.stop failed: ", e.getMessage()));
            }
        }
    }

    private native void nativeCacheDirectBufferAddress(ByteBuffer byteBuffer2, long j);

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native void nativeDataIsRecorded(int i, long j);

    public static void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Expected condition to be true");
        }
    }

    private boolean enableBuiltInAEC(boolean z) {
        StringBuilder sb = new StringBuilder("enableBuiltInAEC(");
        sb.append(z);
        sb.append(')');
        Logging.d(TAG, sb.toString());
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            return webRtcAudioEffects.setAEC(z);
        }
        Logging.e(TAG, "Built-in AEC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInAGC(boolean z) {
        StringBuilder sb = new StringBuilder("enableBuiltInAGC(");
        sb.append(z);
        sb.append(')');
        Logging.d(TAG, sb.toString());
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            return webRtcAudioEffects.setAGC(z);
        }
        Logging.e(TAG, "Built-in AGC is not supported on this platform");
        return false;
    }

    private boolean enableBuiltInNS(boolean z) {
        StringBuilder sb = new StringBuilder("enableBuiltInNS(");
        sb.append(z);
        sb.append(')');
        Logging.d(TAG, sb.toString());
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            return webRtcAudioEffects.setNS(z);
        }
        Logging.e(TAG, "Built-in NS is not supported on this platform");
        return false;
    }

    private int initRecording(int i, int i2) {
        String A01;
        StringBuilder sb = new StringBuilder("initRecording(sampleRate=");
        sb.append(i);
        sb.append(", channels=");
        sb.append(i2);
        sb.append(")");
        Logging.d(TAG, sb.toString());
        if (!WebRtcAudioUtils.hasPermission(this.context, "android.permission.RECORD_AUDIO")) {
            A01 = "RECORD_AUDIO permission is missing";
        } else if (this.audioRecord != null) {
            A01 = "InitRecording() called twice without StopRecording()";
        } else {
            int i3 = i / 100;
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect((i2 << 1) * i3);
            this.byteBuffer = allocateDirect;
            Logging.d(TAG, AnonymousClass006.A01("byteBuffer.capacity: ", allocateDirect.capacity()));
            nativeCacheDirectBufferAddress(this.byteBuffer, this.nativeAudioRecord);
            int minBufferSize = AudioRecord.getMinBufferSize(i, 16, 2);
            if (minBufferSize == -1 || minBufferSize == -2) {
                A01 = AnonymousClass006.A01("AudioRecord.getMinBufferSize failed: ", minBufferSize);
            } else {
                Logging.d(TAG, AnonymousClass006.A01("AudioRecord.getMinBufferSize: ", minBufferSize));
                int max = Math.max(minBufferSize << 1, this.byteBuffer.capacity());
                Logging.d(TAG, AnonymousClass006.A01("bufferSizeInBytes: ", max));
                try {
                    AudioRecord audioRecord2 = new AudioRecord(7, i, 16, 2, max);
                    this.audioRecord = audioRecord2;
                    if (audioRecord2.getState() != 1) {
                        A01 = "Failed to create a new AudioRecord instance";
                    } else {
                        StringBuilder sb2 = new StringBuilder("AudioRecord session ID: ");
                        sb2.append(this.audioRecord.getAudioSessionId());
                        sb2.append(", audio format: ");
                        sb2.append(this.audioRecord.getAudioFormat());
                        sb2.append(", channels: ");
                        sb2.append(this.audioRecord.getChannelCount());
                        sb2.append(", sample rate: ");
                        sb2.append(this.audioRecord.getSampleRate());
                        Logging.d(TAG, sb2.toString());
                        WebRtcAudioEffects webRtcAudioEffects = this.effects;
                        if (webRtcAudioEffects != null) {
                            webRtcAudioEffects.enable(this.audioRecord.getAudioSessionId());
                        }
                        return i3;
                    }
                } catch (IllegalArgumentException e) {
                    Logging.e(TAG, e.getMessage());
                    return -1;
                }
            }
        }
        Logging.e(TAG, A01);
        return -1;
    }

    private boolean startRecording() {
        Logging.d(TAG, "startRecording");
        boolean z = false;
        if (this.audioRecord != null) {
            z = true;
        }
        assertTrue(z);
        boolean z2 = false;
        if (this.audioThread == null) {
            z2 = true;
        }
        assertTrue(z2);
        try {
            this.audioRecord.startRecording();
            if (this.audioRecord.getRecordingState() != 3) {
                Logging.e(TAG, "AudioRecord.startRecording failed");
                return false;
            }
            AudioRecordThread audioRecordThread = new AudioRecordThread("AudioRecordJavaThread");
            this.audioThread = audioRecordThread;
            audioRecordThread.start();
            return true;
        } catch (IllegalStateException e) {
            Logging.e(TAG, AnonymousClass006.A05("AudioRecord.startRecording failed: ", e.getMessage()));
            return false;
        }
    }

    private boolean stopRecording() {
        Logging.d(TAG, "stopRecording");
        boolean z = false;
        if (this.audioThread != null) {
            z = true;
        }
        assertTrue(z);
        this.audioThread.joinThread();
        this.audioThread = null;
        WebRtcAudioEffects webRtcAudioEffects = this.effects;
        if (webRtcAudioEffects != null) {
            webRtcAudioEffects.release();
        }
        this.audioRecord.release();
        this.audioRecord = null;
        return true;
    }

    public WebRtcAudioRecord(Context context2, long j) {
        Logging.d(TAG, AnonymousClass006.A05("ctor", WebRtcAudioUtils.getThreadInfo()));
        this.context = context2;
        this.nativeAudioRecord = j;
        this.effects = new WebRtcAudioEffects();
    }
}
