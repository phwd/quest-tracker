package com.oculus.android.exoplayer2.ext.ffmpeg;

import android.os.Handler;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.audio.AudioProcessor;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.audio.AudioSink;
import com.oculus.android.exoplayer2.audio.DefaultAudioSink;
import com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;
import com.oculus.android.exoplayer2.util.MimeTypes;

public final class FfmpegAudioRenderer extends SimpleDecoderAudioRenderer {
    private static final int INITIAL_INPUT_BUFFER_SIZE = 5760;
    private static final int NUM_BUFFERS = 16;
    private FfmpegDecoder decoder;
    private final boolean enableFloatOutput;

    @Override // com.oculus.android.exoplayer2.BaseRenderer, com.oculus.android.exoplayer2.RendererCapabilities
    public final int supportsMixedMimeTypeAdaptation() throws ExoPlaybackException {
        return 8;
    }

    public FfmpegAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public FfmpegAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioProcessor... audioProcessorArr) {
        this(handler, audioRendererEventListener, new DefaultAudioSink(null, audioProcessorArr), false);
    }

    public FfmpegAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioSink audioSink, boolean z) {
        super(handler, audioRendererEventListener, null, false, audioSink);
        this.enableFloatOutput = z;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager, Format format) {
        String str = format.sampleMimeType;
        if (!FfmpegLibrary.isAvailable() || !MimeTypes.isAudio(str)) {
            return 0;
        }
        if (!FfmpegLibrary.supportsFormat(str) || !isOutputSupported(format)) {
            return 1;
        }
        return !supportsFormatDrm(drmSessionManager, format.drmInitData) ? 2 : 4;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public FfmpegDecoder createDecoder(Format format, ExoMediaCrypto exoMediaCrypto) throws FfmpegDecoderException {
        this.decoder = new FfmpegDecoder(16, 16, INITIAL_INPUT_BUFFER_SIZE, format.sampleMimeType, format.initializationData, shouldUseFloatOutput(format));
        return this.decoder;
    }

    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public Format getOutputFormat() {
        return Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, -1, -1, this.decoder.getChannelCount(), this.decoder.getSampleRate(), this.decoder.getEncoding(), null, null, 0, null);
    }

    private boolean isOutputSupported(Format format) {
        return shouldUseFloatOutput(format) || supportsOutputEncoding(2);
    }

    private boolean shouldUseFloatOutput(Format format) {
        if (!this.enableFloatOutput || !supportsOutputEncoding(4)) {
            return false;
        }
        String str = format.sampleMimeType;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 187078296) {
            if (hashCode == 187094639 && str.equals(MimeTypes.AUDIO_RAW)) {
                c = 0;
            }
        } else if (str.equals(MimeTypes.AUDIO_AC3)) {
            c = 1;
        }
        if (c != 0) {
            if (c != 1) {
                return true;
            }
            return false;
        } else if (format.pcmEncoding == Integer.MIN_VALUE || format.pcmEncoding == 1073741824 || format.pcmEncoding == 4) {
            return true;
        } else {
            return false;
        }
    }
}
