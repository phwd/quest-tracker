package com.oculus.android.exoplayer2.metadata;

import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;

public final class MetadataInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public MetadataInputBuffer() {
        super(1);
    }
}
