package com.oculus.video.renderer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;
import com.oculus.android.exoplayer2.BaseRenderer;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.decoder.DecoderCounters;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.drm.DrmSession;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.TraceUtil;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.android.exoplayer2.video.VideoRendererEventListener;
import com.oculus.video.analytics.VideoPlayerAnalytics;
import com.oculus.video.projection.ProjectionType;
import com.oculus.video.renderer.VideoDirectDecoder;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@TargetApi(16)
public class OculusMediaCodecVideoRenderer extends BaseRenderer implements FrameReleaser {
    private static final long AV_DESYNC_MAX_THRESHOLD_NS = 45000000;
    private static final long AV_DESYNC_MIN_THRESHOLD_NS = -45000000;
    private static final long EARLIEST_NEXT_FRAME_RELEASE_THRESHOLD_NS = 20000000;
    private static final double FRAME_DROP_FPS_THRESHOLD = 45.0d;
    private static final long ILLEGAL_PRESENTATION_TIME = Long.MAX_VALUE;
    private static final String KEY_CSD_0 = "csd-0";
    private static final String KEY_CSD_1 = "csd-1";
    private static final String KEY_CSD_2 = "csd-2";
    private static final long LATEST_RELEASE_THRESHOLD_NS = -8000000;
    private static final long MAX_CODEC_HOTSWAP_TIME_MS = 1000;
    private static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final int MAX_LATE_BUFFER_US = -60000;
    public static final int MAX_RENDER_DELAY_MS = 100;
    private static final int MAX_VERY_LATE_BUFFER_US = -500000;
    private static final double QUEUE_AHEAD_FPS_THRESHOLD = 47.0d;
    private static final double QUEUE_AHEAD_FRAME_COUNT = 2.5d;
    private static final int RECONFIGURATION_STATE_NONE = 0;
    private static final int RECONFIGURATION_STATE_QUEUE_PENDING = 2;
    private static final int RECONFIGURATION_STATE_WRITE_PENDING = 1;
    private static final int REINITIALIZATION_STATE_NONE = 0;
    private static final int REINITIALIZATION_STATE_SIGNAL_END_OF_STREAM = 1;
    private static final int REINITIALIZATION_STATE_WAIT_END_OF_STREAM = 2;
    private static final double SECOND_FRAME_RELEASE_THRESHOLD_NS = 1.6666666E7d;
    private static final int[] STANDARD_LONG_EDGE_VIDEO_PX = {1920, 1600, 1440, 1280, 960, 854, 640, 540, 480};
    private static final String TAG = "OculusVideoRenderer";
    private final long allowedJoiningTimeMs;
    private final DecoderInputBuffer buffer;
    private long codecHotswapDeadlineMs;
    private boolean codecIsAdaptive;
    private CodecMaxValues codecMaxValues;
    private boolean codecReceivedBuffers;
    private boolean codecReceivedEos;
    private int codecReconfigurationState;
    private boolean codecReconfigured;
    private int codecReinitializationState;
    private int consecutiveDroppedFrameCount;
    private final VideoSizeParams currentVideoSizeParams = new VideoSizeParams();
    private DecoderCounters decoderCounters;
    private DrmSession<FrameworkMediaCrypto> drmSession;
    private final DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private long droppedFrameAccumulationStartTimeMs;
    private int droppedFrames;
    private final VideoRendererEventListener.EventDispatcher eventDispatcher;
    private final DecoderInputBuffer flagsOnlyBuffer;
    private Format format;
    private final FormatHolder formatHolder;
    private final SparseIntArray formatSupportMap = new SparseIntArray();
    private ByteBuffer inputBuffer;
    private boolean inputBufferSet;
    private boolean inputStreamEnded;
    private long joiningDeadlineMs;
    private long lastElapsedRealtimeUs = C.TIME_UNSET;
    private ExoPlaybackException lastExoPlaybackException;
    private long lastPositionUs = C.TIME_UNSET;
    private long lastReleaseTimeNs = C.TIME_UNSET;
    private long lastReleasedFrameTimestampNs = C.TIME_UNSET;
    private volatile long lastReleasedPresentationTimeUs = C.TIME_UNSET;
    private long lastRenderTimeMs = C.TIME_UNSET;
    private final MediaCodecSelector mediaCodecSelector;
    private volatile long nextDisplayTimeNs = C.TIME_UNSET;
    private boolean outputStreamEnded;
    private DrmSession<FrameworkMediaCrypto> pendingDrmSession;
    private final boolean playClearSamplesWithoutKeys;
    private final boolean releaseWithMediaTimestamp;
    private boolean releasedFirstFrame;
    private boolean renderedFirstFrame;
    private final Callback rendererCallback;
    private final VideoSizeParams reportedVideoSizeParams = new VideoSizeParams();
    private volatile boolean shouldReleaseNextFrame = false;
    private Format[] streamFormats;
    private Surface surface;
    private final boolean useEarlyFrameRelease;
    private final ReentrantLock videoBufferReleaseLock = new ReentrantLock();
    private final VideoDirectDecoder videoDirectDecoder = new VideoDirectDecoder();
    private boolean waitingForDropToKey;
    private boolean waitingForFirstSyncFrame;
    private boolean waitingForKeys;

    public interface Callback extends VideoRendererEventListener {
        ProjectionType getProjectionType(Format format);
    }

    @Override // com.oculus.android.exoplayer2.BaseRenderer, com.oculus.android.exoplayer2.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 8;
    }

    public static class DecoderInitializationException extends Exception {
        private static final int CUSTOM_ERROR_CODE_BASE = -50000;
        private static final int DECODER_QUERY_ERROR = -49998;
        private static final int NO_SUITABLE_DECODER_ERROR = -49999;
        public final String decoderName;
        public final String diagnosticInfo;
        public final String mimeType;
        public final boolean secureDecoderRequired;

        public DecoderInitializationException(Format format, Throwable th, boolean z, int i) {
            super("Decoder init failed: [" + i + "], " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = null;
            this.diagnosticInfo = buildCustomDiagnosticInfo(i);
        }

        public DecoderInitializationException(Format format, Throwable th, boolean z, String str) {
            super("Decoder init failed: " + str + ", " + format, th);
            this.mimeType = format.sampleMimeType;
            this.secureDecoderRequired = z;
            this.decoderName = str;
            this.diagnosticInfo = Util.SDK_INT >= 21 ? getDiagnosticInfoV21(th) : null;
        }

        @TargetApi(21)
        private static String getDiagnosticInfoV21(Throwable th) {
            if (th instanceof MediaCodec.CodecException) {
                return ((MediaCodec.CodecException) th).getDiagnosticInfo();
            }
            return null;
        }

        private static String buildCustomDiagnosticInfo(int i) {
            String str = i < 0 ? "neg_" : "";
            return "com.google.android.exoplayer.MediaCodecTrackRenderer_" + str + Math.abs(i);
        }
    }

    /* access modifiers changed from: private */
    public static class VideoSizeParams implements VideoDirectDecoder.EventListener {
        public int bottomCrop;
        public int height;
        public int leftCrop;
        public float pixelWidthHeightRatio;
        public long presentationTimeUs;
        public int rightCrop;
        public int rotationDegrees;
        public int topCrop;
        public int width;

        VideoSizeParams() {
            clear();
        }

        /* access modifiers changed from: package-private */
        public synchronized void set(int i, int i2, int i3, float f) {
            this.width = i;
            this.height = i2;
            this.rotationDegrees = i3;
            this.pixelWidthHeightRatio = f;
            this.presentationTimeUs = -1;
            this.topCrop = -1;
            this.leftCrop = -1;
            this.bottomCrop = -1;
            this.rightCrop = -1;
        }

        @Override // com.oculus.video.renderer.VideoDirectDecoder.EventListener
        public synchronized void onRenderTextureSizeChange(int i, int i2, int i3, int i4, int i5, int i6, long j) {
            if (this.presentationTimeUs != -1) {
                boolean z = true;
                boolean z2 = (this.width == i && this.height == i2) ? false : true;
                if (!(i3 == 0 && i4 == 0 && i5 == 0 && i6 == 0)) {
                    z = false;
                }
                if (z2 && z) {
                    if (i < this.width || i2 < this.height) {
                        i = this.width;
                        i2 = this.height;
                    }
                    i6 = i - this.width;
                    i5 = i2 - this.height;
                    i3 = 0;
                    i4 = 0;
                }
                this.width = i - (i6 + i4);
                this.height = i2 - (i5 + i3);
                this.topCrop = i3;
                this.leftCrop = i4;
                this.bottomCrop = i5;
                this.rightCrop = i6;
                this.presentationTimeUs = j;
            }
        }

        /* access modifiers changed from: package-private */
        public synchronized void clear() {
            set(-1, -1, -1, -1.0f);
        }

        /* access modifiers changed from: package-private */
        public synchronized void copy(VideoSizeParams videoSizeParams) {
            set(videoSizeParams.width, videoSizeParams.height, videoSizeParams.rotationDegrees, videoSizeParams.pixelWidthHeightRatio);
            this.topCrop = videoSizeParams.topCrop;
            this.leftCrop = videoSizeParams.leftCrop;
            this.bottomCrop = videoSizeParams.bottomCrop;
            this.rightCrop = videoSizeParams.rightCrop;
            this.presentationTimeUs = videoSizeParams.presentationTimeUs;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean equals(VideoSizeParams videoSizeParams) {
            return this.width == videoSizeParams.width && this.height == videoSizeParams.height && this.pixelWidthHeightRatio == videoSizeParams.pixelWidthHeightRatio && this.rotationDegrees == videoSizeParams.rotationDegrees;
        }

        /* access modifiers changed from: package-private */
        public synchronized Rect getCropRect() {
            if (!(this.leftCrop == -1 || this.rightCrop == -1 || this.topCrop == -1)) {
                if (this.bottomCrop != -1) {
                    return new Rect(this.leftCrop, this.topCrop, this.rightCrop, this.bottomCrop);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static final class CodecMaxValues {
        public final int height;
        public final int inputSize;
        public final int width;

        public CodecMaxValues(int i, int i2, int i3) {
            this.width = i;
            this.height = i2;
            this.inputSize = i3;
        }
    }

    public OculusMediaCodecVideoRenderer(Context context, MediaCodecSelector mediaCodecSelector2, long j, boolean z, boolean z2, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, boolean z3, Handler handler, VideoRendererEventListener videoRendererEventListener) {
        super(2);
        Callback callback = null;
        this.lastExoPlaybackException = null;
        boolean z4 = true;
        Assertions.checkState(Util.SDK_INT >= 16);
        this.mediaCodecSelector = (MediaCodecSelector) Assertions.checkNotNull(mediaCodecSelector2);
        this.drmSessionManager = drmSessionManager2;
        this.playClearSamplesWithoutKeys = z3;
        this.buffer = new DecoderInputBuffer(2);
        this.flagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        this.formatHolder = new FormatHolder();
        this.codecReconfigurationState = 0;
        this.codecReinitializationState = 0;
        this.allowedJoiningTimeMs = j;
        this.releaseWithMediaTimestamp = z;
        this.useEarlyFrameRelease = (!z2 || z) ? false : z4;
        this.rendererCallback = videoRendererEventListener instanceof Callback ? (Callback) videoRendererEventListener : callback;
        this.eventDispatcher = new VideoRendererEventListener.EventDispatcher(handler, videoRendererEventListener);
        this.joiningDeadlineMs = C.TIME_UNSET;
        this.waitingForDropToKey = false;
    }

    @Override // com.oculus.android.exoplayer2.RendererCapabilities
    public final int supportsFormat(Format format2) throws ExoPlaybackException {
        try {
            int i = this.formatSupportMap.get(format2.hashCode());
            if (i != 0) {
                return i;
            }
            int supportsFormat = supportsFormat(this.mediaCodecSelector, format2);
            this.formatSupportMap.append(format2.hashCode(), supportsFormat);
            return supportsFormat;
        } catch (MediaCodecUtil.DecoderQueryException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    private int supportsFormat(MediaCodecSelector mediaCodecSelector2, Format format2) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        String str = format2.sampleMimeType;
        int i = 0;
        if (!MimeTypes.isVideo(str)) {
            return 0;
        }
        DrmInitData drmInitData = format2.drmInitData;
        if (drmInitData != null) {
            z = false;
            for (int i2 = 0; i2 < drmInitData.schemeDataCount; i2++) {
                z |= drmInitData.get(i2).requiresSecureDecryption;
            }
        } else {
            z = false;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector2.getDecoderInfo(str, z);
        boolean z2 = true;
        if (decoderInfo == null) {
            return 1;
        }
        if (!decoderInfo.isCodecSupported(format2.codecs) || format2.width <= 0 || format2.height <= 0) {
            z2 = false;
        }
        int i3 = decoderInfo.adaptive ? 16 : 8;
        if (decoderInfo.tunneling) {
            i = 32;
        }
        return (z2 ? 4 : 3) | i3 | i;
    }

    /* access modifiers changed from: protected */
    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector2, Format format2, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return mediaCodecSelector2.getDecoderInfo(format2.sampleMimeType, z);
    }

    private void throwDecoderInitError(DecoderInitializationException decoderInitializationException) throws ExoPlaybackException {
        throw ExoPlaybackException.createForRenderer(decoderInitializationException, getIndex());
    }

    private boolean shouldInitCodec() {
        return (this.videoDirectDecoder.isActive() || this.format == null || this.surface == null) ? false : true;
    }

    @Override // com.oculus.android.exoplayer2.BaseRenderer, com.oculus.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            setSurface((Surface) obj);
        } else {
            super.handleMessage(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters = new DecoderCounters();
        this.eventDispatcher.enabled(this.decoderCounters);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        this.streamFormats = formatArr;
        super.onStreamChanged(formatArr, j);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        this.inputStreamEnded = false;
        this.outputStreamEnded = false;
        this.consecutiveDroppedFrameCount = 0;
        flushCodec();
        if (z) {
            setJoiningDeadlineMs();
        } else {
            this.joiningDeadlineMs = C.TIME_UNSET;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStarted() {
        this.joiningDeadlineMs = C.TIME_UNSET;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.lastPositionUs = C.TIME_UNSET;
        this.lastElapsedRealtimeUs = C.TIME_UNSET;
        maybeNotifyDroppedFrames();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.currentVideoSizeParams.clear();
        this.reportedVideoSizeParams.clear();
        clearRenderedFirstFrame();
        try {
            this.format = null;
            try {
                releaseCodec();
                try {
                    if (this.drmSession != null) {
                        this.drmSessionManager.releaseSession(this.drmSession);
                    }
                    try {
                        if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                            this.drmSessionManager.releaseSession(this.pendingDrmSession);
                        }
                        this.drmSession = null;
                        this.pendingDrmSession = null;
                        this.videoDirectDecoder.releaseOutputSurface();
                    } catch (Throwable th) {
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                        this.drmSessionManager.releaseSession(this.pendingDrmSession);
                    }
                    throw th2;
                } finally {
                    this.drmSession = null;
                    this.pendingDrmSession = null;
                }
            } catch (Throwable th3) {
                try {
                    if (!(this.pendingDrmSession == null || this.pendingDrmSession == this.drmSession)) {
                        this.drmSessionManager.releaseSession(this.pendingDrmSession);
                    }
                    throw th3;
                } finally {
                    this.drmSession = null;
                    this.pendingDrmSession = null;
                }
            } finally {
            }
        } finally {
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    private void clearRenderedFirstFrame() {
        this.renderedFirstFrame = false;
        this.releasedFirstFrame = false;
    }

    private void setSurface(Surface surface2) throws ExoPlaybackException {
        Surface surface3 = this.surface;
        if (surface3 != surface2) {
            this.surface = surface2;
            int state = getState();
            if ((state == 1 || state == 2) && Util.SDK_INT < 23) {
                releaseCodec();
                maybeInitCodec();
            }
            if (this.surface != null) {
                maybeRenotifyVideoSizeChanged();
                clearRenderedFirstFrame();
                if (state == 2) {
                    setJoiningDeadlineMs();
                    return;
                }
                return;
            }
            this.reportedVideoSizeParams.clear();
            clearRenderedFirstFrame();
        } else if (surface3 != null) {
            maybeRenotifyVideoSizeChanged();
            maybeRenotifyRenderedFirstFrame();
        }
    }

    public Rect getCurrentFrameCropRect() {
        return this.reportedVideoSizeParams.getCropRect();
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (!this.outputStreamEnded) {
            if (this.format == null) {
                this.flagsOnlyBuffer.clear();
                int readSource = readSource(this.formatHolder, this.flagsOnlyBuffer, true);
                if (readSource == -5) {
                    onInputFormatChanged(this.formatHolder.format);
                } else if (readSource == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    processEndOfStream();
                    return;
                } else {
                    return;
                }
            }
            maybeInitCodec();
            if (this.videoDirectDecoder.isActive()) {
                TraceUtil.beginSection("drainAndFeed");
                maybeRequestVideoBufferRelease(j, j2);
                do {
                } while (maybeFeedInputBuffer());
                TraceUtil.endSection();
            } else {
                skipSource(j);
                this.flagsOnlyBuffer.clear();
                int readSource2 = readSource(this.formatHolder, this.flagsOnlyBuffer, false);
                if (readSource2 == -5) {
                    onInputFormatChanged(this.formatHolder.format);
                } else if (readSource2 == -4) {
                    Assertions.checkState(this.flagsOnlyBuffer.isEndOfStream());
                    this.inputStreamEnded = true;
                    processEndOfStream();
                }
            }
            this.decoderCounters.ensureUpdated();
        }
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isReady() {
        maybeNotifyRenderedFirstFrame();
        if ((this.renderedFirstFrame || shouldInitCodec()) && this.format != null && !this.waitingForKeys && (isSourceReady() || (this.codecHotswapDeadlineMs != C.TIME_UNSET && SystemClock.elapsedRealtime() < this.codecHotswapDeadlineMs))) {
            this.joiningDeadlineMs = C.TIME_UNSET;
            return true;
        } else if (this.joiningDeadlineMs == C.TIME_UNSET) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.joiningDeadlineMs) {
                return true;
            }
            this.joiningDeadlineMs = C.TIME_UNSET;
            return false;
        }
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.outputStreamEnded;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b9  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void maybeInitCodec() throws com.oculus.android.exoplayer2.ExoPlaybackException {
        /*
        // Method dump skipped, instructions count: 213
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.renderer.OculusMediaCodecVideoRenderer.maybeInitCodec():void");
    }

    private void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format2, MediaCrypto mediaCrypto) throws MediaCodecUtil.DecoderQueryException {
        byte[] bArr;
        this.codecMaxValues = getCodecMaxValues(mediaCodecInfo, format2, this.streamFormats);
        MediaFormat mediaFormat = getMediaFormat(format2, this.codecMaxValues);
        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2 = this.drmSessionManager;
        byte[] bArr2 = null;
        if (drmSessionManager2 != null) {
            try {
                Field declaredField = drmSessionManager2.getClass().getDeclaredField("sessions");
                declaredField.setAccessible(true);
                List list = (List) declaredField.get(this.drmSessionManager);
                if (list == null || list.size() <= 0) {
                    bArr = null;
                } else {
                    Object obj = list.get(0);
                    Field declaredField2 = obj.getClass().getDeclaredField("sessionId");
                    declaredField2.setAccessible(true);
                    bArr = (byte[]) declaredField2.get(obj);
                }
                bArr2 = bArr;
            } catch (IllegalAccessException | NoSuchFieldException unused) {
            }
            if (bArr2 == null) {
                Log.w(TAG, "Failed to get DRM sessionId. Proceed w/o MediaCrypto");
            }
        }
        boolean z = this.drmSessionManager != null;
        if (!z) {
            this.codecReconfigurationState = 1;
        }
        this.inputBuffer = ByteBuffer.allocateDirect(this.codecMaxValues.inputSize);
        this.videoDirectDecoder.setOutputSurface(this.surface);
        this.videoDirectDecoder.setVideoExtractorInfo(z, this.codecIsAdaptive, mediaFormat.getString("mime"), mediaFormat.getInteger("max-input-size"), format2.rotationDegrees, mediaFormat.getInteger(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_WIDTH), mediaFormat.getInteger(VideoPlayerAnalytics.PLAYLIST_PROFILE_ITEM_HEIGHT), mediaFormat.getInteger("max-width"), mediaFormat.getInteger("max-height"), bArr2);
        String[] strArr = {KEY_CSD_0, KEY_CSD_1, KEY_CSD_2};
        for (String str : strArr) {
            if (mediaFormat.containsKey(str)) {
                this.videoDirectDecoder.writeCsdBuffer(str, mediaFormat.getByteBuffer(str).array());
            }
        }
        this.videoDirectDecoder.start(this.currentVideoSizeParams);
    }

    private void releaseCodec() {
        if (this.videoDirectDecoder.isActive()) {
            this.decoderCounters.renderedOutputBufferCount -= this.videoDirectDecoder.stop();
            this.codecHotswapDeadlineMs = C.TIME_UNSET;
            this.inputBufferSet = false;
            this.waitingForKeys = false;
            this.codecReconfigured = false;
            this.codecReceivedBuffers = false;
            this.codecIsAdaptive = false;
            this.codecReceivedEos = false;
            this.codecReconfigurationState = 0;
            this.codecReinitializationState = 0;
            this.decoderCounters.decoderReleaseCount++;
            this.buffer.data = null;
            DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
            if (drmSession2 != null && this.pendingDrmSession != drmSession2) {
                try {
                    this.drmSessionManager.releaseSession(drmSession2);
                } finally {
                    this.drmSession = null;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void flushCodec() throws ExoPlaybackException {
        if (this.videoDirectDecoder.isActive()) {
            this.codecHotswapDeadlineMs = C.TIME_UNSET;
            this.inputBufferSet = false;
            this.waitingForFirstSyncFrame = true;
            this.waitingForKeys = false;
            if (this.codecReinitializationState != 0) {
                releaseCodec();
                maybeInitCodec();
            } else {
                this.decoderCounters.renderedOutputBufferCount -= this.videoDirectDecoder.onPositionReset();
                this.codecReceivedBuffers = false;
            }
            if (this.codecReconfigured && this.format != null) {
                this.codecReconfigurationState = 1;
            }
        }
        clearRenderedFirstFrame();
    }

    private int getLayoutID(Format format2) {
        Callback callback = this.rendererCallback;
        return (callback != null ? callback.getProjectionType(format2) : ProjectionType.UNKNOWN).getLayoutID();
    }

    private boolean maybeFeedInputBuffer() throws ExoPlaybackException {
        int i;
        int i2;
        if (!this.videoDirectDecoder.isExtractedBufferReady() || this.codecReinitializationState == 2 || this.inputStreamEnded || this.waitingForDropToKey) {
            return false;
        }
        if (!this.inputBufferSet) {
            this.inputBufferSet = true;
            DecoderInputBuffer decoderInputBuffer = this.buffer;
            decoderInputBuffer.data = this.inputBuffer;
            decoderInputBuffer.clear();
        }
        if (this.codecReinitializationState == 1) {
            this.codecReceivedEos = true;
            this.videoDirectDecoder.writeBuffer(this.buffer.data, this.buffer.data.position(), 4, this.buffer.timeUs, getLayoutID(this.format), this.buffer.isDecodeOnly());
            this.inputBufferSet = false;
            this.codecReinitializationState = 2;
            return false;
        }
        if (this.waitingForKeys) {
            i2 = -4;
            i = 0;
        } else {
            if (this.codecReconfigurationState == 1) {
                for (int i3 = 0; i3 < this.format.initializationData.size(); i3++) {
                    this.buffer.data.put(this.format.initializationData.get(i3));
                }
                this.codecReconfigurationState = 2;
            }
            i = this.buffer.data.position();
            i2 = readSource(this.formatHolder, this.buffer, false);
        }
        if (i2 == -3) {
            return false;
        }
        if (i2 == -5) {
            if (this.codecReconfigurationState == 2) {
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            onInputFormatChanged(this.formatHolder.format);
            return true;
        } else if (this.buffer.isEndOfStream()) {
            if (this.codecReconfigurationState == 2) {
                this.buffer.clear();
                this.codecReconfigurationState = 1;
            }
            this.inputStreamEnded = true;
            if (!this.codecReceivedBuffers) {
                processEndOfStream();
                return false;
            }
            try {
                this.codecReceivedEos = true;
                this.videoDirectDecoder.writeBuffer(this.buffer.data, this.buffer.data.position(), 4, this.buffer.timeUs, getLayoutID(this.format), this.buffer.isDecodeOnly());
                this.inputBufferSet = false;
                return false;
            } catch (MediaCodec.CryptoException e) {
                throw ExoPlaybackException.createForRenderer(e, getIndex());
            }
        } else if (!this.waitingForFirstSyncFrame || this.buffer.isKeyFrame()) {
            this.waitingForFirstSyncFrame = false;
            boolean isEncrypted = this.buffer.isEncrypted();
            this.waitingForKeys = shouldWaitForKeys(isEncrypted);
            if (this.waitingForKeys) {
                return false;
            }
            try {
                onQueueInputBuffer(this.buffer);
                if (this.buffer.isDecodeOnly()) {
                    this.decoderCounters.skippedOutputBufferCount++;
                }
                if (isEncrypted) {
                    MediaCodec.CryptoInfo frameworkCryptoInfo = getFrameworkCryptoInfo(this.buffer, i);
                    this.videoDirectDecoder.writeBufferWithCrypto(this.buffer.data, this.buffer.data.position(), 0, this.buffer.timeUs, getLayoutID(this.format), this.buffer.isDecodeOnly(), frameworkCryptoInfo.iv, frameworkCryptoInfo.key, frameworkCryptoInfo.mode, frameworkCryptoInfo.numBytesOfClearData, frameworkCryptoInfo.numBytesOfEncryptedData, frameworkCryptoInfo.numSubSamples);
                } else {
                    this.videoDirectDecoder.writeBuffer(this.buffer.data, this.buffer.data.position(), 0, this.buffer.timeUs, getLayoutID(this.format), this.buffer.isDecodeOnly());
                }
                this.inputBufferSet = false;
                this.codecReceivedBuffers = true;
                this.codecReconfigurationState = 0;
                this.decoderCounters.inputBufferCount++;
                return true;
            } catch (MediaCodec.CryptoException e2) {
                throw ExoPlaybackException.createForRenderer(e2, getIndex());
            }
        } else {
            this.buffer.clear();
            if (this.codecReconfigurationState == 2) {
                this.codecReconfigurationState = 1;
            }
            return true;
        }
    }

    private static MediaCodec.CryptoInfo getFrameworkCryptoInfo(DecoderInputBuffer decoderInputBuffer, int i) {
        MediaCodec.CryptoInfo frameworkCryptoInfoV16 = decoderInputBuffer.cryptoInfo.getFrameworkCryptoInfoV16();
        if (i == 0) {
            return frameworkCryptoInfoV16;
        }
        if (frameworkCryptoInfoV16.numBytesOfClearData == null) {
            frameworkCryptoInfoV16.numBytesOfClearData = new int[1];
        }
        int[] iArr = frameworkCryptoInfoV16.numBytesOfClearData;
        iArr[0] = iArr[0] + i;
        return frameworkCryptoInfoV16;
    }

    private boolean shouldWaitForKeys(boolean z) throws ExoPlaybackException {
        DrmSession<FrameworkMediaCrypto> drmSession2 = this.drmSession;
        if (drmSession2 == null) {
            return false;
        }
        int state = drmSession2.getState();
        if (state == 1) {
            throw ExoPlaybackException.createForRenderer(this.drmSession.getError(), getIndex());
        } else if (state == 4) {
            return false;
        } else {
            if (z || !this.playClearSamplesWithoutKeys) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onInputFormatChanged(Format format2) throws ExoPlaybackException {
        Format format3 = this.format;
        this.format = format2;
        if (!Util.areEqual(this.format.drmInitData, format3 == null ? null : format3.drmInitData)) {
            if (this.format.drmInitData != null) {
                DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2 = this.drmSessionManager;
                if (drmSessionManager2 != null) {
                    this.pendingDrmSession = drmSessionManager2.acquireSession(Looper.myLooper(), this.format.drmInitData);
                    DrmSession<FrameworkMediaCrypto> drmSession2 = this.pendingDrmSession;
                    if (drmSession2 == this.drmSession) {
                        this.drmSessionManager.releaseSession(drmSession2);
                    }
                } else {
                    throw ExoPlaybackException.createForRenderer(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
            } else {
                this.pendingDrmSession = null;
            }
        }
        if (this.pendingDrmSession == this.drmSession && this.videoDirectDecoder.isActive() && canReconfigureCodec(null, this.codecIsAdaptive, format3, this.format)) {
            this.codecReconfigured = true;
            this.codecReconfigurationState = 1;
        } else if (this.codecReceivedBuffers) {
            this.codecReinitializationState = 1;
        } else {
            releaseCodec();
            maybeInitCodec();
        }
        this.eventDispatcher.inputFormatChanged(format2);
        int i = format2.width;
        int i2 = format2.height;
        float pixelWidthHeightRatio = getPixelWidthHeightRatio(format2);
        int rotationDegrees = getRotationDegrees(format2);
        if (Util.SDK_INT >= 21) {
            if (rotationDegrees == 90 || rotationDegrees == 270) {
                pixelWidthHeightRatio = 1.0f / pixelWidthHeightRatio;
                i2 = i;
                i = i2;
            }
            rotationDegrees = 0;
        }
        this.currentVideoSizeParams.set(i, i2, rotationDegrees, pixelWidthHeightRatio);
    }

    /* access modifiers changed from: protected */
    public void onQueueInputBuffer(DecoderInputBuffer decoderInputBuffer) {
        if (this.currentVideoSizeParams.presentationTimeUs == -1) {
            this.currentVideoSizeParams.presentationTimeUs = decoderInputBuffer.timeUs;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canReconfigureCodec(MediaCodec mediaCodec, boolean z, Format format2, Format format3) {
        return areAdaptationCompatible(z, format2, format3) && format3.width <= this.codecMaxValues.width && format3.height <= this.codecMaxValues.height && format3.maxInputSize <= this.codecMaxValues.inputSize;
    }

    /* access modifiers changed from: protected */
    public void maybeRequestVideoBufferRelease(long j, long j2) throws ExoPlaybackException {
        long j3;
        ExoPlaybackException exoPlaybackException = this.lastExoPlaybackException;
        if (exoPlaybackException == null) {
            this.lastPositionUs = j;
            this.lastElapsedRealtimeUs = j2;
            if (this.videoDirectDecoder.isActive()) {
                if (this.videoDirectDecoder.isOutputStreamEnded()) {
                    processEndOfStream();
                    return;
                }
                long nextRenderBufferPresentationTime = this.videoDirectDecoder.getNextRenderBufferPresentationTime();
                if (nextRenderBufferPresentationTime != Long.MAX_VALUE) {
                    if (!this.releasedFirstFrame) {
                        if (this.releaseWithMediaTimestamp) {
                            j3 = nextRenderBufferPresentationTime * MAX_CODEC_HOTSWAP_TIME_MS;
                        } else {
                            j3 = System.nanoTime();
                        }
                        releaseVideoBufferToRender(nextRenderBufferPresentationTime, j3);
                    }
                    if (!shouldDropBuffersToKeyframe(nextRenderBufferPresentationTime - (((SystemClock.elapsedRealtime() * MAX_CODEC_HOTSWAP_TIME_MS) - j2) + j), nextRenderBufferPresentationTime) || !maybeDropBuffersToKeyframe(j)) {
                        boolean z = true;
                        boolean z2 = this.lastRenderTimeMs != C.TIME_UNSET && SystemClock.elapsedRealtime() - this.lastRenderTimeMs > 100;
                        if (!this.shouldReleaseNextFrame && !z2) {
                            z = false;
                        }
                        this.shouldReleaseNextFrame = z;
                        if (this.shouldReleaseNextFrame && this.renderedFirstFrame) {
                            maybeRequestVideoBufferRelease();
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        this.lastExoPlaybackException = null;
        throw exoPlaybackException;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean maybeRequestVideoBufferRelease() throws com.oculus.android.exoplayer2.ExoPlaybackException {
        /*
        // Method dump skipped, instructions count: 222
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.renderer.OculusMediaCodecVideoRenderer.maybeRequestVideoBufferRelease():boolean");
    }

    /* access modifiers changed from: protected */
    public boolean shouldDropBuffersToKeyframe(long j, long j2) {
        return (this.releasedFirstFrame && ((1000000.0d / ((double) (j2 - this.lastReleasedPresentationTimeUs))) > FRAME_DROP_FPS_THRESHOLD ? 1 : ((1000000.0d / ((double) (j2 - this.lastReleasedPresentationTimeUs))) == FRAME_DROP_FPS_THRESHOLD ? 0 : -1)) > 0) && j < -500000;
    }

    /* access modifiers changed from: protected */
    public boolean maybeDropBuffersToKeyframe(long j) throws ExoPlaybackException {
        this.waitingForDropToKey = true;
        int skipSource = skipSource(j);
        if (skipSource == 0) {
            this.waitingForDropToKey = false;
            return false;
        }
        this.decoderCounters.droppedToKeyframeCount++;
        updateDroppedBufferCounters(this.videoDirectDecoder.getBuffersInCodecCount() + skipSource);
        flushCodec();
        this.waitingForDropToKey = false;
        return true;
    }

    /* access modifiers changed from: protected */
    public void updateDroppedBufferCounters(int i) {
        this.decoderCounters.droppedBufferCount += i;
        this.droppedFrames += i;
        this.consecutiveDroppedFrameCount += i;
        DecoderCounters decoderCounters2 = this.decoderCounters;
        decoderCounters2.maxConsecutiveDroppedBufferCount = Math.max(this.consecutiveDroppedFrameCount, decoderCounters2.maxConsecutiveDroppedBufferCount);
        if (this.droppedFrames >= MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY) {
            maybeNotifyDroppedFrames();
        }
    }

    private void releaseVideoBufferToRender(long j, long j2) {
        this.lastReleasedPresentationTimeUs = j;
        this.lastReleasedFrameTimestampNs = j2;
        maybeNotifyVideoSizeChanged(j);
        this.videoDirectDecoder.requestVideoBufferRelease(j2);
        this.decoderCounters.renderedOutputBufferCount++;
        this.shouldReleaseNextFrame = false;
        this.lastReleaseTimeNs = System.nanoTime();
        this.releasedFirstFrame = true;
        this.consecutiveDroppedFrameCount = 0;
    }

    private void maybeNotifyDroppedFrames() {
        if (this.droppedFrames > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.eventDispatcher.droppedFrames(this.droppedFrames, elapsedRealtime - this.droppedFrameAccumulationStartTimeMs);
            this.droppedFrames = 0;
            this.droppedFrameAccumulationStartTimeMs = elapsedRealtime;
        }
    }

    private void setJoiningDeadlineMs() {
        this.joiningDeadlineMs = this.allowedJoiningTimeMs > 0 ? SystemClock.elapsedRealtime() + this.allowedJoiningTimeMs : C.TIME_UNSET;
    }

    private void maybeNotifyRenderedFirstFrame() {
        if (!this.renderedFirstFrame && this.releasedFirstFrame && ((double) (System.nanoTime() - this.lastReleaseTimeNs)) > SECOND_FRAME_RELEASE_THRESHOLD_NS) {
            this.renderedFirstFrame = true;
            this.shouldReleaseNextFrame = true;
            this.eventDispatcher.renderedFirstFrame(null);
        }
    }

    private void maybeRenotifyRenderedFirstFrame() {
        if (this.renderedFirstFrame) {
            this.eventDispatcher.renderedFirstFrame(null);
        }
    }

    private void maybeNotifyVideoSizeChanged(long j) {
        this.videoDirectDecoder.checkRenderTextureSizeChange();
        if (this.currentVideoSizeParams.presentationTimeUs != -1 && j >= this.currentVideoSizeParams.presentationTimeUs && !this.reportedVideoSizeParams.equals(this.currentVideoSizeParams)) {
            this.eventDispatcher.videoSizeChanged(this.currentVideoSizeParams.width, this.currentVideoSizeParams.height, this.currentVideoSizeParams.rotationDegrees, this.currentVideoSizeParams.pixelWidthHeightRatio);
            this.reportedVideoSizeParams.copy(this.currentVideoSizeParams);
        }
    }

    private void maybeRenotifyVideoSizeChanged() {
        if (this.reportedVideoSizeParams.width != -1 || this.reportedVideoSizeParams.height != -1) {
            this.eventDispatcher.videoSizeChanged(this.currentVideoSizeParams.width, this.currentVideoSizeParams.height, this.currentVideoSizeParams.rotationDegrees, this.currentVideoSizeParams.pixelWidthHeightRatio);
        }
    }

    @SuppressLint({"InlinedApi"})
    private static MediaFormat getMediaFormat(Format format2, CodecMaxValues codecMaxValues2) {
        MediaFormat frameworkMediaFormatV16 = format2.getFrameworkMediaFormatV16();
        frameworkMediaFormatV16.setInteger("max-width", codecMaxValues2.width);
        frameworkMediaFormatV16.setInteger("max-height", codecMaxValues2.height);
        if (codecMaxValues2.inputSize != -1) {
            frameworkMediaFormatV16.setInteger("max-input-size", codecMaxValues2.inputSize);
        }
        return frameworkMediaFormatV16;
    }

    private CodecMaxValues getCodecMaxValues(MediaCodecInfo mediaCodecInfo, Format format2, Format[] formatArr) throws MediaCodecUtil.DecoderQueryException {
        int i = format2.width;
        int i2 = format2.height;
        int maxInputSize = getMaxInputSize(format2);
        if (formatArr == null || formatArr.length == 1) {
            return new CodecMaxValues(i, i2, maxInputSize);
        }
        int i3 = i2;
        int i4 = maxInputSize;
        boolean z = false;
        int i5 = i;
        for (Format format3 : formatArr) {
            if (areAdaptationCompatible(mediaCodecInfo.adaptive, format2, format3)) {
                z |= format3.width == -1 || format3.height == -1;
                i5 = Math.max(i5, format3.width);
                i3 = Math.max(i3, format3.height);
                i4 = Math.max(i4, getMaxInputSize(format3));
            }
        }
        if (z) {
            Log.w(TAG, "Resolutions unknown. Codec max resolution: " + i5 + "x" + i3);
            Point codecMaxSize = getCodecMaxSize(mediaCodecInfo, format2);
            if (codecMaxSize != null) {
                i5 = Math.max(i5, codecMaxSize.x);
                i3 = Math.max(i3, codecMaxSize.y);
                i4 = Math.max(i4, getMaxInputSize(format2.sampleMimeType, i5, i3));
                Log.w(TAG, "Codec max resolution adjusted to: " + i5 + "x" + i3);
            }
        }
        return new CodecMaxValues(i5, i3, i4);
    }

    private static Point getCodecMaxSize(MediaCodecInfo mediaCodecInfo, Format format2) throws MediaCodecUtil.DecoderQueryException {
        boolean z = format2.height > format2.width;
        int i = z ? format2.height : format2.width;
        int i2 = z ? format2.width : format2.height;
        float f = ((float) i2) / ((float) i);
        int[] iArr = STANDARD_LONG_EDGE_VIDEO_PX;
        for (int i3 : iArr) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (Util.SDK_INT >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point alignVideoSizeV21 = mediaCodecInfo.alignVideoSizeV21(i5, i3);
                if (mediaCodecInfo.isVideoSizeAndRateSupportedV21(alignVideoSizeV21.x, alignVideoSizeV21.y, (double) format2.frameRate)) {
                    return alignVideoSizeV21;
                }
            } else {
                int ceilDivide = Util.ceilDivide(i3, 16) * 16;
                int ceilDivide2 = 16 * Util.ceilDivide(i4, 16);
                if (ceilDivide * ceilDivide2 <= MediaCodecUtil.maxH264DecodableFrameSize()) {
                    int i6 = z ? ceilDivide2 : ceilDivide;
                    if (z) {
                        ceilDivide2 = ceilDivide;
                    }
                    return new Point(i6, ceilDivide2);
                }
            }
        }
        return null;
    }

    private static int getMaxInputSize(Format format2) {
        if (format2.maxInputSize != -1) {
            return format2.maxInputSize;
        }
        return getMaxInputSize(format2.sampleMimeType, format2.width, format2.height);
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static int getMaxInputSize(String str, int i, int i2) {
        boolean z;
        int i3;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        int i4 = 4;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MimeTypes.VIDEO_H263)) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case -1662541442:
                if (str.equals(MimeTypes.VIDEO_H265)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1187890754:
                if (str.equals(MimeTypes.VIDEO_MP4V)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1331836730:
                if (str.equals(MimeTypes.VIDEO_H264)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1599127256:
                if (str.equals(MimeTypes.VIDEO_VP8)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1599127257:
                if (str.equals(MimeTypes.VIDEO_VP9)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (z && !z) {
            if (!z) {
                if (!z) {
                    if (!z && !z) {
                        return -1;
                    }
                    i3 = i * i2;
                    return (i3 * 3) / (i4 * 2);
                }
            } else if ("BRAVIA 4K 2015".equals(Util.MODEL)) {
                return -1;
            } else {
                i3 = Util.ceilDivide(i, 16) * Util.ceilDivide(i2, 16) * 16 * 16;
                i4 = 2;
                return (i3 * 3) / (i4 * 2);
            }
        }
        i3 = i * i2;
        i4 = 2;
        return (i3 * 3) / (i4 * 2);
    }

    private static boolean areAdaptationCompatible(boolean z, Format format2, Format format3) {
        return format2.sampleMimeType.equals(format3.sampleMimeType) && getRotationDegrees(format2) == getRotationDegrees(format3) && (z || (format2.width == format3.width && format2.height == format3.height));
    }

    private static float getPixelWidthHeightRatio(Format format2) {
        if (format2.pixelWidthHeightRatio == -1.0f) {
            return 1.0f;
        }
        return format2.pixelWidthHeightRatio;
    }

    private static int getRotationDegrees(Format format2) {
        if (format2.rotationDegrees == -1) {
            return 0;
        }
        return format2.rotationDegrees;
    }

    public void onBeforeRender(long j) {
        this.nextDisplayTimeNs = j;
    }

    public void onRender(boolean z) {
        this.shouldReleaseNextFrame = z || this.shouldReleaseNextFrame;
        do {
            try {
            } catch (ExoPlaybackException e) {
                this.lastExoPlaybackException = e;
            }
        } while (maybeRequestVideoBufferRelease());
        this.lastRenderTimeMs = SystemClock.elapsedRealtime();
    }

    private void processEndOfStream() throws ExoPlaybackException {
        if (this.codecReinitializationState == 2) {
            releaseCodec();
            maybeInitCodec();
            return;
        }
        this.outputStreamEnded = true;
    }

    @Override // com.oculus.video.renderer.FrameReleaser
    public long getLastPresentationTimeUs() {
        return this.lastReleasedPresentationTimeUs;
    }
}
