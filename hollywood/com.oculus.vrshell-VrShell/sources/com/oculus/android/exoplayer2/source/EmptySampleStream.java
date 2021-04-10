package com.oculus.android.exoplayer2.source;

import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import java.io.IOException;

public final class EmptySampleStream implements SampleStream {
    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        decoderInputBuffer.setFlags(4);
        return -4;
    }
}
