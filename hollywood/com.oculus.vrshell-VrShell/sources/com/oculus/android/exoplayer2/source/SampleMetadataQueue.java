package com.oculus.android.exoplayer2.source;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Util;

/* access modifiers changed from: package-private */
public final class SampleMetadataQueue {
    private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
    private int absoluteFirstIndex;
    private int capacity = 1000;
    private TrackOutput.CryptoData[] cryptoDatas;
    private int[] flags;
    private Format[] formats;
    private long largestDiscardedTimestampUs;
    private long largestQueuedTimestampUs;
    private int length;
    private long[] offsets;
    private int readPosition;
    private int relativeFirstIndex;
    private int[] sizes;
    private int[] sourceIds;
    private long[] timesUs;
    private Format upstreamFormat;
    private boolean upstreamFormatRequired;
    private boolean upstreamKeyframeRequired;
    private int upstreamSourceId;

    public static final class SampleExtrasHolder {
        public TrackOutput.CryptoData cryptoData;
        public long offset;
        public int size;
    }

    public SampleMetadataQueue() {
        int i = this.capacity;
        this.sourceIds = new int[i];
        this.offsets = new long[i];
        this.timesUs = new long[i];
        this.flags = new int[i];
        this.sizes = new int[i];
        this.cryptoDatas = new TrackOutput.CryptoData[i];
        this.formats = new Format[i];
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        this.upstreamFormatRequired = true;
        this.upstreamKeyframeRequired = true;
    }

    public void reset(boolean z) {
        this.length = 0;
        this.absoluteFirstIndex = 0;
        this.relativeFirstIndex = 0;
        this.readPosition = 0;
        this.upstreamKeyframeRequired = true;
        this.largestDiscardedTimestampUs = Long.MIN_VALUE;
        this.largestQueuedTimestampUs = Long.MIN_VALUE;
        if (z) {
            this.upstreamFormat = null;
            this.upstreamFormatRequired = true;
        }
    }

    public int getWriteIndex() {
        return this.absoluteFirstIndex + this.length;
    }

    public long discardUpstreamSamples(int i) {
        int writeIndex = getWriteIndex() - i;
        Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.length - this.readPosition);
        this.length -= writeIndex;
        this.largestQueuedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.length));
        int i2 = this.length;
        if (i2 == 0) {
            return 0;
        }
        int relativeIndex = getRelativeIndex(i2 - 1);
        return this.offsets[relativeIndex] + ((long) this.sizes[relativeIndex]);
    }

    public void sourceId(int i) {
        this.upstreamSourceId = i;
    }

    public int getFirstIndex() {
        return this.absoluteFirstIndex;
    }

    public int getReadIndex() {
        return this.absoluteFirstIndex + this.readPosition;
    }

    public int peekSourceId() {
        return hasNextSample() ? this.sourceIds[getRelativeIndex(this.readPosition)] : this.upstreamSourceId;
    }

    public synchronized boolean hasNextSample() {
        return this.readPosition != this.length;
    }

    public synchronized Format getUpstreamFormat() {
        return this.upstreamFormatRequired ? null : this.upstreamFormat;
    }

    public synchronized long getLargestQueuedTimestampUs() {
        return this.largestQueuedTimestampUs;
    }

    public synchronized long getFirstTimestampUs() {
        return this.length == 0 ? Long.MIN_VALUE : this.timesUs[this.relativeFirstIndex];
    }

    public synchronized void rewind() {
        this.readPosition = 0;
    }

    public synchronized int read(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z, boolean z2, Format format, SampleExtrasHolder sampleExtrasHolder) {
        if (hasNextSample()) {
            int relativeIndex = getRelativeIndex(this.readPosition);
            if (z || this.formats[relativeIndex] != format) {
                formatHolder.format = this.formats[relativeIndex];
                return -5;
            } else if (decoderInputBuffer.isFlagsOnly()) {
                return -3;
            } else {
                decoderInputBuffer.timeUs = this.timesUs[relativeIndex];
                decoderInputBuffer.setFlags(this.flags[relativeIndex]);
                sampleExtrasHolder.size = this.sizes[relativeIndex];
                sampleExtrasHolder.offset = this.offsets[relativeIndex];
                sampleExtrasHolder.cryptoData = this.cryptoDatas[relativeIndex];
                this.readPosition++;
                return -4;
            }
        } else if (z2) {
            decoderInputBuffer.setFlags(4);
            return -4;
        } else if (this.upstreamFormat == null || (!z && this.upstreamFormat == format)) {
            return -3;
        } else {
            formatHolder.format = this.upstreamFormat;
            return -5;
        }
    }

    public synchronized int advanceTo(long j, boolean z, boolean z2) {
        int relativeIndex = getRelativeIndex(this.readPosition);
        if (hasNextSample() && j >= this.timesUs[relativeIndex]) {
            if (j <= this.largestQueuedTimestampUs || z2) {
                int findSampleBefore = findSampleBefore(relativeIndex, this.length - this.readPosition, j, z);
                if (findSampleBefore == -1) {
                    return -1;
                }
                this.readPosition += findSampleBefore;
                return findSampleBefore;
            }
        }
        return -1;
    }

    public synchronized int advanceToEnd() {
        int i;
        i = this.length - this.readPosition;
        this.readPosition = this.length;
        return i;
    }

    public synchronized boolean setReadPosition(int i) {
        if (this.absoluteFirstIndex > i || i > this.absoluteFirstIndex + this.length) {
            return false;
        }
        this.readPosition = i - this.absoluteFirstIndex;
        return true;
    }

    public synchronized long discardTo(long j, boolean z, boolean z2) {
        if (this.length != 0) {
            if (j >= this.timesUs[this.relativeFirstIndex]) {
                int findSampleBefore = findSampleBefore(this.relativeFirstIndex, (!z2 || this.readPosition == this.length) ? this.length : this.readPosition + 1, j, z);
                if (findSampleBefore == -1) {
                    return -1;
                }
                return discardSamples(findSampleBefore);
            }
        }
        return -1;
    }

    public synchronized long discardToRead() {
        if (this.readPosition == 0) {
            return -1;
        }
        return discardSamples(this.readPosition);
    }

    public synchronized long discardToEnd() {
        if (this.length == 0) {
            return -1;
        }
        return discardSamples(this.length);
    }

    public synchronized boolean format(Format format) {
        if (format == null) {
            this.upstreamFormatRequired = true;
            return false;
        }
        this.upstreamFormatRequired = false;
        if (Util.areEqual(format, this.upstreamFormat)) {
            return false;
        }
        this.upstreamFormat = format;
        return true;
    }

    public synchronized void commitSample(long j, int i, long j2, int i2, TrackOutput.CryptoData cryptoData) {
        if (this.upstreamKeyframeRequired) {
            if ((i & 1) != 0) {
                this.upstreamKeyframeRequired = false;
            } else {
                return;
            }
        }
        Assertions.checkState(!this.upstreamFormatRequired);
        commitSampleTimestamp(j);
        int relativeIndex = getRelativeIndex(this.length);
        this.timesUs[relativeIndex] = j;
        this.offsets[relativeIndex] = j2;
        this.sizes[relativeIndex] = i2;
        this.flags[relativeIndex] = i;
        this.cryptoDatas[relativeIndex] = cryptoData;
        this.formats[relativeIndex] = this.upstreamFormat;
        this.sourceIds[relativeIndex] = this.upstreamSourceId;
        this.length++;
        if (this.length == this.capacity) {
            int i3 = this.capacity + 1000;
            int[] iArr = new int[i3];
            long[] jArr = new long[i3];
            long[] jArr2 = new long[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            TrackOutput.CryptoData[] cryptoDataArr = new TrackOutput.CryptoData[i3];
            Format[] formatArr = new Format[i3];
            int i4 = this.capacity - this.relativeFirstIndex;
            System.arraycopy(this.offsets, this.relativeFirstIndex, jArr, 0, i4);
            System.arraycopy(this.timesUs, this.relativeFirstIndex, jArr2, 0, i4);
            System.arraycopy(this.flags, this.relativeFirstIndex, iArr2, 0, i4);
            System.arraycopy(this.sizes, this.relativeFirstIndex, iArr3, 0, i4);
            System.arraycopy(this.cryptoDatas, this.relativeFirstIndex, cryptoDataArr, 0, i4);
            System.arraycopy(this.formats, this.relativeFirstIndex, formatArr, 0, i4);
            System.arraycopy(this.sourceIds, this.relativeFirstIndex, iArr, 0, i4);
            int i5 = this.relativeFirstIndex;
            System.arraycopy(this.offsets, 0, jArr, i4, i5);
            System.arraycopy(this.timesUs, 0, jArr2, i4, i5);
            System.arraycopy(this.flags, 0, iArr2, i4, i5);
            System.arraycopy(this.sizes, 0, iArr3, i4, i5);
            System.arraycopy(this.cryptoDatas, 0, cryptoDataArr, i4, i5);
            System.arraycopy(this.formats, 0, formatArr, i4, i5);
            System.arraycopy(this.sourceIds, 0, iArr, i4, i5);
            this.offsets = jArr;
            this.timesUs = jArr2;
            this.flags = iArr2;
            this.sizes = iArr3;
            this.cryptoDatas = cryptoDataArr;
            this.formats = formatArr;
            this.sourceIds = iArr;
            this.relativeFirstIndex = 0;
            this.length = this.capacity;
            this.capacity = i3;
        }
    }

    public synchronized void commitSampleTimestamp(long j) {
        this.largestQueuedTimestampUs = Math.max(this.largestQueuedTimestampUs, j);
    }

    public synchronized boolean attemptSplice(long j) {
        boolean z = false;
        if (this.length == 0) {
            if (j > this.largestDiscardedTimestampUs) {
                z = true;
            }
            return z;
        } else if (Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(this.readPosition)) >= j) {
            return false;
        } else {
            int i = this.length;
            int relativeIndex = getRelativeIndex(this.length - 1);
            while (i > this.readPosition && this.timesUs[relativeIndex] >= j) {
                i--;
                relativeIndex--;
                if (relativeIndex == -1) {
                    relativeIndex = this.capacity - 1;
                }
            }
            discardUpstreamSamples(this.absoluteFirstIndex + i);
            return true;
        }
    }

    private int findSampleBefore(int i, int i2, long j, boolean z) {
        int i3 = -1;
        int i4 = i;
        for (int i5 = 0; i5 < i2 && this.timesUs[i4] <= j; i5++) {
            if (!z || (this.flags[i4] & 1) != 0) {
                i3 = i5;
            }
            i4++;
            if (i4 == this.capacity) {
                i4 = 0;
            }
        }
        return i3;
    }

    private long discardSamples(int i) {
        this.largestDiscardedTimestampUs = Math.max(this.largestDiscardedTimestampUs, getLargestTimestamp(i));
        this.length -= i;
        this.absoluteFirstIndex += i;
        this.relativeFirstIndex += i;
        int i2 = this.relativeFirstIndex;
        int i3 = this.capacity;
        if (i2 >= i3) {
            this.relativeFirstIndex = i2 - i3;
        }
        this.readPosition -= i;
        if (this.readPosition < 0) {
            this.readPosition = 0;
        }
        if (this.length != 0) {
            return this.offsets[this.relativeFirstIndex];
        }
        int i4 = this.relativeFirstIndex;
        if (i4 == 0) {
            i4 = this.capacity;
        }
        int i5 = i4 - 1;
        return this.offsets[i5] + ((long) this.sizes[i5]);
    }

    private long getLargestTimestamp(int i) {
        long j = Long.MIN_VALUE;
        if (i == 0) {
            return Long.MIN_VALUE;
        }
        int relativeIndex = getRelativeIndex(i - 1);
        for (int i2 = 0; i2 < i; i2++) {
            j = Math.max(j, this.timesUs[relativeIndex]);
            if ((this.flags[relativeIndex] & 1) != 0) {
                break;
            }
            relativeIndex--;
            if (relativeIndex == -1) {
                relativeIndex = this.capacity - 1;
            }
        }
        return j;
    }

    private int getRelativeIndex(int i) {
        int i2 = this.relativeFirstIndex + i;
        int i3 = this.capacity;
        return i2 < i3 ? i2 : i2 - i3;
    }
}
