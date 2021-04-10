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

    private static VideoPlayerInstance getVideoPlayerInstance(String methodName, int videoHandle) {
        if (videoPlayerInstances.containsKey(Integer.valueOf(videoHandle))) {
            return videoPlayerInstances.get(Integer.valueOf(videoHandle));
        }
        Log.w("RCTVideo", methodName + ": VideoHandle " + videoHandle + " doesn't exist!");
        return null;
    }

    public static void createVideoPlayerInstance(long ctxPtr, int videoHandle) {
        videoPlayerInstances.put(Integer.valueOf(videoHandle), new VideoPlayerInstance(ctxPtr, videoHandle));
    }

    public static void deleteVideoPlayerInstance(int videoHandle) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("deleteVideoPlayerInstance", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.close();
            videoPlayerInstances.remove(Integer.valueOf(videoHandle));
        }
    }

    public static void prepareVideo(int videoHandle) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("prepareVideo", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.prepareVideo();
        }
    }

    public static void updateTexture(int videoHandle, int textureIndex) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("updateTexture", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.updateTexture(textureIndex);
        }
    }

    public static void setLoop(int videoHandle, boolean looping) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setLoop", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setLooping(looping);
        }
    }

    public static void setPlay(int videoHandle, boolean isPlaying) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setPlay", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setPlay(isPlaying);
        }
    }

    public static void setPositionEventThrottle(int videoHandle, int positionEventThrottle) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setPositionEventThrottle", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setPositionEventThrottle(positionEventThrottle);
        }
    }

    public static void setVideoSource(int videoHandle, String url) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setVideoSource", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setVideoSource(url);
        }
    }

    public static void setVolume(int videoHandle, float volume) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("setVolume", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.setVolume(volume);
        }
    }

    public static void seekVideo(int videoHandle, int positionInMs, boolean isExact) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("seekVideo", videoHandle);
        if (videoPlayerInstance != null) {
            videoPlayerInstance.seekVideo(positionInMs, isExact);
        }
    }

    public static int frameVideo(int videoHandle) {
        VideoPlayerInstance videoPlayerInstance = getVideoPlayerInstance("frameVideo", videoHandle);
        if (videoPlayerInstance == null) {
            return 0;
        }
        return videoPlayerInstance.frame();
    }
}
