package com.oculus.video.renderer;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Handler;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.PlaybackParameters;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.oculus.android.exoplayer2.util.MediaClock;
import com.oculus.video.audio.AudioSpatializer;
import com.oculus.video.audio.Mp4Parser;
import com.oculus.video.audio.SpatialAudioTrack;
import com.oculus.video.extractor.OculusExtractorsFactory;

@TargetApi(16)
public class SpatialOpusAudioDeviceRenderer extends OpusAudioRenderer implements MediaClock, OculusExtractorsFactory.EventListener {
    private boolean mAllowPositionDiscontinuity;
    private long mCurrentPositionUs;
    private final SpatialAudioTrack mSpatialAudioTrack;
    private volatile String movieMetadataXml;
    private PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;

    @Override // com.oculus.android.exoplayer2.Renderer, com.oculus.android.exoplayer2.BaseRenderer
    public MediaClock getMediaClock() {
        return this;
    }

    @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
    public void onFindVideoSeekTimestamp(long j) {
    }

    @Override // com.oculus.video.extractor.OculusExtractorsFactory.EventListener
    public void onSphericalV1Xml(String str) {
    }

    public SpatialOpusAudioDeviceRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSpatializer audioSpatializer) {
        super(true, handler, audioRendererEventListener);
        this.mSpatialAudioTrack = new SpatialAudioTrack(audioSpatializer);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer
    public void onOutputFormatChanged(MediaFormat mediaFormat) throws ExoPlaybackException {
        try {
            this.mSpatialAudioTrack.configure(mediaFormat, true);
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
                    audioSpatializer.setFocusProperties(mp4Parser.getOffFocusLevel(), mp4Parser.getFocusWidthDegrees());
                } catch (Mp4Parser.ParserException unused) {
                }
            }
        } catch (AudioSpatializer.InitializationException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onStarted() {
        super.onStarted();
        this.mSpatialAudioTrack.play();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onStopped() {
        this.mSpatialAudioTrack.pause();
        super.onStopped();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer, com.oculus.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        this.mSpatialAudioTrack.release();
        super.onDisabled();
    }

    @Override // com.oculus.android.exoplayer2.Renderer, com.oculus.video.renderer.OpusAudioRenderer
    public boolean isEnded() {
        return super.isEnded() && this.mSpatialAudioTrack.canEndStream();
    }

    @Override // com.oculus.android.exoplayer2.Renderer, com.oculus.video.renderer.OpusAudioRenderer
    public boolean isReady() {
        return this.mSpatialAudioTrack.isInitialized() && (this.mSpatialAudioTrack.hasPendingData() || super.isReady());
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer
    public void onDiscontinuity(long j) {
        super.onDiscontinuity(j);
        this.mSpatialAudioTrack.reset();
        this.mCurrentPositionUs = j;
        this.mAllowPositionDiscontinuity = true;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer
    public boolean renderOutputBuffer(SimpleOutputBuffer simpleOutputBuffer) throws ExoPlaybackException {
        if (!this.mSpatialAudioTrack.isInitialized()) {
            return false;
        }
        if (this.mSpatialAudioTrack.getPlayState() != AudioSpatializer.PlayState.PLAYING && getState() == 2) {
            this.mSpatialAudioTrack.play();
        }
        try {
            int handleBuffer = this.mSpatialAudioTrack.handleBuffer(simpleOutputBuffer.data, simpleOutputBuffer.data.position(), simpleOutputBuffer.data.remaining(), simpleOutputBuffer.timeUs);
            if ((handleBuffer & 1) != 0) {
                this.mAllowPositionDiscontinuity = true;
            }
            if ((handleBuffer & 2) == 0) {
                return false;
            }
            this.decoderCounters.renderedOutputBufferCount++;
            return true;
        } catch (AudioSpatializer.UnsupportedAudioChannelLayoutException | AudioSpatializer.WriteException e) {
            throw ExoPlaybackException.createForRenderer(e, getIndex());
        }
    }

    @Override // com.oculus.android.exoplayer2.Renderer, com.oculus.video.renderer.OpusAudioRenderer
    public void render(long j, long j2) throws ExoPlaybackException {
        this.mSpatialAudioTrack.updateListenerOrientation();
        super.render(j, j2);
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
        return this.playbackParameters;
    }

    @Override // com.oculus.android.exoplayer2.util.MediaClock
    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.video.renderer.OpusAudioRenderer
    public void onOutputStreamEnded() {
        this.mSpatialAudioTrack.handleEndOfStream();
    }

    @Override // com.oculus.android.exoplayer2.BaseRenderer, com.oculus.android.exoplayer2.PlayerMessage.Target
    public void handleMessage(int i, Object obj) throws ExoPlaybackException {
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

    @Override // com.oculus.video.renderer.OpusAudioRenderer, com.oculus.android.exoplayer2.RendererCapabilities
    public int supportsFormat(Format format) {
        if (this.mSpatialAudioTrack.getAudioSpatializer().supportsFormat(format)) {
            return super.supportsFormat(format);
        }
        return 0;
    }
}
