package com.oculus.platform;

import X.AnonymousClass006;
import android.content.Context;
import android.media.AudioRecord;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;
import android.util.Log;
import java.nio.ByteBuffer;
import org.webrtc.voiceengine.WebRtcAudioEffects;

public class OVRMicrophone {
    public static final int CHANNELS = 16;
    public static final int FORMAT = 2;
    public static final int MIC_START_ALREADY_STARTED = -2;
    public static final int MIC_START_EXCLUSIVE_FAILURE = -3;
    public static final int MIC_START_MISSING_PERMISSION = -1;
    public static final int MIC_START_SUCCESS = 0;
    public static final int MODE = 0;
    public static final int SAMPLE_RATE = 48000;
    public final int AUDIO_THREAD_JOIN_TIME_MS = 150;
    public final String TAG = "OVRPlatform-OVRMicrophone";
    public AcousticEchoCanceler aecEffect = null;
    public AudioRecordThread audioThread = null;
    public final int bufferSize;
    public ByteBuffer byteBuffer;
    public long nativeMicrophoneImpl = 0;
    public NoiseSuppressor nsEffect = null;
    public AudioRecord recorder = null;
    public Object shutdownLock = new Object();
    public State state = State.Uninitialized;
    public Object stateLock = new Object();
    public final boolean useRtcEffects;
    public boolean usingNoiseSuppressor = true;
    public WebRtcAudioEffects webRtcEffects = null;

    public class AudioRecordThread extends Thread {
        public AudioRecordThread(String str) {
            super(str);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0040, code lost:
            if (r3 == com.oculus.platform.OVRMicrophone.State.Stopping) goto L_0x0042;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
            r2 = r8.this$0;
            r0 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
            if (r3 != com.oculus.platform.OVRMicrophone.State.Recording) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0054, code lost:
            r0 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0055, code lost:
            r2.assertTrue(r0);
            r1 = r8.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0060, code lost:
            if (r7 == r1.byteBuffer.capacity()) goto L_0x0063;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0062, code lost:
            r5 = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0063, code lost:
            r1.assertTrue(r5);
            r6 = r8.this$0.shutdownLock;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006a, code lost:
            monitor-enter(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
            r5 = r8.this$0;
            r3 = r5.nativeMicrophoneImpl;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0073, code lost:
            if (r3 != 0) goto L_0x007e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0075, code lost:
            android.util.Log.w("OVRPlatform-OVRMicrophone", "MicrophoneServer already destroyed");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x007c, code lost:
            monitor-exit(r6);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:0x007e, code lost:
            r5.nativeOnAudioData(r3, r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x002d, code lost:
            if (r7 >= 0) goto L_0x002f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            // Method dump skipped, instructions count: 152
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRMicrophone.AudioRecordThread.run():void");
        }
    }

    public enum State {
        Uninitialized,
        Initialized,
        Recording,
        Stopping
    }

    public void disableNoiseSuppressor() {
        this.usingNoiseSuppressor = false;
        waitAudioThread();
    }

    public void enableNoiseSuppressor() {
        this.usingNoiseSuppressor = true;
        waitAudioThread();
    }

    public native void nativeOnAudioData(long j, int i);

    public native void nativeSetDirectByteBuffer(long j, ByteBuffer byteBuffer2);

    /* renamed from: com.oculus.platform.OVRMicrophone$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$platform$OVRMicrophone$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.platform.OVRMicrophone$State[] r0 = com.oculus.platform.OVRMicrophone.State.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.platform.OVRMicrophone.AnonymousClass1.$SwitchMap$com$oculus$platform$OVRMicrophone$State = r2
                com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Uninitialized     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Initialized     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Recording     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Stopping     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRMicrophone.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void assertTrue(boolean z) {
        if (!z) {
            throw new AssertionError("Assertion failed");
        }
    }

    private void enableEffects(int i, boolean z) {
        if (this.useRtcEffects) {
            enableWebRtcEffects(i);
        } else {
            enableAndroidEffects(i, z);
        }
    }

    private void enableWebRtcEffects(int i) {
        if (this.useRtcEffects && this.webRtcEffects == null) {
            WebRtcAudioEffects webRtcAudioEffects = new WebRtcAudioEffects();
            this.webRtcEffects = webRtcAudioEffects;
            webRtcAudioEffects.setAEC(true);
            this.webRtcEffects.setAGC(true);
            this.webRtcEffects.setNS(true);
            this.webRtcEffects.enable(i);
        }
    }

    private boolean initializeRecorder() {
        int i = 0;
        if (this.useRtcEffects) {
            i = 7;
        }
        AudioRecord audioRecord = new AudioRecord(i, 48000, 16, 2, this.bufferSize * 3);
        this.recorder = audioRecord;
        if (audioRecord.getState() != 1) {
            Log.e("OVRPlatform-OVRMicrophone", "Failed to initialize AudioRecord.");
            this.recorder = null;
            return false;
        }
        this.recorder.getAudioSessionId();
        this.recorder.getAudioFormat();
        this.recorder.getChannelCount();
        this.recorder.getSampleRate();
        enableEffects(this.recorder.getAudioSessionId(), this.usingNoiseSuppressor);
        return true;
    }

    private void releaseAndroidEffects() {
        AcousticEchoCanceler acousticEchoCanceler = this.aecEffect;
        if (acousticEchoCanceler != null) {
            try {
                acousticEchoCanceler.release();
            } catch (Exception e) {
                Log.e("OVRPlatform-OVRMicrophone", "aecEffect release exception: ", e);
            }
            this.aecEffect = null;
        }
        NoiseSuppressor noiseSuppressor = this.nsEffect;
        if (noiseSuppressor != null) {
            try {
                noiseSuppressor.release();
                this.nsEffect = null;
            } catch (Exception e2) {
                Log.e("OVRPlatform-OVRMicrophone", "nsEffect release exception: ", e2);
            }
        }
    }

    private void releaseEffects() {
        if (this.useRtcEffects) {
            releaseWebRtcEffects();
        } else {
            releaseAndroidEffects();
        }
    }

    private void releaseWebRtcEffects() {
        WebRtcAudioEffects webRtcAudioEffects = this.webRtcEffects;
        if (webRtcAudioEffects != null) {
            webRtcAudioEffects.release();
            this.webRtcEffects = null;
        }
    }

    private void signalNativeObjectDestroy() {
        synchronized (this.shutdownLock) {
            this.nativeMicrophoneImpl = 0;
        }
        stop();
    }

    private int startRecording() {
        System.nanoTime();
        try {
            this.recorder.startRecording();
            System.nanoTime();
            if (this.recorder.getRecordingState() != 3) {
                Log.w("OVRPlatform-OVRMicrophone", "AudioRecord.startRecording() failed");
                return -3;
            }
            System.nanoTime();
            try {
                AudioRecordThread audioRecordThread = new AudioRecordThread("OVRMicRecord");
                this.audioThread = audioRecordThread;
                audioRecordThread.start();
                System.nanoTime();
                enableEffects(this.recorder.getAudioSessionId(), this.usingNoiseSuppressor);
                return 0;
            } catch (IllegalStateException unused) {
                return -2;
            }
        } catch (IllegalStateException unused2) {
            return -1;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void stopRecording() {
        try {
            this.recorder.stop();
            releaseEffects();
        } catch (IllegalStateException e) {
            Log.e("OVRPlatform-OVRMicrophone", AnonymousClass006.A05("AudioRecord.stop failed: ", e.getMessage()));
        }
    }

    private void waitAudioThread() {
        AudioRecordThread audioRecordThread = this.audioThread;
        if (audioRecordThread != null) {
            try {
                audioRecordThread.join(150, 0);
            } catch (NullPointerException unused) {
            } catch (InterruptedException e) {
                Log.e("OVRPlatform-OVRMicrophone", "Got exception while waiting for audioThread", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0032  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int start() {
        /*
            r4 = this;
            java.lang.Object r3 = r4.stateLock
            monitor-enter(r3)
            com.oculus.platform.OVRMicrophone$State r0 = r4.state     // Catch:{ all -> 0x0043 }
            int r0 = r0.ordinal()     // Catch:{ all -> 0x0043 }
            r2 = 0
            switch(r0) {
                case 0: goto L_0x000f;
                case 1: goto L_0x0023;
                case 2: goto L_0x003e;
                case 3: goto L_0x0037;
                default: goto L_0x000d;
            }     // Catch:{ all -> 0x0043 }
        L_0x000d:
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            goto L_0x003c
        L_0x000f:
            android.media.AudioRecord r1 = r4.recorder     // Catch:{ all -> 0x0043 }
            r0 = 0
            if (r1 != 0) goto L_0x0015
            r0 = 1
        L_0x0015:
            r4.assertTrue(r0)     // Catch:{ all -> 0x0043 }
            boolean r0 = r4.initializeRecorder()     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x001f
            goto L_0x0040
        L_0x001f:
            com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Initialized     // Catch:{ all -> 0x0043 }
            r4.state = r0     // Catch:{ all -> 0x0043 }
        L_0x0023:
            com.oculus.platform.OVRMicrophone$AudioRecordThread r0 = r4.audioThread     // Catch:{ all -> 0x0043 }
            if (r0 != 0) goto L_0x0028
            r2 = 1
        L_0x0028:
            r4.assertTrue(r2)     // Catch:{ all -> 0x0043 }
            int r1 = r4.startRecording()     // Catch:{ all -> 0x0043 }
            r0 = 0
            if (r1 != r0) goto L_0x0041
            com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Recording     // Catch:{ all -> 0x0043 }
            r4.state = r0     // Catch:{ all -> 0x0043 }
            goto L_0x000d
        L_0x0037:
            com.oculus.platform.OVRMicrophone$State r0 = com.oculus.platform.OVRMicrophone.State.Recording     // Catch:{ all -> 0x0043 }
            r4.state = r0     // Catch:{ all -> 0x0043 }
            goto L_0x000d
        L_0x003c:
            r0 = 0
            return r0
        L_0x003e:
            r1 = -2
            goto L_0x0041
        L_0x0040:
            r1 = -1
        L_0x0041:
            monitor-exit(r3)     // Catch:{ all -> 0x0043 }
            return r1
        L_0x0043:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
            switch-data {0->0x000f, 1->0x0023, 2->0x003e, 3->0x0037, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.platform.OVRMicrophone.start():int");
    }

    public void stop() {
        synchronized (this.stateLock) {
            if (this.state == State.Recording) {
                this.state = State.Stopping;
            }
        }
    }

    public OVRMicrophone(Context context, long j, boolean z) {
        this.useRtcEffects = z;
        this.bufferSize = AudioRecord.getMinBufferSize(48000, 16, 2);
        this.nativeMicrophoneImpl = j;
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(960);
        this.byteBuffer = allocateDirect;
        nativeSetDirectByteBuffer(this.nativeMicrophoneImpl, allocateDirect);
    }

    public static boolean checkMicPermission() {
        AudioRecord audioRecord = new AudioRecord(0, 48000, 16, 2, AudioRecord.getMinBufferSize(48000, 16, 2));
        boolean z = true;
        if (audioRecord.getState() != 1) {
            z = false;
        }
        audioRecord.release();
        return z;
    }

    private void enableAndroidEffects(int i, boolean z) {
        if (AcousticEchoCanceler.isAvailable() && this.aecEffect == null) {
            AcousticEchoCanceler create = AcousticEchoCanceler.create(i);
            this.aecEffect = create;
            if (create != null) {
                create.setEnabled(true);
            }
        }
        if (NoiseSuppressor.isAvailable() && this.nsEffect == null && z) {
            NoiseSuppressor create2 = NoiseSuppressor.create(i);
            this.nsEffect = create2;
            if (create2 != null) {
                create2.setEnabled(true);
            }
        }
    }

    private void setState(State state2) {
        this.state = state2;
    }
}
