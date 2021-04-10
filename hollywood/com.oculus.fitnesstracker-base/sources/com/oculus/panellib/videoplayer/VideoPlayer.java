package com.oculus.panellib.videoplayer;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class VideoPlayer {
    private static final Map<Integer, VideoPlayerInstance> videoPlayerInstances = new HashMap();

    public static native void notifyOnCompletion(long j, int i);

    public static native void notifyOnDurationChange(long j, int i, int i2);

    public static native void notifyOnPositionChange(long j, int i, int i2);

    public static native void notifyOnSeek(long j, int i, int i2);

    public static native void notifyOnVideoSizeChange(long j, int i, int i2, int i3);

    private static VideoPlayerInstance getVideoPlayerInstance(String str, int i) {
        if (videoPlayerInstances.containsKey(Integer.valueOf(i))) {
            return videoPlayerInstances.get(Integer.valueOf(i));
        }
        Log.w("RCTVideo", str + ": VideoHandle " + i + " doesn't exist!");
        return null;
    }

    public static void createVideoPlayerInstance(long j, int i) {
        videoPlayerInstances.put(Integer.valueOf(i), new VideoPlayerInstance(j, i));
    }

    public static void deleteVideoPlayerInstance(int i) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("deleteVideoPlayerInstance", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.close();
            videoPlayerInstances.remove(Integer.valueOf(i));
        }
    }

    public static void prepareVideo(int i) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("prepareVideo", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.prepareVideo();
        }
    }

    public static void updateTexture(int i, int i2) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("updateTexture", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.updateTexture(i2);
        }
    }

    public static void setLoop(int i, boolean z) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setLoop", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setLooping(z);
        }
    }

    public static void setPlay(int i, boolean z) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setPlay", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setPlay(z);
        }
    }

    public static void setPositionEventThrottle(int i, int i2) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setPositionEventThrottle", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setPositionEventThrottle(i2);
        }
    }

    public static void setVideoSource(int i, String str) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setVideoSource", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setVideoSource(str);
        }
    }

    public static void setVolume(int i, float f) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setVolume", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setVolume(f);
        }
    }

    public static void seekVideo(int i, int i2, boolean z) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("seekVideo", i);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.seekVideo(i2, z);
        }
    }

    public static int frameVideo(int i) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("frameVideo", i);
        if (videoPlayerInstance == null) {
            return 0;
        }
        return videoPlayerInstance.frame();
    }
}
