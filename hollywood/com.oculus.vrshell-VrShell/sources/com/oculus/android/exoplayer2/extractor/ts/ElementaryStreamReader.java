package com.oculus.android.exoplayer2.extractor.ts;

import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.oculus.android.exoplayer2.util.ParsableByteArray;

public interface ElementaryStreamReader {
    void consume(ParsableByteArray parsableByteArray) throws ParserException;

    void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator);

    void packetFinished();

    void packetStarted(long j, boolean z);

    void seek();
}
