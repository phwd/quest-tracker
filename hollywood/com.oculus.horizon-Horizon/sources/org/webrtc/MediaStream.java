package org.webrtc;

import java.util.LinkedList;

public class MediaStream {
    public final LinkedList<AudioTrack> audioTracks = new LinkedList<>();
    public final long nativeStream;
    public final LinkedList<VideoTrack> preservedVideoTracks = new LinkedList<>();
    public final LinkedList<VideoTrack> videoTracks = new LinkedList<>();

    public static native void free(long j);

    public static native boolean nativeAddAudioTrack(long j, long j2);

    public static native boolean nativeAddVideoTrack(long j, long j2);

    public static native String nativeLabel(long j);

    public static native boolean nativeRemoveAudioTrack(long j, long j2);

    public static native boolean nativeRemoveVideoTrack(long j, long j2);

    public boolean addPreservedTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.preservedVideoTracks.add(videoTrack);
        return true;
    }

    public void dispose() {
        while (!this.audioTracks.isEmpty()) {
            AudioTrack first = this.audioTracks.getFirst();
            removeTrack(first);
            first.dispose();
        }
        while (!this.videoTracks.isEmpty()) {
            VideoTrack first2 = this.videoTracks.getFirst();
            removeTrack(first2);
            first2.dispose();
        }
        while (!this.preservedVideoTracks.isEmpty()) {
            removeTrack(this.preservedVideoTracks.getFirst());
        }
        free(this.nativeStream);
    }

    public String label() {
        return nativeLabel(this.nativeStream);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        sb.append(nativeLabel(this.nativeStream));
        sb.append(":A=");
        sb.append(this.audioTracks.size());
        sb.append(":V=");
        sb.append(this.videoTracks.size());
        sb.append("]");
        return sb.toString();
    }

    public MediaStream(long j) {
        this.nativeStream = j;
    }

    public boolean addTrack(AudioTrack audioTrack) {
        if (!nativeAddAudioTrack(this.nativeStream, audioTrack.nativeTrack)) {
            return false;
        }
        this.audioTracks.add(audioTrack);
        return true;
    }

    public boolean addTrack(VideoTrack videoTrack) {
        if (!nativeAddVideoTrack(this.nativeStream, videoTrack.nativeTrack)) {
            return false;
        }
        this.videoTracks.add(videoTrack);
        return true;
    }

    public boolean removeTrack(AudioTrack audioTrack) {
        this.audioTracks.remove(audioTrack);
        return nativeRemoveAudioTrack(this.nativeStream, audioTrack.nativeTrack);
    }

    public boolean removeTrack(VideoTrack videoTrack) {
        this.videoTracks.remove(videoTrack);
        this.preservedVideoTracks.remove(videoTrack);
        return nativeRemoveVideoTrack(this.nativeStream, videoTrack.nativeTrack);
    }
}
