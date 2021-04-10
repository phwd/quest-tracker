package com.oculus.video.metadata;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.oculus.android.exoplayer2.BaseRenderer;
import com.oculus.android.exoplayer2.ExoPlaybackException;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.metadata.MetadataDecoder;
import com.oculus.android.exoplayer2.metadata.MetadataDecoderException;
import com.oculus.android.exoplayer2.metadata.MetadataDecoderFactory;
import com.oculus.android.exoplayer2.metadata.MetadataInputBuffer;
import com.oculus.android.exoplayer2.metadata.MetadataOutput;
import com.oculus.android.exoplayer2.util.Assertions;
import java.util.Arrays;

public class OculusMetadataRenderer extends BaseRenderer implements Handler.Callback {
    private static final int MAX_PENDING_METADATA_COUNT = 5;
    private static final int MSG_INVOKE_RENDERER = 0;
    private final MetadataInputBuffer buffer;
    private MetadataDecoder decoder;
    private final MetadataDecoderFactory decoderFactory;
    private final FormatHolder formatHolder;
    private boolean inputStreamEnded;
    private final MetadataOutput output;
    private final Handler outputHandler;
    private final Metadata[] pendingMetadata;
    private int pendingMetadataCount;
    private int pendingMetadataIndex;
    private final long[] pendingMetadataTimestamps;

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isReady() {
        return true;
    }

    public OculusMetadataRenderer(MetadataOutput metadataOutput, Looper looper) {
        this(metadataOutput, looper, MetadataDecoderFactory.DEFAULT);
    }

    public OculusMetadataRenderer(MetadataOutput metadataOutput, Looper looper, MetadataDecoderFactory metadataDecoderFactory) {
        super(4);
        Handler handler;
        this.output = (MetadataOutput) Assertions.checkNotNull(metadataOutput);
        if (looper == null) {
            handler = null;
        } else {
            handler = new Handler(looper, this);
        }
        this.outputHandler = handler;
        this.decoderFactory = (MetadataDecoderFactory) Assertions.checkNotNull(metadataDecoderFactory);
        this.formatHolder = new FormatHolder();
        this.buffer = new MetadataInputBuffer();
        this.pendingMetadata = new Metadata[5];
        this.pendingMetadataTimestamps = new long[5];
    }

    @Override // com.oculus.android.exoplayer2.RendererCapabilities
    public int supportsFormat(Format format) {
        if (this.decoderFactory.supportsFormat(format)) {
            return supportsFormatDrm(null, format.drmInitData) ? 4 : 2;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onStreamChanged(Format[] formatArr, long j) throws ExoPlaybackException {
        this.decoder = this.decoderFactory.createDecoder(formatArr[0]);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onPositionReset(long j, boolean z) {
        flushPendingMetadata();
        this.inputStreamEnded = false;
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public void render(long j, long j2) throws ExoPlaybackException {
        while (!this.inputStreamEnded && this.pendingMetadataCount < 5) {
            this.buffer.clear();
            if (readSource(this.formatHolder, this.buffer, false) != -4) {
                break;
            } else if (this.buffer.isEndOfStream()) {
                this.inputStreamEnded = true;
            } else if (!this.buffer.isDecodeOnly()) {
                this.buffer.subsampleOffsetUs = this.formatHolder.format.subsampleOffsetUs;
                this.buffer.flip();
                try {
                    int i = (this.pendingMetadataIndex + this.pendingMetadataCount) % 5;
                    this.pendingMetadata[i] = this.decoder.decode(this.buffer);
                    this.pendingMetadataTimestamps[i] = this.buffer.timeUs;
                    this.pendingMetadataCount++;
                } catch (MetadataDecoderException e) {
                    throw ExoPlaybackException.createForRenderer(e, getIndex());
                }
            }
        }
        long j3 = this.decoder instanceof CameraMotionDecoder ? CameraMotionDecoder.ADVANCED_RENDERING_OFFSET_US : 0;
        while (this.pendingMetadataCount > 0) {
            long[] jArr = this.pendingMetadataTimestamps;
            int i2 = this.pendingMetadataIndex;
            if (jArr[i2] <= j + j3) {
                invokeRenderer(this.pendingMetadata[i2]);
                Metadata[] metadataArr = this.pendingMetadata;
                int i3 = this.pendingMetadataIndex;
                metadataArr[i3] = null;
                this.pendingMetadataIndex = (i3 + 1) % 5;
                this.pendingMetadataCount--;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.BaseRenderer
    public void onDisabled() {
        flushPendingMetadata();
        this.decoder = null;
    }

    @Override // com.oculus.android.exoplayer2.Renderer
    public boolean isEnded() {
        return this.inputStreamEnded;
    }

    private void invokeRenderer(Metadata metadata) {
        Handler handler = this.outputHandler;
        if (handler != null) {
            handler.obtainMessage(0, metadata).sendToTarget();
        } else {
            invokeRendererInternal(metadata);
        }
    }

    private void flushPendingMetadata() {
        Arrays.fill(this.pendingMetadata, (Object) null);
        this.pendingMetadataIndex = 0;
        this.pendingMetadataCount = 0;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            invokeRendererInternal((Metadata) message.obj);
            return true;
        }
        throw new IllegalStateException();
    }

    private void invokeRendererInternal(Metadata metadata) {
        this.output.onMetadata(metadata);
    }
}
