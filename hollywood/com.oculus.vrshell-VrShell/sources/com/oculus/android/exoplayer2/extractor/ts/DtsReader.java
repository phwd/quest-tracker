package com.oculus.android.exoplayer2.extractor.ts;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.audio.DtsUtil;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.oculus.android.exoplayer2.util.ParsableByteArray;

public final class DtsReader implements ElementaryStreamReader {
    private static final int HEADER_SIZE = 18;
    private static final int STATE_FINDING_SYNC = 0;
    private static final int STATE_READING_HEADER = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private int bytesRead;
    private Format format;
    private String formatId;
    private final ParsableByteArray headerScratchBytes = new ParsableByteArray(new byte[18]);
    private final String language;
    private TrackOutput output;
    private long sampleDurationUs;
    private int sampleSize;
    private int state = 0;
    private int syncBytes;
    private long timeUs;

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public DtsReader(String str) {
        this.language = str;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        this.state = 0;
        this.bytesRead = 0;
        this.syncBytes = 0;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        this.timeUs = j;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            int i = this.state;
            if (i != 0) {
                if (i != 1) {
                    if (i == 2) {
                        int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                        this.output.sampleData(parsableByteArray, min);
                        this.bytesRead += min;
                        int i2 = this.bytesRead;
                        int i3 = this.sampleSize;
                        if (i2 == i3) {
                            this.output.sampleMetadata(this.timeUs, 1, i3, 0, null);
                            this.timeUs += this.sampleDurationUs;
                            this.state = 0;
                        }
                    }
                } else if (continueRead(parsableByteArray, this.headerScratchBytes.data, 18)) {
                    parseHeader();
                    this.headerScratchBytes.setPosition(0);
                    this.output.sampleData(this.headerScratchBytes, 18);
                    this.state = 2;
                }
            } else if (skipToNextSync(parsableByteArray)) {
                this.state = 1;
            }
        }
    }

    private boolean continueRead(ParsableByteArray parsableByteArray, byte[] bArr, int i) {
        int min = Math.min(parsableByteArray.bytesLeft(), i - this.bytesRead);
        parsableByteArray.readBytes(bArr, this.bytesRead, min);
        this.bytesRead += min;
        return this.bytesRead == i;
    }

    private boolean skipToNextSync(ParsableByteArray parsableByteArray) {
        while (parsableByteArray.bytesLeft() > 0) {
            this.syncBytes <<= 8;
            this.syncBytes |= parsableByteArray.readUnsignedByte();
            if (DtsUtil.isSyncWord(this.syncBytes)) {
                this.headerScratchBytes.data[0] = (byte) ((this.syncBytes >> 24) & 255);
                this.headerScratchBytes.data[1] = (byte) ((this.syncBytes >> 16) & 255);
                this.headerScratchBytes.data[2] = (byte) ((this.syncBytes >> 8) & 255);
                this.headerScratchBytes.data[3] = (byte) (this.syncBytes & 255);
                this.bytesRead = 4;
                this.syncBytes = 0;
                return true;
            }
        }
        return false;
    }

    private void parseHeader() {
        byte[] bArr = this.headerScratchBytes.data;
        if (this.format == null) {
            this.format = DtsUtil.parseDtsFormat(bArr, this.formatId, this.language, null);
            this.output.format(this.format);
        }
        this.sampleSize = DtsUtil.getDtsFrameSize(bArr);
        this.sampleDurationUs = (long) ((int) ((((long) DtsUtil.parseDtsAudioSampleCount(bArr)) * C.MICROS_PER_SECOND) / ((long) this.format.sampleRate)));
    }
}
