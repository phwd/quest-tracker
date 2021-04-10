package com.oculus.video.renderer;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.PlaybackParameters;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecInfo;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.oculus.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.oculus.android.exoplayer2.util.MediaClock;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.audio.AudioSpatializer;
import com.oculus.video.audio.Mp4Parser;
import com.oculus.video.audio.SpatialAudioTrack;
import com.oculus.video.extractor.OculusExtractorsFactory;
import java.nio.ByteBuffer;

public class SpatialMediaCodecAudioDeviceRenderer extends MediaCodecRenderer implements MediaClock, OculusExtractorsFactory.EventListener {
    private static final int TRACK_NUMBER_UNDEFINED = -1;
    private boolean mAllowPositionDiscontinuity;
    private long mCurrentPositionUs;
    private final AudioRendererEventListener.EventDispatcher mEventDispatcher;
    private final boolean mIsPrimaryTrack;
    private final SpatialAudioTrack mSpatialAudioTrack;
    private volatile String movieMetadataXml;
    private PlaybackParameters playbackParameters;

    @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
    public void onFindVideoSeekTimestamp(long j) {
    }

    @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
    public void onSphericalV1Xml(String str) {
    }

    public SpatialMediaCodecAudioDeviceRenderer(DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSpatializer audioSpatializer) {
        this(drmSessionManager, z, handler, audioRendererEventListener, audioSpatializer, -1, true);
    }

    public SpatialMediaCodecAudioDeviceRenderer(DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSpatializer audioSpatializer, int i, boolean z2) {
        super(1, MediaCodecSelector.DEFAULT, drmSessionManager, z);
        this.playbackParameters = PlaybackParameters.DEFAULT;
        this.mEventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
        this.mSpatialAudioTrack = new SpatialAudioTrack(audioSpatializer, i);
        this.mIsPrimaryTrack = z2;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
        try {
            this.mSpatialAudioTrack.configure(mediaFormat, this.mIsPrimaryTrack);
            if (this.movieMetadataXml != null && this.movieMetadataXml.length() > 0) {
                try {
                    AudioSpatializer audioSpatializer = this.mSpatialAudioTrack.getAudioSpatializer();
                    Mp4Parser mp4Parser = new Mp4Parser(this.movieMetadataXml);
                    audioSpatializer.enableFocus(mp4Parser.getFocusEnabled());
                    if (mp4Parser.getOffFocusLeveldB() < 0.0f) {
                        audioSpatializer.setOffFocusLeveldB(mp4Parser.getOffFocusLeveldB());
                        audioSpatializer.setFocusWidthDegrees(mp4Parser.getFocusWidthDegrees());
                        return;
                    }
                    audioSpatializer.setOffFocusLeveldB(mp4Parser.getOffFocusLevel());
                    audioSpatializer.setFocusWidthDegrees(mp4Parser.getFocusWidthDegrees());
                } catch (Mp4Parser.ParserException unused) {
                }
            }
        } catch (AudioSpatializer.InitializationException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onStarted() {
        super.onStarted();
        if (this.mIsPrimaryTrack) {
            this.mSpatialAudioTrack.play();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onStopped() {
        if (this.mIsPrimaryTrack) {
            this.mSpatialAudioTrack.pause();
        }
        super.onStopped();
    }

    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.Renderer
    public boolean isEnded() {
        return !this.mIsPrimaryTrack || (super.isEnded() && this.mSpatialAudioTrack.canEndStream());
    }

    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.mSpatialAudioTrack.isInitialized() && (!this.mIsPrimaryTrack || this.mSpatialAudioTrack.hasPendingData() || super.isReady());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) throws ExoPlaybackException {
        super.onPositionReset(j, z);
        if (this.mIsPrimaryTrack) {
            this.mSpatialAudioTrack.reset();
            this.mCurrentPositionUs = j;
            this.mAllowPositionDiscontinuity = true;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        try {
            super.onDisabled();
            if (this.mIsPrimaryTrack) {
                this.mSpatialAudioTrack.release();
            }
        } finally {
            this.decoderCounters.ensureUpdated();
            this.mEventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (this.mIsPrimaryTrack) {
            this.mSpatialAudioTrack.updateListenerOrientation();
        }
        super.render(j, j2);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean processOutputBuffer(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws ExoPlaybackException {
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.decoderCounters.skippedOutputBufferCount++;
            if (this.mIsPrimaryTrack) {
                this.mSpatialAudioTrack.handleDiscontinuity();
            }
            return true;
        } else if (!this.mSpatialAudioTrack.isInitialized()) {
            return false;
        } else {
            if (this.mSpatialAudioTrack.getPlayState() != AudioSpatializer.PlayState.PLAYING && getState() == 2) {
                this.mSpatialAudioTrack.play();
            }
            try {
                int handleBuffer = this.mSpatialAudioTrack.handleBuffer(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), j3);
                if ((handleBuffer & 1) != 0) {
                    this.mAllowPositionDiscontinuity = true;
                }
                if ((handleBuffer & 2) == 0) {
                    return false;
                }
                mediaCodec.releaseOutputBuffer(i, false);
                this.decoderCounters.renderedOutputBufferCount++;
                return true;
            } catch (AudioSpatializer.UnsupportedAudioChannelLayoutException | AudioSpatializer.WriteException e) {
                throw ExoPlaybackException.createForRenderer(e, getIndex());
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.util.MediaClock
    public long getPositionUs() {
        long currentPositionUs = this.mSpatialAudioTrack.getCurrentPositionUs();
        if (currentPositionUs != Long.MIN_VALUE) {
            if (!this.mAllowPositionDiscontinuity) {
                currentPositionUs = Math.max(this.mCurrentPositionUs, currentPositionUs);
            }
            this.mCurrentPositionUs = currentPositionUs;
            this.mAllowPositionDiscontinuity = false;
        }
        return this.mCurrentPositionUs;
    }

    @Override // com.oculus.android.exoplayer2.util.MediaClock
    public PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters2) {
        this.playbackParameters = playbackParameters2;
        return getPlaybackParameters();
    }

    @Override // com.oculus.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    @Override // com.oculus.android.exoplayer2.Renderer, com.oculus.android.exoplayer2.BaseRenderer
    public MediaClock getMediaClock() {
        if (this.mIsPrimaryTrack) {
            return this;
        }
        return null;
    }

    @Override // com.oculus.android.exoplayer2.BaseRenderer, com.oculus.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
        if (!this.mIsPrimaryTrack) {
            return;
        }
        if (i != 2) {
            super.handleMessage(i, obj);
        } else {
            this.mSpatialAudioTrack.setVolume(((Float) obj).floatValue());
        }
    }

    @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
    public void onMovieMetadataXml(String str) {
        this.movieMetadataXml = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int supportsFormat(MediaCodecSelector mediaCodecSelector, DrmSessionManager<FrameworkMediaCrypto> drmSessionManager, Format format) throws MediaCodecUtil.DecoderQueryException {
        AudioSpatializer audioSpatializer = this.mSpatialAudioTrack.getAudioSpatializer();
        if (audioSpatializer == null || !audioSpatializer.supportsFormat(format)) {
            return 0;
        }
        String str = format.sampleMimeType;
        if (!MimeTypes.isAudio(str)) {
            return 0;
        }
        MediaCodecInfo decoderInfo = mediaCodecSelector.getDecoderInfo(str, false);
        boolean z = true;
        if (decoderInfo == null) {
            return 1;
        }
        if (Util.SDK_INT >= 21 && ((format.sampleRate != -1 && !decoderInfo.isAudioSampleRateSupportedV21(format.sampleRate)) || (format.channelCount != -1 && !decoderInfo.isAudioChannelCountSupportedV21(format.channelCount)))) {
            z = false;
        }
        return (z ? 4 : 3) | 8;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public MediaCodecInfo getDecoderInfo(MediaCodecSelector mediaCodecSelector, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        return super.getDecoderInfo(mediaCodecSelector, format, z);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void configureCodec(MediaCodecInfo mediaCodecInfo, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto) {
        mediaCodec.configure(format.getFrameworkMediaFormatV16(), (Surface) null, mediaCrypto, 0);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onCodecInitialized(String str, long j, long j2) {
        this.mEventDispatcher.decoderInitialized(str, j, j2);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void onInputFormatChanged(Format format) throws ExoPlaybackException {
        super.onInputFormatChanged(format);
        this.mEventDispatcher.inputFormatChanged(format);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.mediacodec.MediaCodecRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z) throws ExoPlaybackException {
        super.onEnabled(z);
        this.mEventDispatcher.enabled(this.decoderCounters);
    }
}
