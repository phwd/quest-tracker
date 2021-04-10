package com.oculus.video.extractor.mkv;

import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* access modifiers changed from: package-private */
public interface EbmlReader {
    public static final int TYPE_BINARY = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_MASTER = 1;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_UNSIGNED_INT = 2;
    public static final int TYPE_UTF8_STRING = 6;

    void init(EbmlReaderOutput ebmlReaderOutput);

    boolean read(ExtractorInput extractorInput) throws IOException, InterruptedException;

    void reset();
}
