package com.oculus.video;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.debug.log.LoggingUtil;
import com.oculus.android.exoplayer2.BaseRenderer;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.ExoPlayerFactory;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.PlaybackParameters;
import com.oculus.android.exoplayer2.Player;
import com.oculus.android.exoplayer2.Renderer;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.audio.AudioProcessor;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.audio.MediaCodecAudioRenderer;
import com.oculus.android.exoplayer2.decoder.DecoderCounters;
import com.oculus.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.oculus.android.exoplayer2.drm.UnsupportedDrmException;
import com.oculus.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.metadata.MetadataOutput;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.MergingMediaSource;
import com.oculus.android.exoplayer2.source.SingleSampleMediaSource;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.source.dash.DashMediaSource;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.oculus.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.oculus.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.oculus.android.exoplayer2.text.Cue;
import com.oculus.android.exoplayer2.text.TextOutput;
import com.oculus.android.exoplayer2.text.TextRenderer;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.trackselection.TrackSelectionArray;
import com.oculus.android.exoplayer2.upstream.BandwidthMeter;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.upstream.cache.Cache;
import com.oculus.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor;
import com.oculus.android.exoplayer2.upstream.cache.SimpleCache;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.android.exoplayer2.video.VideoRendererEventListener;
import com.oculus.video.VideoPlayer;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.audio.AudioSpatializerController;
import com.oculus.video.audio.TbeAudioSpatializer;
import com.oculus.video.drm.OculusHttpMediaDrmCallback;
import com.oculus.video.extractor.OculusExtractorsFactory;
import com.oculus.video.extractor.source.OculusExtractorMediaSource;
import com.oculus.video.metadata.CameraMotionData;
import com.oculus.video.metadata.OculusMetadataDecoderFactory;
import com.oculus.video.metadata.OculusMetadataRenderer;
import com.oculus.video.projection.ProjectionType;
import com.oculus.video.projection.VideoProjection;
import com.oculus.video.renderer.FrameReleaser;
import com.oculus.video.renderer.OculusMediaCodecVideoRenderer;
import com.oculus.video.renderer.SpatialMediaCodecAudioDeviceRenderer;
import com.oculus.video.renderer.SpatialOpusAudioDeviceRenderer;
import com.oculus.video.renderer.VanillaMediaCodecVideoRenderer;
import com.oculus.video.source.dash.OculusDashChunkSource;
import com.oculus.video.source.dash.OculusDashManifest;
import com.oculus.video.source.dash.OculusDashManifestParser;
import com.oculus.video.source.hls.OculusHlsMediaSource;
import com.oculus.video.source.hls.OculusHlsPlaylistParser;
import com.oculus.video.source.hls.OculusHlsPlaylistTracker;
import com.oculus.video.trackselection.OculusTrackSelectorManager;
import com.oculus.video.ui.SubtitleSurfacePainter;
import com.oculus.video.upstream.HttpConnectionListener;
import com.oculus.video.upstream.OculusCacheDataSource;
import com.oculus.video.upstream.OculusDataSourceFactory;
import com.oculus.video.upstream.OculusHttpDataSourceFactory;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class OculusExoPlayer implements VideoPlayer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long CACHE_SIZE = 536870912;
    private static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    private static final String LAST_REPORTED_BITRATE_PREFRERENCE = "last_reported_bitrate";
    private static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final long MAX_SEEK_ERROR_US = 10000000;
    private static final String SECURITY_LEVEL_PROPERTY = "securityLevel";
    private static final String TAG = "OculusExoPlayer";
    private final AudioRendererEventListener audioRendererEventListener = new AudioRendererEventListener() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass3 */

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioDecoderInitialized(String str, long j, long j2) {
        }

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioDisabled(DecoderCounters decoderCounters) {
        }

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioEnabled(DecoderCounters decoderCounters) {
        }

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioSessionId(int i) {
        }

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioSinkUnderrun(int i, long j, long j2) {
        }

        @Override // com.oculus.android.exoplayer2.audio.AudioRendererEventListener
        public void onAudioInputFormatChanged(Format format) {
            if (OculusExoPlayer.this.audioRenderers != null) {
                Renderer renderer = null;
                Iterator it = OculusExoPlayer.this.audioRenderers.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Renderer renderer2 = (Renderer) it.next();
                    try {
                        if ((renderer2 instanceof BaseRenderer) && (((BaseRenderer) renderer2).supportsFormat(format) & 4) == 4) {
                            renderer = renderer2;
                            break;
                        }
                    } catch (ExoPlaybackException unused) {
                    }
                }
                OculusExoPlayer.this.eventListener.onAudioFormatChanged(format.sampleMimeType, renderer != null ? renderer.getClass().getName() : LoggingUtil.NO_HASHCODE);
            }
        }
    };
    private List<Renderer> audioRenderers;
    private final AudioSpatializerController audioSpatializerController;
    private DefaultBandwidthMeter bandwidthMeter;
    private final BandwidthMeter.EventListener bandwidthMeterListener = new BandwidthMeter.EventListener() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass6 */

        @Override // com.oculus.android.exoplayer2.upstream.BandwidthMeter.EventListener
        public void onBandwidthSample(int i, long j, long j2) {
            long bufferedPosition = (OculusExoPlayer.this.exoPlayer2 != null ? OculusExoPlayer.this.exoPlayer2.getBufferedPosition() : 0) - (OculusExoPlayer.this.exoPlayer2 != null ? OculusExoPlayer.this.exoPlayer2.getCurrentPosition() : 0);
            String str = OculusExoPlayer.TAG;
            Log.v(str, "onBandwidthSample " + (((double) Math.round((((double) j) / 1048576.0d) * 1000.0d)) / 1000.0d) + "MB " + i + "ms = " + (((double) Math.round((((double) j2) / 1048576.0d) * 1000.0d)) / 1000.0d) + "ms, buffered " + bufferedPosition + "ms");
            OculusExoPlayer.this.lastSlidingBitrate = j2;
            if (OculusExoPlayer.this.eventListener != null) {
                OculusExoPlayer.this.videoRendererEventListener.onDroppedFrames(0, 0);
                OculusExoPlayer.this.eventListener.onStreamingSample(j, j2, bufferedPosition, (long) i);
            }
        }
    };
    private String bitratePreferenceKey = null;
    private final MetadataOutput cameraMotionDataListener = new MetadataOutput() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass8 */

        @Override // com.oculus.android.exoplayer2.metadata.MetadataOutput
        public void onMetadata(Metadata metadata) {
            Metadata.Entry entry = (metadata == null || metadata.length() == 0) ? null : metadata.get(0);
            if (entry instanceof CameraMotionData) {
                OculusExoPlayer.this.eventListener.onCameraMotionData((CameraMotionData) entry);
            }
        }
    };
    private final Context context;
    private float currentVolume = 1.0f;
    private DashUpdateListener dashUpdateListener = null;
    private Cache dataCache;
    private final Handler eventHandler;
    private VideoPlayer.EventListener eventListener;
    protected ExoPlayer exoPlayer2;
    private final Player.EventListener exoPlayer2EventListener = new Player.EventListener() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass1 */

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onLoadingChanged(boolean z) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onRepeatModeChanged(int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onShuffleModeEnabledChanged(boolean z) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
            if (obj instanceof DashManifest) {
                if (OculusExoPlayer.this.dashUpdateListener == null) {
                    OculusExoPlayer oculusExoPlayer = OculusExoPlayer.this;
                    oculusExoPlayer.dashUpdateListener = new DashUpdateListener();
                    OculusExoPlayer.this.dashUpdateListener.awaitingInitChunk = OculusExoPlayer.this.playbackSettings.useFastSeekOnDashLoad(OculusExoPlayer.this.isSyncMedia);
                }
                OculusExoPlayer.this.dashUpdateListener.latestManifest = (DashManifest) obj;
            }
            if (OculusExoPlayer.this.dashUpdateListener == null || !OculusExoPlayer.this.dashUpdateListener.awaitingInitChunk) {
                OculusExoPlayer.this.notifyTimelineChange();
            }
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            OculusExoPlayer.this.trackSelectorManager.createTrackInfo();
            if (OculusExoPlayer.this.dashUpdateListener != null) {
                OculusExoPlayer.this.dashUpdateListener.reportDashUpdate(trackSelectionArray);
            }
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
            if (i == 2) {
                OculusExoPlayer.this.eventListener.onBuffering();
            } else if (i == 3) {
                OculusExoPlayer.this.eventListener.onReady();
            } else if (i == 4) {
                OculusExoPlayer.this.videoRendererEventListener.onDroppedFrames(0, 0);
                OculusExoPlayer.this.eventListener.onFinish();
                OculusExoPlayer.this.release();
            }
            if (i != 2) {
                OculusExoPlayer.this.eventListener.onStopBuffering();
            }
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPlayerError(ExoPlaybackException exoPlaybackException) {
            OculusExoPlayer.this.videoRendererEventListener.onDroppedFrames(0, 0);
            OculusExoPlayer.this.eventListener.onError(exoPlaybackException);
            OculusExoPlayer.this.release();
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onSeekProcessed() {
            OculusExoPlayer.this.eventListener.onSeekProcessed();
        }
    };
    private final OculusExtractorsFactory.EventListener extractorEventListener = new OculusExtractorsFactory.EventListener() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass9 */

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onMovieMetadataXml(String str) {
            if (OculusExoPlayer.this.audioRenderers != null) {
                for (Renderer renderer : OculusExoPlayer.this.audioRenderers) {
                    if (renderer instanceof OculusExtractorsFactory.EventListener) {
                        ((OculusExtractorsFactory.EventListener) renderer).onMovieMetadataXml(str);
                    }
                }
            }
        }

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onSphericalV1Xml(String str) {
            OculusExoPlayer.this.onTryExtractVideoProjection(VideoProjection.fromSphericalV1Xml(str));
        }

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onFindVideoSeekTimestamp(long j) {
            synchronized (OculusExoPlayer.this) {
                OculusExoPlayer.this.keyFrameTimestampsUs.add(Long.valueOf(j));
            }
        }
    };
    private Map<String, Double> fbVqmMap = null;
    private long frameDropReportStartTimeMs;
    private boolean hasRemoteAES128IV = false;
    private boolean hasRemoteAES128Key = false;
    private boolean isMuted = false;
    private boolean isSyncMedia = false;
    private final SortedSet<Long> keyFrameTimestampsUs = new TreeSet();
    private long lastRecordedConsumedFrameCount;
    private long lastRecordedReleasedFrameCount;
    private long lastSlidingBitrate = -1;
    private final HttpConnectionListener<Object> networkListener = new HttpConnectionListener<Object>() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass5 */

        @Override // com.oculus.video.upstream.HttpConnectionListener
        public void onConnect(String str, String str2, int i, long j) {
            String str3;
            if (OculusExoPlayer.this.eventListener != null) {
                try {
                    str3 = InetAddress.getByName(new URL(str).getHost()).getHostAddress();
                } catch (MalformedURLException | UnknownHostException unused) {
                    str3 = "";
                }
                OculusExoPlayer.this.eventListener.onHttpConnect(str, str3, str2, i, j);
            }
        }

        @Override // com.oculus.android.exoplayer2.upstream.TransferListener
        public void onTransferStart(Object obj, DataSpec dataSpec) {
            if (OculusExoPlayer.this.bandwidthMeter != null) {
                OculusExoPlayer.this.bandwidthMeter.onTransferStart(obj, dataSpec);
            }
        }

        @Override // com.oculus.android.exoplayer2.upstream.TransferListener
        public void onBytesTransferred(Object obj, int i) {
            if (OculusExoPlayer.this.bandwidthMeter != null) {
                OculusExoPlayer.this.bandwidthMeter.onBytesTransferred(obj, i);
            }
        }

        @Override // com.oculus.android.exoplayer2.upstream.TransferListener
        public void onTransferEnd(Object obj) {
            if (OculusExoPlayer.this.bandwidthMeter != null) {
                OculusExoPlayer.this.bandwidthMeter.onTransferEnd(obj);
            }
        }
    };
    private final OculusDashChunkSource.Callback oculusDashChunkSourceCallback = new OculusDashChunkSource.Callback() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass2 */

        @Override // com.oculus.video.source.dash.OculusDashChunkSource.Callback
        public boolean shouldUseAbr4Vbr() {
            return OculusExoPlayer.this.playbackSettings.useABR4VBR();
        }

        @Override // com.oculus.video.source.dash.OculusDashChunkSource.Callback
        public void onVideoSegmentIndexLoaded() {
            if (OculusExoPlayer.this.dashUpdateListener == null) {
                OculusExoPlayer oculusExoPlayer = OculusExoPlayer.this;
                oculusExoPlayer.dashUpdateListener = new DashUpdateListener();
                OculusExoPlayer.this.dashUpdateListener.awaitingInitChunk = false;
            } else if (OculusExoPlayer.this.dashUpdateListener.awaitingInitChunk) {
                OculusExoPlayer.this.dashUpdateListener.awaitingInitChunk = false;
                OculusExoPlayer.this.notifyTimelineChange();
            }
        }
    };
    private final Settings playbackSettings;
    PrimaryHlsPlaylistListener primaryHlsPlaylistListener = new PrimaryHlsPlaylistListener();
    private Map<String, ProjectionType> projectionMap = null;
    private VideoProjection projectionMetadata;
    private final TextOutput subtitleOutput = new TextOutput() {
        /* class com.oculus.video.OculusExoPlayer.AnonymousClass4 */

        @Override // com.oculus.android.exoplayer2.text.TextOutput
        public void onCues(List<Cue> list) {
            Surface subtitleSurface = OculusExoPlayer.this.eventListener.getSubtitleSurface();
            if (subtitleSurface != null) {
                OculusExoPlayer.this.subtitleSurfacePainter.setCues(list, subtitleSurface);
            }
        }
    };
    private final SubtitleSurfacePainter subtitleSurfacePainter;
    private Timeline.Period timelinePeriod = new Timeline.Period();
    private final AtomicLong totalConsumedFrameCount = new AtomicLong();
    private OculusTrackSelectorManager trackSelectorManager;
    private DecoderCounters videoDecoderCounters;
    private Renderer videoRenderer;
    protected VideoRendererEventListener videoRendererEventListener = new OculusVideoRendererEventListener();
    private Surface videoSurface;

    @Override // com.oculus.video.VideoPlayer
    public String getName() {
        return TAG;
    }

    OculusExoPlayer(Context context2, AudioSpatializerController audioSpatializerController2, Handler handler, Settings settings) {
        this.context = context2;
        this.audioSpatializerController = audioSpatializerController2;
        this.subtitleSurfacePainter = new SubtitleSurfacePainter(context2);
        this.playbackSettings = settings;
        this.eventHandler = handler;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyTimelineChange() {
        this.eventListener.onTimelineChanged(getDurationMs(), getCurrentPositionMs(), isSeekable());
    }

    protected class OculusVideoRendererEventListener implements OculusMediaCodecVideoRenderer.Callback {
        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onRenderedFirstFrame(Surface surface) {
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoDecoderInitialized(String str, long j, long j2) {
        }

        protected OculusVideoRendererEventListener() {
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoEnabled(DecoderCounters decoderCounters) {
            if (OculusExoPlayer.this.videoDecoderCounters == null) {
                OculusExoPlayer.this.lastRecordedReleasedFrameCount = 0;
                OculusExoPlayer.this.videoDecoderCounters = decoderCounters;
                OculusExoPlayer.this.frameDropReportStartTimeMs = System.currentTimeMillis();
            }
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoInputFormatChanged(Format format) {
            String str = format.sampleMimeType != null ? format.sampleMimeType : format.containerMimeType;
            OculusExoPlayer.this.onTryExtractVideoProjection(VideoProjection.fromSphericalV2(format));
            OculusExoPlayer.this.eventListener.onVideoFormatChanged(format.width, format.height, format.pixelWidthHeightRatio == -1.0f ? 1.0f : format.pixelWidthHeightRatio, format.rotationDegrees == -1 ? 0 : format.rotationDegrees, OculusExoPlayer.this.projectionMetadata, format.frameRate == -1.0f ? 0.0f : format.frameRate, format.bitrate, str, OculusExoPlayer.this.videoRenderer == null ? "" : OculusExoPlayer.this.videoRenderer.getClass().getName(), (OculusExoPlayer.this.fbVqmMap == null || !OculusExoPlayer.this.fbVqmMap.containsKey(format.id)) ? 0.0d : ((Double) OculusExoPlayer.this.fbVqmMap.get(format.id)).doubleValue());
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onDroppedFrames(int i, long j) {
            long j2;
            int i2;
            long remainingReleasedFrameCount = OculusExoPlayer.this.getRemainingReleasedFrameCount();
            long remainingConsumedFrameCount = OculusExoPlayer.this.getRemainingConsumedFrameCount();
            if (remainingConsumedFrameCount > 0) {
                long max = Math.max(remainingReleasedFrameCount - remainingConsumedFrameCount, 0L);
                OculusExoPlayer.this.lastRecordedConsumedFrameCount -= Math.max(remainingConsumedFrameCount - remainingReleasedFrameCount, 0L);
                i2 = i;
                j2 = max;
            } else {
                i2 = i;
                j2 = 0;
            }
            long j3 = (long) i2;
            long j4 = remainingReleasedFrameCount + j3;
            long currentTimeMillis = System.currentTimeMillis();
            long j5 = currentTimeMillis - OculusExoPlayer.this.frameDropReportStartTimeMs;
            OculusExoPlayer.this.frameDropReportStartTimeMs = currentTimeMillis;
            if (!(!(OculusExoPlayer.this.videoRenderer instanceof OculusMediaCodecVideoRenderer) || OculusExoPlayer.this.trackSelectorManager == null || OculusExoPlayer.this.trackSelectorManager.getTrackSelector() == null)) {
                OculusExoPlayer.this.trackSelectorManager.getTrackSelector().onDroppedDelayedFrame(j3, j4, j5);
            }
            OculusExoPlayer.this.eventListener.onDroppedFrames(j5, j3, j2, j4);
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoSizeChanged(int i, int i2, int i3, float f) {
            if (!(OculusExoPlayer.this.trackSelectorManager == null || OculusExoPlayer.this.trackSelectorManager.getTrackSelector() == null)) {
                OculusExoPlayer.this.trackSelectorManager.getTrackSelector().onVideoSizeChange(i, i2);
            }
            Rect rect = null;
            if (OculusExoPlayer.this.videoRenderer instanceof OculusMediaCodecVideoRenderer) {
                rect = ((OculusMediaCodecVideoRenderer) OculusExoPlayer.this.videoRenderer).getCurrentFrameCropRect();
            }
            OculusExoPlayer.this.eventListener.onVideoSizeChanged(i, i2, f, rect);
        }

        @Override // com.oculus.android.exoplayer2.video.VideoRendererEventListener
        public void onVideoDisabled(DecoderCounters decoderCounters) {
            if (decoderCounters == OculusExoPlayer.this.videoDecoderCounters) {
                onDroppedFrames(0, 0);
                OculusExoPlayer.this.videoDecoderCounters = null;
                OculusExoPlayer.this.frameDropReportStartTimeMs = 0;
            }
        }

        @Override // com.oculus.video.renderer.OculusMediaCodecVideoRenderer.Callback
        public ProjectionType getProjectionType(Format format) {
            ProjectionType projectionType = OculusExoPlayer.this.projectionMetadata != null ? OculusExoPlayer.this.projectionMetadata.getProjectionData().getProjectionType() : ProjectionType.UNKNOWN;
            return (OculusExoPlayer.this.projectionMap == null || !OculusExoPlayer.this.projectionMap.containsKey(format.id)) ? projectionType : (ProjectionType) OculusExoPlayer.this.projectionMap.get(format.id);
        }
    }

    private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManager(Video video) {
        if (!video.getDrmProxyUrl().isEmpty()) {
            try {
                return buildDrmSessionManager(video, C.WIDEVINE_UUID, null);
            } catch (UnsupportedDrmException e) {
                Log.e(TAG, Util.SDK_INT < 18 ? "DRM not supported" : e.reason == 1 ? "DRM Scheme not supported" : "Unknown DRM Issue");
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void appendProfileIndex(@NonNull Map<String, Object> map, @NonNull List<Map<String, Object>> list) {
        if (map.containsKey(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE) && map.containsKey(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH)) {
            int intValue = ((Integer) map.get(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE)).intValue();
            int i = 0;
            for (Map<String, Object> map2 : list) {
                if (map2.containsKey(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE) && map2.containsKey(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH) && ((Integer) map2.get(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE)).intValue() > intValue) {
                    i++;
                }
            }
            map.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE_INDEX, Integer.valueOf(i));
        }
    }

    /* access modifiers changed from: private */
    @NonNull
    public static Map<String, Object> getFormatData(@Nullable Format format) {
        HashMap hashMap = new HashMap();
        if (format == null) {
            return hashMap;
        }
        if (format.width > 0) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH, Integer.valueOf(format.width));
        }
        if (format.height > 0) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_HEIGHT, Integer.valueOf(format.height));
        }
        if (format.bitrate > 0) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE, Integer.valueOf(format.bitrate));
        }
        if (format.frameRate > 0.0f) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_FPS, Integer.valueOf((int) (format.frameRate + 0.5f)));
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private Map<String, Object> getMediaPlaylistData(@Nullable HlsMediaPlaylist hlsMediaPlaylist) {
        HashMap hashMap = new HashMap();
        if (hlsMediaPlaylist == null) {
            return hashMap;
        }
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_TARGET_DURATION_US, Long.valueOf(hlsMediaPlaylist.targetDurationUs));
        int i = hlsMediaPlaylist.playlistType;
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_HLS_TYPE, i != 1 ? i != 2 ? "UNKNOWN" : "EVENT" : "VOD");
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_EMBEDDED_UTC, Integer.valueOf(hlsMediaPlaylist.hasProgramDateTime ? 1 : 0));
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_REMOTE_KEY, Integer.valueOf(this.hasRemoteAES128Key ? 1 : 0));
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_REMOTE_IV, Integer.valueOf(this.hasRemoteAES128IV ? 1 : 0));
        long size = hlsMediaPlaylist.segments != null ? (long) hlsMediaPlaylist.segments.size() : 0;
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_SEGMENT_COUNT, Long.valueOf(size));
        if (size > 0) {
            HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(0);
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_EMBEDDED_KEY, Integer.valueOf(!TextUtils.isEmpty(segment.fullSegmentEncryptionKeyUri) ? 1 : 0));
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_EMBEDDED_IV, Integer.valueOf(!TextUtils.isEmpty(segment.encryptionIV) ? 1 : 0));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public class PrimaryHlsPlaylistListener implements OculusHlsPlaylistTracker.PrimaryPlaylistListener {
        protected PrimaryHlsPlaylistListener() {
        }

        @Override // com.oculus.video.source.hls.OculusHlsPlaylistTracker.PrimaryPlaylistListener
        public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist, HlsMasterPlaylist.HlsUrl hlsUrl, HlsMasterPlaylist hlsMasterPlaylist) {
            if (!(hlsUrl == null || hlsUrl.format == null)) {
                ArrayList arrayList = new ArrayList();
                List<HlsMasterPlaylist.HlsUrl> list = hlsMasterPlaylist.variants;
                for (int i = 0; i < list.size(); i++) {
                    Map formatData = OculusExoPlayer.getFormatData(list.get(i).format);
                    if (formatData.size() != 0) {
                        arrayList.add(formatData);
                    }
                }
                Map<String, Object> formatData2 = OculusExoPlayer.getFormatData(hlsUrl.format);
                formatData2.putAll(OculusExoPlayer.this.getMediaPlaylistData(hlsMediaPlaylist));
                if (!(arrayList.size() == 0 || formatData2.size() == 0)) {
                    OculusExoPlayer.appendProfileIndex(formatData2, arrayList);
                    OculusExoPlayer.this.eventListener.onAdaptivePlaylistUpdate(formatData2, arrayList);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @NonNull
    private Map<String, Object> getDashPlaylistData(@Nullable DashManifest dashManifest) {
        HashMap hashMap = new HashMap();
        if (dashManifest == null) {
            return hashMap;
        }
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_DASH_IS_DYNAMIC, Boolean.valueOf(dashManifest.dynamic));
        hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_HAS_EMBEDDED_UTC, Integer.valueOf(dashManifest.utcTiming != null ? 1 : 0));
        if (dashManifest.minBufferTimeMs != C.TIME_UNSET) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_DASH_MIN_BUFFER_TIME_MS, Long.valueOf(dashManifest.minBufferTimeMs));
        }
        if (dashManifest.minUpdatePeriodMs != C.TIME_UNSET) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_DASH_MIN_UPDATE_PERIOD_MS, Long.valueOf(dashManifest.minUpdatePeriodMs));
        }
        if (dashManifest.timeShiftBufferDepthMs != C.TIME_UNSET) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_DASH_TIME_SHIFT_BUFFER_DEPTH_MS, Long.valueOf(dashManifest.timeShiftBufferDepthMs));
        }
        if (dashManifest.timeShiftBufferDepthMs != C.TIME_UNSET) {
            hashMap.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_DASH_SUGGESTED_PRESENTATION_DELAY_MS, Long.valueOf(dashManifest.suggestedPresentationDelayMs));
        }
        return hashMap;
    }

    /* access modifiers changed from: private */
    public class DashUpdateListener {
        boolean awaitingInitChunk;
        DashManifest latestManifest;

        private DashUpdateListener() {
            this.awaitingInitChunk = false;
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void reportDashUpdate(TrackSelectionArray trackSelectionArray) {
            if (!(this.latestManifest == null || trackSelectionArray == null)) {
                TrackSelection[] all = trackSelectionArray.getAll();
                ArrayList arrayList = new ArrayList();
                int i = 0;
                for (TrackSelection trackSelection : all) {
                    if (trackSelection != null) {
                        TrackGroup trackGroup = trackSelection.getTrackGroup();
                        for (int i2 = 0; i2 < trackGroup.length; i2++) {
                            Map formatData = OculusExoPlayer.getFormatData(trackGroup.getFormat(i2));
                            if (formatData.size() != 0) {
                                arrayList.add(formatData);
                            }
                        }
                    }
                }
                if (arrayList.size() != 0) {
                    Format format = null;
                    int length = all.length;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        TrackSelection trackSelection2 = all[i];
                        if (trackSelection2 != null && OculusExoPlayer.getFormatData(trackSelection2.getSelectedFormat()).size() > 0) {
                            format = trackSelection2.getSelectedFormat();
                            break;
                        }
                        i++;
                    }
                    Map<String, Object> formatData2 = OculusExoPlayer.getFormatData(format);
                    formatData2.putAll(OculusExoPlayer.this.getDashPlaylistData(this.latestManifest));
                    if (formatData2.size() != 0) {
                        OculusExoPlayer.appendProfileIndex(formatData2, arrayList);
                        OculusExoPlayer.this.eventListener.onAdaptivePlaylistUpdate(formatData2, arrayList);
                    }
                }
            }
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void start(Video video, boolean z, Surface surface, VideoPlayer.EventListener eventListener2) {
        stop();
        this.eventListener = eventListener2;
        this.videoSurface = surface;
        boolean z2 = true;
        this.hasRemoteAES128Key = !TextUtils.isEmpty(video.getAes128Key());
        this.hasRemoteAES128IV = !TextUtils.isEmpty(video.getAes128Iv());
        this.isSyncMedia = video.isSyncMedia();
        eventListener2.onStartVideo();
        try {
            this.bitratePreferenceKey = getBitratePreferenceKey(video);
            String videoUrl = video.getVideoUrl();
            if (TextUtils.isEmpty(videoUrl) || !videoUrl.startsWith("file://")) {
                z2 = false;
            }
            this.bandwidthMeter = new DefaultBandwidthMeter(this.eventHandler, z2 ? null : this.bandwidthMeterListener, 2000);
            Renderer[] buildRenderers = buildRenderers(buildDrmSessionManager(video), video);
            buildTrackSelectorManager(video);
            buildExoPlayer2(buildRenderers, video);
            this.exoPlayer2.prepare(buildMediaSource(video));
            this.exoPlayer2.setPlayWhenReady(z);
        } catch (Exception e) {
            eventListener2.onError(e);
            release();
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void stop() {
        release();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onTryExtractVideoProjection(VideoProjection videoProjection) {
        if (videoProjection != null) {
            this.eventListener.onExtractProjectionMetadata(videoProjection);
        }
        this.projectionMetadata = VideoProjection.selectPreferred(this.projectionMetadata, videoProjection);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void resetPlaybackProperties() {
        this.bandwidthMeter = null;
        this.videoDecoderCounters = null;
        this.frameDropReportStartTimeMs = 0;
        this.exoPlayer2 = null;
        this.keyFrameTimestampsUs.clear();
        this.videoRenderer = null;
        this.audioRenderers = null;
        this.trackSelectorManager.reset();
        this.isMuted = false;
        this.currentVolume = 1.0f;
        this.totalConsumedFrameCount.set(0);
        this.lastRecordedReleasedFrameCount = 0;
        this.lastRecordedConsumedFrameCount = 0;
        this.fbVqmMap = null;
        this.projectionMap = null;
        this.projectionMetadata = null;
        this.isSyncMedia = false;
        this.hasRemoteAES128Key = false;
        this.hasRemoteAES128IV = false;
        this.bitratePreferenceKey = null;
        this.lastSlidingBitrate = -1;
        this.timelinePeriod = null;
        this.dashUpdateListener = null;
    }

    @Override // com.oculus.video.VideoPlayer
    public void release() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer != null) {
            exoPlayer.stop();
            this.eventHandler.post(new Runnable() {
                /* class com.oculus.video.OculusExoPlayer.AnonymousClass7 */

                public void run() {
                    if (OculusExoPlayer.this.exoPlayer2 != null) {
                        OculusExoPlayer.this.exoPlayer2.release();
                        OculusExoPlayer.this.videoRendererEventListener.onDroppedFrames(0, 0);
                        if (OculusExoPlayer.this.lastSlidingBitrate != -1) {
                            VideoPlayer.EventListener eventListener = OculusExoPlayer.this.eventListener;
                            String str = OculusExoPlayer.this.bitratePreferenceKey;
                            eventListener.setStringPreference(str, "" + OculusExoPlayer.this.lastSlidingBitrate);
                        }
                        OculusExoPlayer.this.resetPlaybackProperties();
                        OculusExoPlayer.this.eventListener.onRelease();
                    }
                }
            });
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void pause() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(false);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void resume() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer != null) {
            exoPlayer.setPlayWhenReady(true);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public synchronized void seekTo(long j) {
        long j2;
        if (this.exoPlayer2 != null) {
            boolean z = true;
            if (!this.playbackSettings.useFastSeek(this.isSyncMedia) || this.keyFrameTimestampsUs.size() <= 1) {
                z = false;
            }
            if (z) {
                long j3 = j * 1000;
                SortedSet<Long> headSet = this.keyFrameTimestampsUs.headSet(Long.valueOf(j3));
                long longValue = headSet.size() == 0 ? 0 : headSet.last().longValue();
                SortedSet<Long> tailSet = this.keyFrameTimestampsUs.tailSet(Long.valueOf(j3));
                long longValue2 = tailSet.size() == 0 ? longValue : tailSet.first().longValue();
                long abs = Math.abs(j3 - longValue);
                long abs2 = Math.abs(longValue2 - j3);
                j2 = (abs2 > MAX_SEEK_ERROR_US || abs <= abs2) ? (abs > MAX_SEEK_ERROR_US || abs > abs2) ? j : (longValue / 1000) + 10 : (longValue2 / 1000) + 10;
            } else {
                j2 = j;
            }
            this.exoPlayer2.seekTo(j2);
        }
    }

    private String getBitratePreferenceKey(Video video) {
        if (video == null) {
            return LAST_REPORTED_BITRATE_PREFRERENCE;
        }
        if (video.getVideoUri() == null || video.getVideoUri().getHost() == null) {
            return "last_reported_bitrate|" + video.getProvider() + "." + video.getChannel();
        }
        return "last_reported_bitrate|" + video.getVideoUri().getHost();
    }

    private void buildTrackSelectorManager(Video video) {
        long j;
        try {
            j = Long.parseLong(this.eventListener.getStringPreference(this.bitratePreferenceKey));
        } catch (Exception unused) {
            j = -1;
        }
        this.trackSelectorManager = new OculusTrackSelectorManager(this.playbackSettings, this.bandwidthMeter, video.getAudioChannelLayout(), j);
    }

    private void buildExoPlayer2(Renderer[] rendererArr, Video video) {
        if (this.exoPlayer2 == null) {
            this.exoPlayer2 = ExoPlayerFactory.newInstance(rendererArr, this.trackSelectorManager.getTrackSelector(), new OculusLoadControl());
            this.exoPlayer2.addListener(this.exoPlayer2EventListener);
            if (this.playbackSettings.useFastSeek(this.isSyncMedia)) {
                this.exoPlayer2.setSeekParameters(new SeekParameters(MAX_SEEK_ERROR_US, MAX_SEEK_ERROR_US));
            }
            this.exoPlayer2.createMessage(this.videoRenderer).setType(1).setPayload(this.videoSurface).send();
        }
    }

    private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManager(Video video, UUID uuid, String[] strArr) throws UnsupportedDrmException {
        if (Util.SDK_INT < 18 || video.getDrmProxyUrl().isEmpty()) {
            return null;
        }
        OculusHttpMediaDrmCallback oculusHttpMediaDrmCallback = new OculusHttpMediaDrmCallback(video.getDrmProxyUrl(), buildHttpDataSourceFactory(video, false), video.getDrmResponseFormat());
        if (strArr != null) {
            for (int i = 0; i < strArr.length - 1; i += 2) {
                oculusHttpMediaDrmCallback.setKeyRequestProperty(strArr[i], strArr[i + 1]);
            }
        }
        DefaultDrmSessionManager<FrameworkMediaCrypto> newFrameworkInstance = DefaultDrmSessionManager.newFrameworkInstance(uuid, oculusHttpMediaDrmCallback, null, this.eventHandler, null);
        if (!video.getDrmSecurityLevel().isEmpty()) {
            try {
                newFrameworkInstance.setPropertyString(SECURITY_LEVEL_PROPERTY, video.getDrmSecurityLevel());
            } catch (Exception unused) {
                Log.e(TAG, "Failed to set securityLevel property for defaultDrmSessionManager");
            }
        }
        return newFrameworkInstance;
    }

    private HttpDataSource.Factory buildHttpDataSourceFactory(Video video, boolean z) {
        String userAgent = this.eventListener.getUserAgent();
        if (TextUtils.isEmpty(userAgent)) {
            userAgent = "";
        }
        String str = TAG;
        Log.d(str, "UserAgent = " + userAgent);
        OculusHttpDataSourceFactory oculusHttpDataSourceFactory = new OculusHttpDataSourceFactory(userAgent, z ? this.networkListener : null);
        JSONObject httpRequestHeaders = video.getHttpRequestHeaders();
        if (httpRequestHeaders != null) {
            Iterator<String> keys = httpRequestHeaders.keys();
            HttpDataSource.RequestProperties defaultRequestProperties = oculusHttpDataSourceFactory.getDefaultRequestProperties();
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    defaultRequestProperties.set(next, httpRequestHeaders.getString(next));
                } catch (JSONException unused) {
                }
            }
        }
        return oculusHttpDataSourceFactory;
    }

    private Renderer getVideoRenderer(Video video, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        if (this.playbackSettings.useVideoDirectDecoder()) {
            return new OculusMediaCodecVideoRenderer(this.context, MediaCodecSelector.DEFAULT, 5000, video.hasCameraMotionData(), this.playbackSettings.useEarlyFrameRelease(), drmSessionManager, false, this.eventHandler, this.videoRendererEventListener);
        }
        return new VanillaMediaCodecVideoRenderer(this.context, MediaCodecSelector.DEFAULT, 5000, drmSessionManager, false, this.eventHandler, this.videoRendererEventListener, MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY);
    }

    private List<Renderer> getAudioRenderers(Video video, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager) {
        ArrayList arrayList = new ArrayList();
        AudioChannelLayout audioChannelLayout = video.getAudioChannelLayout();
        if (!audioChannelLayout.isSpatializationLayout()) {
            arrayList.add(new FfmpegAudioRenderer(this.eventHandler, this.audioRendererEventListener, new AudioProcessor[0]));
        }
        if (this.playbackSettings.useAudioSpatialization(video) && TbeAudioSpatializer.handlesAudioChannelLayout(audioChannelLayout)) {
            TbeAudioSpatializer tbeAudioSpatializer = new TbeAudioSpatializer(audioChannelLayout);
            tbeAudioSpatializer.setAudioSpatializerController(this.audioSpatializerController);
            if (audioChannelLayout == AudioChannelLayout.TBE_4_4_2) {
                int i = 0;
                while (i < 3) {
                    arrayList.add(new SpatialMediaCodecAudioDeviceRenderer(null, true, this.eventHandler, this.audioRendererEventListener, tbeAudioSpatializer, video.getPrimaryAudioTrackIndex() + i, i == 0));
                    i++;
                }
                return arrayList;
            }
            if (drmSessionManager == null && !audioChannelLayout.isVirtualizationLayout()) {
                arrayList.add(new SpatialOpusAudioDeviceRenderer(this.eventHandler, this.audioRendererEventListener, tbeAudioSpatializer));
            }
            arrayList.add(new SpatialMediaCodecAudioDeviceRenderer(drmSessionManager, true, this.eventHandler, this.audioRendererEventListener, tbeAudioSpatializer));
        }
        arrayList.add(new MediaCodecAudioRenderer(MediaCodecSelector.DEFAULT, drmSessionManager, true, this.eventHandler, this.audioRendererEventListener));
        return arrayList;
    }

    private Renderer[] buildRenderers(DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Video video) {
        ArrayList arrayList = new ArrayList();
        this.videoRenderer = getVideoRenderer(video, drmSessionManager);
        arrayList.add(this.videoRenderer);
        this.audioRenderers = getAudioRenderers(video, drmSessionManager);
        arrayList.addAll(this.audioRenderers);
        arrayList.add(new TextRenderer(this.subtitleOutput, this.eventHandler.getLooper()));
        arrayList.add(new OculusMetadataRenderer(this.cameraMotionDataListener, this.eventHandler.getLooper(), new OculusMetadataDecoderFactory()));
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    private MediaSource buildMediaSource(Video video) throws IOException {
        AnonymousClass10 r0;
        MediaSource mediaSource;
        if (this.playbackSettings.useDataCache(video)) {
            this.dataCache = new SimpleCache(new File(this.context.getCacheDir().getAbsolutePath() + File.separator + getClass().getName()), new LeastRecentlyUsedCacheEvictor(CACHE_SIZE));
            r0 = new OculusCacheDataSource.Callback() {
                /* class com.oculus.video.OculusExoPlayer.AnonymousClass10 */

                @Override // com.oculus.video.upstream.OculusCacheDataSource.Callback
                public Cache getCache() {
                    return OculusExoPlayer.this.dataCache;
                }

                @Override // com.oculus.video.upstream.OculusCacheDataSource.Callback
                public long getCurrentPositionMs() {
                    return OculusExoPlayer.this.getCurrentPositionMs();
                }
            };
        } else {
            r0 = null;
        }
        OculusDataSourceFactory oculusDataSourceFactory = new OculusDataSourceFactory(this.context, this.bandwidthMeter, buildHttpDataSourceFactory(video, true), r0);
        OculusDataSourceFactory oculusDataSourceFactory2 = new OculusDataSourceFactory(this.context, null, buildHttpDataSourceFactory(video, false), null);
        if (video.getDashManifest() != null) {
            mediaSource = buildDashManifestMediaSource(video.getDashManifest(), oculusDataSourceFactory);
        } else {
            mediaSource = buildUriMediaSource(video, null, oculusDataSourceFactory, oculusDataSourceFactory2);
        }
        ArrayList<MediaSource> buildSubtitleMediaSource = buildSubtitleMediaSource(video, oculusDataSourceFactory);
        ArrayList arrayList = new ArrayList();
        arrayList.add(mediaSource);
        arrayList.addAll(buildSubtitleMediaSource);
        return arrayList.size() > 1 ? new MergingMediaSource((MediaSource[]) arrayList.toArray(new MediaSource[arrayList.size()])) : mediaSource;
    }

    private MediaSource buildDashManifestMediaSource(DashManifest dashManifest, DataSource.Factory factory) {
        if (dashManifest instanceof OculusDashManifest) {
            OculusDashManifest oculusDashManifest = (OculusDashManifest) dashManifest;
            onTryExtractVideoProjection(VideoProjection.fromFBProjection(oculusDashManifest));
            this.fbVqmMap = oculusDashManifest.fbVqmMap;
            this.projectionMap = oculusDashManifest.projectionMap;
        }
        return new DashMediaSource.Factory(new OculusDashChunkSource.Factory(factory, this.oculusDashChunkSourceCallback), null).createMediaSource(dashManifest, this.eventHandler, (MediaSourceEventListener) null);
    }

    private MediaSource buildUriMediaSource(Video video, String str, DataSource.Factory factory, DataSource.Factory factory2) {
        int i;
        Uri videoUri = video.getVideoUri();
        if (TextUtils.isEmpty(str)) {
            i = Util.inferContentType(videoUri);
        } else {
            i = Util.inferContentType("." + str);
        }
        if (i == 0) {
            return new DashMediaSource.Factory(new OculusDashChunkSource.Factory(factory, this.oculusDashChunkSourceCallback), factory2).setManifestParser(new OculusDashManifestParser()).createMediaSource(videoUri, this.eventHandler, (MediaSourceEventListener) null);
        }
        if (i == 1) {
            return new SsMediaSource.Factory(new DefaultSsChunkSource.Factory(factory), factory2).createMediaSource(videoUri, this.eventHandler, (MediaSourceEventListener) null);
        }
        if (i == 2) {
            return new OculusHlsMediaSource.Factory(factory).setPlaylistParser(new OculusHlsPlaylistParser(video.getAes128Key(), video.getAes128Iv())).createMediaSource(videoUri, this.eventHandler, null, this.primaryHlsPlaylistListener);
        }
        if (i == 3) {
            return new OculusExtractorMediaSource(this.context, videoUri, factory, new OculusExtractorsFactory().setExtractorEventListener(this.extractorEventListener), this.eventHandler, (OculusExtractorMediaSource.EventListener) null);
        }
        throw new IllegalStateException("Unsupported type: " + i);
    }

    private Uri buildSubtitleUri(String str) {
        if (str.startsWith("/")) {
            str = "file://" + str;
        }
        return Uri.parse(str);
    }

    private ArrayList<MediaSource> buildSubtitleMediaSource(Video video, DataSource.Factory factory) {
        ArrayList<MediaSource> arrayList = new ArrayList<>();
        JSONObject subtitleTracks = video.getSubtitleTracks();
        if (subtitleTracks != null) {
            Iterator<String> keys = subtitleTracks.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                String extractSubtitleMimeType = Video.extractSubtitleMimeType(next);
                if (extractSubtitleMimeType != null) {
                    try {
                        arrayList.add(new SingleSampleMediaSource.Factory(factory).createMediaSource(buildSubtitleUri(next), Format.createTextSampleFormat(next, extractSubtitleMimeType, 0, subtitleTracks.getString(next)), C.TIME_UNSET));
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.oculus.video.VideoPlayer
    public void setMute(boolean z) {
        List<Renderer> list;
        float f = z ? 0.0f : this.currentVolume;
        if (!(this.exoPlayer2 == null || (list = this.audioRenderers) == null || list.size() <= 0)) {
            for (Renderer renderer : this.audioRenderers) {
                this.exoPlayer2.sendMessages(new ExoPlayer.ExoPlayerMessage(renderer, 2, Float.valueOf(f)));
            }
        }
        this.isMuted = z;
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isMuted() {
        return this.isMuted;
    }

    @Override // com.oculus.video.VideoPlayer
    public void setVolume(float f) {
        this.currentVolume = f;
        setMute(this.isMuted);
    }

    @Override // com.oculus.video.VideoPlayer
    public float getVolume() {
        return this.currentVolume;
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isPlaying() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        return exoPlayer != null && exoPlayer.getPlayWhenReady();
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isBuffering() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        return (exoPlayer == null ? 1 : exoPlayer.getPlaybackState()) == 2;
    }

    @Override // com.oculus.video.VideoPlayer
    public long getBufferedPositionMs() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer == null) {
            return -1;
        }
        return exoPlayer.getBufferedPosition();
    }

    @Override // com.oculus.video.VideoPlayer
    public long getCurrentPositionMs() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer == null) {
            return 0;
        }
        return exoPlayer.getCurrentPosition();
    }

    @Override // com.oculus.video.VideoPlayer
    public long getCurrentEpochTimePositionMs() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer == null) {
            return 0;
        }
        long currentPosition = exoPlayer.getCurrentPosition();
        Timeline currentTimeline = this.exoPlayer2.getCurrentTimeline();
        if (currentPosition <= 0 || currentTimeline.isEmpty()) {
            return currentPosition;
        }
        currentTimeline.getPeriod(this.exoPlayer2.getCurrentPeriodIndex(), this.timelinePeriod);
        long positionInWindowMs = currentPosition - this.timelinePeriod.getPositionInWindowMs();
        Object currentManifest = this.exoPlayer2.getCurrentManifest();
        DashManifest dashManifest = currentManifest instanceof DashManifest ? (DashManifest) currentManifest : null;
        return (dashManifest == null || dashManifest.publishTimeMs == C.TIME_UNSET) ? positionInWindowMs : positionInWindowMs + dashManifest.publishTimeMs;
    }

    @Override // com.oculus.video.VideoPlayer
    public long getDurationMs() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        if (exoPlayer == null) {
            return 0;
        }
        return exoPlayer.getDuration();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getRemainingReleasedFrameCount() {
        DecoderCounters decoderCounters = this.videoDecoderCounters;
        if (decoderCounters == null) {
            return 0;
        }
        decoderCounters.ensureUpdated();
        long j = (long) this.videoDecoderCounters.renderedOutputBufferCount;
        long j2 = j - this.lastRecordedReleasedFrameCount;
        this.lastRecordedReleasedFrameCount = j;
        return j2;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getRemainingConsumedFrameCount() {
        long j = this.totalConsumedFrameCount.get();
        long j2 = j - this.lastRecordedConsumedFrameCount;
        this.lastRecordedConsumedFrameCount = j;
        return j2;
    }

    @Override // com.oculus.video.VideoPlayer
    public void onBeforeRender(long j) {
        Renderer renderer;
        if (this.exoPlayer2 != null && (renderer = this.videoRenderer) != null && (renderer instanceof OculusMediaCodecVideoRenderer)) {
            ((OculusMediaCodecVideoRenderer) renderer).onBeforeRender(j);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public void onRender(boolean z) {
        Renderer renderer;
        if (z) {
            this.totalConsumedFrameCount.incrementAndGet();
        }
        if (this.exoPlayer2 != null && (renderer = this.videoRenderer) != null && (renderer instanceof OculusMediaCodecVideoRenderer)) {
            ((OculusMediaCodecVideoRenderer) renderer).onRender(z);
        }
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean selectSubtitle(String str) {
        return this.trackSelectorManager.selectSubtitleTrack(str);
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean selectVideoTrack(String str) {
        return this.trackSelectorManager.selectVideoTrack(str);
    }

    @Override // com.oculus.video.VideoPlayer
    public boolean isSeekable() {
        ExoPlayer exoPlayer = this.exoPlayer2;
        return exoPlayer != null && exoPlayer.isCurrentWindowSeekable();
    }

    @Override // com.oculus.video.VideoPlayer
    public long getLastPresentationTimeUs() {
        Renderer renderer = this.videoRenderer;
        if (renderer instanceof FrameReleaser) {
            return ((FrameReleaser) renderer).getLastPresentationTimeUs();
        }
        return -1;
    }
}
