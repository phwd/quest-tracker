package com.oculus.video;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.ExoPlayerFactory;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.PlaybackParameters;
import com.oculus.android.exoplayer2.Player;
import com.oculus.android.exoplayer2.Renderer;
import com.oculus.android.exoplayer2.RendererCapabilities;
import com.oculus.android.exoplayer2.RendererConfiguration;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.source.dash.DashMediaSource;
import com.oculus.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.oculus.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.oculus.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.oculus.android.exoplayer2.trackselection.TrackSelectionArray;
import com.oculus.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.oculus.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.oculus.android.exoplayer2.util.MediaClock;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.extractor.OculusExtractorsFactory;
import com.oculus.video.extractor.source.OculusExtractorMediaSource;
import com.oculus.video.projection.VideoProjection;
import com.oculus.video.source.dash.OculusDashManifest;
import com.oculus.video.source.dash.OculusDashManifestParser;
import com.oculus.video.source.hls.OculusHlsMediaSource;
import com.oculus.video.source.hls.OculusHlsPlaylistParser;
import com.oculus.video.upstream.OculusDataSourceFactory;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONException;
import org.json.JSONObject;

/* access modifiers changed from: package-private */
public class VideoInfoExtractor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int[] TBE442_CHANNELS = {4, 4, 2};
    private final OculusExtractorsFactory.EventListener extractorListener = new OculusExtractorsFactory.EventListener() {
        /* class com.oculus.video.VideoInfoExtractor.AnonymousClass2 */

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onFindVideoSeekTimestamp(long j) {
        }

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onMovieMetadataXml(String str) {
        }

        @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
        public void onSphericalV1Xml(String str) {
            try {
                VideoInfoExtractor.this.video.setProjectionMetadata(VideoProjection.fromSphericalV1Xml(str));
            } catch (JSONException unused) {
            }
        }
    };
    private ExoPlayer extractorPlayer;
    private Listener listener;
    private final Renderer noOpRenderer = new Renderer() {
        /* class com.oculus.video.VideoInfoExtractor.AnonymousClass1 */
        private final MediaClock noOpMediaClock = new MediaClock() {
            /* class com.oculus.video.VideoInfoExtractor.AnonymousClass1.AnonymousClass2 */

            @Override // com.oculus.android.exoplayer2.util.MediaClock
            public PlaybackParameters getPlaybackParameters() {
                return null;
            }

            @Override // com.oculus.android.exoplayer2.util.MediaClock
            public long getPositionUs() {
                return 0;
            }

            @Override // com.oculus.android.exoplayer2.util.MediaClock
            public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
                return null;
            }
        };
        private final RendererCapabilities noOpRendererCapabilities = new RendererCapabilities() {
            /* class com.oculus.video.VideoInfoExtractor.AnonymousClass1.AnonymousClass1 */

            @Override // com.oculus.android.exoplayer2.RendererCapabilities
            public int getTrackType() {
                return -1;
            }

            @Override // com.oculus.android.exoplayer2.RendererCapabilities
            public int supportsFormat(Format format) throws ExoPlaybackException {
                return 0;
            }

            @Override // com.oculus.android.exoplayer2.RendererCapabilities
            public int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
                return 0;
            }
        };

        @Override // com.oculus.android.exoplayer2.Renderer
        public void disable() {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void enable(RendererConfiguration rendererConfiguration, Format[] formatArr, SampleStream sampleStream, long j, boolean z, long j2) throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public int getState() {
            return 0;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public SampleStream getStream() {
            return null;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public int getTrackType() {
            return -1;
        }

        @Override // com.oculus.android.exoplayer2.PlayerMessage.Target
        public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public boolean hasReadStreamToEnd() {
            return false;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public boolean isCurrentStreamFinal() {
            return false;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public boolean isEnded() {
            return false;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public boolean isReady() {
            return false;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void maybeThrowStreamError() throws IOException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void render(long j, long j2) throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void replaceStream(Format[] formatArr, SampleStream sampleStream, long j) throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void resetPosition(long j) throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void setCurrentStreamFinal() {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void setIndex(int i) {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void start() throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public void stop() throws ExoPlaybackException {
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public RendererCapabilities getCapabilities() {
            return this.noOpRendererCapabilities;
        }

        @Override // com.oculus.android.exoplayer2.Renderer
        public MediaClock getMediaClock() {
            return this.noOpMediaClock;
        }
    };
    private final OculusExtractorsFactory oculusExtractorsFactory = new OculusExtractorsFactory();
    private final Player.EventListener playerEventListener = new Player.EventListener() {
        /* class com.oculus.video.VideoInfoExtractor.AnonymousClass3 */

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onLoadingChanged(boolean z) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPlayerStateChanged(boolean z, int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onPositionDiscontinuity(int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onRepeatModeChanged(int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onSeekProcessed() {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onShuffleModeEnabledChanged(boolean z) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onTimelineChanged(Timeline timeline, Object obj, int i) {
        }

        @Override // com.oculus.android.exoplayer2.Player.EventListener
        public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            int i;
            TrackGroupArray trackGroupArray2 = trackGroupArray;
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            TreeMap treeMap = new TreeMap();
            String str = "";
            float f = 0.0f;
            float f2 = -1.0f;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            int i4 = 0;
            VideoProjection videoProjection = null;
            String str2 = str;
            String str3 = str2;
            int i5 = 0;
            while (i5 < trackGroupArray2.length) {
                VideoProjection videoProjection2 = videoProjection;
                float f3 = f2;
                int i6 = i4;
                float f4 = f;
                int i7 = i3;
                int i8 = i2;
                String str4 = str3;
                String str5 = str2;
                int i9 = 0;
                for (TrackGroup trackGroup = trackGroupArray2.get(i5); i9 < trackGroup.length; trackGroup = trackGroup) {
                    Format format = trackGroup.getFormat(i9);
                    int trackType = MimeTypes.getTrackType(format.sampleMimeType);
                    if (trackType == 1) {
                        try {
                            if (TextUtils.isEmpty(str5)) {
                                str5 = format.sampleMimeType;
                            }
                            jSONObject.put(Integer.toString(i5), format.channelCount);
                        } catch (JSONException unused) {
                        }
                    } else if (trackType == 3) {
                        if (TextUtils.isEmpty(str)) {
                            str = format.sampleMimeType;
                        }
                        String str6 = format.language;
                        if (TextUtils.isEmpty(str6)) {
                            str6 = "subtitle_" + (jSONObject2.length() + 1);
                        }
                        jSONObject2.put(format.id, str6);
                    } else if (trackType == 2) {
                        try {
                            if (TextUtils.isEmpty(str4)) {
                                str4 = format.sampleMimeType;
                            }
                            if (format.projectionData != null) {
                                videoProjection2 = VideoProjection.fromSphericalV2(format);
                            }
                            float f5 = format.frameRate;
                            try {
                                i = i7;
                                try {
                                    i8 = Math.max(format.width, 0);
                                    int max = Math.max(format.height, 0);
                                    if (i8 <= 0 || max <= 0) {
                                        f3 = f5;
                                    } else {
                                        f3 = f5;
                                        try {
                                            f4 = (((float) i8) * format.pixelWidthHeightRatio) / ((float) max);
                                        } catch (JSONException unused2) {
                                        }
                                    }
                                    if (format.rotationDegrees >= 0) {
                                        i6 = format.rotationDegrees;
                                    }
                                    treeMap.put(Integer.valueOf(format.bitrate), format);
                                    i7 = max;
                                } catch (JSONException unused3) {
                                    f3 = f5;
                                }
                            } catch (JSONException unused4) {
                                f3 = f5;
                                i9++;
                            }
                        } catch (JSONException unused5) {
                            i9++;
                        }
                    } else {
                        i = i7;
                        if (trackType == 4) {
                            if (!z) {
                                try {
                                    if (!MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType)) {
                                        z = false;
                                    }
                                } catch (JSONException unused6) {
                                }
                            }
                            z = true;
                        }
                        i7 = i;
                    }
                    i9++;
                }
                i5++;
                trackGroupArray2 = trackGroupArray;
                str2 = str5;
                str3 = str4;
                i2 = i8;
                f = f4;
                i4 = i6;
                f2 = f3;
                videoProjection = videoProjection2;
                i3 = i7;
            }
            try {
                int i10 = 0;
                for (Map.Entry entry : treeMap.entrySet()) {
                    int i11 = i10 + 1;
                    jSONObject3.put(Integer.toString(i10), VideoInfoExtractor.buildFormatJson((Format) entry.getValue()).toString());
                    i10 = i11;
                }
            } catch (JSONException unused7) {
            }
            Object currentManifest = VideoInfoExtractor.this.extractorPlayer != null ? VideoInfoExtractor.this.extractorPlayer.getCurrentManifest() : null;
            VideoInfoExtractor.this.onExtractionSuccess(currentManifest instanceof OculusDashManifest ? ((OculusDashManifest) currentManifest).audioChannelLayout : AudioChannelLayout.UNKNOWN, str2, jSONObject, str3, jSONObject3, str, jSONObject2, videoProjection, f2, i2, i3, f, i4, z, true);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e5, code lost:
            r23.this$0.onExtractionFailed();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
            return;
         */
        /* JADX WARNING: Removed duplicated region for block: B:41:? A[ExcHandler: IOException | IllegalArgumentException | JSONException (unused java.lang.Throwable), SYNTHETIC, Splitter:B:2:0x003e] */
        @Override // com.oculus.android.exoplayer2.Player.EventListener
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onPlayerError(com.oculus.android.exoplayer2.ExoPlaybackException r24) {
            /*
            // Method dump skipped, instructions count: 235
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.VideoInfoExtractor.AnonymousClass3.onPlayerError(com.oculus.android.exoplayer2.ExoPlaybackException):void");
        }
    };
    private final Video video;

    /* access modifiers changed from: package-private */
    public interface Listener {
        void onFinish(boolean z);
    }

    private AudioChannelLayout getAudioChannelLayout(JSONObject jSONObject, String str) {
        if (jSONObject != null && jSONObject.length() > 0) {
            int i = 0;
            if (jSONObject.length() == TBE442_CHANNELS.length) {
                Iterator<String> keys = jSONObject.keys();
                int i2 = 0;
                while (keys.hasNext()) {
                    try {
                        if (jSONObject.getInt(keys.next()) != TBE442_CHANNELS[i2]) {
                            break;
                        }
                        i2++;
                    } catch (JSONException unused) {
                    }
                }
                if (i2 == TBE442_CHANNELS.length) {
                    return AudioChannelLayout.TBE_4_4_2;
                }
            }
            try {
                i = jSONObject.getInt(jSONObject.keys().next());
            } catch (JSONException unused2) {
            }
            if (i == 6 && MimeTypes.AUDIO_OPUS.equalsIgnoreCase(str) && VideoPlayer_Android.isSideLoaded(this.video.getVideoUrl())) {
                return AudioChannelLayout.AMBIX_4_2;
            }
            if (i == 1) {
                return AudioChannelLayout.MONO;
            }
            if (i == 2) {
                return AudioChannelLayout.STEREO;
            }
            if (i == 4) {
                return AudioChannelLayout.AMBIX_4;
            }
            if (i == 6) {
                return AudioChannelLayout.S5_1;
            }
            if (i == 16) {
                return AudioChannelLayout.AMBIX_16;
            }
            if (i == 18) {
                return AudioChannelLayout.AMBIX_16_2;
            }
            switch (i) {
                case 8:
                    return AudioChannelLayout.S7_1;
                case 9:
                    return AudioChannelLayout.AMBIX_9;
                case 10:
                    return AudioChannelLayout.TBE_8_2;
                case 11:
                    return AudioChannelLayout.AMBIX_9_2;
            }
        }
        return AudioChannelLayout.UNKNOWN;
    }

    VideoInfoExtractor(Video video2) {
        this.video = video2;
    }

    /* access modifiers changed from: package-private */
    public void extract(Context context, Handler handler, Listener listener2) {
        MediaSource mediaSource;
        this.listener = listener2;
        this.extractorPlayer = ExoPlayerFactory.newInstance(new Renderer[]{this.noOpRenderer}, new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(new DefaultBandwidthMeter())));
        this.extractorPlayer.addListener(this.playerEventListener);
        this.extractorPlayer.setPlayWhenReady(false);
        OculusDataSourceFactory oculusDataSourceFactory = new OculusDataSourceFactory(context, null, new DefaultDataSourceFactory(context, "oculus_video_sdk"), null);
        if (this.video.getDashManifest() != null) {
            mediaSource = new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(oculusDataSourceFactory), null).createMediaSource(this.video.getDashManifest(), (Handler) null, (MediaSourceEventListener) null);
        } else {
            int inferContentType = Util.inferContentType(this.video.getVideoUri());
            if (inferContentType == 0) {
                mediaSource = new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(oculusDataSourceFactory), oculusDataSourceFactory).setManifestParser(new OculusDashManifestParser()).createMediaSource(this.video.getVideoUri(), handler, (MediaSourceEventListener) null);
            } else if (inferContentType != 2) {
                this.oculusExtractorsFactory.setExtractorEventListener(this.extractorListener);
                mediaSource = new OculusExtractorMediaSource(context, this.video.getVideoUri(), oculusDataSourceFactory, this.oculusExtractorsFactory, handler, (OculusExtractorMediaSource.EventListener) null);
            } else {
                mediaSource = new OculusHlsMediaSource.Factory(oculusDataSourceFactory).setPlaylistParser(new OculusHlsPlaylistParser(this.video.getAes128Key(), this.video.getAes128Iv())).createMediaSource(this.video.getVideoUri(), handler, null, null);
            }
        }
        this.extractorPlayer.prepare(mediaSource);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x009a */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ab A[SYNTHETIC, Splitter:B:36:0x00ab] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onExtractionSuccess(com.oculus.video.audio.AudioChannelLayout r16, java.lang.String r17, org.json.JSONObject r18, java.lang.String r19, org.json.JSONObject r20, java.lang.String r21, org.json.JSONObject r22, com.oculus.video.projection.VideoProjection r23, float r24, int r25, int r26, float r27, int r28, boolean r29, boolean r30) {
        /*
        // Method dump skipped, instructions count: 302
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.VideoInfoExtractor.onExtractionSuccess(com.oculus.video.audio.AudioChannelLayout, java.lang.String, org.json.JSONObject, java.lang.String, org.json.JSONObject, java.lang.String, org.json.JSONObject, com.oculus.video.projection.VideoProjection, float, int, int, float, int, boolean, boolean):void");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onExtractionFailed() {
        release();
        this.listener.onFinish(false);
    }

    private void release() {
        this.oculusExtractorsFactory.setExtractorEventListener(null);
        ExoPlayer exoPlayer = this.extractorPlayer;
        if (exoPlayer != null) {
            exoPlayer.stop();
            this.extractorPlayer.release();
            this.extractorPlayer = null;
        }
    }

    /* access modifiers changed from: private */
    public static JSONObject buildFormatJson(Format format) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Id", format.id);
            jSONObject.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH, format.width);
            jSONObject.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_HEIGHT, format.height);
            jSONObject.put("framerate", (double) format.frameRate);
            jSONObject.put(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_BITRATE, format.bitrate);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
