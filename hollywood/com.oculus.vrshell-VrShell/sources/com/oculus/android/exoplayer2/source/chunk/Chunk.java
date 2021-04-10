package com.oculus.android.exoplayer2.source.chunk;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.Loader;
import com.oculus.android.exoplayer2.util.Assertions;

public abstract class Chunk implements Loader.Loadable {
    protected final DataSource dataSource;
    public final DataSpec dataSpec;
    public final long endTimeUs;
    public final long startTimeUs;
    public final Format trackFormat;
    public final Object trackSelectionData;
    public final int trackSelectionReason;
    public final int type;

    public abstract long bytesLoaded();

    public Chunk(DataSource dataSource2, DataSpec dataSpec2, int i, Format format, int i2, Object obj, long j, long j2) {
        this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
        this.dataSpec = (DataSpec) Assertions.checkNotNull(dataSpec2);
        this.type = i;
        this.trackFormat = format;
        this.trackSelectionReason = i2;
        this.trackSelectionData = obj;
        this.startTimeUs = j;
        this.endTimeUs = j2;
    }

    public final long getDurationUs() {
        return this.endTimeUs - this.startTimeUs;
    }
}
