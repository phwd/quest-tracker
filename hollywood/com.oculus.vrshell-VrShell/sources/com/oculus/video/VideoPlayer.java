package com.oculus.video;

import android.graphics.Rect;
import android.view.Surface;
import com.oculus.video.metadata.CameraMotionData;
import com.oculus.video.projection.VideoProjection;
import java.util.List;
import java.util.Map;

public interface VideoPlayer {

    public interface EventListener {
        String getStringPreference(String str);

        Surface getSubtitleSurface();

        String getUserAgent();

        void onAdaptivePlaylistUpdate(Map<String, Object> map, List<Map<String, Object>> list);

        void onAudioFormatChanged(String str, String str2);

        void onBuffering();

        void onCameraMotionData(CameraMotionData cameraMotionData);

        void onDroppedFrames(long j, long j2, long j3, long j4);

        void onError(Exception exc);

        void onExtractProjectionMetadata(VideoProjection videoProjection);

        void onFinish();

        void onHttpConnect(String str, String str2, String str3, int i, long j);

        void onReady();

        void onRelease();

        void onSeekProcessed();

        void onSoftError(Throwable th);

        void onStartVideo();

        void onStopBuffering();

        void onStreamingSample(long j, long j2, long j3, long j4);

        void onTimelineChanged(long j, long j2, boolean z);

        void onVideoFormatChanged(int i, int i2, float f, int i3, VideoProjection videoProjection, float f2, int i4, String str, String str2, double d);

        void onVideoSizeChanged(int i, int i2, float f, Rect rect);

        void setStringPreference(String str, String str2);
    }

    long getBufferedPositionMs();

    long getCurrentEpochTimePositionMs();

    long getCurrentPositionMs();

    long getDurationMs();

    long getLastPresentationTimeUs();

    String getName();

    float getVolume();

    boolean isBuffering();

    boolean isMuted();

    boolean isPlaying();

    boolean isSeekable();

    void onBeforeRender(long j);

    void onRender(boolean z);

    void pause();

    void release();

    void resume();

    void seekTo(long j);

    boolean selectSubtitle(String str);

    boolean selectVideoTrack(String str);

    void setMute(boolean z);

    void setVolume(float f);

    void start(Video video, boolean z, Surface surface, EventListener eventListener);

    void stop();
}
