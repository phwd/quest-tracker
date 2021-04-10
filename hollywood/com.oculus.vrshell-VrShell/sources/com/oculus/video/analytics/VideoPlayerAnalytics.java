package com.oculus.video.analytics;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.Settings;
import com.oculus.video.SyncMediaPlayer;
import com.oculus.video.VideoPlayer_Android;
import com.oculus.video.projection.ProjectionType;
import com.oculus.video.projection.VideoProjection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class VideoPlayerAnalytics {
    private static final String EVENT_NAME = "oculus_video_sdk_playback_event";
    private static final String EVENT_TYPE_ON_DROPPED_FRAMES = "on_dropped_frames";
    private static final String EVENT_TYPE_ON_EXTRACT_AUDIO = "on_extract_audio";
    private static final String EVENT_TYPE_ON_EXTRACT_VIDEO = "on_extract_video";
    private static final String EVENT_TYPE_ON_FATAL_ERROR = "on_fatal_error";
    private static final String EVENT_TYPE_ON_SOFT_ERROR = "on_soft_error";
    private static final String EVENT_TYPE_ON_START_PLAYBACK = "on_start_playback";
    private static final String EVENT_TYPE_ON_START_SESSION = "on_start_session";
    private static final String EVENT_TYPE_ON_STOP_SESSION = "on_stop_session";
    private static final String EVENT_TYPE_ON_STREAMING_UPDATE = "on_streaming_update";
    private static final String EVENT_TYPE_ON_VIDEO_SIZE_CHANGED = "on_video_size_changed";
    private static final String EXTRA_ADAPTIVE_PLAYLIST = "adaptive_playlist";
    private static final String EXTRA_AUDIO_CHANNEL_LAYOUT = "audio_channel_layout";
    private static final String EXTRA_AUDIO_MIME_TYPE = "audio_mime_type";
    private static final String EXTRA_AUDIO_RENDERER_NAME = "audio_renderer_name";
    private static final String EXTRA_AUDIO_TRACK_COUNT = "audio_track_count";
    private static final String EXTRA_BOARD = "board";
    private static final String EXTRA_CHANNEL = "channel";
    private static final String EXTRA_CLIENT_DATA = "client_data";
    private static final String EXTRA_CONTENT_TYPE = "content_type";
    private static final String EXTRA_DECODED_FRAMES_COUNT = "decoded_frames_count";
    private static final String EXTRA_DELAYED_DROPPED_FRAMES_COUNT = "dropped_delayed_frames_count";
    private static final String EXTRA_DID_EXTRACT_AUDIO = "did_extract_audio";
    private static final String EXTRA_DID_EXTRACT_VIDEO = "did_extract_video";
    private static final String EXTRA_DID_START_PLAYBACK = "did_start_playback";
    private static final String EXTRA_DROPPED_FRAMES_COUNT = "dropped_frames_count";
    private static final String EXTRA_DURATION_MS = "duration_ms";
    private static final String EXTRA_EFFECTIVE_FPS = "effective_fps";
    private static final String EXTRA_ELAPSED_TIME_MS = "elapsed_time_ms";
    private static final String EXTRA_EVENT_TYPE = "event_type";
    private static final String EXTRA_EXCEPTION = "exception";
    private static final String EXTRA_EXCEPTION_STACK = "exception_stack";
    private static final String EXTRA_FATAL_ERROR_MESSAGE = "fatal_error_message";
    private static final String EXTRA_FILE_EXTENSION = "file_extension";
    private static final String EXTRA_FOV_X = "FOV_X";
    private static final String EXTRA_FOV_Y = "FOV_Y";
    private static final String EXTRA_FPS = "fps";
    private static final String EXTRA_HEIGHT = "height";
    private static final String EXTRA_HTTP_METHOD = "http_method";
    private static final String EXTRA_HTTP_RESPONSE_CODE = "http_response_code";
    private static final String EXTRA_HTTP_URL = "http_url";
    private static final String EXTRA_HVQ_PERCENTAGE = "hvq_percentage";
    private static final String EXTRA_IS_3D = "is_3D";
    private static final String EXTRA_IS_SEEKABLE = "is_seekable";
    private static final String EXTRA_IS_SYNC_MEDIA = "is_sync_media";
    private static final String EXTRA_LOAD_DURATION_MS = "load_duration_ms";
    private static final String EXTRA_MAX_POSITION_MS = "max_position_ms";
    private static final String EXTRA_MOST_USED_SUBTITLE = "most_used_subtitle";
    private static final String EXTRA_OVR_USER_ID = "ovr_user_id";
    private static final String EXTRA_PAUSED_DURATION_MS = "paused_duration_ms";
    private static final String EXTRA_PLAYBACK_DURATION_MS = "playback_duration_ms";
    private static final String EXTRA_PLAYBACK_PERCENTAGE = "playback_percentage";
    private static final String EXTRA_POSITION_MS = "position_ms";
    private static final String EXTRA_POWER_CONSUMED_MAH = "power_consumed_mAh";
    private static final String EXTRA_PROJECTION = "projection";
    private static final String EXTRA_PROVIDER = "provider";
    private static final String EXTRA_REMOTE_IP_ADDRESS = "remote_ip_address";
    private static final String EXTRA_SESSION_DURATION_MS = "session_duration_ms";
    private static final String EXTRA_SESSION_ID = "session_ID";
    private static final String EXTRA_SETTINGS = "gatekeepers";
    private static final String EXTRA_SPHERICAL_METADATA = "spherical_metadata";
    private static final String EXTRA_STREAMED_BYTES_COUNT = "streamed_bytes_count";
    private static final String EXTRA_SUBTITLE_LIST = "subtitle_list";
    private static final String EXTRA_SUBTITLE_MIME_TYPE = "subtitle_mime_type";
    private static final String EXTRA_SYNC_PLAYBACK_DELAY_MS = "sync_playback_delay_ms";
    private static final String EXTRA_TIME_FROM_LAUNCH_MS = "time_from_launch_ms";
    private static final String EXTRA_UNCONSUMED_DROPPED_FRAMES_COUNT = "dropped_unconsumed_frames_count";
    private static final String EXTRA_UNLOAD_DURATION_MS = "unload_duration_ms";
    private static final String EXTRA_URL = "url";
    private static final String EXTRA_VIDEO_BITRATE = "video_bitrate";
    private static final String EXTRA_VIDEO_ID = "video_id";
    private static final String EXTRA_VIDEO_MIME_TYPE = "video_mime_type";
    private static final String EXTRA_VIDEO_RENDERER_NAME = "video_renderer_name";
    private static final String EXTRA_VIDEO_TRACK_COUNT = "video_track_count";
    private static final String EXTRA_VIDEO_TRACK_SELECTION_DATA = "video_track_selection_data";
    private static final String EXTRA_WIDTH = "width";
    private static final int GO_360_MONO_HQ_WIDTH_THRES = 5120;
    private static final int MAX_CLIENT_DATA_LENGTH = 1024;
    private static final int MAX_LOG_LINE_LENGTH = 1000;
    private static final long MIN_DEFAULT_TIME_BETWEEN_HEAVY_EVENTS_MS = 150000;
    private static final long MIN_DEFAULT_TIME_BETWEEN_LIGHT_EVENTS_MS = 15000;
    private static final int MIN_DROPPED_FRAMES_TO_LOG = 50;
    private static final long MIN_SYNC_PLAYER_TIME_BETWEEN_HEAVY_EVENTS_MS = 30000;
    private static final long ONGOING_PLAYBACK = -1;
    private static final String PLAYLIST_ALL_PROFILES = "playlist";
    public static final String PLAYLIST_DASH_IS_DYNAMIC = "dash_is_dynamic";
    public static final String PLAYLIST_HLS_TYPE = "hls_type";
    public static final String PLAYLIST_PROFILE_DASH_MIN_BUFFER_TIME_MS = "dash_min_buffer_time_ms";
    public static final String PLAYLIST_PROFILE_DASH_MIN_UPDATE_PERIOD_MS = "dash_min_update_period_ms";
    public static final String PLAYLIST_PROFILE_DASH_SUGGESTED_PRESENTATION_DELAY_MS = "dash_suggested_presentation_delay_ms";
    public static final String PLAYLIST_PROFILE_DASH_TIME_SHIFT_BUFFER_DEPTH_MS = "dash_time_shift_buffer_depth_ms";
    public static final String PLAYLIST_PROFILE_HAS_EMBEDDED_IV = "has_embedded_iv";
    public static final String PLAYLIST_PROFILE_HAS_EMBEDDED_KEY = "has_embedded_key";
    public static final String PLAYLIST_PROFILE_HAS_EMBEDDED_UTC = "has_embedded_utc";
    public static final String PLAYLIST_PROFILE_HAS_REMOTE_IV = "has_remote_iv";
    public static final String PLAYLIST_PROFILE_HAS_REMOTE_KEY = "has_remote_key";
    public static final String PLAYLIST_PROFILE_ITEM_BITRATE = "bitrate";
    public static final String PLAYLIST_PROFILE_ITEM_BITRATE_INDEX = "bitrate_index";
    public static final String PLAYLIST_PROFILE_ITEM_FPS = "fps";
    public static final String PLAYLIST_PROFILE_ITEM_HEIGHT = "height";
    public static final String PLAYLIST_PROFILE_ITEM_TARGET_DURATION_US = "target_duration_us";
    public static final String PLAYLIST_PROFILE_ITEM_WIDTH = "width";
    private static final String PLAYLIST_PROFILE_UPDATE_COUNT = "update_count";
    private static final String PLAYLIST_PROFILE_UPDATE_INTERVAL_MS = "update_interval_ms";
    public static final String PLAYLIST_SEGMENT_COUNT = "segment_count";
    private static final String PLAYLIST_SELECTED_PROFILE = "selected";
    private static final int QUEST_360_MONO_HQ_WIDTH_THRES = 6000;
    private static final String REDACTED_URL = "REDACTED";
    private static final String TAG = "VideoPlayerAnalytics";
    private final Context context;
    private long creationTimeMs;
    private final Handler eventHandler;
    private long nativePtr;
    private String ovrUserID;
    private final Settings playerSettings;
    private final PowerMonitor powerMonitor;
    private final Map<Long, VideoAnalyticsSession> sessions = new HashMap();
    private String settings;

    /* access modifiers changed from: package-private */
    public native void nativeReportAnalyticsEvent(long j, String str, boolean z);

    /* access modifiers changed from: private */
    public enum VideoSessionEvent {
        START_SESSION("start_session"),
        VIDEO_SIZE_CHANGED("video_size_changed"),
        RENDER_RESTART("render_restart"),
        SEEK_START("seek_start"),
        PAUSE("pause"),
        RESUME("resume"),
        STALL("stall"),
        COMPLETE_SESSION("complete_session");
        
        final String value;

        private VideoSessionEvent(String str) {
            this.value = str;
        }

        public String toString() {
            return this.value;
        }
    }

    /* access modifiers changed from: package-private */
    public static class ValueComparator implements Comparator<Integer> {
        Map<Integer, Long> base;

        public ValueComparator(Map<Integer, Long> map) {
            this.base = map;
        }

        public int compare(Integer num, Integer num2) {
            return this.base.get(num).longValue() >= this.base.get(num2).longValue() ? -1 : 1;
        }
    }

    public VideoPlayerAnalytics(@NonNull Context context2, @NonNull Settings settings2, @NonNull Handler handler) {
        this.context = context2;
        this.playerSettings = settings2;
        this.eventHandler = handler;
        this.powerMonitor = new PowerMonitor(context2, handler);
        this.creationTimeMs = getCurrentTimeMs();
    }

    public void release() {
        this.powerMonitor.release();
    }

    public void setNativePtr(long j) {
        this.nativePtr = j;
    }

    public void setOvrUserId(String str) {
        this.ovrUserID = str;
    }

    public void reportSettings(String str) {
        this.settings = str;
    }

    public long getSurfaceTextureTimestamp(Object obj) {
        if (obj instanceof SurfaceTexture) {
            return ((SurfaceTexture) obj).getTimestamp();
        }
        return 0;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addErrorDataToEvent(Throwable th, Settings settings2, String str, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        if (th != null) {
            StringBuilder sb = new StringBuilder();
            for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
                if (th2 != th) {
                    sb.append("Caused by: ");
                }
                sb.append(th2.getClass().getName());
                sb.append(": ");
                sb.append(th2.getMessage());
                sb.append("\n");
                StackTraceElement[] stackTrace = th2.getStackTrace();
                for (StackTraceElement stackTraceElement : stackTrace) {
                    sb.append("  at ");
                    sb.append(stackTraceElement.getClassName());
                    sb.append('.');
                    sb.append(stackTraceElement.getMethodName());
                    sb.append('(');
                    sb.append(stackTraceElement.getFileName());
                    sb.append(':');
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append(")\n");
                }
            }
            String message = th.getMessage();
            ExoPlaybackException exoPlaybackException = th instanceof Exception ? (Exception) th : null;
            if (th instanceof ExoPlaybackException) {
                ExoPlaybackException exoPlaybackException2 = (ExoPlaybackException) th;
                int i = exoPlaybackException2.type;
                if (i == 0) {
                    exoPlaybackException = exoPlaybackException2.getSourceException();
                } else if (i != 1) {
                    exoPlaybackException = exoPlaybackException2;
                    if (i == 2) {
                        exoPlaybackException = exoPlaybackException2.getUnexpectedException();
                    }
                } else {
                    exoPlaybackException = exoPlaybackException2.getRendererException();
                }
            }
            if (exoPlaybackException != null) {
                String message2 = exoPlaybackException.getMessage();
                if (exoPlaybackException instanceof HttpDataSource.HttpDataSourceException) {
                    if (exoPlaybackException.getCause() != null) {
                        message2 = exoPlaybackException.getCause().getMessage();
                    }
                    DataSpec dataSpec = ((HttpDataSource.HttpDataSourceException) exoPlaybackException).dataSpec;
                    Object uri = dataSpec.uri.toString();
                    if (!settings2.redactAnalyticsVideoSource(str)) {
                        videoPlayerAnalyticsLogEvent.put(EXTRA_HTTP_URL, uri);
                        if (dataSpec instanceof VideoPlayer_Android.OculusDataSpec) {
                            String str2 = ((VideoPlayer_Android.OculusDataSpec) dataSpec).ipAddress;
                            if (!TextUtils.isEmpty(str2)) {
                                videoPlayerAnalyticsLogEvent.put(EXTRA_REMOTE_IP_ADDRESS, str2);
                            }
                        }
                    }
                }
                message = message2;
                if (TextUtils.isEmpty(message)) {
                    message = exoPlaybackException.getClass().getSimpleName();
                }
            }
            videoPlayerAnalyticsLogEvent.put("exception", message).put(EXTRA_EXCEPTION_STACK, sb.toString());
        }
    }

    private void maybeLogFatalError(VideoAnalyticsSession videoAnalyticsSession, long j, Throwable th, long j2) {
        if (th != null) {
            videoAnalyticsSession.fatalErrorMessage = th.toString();
            try {
                VideoPlayerAnalyticsLogEvent newEvent = newEvent(videoAnalyticsSession, EVENT_TYPE_ON_FATAL_ERROR, j2);
                addErrorDataToEvent(th, videoAnalyticsSession.playbackSettings, videoAnalyticsSession.provider, newEvent);
                reportEvent(newEvent);
            } catch (JSONException e) {
                Log.w(TAG, "Exception when log fatal error: " + e.getMessage());
            }
        }
    }

    public void onSoftError(final long j, final Throwable th) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass1 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onSoftError: Session " + j + " not found!");
                } else if (VideoPlayerAnalytics.canLogEvent(videoAnalyticsSession, VideoPlayerAnalytics.EVENT_TYPE_ON_SOFT_ERROR)) {
                    try {
                        VideoPlayerAnalyticsLogEvent newEvent = VideoPlayerAnalytics.this.newEvent(videoAnalyticsSession, VideoPlayerAnalytics.EVENT_TYPE_ON_SOFT_ERROR, currentTimeMs);
                        VideoPlayerAnalytics.this.addErrorDataToEvent(th, videoAnalyticsSession.playbackSettings, videoAnalyticsSession.provider, newEvent);
                        VideoPlayerAnalytics.this.reportEvent(newEvent);
                    } catch (JSONException e) {
                        Log.w(VideoPlayerAnalytics.TAG, "Exception when log soft error: " + e.getMessage());
                    }
                }
            }
        });
    }

    public void onStartSession(final long j, final String str, final boolean z, final String str2, final float f, final float f2, final String str3, final String str4, final String str5, final String str6) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass2 */

            public void run() {
                String str;
                String str2;
                int i;
                String str3;
                if (((VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j))) != null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onStartSession: Session " + j + " already started!");
                    return;
                }
                VideoAnalyticsSession videoAnalyticsSession = new VideoAnalyticsSession(VideoPlayerAnalytics.this.playerSettings);
                videoAnalyticsSession.ID = UUID.randomUUID().toString();
                VideoPlayerAnalytics.sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.START_SESSION, currentTimeMs);
                videoAnalyticsSession.videoStartTimeMs = currentTimeMs;
                VideoPlayerAnalytics.this.sessions.put(Long.valueOf(j), videoAnalyticsSession);
                Log.i(VideoPlayerAnalytics.TAG, "Starting session. ID=" + videoAnalyticsSession.ID);
                boolean redactAnalyticsVideoSource = videoAnalyticsSession.playbackSettings.redactAnalyticsVideoSource(str5);
                if (redactAnalyticsVideoSource) {
                    str = VideoPlayerAnalytics.REDACTED_URL;
                } else {
                    str = str;
                }
                if (redactAnalyticsVideoSource) {
                    str2 = "0";
                } else {
                    str2 = str3;
                }
                String guessFileName = URLUtil.guessFileName(str, null, null);
                if (guessFileName == null) {
                    i = -1;
                } else {
                    i = guessFileName.lastIndexOf(".");
                }
                if (i != -1) {
                    str3 = guessFileName.substring(i + 1);
                } else {
                    str3 = "";
                }
                String lowerCase = str3.toLowerCase();
                String substring = lowerCase.substring(Math.max(lowerCase.length() - 5, 0));
                videoAnalyticsSession.url = str;
                int inferContentType = Util.inferContentType(str);
                if (inferContentType == 0) {
                    videoAnalyticsSession.contentType = "DASH";
                } else if (inferContentType == 1) {
                    videoAnalyticsSession.contentType = "SS";
                } else if (inferContentType == 2) {
                    videoAnalyticsSession.contentType = "HLS";
                } else if (z) {
                    videoAnalyticsSession.contentType = "DASH";
                } else if (!TextUtils.isEmpty(str)) {
                    Uri parse = Uri.parse(str);
                    videoAnalyticsSession.contentType = (Util.isLocalFileUri(parse) ? "file" : parse.getScheme()).toUpperCase();
                } else {
                    videoAnalyticsSession.contentType = "";
                }
                videoAnalyticsSession.fileExtension = substring;
                videoAnalyticsSession.projection = str2;
                videoAnalyticsSession.fovX = f;
                videoAnalyticsSession.fovY = f2;
                videoAnalyticsSession.videoID = str2;
                videoAnalyticsSession.channel = str4;
                videoAnalyticsSession.provider = str5;
                videoAnalyticsSession.clientAnalyticsData = str6;
                try {
                    VideoPlayerAnalytics.this.reportEvent(VideoPlayerAnalytics.this.newEvent(videoAnalyticsSession, VideoPlayerAnalytics.EVENT_TYPE_ON_START_SESSION, currentTimeMs));
                } catch (JSONException e) {
                    Log.w(VideoPlayerAnalytics.TAG, "Exception when log start session: " + e.getMessage());
                }
                videoAnalyticsSession.startingChargeDrainedMah = VideoPlayerAnalytics.this.powerMonitor.getChargeDrainedMah();
            }
        });
    }

    public void onTimelineChanged(final long j, final long j2, final long j3, final boolean z) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass3 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onTimelineChanged: Session " + j + " not found!");
                    return;
                }
                if (!videoAnalyticsSession.didExtractVideo) {
                    VideoPlayerAnalytics.this.onExtractTimeline((VideoPlayerAnalytics) videoAnalyticsSession, (VideoAnalyticsSession) j2, (long) z);
                }
                if (videoAnalyticsSession.selectedProfile != null) {
                    videoAnalyticsSession.selectedProfileWithTimeline = videoAnalyticsSession.selectedProfile;
                }
                videoAnalyticsSession.selectedProfileWithTimeline.put(VideoPlayerAnalytics.EXTRA_DURATION_MS, Long.valueOf(j2));
                videoAnalyticsSession.selectedProfileWithTimeline.put(VideoPlayerAnalytics.EXTRA_POSITION_MS, Long.valueOf(j3));
            }
        });
    }

    public void onExtractTimeline(final long j, final long j2, final boolean z) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass4 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onExtractVideo: Session " + j + " not found!");
                } else if (!videoAnalyticsSession.didExtractVideo) {
                    VideoPlayerAnalytics.this.onExtractTimeline((VideoPlayerAnalytics) videoAnalyticsSession, (VideoAnalyticsSession) j2, (long) z);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onExtractTimeline(VideoAnalyticsSession videoAnalyticsSession, long j, boolean z) {
        Assertions.checkState(!videoAnalyticsSession.didExtractVideo);
        videoAnalyticsSession.durationMs = j;
        videoAnalyticsSession.isSeekable = z;
    }

    public void onVideoFormatChanged(final long j, final float f, final int i, final double d) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass5 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onVideoFormatChanged: Session " + j + " not found!");
                    return;
                }
                VideoPlayerAnalytics.sampleLastVideoFormat(videoAnalyticsSession, currentTimeMs);
                videoAnalyticsSession.fps = f;
                int i = i;
                if (i <= 0) {
                    i = 0;
                }
                videoAnalyticsSession.videoBitrate = i;
                videoAnalyticsSession.vqm = d;
                VideoPlayerAnalytics.this.maybeLogStreamingSampleEvent(videoAnalyticsSession, true, currentTimeMs);
            }
        });
    }

    public void onExtractVideo(final long j, final int i, final int i2, final float f, final boolean z, final boolean z2, final String str, final float f2, final float f3, final String str2, final String str3) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass6 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onExtractVideo: Session " + j + " not found!");
                    return;
                }
                VideoPlayerAnalytics.sampleLastVideoFormat(videoAnalyticsSession, currentTimeMs);
                if (!videoAnalyticsSession.didExtractVideo) {
                    VideoPlayerAnalytics.this.onExtractVideo(videoAnalyticsSession, i, i2, f, z, Boolean.valueOf(z2), str, f2, f3, str2, str3, currentTimeMs);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onExtractVideo(VideoAnalyticsSession videoAnalyticsSession, int i, int i2, float f, boolean z, Boolean bool, String str, float f2, float f3, String str2, String str3, long j) {
        String str4;
        Assertions.checkState(!videoAnalyticsSession.didExtractVideo);
        videoAnalyticsSession.didExtractVideo = true;
        videoAnalyticsSession.width = i;
        videoAnalyticsSession.height = i2;
        videoAnalyticsSession.fps = f;
        videoAnalyticsSession.is3D = z;
        videoAnalyticsSession.isSyncMedia = bool.booleanValue();
        if (TextUtils.isEmpty(str)) {
            str4 = videoAnalyticsSession.projection;
        } else {
            str4 = str.toUpperCase();
        }
        videoAnalyticsSession.projection = str4;
        if (f2 <= 0.0f) {
            f2 = videoAnalyticsSession.fovX;
        }
        videoAnalyticsSession.fovX = f2;
        if (f3 <= 0.0f) {
            f3 = videoAnalyticsSession.fovY;
        }
        videoAnalyticsSession.fovY = f3;
        videoAnalyticsSession.videoMimeType = str2;
        videoAnalyticsSession.videoRendererName = str3;
        try {
            reportEvent(newEvent(videoAnalyticsSession, EVENT_TYPE_ON_EXTRACT_VIDEO, j));
        } catch (JSONException e) {
            Log.w(TAG, "Exception when log extract video: " + e.getMessage());
        }
    }

    public void onExtractAudio(final long j, final String str, final String str2, final int i, final String str3) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass7 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onExtractAudio: Session " + j + " not found!");
                } else if (!videoAnalyticsSession.didExtractAudio) {
                    videoAnalyticsSession.didExtractAudio = true;
                    videoAnalyticsSession.audioMimeType = str;
                    videoAnalyticsSession.audioRendererName = str2;
                    videoAnalyticsSession.audioTrackCount = i;
                    videoAnalyticsSession.audioChannelLayout = str3;
                    try {
                        VideoPlayerAnalytics.this.reportEvent(VideoPlayerAnalytics.this.newEvent(videoAnalyticsSession, VideoPlayerAnalytics.EVENT_TYPE_ON_EXTRACT_AUDIO, currentTimeMs));
                    } catch (JSONException e) {
                        Log.w(VideoPlayerAnalytics.TAG, "Exception when log extract audio: " + e.getMessage());
                    }
                }
            }
        });
    }

    public void onRestartRender(final long j) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass8 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onRestartRender: Session " + j + " not found!");
                    return;
                }
                VideoPlayerAnalytics.this.maybeLogPlaybackStart(videoAnalyticsSession, currentTimeMs);
                VideoPlayerAnalytics.maybeLogSeekSample(videoAnalyticsSession, currentTimeMs);
                VideoPlayerAnalytics.maybeLogResumeSample(videoAnalyticsSession, currentTimeMs);
                VideoPlayerAnalytics.sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.RENDER_RESTART, currentTimeMs);
                VideoPlayerAnalytics.recordHQPlaybackStart(videoAnalyticsSession, currentTimeMs, VideoSessionEvent.RENDER_RESTART);
            }
        });
    }

    public void onSyncDelayUpdate(final long j, final long j2) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass9 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onSyncDelayUpdate: Session " + j + " not found!");
                    return;
                }
                videoAnalyticsSession.lastSyncPlaybackDelayMs = j2;
            }
        });
    }

    public void onStreamingSample(final long j, final long j2, final long j3, final long j4, final long j5) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass10 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onStreamingSample: Session " + j + " not found!");
                    return;
                }
                videoAnalyticsSession.bufferAheadDurationMsSampler.sample(j4, (double) j5);
                videoAnalyticsSession.bufferAheadDurationMsPeriodicSampler.sample(j4, (double) j5);
                videoAnalyticsSession.streamingBitrateBpsSampler.sample(j3, (double) j5);
                videoAnalyticsSession.streamingBitrateBpsPeriodicSampler.sample(j3, (double) j5);
                videoAnalyticsSession.accumulatedStreamingTimeMs += j5;
                videoAnalyticsSession.accumulatedStreamedBytes += j2;
                videoAnalyticsSession.totalStreamedBytes += j2;
                VideoPlayerAnalytics.this.maybeLogStreamingSampleEvent(videoAnalyticsSession, false, currentTimeMs);
            }
        });
    }

    public void onHttpConnect(final long j, final String str, final String str2, final String str3, final int i, final long j2) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass11 */

            public void run() {
                String str;
                String str2;
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onHttpConnect: Session " + j + " not found!");
                    return;
                }
                boolean redactAnalyticsVideoSource = videoAnalyticsSession.playbackSettings.redactAnalyticsVideoSource(videoAnalyticsSession.provider);
                if (redactAnalyticsVideoSource) {
                    str = VideoPlayerAnalytics.REDACTED_URL;
                } else {
                    str = str;
                }
                videoAnalyticsSession.httpUrl = str;
                if (redactAnalyticsVideoSource) {
                    str2 = "";
                } else {
                    str2 = str2;
                }
                videoAnalyticsSession.remoteIPAddress = str2;
                videoAnalyticsSession.httpMethod = str3;
                videoAnalyticsSession.httpResponseCode = i;
                videoAnalyticsSession.responseTimeMsSampler.sample(j2);
                videoAnalyticsSession.responseTimeMsPeriodicSampler.sample(j2);
            }
        });
    }

    private void addHttpDataToEvent(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        if (videoAnalyticsSession.httpUrl != null) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_HTTP_URL, videoAnalyticsSession.httpUrl).put(EXTRA_HTTP_METHOD, videoAnalyticsSession.httpMethod).put(EXTRA_HTTP_RESPONSE_CODE, videoAnalyticsSession.httpResponseCode);
            if (!TextUtils.isEmpty(videoAnalyticsSession.remoteIPAddress)) {
                videoPlayerAnalyticsLogEvent.put(EXTRA_REMOTE_IP_ADDRESS, videoAnalyticsSession.remoteIPAddress);
            }
        }
    }

    public void onAdaptivePlaylistUpdate(final long j, final Map<String, Object> map, final List<Map<String, Object>> list) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass12 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onAdaptivePlaylistUpdate: Session " + j + " not found!");
                } else if (map == null || list == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onAdaptivePlaylistUpdate: selectedProfile and availableProfiles must not be null");
                } else {
                    if (videoAnalyticsSession.lastPlaylistUpdateTimeMs > 0) {
                        map.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_UPDATE_INTERVAL_MS, Long.valueOf(currentTimeMs - videoAnalyticsSession.lastPlaylistUpdateTimeMs));
                    }
                    videoAnalyticsSession.playListUpdatePeriodicCount++;
                    if (videoAnalyticsSession.selectedProfileWithTimeline != null) {
                        Long l = (Long) videoAnalyticsSession.selectedProfileWithTimeline.get(VideoPlayerAnalytics.EXTRA_DURATION_MS);
                        if (l != null) {
                            videoAnalyticsSession.selectedProfile.put(VideoPlayerAnalytics.EXTRA_DURATION_MS, l);
                        }
                        Long l2 = (Long) videoAnalyticsSession.selectedProfileWithTimeline.get(VideoPlayerAnalytics.EXTRA_POSITION_MS);
                        if (l2 != null) {
                            videoAnalyticsSession.selectedProfile.put(VideoPlayerAnalytics.EXTRA_POSITION_MS, l2);
                        }
                    }
                    videoAnalyticsSession.selectedProfileWithTimeline = videoAnalyticsSession.selectedProfile;
                    videoAnalyticsSession.selectedProfile = map;
                    videoAnalyticsSession.availableProfiles = list;
                    videoAnalyticsSession.lastPlaylistUpdateTimeMs = currentTimeMs;
                }
            }
        });
    }

    public void onExtractProjectionMetadata(final long j, final VideoProjection videoProjection) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass13 */

            public void run() {
                try {
                    VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                    if (videoAnalyticsSession == null) {
                        Log.w(VideoPlayerAnalytics.TAG, "onExtractProjectionMetadata: Session " + j + " not found!");
                        return;
                    }
                    if (videoAnalyticsSession.sphericalMetadata == null) {
                        videoAnalyticsSession.sphericalMetadata = new JSONObject();
                    }
                    videoAnalyticsSession.sphericalMetadata.put(videoProjection.getMetadataSource(), videoProjection.toJSONObject());
                } catch (JSONException e) {
                    Log.e(VideoPlayerAnalytics.TAG, "Error tracking projection metadata: ", e);
                }
            }
        });
    }

    public void onSelectProjectionMetadata(final long j, final VideoProjection videoProjection) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass14 */

            public void run() {
                try {
                    VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                    if (videoAnalyticsSession == null) {
                        Log.w(VideoPlayerAnalytics.TAG, "onExtractProjectionMetadata: Session " + j + " not found!");
                        return;
                    }
                    if (videoAnalyticsSession.sphericalMetadata == null) {
                        videoAnalyticsSession.sphericalMetadata = new JSONObject();
                    }
                    videoAnalyticsSession.sphericalMetadata.put(VideoPlayerAnalytics.PLAYLIST_SELECTED_PROFILE, videoProjection.toJSONObject());
                } catch (JSONException e) {
                    Log.e(VideoPlayerAnalytics.TAG, "Error tracking projection metadata: ", e);
                }
            }
        });
    }

    public void onDroppedFrames(final long j, final long j2, final long j3, final long j4, final long j5) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass15 */

            public void run() {
                if (j5 != 0) {
                    VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                    if (videoAnalyticsSession == null) {
                        Log.w(VideoPlayerAnalytics.TAG, "onDroppedFrames: Session " + j + " not found!");
                        return;
                    }
                    videoAnalyticsSession.accumulatedDroppedFramesElapsedTimeMs += j2;
                    videoAnalyticsSession.accumulatedDelayedDroppedFrames += j3;
                    videoAnalyticsSession.accumulatedUnconsumedDroppedFrames += j4;
                    videoAnalyticsSession.accumulatedDroppedFrames += j3 + j4;
                    videoAnalyticsSession.accumulatedDecodedFrames += j5;
                    videoAnalyticsSession.streamingUpdateFrameDecodeTimeMs += j2;
                    videoAnalyticsSession.streamingUpdateDelayedDroppedFrames += j3;
                    videoAnalyticsSession.streamingUpdateUnconsumedDroppedFrames += j4;
                    videoAnalyticsSession.streamingUpdateDroppedFrames += j3 + j4;
                    videoAnalyticsSession.streamingUpdateDecodedFrames += j5;
                    videoAnalyticsSession.totalDelayedDroppedFrames += j3;
                    videoAnalyticsSession.totalUnconsumedDroppedFrames += j4;
                    videoAnalyticsSession.totalDroppedFrames += j3 + j4;
                    videoAnalyticsSession.totalDecodedFrames += j5;
                    VideoPlayerAnalytics.this.maybeLogDroppedFramesEvent(videoAnalyticsSession, false, currentTimeMs);
                }
            }
        });
    }

    public void onVideoSizeChanged(final long j, final int i, final int i2) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass16 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onVideoSizeChanged: Session " + j + " not found!");
                } else if (videoAnalyticsSession.lastVideoSizeChangeTimeMs <= 0 || i != videoAnalyticsSession.width || i2 != videoAnalyticsSession.height) {
                    VideoPlayerAnalytics.sampleLastVideoSize(videoAnalyticsSession, currentTimeMs);
                    videoAnalyticsSession.width = i;
                    videoAnalyticsSession.height = i2;
                    VideoPlayerAnalytics.recordHQPlaybackEnd(videoAnalyticsSession, currentTimeMs, VideoSessionEvent.VIDEO_SIZE_CHANGED);
                    VideoPlayerAnalytics.recordHQPlaybackStart(videoAnalyticsSession, currentTimeMs, VideoSessionEvent.VIDEO_SIZE_CHANGED);
                    try {
                        VideoPlayerAnalyticsLogEvent newEvent = VideoPlayerAnalytics.this.newEvent(videoAnalyticsSession, VideoPlayerAnalytics.EVENT_TYPE_ON_VIDEO_SIZE_CHANGED, currentTimeMs);
                        newEvent.put("width", videoAnalyticsSession.width).put("height", videoAnalyticsSession.height);
                        VideoPlayerAnalytics.this.reportEvent(newEvent);
                    } catch (JSONException e) {
                        Log.w(VideoPlayerAnalytics.TAG, "Exception when log changed video size: " + e.getMessage());
                    }
                }
            }
        });
    }

    public void onSubtitleInfo(final long j, final String str, final String str2, final Collection<String> collection) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass17 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onSubtitleInfo: Session " + j + " not found!");
                    return;
                }
                videoAnalyticsSession.subtitleMimeType = str;
                String str = str2;
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                String lowerCase = str.toLowerCase();
                if (!TextUtils.isEmpty(lowerCase) && !videoAnalyticsSession.subtitleDurationMs.containsKey(lowerCase)) {
                    videoAnalyticsSession.subtitleDurationMs.put(lowerCase, 0L);
                }
                for (String str2 : collection) {
                    if (!TextUtils.isEmpty(str2) && !videoAnalyticsSession.subtitleDurationMs.containsKey(str2)) {
                        videoAnalyticsSession.subtitleDurationMs.put(str2.toLowerCase(), 0L);
                    }
                }
                VideoPlayerAnalytics.addLastSubtitleDurationMs(videoAnalyticsSession, currentTimeMs);
                videoAnalyticsSession.lastSubtitleSelectionTimeMs = new Pair<>(lowerCase, Long.valueOf(currentTimeMs));
            }
        });
    }

    public void onVideoTrackInfo(final long j, final int i, final int i2) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass18 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onVideoTrackInfo: Session " + j + " not found!");
                    return;
                }
                int i = i2;
                videoAnalyticsSession.videoTrackCount = i;
                int i2 = i;
                if (i2 < -1 || i2 >= i) {
                    i2 = -1;
                }
                if (!videoAnalyticsSession.videoTrackDurationMs.containsKey(Integer.valueOf(i2))) {
                    videoAnalyticsSession.videoTrackDurationMs.put(Integer.valueOf(i2), 0L);
                }
                VideoPlayerAnalytics.addLastVideoTrackDurationMs(videoAnalyticsSession, currentTimeMs);
                videoAnalyticsSession.lastVideoTrackSelectionTimeMs = new Pair<>(Integer.valueOf(i2), Long.valueOf(currentTimeMs));
            }
        });
    }

    public void onPause(final long j) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass19 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onPause: Session " + j + " not found!");
                } else if (!videoAnalyticsSession.didPlaybackStart) {
                    videoAnalyticsSession.didPauseBeforeLoad = true;
                } else {
                    VideoPlayerAnalytics.this.onPause(videoAnalyticsSession, currentTimeMs);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onPause(VideoAnalyticsSession videoAnalyticsSession, long j) {
        maybeLogSeekSample(videoAnalyticsSession, j);
        maybeLogResumeSample(videoAnalyticsSession, j);
        videoAnalyticsSession.pauseStartTimeMs = j;
        sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.PAUSE, j);
        recordHQPlaybackEnd(videoAnalyticsSession, j, VideoSessionEvent.PAUSE);
    }

    public void onResume(final long j) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass20 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onResume: Session " + j + " not found!");
                    return;
                }
                videoAnalyticsSession.didPauseBeforeLoad = false;
                if (videoAnalyticsSession.pauseStartTimeMs != 0) {
                    VideoPlayerAnalytics.maybeAddPausedDuration(videoAnalyticsSession, currentTimeMs);
                    videoAnalyticsSession.resumeStartTimeMs = currentTimeMs;
                    VideoPlayerAnalytics.sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.RESUME, currentTimeMs);
                }
            }
        });
    }

    public void onBuffering(final long j) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass21 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onStopBuffering: Session " + j + " not found!");
                } else if (videoAnalyticsSession.bufferingStartTimeNs == 0) {
                    videoAnalyticsSession.bufferingStartTimeNs = System.nanoTime();
                }
            }
        });
    }

    public void onStopBuffering(final long j) {
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass22 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onStopBuffering: Session " + j + " not found!");
                } else if (videoAnalyticsSession.bufferingStartTimeNs != 0) {
                    long nanoTime = (System.nanoTime() - videoAnalyticsSession.bufferingStartTimeNs) / C.MICROS_PER_SECOND;
                    videoAnalyticsSession.bufferingDurationMsSampler.sample(nanoTime);
                    videoAnalyticsSession.bufferingDurationMsPeriodicSampler.sample(nanoTime);
                    videoAnalyticsSession.bufferingStartTimeNs = 0;
                }
            }
        });
    }

    public void onRenderStall(final long j, final long j2) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass23 */

            public void run() {
                long j;
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onRenderStall: Session " + j + " not found!");
                    return;
                }
                if (videoAnalyticsSession.state == VideoSessionState.PLAYING) {
                    j = Math.min(currentTimeMs - videoAnalyticsSession.lastPlaybackStartTimeMs, j2);
                } else {
                    j = Math.max(0L, videoAnalyticsSession.lastPlaybackEndTimeMs - (currentTimeMs - j2));
                }
                videoAnalyticsSession.stallDurationMsSampler.sample(j);
                videoAnalyticsSession.stallDurationMsPeriodicSampler.sample(j);
                VideoPlayerAnalytics.updateHQPlaybackDurations(videoAnalyticsSession, currentTimeMs - j2, VideoSessionEvent.STALL);
                VideoPlayerAnalytics.recordHQPlaybackStart(videoAnalyticsSession, currentTimeMs, VideoSessionEvent.STALL);
            }
        });
    }

    public void onSeekStart(final long j, long j2) {
        final long currentTimeMs = getCurrentTimeMs();
        this.eventHandler.post(new Runnable() {
            /* class com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass24 */

            public void run() {
                VideoAnalyticsSession videoAnalyticsSession = (VideoAnalyticsSession) VideoPlayerAnalytics.this.sessions.get(Long.valueOf(j));
                if (videoAnalyticsSession == null) {
                    Log.w(VideoPlayerAnalytics.TAG, "onSeekStart: Session " + j + " not found!");
                } else if (videoAnalyticsSession.didPlaybackStart) {
                    VideoPlayerAnalytics.maybeLogSeekSample(videoAnalyticsSession, currentTimeMs);
                    VideoPlayerAnalytics.maybeLogResumeSample(videoAnalyticsSession, currentTimeMs);
                    if (videoAnalyticsSession.pauseStartTimeMs == 0) {
                        videoAnalyticsSession.seekStartTimeMs = currentTimeMs;
                    }
                    VideoPlayerAnalytics.sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.SEEK_START, currentTimeMs);
                    VideoPlayerAnalytics.recordHQPlaybackEnd(videoAnalyticsSession, currentTimeMs, VideoSessionEvent.SEEK_START);
                }
            }
        });
    }

    public void onCompleteSession(long j, long j2, long j3, String str, String str2, String str3, long j4) {
        onCompleteSession(j, j2, j3, str, str2, TextUtils.isEmpty(str3) ? null : new Exception(str3), j4);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0028 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCompleteSession(final long r17, final long r19, final long r21, final java.lang.String r23, final java.lang.String r24, final java.lang.Throwable r25, long r26) {
        /*
            r16 = this;
            r14 = r16
            long r12 = getCurrentTimeMs()
            android.os.Handler r0 = r14.eventHandler
            com.oculus.video.analytics.VideoPlayerAnalytics$25 r15 = new com.oculus.video.analytics.VideoPlayerAnalytics$25
            r1 = r15
            r2 = r16
            r3 = r17
            r5 = r19
            r7 = r21
            r9 = r23
            r10 = r24
            r11 = r25
            r1.<init>(r3, r5, r7, r9, r10, r11, r12)
            r0.post(r15)
            monitor-enter(r16)
            r0 = r26
            r14.wait(r0)     // Catch:{ InterruptedException -> 0x0028 }
            goto L_0x0028
        L_0x0026:
            r0 = move-exception
            goto L_0x002a
        L_0x0028:
            monitor-exit(r16)     // Catch:{ all -> 0x0026 }
            return
        L_0x002a:
            monitor-exit(r16)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.analytics.VideoPlayerAnalytics.onCompleteSession(long, long, long, java.lang.String, java.lang.String, java.lang.Throwable, long):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCompleteSessionImpl(long j, long j2, long j3, String str, String str2, Throwable th, long j4) {
        RenderingStats renderingStats;
        VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent;
        long j5;
        VideoAnalyticsSession videoAnalyticsSession = this.sessions.get(Long.valueOf(j));
        if (videoAnalyticsSession == null) {
            Log.w(TAG, "onCompleteSessionImpl: Session " + j + " not found");
            return;
        }
        try {
            renderingStats = new RenderingStats(str);
        } catch (Throwable th2) {
            Log.e(TAG, "Exception fetching native rendering stats: " + th2.getMessage());
            renderingStats = null;
        }
        try {
            videoAnalyticsSession.clientAnalyticsData = str2;
            if (videoAnalyticsSession.fps <= 0.0f && renderingStats != null) {
                videoAnalyticsSession.fps = (float) renderingStats.estimatedFrameRate;
            }
            maybeLogFatalError(videoAnalyticsSession, j, th, j4);
            maybeLogStreamingSampleEvent(videoAnalyticsSession, videoAnalyticsSession.isSyncMedia, j4);
            long j6 = videoAnalyticsSession.state == VideoSessionState.PLAYING ? j3 : 0;
            maybeLogDroppedFramesEvent(videoAnalyticsSession, true, j4);
            maybeLogSeekSample(videoAnalyticsSession, j4);
            maybeLogResumeSample(videoAnalyticsSession, j4);
            maybeAddPausedDuration(videoAnalyticsSession, j4);
            sampleLastVideoSize(videoAnalyticsSession, j4);
            sampleLastVideoFormat(videoAnalyticsSession, j4);
            sessionStateTransit(videoAnalyticsSession, VideoSessionEvent.COMPLETE_SESSION, j4);
            updateHQPlaybackDurations(videoAnalyticsSession, j4, VideoSessionEvent.COMPLETE_SESSION);
            VideoPlayerAnalyticsLogEvent newEvent = newEvent(videoAnalyticsSession, EVENT_TYPE_ON_STOP_SESSION, j4);
            if (!TextUtils.isEmpty(videoAnalyticsSession.fatalErrorMessage)) {
                newEvent.put(EXTRA_FATAL_ERROR_MESSAGE, videoAnalyticsSession.fatalErrorMessage);
            }
            long j7 = j4 - videoAnalyticsSession.videoStartTimeMs;
            newEvent.put(EXTRA_SESSION_DURATION_MS, j7);
            newEvent.put(EXTRA_PAUSED_DURATION_MS, videoAnalyticsSession.totalPausedDurationMs);
            newEvent.put(EXTRA_UNLOAD_DURATION_MS, j6);
            videoAnalyticsSession.seekDurationMsSampler.appendToEvent(newEvent, true, false);
            videoAnalyticsSession.resumeDurationMsSampler.appendToEvent(newEvent, true, false);
            videoAnalyticsSession.stallDurationMsSampler.appendToEvent(newEvent, true, false);
            if (videoAnalyticsSession.didPlaybackStart) {
                videoPlayerAnalyticsLogEvent = newEvent;
                j5 = (((((j7 - videoAnalyticsSession.loadDurationMs) - videoAnalyticsSession.totalPausedDurationMs) - videoAnalyticsSession.resumeDurationMsSampler.getSum()) - videoAnalyticsSession.seekDurationMsSampler.getSum()) - videoAnalyticsSession.stallDurationMsSampler.getSum()) - j6;
            } else {
                videoPlayerAnalyticsLogEvent = newEvent;
                j5 = 0;
            }
            if (j5 > j7 || j5 < 0) {
                Log.w(TAG, "Invalid playback_duration_ms: " + j5);
            }
            long max = Math.max(0L, Math.min(j7, j5));
            videoPlayerAnalyticsLogEvent.put(EXTRA_PLAYBACK_DURATION_MS, max);
            if (max > 0) {
                float sum = (((float) videoAnalyticsSession.hqPlaybackDurationMsSampler.getSum()) * 100.0f) / ((float) max);
                if (sum > 100.0f) {
                    Log.w(TAG, String.format("Weird thing happening, HVQ=%.02f%% > 1 for Session %d", Float.valueOf(sum), Long.valueOf(j)));
                }
                videoPlayerAnalyticsLogEvent.put(EXTRA_HVQ_PERCENTAGE, (double) Math.min(sum, 100.0f));
                Log.d(TAG, String.format("Logging HVQ=%.02f for Session %d", Float.valueOf(sum), Long.valueOf(j)));
            } else {
                Log.w(TAG, String.format("HVQ not available for Session %d, skip logging.", Long.valueOf(j)));
            }
            videoPlayerAnalyticsLogEvent.put(EXTRA_DELAYED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.totalDelayedDroppedFrames).put(EXTRA_UNCONSUMED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.totalUnconsumedDroppedFrames).put(EXTRA_DROPPED_FRAMES_COUNT, videoAnalyticsSession.totalDroppedFrames).put(EXTRA_DECODED_FRAMES_COUNT, videoAnalyticsSession.totalDecodedFrames);
            addHttpDataToEvent(videoAnalyticsSession, videoPlayerAnalyticsLogEvent);
            videoPlayerAnalyticsLogEvent.put(EXTRA_STREAMED_BYTES_COUNT, videoAnalyticsSession.totalStreamedBytes);
            videoAnalyticsSession.streamingBitrateBpsSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            videoAnalyticsSession.bufferAheadDurationMsSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            videoAnalyticsSession.responseTimeMsSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            videoAnalyticsSession.bufferingDurationMsSampler.appendToEvent(videoPlayerAnalyticsLogEvent, true, false);
            videoAnalyticsSession.vqmSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            videoAnalyticsSession.videoBitrateSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            videoAnalyticsSession.videoSizeSampler.appendToEvent(videoPlayerAnalyticsLogEvent, false, true);
            if (renderingStats != null) {
                renderingStats.appendToEvent(videoPlayerAnalyticsLogEvent);
            }
            videoPlayerAnalyticsLogEvent.put(EXTRA_POWER_CONSUMED_MAH, this.powerMonitor.getChargeDrainedMah() - videoAnalyticsSession.startingChargeDrainedMah);
            videoPlayerAnalyticsLogEvent.put(EXTRA_MAX_POSITION_MS, j2);
            videoPlayerAnalyticsLogEvent.put(EXTRA_PLAYBACK_PERCENTAGE, ((double) (((float) j2) * 100.0f)) / Math.max((double) ((float) videoAnalyticsSession.durationMs), 1.0E-6d));
            maybeLogSubtitleInfo(videoAnalyticsSession, videoPlayerAnalyticsLogEvent, j4);
            maybeLogVideoTrackInfo(videoAnalyticsSession, videoPlayerAnalyticsLogEvent, j4);
            maybeAddAdaptivePlaylistJSON(videoAnalyticsSession, videoPlayerAnalyticsLogEvent);
            reportEvent(videoPlayerAnalyticsLogEvent);
        } catch (Throwable th3) {
            Log.e(TAG, "Exception when log completed video session: " + th3.getMessage());
            nativeReportAnalyticsEvent(this.nativePtr, "{}", true);
        }
        this.sessions.remove(Long.valueOf(j));
        Log.i(TAG, "Completed session. ID=" + videoAnalyticsSession.ID);
    }

    /* access modifiers changed from: private */
    public static void sampleLastVideoSize(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.lastVideoSizeChangeTimeMs > 0) {
            videoAnalyticsSession.videoSizeSampler.sample(((long) videoAnalyticsSession.width) * ((long) videoAnalyticsSession.height), (double) (j - videoAnalyticsSession.lastVideoSizeChangeTimeMs));
        }
        videoAnalyticsSession.lastVideoSizeChangeTimeMs = j;
    }

    /* access modifiers changed from: private */
    public static void sampleLastVideoFormat(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.lastVideoFormatSampleTimeMs > 0) {
            long j2 = j - videoAnalyticsSession.lastVideoFormatSampleTimeMs;
            if (videoAnalyticsSession.vqm > 0.0d) {
                videoAnalyticsSession.vqmSampler.sample((long) (videoAnalyticsSession.vqm * 1.0E9d), (double) j2);
            }
            if (videoAnalyticsSession.videoBitrate > 0) {
                videoAnalyticsSession.videoBitrateSampler.sample((long) videoAnalyticsSession.videoBitrate, (double) j2);
            }
        }
        videoAnalyticsSession.lastVideoFormatSampleTimeMs = j;
    }

    private static void setInitializationInfo(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        if (!TextUtils.isEmpty(videoAnalyticsSession.ID)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_SESSION_ID, videoAnalyticsSession.ID);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.url)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_URL, videoAnalyticsSession.url);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.contentType)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_CONTENT_TYPE, videoAnalyticsSession.contentType);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.fileExtension)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_FILE_EXTENSION, videoAnalyticsSession.fileExtension);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.projection)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_PROJECTION, videoAnalyticsSession.projection);
            videoPlayerAnalyticsLogEvent.put(EXTRA_FOV_X, (double) videoAnalyticsSession.fovX);
            videoPlayerAnalyticsLogEvent.put(EXTRA_FOV_Y, (double) videoAnalyticsSession.fovY);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.videoID)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_VIDEO_ID, videoAnalyticsSession.videoID);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.provider)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_PROVIDER, videoAnalyticsSession.provider);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.channel)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_CHANNEL, videoAnalyticsSession.channel);
        }
        videoPlayerAnalyticsLogEvent.put(EXTRA_DID_EXTRACT_VIDEO, videoAnalyticsSession.didExtractVideo ? 1 : 0);
        videoPlayerAnalyticsLogEvent.put(EXTRA_DID_EXTRACT_AUDIO, videoAnalyticsSession.didExtractAudio ? 1 : 0);
        videoPlayerAnalyticsLogEvent.put(EXTRA_DID_START_PLAYBACK, videoAnalyticsSession.didPlaybackStart ? 1 : 0);
    }

    private static void setExtractedInfo(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        if (videoAnalyticsSession.didExtractVideo) {
            videoPlayerAnalyticsLogEvent.put("width", videoAnalyticsSession.width).put("height", videoAnalyticsSession.height).put(EXTRA_DURATION_MS, videoAnalyticsSession.durationMs).put(EXTRA_IS_3D, videoAnalyticsSession.is3D ? 1 : 0).put(EXTRA_IS_SEEKABLE, videoAnalyticsSession.isSeekable ? 1 : 0).put(EXTRA_IS_SYNC_MEDIA, videoAnalyticsSession.isSyncMedia ? 1 : 0);
            if (videoAnalyticsSession.fps > 0.0f) {
                videoPlayerAnalyticsLogEvent.put("fps", (double) videoAnalyticsSession.fps);
            }
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.videoMimeType)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_VIDEO_MIME_TYPE, videoAnalyticsSession.videoMimeType);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.videoRendererName)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_VIDEO_RENDERER_NAME, videoAnalyticsSession.videoRendererName);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.audioMimeType)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_AUDIO_MIME_TYPE, videoAnalyticsSession.audioMimeType);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.audioRendererName)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_AUDIO_RENDERER_NAME, videoAnalyticsSession.audioRendererName);
        }
        videoPlayerAnalyticsLogEvent.put(EXTRA_AUDIO_TRACK_COUNT, videoAnalyticsSession.audioTrackCount);
        if (!TextUtils.isEmpty(videoAnalyticsSession.audioChannelLayout)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_AUDIO_CHANNEL_LAYOUT, videoAnalyticsSession.audioChannelLayout);
        }
        if (!TextUtils.isEmpty(videoAnalyticsSession.subtitleMimeType)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_SUBTITLE_MIME_TYPE, videoAnalyticsSession.subtitleMimeType);
        }
        if (videoAnalyticsSession.sphericalMetadata != null) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_SPHERICAL_METADATA, videoAnalyticsSession.sphericalMetadata.toString());
        }
    }

    private static void setPlaybackStartInfo(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) throws JSONException {
        if (videoAnalyticsSession.didPlaybackStart) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_LOAD_DURATION_MS, videoAnalyticsSession.loadDurationMs);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private VideoPlayerAnalyticsLogEvent newEvent(VideoAnalyticsSession videoAnalyticsSession, String str, long j) throws JSONException {
        VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent = new VideoPlayerAnalyticsLogEvent(EVENT_NAME);
        videoAnalyticsSession.lastEventTimeMs.put(str, Long.valueOf(j));
        videoPlayerAnalyticsLogEvent.put(EXTRA_EVENT_TYPE, str).put(EXTRA_TIME_FROM_LAUNCH_MS, j - this.creationTimeMs).put(EXTRA_OVR_USER_ID, this.ovrUserID).put(EXTRA_BOARD, Build.BOARD);
        if (EVENT_TYPE_ON_START_SESSION.equals(str) || EVENT_TYPE_ON_STOP_SESSION.equals(str) || EVENT_TYPE_ON_FATAL_ERROR.equals(str)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_SETTINGS, this.settings);
        }
        setInitializationInfo(videoAnalyticsSession, videoPlayerAnalyticsLogEvent);
        setExtractedInfo(videoAnalyticsSession, videoPlayerAnalyticsLogEvent);
        setPlaybackStartInfo(videoAnalyticsSession, videoPlayerAnalyticsLogEvent);
        String str2 = videoAnalyticsSession.clientAnalyticsData;
        if (!TextUtils.isEmpty(str2)) {
            videoPlayerAnalyticsLogEvent.put(EXTRA_CLIENT_DATA, str2.substring(0, Math.min(str2.length(), 1024)));
        }
        return videoPlayerAnalyticsLogEvent;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void reportEvent(VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) {
        String str;
        logEventToLogcat(videoPlayerAnalyticsLogEvent);
        try {
            str = videoPlayerAnalyticsLogEvent.getString(EXTRA_EVENT_TYPE);
        } catch (JSONException unused) {
            Log.w(TAG, "reportEvent: Unable to fetch event_type");
            str = null;
        }
        nativeReportAnalyticsEvent(this.nativePtr, videoPlayerAnalyticsLogEvent.toString(), EVENT_TYPE_ON_STOP_SESSION.equals(str));
    }

    private void logEventToLogcat(VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) {
        String str = "Event : " + videoPlayerAnalyticsLogEvent.toString();
        int length = str.length() / 1000;
        int i = 0;
        while (i <= length) {
            int i2 = i * 1000;
            i++;
            Log.d(TAG, str.substring(i2, Math.min(str.length(), i * 1000)));
        }
    }

    private void maybeAddAdaptivePlaylistJSON(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent) {
        if (videoAnalyticsSession != null && videoPlayerAnalyticsLogEvent != null && videoAnalyticsSession.availableProfiles != null) {
            Map<String, Object> map = videoAnalyticsSession.selectedProfileWithTimeline != null ? videoAnalyticsSession.selectedProfileWithTimeline : videoAnalyticsSession.selectedProfile;
            if (map != null) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(PLAYLIST_PROFILE_UPDATE_COUNT, videoAnalyticsSession.playListUpdatePeriodicCount);
                    jSONObject.put(PLAYLIST_SELECTED_PROFILE, new JSONObject(map));
                    JSONArray jSONArray = new JSONArray();
                    for (Map<String, Object> map2 : videoAnalyticsSession.availableProfiles) {
                        if (map2 != null) {
                            jSONArray.put(new JSONObject(map2));
                        }
                    }
                    if (jSONArray.length() != 0) {
                        jSONObject.put(PLAYLIST_ALL_PROFILES, jSONArray);
                    }
                    videoPlayerAnalyticsLogEvent.put(EXTRA_ADAPTIVE_PLAYLIST, jSONObject.toString());
                } catch (JSONException unused) {
                    Log.w(TAG, "Unable to construct adaptive playlist JSON");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeLogStreamingSampleEvent(VideoAnalyticsSession videoAnalyticsSession, boolean z, long j) {
        if ((z && videoAnalyticsSession.accumulatedStreamedBytes > 0) || canLogEvent(videoAnalyticsSession, EVENT_TYPE_ON_STREAMING_UPDATE)) {
            long lastEventTimeMs = j - getLastEventTimeMs(videoAnalyticsSession, EVENT_TYPE_ON_STREAMING_UPDATE);
            try {
                VideoPlayerAnalyticsLogEvent newEvent = newEvent(videoAnalyticsSession, EVENT_TYPE_ON_STREAMING_UPDATE, j);
                newEvent.put(EXTRA_ELAPSED_TIME_MS, lastEventTimeMs).put(EXTRA_STREAMED_BYTES_COUNT, videoAnalyticsSession.accumulatedStreamedBytes);
                videoAnalyticsSession.bufferAheadDurationMsPeriodicSampler.appendToEvent(newEvent, false, false);
                videoAnalyticsSession.streamingBitrateBpsPeriodicSampler.appendToEvent(newEvent, false, false);
                videoAnalyticsSession.responseTimeMsPeriodicSampler.appendToEvent(newEvent, false, false);
                if (videoAnalyticsSession.videoBitrate <= 0 && videoAnalyticsSession.selectedProfile != null && videoAnalyticsSession.selectedProfile.containsKey(PLAYLIST_PROFILE_ITEM_BITRATE)) {
                    try {
                        videoAnalyticsSession.videoBitrate = Integer.parseInt(videoAnalyticsSession.selectedProfile.get(PLAYLIST_PROFILE_ITEM_BITRATE).toString());
                    } catch (Exception unused) {
                        videoAnalyticsSession.videoBitrate = 0;
                    }
                }
                if (videoAnalyticsSession.videoBitrate > 0) {
                    newEvent.put(EXTRA_VIDEO_BITRATE, videoAnalyticsSession.videoBitrate);
                }
                if (videoAnalyticsSession.lastSyncPlaybackDelayMs > SyncMediaPlayer.SYNC_DELAY_UNSET_MS) {
                    newEvent.put(EXTRA_SYNC_PLAYBACK_DELAY_MS, videoAnalyticsSession.lastSyncPlaybackDelayMs);
                }
                newEvent.put("width", videoAnalyticsSession.width).put("height", videoAnalyticsSession.height);
                videoAnalyticsSession.stallDurationMsPeriodicSampler.appendToEvent(newEvent, true, false);
                videoAnalyticsSession.bufferingDurationMsPeriodicSampler.appendToEvent(newEvent, true, false);
                videoAnalyticsSession.seekDurationMsPeriodicSampler.appendToEvent(newEvent, true, false);
                float f = (float) videoAnalyticsSession.streamingUpdateFrameDecodeTimeMs;
                int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
                newEvent.put("fps", videoAnalyticsSession.fps > 0.0f ? (double) videoAnalyticsSession.fps : (double) (i > 0 ? (((float) videoAnalyticsSession.streamingUpdateDecodedFrames) * 1000.0f) / f : 0.0f)).put(EXTRA_EFFECTIVE_FPS, (double) (i > 0 ? (((float) (videoAnalyticsSession.streamingUpdateDecodedFrames - videoAnalyticsSession.streamingUpdateDroppedFrames)) * 1000.0f) / f : 0.0f)).put(EXTRA_DELAYED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.streamingUpdateDelayedDroppedFrames).put(EXTRA_UNCONSUMED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.streamingUpdateUnconsumedDroppedFrames).put(EXTRA_DROPPED_FRAMES_COUNT, videoAnalyticsSession.streamingUpdateDroppedFrames).put(EXTRA_DECODED_FRAMES_COUNT, videoAnalyticsSession.streamingUpdateDecodedFrames);
                addHttpDataToEvent(videoAnalyticsSession, newEvent);
                maybeAddAdaptivePlaylistJSON(videoAnalyticsSession, newEvent);
                reportEvent(newEvent);
                videoAnalyticsSession.accumulatedStreamingTimeMs = 0;
                videoAnalyticsSession.accumulatedStreamedBytes = 0;
                videoAnalyticsSession.streamingUpdateFrameDecodeTimeMs = 0;
                videoAnalyticsSession.streamingUpdateDelayedDroppedFrames = 0;
                videoAnalyticsSession.streamingUpdateUnconsumedDroppedFrames = 0;
                videoAnalyticsSession.streamingUpdateDecodedFrames = 0;
                videoAnalyticsSession.streamingUpdateDroppedFrames = 0;
                videoAnalyticsSession.resetPeriodicValues();
            } catch (JSONException e) {
                Log.w(TAG, "Unable to construct streaming sample JSON: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeLogDroppedFramesEvent(VideoAnalyticsSession videoAnalyticsSession, boolean z, long j) {
        if (z || (canLogEvent(videoAnalyticsSession, EVENT_TYPE_ON_DROPPED_FRAMES) && videoAnalyticsSession.accumulatedDroppedFrames >= 50)) {
            try {
                VideoPlayerAnalyticsLogEvent newEvent = newEvent(videoAnalyticsSession, EVENT_TYPE_ON_DROPPED_FRAMES, j);
                newEvent.put(EXTRA_ELAPSED_TIME_MS, videoAnalyticsSession.accumulatedDroppedFramesElapsedTimeMs).put(EXTRA_DELAYED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.accumulatedDelayedDroppedFrames).put(EXTRA_UNCONSUMED_DROPPED_FRAMES_COUNT, videoAnalyticsSession.accumulatedUnconsumedDroppedFrames).put(EXTRA_DROPPED_FRAMES_COUNT, videoAnalyticsSession.accumulatedDroppedFrames).put(EXTRA_DECODED_FRAMES_COUNT, videoAnalyticsSession.accumulatedDecodedFrames);
                reportEvent(newEvent);
            } catch (JSONException e) {
                Log.w(TAG, "Exception when log dropped frame: " + e.getMessage());
            }
            videoAnalyticsSession.accumulatedDroppedFramesElapsedTimeMs = 0;
            videoAnalyticsSession.accumulatedDelayedDroppedFrames = 0;
            videoAnalyticsSession.accumulatedUnconsumedDroppedFrames = 0;
            videoAnalyticsSession.accumulatedDroppedFrames = 0;
            videoAnalyticsSession.accumulatedDecodedFrames = 0;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeLogPlaybackStart(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (!videoAnalyticsSession.didPlaybackStart) {
            if (videoAnalyticsSession.didPauseBeforeLoad) {
                videoAnalyticsSession.didPauseBeforeLoad = false;
                onPause(videoAnalyticsSession, j);
            }
            videoAnalyticsSession.didPlaybackStart = true;
            videoAnalyticsSession.loadDurationMs = j - videoAnalyticsSession.videoStartTimeMs;
            try {
                reportEvent(newEvent(videoAnalyticsSession, EVENT_TYPE_ON_START_PLAYBACK, j));
            } catch (JSONException e) {
                Log.w(TAG, "Exception when log playback start: " + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public static void maybeLogSeekSample(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.seekStartTimeMs != 0) {
            long j2 = j - videoAnalyticsSession.seekStartTimeMs;
            videoAnalyticsSession.seekDurationMsSampler.sample(j2);
            videoAnalyticsSession.seekDurationMsPeriodicSampler.sample(j2);
            videoAnalyticsSession.seekStartTimeMs = 0;
        }
    }

    /* access modifiers changed from: private */
    public static void maybeLogResumeSample(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.resumeStartTimeMs != 0) {
            videoAnalyticsSession.resumeDurationMsSampler.sample(j - videoAnalyticsSession.resumeStartTimeMs);
            videoAnalyticsSession.resumeStartTimeMs = 0;
        }
    }

    /* access modifiers changed from: private */
    public static void maybeAddPausedDuration(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.pauseStartTimeMs != 0) {
            videoAnalyticsSession.totalPausedDurationMs += j - videoAnalyticsSession.pauseStartTimeMs;
            videoAnalyticsSession.pauseStartTimeMs = 0;
        }
    }

    /* access modifiers changed from: private */
    public static void addLastSubtitleDurationMs(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.lastSubtitleSelectionTimeMs != null && !TextUtils.isEmpty((CharSequence) videoAnalyticsSession.lastSubtitleSelectionTimeMs.first)) {
            String str = (String) videoAnalyticsSession.lastSubtitleSelectionTimeMs.first;
            videoAnalyticsSession.subtitleDurationMs.put(str, Long.valueOf(videoAnalyticsSession.subtitleDurationMs.get(str).longValue() + (j - ((Long) videoAnalyticsSession.lastSubtitleSelectionTimeMs.second).longValue())));
        }
    }

    private static void maybeLogSubtitleInfo(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent, long j) throws JSONException {
        addLastSubtitleDurationMs(videoAnalyticsSession, j);
        if (!videoAnalyticsSession.subtitleDurationMs.isEmpty()) {
            Set<String> keySet = videoAnalyticsSession.subtitleDurationMs.keySet();
            String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
            Arrays.sort(strArr);
            videoPlayerAnalyticsLogEvent.put(EXTRA_SUBTITLE_LIST, Arrays.toString(strArr));
            String str = null;
            long j2 = 0;
            for (String str2 : keySet) {
                long longValue = videoAnalyticsSession.subtitleDurationMs.get(str2).longValue();
                if (longValue > j2) {
                    str = str2;
                    j2 = longValue;
                }
            }
            if (str != null) {
                videoPlayerAnalyticsLogEvent.put(EXTRA_MOST_USED_SUBTITLE, str);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void addLastVideoTrackDurationMs(VideoAnalyticsSession videoAnalyticsSession, long j) {
        if (videoAnalyticsSession.lastVideoTrackSelectionTimeMs != null) {
            int intValue = ((Integer) videoAnalyticsSession.lastVideoTrackSelectionTimeMs.first).intValue();
            videoAnalyticsSession.videoTrackDurationMs.put(Integer.valueOf(intValue), Long.valueOf(videoAnalyticsSession.videoTrackDurationMs.get(Integer.valueOf(intValue)).longValue() + (j - ((Long) videoAnalyticsSession.lastVideoTrackSelectionTimeMs.second).longValue())));
        }
    }

    private static void maybeLogVideoTrackInfo(VideoAnalyticsSession videoAnalyticsSession, VideoPlayerAnalyticsLogEvent videoPlayerAnalyticsLogEvent, long j) throws JSONException {
        addLastVideoTrackDurationMs(videoAnalyticsSession, j);
        if (!videoAnalyticsSession.videoTrackDurationMs.isEmpty()) {
            TreeMap treeMap = new TreeMap(new ValueComparator(videoAnalyticsSession.videoTrackDurationMs));
            treeMap.putAll(videoAnalyticsSession.videoTrackDurationMs);
            JSONArray jSONArray = new JSONArray();
            for (Map.Entry entry : treeMap.entrySet()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("idx", entry.getKey());
                jSONObject.put("selected_duration_ms", entry.getValue());
                jSONArray.put(jSONObject);
            }
            videoPlayerAnalyticsLogEvent.put(EXTRA_VIDEO_TRACK_SELECTION_DATA, jSONArray.toString());
            videoPlayerAnalyticsLogEvent.put(EXTRA_VIDEO_TRACK_COUNT, videoAnalyticsSession.videoTrackCount);
        }
    }

    private static long getCurrentTimeMs() {
        return System.nanoTime() / C.MICROS_PER_SECOND;
    }

    private static long getLastEventTimeMs(VideoAnalyticsSession videoAnalyticsSession, String str) {
        return videoAnalyticsSession.lastEventTimeMs.containsKey(str) ? videoAnalyticsSession.lastEventTimeMs.get(str).longValue() : videoAnalyticsSession.videoStartTimeMs;
    }

    /* access modifiers changed from: private */
    public static boolean canLogEvent(VideoAnalyticsSession videoAnalyticsSession, String str) {
        long j;
        if (EVENT_TYPE_ON_STREAMING_UPDATE.equals(str)) {
            j = MIN_DEFAULT_TIME_BETWEEN_HEAVY_EVENTS_MS;
            if (videoAnalyticsSession.isSyncMedia) {
                j = 30000;
            }
        } else {
            j = MIN_DEFAULT_TIME_BETWEEN_LIGHT_EVENTS_MS;
        }
        return getCurrentTimeMs() - getLastEventTimeMs(videoAnalyticsSession, str) > j;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        if (r0 == com.oculus.video.analytics.VideoSessionState.PAUSED) goto L_0x0017;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r7 != 4) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005d, code lost:
        if (r7 != 4) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0064, code lost:
        if (r0 == com.oculus.video.analytics.VideoSessionState.INIT) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void sessionStateTransit(com.oculus.video.analytics.VideoAnalyticsSession r9, com.oculus.video.analytics.VideoPlayerAnalytics.VideoSessionEvent r10, long r11) {
        /*
        // Method dump skipped, instructions count: 190
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.analytics.VideoPlayerAnalytics.sessionStateTransit(com.oculus.video.analytics.VideoAnalyticsSession, com.oculus.video.analytics.VideoPlayerAnalytics$VideoSessionEvent, long):void");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.video.analytics.VideoPlayerAnalytics$26  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass26 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$analytics$VideoSessionState = new int[VideoSessionState.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$projection$ProjectionType = new int[ProjectionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|(2:15|16)|17|(2:19|20)|21|23|24|(2:25|26)|27|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(28:0|(2:1|2)|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|17|(2:19|20)|21|23|24|25|26|27|29|30|31|32|33|34|35|36|(3:37|38|40)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(32:0|1|2|3|(2:5|6)|7|9|10|(2:11|12)|13|15|16|17|19|20|21|23|24|25|26|27|29|30|31|32|33|34|35|36|37|38|40) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0032 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x005d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0099 */
        static {
            /*
            // Method dump skipped, instructions count: 164
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.analytics.VideoPlayerAnalytics.AnonymousClass26.<clinit>():void");
        }
    }

    private static boolean isVideoHQ(VideoAnalyticsSession videoAnalyticsSession) {
        int i = AnonymousClass26.$SwitchMap$com$oculus$video$projection$ProjectionType[ProjectionType.fromVideoLayout(videoAnalyticsSession.projection).ordinal()];
        if ((i == 1 || i == 2) && videoAnalyticsSession.fovX == 360.0f && !videoAnalyticsSession.is3D) {
            if (TextUtils.equals(Build.BOARD, "pacific")) {
                return videoAnalyticsSession.width >= GO_360_MONO_HQ_WIDTH_THRES;
            }
            if (TextUtils.equals(Build.BOARD, "monterey")) {
                return videoAnalyticsSession.width >= QUEST_360_MONO_HQ_WIDTH_THRES;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static void recordHQPlaybackStart(VideoAnalyticsSession videoAnalyticsSession, long j, VideoSessionEvent videoSessionEvent) {
        if (videoAnalyticsSession.state == VideoSessionState.PLAYING && isVideoHQ(videoAnalyticsSession)) {
            recordPlaybackStartTime(videoAnalyticsSession.hqPlaybackTimeRangeMsList, j, videoSessionEvent);
        }
    }

    private static void recordPlaybackStartTime(LinkedList<Pair<Long, Long>> linkedList, long j, VideoSessionEvent videoSessionEvent) {
        if (linkedList.isEmpty() || ((Long) linkedList.getLast().second).longValue() != -1) {
            linkedList.addLast(new Pair<>(Long.valueOf(j), -1L));
        }
    }

    /* access modifiers changed from: private */
    public static void recordHQPlaybackEnd(VideoAnalyticsSession videoAnalyticsSession, long j, VideoSessionEvent videoSessionEvent) {
        recordPlaybackEndTime(videoAnalyticsSession.hqPlaybackTimeRangeMsList, j, videoSessionEvent);
    }

    private static void recordPlaybackEndTime(LinkedList<Pair<Long, Long>> linkedList, long j, VideoSessionEvent videoSessionEvent) {
        if (!linkedList.isEmpty()) {
            Pair<Long, Long> last = linkedList.getLast();
            if (((Long) last.second).longValue() == -1) {
                linkedList.pollLast();
                linkedList.addLast(new Pair<>(last.first, Long.valueOf(j)));
            }
        }
    }

    /* access modifiers changed from: private */
    public static void updateHQPlaybackDurations(VideoAnalyticsSession videoAnalyticsSession, long j, VideoSessionEvent videoSessionEvent) {
        updatePlaybackDurations(videoAnalyticsSession.hqPlaybackTimeRangeMsList, videoAnalyticsSession.hqPlaybackDurationMsSampler, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0049, code lost:
        if (r9 <= r2.longValue()) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004b, code lost:
        r8.sample(r9 - r2.longValue());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void updatePlaybackDurations(java.util.LinkedList<android.util.Pair<java.lang.Long, java.lang.Long>> r7, com.oculus.video.analytics.DataSampler r8, long r9) {
        /*
            java.util.Iterator r0 = r7.iterator()
        L_0x0004:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0053
            java.lang.Object r1 = r0.next()
            android.util.Pair r1 = (android.util.Pair) r1
            java.lang.Object r2 = r1.first
            java.lang.Long r2 = (java.lang.Long) r2
            java.lang.Object r1 = r1.second
            java.lang.Long r1 = (java.lang.Long) r1
            long r3 = r1.longValue()
            r5 = -1
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x0043
            long r3 = r1.longValue()
            int r3 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r3 > 0) goto L_0x0043
            long r3 = r1.longValue()
            long r5 = r2.longValue()
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0004
            long r3 = r1.longValue()
            long r1 = r2.longValue()
            long r3 = r3 - r1
            r8.sample(r3)
            goto L_0x0004
        L_0x0043:
            long r0 = r2.longValue()
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 <= 0) goto L_0x0053
            long r0 = r2.longValue()
            long r9 = r9 - r0
            r8.sample(r9)
        L_0x0053:
            r7.clear()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.analytics.VideoPlayerAnalytics.updatePlaybackDurations(java.util.LinkedList, com.oculus.video.analytics.DataSampler, long):void");
    }
}
