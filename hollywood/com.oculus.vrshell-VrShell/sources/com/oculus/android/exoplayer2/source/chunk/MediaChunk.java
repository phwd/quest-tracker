package com.oculus.android.exoplayer2.source.chunk;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.util.Assertions;

public abstract class MediaChunk extends Chunk {
    public final long chunkIndex;

    public abstract boolean isLoadCompleted();

    public MediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3) {
        super(dataSource, dataSpec, 1, format, i, obj, j, j2);
        Assertions.checkNotNull(format);
        this.chunkIndex = j3;
    }

    public long getNextChunkIndex() {
        return this.chunkIndex + 1;
    }
}
