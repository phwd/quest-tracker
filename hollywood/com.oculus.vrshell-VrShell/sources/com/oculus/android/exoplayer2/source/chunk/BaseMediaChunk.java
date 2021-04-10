package com.oculus.android.exoplayer2.source.chunk;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;

public abstract class BaseMediaChunk extends MediaChunk {
    private int[] firstSampleIndices;
    private BaseMediaChunkOutput output;

    public BaseMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3);
    }

    public void init(BaseMediaChunkOutput baseMediaChunkOutput) {
        this.output = baseMediaChunkOutput;
        this.firstSampleIndices = baseMediaChunkOutput.getWriteIndices();
    }

    public final int getFirstSampleIndex(int i) {
        return this.firstSampleIndices[i];
    }

    /* access modifiers changed from: protected */
    public final BaseMediaChunkOutput getOutput() {
        return this.output;
    }
}
