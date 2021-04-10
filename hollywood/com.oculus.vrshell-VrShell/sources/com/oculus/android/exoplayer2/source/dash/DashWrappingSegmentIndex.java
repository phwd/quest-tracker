package com.oculus.android.exoplayer2.source.dash;

import com.oculus.android.exoplayer2.extractor.ChunkIndex;
import com.oculus.android.exoplayer2.source.dash.manifest.RangedUri;

public final class DashWrappingSegmentIndex implements DashSegmentIndex {
    private final ChunkIndex chunkIndex;

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getFirstSegmentNum() {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public boolean isExplicit() {
        return true;
    }

    public DashWrappingSegmentIndex(ChunkIndex chunkIndex2) {
        this.chunkIndex = chunkIndex2;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public int getSegmentCount(long j) {
        return this.chunkIndex.length;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getTimeUs(long j) {
        return this.chunkIndex.timesUs[(int) j];
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getDurationUs(long j, long j2) {
        return this.chunkIndex.durationsUs[(int) j];
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public RangedUri getSegmentUrl(long j) {
        int i = (int) j;
        return new RangedUri(null, this.chunkIndex.offsets[i], (long) this.chunkIndex.sizes[i]);
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getSegmentNum(long j, long j2) {
        return (long) this.chunkIndex.getChunkIndex(j);
    }
}
