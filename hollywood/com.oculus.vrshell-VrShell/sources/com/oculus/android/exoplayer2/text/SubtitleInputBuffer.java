package com.oculus.android.exoplayer2.text;

import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;

public class SubtitleInputBuffer extends DecoderInputBuffer {
    public long subsampleOffsetUs;

    public SubtitleInputBuffer() {
        super(1);
    }
}
