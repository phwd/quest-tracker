package com.oculus.android.exoplayer2.extractor.mp3;

import android.util.Log;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.extractor.MpegAudioHeader;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.SeekPoint;
import com.oculus.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;

/* access modifiers changed from: package-private */
public final class XingSeeker implements Mp3Extractor.Seeker {
    private static final String TAG = "XingSeeker";
    private final long dataSize;
    private final long dataStartPosition;
    private final long durationUs;
    private final long[] tableOfContents;
    private final int xingFrameSize;

    public static XingSeeker create(long j, long j2, MpegAudioHeader mpegAudioHeader, ParsableByteArray parsableByteArray) {
        int readUnsignedIntToInt;
        int i = mpegAudioHeader.samplesPerFrame;
        int i2 = mpegAudioHeader.sampleRate;
        int readInt = parsableByteArray.readInt();
        if ((readInt & 1) != 1 || (readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt()) == 0) {
            return null;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp((long) readUnsignedIntToInt, ((long) i) * C.MICROS_PER_SECOND, (long) i2);
        if ((readInt & 6) != 6) {
            return new XingSeeker(j2, mpegAudioHeader.frameSize, scaleLargeTimestamp);
        }
        long readUnsignedIntToInt2 = (long) parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[100];
        for (int i3 = 0; i3 < 100; i3++) {
            jArr[i3] = (long) parsableByteArray.readUnsignedByte();
        }
        if (j != -1) {
            long j3 = j2 + readUnsignedIntToInt2;
            if (j != j3) {
                Log.w(TAG, "XING data size mismatch: " + j + ", " + j3);
            }
        }
        return new XingSeeker(j2, mpegAudioHeader.frameSize, scaleLargeTimestamp, readUnsignedIntToInt2, jArr);
    }

    private XingSeeker(long j, int i, long j2) {
        this(j, i, j2, -1, null);
    }

    private XingSeeker(long j, int i, long j2, long j3, long[] jArr) {
        this.dataStartPosition = j;
        this.xingFrameSize = i;
        this.durationUs = j2;
        this.dataSize = j3;
        this.tableOfContents = jArr;
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public boolean isSeekable() {
        return this.tableOfContents != null;
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public SeekMap.SeekPoints getSeekPoints(long j) {
        double d;
        if (!isSeekable()) {
            return new SeekMap.SeekPoints(new SeekPoint(0, this.dataStartPosition + ((long) this.xingFrameSize)));
        }
        long constrainValue = Util.constrainValue(j, 0, this.durationUs);
        double d2 = (((double) constrainValue) * 100.0d) / ((double) this.durationUs);
        double d3 = 0.0d;
        if (d2 > 0.0d) {
            if (d2 >= 100.0d) {
                d3 = 256.0d;
            } else {
                int i = (int) d2;
                long[] jArr = this.tableOfContents;
                double d4 = (double) jArr[i];
                if (i == 99) {
                    d = 256.0d;
                } else {
                    d = (double) jArr[i + 1];
                }
                d3 = d4 + ((d2 - ((double) i)) * (d - d4));
            }
        }
        return new SeekMap.SeekPoints(new SeekPoint(constrainValue, this.dataStartPosition + Util.constrainValue(Math.round((d3 / 256.0d) * ((double) this.dataSize)), (long) this.xingFrameSize, this.dataSize - 1)));
    }

    @Override // com.oculus.android.exoplayer2.extractor.mp3.Mp3Extractor.Seeker
    public long getTimeUs(long j) {
        long j2;
        long j3 = j - this.dataStartPosition;
        if (!isSeekable() || j3 <= ((long) this.xingFrameSize)) {
            return 0;
        }
        double d = (((double) j3) * 256.0d) / ((double) this.dataSize);
        int binarySearchFloor = Util.binarySearchFloor(this.tableOfContents, (long) d, true, true);
        long timeUsForTableIndex = getTimeUsForTableIndex(binarySearchFloor);
        long j4 = this.tableOfContents[binarySearchFloor];
        int i = binarySearchFloor + 1;
        long timeUsForTableIndex2 = getTimeUsForTableIndex(i);
        if (binarySearchFloor == 99) {
            j2 = 256;
        } else {
            j2 = this.tableOfContents[i];
        }
        return timeUsForTableIndex + Math.round((j4 == j2 ? 0.0d : (d - ((double) j4)) / ((double) (j2 - j4))) * ((double) (timeUsForTableIndex2 - timeUsForTableIndex)));
    }

    @Override // com.oculus.android.exoplayer2.extractor.SeekMap
    public long getDurationUs() {
        return this.durationUs;
    }

    private long getTimeUsForTableIndex(int i) {
        return (this.durationUs * ((long) i)) / 100;
    }
}
