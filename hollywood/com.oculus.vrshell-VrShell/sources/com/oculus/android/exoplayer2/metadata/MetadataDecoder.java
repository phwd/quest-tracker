package com.oculus.android.exoplayer2.metadata;

public interface MetadataDecoder {
    Metadata decode(MetadataInputBuffer metadataInputBuffer) throws MetadataDecoderException;
}
