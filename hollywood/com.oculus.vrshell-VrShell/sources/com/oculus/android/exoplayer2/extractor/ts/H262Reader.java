package com.oculus.android.exoplayer2.extractor.ts;

import android.util.Pair;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.NalUnitUtil;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.util.Arrays;
import java.util.Collections;

public final class H262Reader implements ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private final CsdBuffer csdBuffer = new CsdBuffer(128);
    private String formatId;
    private long frameDurationUs;
    private boolean hasOutputFormat;
    private TrackOutput output;
    private long pesTimeUs;
    private final boolean[] prefixFlags = new boolean[4];
    private boolean sampleHasPicture;
    private boolean sampleIsKeyframe;
    private long samplePosition;
    private long sampleTimeUs;
    private boolean startedFirstSample;
    private long totalBytesWritten;

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.totalBytesWritten = 0;
        this.startedFirstSample = false;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void createTracks(ExtractorOutput extractorOutput, TsPayloadReader.TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.formatId = trackIdGenerator.getFormatId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 2);
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        this.pesTimeUs = j;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        boolean z;
        boolean z2;
        int position = parsableByteArray.getPosition();
        int limit = parsableByteArray.limit();
        byte[] bArr = parsableByteArray.data;
        this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
        this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
        while (true) {
            int findNalUnit = NalUnitUtil.findNalUnit(bArr, position, limit, this.prefixFlags);
            if (findNalUnit == limit) {
                break;
            }
            int i = findNalUnit + 3;
            int i2 = parsableByteArray.data[i] & 255;
            if (!this.hasOutputFormat) {
                int i3 = findNalUnit - position;
                if (i3 > 0) {
                    this.csdBuffer.onData(bArr, position, findNalUnit);
                }
                if (this.csdBuffer.onStartCode(i2, i3 < 0 ? -i3 : 0)) {
                    Pair<Format, Long> parseCsdBuffer = parseCsdBuffer(this.csdBuffer, this.formatId);
                    this.output.format((Format) parseCsdBuffer.first);
                    this.frameDurationUs = ((Long) parseCsdBuffer.second).longValue();
                    this.hasOutputFormat = true;
                }
            }
            if (i2 == 0 || i2 == START_SEQUENCE_HEADER) {
                int i4 = limit - findNalUnit;
                if (this.startedFirstSample && this.sampleHasPicture && this.hasOutputFormat) {
                    this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.samplePosition)) - i4, i4, null);
                }
                if (!this.startedFirstSample || this.sampleHasPicture) {
                    this.samplePosition = this.totalBytesWritten - ((long) i4);
                    long j = this.pesTimeUs;
                    if (j == C.TIME_UNSET) {
                        j = this.startedFirstSample ? this.sampleTimeUs + this.frameDurationUs : 0;
                    }
                    this.sampleTimeUs = j;
                    z2 = false;
                    this.sampleIsKeyframe = false;
                    this.pesTimeUs = C.TIME_UNSET;
                    z = true;
                    this.startedFirstSample = true;
                } else {
                    z2 = false;
                    z = true;
                }
                if (i2 == 0) {
                    z2 = z;
                }
                this.sampleHasPicture = z2;
            } else if (i2 == START_GROUP) {
                this.sampleIsKeyframe = true;
            }
            position = i;
        }
        if (!this.hasOutputFormat) {
            this.csdBuffer.onData(bArr, position, limit);
        }
    }

    private static Pair<Format, Long> parseCsdBuffer(CsdBuffer csdBuffer2, String str) {
        int i;
        float f;
        float f2;
        byte[] copyOf = Arrays.copyOf(csdBuffer2.data, csdBuffer2.length);
        int i2 = copyOf[5] & 255;
        int i3 = ((copyOf[4] & 255) << 4) | (i2 >> 4);
        int i4 = ((i2 & 15) << 8) | (copyOf[6] & 255);
        int i5 = (copyOf[7] & 240) >> 4;
        if (i5 != 2) {
            if (i5 == 3) {
                f = (float) (i4 * 16);
                i = i3 * 9;
            } else if (i5 != 4) {
                f2 = 1.0f;
            } else {
                f = (float) (i4 * 121);
                i = i3 * 100;
            }
            f2 = f / ((float) i);
        } else {
            f = (float) (i4 * 4);
            i = i3 * 3;
            f2 = f / ((float) i);
        }
        Format createVideoSampleFormat = Format.createVideoSampleFormat(str, MimeTypes.VIDEO_MPEG2, null, -1, -1, i3, i4, -1.0f, Collections.singletonList(copyOf), -1, f2, null);
        long j = 0;
        int i6 = (copyOf[7] & 15) - 1;
        if (i6 >= 0) {
            double[] dArr = FRAME_RATE_VALUES;
            if (i6 < dArr.length) {
                double d = dArr[i6];
                int i7 = csdBuffer2.sequenceExtensionPosition + 9;
                int i8 = (copyOf[i7] & 96) >> 5;
                int i9 = copyOf[i7] & 31;
                if (i8 != i9) {
                    d *= (((double) i8) + 1.0d) / ((double) (i9 + 1));
                }
                j = (long) (1000000.0d / d);
            }
        }
        return Pair.create(createVideoSampleFormat, Long.valueOf(j));
    }

    /* access modifiers changed from: private */
    public static final class CsdBuffer {
        private static final byte[] START_CODE = {0, 0, 1};
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                this.length -= i2;
                if (this.sequenceExtensionPosition == 0 && i == H262Reader.START_EXTENSION) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.isFilling = false;
                    return true;
                }
            } else if (i == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            byte[] bArr = START_CODE;
            onData(bArr, 0, bArr.length);
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                byte[] bArr2 = this.data;
                int length2 = bArr2.length;
                int i4 = this.length;
                if (length2 < i4 + i3) {
                    this.data = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }
    }
}
