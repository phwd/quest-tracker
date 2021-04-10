package com.oculus.video.renderer;

import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Handler;
import com.oculus.android.exoplayer2.BaseRenderer;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.audio.AudioRendererEventListener;
import com.oculus.android.exoplayer2.decoder.DecoderCounters;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.oculus.android.exoplayer2.ext.opus.OpusDecoder;
import com.oculus.android.exoplayer2.ext.opus.OpusDecoderException;
import com.oculus.android.exoplayer2.ext.opus.OpusLibrary;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.video.audio.OutputBufferConsumer;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@TargetApi(16)
public abstract class OpusAudioRenderer extends BaseRenderer {
    private static final int INITIAL_INPUT_BUFFER_SIZE = 9600;
    private static final int NUM_BUFFERS = 16;
    public final DecoderCounters decoderCounters = new DecoderCounters();
    private final AudioRendererEventListener.EventDispatcher eventDispatcher;
    private final AtomicReference<Format> mFormat = new AtomicReference<>();
    private OpusAudioProcessor mOpusAudioProcessor;
    private final AtomicReference<SimpleOutputBuffer> mOutputBufferRef = new AtomicReference<>();

    /* access modifiers changed from: protected */
    public abstract void onOutputFormatChanged(MediaFormat mediaFormat) throws ExoPlaybackException;

    /* access modifiers changed from: protected */
    public abstract void onOutputStreamEnded();

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStarted() {
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStopped() {
    }

    /* access modifiers changed from: protected */
    public abstract boolean renderOutputBuffer(SimpleOutputBuffer simpleOutputBuffer) throws ExoPlaybackException;

    /* access modifiers changed from: private */
    public class OpusAudioProcessor extends OutputBufferConsumer<ExoPlaybackException> {
        private volatile long mCurrentPositionUs = 0;
        private OpusDecoder mDecoder;
        private final DecoderInputBuffer mFlagsOnlyBuffer = DecoderInputBuffer.newFlagsOnlyInstance();
        private final FormatHolder mFormatHolder = new FormatHolder();
        private DecoderInputBuffer mInputBuffer;
        private volatile boolean mInputStreamEnded;
        private volatile boolean mOutputStreamEnded;

        OpusAudioProcessor(boolean z) {
            super(z, true);
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.video.audio.OutputBufferConsumer
        public void consumeSynchronously() throws ExoPlaybackException {
            if (OpusAudioRenderer.this.mOutputBufferRef.get() == null) {
                decodeToOutputBuffer();
            } else if (isAsync()) {
                synchronized (this) {
                    OpusAudioRenderer.this.mOutputBufferRef.set(renderAndGetRemaining((SimpleOutputBuffer) OpusAudioRenderer.this.mOutputBufferRef.get()));
                }
            } else {
                OpusAudioRenderer.this.mOutputBufferRef.set(renderAndGetRemaining((SimpleOutputBuffer) OpusAudioRenderer.this.mOutputBufferRef.get()));
            }
        }

        private void decodeToOutputBuffer() throws ExoPlaybackException {
            if (OpusAudioRenderer.this.mFormat.get() == null) {
                OpusAudioRenderer.this.mFormat.set(readFormat());
            }
            if (OpusAudioRenderer.this.mFormat.get() != null) {
                if (this.mDecoder == null) {
                    long nanoTime = System.nanoTime() / C.MICROS_PER_SECOND;
                    List<byte[]> list = ((Format) OpusAudioRenderer.this.mFormat.get()).initializationData;
                    if (list.size() >= 1) {
                        try {
                            this.mDecoder = new OpusDecoder(16, 16, OpusAudioRenderer.INITIAL_INPUT_BUFFER_SIZE, list, null);
                            long nanoTime2 = System.nanoTime() / C.MICROS_PER_SECOND;
                            OpusAudioRenderer.this.eventDispatcher.decoderInitialized(OpusDecoder.class.getName(), nanoTime2, nanoTime2 - nanoTime);
                            OpusAudioRenderer.this.decoderCounters.decoderInitCount++;
                        } catch (Exception e) {
                            throw ExoPlaybackException.createForRenderer(e, OpusAudioRenderer.this.getIndex());
                        }
                    } else {
                        throw ExoPlaybackException.createForRenderer(new Exception("Missing initialization data"), OpusAudioRenderer.this.getIndex());
                    }
                }
                try {
                    drainOutputBuffer();
                    do {
                    } while (feedInputBuffer());
                    OpusAudioRenderer.this.decoderCounters.ensureUpdated();
                } catch (OpusDecoderException e2) {
                    throw ExoPlaybackException.createForRenderer(e2, OpusAudioRenderer.this.getIndex());
                }
            }
        }

        private Format readFormat() throws ExoPlaybackException {
            this.mFlagsOnlyBuffer.clear();
            if (OpusAudioRenderer.this.readSource(this.mFormatHolder, this.mFlagsOnlyBuffer, true) != -5) {
                return null;
            }
            Format format = this.mFormatHolder.format;
            OpusAudioRenderer.this.eventDispatcher.inputFormatChanged(format);
            OpusAudioRenderer.this.onOutputFormatChanged(format.getFrameworkMediaFormatV16());
            return format;
        }

        private boolean feedInputBuffer() throws OpusDecoderException {
            if (this.mInputStreamEnded) {
                return false;
            }
            if (this.mInputBuffer == null) {
                this.mInputBuffer = this.mDecoder.dequeueInputBuffer();
                if (this.mInputBuffer == null) {
                    return false;
                }
            }
            this.mInputBuffer.timeUs = this.mCurrentPositionUs;
            int readSource = OpusAudioRenderer.this.readSource(this.mFormatHolder, this.mInputBuffer, false);
            if (readSource == -3) {
                return false;
            }
            if (readSource == -5) {
                OpusAudioRenderer.this.mFormat.set(this.mFormatHolder.format);
                OpusAudioRenderer.this.eventDispatcher.inputFormatChanged(this.mFormatHolder.format);
                return true;
            } else if (readSource == -1) {
                this.mInputBuffer.setFlags(4);
                this.mDecoder.queueInputBuffer(this.mInputBuffer);
                this.mInputBuffer = null;
                this.mInputStreamEnded = true;
                return false;
            } else {
                this.mDecoder.queueInputBuffer(this.mInputBuffer);
                this.mInputBuffer = null;
                return true;
            }
        }

        private void drainOutputBuffer() throws OpusDecoderException, ExoPlaybackException {
            if (!this.mOutputStreamEnded) {
                OpusAudioRenderer.this.mOutputBufferRef.set(renderAndGetRemaining((SimpleOutputBuffer) this.mDecoder.dequeueOutputBuffer()));
            }
        }

        private SimpleOutputBuffer renderAndGetRemaining(SimpleOutputBuffer simpleOutputBuffer) throws ExoPlaybackException {
            if (simpleOutputBuffer == null) {
                return null;
            }
            if (simpleOutputBuffer.isEndOfStream()) {
                this.mOutputStreamEnded = true;
                OpusAudioRenderer.this.onOutputStreamEnded();
                simpleOutputBuffer.release();
                return null;
            } else if (!simpleOutputBuffer.isDecodeOnly() && !OpusAudioRenderer.this.renderOutputBuffer(simpleOutputBuffer)) {
                return simpleOutputBuffer;
            } else {
                simpleOutputBuffer.release();
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isEnded() {
            return this.mOutputStreamEnded;
        }

        public void setPositionUs(long j) {
            this.mCurrentPositionUs = j;
        }

        private void flushInternal() {
            this.mInputStreamEnded = false;
            this.mOutputStreamEnded = false;
            if (this.mDecoder != null) {
                this.mInputBuffer = null;
                SimpleOutputBuffer simpleOutputBuffer = (SimpleOutputBuffer) OpusAudioRenderer.this.mOutputBufferRef.get();
                if (simpleOutputBuffer != null) {
                    simpleOutputBuffer.release();
                    OpusAudioRenderer.this.mOutputBufferRef.set(null);
                    OpusAudioRenderer.this.mFormat.set(null);
                    this.mCurrentPositionUs = 0;
                    this.mDecoder.flush();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void flush() {
            super.disable();
            synchronized (this) {
                flushInternal();
            }
            super.enable();
        }

        @Override // com.oculus.video.audio.OutputBufferConsumer
        public void disable() {
            super.disable();
            synchronized (this) {
                flushInternal();
                if (this.mDecoder != null) {
                    this.mDecoder.release();
                    this.mDecoder = null;
                    OpusAudioRenderer.this.decoderCounters.decoderReleaseCount++;
                }
            }
        }
    }

    public OpusAudioRenderer(boolean z, Handler handler, AudioRendererEventListener audioRendererEventListener) {
        super(1);
        this.mOpusAudioProcessor = new OpusAudioProcessor(z);
        this.eventDispatcher = new AudioRendererEventListener.EventDispatcher(handler, audioRendererEventListener);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onEnabled(boolean z) throws ExoPlaybackException {
        this.decoderCounters.ensureUpdated();
        this.eventDispatcher.enabled(this.decoderCounters);
        this.mOpusAudioProcessor.enable();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        try {
            this.mOpusAudioProcessor.disable();
        } finally {
            super.onDisabled();
            this.decoderCounters.ensureUpdated();
            this.eventDispatcher.disabled(this.decoderCounters);
        }
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.mOpusAudioProcessor.isEnded();
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isReady() {
        return this.mFormat.get() != null && (isSourceReady() || this.mOutputBufferRef.get() != null);
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        if (!this.mOpusAudioProcessor.isEnded()) {
            this.mOpusAudioProcessor.maybeThrowLastException();
            this.mOpusAudioProcessor.setPositionUs(j);
            this.mOpusAudioProcessor.consume();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        onDiscontinuity(j);
    }

    /* access modifiers changed from: protected */
    public void onDiscontinuity(long j) {
        this.mOpusAudioProcessor.flush();
        this.mOpusAudioProcessor.setPositionUs(j);
    }

    @Override // com.oculus.android.exoplayer2.RendererCapabilities
    public int supportsFormat(Format format) {
        return (!OpusLibrary.isAvailable() || !MimeTypes.AUDIO_OPUS.equalsIgnoreCase(format.sampleMimeType)) ? 0 : 4;
    }
}
