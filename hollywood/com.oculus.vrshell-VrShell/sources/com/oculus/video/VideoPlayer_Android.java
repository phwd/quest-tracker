package com.oculus.video;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.HttpResponseCache;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.VideoSession;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.audio.AudioSpatializerController;
import com.oculus.video.metadata.CameraMotionData;
import com.oculus.video.projection.ProjectionType;
import com.oculus.video.projection.VideoProjection;
import com.oculus.video.ui.DebugSurface;
import java.io.IOException;
import java.lang.Thread;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoPlayer_Android implements VideoSession.EventListener {
    private static final String TAG = "VideoPlayer_Android";
    private static final String VIDEO_SDK_DEEFAULT_USER_AGENT = "oculus_video_sdk";
    private static final String VIDEO_SDK_PREFERENCE_NAME = "oculus_video_sdk_pref";
    private static final String VIDEO_UPDATE_REASON_AUDIO_METADATA = "AUDIO_METADATA";
    private static final String VIDEO_UPDATE_REASON_PREPARE = "PREPARE";
    private static final String VIDEO_UPDATE_REASON_TIMELINE_METADATA = "TIMELINE_METADATA";
    private static final String VIDEO_UPDATE_REASON_VIDEO_METADATA = "VIDEO_METADATA";
    private final OculusAudioSpatializerController audioSpatializerController = new OculusAudioSpatializerController();
    private final Context context;
    private DebugSurface debugSurface;
    private Map<String, String> dynamicDebugInfo;
    private long nativePtr = 0;
    private final Settings playerSettings = new Settings();
    private SharedPreferences preferences;
    private Map<String, String> staticDebugInfo;
    private String userAgentString = VIDEO_SDK_DEEFAULT_USER_AGENT;
    private VideoPlayerAnalytics videoPlayerAnalytics;
    private Handler videoPlayerHandler;
    private HandlerThread videoPlayerThread;
    private Map<Long, VideoSession> videoSessions = new HashMap();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native float[] nativeGetHeadOrientationMatrix(long j);

    private native Surface nativeGetSubtitleSurface(long j, long j2);

    private native void nativeOnStartBuffering(long j, long j2);

    private native void nativePlaybackFailed(long j, long j2, Throwable th);

    private native void nativePlaybackFinished(long j, long j2);

    private native void nativePlayerReleased(long j, long j2);

    private native void nativeSeekFinished(long j, long j2);

    private native void nativeSetCameraMotion(long j, long j2, long j3, float[] fArr);

    private native void nativeSetVideoSize(long j, long j2, int i, int i2, float f, int i3, int i4, int i5, int i6);

    private native void nativeUpdateVideo(long j, long j2, String str, String str2);

    public void freeCachedResources() {
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onStartVideo(VideoSession videoSession) {
    }

    public class OculusDataSpec extends DataSpec {
        public final String ipAddress;

        public OculusDataSpec(DataSpec dataSpec) {
            super(dataSpec.uri, dataSpec.postBody, dataSpec.absoluteStreamPosition, dataSpec.position, dataSpec.length, dataSpec.key, dataSpec.flags);
            String str;
            try {
                str = InetAddress.getByName(new URL(dataSpec.uri.toString()).getHost()).getHostAddress();
            } catch (Exception unused) {
                str = "";
            }
            this.ipAddress = str;
        }
    }

    /* access modifiers changed from: private */
    public class OculusAudioSpatializerController implements AudioSpatializerController {
        Video video;

        @Override // com.oculus.video.audio.AudioSpatializerController
        public void onBufferUnderrun(int i) {
        }

        private OculusAudioSpatializerController() {
        }

        @Override // com.oculus.video.audio.AudioSpatializerController
        public float[] getHeadOrientationMatrix() {
            VideoPlayer_Android videoPlayer_Android = VideoPlayer_Android.this;
            return videoPlayer_Android.nativeGetHeadOrientationMatrix(videoPlayer_Android.nativePtr);
        }

        @Override // com.oculus.video.audio.AudioSpatializerController
        public boolean getFocusEnabled() {
            Video video2 = this.video;
            return video2 != null && video2.isSpatialAudioFocusEnabled();
        }

        @Override // com.oculus.video.audio.AudioSpatializerController
        public float getOffFocusLeveldB() {
            Video video2 = this.video;
            if (video2 != null) {
                return (float) video2.getSpatialAudioOffFocusLevel();
            }
            return 0.0f;
        }

        @Override // com.oculus.video.audio.AudioSpatializerController
        public float getFocusWidthDegrees() {
            Video video2 = this.video;
            if (video2 != null) {
                return (float) video2.getSpatialAudioFocusWidthDegrees();
            }
            return 0.0f;
        }
    }

    public VideoPlayer_Android(Context context2, long j) {
        this.context = context2;
        this.nativePtr = j;
        configureVideoPlayerHandler();
        configurePreferences();
        this.videoPlayerAnalytics = new VideoPlayerAnalytics(context2, this.playerSettings, this.videoPlayerHandler);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void configureVideoPlayerHandler() {
        this.videoPlayerThread = new HandlerThread("VideoPlayerThread", 5);
        this.videoPlayerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            /* class com.oculus.video.VideoPlayer_Android.AnonymousClass1 */

            public void uncaughtException(Thread thread, Throwable th) {
                Log.d(VideoPlayer_Android.TAG, "videoPlayerThread.uncaughtException", th);
                try {
                    VideoPlayer_Android.this.videoPlayerThread.quit();
                } catch (Throwable unused) {
                }
                VideoPlayer_Android.this.configureVideoPlayerHandler();
            }
        });
        this.videoPlayerThread.start();
        this.videoPlayerHandler = new Handler(this.videoPlayerThread.getLooper());
    }

    public void shutDown() {
        HttpResponseCache installed = HttpResponseCache.getInstalled();
        if (installed != null) {
            installed.flush();
        }
        this.videoPlayerAnalytics.release();
        this.nativePtr = 0;
        this.videoPlayerThread.quitSafely();
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    public void setUserAgent(String str) {
        if (TextUtils.isEmpty(str)) {
            str = VIDEO_SDK_DEEFAULT_USER_AGENT;
        }
        this.userAgentString = str;
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public String getUserAgent() {
        return this.userAgentString;
    }

    public void setMute(long j, boolean z) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in setMute: " + j);
            return;
        }
        videoSession.setMute(z);
    }

    public boolean isMuted(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.isMuted();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in isMuted: " + j);
        return false;
    }

    public void setVolume(long j, float f) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in setVolume: " + j);
            return;
        }
        videoSession.setVolume(f);
    }

    public float getVolume(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.getVolume();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in getVolume: " + j);
        return 0.0f;
    }

    public void clearSurface(Surface surface) {
        if (surface != null) {
            try {
                Canvas lockCanvas = surface.lockCanvas(null);
                lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
                surface.unlockCanvasAndPost(lockCanvas);
            } catch (Exception unused) {
            }
        }
    }

    public void setDebugSurface(long j, Surface surface) {
        if (surface != null) {
            this.debugSurface = new DebugSurface(surface);
            if (this.staticDebugInfo == null) {
                this.staticDebugInfo = new TreeMap();
            }
            if (this.dynamicDebugInfo == null) {
                this.dynamicDebugInfo = new TreeMap();
            }
            boolean z = !isPlaying(j);
            this.debugSurface.setContent(z ? this.staticDebugInfo : this.dynamicDebugInfo, z);
            return;
        }
        this.debugSurface = null;
    }

    public void prepareVideo(long j, String str) {
        if (this.videoSessions.get(Long.valueOf(j)) != null) {
            String str2 = TAG;
            Log.w(str2, "VideoSession already exists: " + j);
            return;
        }
        try {
            VideoSession videoSession = new VideoSession(j, this.playerSettings, this.videoPlayerHandler, this);
            this.videoSessions.put(Long.valueOf(j), videoSession);
            if (this.staticDebugInfo != null) {
                this.staticDebugInfo.clear();
            }
            if (this.dynamicDebugInfo != null) {
                this.dynamicDebugInfo.clear();
            }
            if (this.debugSurface != null) {
                this.debugSurface.setContent(null, false);
            }
            Video video = new Video(str);
            this.videoPlayerAnalytics.onExtractProjectionMetadata(j, video.getProjectionMetadata());
            videoSession.prepareVideo(this.context, video);
        } catch (JSONException e) {
            nativePlaybackFailed(this.nativePtr, j, e);
        }
    }

    public void startVideo(long j, boolean z, Surface surface) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in startVideo:: " + j);
            return;
        }
        try {
            Video sessionVideo = videoSession.getSessionVideo();
            Settings playbackSettings = videoSession.getPlaybackSettings();
            this.audioSpatializerController.video = sessionVideo;
            videoSession.startVideo(configureOculusPlayer(sessionVideo, playbackSettings), z, surface);
        } catch (Exception e) {
            onError(videoSession, e);
        }
    }

    public void stopVideo(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in stopVideo: " + j);
            return;
        }
        videoSession.stopVideo();
    }

    public void releaseVideo(long j) {
        if (this.videoSessions.get(Long.valueOf(j)) == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in releaseVideo: " + j);
            return;
        }
        Log.d("VideoSession", j + " - deleted");
        this.videoSessions.remove(Long.valueOf(j));
        this.audioSpatializerController.video = null;
    }

    public void pauseVideo(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in pauseVideo: " + j);
            return;
        }
        videoSession.pauseVideo();
    }

    public void resumeVideo(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in resumeVideo: " + j);
            return;
        }
        videoSession.resumeVideo();
    }

    public void seekVideo(long j, int i) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in seekVideo: " + j);
            return;
        }
        videoSession.seekTo((long) i);
    }

    public boolean selectSubtitle(long j, String str) {
        String str2;
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str3 = TAG;
            Log.w(str3, "VideoSession not found in selectSubtitle: " + j);
            return false;
        } else if (!videoSession.selectSubtitle(str)) {
            return false;
        } else {
            try {
                JSONArray subtitleMimeTypes = videoSession.getSessionVideo().getSubtitleMimeTypes();
                VideoPlayerAnalytics videoPlayerAnalytics2 = this.videoPlayerAnalytics;
                if (subtitleMimeTypes == null) {
                    str2 = "";
                } else {
                    str2 = subtitleMimeTypes.join(",");
                }
                videoPlayerAnalytics2.onSubtitleInfo(j, str2, str, videoSession.getSessionVideo().getAvailableSubtitles());
                return true;
            } catch (JSONException unused) {
                Log.w(TAG, "Failed to parse subtitle info");
                return true;
            }
        }
    }

    public boolean selectVideoTrack(long j, int i) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in selectVideoTrack: " + j);
            return false;
        } else if (!videoSession.selectVideoTrack(i)) {
            return false;
        } else {
            JSONObject videoTracks = videoSession.getSessionVideo().getVideoTracks();
            if (videoTracks == null || videoTracks.length() <= 0) {
                return true;
            }
            this.videoPlayerAnalytics.onVideoTrackInfo(videoSession.getSessionId(), i, videoTracks.length());
            return true;
        }
    }

    public void setSyncReferenceTimeMs(long j, long j2) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in setSyncReferenceTimeMs: " + j);
            return;
        }
        videoSession.setSyncReferenceTimeMs(j2);
    }

    public void updateSyncedPlaybackTime(long j, int i, String str) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str2 = TAG;
            Log.w(str2, "VideoSession not found in updateSyncedPlaybackTime: " + j);
            return;
        }
        videoSession.updateSyncedPlaybackTime(i, str);
    }

    public boolean isPlaying(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.isPlaying();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in isPlaying: " + j);
        return false;
    }

    public boolean isBuffering(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.isBuffering();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in isBuffering: " + j);
        return false;
    }

    public int getBufferedPosition(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.getBufferedPosition();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in getBufferedPosition: " + j);
        return -1;
    }

    public int getPosition(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.getPosition();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in getPosition: " + j);
        return 0;
    }

    public long getEpochTimePosition(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.getEpochTimePosition();
        }
        String str = TAG;
        Log.w(str, "VideoSession not found in getEpochTimePosition: " + j);
        return 0;
    }

    public void onBeforeRender(long j, long j2) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in onBeforeRender: " + j);
            return;
        }
        videoSession.onBeforeRender(j2);
    }

    public void onRender(long j, boolean z) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession == null) {
            String str = TAG;
            Log.w(str, "VideoSession not found in onRender: " + j);
            return;
        }
        videoSession.onRender(z);
    }

    public long getLastPresentationTimeUs(long j) {
        VideoSession videoSession = this.videoSessions.get(Long.valueOf(j));
        if (videoSession != null) {
            return videoSession.getLastPresentationTimeUs();
        }
        return -1;
    }

    public void applySetting(String str, boolean z) {
        this.playerSettings.configure(str, z);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public String getStringPreference(String str) {
        return this.preferences.getString(str, "");
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void setStringPreference(String str, String str2) {
        SharedPreferences.Editor edit = this.preferences.edit();
        edit.putString(str, str2);
        edit.apply();
    }

    public Object getVideoPlayerAnalytics() {
        return this.videoPlayerAnalytics;
    }

    private void configurePreferences() {
        this.preferences = this.context.getSharedPreferences(VIDEO_SDK_PREFERENCE_NAME, 0);
    }

    private VideoPlayer configureOculusPlayer(Video video, Settings settings) throws Exception {
        if (!video.canUseExoPlayer()) {
            Log.d(TAG, "Using OculusMediaPlayer");
            freeCachedResources();
            return new OculusMediaPlayer(this.context);
        } else if (video.isSyncMedia()) {
            Log.d(TAG, "Using SyncMediaPlayer");
            freeCachedResources();
            return new SyncMediaPlayer(this.context, this.audioSpatializerController, this.videoPlayerHandler, settings);
        } else {
            Log.d(TAG, "Using OculusExoPlayer");
            if (!this.playerSettings.useDataCache(video)) {
                freeCachedResources();
            }
            return new OculusExoPlayer(this.context, this.audioSpatializerController, this.videoPlayerHandler, settings);
        }
    }

    public static boolean isSideLoaded(String str) {
        Uri parse;
        if (!TextUtils.isEmpty(str) && (parse = Uri.parse(str)) != null && Util.isLocalFileUri(parse)) {
            return true;
        }
        return false;
    }

    private boolean canUseMediaPlayer(Video video) {
        return video.getContentType() == 3 && video.getDrmProxyUrl().isEmpty() && !video.isSyncMedia();
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onVideoPrepared(VideoSession videoSession, boolean z) {
        Video sessionVideo = videoSession.getSessionVideo();
        Map<String, String> map = this.staticDebugInfo;
        if (map != null) {
            map.put("Video Uri", sessionVideo.getVideoUri().getLastPathSegment());
            VideoProjection projectionMetadata = sessionVideo.getProjectionMetadata();
            this.staticDebugInfo.put("Video Stereo Mode", projectionMetadata.getStereoModeString());
            this.staticDebugInfo.put("Video Projection", projectionMetadata.getProjectionType().videoLayout);
        }
        try {
            JSONArray subtitleMimeTypes = sessionVideo.getSubtitleMimeTypes();
            if (subtitleMimeTypes != null && subtitleMimeTypes.length() > 0) {
                this.videoPlayerAnalytics.onSubtitleInfo(videoSession.getSessionId(), subtitleMimeTypes.join(","), "", sessionVideo.getAvailableSubtitles());
            }
        } catch (JSONException unused) {
            Log.w(TAG, "Failed to log subtitle info");
        }
        JSONObject videoTracks = sessionVideo.getVideoTracks();
        if (videoTracks != null && videoTracks.length() > 0) {
            this.videoPlayerAnalytics.onVideoTrackInfo(videoSession.getSessionId(), -1, videoTracks.length());
        }
        nativeUpdateVideo(this.nativePtr, videoSession.getSessionId(), z ? sessionVideo.toString() : null, VIDEO_UPDATE_REASON_PREPARE);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onReady(VideoSession videoSession) {
        if (this.debugSurface != null) {
            boolean z = !videoSession.isPlaying();
            this.debugSurface.setContent(z ? this.staticDebugInfo : this.dynamicDebugInfo, z);
        }
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onFinish(VideoSession videoSession) {
        nativePlaybackFinished(this.nativePtr, videoSession.getSessionId());
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onRelease(VideoSession videoSession) {
        if (videoSession.needsFallback()) {
            Log.v(TAG, "Movie playback failed with ExoPlayer, attempting to play with OculusMediaPlayer");
            videoSession.startVideo(new OculusMediaPlayer(this.context));
            return;
        }
        nativePlayerReleased(this.nativePtr, videoSession.getSessionId());
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onBuffering(VideoSession videoSession) {
        nativeOnStartBuffering(this.nativePtr, videoSession.getSessionId());
        this.videoPlayerAnalytics.onBuffering(videoSession.getSessionId());
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onStopBuffering(VideoSession videoSession) {
        this.videoPlayerAnalytics.onStopBuffering(videoSession.getSessionId());
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onSeekComplete(VideoSession videoSession) {
        nativeSeekFinished(this.nativePtr, videoSession.getSessionId());
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onError(VideoSession videoSession, Exception exc) {
        HttpDataSource.HttpDataSourceException httpDataSourceException;
        if (!(exc instanceof ExoPlaybackException) || !(exc.getCause() instanceof HttpDataSource.HttpDataSourceException)) {
            httpDataSourceException = exc;
        } else {
            HttpDataSource.HttpDataSourceException httpDataSourceException2 = (HttpDataSource.HttpDataSourceException) exc.getCause();
            httpDataSourceException = new HttpDataSource.HttpDataSourceException(httpDataSourceException2.getMessage(), httpDataSourceException2.getCause() instanceof IOException ? (IOException) httpDataSourceException2.getCause() : null, new OculusDataSpec(httpDataSourceException2.dataSpec), httpDataSourceException2.type);
        }
        if (!canUseMediaPlayer(videoSession.getSessionVideo()) || (videoSession.getPlayer() instanceof OculusMediaPlayer)) {
            nativePlaybackFailed(this.nativePtr, videoSession.getSessionId(), httpDataSourceException);
            return;
        }
        freeCachedResources();
        videoSession.markForFallback();
        onSoftError(videoSession, httpDataSourceException);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onSoftError(VideoSession videoSession, Throwable th) {
        this.videoPlayerAnalytics.onSoftError(videoSession.getSessionId(), th);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onTimelineChanged(VideoSession videoSession, long j, long j2, boolean z) {
        Video sessionVideo = videoSession.getSessionVideo();
        if (sessionVideo != null) {
            try {
                sessionVideo.setDuration(j).setIsSeekable(z);
            } catch (JSONException unused) {
            }
            nativeUpdateVideo(this.nativePtr, videoSession.getSessionId(), sessionVideo.toString(), VIDEO_UPDATE_REASON_TIMELINE_METADATA);
            Map<String, String> map = this.staticDebugInfo;
            if (map != null) {
                map.put("Duration (ms)", Long.toString(j));
                this.staticDebugInfo.put("Is Seekable", Boolean.toString(z));
            }
        }
    }

    private void apply5k360HevcMkvRotationHack(Video video) throws JSONException {
        if (video.getVideoUri() != null) {
            String lastPathSegment = video.getVideoUri().getLastPathSegment();
            if (!TextUtils.isEmpty(lastPathSegment) && lastPathSegment.toLowerCase().endsWith("mkv") && video.getVideoMimeType().equalsIgnoreCase(MimeTypes.VIDEO_H265) && video.getInitialHeight() > 4096 && video.getFrameAspectRatio() <= 0.6666667f && video.getProjectionMetadata().getProjectionType() != ProjectionType.UNKNOWN && video.getProjectionMetadata().getProjectionType() != ProjectionType.RECT && video.getFovX() >= 270.0d) {
                video.setFrameRotation(270);
            }
        }
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onVideoFormatChanged(VideoSession videoSession, int i, int i2, float f, int i3, VideoProjection videoProjection, float f2, int i4, String str, String str2, double d) {
        Video sessionVideo = videoSession.getSessionVideo();
        if (sessionVideo != null) {
            if (!videoSession.didStartPlayback()) {
                try {
                    try {
                        sessionVideo.setInitialWidth(i).setInitialHeight(i2).setFrameRotation(i3).setFrameAspectRatio((((float) i) * f) / ((float) i2)).setFrameRate(f2).setVideoMimeType(str).setVideoRendererName(str2).setProjectionMetadata(videoProjection);
                        apply5k360HevcMkvRotationHack(sessionVideo);
                    } catch (JSONException unused) {
                    }
                } catch (JSONException unused2) {
                }
                this.videoPlayerAnalytics.onSelectProjectionMetadata(videoSession.getSessionId(), sessionVideo.getProjectionMetadata());
                nativeUpdateVideo(this.nativePtr, videoSession.getSessionId(), sessionVideo.toString(), VIDEO_UPDATE_REASON_VIDEO_METADATA);
            }
            this.videoPlayerAnalytics.onVideoFormatChanged(videoSession.getSessionId(), f2, i4, d);
            Map<String, String> map = this.staticDebugInfo;
            if (map != null) {
                map.put("Size Width", Integer.toString(i));
                this.staticDebugInfo.put("Size Height", Integer.toString(i2));
                this.staticDebugInfo.put("Frame Rate", Float.toString(f2));
                this.staticDebugInfo.put("Video Mime", sessionVideo.getVideoMimeType());
                this.staticDebugInfo.put("Video Renderer", str2);
                this.staticDebugInfo.put("Video Quality Metric", Double.toString(d));
            }
        }
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onAudioFormatChanged(VideoSession videoSession, String str, String str2) {
        Video sessionVideo = videoSession.getSessionVideo();
        if (sessionVideo != null) {
            int i = 0;
            JSONObject audioTracks = sessionVideo.getAudioTracks();
            if (audioTracks != null) {
                i = audioTracks.length();
            }
            AudioChannelLayout audioChannelLayout = sessionVideo.getAudioChannelLayout();
            if (!videoSession.didStartPlayback()) {
                try {
                    sessionVideo.setAudioMimeType(str).setAudioRendererName(str2).setAudioChannelLayout(audioChannelLayout);
                } catch (JSONException unused) {
                }
                nativeUpdateVideo(this.nativePtr, videoSession.getSessionId(), sessionVideo.toString(), VIDEO_UPDATE_REASON_AUDIO_METADATA);
            }
            Map<String, String> map = this.staticDebugInfo;
            if (map != null) {
                map.put("Audio Mime", str);
                this.staticDebugInfo.put("Audio Renderer", str2.substring(str2.lastIndexOf(46) + 1));
                this.staticDebugInfo.put("Audio Tracks", Integer.toString(i));
                this.staticDebugInfo.put("Audio Layout", audioChannelLayout.key);
            }
        }
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onVideoSizeChanged(VideoSession videoSession, int i, int i2, float f, Rect rect) {
        Map<String, String> map = this.staticDebugInfo;
        if (map != null) {
            map.put("Size Width", Integer.toString(i));
            this.staticDebugInfo.put("Size Height", Integer.toString(i2));
        }
        Rect rect2 = rect == null ? new Rect() : rect;
        nativeSetVideoSize(this.nativePtr, videoSession.getSessionId(), i, i2, f, rect2.top, rect2.left, rect2.bottom, rect2.right);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onCameraMotionData(VideoSession videoSession, CameraMotionData cameraMotionData) {
        nativeSetCameraMotion(this.nativePtr, videoSession.getSessionId(), cameraMotionData.timeUs, cameraMotionData.data);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onStreamingSample(VideoSession videoSession, long j, long j2, long j3, long j4) {
        if (videoSession.getPlayer() instanceof SyncMediaPlayer) {
            this.videoPlayerAnalytics.onSyncDelayUpdate(videoSession.getSessionId(), ((SyncMediaPlayer) videoSession.getPlayer()).getSyncDelayMs());
        }
        this.videoPlayerAnalytics.onStreamingSample(videoSession.getSessionId(), j, j2, j3, j4);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onDroppedFrames(VideoSession videoSession, long j, long j2, long j3, long j4) {
        this.videoPlayerAnalytics.onDroppedFrames(videoSession.getSessionId(), j, j2, j3, j4);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onHttpConnect(VideoSession videoSession, String str, String str2, String str3, int i, long j) {
        this.videoPlayerAnalytics.onHttpConnect(videoSession.getSessionId(), str, str2, str3, i, j);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onAdaptivePlaylistUpdate(VideoSession videoSession, Map<String, Object> map, List<Map<String, Object>> list) {
        this.videoPlayerAnalytics.onAdaptivePlaylistUpdate(videoSession.getSessionId(), map, list);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public void onExtractProjectionMetadata(VideoSession videoSession, VideoProjection videoProjection) {
        this.videoPlayerAnalytics.onExtractProjectionMetadata(videoSession.getSessionId(), videoProjection);
    }

    @Override // com.oculus.video.VideoSession.EventListener
    public Surface getSubtitleSurface(VideoSession videoSession) {
        return nativeGetSubtitleSurface(this.nativePtr, videoSession.getSessionId());
    }
}
