package com.oculus.android.exoplayer2.source.chunk;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.SampleQueue;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.SequenceableLoader;
import com.oculus.android.exoplayer2.source.chunk.ChunkSource;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.Loader;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChunkSampleStream<T extends ChunkSource> implements SampleStream, SequenceableLoader, Loader.Callback<Chunk>, Loader.ReleaseCallback {
    private static final String TAG = "ChunkSampleStream";
    private final SequenceableLoader.Callback<ChunkSampleStream<T>> callback;
    private final T chunkSource;
    long decodeOnlyUntilPositionUs;
    private final SampleQueue[] embeddedSampleQueues;
    private final Format[] embeddedTrackFormats;
    private final int[] embeddedTrackTypes;
    private final boolean[] embeddedTracksSelected;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private long lastSeekPositionUs;
    private final Loader loader = new Loader("Loader:ChunkSampleStream");
    boolean loadingFinished;
    private final BaseMediaChunkOutput mediaChunkOutput;
    private final ArrayList<BaseMediaChunk> mediaChunks = new ArrayList<>();
    private final int minLoadableRetryCount;
    private final ChunkHolder nextChunkHolder = new ChunkHolder();
    private long pendingResetPositionUs;
    private Format primaryDownstreamTrackFormat;
    private final SampleQueue primarySampleQueue;
    public final int primaryTrackType;
    private final List<BaseMediaChunk> readOnlyMediaChunks = Collections.unmodifiableList(this.mediaChunks);
    @Nullable
    private ReleaseCallback<T> releaseCallback;

    public interface ReleaseCallback<T extends ChunkSource> {
        void onSampleStreamReleased(ChunkSampleStream<T> chunkSampleStream);
    }

    public ChunkSampleStream(int i, int[] iArr, Format[] formatArr, T t, SequenceableLoader.Callback<ChunkSampleStream<T>> callback2, Allocator allocator, long j, int i2, MediaSourceEventListener.EventDispatcher eventDispatcher2) {
        int i3;
        this.primaryTrackType = i;
        this.embeddedTrackTypes = iArr;
        this.embeddedTrackFormats = formatArr;
        this.chunkSource = t;
        this.callback = callback2;
        this.eventDispatcher = eventDispatcher2;
        this.minLoadableRetryCount = i2;
        int i4 = 0;
        if (iArr == null) {
            i3 = 0;
        } else {
            i3 = iArr.length;
        }
        this.embeddedSampleQueues = new SampleQueue[i3];
        this.embeddedTracksSelected = new boolean[i3];
        int i5 = i3 + 1;
        int[] iArr2 = new int[i5];
        SampleQueue[] sampleQueueArr = new SampleQueue[i5];
        this.primarySampleQueue = new SampleQueue(allocator);
        iArr2[0] = i;
        sampleQueueArr[0] = this.primarySampleQueue;
        while (i4 < i3) {
            SampleQueue sampleQueue = new SampleQueue(allocator);
            this.embeddedSampleQueues[i4] = sampleQueue;
            int i6 = i4 + 1;
            sampleQueueArr[i6] = sampleQueue;
            iArr2[i6] = iArr[i4];
            i4 = i6;
        }
        this.mediaChunkOutput = new BaseMediaChunkOutput(iArr2, sampleQueueArr);
        this.pendingResetPositionUs = j;
        this.lastSeekPositionUs = j;
    }

    public void discardBuffer(long j, boolean z) {
        int firstIndex = this.primarySampleQueue.getFirstIndex();
        this.primarySampleQueue.discardTo(j, z, true);
        int firstIndex2 = this.primarySampleQueue.getFirstIndex();
        if (firstIndex2 > firstIndex) {
            long firstTimestampUs = this.primarySampleQueue.getFirstTimestampUs();
            int i = 0;
            while (true) {
                SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
                if (i < sampleQueueArr.length) {
                    sampleQueueArr[i].discardTo(firstTimestampUs, z, this.embeddedTracksSelected[i]);
                    i++;
                } else {
                    discardDownstreamMediaChunks(firstIndex2);
                    return;
                }
            }
        }
    }

    public ChunkSampleStream<T>.EmbeddedSampleStream selectEmbeddedTrack(long j, int i) {
        for (int i2 = 0; i2 < this.embeddedSampleQueues.length; i2++) {
            if (this.embeddedTrackTypes[i2] == i) {
                Assertions.checkState(!this.embeddedTracksSelected[i2]);
                this.embeddedTracksSelected[i2] = true;
                this.embeddedSampleQueues[i2].rewind();
                this.embeddedSampleQueues[i2].advanceTo(j, true, true);
                return new EmbeddedSampleStream(this, this.embeddedSampleQueues[i2], i2);
            }
        }
        throw new IllegalStateException();
    }

    public T getChunkSource() {
        return this.chunkSource;
    }

    @Override // com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        long j = this.lastSeekPositionUs;
        BaseMediaChunk lastMediaChunk = getLastMediaChunk();
        if (!lastMediaChunk.isLoadCompleted()) {
            if (this.mediaChunks.size() > 1) {
                ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
                lastMediaChunk = arrayList.get(arrayList.size() - 2);
            } else {
                lastMediaChunk = null;
            }
        }
        if (lastMediaChunk != null) {
            j = Math.max(j, lastMediaChunk.endTimeUs);
        }
        return Math.max(j, this.primarySampleQueue.getLargestQueuedTimestampUs());
    }

    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.chunkSource.getAdjustedSeekPositionUs(j, seekParameters);
    }

    public void seekToUs(long j) {
        boolean z;
        this.lastSeekPositionUs = j;
        this.primarySampleQueue.rewind();
        if (isPendingReset()) {
            z = false;
        } else {
            BaseMediaChunk baseMediaChunk = null;
            int i = 0;
            while (true) {
                if (i >= this.mediaChunks.size()) {
                    break;
                }
                BaseMediaChunk baseMediaChunk2 = this.mediaChunks.get(i);
                int i2 = (baseMediaChunk2.startTimeUs > j ? 1 : (baseMediaChunk2.startTimeUs == j ? 0 : -1));
                if (i2 == 0) {
                    baseMediaChunk = baseMediaChunk2;
                    break;
                } else if (i2 > 0) {
                    break;
                } else {
                    i++;
                }
            }
            if (baseMediaChunk != null) {
                z = this.primarySampleQueue.setReadPosition(baseMediaChunk.getFirstSampleIndex(0));
                this.decodeOnlyUntilPositionUs = Long.MIN_VALUE;
            } else {
                z = this.primarySampleQueue.advanceTo(j, true, (j > getNextLoadPositionUs() ? 1 : (j == getNextLoadPositionUs() ? 0 : -1)) < 0) != -1;
                this.decodeOnlyUntilPositionUs = this.lastSeekPositionUs;
            }
        }
        if (z) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            for (SampleQueue sampleQueue : sampleQueueArr) {
                sampleQueue.rewind();
                sampleQueue.advanceTo(j, true, false);
            }
            return;
        }
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        this.mediaChunks.clear();
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
            return;
        }
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue2 : this.embeddedSampleQueues) {
            sampleQueue2.reset();
        }
    }

    public void release() {
        release(null);
    }

    public void release(@Nullable ReleaseCallback<T> releaseCallback2) {
        this.releaseCallback = releaseCallback2;
        this.primarySampleQueue.discardToEnd();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.discardToEnd();
        }
        this.loader.release(this);
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        this.primarySampleQueue.reset();
        for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
            sampleQueue.reset();
        }
        ReleaseCallback<T> releaseCallback2 = this.releaseCallback;
        if (releaseCallback2 != null) {
            releaseCallback2.onSampleStreamReleased(this);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public boolean isReady() {
        return this.loadingFinished || (!isPendingReset() && this.primarySampleQueue.hasNextSample());
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError();
        if (!this.loader.isLoading()) {
            this.chunkSource.maybeThrowError();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (isPendingReset()) {
            return -3;
        }
        int read = this.primarySampleQueue.read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.decodeOnlyUntilPositionUs);
        if (read == -4) {
            maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), 1);
        }
        return read;
    }

    @Override // com.oculus.android.exoplayer2.source.SampleStream
    public int skipData(long j) {
        int i = 0;
        if (isPendingReset()) {
            return 0;
        }
        if (!this.loadingFinished || j <= this.primarySampleQueue.getLargestQueuedTimestampUs()) {
            int advanceTo = this.primarySampleQueue.advanceTo(j, true, true);
            if (advanceTo != -1) {
                i = advanceTo;
            }
        } else {
            i = this.primarySampleQueue.advanceToEnd();
        }
        if (i > 0) {
            maybeNotifyPrimaryTrackFormatChanged(this.primarySampleQueue.getReadIndex(), i);
        }
        return i;
    }

    public void onLoadCompleted(Chunk chunk, long j, long j2) {
        this.chunkSource.onChunkLoadCompleted(chunk);
        this.eventDispatcher.loadCompleted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(Chunk chunk, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, j, j2, chunk.bytesLoaded());
        if (!z) {
            this.primarySampleQueue.reset();
            for (SampleQueue sampleQueue : this.embeddedSampleQueues) {
                sampleQueue.reset();
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int onLoadError(com.oculus.android.exoplayer2.source.chunk.Chunk r24, long r25, long r27, java.io.IOException r29) {
        /*
        // Method dump skipped, instructions count: 132
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.source.chunk.ChunkSampleStream.onLoadError(com.oculus.android.exoplayer2.source.chunk.Chunk, long, long, java.io.IOException):int");
    }

    @Override // com.oculus.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        long j2;
        BaseMediaChunk baseMediaChunk;
        long j3;
        boolean z = false;
        if (this.loadingFinished || this.loader.isLoading()) {
            return false;
        }
        boolean isPendingReset = isPendingReset();
        if (isPendingReset) {
            baseMediaChunk = null;
            j2 = this.pendingResetPositionUs;
        } else {
            baseMediaChunk = getLastMediaChunk();
            j2 = ((MediaChunk) baseMediaChunk).endTimeUs;
        }
        this.chunkSource.getNextChunk(baseMediaChunk, j, j2, this.nextChunkHolder);
        boolean z2 = this.nextChunkHolder.endOfStream;
        Chunk chunk = this.nextChunkHolder.chunk;
        this.nextChunkHolder.clear();
        if (z2) {
            this.pendingResetPositionUs = C.TIME_UNSET;
            this.loadingFinished = true;
            return true;
        } else if (chunk == null) {
            return false;
        } else {
            if (isMediaChunk(chunk)) {
                BaseMediaChunk baseMediaChunk2 = (BaseMediaChunk) chunk;
                if (isPendingReset) {
                    if (baseMediaChunk2.startTimeUs == this.pendingResetPositionUs) {
                        z = true;
                    }
                    if (z) {
                        j3 = Long.MIN_VALUE;
                    } else {
                        j3 = this.pendingResetPositionUs;
                    }
                    this.decodeOnlyUntilPositionUs = j3;
                    this.pendingResetPositionUs = C.TIME_UNSET;
                }
                baseMediaChunk2.init(this.mediaChunkOutput);
                this.mediaChunks.add(baseMediaChunk2);
            }
            this.eventDispatcher.loadStarted(chunk.dataSpec, chunk.type, this.primaryTrackType, chunk.trackFormat, chunk.trackSelectionReason, chunk.trackSelectionData, chunk.startTimeUs, chunk.endTimeUs, this.loader.startLoading(chunk, this, this.minLoadableRetryCount));
            return true;
        }
    }

    @Override // com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        return getLastMediaChunk().endTimeUs;
    }

    @Override // com.oculus.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        int size;
        int preferredQueueSize;
        if (!this.loader.isLoading() && !isPendingReset() && (size = this.mediaChunks.size()) > (preferredQueueSize = this.chunkSource.getPreferredQueueSize(j, this.readOnlyMediaChunks))) {
            while (true) {
                if (preferredQueueSize >= size) {
                    preferredQueueSize = size;
                    break;
                } else if (!haveReadFromMediaChunk(preferredQueueSize)) {
                    break;
                } else {
                    preferredQueueSize++;
                }
            }
            if (preferredQueueSize != size) {
                long j2 = getLastMediaChunk().endTimeUs;
                BaseMediaChunk discardUpstreamMediaChunksFromIndex = discardUpstreamMediaChunksFromIndex(preferredQueueSize);
                if (this.mediaChunks.isEmpty()) {
                    this.pendingResetPositionUs = this.lastSeekPositionUs;
                }
                this.loadingFinished = false;
                this.eventDispatcher.upstreamDiscarded(this.primaryTrackType, discardUpstreamMediaChunksFromIndex.startTimeUs, j2);
            }
        }
    }

    private boolean isMediaChunk(Chunk chunk) {
        return chunk instanceof BaseMediaChunk;
    }

    private boolean haveReadFromMediaChunk(int i) {
        int readIndex;
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i);
        if (this.primarySampleQueue.getReadIndex() > baseMediaChunk.getFirstSampleIndex(0)) {
            return true;
        }
        int i2 = 0;
        do {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i2 >= sampleQueueArr.length) {
                return false;
            }
            readIndex = sampleQueueArr[i2].getReadIndex();
            i2++;
        } while (readIndex <= baseMediaChunk.getFirstSampleIndex(i2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    private void discardDownstreamMediaChunks(int i) {
        int primaryStreamIndexToMediaChunkIndex = primaryStreamIndexToMediaChunkIndex(i, 0);
        if (primaryStreamIndexToMediaChunkIndex > 0) {
            Util.removeRange(this.mediaChunks, 0, primaryStreamIndexToMediaChunkIndex);
        }
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i, int i2) {
        int i3;
        int primaryStreamIndexToMediaChunkIndex = primaryStreamIndexToMediaChunkIndex(i - i2, 0);
        if (i2 == 1) {
            i3 = primaryStreamIndexToMediaChunkIndex;
        } else {
            i3 = primaryStreamIndexToMediaChunkIndex(i - 1, primaryStreamIndexToMediaChunkIndex);
        }
        while (primaryStreamIndexToMediaChunkIndex <= i3) {
            maybeNotifyPrimaryTrackFormatChanged(primaryStreamIndexToMediaChunkIndex);
            primaryStreamIndexToMediaChunkIndex++;
        }
    }

    private void maybeNotifyPrimaryTrackFormatChanged(int i) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i);
        Format format = baseMediaChunk.trackFormat;
        if (!format.equals(this.primaryDownstreamTrackFormat)) {
            this.eventDispatcher.downstreamFormatChanged(this.primaryTrackType, format, baseMediaChunk.trackSelectionReason, baseMediaChunk.trackSelectionData, baseMediaChunk.startTimeUs);
        }
        this.primaryDownstreamTrackFormat = format;
    }

    private int primaryStreamIndexToMediaChunkIndex(int i, int i2) {
        do {
            i2++;
            if (i2 >= this.mediaChunks.size()) {
                return this.mediaChunks.size() - 1;
            }
        } while (this.mediaChunks.get(i2).getFirstSampleIndex(0) <= i);
        return i2 - 1;
    }

    private BaseMediaChunk getLastMediaChunk() {
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        return arrayList.get(arrayList.size() - 1);
    }

    private BaseMediaChunk discardUpstreamMediaChunksFromIndex(int i) {
        BaseMediaChunk baseMediaChunk = this.mediaChunks.get(i);
        ArrayList<BaseMediaChunk> arrayList = this.mediaChunks;
        Util.removeRange(arrayList, i, arrayList.size());
        int i2 = 0;
        this.primarySampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(0));
        while (true) {
            SampleQueue[] sampleQueueArr = this.embeddedSampleQueues;
            if (i2 >= sampleQueueArr.length) {
                return baseMediaChunk;
            }
            SampleQueue sampleQueue = sampleQueueArr[i2];
            i2++;
            sampleQueue.discardUpstreamSamples(baseMediaChunk.getFirstSampleIndex(i2));
        }
    }

    public final class EmbeddedSampleStream implements SampleStream {
        private boolean formatNotificationSent;
        private final int index;
        public final ChunkSampleStream<T> parent;
        private final SampleQueue sampleQueue;

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
        }

        public EmbeddedSampleStream(ChunkSampleStream<T> chunkSampleStream, SampleQueue sampleQueue2, int i) {
            this.parent = chunkSampleStream;
            this.sampleQueue = sampleQueue2;
            this.index = i;
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return ChunkSampleStream.this.loadingFinished || (!ChunkSampleStream.this.isPendingReset() && this.sampleQueue.hasNextSample());
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public int skipData(long j) {
            int i;
            if (!ChunkSampleStream.this.loadingFinished || j <= this.sampleQueue.getLargestQueuedTimestampUs()) {
                i = this.sampleQueue.advanceTo(j, true, true);
                if (i == -1) {
                    i = 0;
                }
            } else {
                i = this.sampleQueue.advanceToEnd();
            }
            if (i > 0) {
                maybeNotifyTrackFormatChanged();
            }
            return i;
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (ChunkSampleStream.this.isPendingReset()) {
                return -3;
            }
            int read = this.sampleQueue.read(formatHolder, decoderInputBuffer, z, ChunkSampleStream.this.loadingFinished, ChunkSampleStream.this.decodeOnlyUntilPositionUs);
            if (read == -4) {
                maybeNotifyTrackFormatChanged();
            }
            return read;
        }

        public void release() {
            Assertions.checkState(ChunkSampleStream.this.embeddedTracksSelected[this.index]);
            ChunkSampleStream.this.embeddedTracksSelected[this.index] = false;
        }

        private void maybeNotifyTrackFormatChanged() {
            if (!this.formatNotificationSent) {
                ChunkSampleStream.this.eventDispatcher.downstreamFormatChanged(ChunkSampleStream.this.embeddedTrackTypes[this.index], ChunkSampleStream.this.embeddedTrackFormats[this.index], 0, null, ChunkSampleStream.this.lastSeekPositionUs);
                this.formatNotificationSent = true;
            }
        }
    }
}
