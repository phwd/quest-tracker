package com.oculus.android.exoplayer2.source.dash.manifest;

import com.oculus.android.exoplayer2.source.dash.DashSegmentIndex;

/* access modifiers changed from: package-private */
public final class SingleSegmentIndex implements DashSegmentIndex {
    private final RangedUri uri;

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getDurationUs(long j, long j2) {
        return j2;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getFirstSegmentNum() {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public int getSegmentCount(long j) {
        return 1;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getSegmentNum(long j, long j2) {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public long getTimeUs(long j) {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public boolean isExplicit() {
        return true;
    }

    public SingleSegmentIndex(RangedUri rangedUri) {
        this.uri = rangedUri;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
    public RangedUri getSegmentUrl(long j) {
        return this.uri;
    }
}
