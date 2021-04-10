package com.oculus.android.exoplayer2.extractor.wav;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.ExtractorsFactory;
import com.oculus.android.exoplayer2.extractor.PositionHolder;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.util.MimeTypes;
import java.io.IOException;

public final class WavExtractor implements Extractor {
    public static final ExtractorsFactory FACTORY = new ExtractorsFactory() {
        /* class com.oculus.android.exoplayer2.extractor.wav.WavExtractor.AnonymousClass1 */

        @Override // com.oculus.android.exoplayer2.extractor.ExtractorsFactory
        public Extractor[] createExtractors() {
            return new Extractor[]{new WavExtractor()};
        }
    };
    private static final int MAX_INPUT_SIZE = 32768;
    private int bytesPerFrame;
    private ExtractorOutput extractorOutput;
    private int pendingBytes;
    private TrackOutput trackOutput;
    private WavHeader wavHeader;

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void release() {
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return WavHeaderReader.peek(extractorInput) != null;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        this.trackOutput = extractorOutput2.track(0, 1);
        this.wavHeader = null;
        extractorOutput2.endTracks();
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public void seek(long j, long j2) {
        this.pendingBytes = 0;
    }

    @Override // com.oculus.android.exoplayer2.extractor.Extractor
    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        if (this.wavHeader == null) {
            this.wavHeader = WavHeaderReader.peek(extractorInput);
            WavHeader wavHeader2 = this.wavHeader;
            if (wavHeader2 != null) {
                this.trackOutput.format(Format.createAudioSampleFormat(null, MimeTypes.AUDIO_RAW, null, wavHeader2.getBitrate(), 32768, this.wavHeader.getNumChannels(), this.wavHeader.getSampleRateHz(), this.wavHeader.getEncoding(), null, null, 0, null));
                this.bytesPerFrame = this.wavHeader.getBytesPerFrame();
            } else {
                throw new ParserException("Unsupported or unrecognized wav header.");
            }
        }
        if (!this.wavHeader.hasDataBounds()) {
            WavHeaderReader.skipToData(extractorInput, this.wavHeader);
            this.extractorOutput.seekMap(this.wavHeader);
        }
        int sampleData = this.trackOutput.sampleData(extractorInput, 32768 - this.pendingBytes, true);
        if (sampleData != -1) {
            this.pendingBytes += sampleData;
        }
        int i = this.pendingBytes / this.bytesPerFrame;
        if (i > 0) {
            long timeUs = this.wavHeader.getTimeUs(extractorInput.getPosition() - ((long) this.pendingBytes));
            int i2 = i * this.bytesPerFrame;
            this.pendingBytes -= i2;
            this.trackOutput.sampleMetadata(timeUs, 1, i2, this.pendingBytes, null);
        }
        if (sampleData == -1) {
            return -1;
        }
        return 0;
    }
}
