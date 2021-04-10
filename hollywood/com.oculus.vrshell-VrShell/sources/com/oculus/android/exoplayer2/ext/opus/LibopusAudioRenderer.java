package com.oculus.android.exoplayer2.ext.opus;

import android.os.Handler;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.audio.AudioProcessor;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer;
import com.oculus.android.exoplayer2.drm.DrmSessionManager;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;
import com.oculus.android.exoplayer2.util.MimeTypes;

public final class LibopusAudioRenderer extends SimpleDecoderAudioRenderer {
    private static final int INITIAL_INPUT_BUFFER_SIZE = 5760;
    private static final int NUM_BUFFERS = 16;
    private OpusDecoder decoder;

    public LibopusAudioRenderer() {
        this(null, null, new AudioProcessor[0]);
    }

    public LibopusAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, AudioProcessor... audioProcessorArr) {
        super(handler, audioRendererEventListener, audioProcessorArr);
    }

    public LibopusAudioRenderer(Handler handler, AudioRendererEventListener audioRendererEventListener, DrmSessionManager<ExoMediaCrypto> drmSessionManager, boolean z, AudioProcessor... audioProcessorArr) {
        super(handler, audioRendererEventListener, null, drmSessionManager, z, audioProcessorArr);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public int supportsFormatInternal(DrmSessionManager<ExoMediaCrypto> drmSessionManager, Format format) {
        if (!OpusLibrary.isAvailable() || !MimeTypes.AUDIO_OPUS.equalsIgnoreCase(format.sampleMimeType)) {
            return 0;
        }
        if (!supportsOutputEncoding(2)) {
            return 1;
        }
        if (!supportsFormatDrm(drmSessionManager, format.drmInitData)) {
            return 2;
        }
        return 4;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public OpusDecoder createDecoder(Format format, ExoMediaCrypto exoMediaCrypto) throws OpusDecoderException {
        this.decoder = new OpusDecoder(16, 16, INITIAL_INPUT_BUFFER_SIZE, format.initializationData, exoMediaCrypto);
        return this.decoder;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.audio.SimpleDecoderAudioRenderer
    public Format getOutputFormat() {
        return Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, -1, -1, this.decoder.getChannelCount(), this.decoder.getSampleRate(), 2, null, null, 0, null);
    }
}
