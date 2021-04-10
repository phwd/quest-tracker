package com.oculus.android.exoplayer2.extractor.ogg;

import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.SeekPoint;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.video.metadata.CameraMotionDecoder;
import java.io.EOFException;
import java.io.IOException;

/* access modifiers changed from: package-private */
public final class DefaultOggSeeker implements OggSeeker {
    private static final int DEFAULT_OFFSET = 30000;
    public static final int MATCH_BYTE_RANGE = 100000;
    public static final int MATCH_RANGE = 72000;
    private static final int STATE_IDLE = 3;
    private static final int STATE_READ_LAST_PAGE = 1;
    private static final int STATE_SEEK = 2;
    private static final int STATE_SEEK_TO_END = 0;
    private long end;
    private long endGranule;
    private final long endPosition;
    private final OggPageHeader pageHeader = new OggPageHeader();
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private final long startPosition;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    public DefaultOggSeeker(long j, long j2, StreamReader streamReader2, int i, long j3) {
        Assertions.checkArgument(j >= 0 && j2 > j);
        this.streamReader = streamReader2;
        this.startPosition = j;
        this.endPosition = j2;
        if (((long) i) == j2 - j) {
            this.totalGranules = j3;
            this.state = 3;
            return;
        }
        this.state = 0;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ogg.OggSeeker
    public long read(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = this.state;
        if (i == 0) {
            this.positionBeforeSeekToEnd = extractorInput.getPosition();
            this.state = 1;
            long j = this.endPosition - 65307;
            if (j > this.positionBeforeSeekToEnd) {
                return j;
            }
        } else if (i != 1) {
            if (i == 2) {
                long j2 = this.targetGranule;
                long j3 = 0;
                if (j2 != 0) {
                    long nextSeekPosition = getNextSeekPosition(j2, extractorInput);
                    if (nextSeekPosition >= 0) {
                        return nextSeekPosition;
                    }
                    j3 = skipToPageOfGranule(extractorInput, this.targetGranule, -(nextSeekPosition + 2));
                }
                this.state = 3;
                return -(j3 + 2);
            } else if (i == 3) {
                return -1;
            } else {
                throw new IllegalStateException();
            }
        }
        this.totalGranules = readGranuleOfLastPage(extractorInput);
        this.state = 3;
        return this.positionBeforeSeekToEnd;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ogg.OggSeeker
    public long startSeek(long j) {
        int i = this.state;
        Assertions.checkArgument(i == 3 || i == 2);
        long j2 = 0;
        if (j != 0) {
            j2 = this.streamReader.convertTimeToGranule(j);
        }
        this.targetGranule = j2;
        this.state = 2;
        resetSeeking();
        return this.targetGranule;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ogg.OggSeeker
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) {
            return new OggSeekMap();
        }
        return null;
    }

    public void resetSeeking() {
        this.start = this.startPosition;
        this.end = this.endPosition;
        this.startGranule = 0;
        this.endGranule = this.totalGranules;
    }

    public long getNextSeekPosition(long j, ExtractorInput extractorInput) throws IOException, InterruptedException {
        long j2 = 2;
        if (this.start == this.end) {
            return -(this.startGranule + 2);
        }
        long position = extractorInput.getPosition();
        if (!skipToNextPage(extractorInput, this.end)) {
            long j3 = this.start;
            if (j3 != position) {
                return j3;
            }
            throw new IOException("No ogg page can be found.");
        }
        this.pageHeader.populate(extractorInput, false);
        extractorInput.resetPeekPosition();
        long j4 = j - this.pageHeader.granulePosition;
        int i = this.pageHeader.headerSize + this.pageHeader.bodySize;
        int i2 = (j4 > 0 ? 1 : (j4 == 0 ? 0 : -1));
        if (i2 < 0 || j4 > 72000) {
            if (i2 < 0) {
                this.end = position;
                this.endGranule = this.pageHeader.granulePosition;
            } else {
                long j5 = (long) i;
                this.start = extractorInput.getPosition() + j5;
                this.startGranule = this.pageHeader.granulePosition;
                if ((this.end - this.start) + j5 < CameraMotionDecoder.ADVANCED_RENDERING_OFFSET_US) {
                    extractorInput.skipFully(i);
                    return -(this.startGranule + 2);
                }
            }
            long j6 = this.end;
            long j7 = this.start;
            if (j6 - j7 < CameraMotionDecoder.ADVANCED_RENDERING_OFFSET_US) {
                this.end = j7;
                return j7;
            }
            long j8 = (long) i;
            if (i2 > 0) {
                j2 = 1;
            }
            long position2 = extractorInput.getPosition();
            long j9 = this.end;
            long j10 = this.start;
            return Math.min(Math.max((position2 - (j8 * j2)) + ((j4 * (j9 - j10)) / (this.endGranule - this.startGranule)), j10), this.end - 1);
        }
        extractorInput.skipFully(i);
        return -(this.pageHeader.granulePosition + 2);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long getEstimatedPosition(long j, long j2, long j3) {
        long j4 = this.endPosition;
        long j5 = this.startPosition;
        long j6 = j + (((j2 * (j4 - j5)) / this.totalGranules) - j3);
        if (j6 < j5) {
            j6 = j5;
        }
        long j7 = this.endPosition;
        return j6 >= j7 ? j7 - 1 : j6;
    }

    /* access modifiers changed from: private */
    public class OggSeekMap implements SeekMap {
        @Override // com.oculus.android.exoplayer2.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }

        private OggSeekMap() {
        }

        @Override // com.oculus.android.exoplayer2.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long j) {
            if (j == 0) {
                return new SeekMap.SeekPoints(new SeekPoint(0, DefaultOggSeeker.this.startPosition));
            }
            long convertTimeToGranule = DefaultOggSeeker.this.streamReader.convertTimeToGranule(j);
            DefaultOggSeeker defaultOggSeeker = DefaultOggSeeker.this;
            return new SeekMap.SeekPoints(new SeekPoint(j, defaultOggSeeker.getEstimatedPosition(defaultOggSeeker.startPosition, convertTimeToGranule, 30000)));
        }

        @Override // com.oculus.android.exoplayer2.extractor.SeekMap
        public long getDurationUs() {
            return DefaultOggSeeker.this.streamReader.convertGranuleToTime(DefaultOggSeeker.this.totalGranules);
        }
    }

    /* access modifiers changed from: package-private */
    public void skipToNextPage(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (!skipToNextPage(extractorInput, this.endPosition)) {
            throw new EOFException();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean skipToNextPage(ExtractorInput extractorInput, long j) throws IOException, InterruptedException {
        int i;
        long min = Math.min(j + 3, this.endPosition);
        byte[] bArr = new byte[2048];
        int length = bArr.length;
        while (true) {
            int i2 = 0;
            if (extractorInput.getPosition() + ((long) length) <= min || (length = (int) (min - extractorInput.getPosition())) >= 4) {
                extractorInput.peekFully(bArr, 0, length, false);
                while (true) {
                    i = length - 3;
                    if (i2 >= i) {
                        break;
                    } else if (bArr[i2] == 79 && bArr[i2 + 1] == 103 && bArr[i2 + 2] == 103 && bArr[i2 + 3] == 83) {
                        extractorInput.skipFully(i2);
                        return true;
                    } else {
                        i2++;
                    }
                }
            } else {
                return false;
            }
            extractorInput.skipFully(i);
        }
    }

    /* access modifiers changed from: package-private */
    public long readGranuleOfLastPage(ExtractorInput extractorInput) throws IOException, InterruptedException {
        skipToNextPage(extractorInput);
        this.pageHeader.reset();
        while ((this.pageHeader.type & 4) != 4 && extractorInput.getPosition() < this.endPosition) {
            this.pageHeader.populate(extractorInput, false);
            extractorInput.skipFully(this.pageHeader.headerSize + this.pageHeader.bodySize);
        }
        return this.pageHeader.granulePosition;
    }

    /* access modifiers changed from: package-private */
    public long skipToPageOfGranule(ExtractorInput extractorInput, long j, long j2) throws IOException, InterruptedException {
        this.pageHeader.populate(extractorInput, false);
        while (this.pageHeader.granulePosition < j) {
            extractorInput.skipFully(this.pageHeader.headerSize + this.pageHeader.bodySize);
            j2 = this.pageHeader.granulePosition;
            this.pageHeader.populate(extractorInput, false);
        }
        extractorInput.resetPeekPosition();
        return j2;
    }
}
