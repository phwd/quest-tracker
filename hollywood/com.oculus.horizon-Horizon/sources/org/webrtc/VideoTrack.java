package org.webrtc;

import java.util.LinkedList;

public class VideoTrack extends MediaStreamTrack {
    public final LinkedList<VideoRenderer> renderers = new LinkedList<>();

    public static native void free(long j);

    public static native void nativeAddRenderer(long j, long j2);

    public static native void nativeRemoveRenderer(long j, long j2);

    public void addRenderer(VideoRenderer videoRenderer) {
        this.renderers.add(videoRenderer);
        nativeAddRenderer(this.nativeTrack, videoRenderer.nativeVideoRenderer);
    }

    @Override // org.webrtc.MediaStreamTrack
    public void dispose() {
        while (!this.renderers.isEmpty()) {
            removeRenderer(this.renderers.getFirst());
        }
        super.dispose();
    }

    public void removeRenderer(VideoRenderer videoRenderer) {
        if (this.renderers.remove(videoRenderer)) {
            nativeRemoveRenderer(this.nativeTrack, videoRenderer.nativeVideoRenderer);
            videoRenderer.dispose();
        }
    }

    public VideoTrack(long j) {
        super(j);
    }
}
