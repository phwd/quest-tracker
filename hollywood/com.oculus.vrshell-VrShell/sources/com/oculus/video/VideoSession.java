package com.oculus.video;

import android.content.Context;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.annotation.NonNull;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.VideoInfoExtractor;
import com.oculus.video.VideoPlayer;
import com.oculus.video.metadata.CameraMotionData;
import com.oculus.video.projection.VideoProjection;
import com.oculus.video.source.dash.OculusDashManifest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoSession implements VideoPlayer.EventListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TAG = "VideoSession";
    private boolean didStartPlayback = false;
    private EventListener eventListener;
    private boolean isSeeking = false;
    private boolean needsFallbackPlayer = false;
    private boolean playWhenReady;
    private Handler playbackHandler;
    private VideoPlayer player;
    private final Settings playerSettings;
    private final long sessionId;
    private Video sessionVideo;
    private VideoInfoExtractor videoInfoExtractor;
    private Surface videoSurface;

    /* access modifiers changed from: package-private */
    public interface EventListener {
        String getStringPreference(String str);

        Surface getSubtitleSurface(VideoSession videoSession);

        String getUserAgent();

        void onAdaptivePlaylistUpdate(VideoSession videoSession, Map<String, Object> map, List<Map<String, Object>> list);

        void onAudioFormatChanged(VideoSession videoSession, String str, String str2);

        void onBuffering(VideoSession videoSession);

        void onCameraMotionData(VideoSession videoSession, CameraMotionData cameraMotionData);

        void onDroppedFrames(VideoSession videoSession, long j, long j2, long j3, long j4);

        void onError(VideoSession videoSession, Exception exc);

        void onExtractProjectionMetadata(VideoSession videoSession, VideoProjection videoProjection);

        void onFinish(VideoSession videoSession);

        void onHttpConnect(VideoSession videoSession, String str, String str2, String str3, int i, long j);

        void onReady(VideoSession videoSession);

        void onRelease(VideoSession videoSession);

        void onSeekComplete(VideoSession videoSession);

        void onSoftError(VideoSession videoSession, Throwable th);

        void onStartVideo(VideoSession videoSession);

        void onStopBuffering(VideoSession videoSession);

        void onStreamingSample(VideoSession videoSession, long j, long j2, long j3, long j4);

        void onTimelineChanged(VideoSession videoSession, long j, long j2, boolean z);

        void onVideoFormatChanged(VideoSession videoSession, int i, int i2, float f, int i3, VideoProjection videoProjection, float f2, int i4, String str, String str2, double d);

        void onVideoPrepared(VideoSession videoSession, boolean z);

        void onVideoSizeChanged(VideoSession videoSession, int i, int i2, float f, Rect rect);

        void setStringPreference(String str, String str2);
    }

    VideoSession(long j, Settings settings, Handler handler, EventListener eventListener2) {
        String str = TAG;
        Log.d(str, j + " - created");
        this.sessionId = j;
        this.playerSettings = settings;
        this.playbackHandler = handler;
        this.eventListener = eventListener2;
    }

    /* access modifiers changed from: package-private */
    public long getSessionId() {
        return this.sessionId;
    }

    /* access modifiers changed from: package-private */
    public Video getSessionVideo() {
        return this.sessionVideo;
    }

    /* access modifiers changed from: package-private */
    public VideoPlayer getPlayer() {
        return this.player;
    }

    /* access modifiers changed from: package-private */
    public boolean isSeeking() {
        return this.isSeeking;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onVideoPrepared(Video video, boolean z) {
        try {
            video.setProjectionMetadata(VideoProjection.sanitizeAPIProjectionMetadata(video.getProjectionMetadata(), video.getFrameAspectRatio(), video.getFrameRotation()));
        } catch (JSONException unused) {
        }
        this.sessionVideo = video;
        this.eventListener.onVideoPrepared(this, z);
        String str = TAG;
        Log.d(str, this.sessionId + " - video prepared: " + this.sessionVideo);
    }

    /* access modifiers changed from: package-private */
    public void prepareVideo(final Context context, final Video video) throws JSONException {
        int inferContentType = Util.inferContentType(video.getVideoUri());
        boolean z = inferContentType == 3 && video.getDashManifest() == null && TextUtils.isEmpty(video.getDrmProxyUrl()) && !video.getProvider().equalsIgnoreCase("phone");
        String str = TAG;
        Log.d(str, this.sessionId + " - prepareVideo: url=" + video.getVideoUrl());
        if (z || (video.getDashManifest() instanceof OculusDashManifest) || inferContentType == 2 || inferContentType == 0) {
            this.playbackHandler.post(new Runnable() {
                /* class com.oculus.video.VideoSession.AnonymousClass1 */

                public void run() {
                    VideoSession.this.videoInfoExtractor = new VideoInfoExtractor(video);
                    VideoSession.this.videoInfoExtractor.extract(context, VideoSession.this.playbackHandler, new VideoInfoExtractor.Listener() {
                        /* class com.oculus.video.VideoSession.AnonymousClass1.AnonymousClass1 */

                        @Override // com.oculus.video.VideoInfoExtractor.Listener
                        public void onFinish(boolean z) {
                            VideoSession.this.onVideoPrepared(video, z);
                            VideoSession.this.videoInfoExtractor = null;
                        }
                    });
                }
            });
        } else {
            onVideoPrepared(video, false);
        }
    }

    /* access modifiers changed from: package-private */
    public void startVideo(final VideoPlayer videoPlayer, final boolean z, final Surface surface) {
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass2 */

            public void run() {
                String str = VideoSession.TAG;
                Log.d(str, VideoSession.this.sessionId + " - startVideo: player=" + videoPlayer.getName() + ", playWhenReady=" + z);
                VideoSession.this.playWhenReady = z;
                VideoSession.this.videoSurface = surface;
                VideoSession.this.player = videoPlayer;
                VideoSession.this.needsFallbackPlayer = false;
                VideoSession.this.player.start(VideoSession.this.sessionVideo, z, VideoSession.this.videoSurface, VideoSession.this);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void startVideo(VideoPlayer videoPlayer) {
        startVideo(videoPlayer, this.playWhenReady, this.videoSurface);
    }

    /* access modifiers changed from: package-private */
    public void markForFallback() {
        this.needsFallbackPlayer = true;
    }

    /* access modifiers changed from: package-private */
    public boolean needsFallback() {
        return this.needsFallbackPlayer;
    }

    /* access modifiers changed from: package-private */
    public void stopVideo() {
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass3 */

            public void run() {
                if (VideoSession.this.player != null) {
                    String str = VideoSession.TAG;
                    Log.d(str, VideoSession.this.sessionId + " - stopVideo");
                    VideoSession.this.player.stop();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void pauseVideo() {
        System.currentTimeMillis();
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass4 */

            public void run() {
                if (VideoSession.this.player != null) {
                    String str = VideoSession.TAG;
                    Log.d(str, VideoSession.this.sessionId + " - pauseVideo");
                    VideoSession.this.player.pause();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void resumeVideo() {
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass5 */

            public void run() {
                if (VideoSession.this.player != null) {
                    String str = VideoSession.TAG;
                    Log.d(str, VideoSession.this.sessionId + " - resumeVideo");
                    VideoSession.this.player.resume();
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void seekTo(final long j) {
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass6 */

            public void run() {
                if (VideoSession.this.player != null) {
                    if (j == ((long) VideoSession.this.getPosition())) {
                        VideoSession.this.eventListener.onSeekComplete(VideoSession.this);
                        return;
                    }
                    String str = VideoSession.TAG;
                    Log.d(str, VideoSession.this.sessionId + " - seekTo: position=" + j);
                    VideoSession.this.isSeeking = true;
                    VideoSession.this.player.seekTo(j);
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void setMute(boolean z) {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            videoPlayer.setMute(z);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isMuted() {
        VideoPlayer videoPlayer = this.player;
        return videoPlayer != null && videoPlayer.isMuted();
    }

    /* access modifiers changed from: package-private */
    public void setVolume(float f) {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            videoPlayer.setVolume(f);
        }
    }

    /* access modifiers changed from: package-private */
    public float getVolume() {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            return videoPlayer.getVolume();
        }
        return 0.0f;
    }

    /* access modifiers changed from: package-private */
    public boolean isPlaying() {
        VideoPlayer videoPlayer = this.player;
        return videoPlayer != null && videoPlayer.isPlaying();
    }

    /* access modifiers changed from: package-private */
    public boolean isBuffering() {
        VideoPlayer videoPlayer = this.player;
        return videoPlayer != null && videoPlayer.isBuffering();
    }

    /* access modifiers changed from: package-private */
    public int getBufferedPosition() {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            return (int) videoPlayer.getBufferedPositionMs();
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    public int getPosition() {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            return (int) videoPlayer.getCurrentPositionMs();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public long getEpochTimePosition() {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            return videoPlayer.getCurrentEpochTimePositionMs();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public boolean selectSubtitle(String str) {
        String str2;
        boolean z;
        if (this.player == null) {
            return false;
        }
        String str3 = null;
        if (!TextUtils.isEmpty(str)) {
            JSONObject subtitleTracks = this.sessionVideo.getSubtitleTracks();
            if (subtitleTracks != null) {
                Iterator<String> keys = subtitleTracks.keys();
                while (true) {
                    if (!keys.hasNext()) {
                        break;
                    }
                    try {
                        str2 = keys.next();
                        if (subtitleTracks.getString(str2).equals(str)) {
                            z = true;
                            break;
                        }
                    } catch (JSONException unused) {
                    }
                }
                if (z || TextUtils.isEmpty(str2)) {
                    return false;
                }
                str3 = str2;
            }
            str2 = null;
            z = false;
            if (z) {
            }
            return false;
        }
        Log.d(TAG, this.sessionId + " - selectSubtitle: language=" + str);
        return this.player.selectSubtitle(str3);
    }

    /* access modifiers changed from: package-private */
    public boolean selectVideoTrack(int i) {
        if (this.player == null) {
            return false;
        }
        String str = null;
        if (i == -1) {
            str = "";
        } else {
            JSONObject videoTracks = this.sessionVideo.getVideoTracks();
            if (videoTracks == null || !videoTracks.has(Integer.toString(i))) {
                return false;
            }
            try {
                JSONObject jSONObject = new JSONObject(videoTracks.getString(Integer.toString(i)));
                if (!jSONObject.has("Id")) {
                    return false;
                }
                str = jSONObject.getString("Id");
            } catch (JSONException unused) {
            }
        }
        String str2 = TAG;
        Log.d(str2, this.sessionId + " - select video track: " + str);
        return this.player.selectVideoTrack(str);
    }

    public void setSyncReferenceTimeMs(final long j) {
        final long currentTimeMillis = System.currentTimeMillis();
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass7 */

            public void run() {
                if (VideoSession.this.player instanceof SyncMediaPlayer) {
                    ((SyncMediaPlayer) VideoSession.this.player).setSyncReferenceTimeMs(j, currentTimeMillis);
                }
            }
        });
    }

    public void updateSyncedPlaybackTime(final int i, final String str) {
        this.playbackHandler.post(new Runnable() {
            /* class com.oculus.video.VideoSession.AnonymousClass8 */

            public void run() {
                if (VideoSession.this.player instanceof SyncMediaPlayer) {
                    ((SyncMediaPlayer) VideoSession.this.player).updateSyncedPlaybackTime(i, str);
                }
            }
        });
    }

    public void onBeforeRender(long j) {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            videoPlayer.onBeforeRender(j);
        }
    }

    /* access modifiers changed from: package-private */
    public void onRender(boolean z) {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            videoPlayer.onRender(z);
        }
    }

    public boolean didStartPlayback() {
        return this.didStartPlayback;
    }

    public long getLastPresentationTimeUs() {
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer != null) {
            return videoPlayer.getLastPresentationTimeUs();
        }
        return -1;
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onStartVideo() {
        this.eventListener.onStartVideo(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onReady() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onReady");
        this.didStartPlayback = true;
        this.eventListener.onReady(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onFinish() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onFinish");
        if (this.isSeeking) {
            this.isSeeking = false;
            this.eventListener.onSeekComplete(this);
        }
        this.eventListener.onFinish(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onRelease() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onRelease");
        this.eventListener.onRelease(this);
        this.player = null;
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onBuffering() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onBuffering");
        this.eventListener.onBuffering(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onStopBuffering() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onStopBuffering");
        this.eventListener.onStopBuffering(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onError(Exception exc) {
        String str = TAG;
        Log.d(str, this.sessionId + " - onError", exc);
        if (this.isSeeking) {
            this.isSeeking = false;
            this.eventListener.onSeekComplete(this);
        }
        this.eventListener.onError(this, exc);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onSoftError(Throwable th) {
        String str = TAG;
        Log.d(str, this.sessionId + " - onSoftError", th);
        this.eventListener.onSoftError(this, th);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onSeekProcessed() {
        String str = TAG;
        Log.d(str, this.sessionId + " - onSeekProcessed");
        if (this.isSeeking) {
            this.isSeeking = false;
            this.eventListener.onSeekComplete(this);
            return;
        }
        Log.w(TAG, "Unidentified Seek Processed!");
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onTimelineChanged(long j, long j2, boolean z) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append(this.sessionId);
        sb.append(" - onTimelineChanged: ");
        sb.append("duration=");
        sb.append(j);
        sb.append(", position=");
        sb.append(j2);
        sb.append(", isSeekable=");
        sb.append(z ? "true" : "false");
        Log.d(str, sb.toString());
        this.eventListener.onTimelineChanged(this, j, j2, z);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onVideoFormatChanged(int i, int i2, float f, int i3, VideoProjection videoProjection, float f2, int i4, String str, String str2, double d) {
        String str3 = TAG;
        Log.d(str3, this.sessionId + " - onVideoFormatChanged: width=" + i + ", height=" + i2 + ", pixelWidthHeightRatio=" + f + ", rotationDegrees=" + i3 + ", frameRate=" + f2 + ", bitrate=" + i4 + ", videoMimeType=" + str + ", videoRendererName=" + str2 + ", VQM=" + d);
        this.eventListener.onVideoFormatChanged(this, i, i2, f, i3, videoProjection, f2, i4, str, str2, d);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onAudioFormatChanged(String str, String str2) {
        String str3 = TAG;
        Log.d(str3, this.sessionId + " - onAudioFormatChanged: " + ", audioMimeType=" + str + ", rendererName=" + str2);
        this.eventListener.onAudioFormatChanged(this, str, str2);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onVideoSizeChanged(int i, int i2, float f, Rect rect) {
        String str = TAG;
        Log.d(str, this.sessionId + " - onVideoSizeChanged: " + "width=" + i + ", height=" + i2);
        VideoPlayer videoPlayer = this.player;
        if (videoPlayer instanceof OculusMediaPlayer) {
            OculusMediaPlayer oculusMediaPlayer = (OculusMediaPlayer) videoPlayer;
            this.eventListener.onVideoFormatChanged(this, i, i2, f, oculusMediaPlayer.getRotationDegrees(), this.sessionVideo.getProjectionMetadata(), oculusMediaPlayer.getFrameRate(), -1, this.sessionVideo.getVideoMimeType(), "android.media.MediaPlayer", 0.0d);
            this.eventListener.onAudioFormatChanged(this, this.sessionVideo.getAudioMimeType(), "android.media.MediaPlayer");
        }
        this.eventListener.onVideoSizeChanged(this, i, i2, f, rect);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onCameraMotionData(CameraMotionData cameraMotionData) {
        this.eventListener.onCameraMotionData(this, cameraMotionData);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onStreamingSample(long j, long j2, long j3, long j4) {
        String str = TAG;
        Log.v(str, this.sessionId + " - onStreamingSample: bytesStreamed=" + j + ", bitrate=" + j2 + ", bufferLengthMs=" + j3 + ", elapsedMs=" + j4);
        this.eventListener.onStreamingSample(this, j, j2, j3, j4);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onDroppedFrames(long j, long j2, long j3, long j4) {
        String str = TAG;
        Log.v(str, this.sessionId + " - onDroppedFrames: elapsedTimeMs=" + j + ", delayedCount=" + j2 + ", unconsumedCount=" + j3 + ", decodedFrameCount=" + j4);
        this.eventListener.onDroppedFrames(this, j, j2, j3, j4);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onHttpConnect(String str, String str2, String str3, int i, long j) {
        String str4 = TAG;
        Log.v(str4, this.sessionId + " - onHttpConnect: uri=" + str + ", ipAddress=" + str2 + ", method=" + str3 + ", responseCode=" + i + ", responseTimeMs=" + j);
        this.eventListener.onHttpConnect(this, str, str2, str3, i, j);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onAdaptivePlaylistUpdate(Map<String, Object> map, List<Map<String, Object>> list) {
        this.eventListener.onAdaptivePlaylistUpdate(this, map, list);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void onExtractProjectionMetadata(VideoProjection videoProjection) {
        this.eventListener.onExtractProjectionMetadata(this, videoProjection);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public Surface getSubtitleSurface() {
        return this.eventListener.getSubtitleSurface(this);
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public String getStringPreference(String str) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            return eventListener2.getStringPreference(str);
        }
        return null;
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public String getUserAgent() {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            return eventListener2.getUserAgent();
        }
        return null;
    }

    @Override // com.oculus.video.VideoPlayer.EventListener
    public void setStringPreference(String str, String str2) {
        EventListener eventListener2 = this.eventListener;
        if (eventListener2 != null) {
            eventListener2.setStringPreference(str, str2);
        }
    }

    @NonNull
    public Settings getPlaybackSettings() {
        return this.playerSettings;
    }
}
