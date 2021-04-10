package com.oculus.android.exoplayer2.extractor.ogg;

import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface OggSeeker {
    SeekMap createSeekMap();

    long read(ExtractorInput extractorInput) throws IOException, InterruptedException;

    long startSeek(long j);
}
