package org.webrtc;

import X.AnonymousClass006;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.webrtc.DataChannel;

public class PeerConnection {
    public final List<MediaStream> localStreams = new LinkedList();
    public final long nativeObserver;
    public final long nativePeerConnection;
    public List<RtpReceiver> receivers = new LinkedList();
    public List<RtpSender> senders = new LinkedList();

    public enum BundlePolicy {
        BALANCED,
        MAXBUNDLE,
        MAXCOMPAT
    }

    public enum ContinualGatheringPolicy {
        GATHER_ONCE,
        GATHER_CONTINUALLY
    }

    public enum IceConnectionState {
        NEW,
        CHECKING,
        CONNECTED,
        COMPLETED,
        FAILED,
        DISCONNECTED,
        CLOSED
    }

    public enum IceGatheringState {
        NEW,
        GATHERING,
        COMPLETE
    }

    public static class IceServer {
        public final String password;
        public final String uri;
        public final String username;

        public String toString() {
            return AnonymousClass006.A0A(this.uri, "[", this.username, ":", this.password, "]");
        }

        public IceServer(String str) {
            this(str, "", "");
        }

        public IceServer(String str, String str2, String str3) {
            this.uri = str;
            this.username = str2;
            this.password = str3;
        }
    }

    public enum IceTransportsType {
        NONE,
        RELAY,
        NOHOST,
        ALL
    }

    public enum KeyType {
        RSA,
        ECDSA
    }

    public interface Observer {
        void onAddStream(MediaStream mediaStream);

        void onDataChannel(DataChannel dataChannel);

        void onIceCandidate(IceCandidate iceCandidate);

        void onIceConnectionChange(IceConnectionState iceConnectionState);

        void onIceConnectionReceivingChange(boolean z);

        void onIceGatheringChange(IceGatheringState iceGatheringState);

        void onRemoveStream(MediaStream mediaStream);

        void onRenegotiationNeeded();

        void onSignalingChange(SignalingState signalingState);
    }

    public enum RtcpMuxPolicy {
        NEGOTIATE,
        REQUIRE
    }

    public enum SignalingState {
        STABLE,
        HAVE_LOCAL_OFFER,
        HAVE_LOCAL_PRANSWER,
        HAVE_REMOTE_OFFER,
        HAVE_REMOTE_PRANSWER,
        CLOSED
    }

    public enum TcpCandidatePolicy {
        ENABLED,
        DISABLED
    }

    public static native void freeObserver(long j);

    public static native void freePeerConnection(long j);

    private native boolean nativeAddIceCandidate(String str, int i, String str2);

    private native boolean nativeAddLocalStream(long j);

    private native RtpSender nativeCreateSender(String str, String str2);

    private native List<RtpReceiver> nativeGetReceivers();

    private native List<RtpSender> nativeGetSenders();

    private native boolean nativeGetStats(StatsObserver statsObserver, long j);

    private native void nativeRemoveLocalStream(long j);

    public native void close();

    public native void createAnswer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native DataChannel createDataChannel(String str, DataChannel.Init init);

    public native void createOffer(SdpObserver sdpObserver, MediaConstraints mediaConstraints);

    public native SessionDescription getLocalDescription();

    public native SessionDescription getRemoteDescription();

    public native IceConnectionState iceConnectionState();

    public native IceGatheringState iceGatheringState();

    public native boolean setConfiguration(RTCConfiguration rTCConfiguration);

    public native void setLocalDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native void setRemoteDescription(SdpObserver sdpObserver, SessionDescription sessionDescription);

    public native SignalingState signalingState();

    public static class RTCConfiguration {
        public boolean audioJitterBufferFastAccelerate;
        public int audioJitterBufferMaxPackets;
        public BundlePolicy bundlePolicy = BundlePolicy.BALANCED;
        public ContinualGatheringPolicy continualGatheringPolicy;
        public int iceBackupCandidatePairPingInterval;
        public int iceConnectionReceivingTimeout;
        public List<IceServer> iceServers;
        public IceTransportsType iceTransportsType = IceTransportsType.ALL;
        public KeyType keyType;
        public RtcpMuxPolicy rtcpMuxPolicy = RtcpMuxPolicy.NEGOTIATE;
        public TcpCandidatePolicy tcpCandidatePolicy = TcpCandidatePolicy.ENABLED;

        public RTCConfiguration(List<IceServer> list) {
            this.iceServers = list;
            this.audioJitterBufferMaxPackets = 50;
            this.audioJitterBufferFastAccelerate = false;
            this.iceConnectionReceivingTimeout = -1;
            this.iceBackupCandidatePairPingInterval = -1;
            this.keyType = KeyType.ECDSA;
            this.continualGatheringPolicy = ContinualGatheringPolicy.GATHER_ONCE;
        }
    }

    static {
        System.loadLibrary("jingle_peerconnection_so");
    }

    public boolean addIceCandidate(IceCandidate iceCandidate) {
        return nativeAddIceCandidate(iceCandidate.sdpMid, iceCandidate.sdpMLineIndex, iceCandidate.sdp);
    }

    public boolean addStream(MediaStream mediaStream) {
        if (!nativeAddLocalStream(mediaStream.nativeStream)) {
            return false;
        }
        this.localStreams.add(mediaStream);
        return true;
    }

    public List<RtpReceiver> getReceivers() {
        for (RtpReceiver rtpReceiver : this.receivers) {
            rtpReceiver.dispose();
        }
        List<RtpReceiver> nativeGetReceivers = nativeGetReceivers();
        this.receivers = nativeGetReceivers;
        return Collections.unmodifiableList(nativeGetReceivers);
    }

    public List<RtpSender> getSenders() {
        for (RtpSender rtpSender : this.senders) {
            rtpSender.dispose();
        }
        List<RtpSender> nativeGetSenders = nativeGetSenders();
        this.senders = nativeGetSenders;
        return Collections.unmodifiableList(nativeGetSenders);
    }

    public boolean getStats(StatsObserver statsObserver, MediaStreamTrack mediaStreamTrack) {
        long j;
        if (mediaStreamTrack == null) {
            j = 0;
        } else {
            j = mediaStreamTrack.nativeTrack;
        }
        return nativeGetStats(statsObserver, j);
    }

    public void removeStream(MediaStream mediaStream) {
        nativeRemoveLocalStream(mediaStream.nativeStream);
        this.localStreams.remove(mediaStream);
    }

    public PeerConnection(long j, long j2) {
        this.nativePeerConnection = j;
        this.nativeObserver = j2;
    }

    public RtpSender createSender(String str, String str2) {
        RtpSender nativeCreateSender = nativeCreateSender(str, str2);
        if (nativeCreateSender != null) {
            this.senders.add(nativeCreateSender);
        }
        return nativeCreateSender;
    }

    public void dispose() {
        close();
        for (MediaStream mediaStream : this.localStreams) {
            nativeRemoveLocalStream(mediaStream.nativeStream);
            mediaStream.dispose();
        }
        this.localStreams.clear();
        for (RtpSender rtpSender : this.senders) {
            rtpSender.dispose();
        }
        this.senders.clear();
        for (RtpReceiver rtpReceiver : this.receivers) {
            rtpReceiver.dispose();
        }
        this.receivers.clear();
        freePeerConnection(this.nativePeerConnection);
        freeObserver(this.nativeObserver);
    }
}
