package com.oculus.android.exoplayer2.metadata;

public class MetadataDecoderException extends Exception {
    public MetadataDecoderException(String str) {
        super(str);
    }

    public MetadataDecoderException(String str, Throwable th) {
        super(str, th);
    }
}
