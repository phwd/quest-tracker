package com.oculus.video.source.hls;

import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.hls.SampleQueueMappingException;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class HlsSampleStream implements SampleStream {
    private int sampleQueueIndex = -1;
    private final HlsSampleStreamWrapper sampleStreamWrapper;
    private final int trackGroupIndex;

    public HlsSampleStream(HlsSampleStreamWrapper hlsSampleStreamWrapper, int i) {
        this.sampleStreamWrapper = hlsSampleStreamWrapper;
        this.trackGroupIndex = i;
    }

    public void unbindSampleQueue() {
        if (this.sampleQueueIndex != -1) {
            this.sampleStreamWrapper.unbindSampleQueue(this.trackGroupIndex);
            this.sampleQueueIndex = -1;
        }
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return ensureBoundSampleQueue() && this.sampleStreamWrapper.isReady(this.sampleQueueIndex);
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
        if (ensureBoundSampleQueue() || !this.sampleStreamWrapper.isMappingFinished()) {
            this.sampleStreamWrapper.maybeThrowError();
            return;
        }
        throw new SampleQueueMappingException(this.sampleStreamWrapper.getTrackGroups().get(this.trackGroupIndex).getFormat(0).sampleMimeType);
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (!ensureBoundSampleQueue()) {
            return -3;
        }
        return this.sampleStreamWrapper.readData(this.sampleQueueIndex, formatHolder, decoderInputBuffer, z);
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        if (!ensureBoundSampleQueue()) {
            return 0;
        }
        return this.sampleStreamWrapper.skipData(this.sampleQueueIndex, j);
    }

    private boolean ensureBoundSampleQueue() {
        if (this.sampleQueueIndex != -1) {
            return true;
        }
        this.sampleQueueIndex = this.sampleStreamWrapper.bindSampleQueueToSampleStream(this.trackGroupIndex);
        if (this.sampleQueueIndex != -1) {
            return true;
        }
        return false;
    }
}
