package org.webrtc;

public class IceCandidate {
    public final String sdp;
    public final int sdpMLineIndex;
    public final String sdpMid;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.sdpMid);
        sb.append(":");
        sb.append(this.sdpMLineIndex);
        sb.append(":");
        sb.append(this.sdp);
        return sb.toString();
    }

    public IceCandidate(String str, int i, String str2) {
        this.sdpMid = str;
        this.sdpMLineIndex = i;
        this.sdp = str2;
    }
}
