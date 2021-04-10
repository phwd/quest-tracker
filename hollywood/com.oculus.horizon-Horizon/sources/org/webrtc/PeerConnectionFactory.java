package org.webrtc;

import X.AnonymousClass006;
import java.util.List;
import org.webrtc.EglBase;
import org.webrtc.PeerConnection;

public class PeerConnectionFactory {
    public static final String TAG = "PeerConnectionFactory";
    public static Thread signalingThread;
    public static Thread workerThread;
    public EglBase localEglbase;
    public final long nativeFactory;
    public EglBase remoteEglbase;

    public static class Options {
        public static final int ADAPTER_TYPE_CELLULAR = 4;
        public static final int ADAPTER_TYPE_ETHERNET = 1;
        public static final int ADAPTER_TYPE_LOOPBACK = 16;
        public static final int ADAPTER_TYPE_UNKNOWN = 0;
        public static final int ADAPTER_TYPE_VPN = 8;
        public static final int ADAPTER_TYPE_WIFI = 2;
        public boolean disableEncryption;
        public boolean disableNetworkMonitor;
        public int networkIgnoreMask;
    }

    public static native boolean initializeAndroidGlobals(Object obj, boolean z, boolean z2, boolean z3);

    public static native void initializeFieldTrials(String str);

    public static native void initializeInternalTracer();

    public static native long nativeCreateAudioSource(long j, MediaConstraints mediaConstraints);

    public static native long nativeCreateAudioTrack(long j, String str, long j2);

    public static native long nativeCreateLocalMediaStream(long j, String str);

    public static native long nativeCreateObserver(PeerConnection.Observer observer);

    public static native long nativeCreatePeerConnection(long j, PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, long j2);

    public static native long nativeCreatePeerConnectionFactory(Options options);

    public static native long nativeCreateVideoSource(long j, VideoCapturer videoCapturer, MediaConstraints mediaConstraints);

    public static native long nativeCreateVideoTrack(long j, String str, long j2);

    public static native void nativeFreeFactory(long j);

    public static native void nativeSetVideoHwAccelerationOptions(long j, Object obj, Object obj2);

    public static native boolean nativeStartAecDump(long j, int i, int i2);

    public static native boolean nativeStartRtcEventLog(long j, int i);

    public static native void nativeStopAecDump(long j);

    public static native void nativeStopRtcEventLog(long j);

    public static native void nativeThreadsCallbacks(long j);

    public static native void shutdownInternalTracer();

    public static native boolean startInternalTracingCapture(String str);

    public static native void stopInternalTracingCapture();

    @Deprecated
    public native void nativeSetOptions(long j, Options options);

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public static void printStackTrace(Thread thread, String str) {
        StackTraceElement[] stackTrace;
        int length;
        if (thread != null && (length = (stackTrace = thread.getStackTrace()).length) > 0) {
            Logging.d(TAG, AnonymousClass006.A05(str, " stacks trace:"));
            for (int i = 0; i < length; i++) {
                Logging.d(TAG, stackTrace[i].toString());
            }
        }
    }

    public static void printStackTraces() {
        printStackTrace(workerThread, "Worker thread");
        printStackTrace(signalingThread, "Signaling thread");
    }

    public void StopRtcEventLog() {
        nativeStopRtcEventLog(this.nativeFactory);
    }

    public AudioSource createAudioSource(MediaConstraints mediaConstraints) {
        return new AudioSource(nativeCreateAudioSource(this.nativeFactory, mediaConstraints));
    }

    public AudioTrack createAudioTrack(String str, AudioSource audioSource) {
        return new AudioTrack(nativeCreateAudioTrack(this.nativeFactory, str, audioSource.nativeSource));
    }

    public MediaStream createLocalMediaStream(String str) {
        return new MediaStream(nativeCreateLocalMediaStream(this.nativeFactory, str));
    }

    public VideoSource createVideoSource(VideoCapturer videoCapturer, MediaConstraints mediaConstraints) {
        return new VideoSource(nativeCreateVideoSource(this.nativeFactory, videoCapturer, mediaConstraints));
    }

    public VideoTrack createVideoTrack(String str, VideoSource videoSource) {
        return new VideoTrack(nativeCreateVideoTrack(this.nativeFactory, str, videoSource.nativeSource));
    }

    public void dispose() {
        nativeFreeFactory(this.nativeFactory);
        signalingThread = null;
        workerThread = null;
        EglBase eglBase = this.localEglbase;
        if (eglBase != null) {
            eglBase.release();
        }
        EglBase eglBase2 = this.remoteEglbase;
        if (eglBase2 != null) {
            eglBase2.release();
        }
    }

    @Deprecated
    public void setOptions(Options options) {
        nativeSetOptions(this.nativeFactory, options);
    }

    public void setVideoHwAccelerationOptions(EglBase.Context context, EglBase.Context context2) {
        if (this.localEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.localEglbase.release();
        }
        if (this.remoteEglbase != null) {
            Logging.w(TAG, "Egl context already set.");
            this.remoteEglbase.release();
        }
        int[] iArr = EglBase.CONFIG_PLAIN;
        this.localEglbase = EglBase.create(context, iArr);
        EglBase create = EglBase.create(context2, iArr);
        this.remoteEglbase = create;
        nativeSetVideoHwAccelerationOptions(this.nativeFactory, this.localEglbase.getEglBaseContext(), create.getEglBaseContext());
    }

    public boolean startAecDump(int i, int i2) {
        return nativeStartAecDump(this.nativeFactory, i, i2);
    }

    public boolean startRtcEventLog(int i) {
        return nativeStartRtcEventLog(this.nativeFactory, i);
    }

    public void stopAecDump() {
        nativeStopAecDump(this.nativeFactory);
    }

    public void threadsCallbacks() {
        nativeThreadsCallbacks(this.nativeFactory);
    }

    public static void onSignalingThreadReady() {
        signalingThread = Thread.currentThread();
        Logging.d(TAG, "onSignalingThreadReady");
    }

    public static void onWorkerThreadReady() {
        workerThread = Thread.currentThread();
        Logging.d(TAG, "onWorkerThreadReady");
    }

    @Deprecated
    public PeerConnectionFactory() {
        this(null);
    }

    public PeerConnectionFactory(Options options) {
        long nativeCreatePeerConnectionFactory = nativeCreatePeerConnectionFactory(options);
        this.nativeFactory = nativeCreatePeerConnectionFactory;
        if (nativeCreatePeerConnectionFactory == 0) {
            throw new RuntimeException("Failed to initialize PeerConnectionFactory!");
        }
    }

    public PeerConnection createPeerConnection(List<PeerConnection.IceServer> list, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        return createPeerConnection(new PeerConnection.RTCConfiguration(list), mediaConstraints, observer);
    }

    public PeerConnection createPeerConnection(PeerConnection.RTCConfiguration rTCConfiguration, MediaConstraints mediaConstraints, PeerConnection.Observer observer) {
        long nativeCreateObserver = nativeCreateObserver(observer);
        if (nativeCreateObserver == 0) {
            return null;
        }
        long nativeCreatePeerConnection = nativeCreatePeerConnection(this.nativeFactory, rTCConfiguration, mediaConstraints, nativeCreateObserver);
        if (nativeCreatePeerConnection != 0) {
            return new PeerConnection(nativeCreatePeerConnection, nativeCreateObserver);
        }
        return null;
    }
}
