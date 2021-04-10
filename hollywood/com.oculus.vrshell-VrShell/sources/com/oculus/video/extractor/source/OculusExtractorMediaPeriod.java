package com.oculus.video.extractor.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.FormatHolder;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.extractor.DefaultExtractorInput;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.extractor.ExtractorOutput;
import com.oculus.android.exoplayer2.extractor.PositionHolder;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.SampleQueue;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.source.UnrecognizedInputFormatException;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.Loader;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.ConditionVariable;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.extractor.source.OculusExtractorMediaSource;
import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;

public class OculusExtractorMediaPeriod implements MediaPeriod, ExtractorOutput, Loader.Callback<ExtractingLoadable>, Loader.ReleaseCallback, SampleQueue.UpstreamFormatChangedListener {
    private static final long DEFAULT_LAST_SAMPLE_DURATION_US = 10000;
    private int actualMinLoadableRetryCount;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final long continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource dataSource;
    private long durationUs;
    private int enabledTrackCount;
    private final Handler eventHandler;
    private final OculusExtractorMediaSource.EventListener eventListener;
    private int extractedSamplesCountAtStartOfLoad;
    private final ExtractorHolder extractorHolder;
    private final Handler handler;
    private boolean haveAudioVideoTracks;
    private long lastSeekPositionUs;
    private long length;
    private final Listener listener;
    private final ConditionVariable loadCondition;
    private final Loader loader = new Loader("Loader:OculusExtractorMediaPeriod");
    private boolean loadingFinished;
    private final Runnable maybeFinishPrepareRunnable;
    private final int minLoadableRetryCount;
    private boolean notifyDiscontinuity;
    private final Runnable onContinueLoadingRequestedRunnable;
    private boolean pendingDeferredRetry;
    private long pendingResetPositionUs;
    private boolean prepared;
    private boolean released;
    private int[] sampleQueueTrackIds;
    private SampleQueue[] sampleQueues;
    private boolean sampleQueuesBuilt;
    private SeekMap seekMap;
    private boolean seenFirstTrackSelection;
    private boolean[] trackEnabledStates;
    private boolean[] trackIsAudioVideoFlags;
    private TrackGroupArray tracks;
    private final Uri uri;

    /* access modifiers changed from: package-private */
    public interface Listener {
        void onSourceInfoRefreshed(long j, boolean z);
    }

    private void maybeNotifyTrackFormat(int i) {
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
    }

    public OculusExtractorMediaPeriod(Context context, Uri uri2, DataSource dataSource2, Extractor[] extractorArr, int i, Handler handler2, OculusExtractorMediaSource.EventListener eventListener2, Listener listener2, Allocator allocator2, String str, int i2) {
        this.uri = uri2;
        this.dataSource = dataSource2;
        this.minLoadableRetryCount = i;
        this.eventHandler = handler2;
        this.eventListener = eventListener2;
        this.listener = listener2;
        this.allocator = allocator2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = (long) i2;
        this.extractorHolder = new ExtractorHolder(context, extractorArr, this);
        this.loadCondition = new ConditionVariable();
        this.maybeFinishPrepareRunnable = new Runnable() {
            /* class com.oculus.video.extractor.source.OculusExtractorMediaPeriod.AnonymousClass1 */

            public void run() {
                OculusExtractorMediaPeriod.this.maybeFinishPrepare();
            }
        };
        this.onContinueLoadingRequestedRunnable = new Runnable() {
            /* class com.oculus.video.extractor.source.OculusExtractorMediaPeriod.AnonymousClass2 */

            public void run() {
                if (!OculusExtractorMediaPeriod.this.released) {
                    OculusExtractorMediaPeriod.this.callback.onContinueLoadingRequested(OculusExtractorMediaPeriod.this);
                }
            }
        };
        this.handler = new Handler();
        this.sampleQueueTrackIds = new int[0];
        this.sampleQueues = new SampleQueue[0];
        this.pendingResetPositionUs = C.TIME_UNSET;
        this.length = -1;
        this.durationUs = C.TIME_UNSET;
        this.actualMinLoadableRetryCount = i == -1 ? 3 : i;
    }

    public void release() {
        if (this.prepared) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.discardToEnd();
            }
        }
        this.loader.release(this);
        this.handler.removeCallbacksAndMessages(null);
        this.released = true;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.ReleaseCallback
    public void onLoaderReleased() {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            sampleQueue.reset();
        }
        this.extractorHolder.release();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.loadCondition.open();
        startLoading();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        maybeThrowError();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.tracks;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        Assertions.checkState(this.prepared);
        int i = this.enabledTrackCount;
        int i2 = 0;
        for (int i3 = 0; i3 < trackSelectionArr.length; i3++) {
            if (sampleStreamArr[i3] != null && (trackSelectionArr[i3] == null || !zArr[i3])) {
                int i4 = ((SampleStreamImpl) sampleStreamArr[i3]).track;
                Assertions.checkState(this.trackEnabledStates[i4]);
                this.enabledTrackCount--;
                this.trackEnabledStates[i4] = false;
                sampleStreamArr[i3] = null;
            }
        }
        boolean z = !this.seenFirstTrackSelection ? j != 0 : i == 0;
        for (int i5 = 0; i5 < trackSelectionArr.length; i5++) {
            if (sampleStreamArr[i5] == null && trackSelectionArr[i5] != null) {
                TrackSelection trackSelection = trackSelectionArr[i5];
                Assertions.checkState(trackSelection.length() == 1);
                Assertions.checkState(trackSelection.getIndexInTrackGroup(0) == 0);
                int indexOf = this.tracks.indexOf(trackSelection.getTrackGroup());
                Assertions.checkState(!this.trackEnabledStates[indexOf]);
                this.enabledTrackCount++;
                this.trackEnabledStates[indexOf] = true;
                sampleStreamArr[i5] = new SampleStreamImpl(indexOf);
                zArr2[i5] = true;
                if (!z) {
                    SampleQueue sampleQueue = this.sampleQueues[indexOf];
                    sampleQueue.rewind();
                    z = sampleQueue.advanceTo(j, true, true) == -1 && sampleQueue.getReadIndex() != 0;
                }
            }
        }
        if (this.enabledTrackCount == 0) {
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = false;
            if (this.loader.isLoading()) {
                SampleQueue[] sampleQueueArr = this.sampleQueues;
                int length2 = sampleQueueArr.length;
                while (i2 < length2) {
                    sampleQueueArr[i2].discardToEnd();
                    i2++;
                }
                this.loader.cancelLoading();
            } else {
                SampleQueue[] sampleQueueArr2 = this.sampleQueues;
                int length3 = sampleQueueArr2.length;
                while (i2 < length3) {
                    sampleQueueArr2[i2].reset();
                    i2++;
                }
            }
        } else if (z) {
            j = seekToUs(j);
            while (i2 < sampleStreamArr.length) {
                if (sampleStreamArr[i2] != null) {
                    zArr2[i2] = true;
                }
                i2++;
            }
        }
        this.seenFirstTrackSelection = true;
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        int length2 = this.sampleQueues.length;
        for (int i = 0; i < length2; i++) {
            this.sampleQueues[i].discardTo(j, z, this.trackEnabledStates[i]);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        if (this.loadingFinished || this.pendingDeferredRetry) {
            return false;
        }
        if (this.prepared && this.enabledTrackCount == 0) {
            return false;
        }
        boolean open = this.loadCondition.open();
        if (this.loader.isLoading()) {
            return open;
        }
        startLoading();
        return true;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        if (this.enabledTrackCount == 0) {
            return Long.MIN_VALUE;
        }
        return getBufferedPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        if (!this.notifyDiscontinuity) {
            return C.TIME_UNSET;
        }
        this.notifyDiscontinuity = false;
        return this.lastSeekPositionUs;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        long j;
        if (this.loadingFinished) {
            return Long.MIN_VALUE;
        }
        if (isPendingReset()) {
            return this.pendingResetPositionUs;
        }
        if (this.haveAudioVideoTracks) {
            j = Long.MAX_VALUE;
            int length2 = this.sampleQueues.length;
            for (int i = 0; i < length2; i++) {
                if (this.trackIsAudioVideoFlags[i]) {
                    j = Math.min(j, this.sampleQueues[i].getLargestQueuedTimestampUs());
                }
            }
        } else {
            j = getLargestQueuedTimestampUs();
        }
        return j == Long.MIN_VALUE ? this.lastSeekPositionUs : j;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        if (!this.seekMap.isSeekable()) {
            j = 0;
        }
        SeekMap seekMap2 = this.seekMap;
        if (seekMap2 instanceof OculusFrameworkSourceExtractor) {
            j = ((OculusFrameworkSourceExtractor) seekMap2).getSyncFrmPosition(j);
        }
        this.lastSeekPositionUs = j;
        this.notifyDiscontinuity = false;
        if (!isPendingReset() && seekInsideBufferUs(j)) {
            return j;
        }
        this.pendingDeferredRetry = false;
        this.pendingResetPositionUs = j;
        this.loadingFinished = false;
        if (this.loader.isLoading()) {
            this.loader.cancelLoading();
        } else {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
        }
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        if (!this.seekMap.isSeekable()) {
            return 0;
        }
        SeekMap.SeekPoints seekPoints = this.seekMap.getSeekPoints(j);
        return Util.resolveSeekPositionUs(j, seekParameters, seekPoints.first.timeUs, seekPoints.second.timeUs);
    }

    /* access modifiers changed from: package-private */
    public boolean isReady(int i) {
        return this.loadingFinished || (!isPendingReset() && this.sampleQueues[i].hasNextSample());
    }

    /* access modifiers changed from: package-private */
    public void maybeThrowError() throws IOException {
        this.loader.maybeThrowError(this.actualMinLoadableRetryCount);
    }

    /* access modifiers changed from: package-private */
    public int readData(int i, FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
        if (this.notifyDiscontinuity || isPendingReset()) {
            return -3;
        }
        int read = this.sampleQueues[i].read(formatHolder, decoderInputBuffer, z, this.loadingFinished, this.lastSeekPositionUs);
        if (read == -4) {
            maybeNotifyTrackFormat(i);
        } else if (read == -3) {
            maybeStartDeferredRetry(i);
        }
        return read;
    }

    /* access modifiers changed from: package-private */
    public int skipData(int i, long j) {
        int i2 = 0;
        if (suppressRead()) {
            return 0;
        }
        SampleQueue sampleQueue = this.sampleQueues[i];
        if (!this.loadingFinished || j <= sampleQueue.getLargestQueuedTimestampUs()) {
            int advanceTo = sampleQueue.advanceTo(j, true, true);
            if (advanceTo != -1) {
                i2 = advanceTo;
            }
        } else {
            i2 = sampleQueue.advanceToEnd();
        }
        if (i2 > 0) {
            maybeNotifyTrackFormat(i);
        } else {
            maybeStartDeferredRetry(i);
        }
        return i2;
    }

    private void maybeStartDeferredRetry(int i) {
        if (this.pendingDeferredRetry && this.trackIsAudioVideoFlags[i] && !this.sampleQueues[i].hasNextSample()) {
            this.pendingResetPositionUs = 0;
            this.pendingDeferredRetry = false;
            this.notifyDiscontinuity = true;
            this.lastSeekPositionUs = 0;
            this.extractedSamplesCountAtStartOfLoad = 0;
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    private boolean suppressRead() {
        return this.notifyDiscontinuity || isPendingReset();
    }

    public void onLoadCompleted(ExtractingLoadable extractingLoadable, long j, long j2) {
        if (this.durationUs == C.TIME_UNSET) {
            long largestQueuedTimestampUs = getLargestQueuedTimestampUs();
            this.durationUs = largestQueuedTimestampUs == Long.MIN_VALUE ? 0 : largestQueuedTimestampUs + DEFAULT_LAST_SAMPLE_DURATION_US;
            this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
        }
        copyLengthFromLoader(extractingLoadable);
        this.loadingFinished = true;
        this.callback.onContinueLoadingRequested(this);
    }

    public void onLoadCanceled(ExtractingLoadable extractingLoadable, long j, long j2, boolean z) {
        if (!z) {
            copyLengthFromLoader(extractingLoadable);
            for (SampleQueue sampleQueue : this.sampleQueues) {
                sampleQueue.reset();
            }
            if (this.enabledTrackCount > 0) {
                this.callback.onContinueLoadingRequested(this);
            }
        }
    }

    public int onLoadError(ExtractingLoadable extractingLoadable, long j, long j2, IOException iOException) {
        copyLengthFromLoader(extractingLoadable);
        notifyLoadError(iOException);
        if (isLoadableExceptionFatal(iOException)) {
            return 3;
        }
        int extractedSamplesCount = getExtractedSamplesCount();
        boolean z = extractedSamplesCount > this.extractedSamplesCountAtStartOfLoad;
        if (configureRetry(extractingLoadable, extractedSamplesCount)) {
            return z ? 1 : 0;
        }
        return 2;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public TrackOutput track(int i, int i2) {
        int length2 = this.sampleQueues.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (this.sampleQueueTrackIds[i3] == i) {
                return this.sampleQueues[i3];
            }
        }
        SampleQueue sampleQueue = new SampleQueue(this.allocator);
        sampleQueue.setUpstreamFormatChangeListener(this);
        int i4 = length2 + 1;
        this.sampleQueueTrackIds = Arrays.copyOf(this.sampleQueueTrackIds, i4);
        this.sampleQueueTrackIds[length2] = i;
        this.sampleQueues = (SampleQueue[]) Arrays.copyOf(this.sampleQueues, i4);
        this.sampleQueues[length2] = sampleQueue;
        return sampleQueue;
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public void endTracks() {
        this.sampleQueuesBuilt = true;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.oculus.android.exoplayer2.extractor.ExtractorOutput
    public void seekMap(SeekMap seekMap2) {
        this.seekMap = seekMap2;
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    @Override // com.oculus.android.exoplayer2.source.SampleQueue.UpstreamFormatChangedListener
    public void onUpstreamFormatChanged(Format format) {
        this.handler.post(this.maybeFinishPrepareRunnable);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void maybeFinishPrepare() {
        if (!(this.released || this.prepared || this.seekMap == null || !this.sampleQueuesBuilt)) {
            for (SampleQueue sampleQueue : this.sampleQueues) {
                if (sampleQueue.getUpstreamFormat() == null) {
                    return;
                }
            }
            this.loadCondition.close();
            int length2 = this.sampleQueues.length;
            TrackGroup[] trackGroupArr = new TrackGroup[length2];
            this.trackIsAudioVideoFlags = new boolean[length2];
            this.trackEnabledStates = new boolean[length2];
            this.durationUs = this.seekMap.getDurationUs();
            int i = 0;
            while (true) {
                boolean z = true;
                if (i >= length2) {
                    break;
                }
                Format upstreamFormat = this.sampleQueues[i].getUpstreamFormat();
                trackGroupArr[i] = new TrackGroup(upstreamFormat);
                String str = upstreamFormat.sampleMimeType;
                if (!MimeTypes.isVideo(str) && !MimeTypes.isAudio(str)) {
                    z = false;
                }
                this.trackIsAudioVideoFlags[i] = z;
                this.haveAudioVideoTracks = z | this.haveAudioVideoTracks;
                i++;
            }
            this.tracks = new TrackGroupArray(trackGroupArr);
            if (this.minLoadableRetryCount == -1 && this.length == -1 && this.seekMap.getDurationUs() == C.TIME_UNSET) {
                this.actualMinLoadableRetryCount = 6;
            }
            this.prepared = true;
            this.listener.onSourceInfoRefreshed(this.durationUs, this.seekMap.isSeekable());
            this.callback.onPrepared(this);
        }
    }

    private void copyLengthFromLoader(ExtractingLoadable extractingLoadable) {
        if (this.length == -1) {
            this.length = extractingLoadable.length;
        }
    }

    private void startLoading() {
        ExtractingLoadable extractingLoadable = new ExtractingLoadable(this.uri, this.dataSource, this.extractorHolder, this.loadCondition);
        if (this.prepared) {
            Assertions.checkState(isPendingReset());
            long j = this.durationUs;
            if (j == C.TIME_UNSET || this.pendingResetPositionUs < j) {
                extractingLoadable.setLoadPosition(this.seekMap.getSeekPoints(this.pendingResetPositionUs).first.position, this.pendingResetPositionUs);
                this.pendingResetPositionUs = C.TIME_UNSET;
            } else {
                this.loadingFinished = true;
                this.pendingResetPositionUs = C.TIME_UNSET;
                return;
            }
        }
        this.extractedSamplesCountAtStartOfLoad = getExtractedSamplesCount();
        this.loader.startLoading(extractingLoadable, this, this.actualMinLoadableRetryCount);
    }

    private boolean configureRetry(ExtractingLoadable extractingLoadable, int i) {
        SeekMap seekMap2;
        if (this.length == -1 && ((seekMap2 = this.seekMap) == null || seekMap2.getDurationUs() == C.TIME_UNSET)) {
            if (!this.prepared || suppressRead()) {
                this.notifyDiscontinuity = this.prepared;
                this.lastSeekPositionUs = 0;
                this.extractedSamplesCountAtStartOfLoad = 0;
                for (SampleQueue sampleQueue : this.sampleQueues) {
                    sampleQueue.reset();
                }
                extractingLoadable.setLoadPosition(0, 0);
                return true;
            }
            this.pendingDeferredRetry = true;
            return false;
        }
        this.extractedSamplesCountAtStartOfLoad = i;
        return true;
    }

    private boolean seekInsideBufferUs(long j) {
        int length2 = this.sampleQueues.length;
        int i = 0;
        while (true) {
            boolean z = true;
            if (i >= length2) {
                return true;
            }
            SampleQueue sampleQueue = this.sampleQueues[i];
            sampleQueue.rewind();
            if (sampleQueue.advanceTo(j, true, false) == -1) {
                z = false;
            }
            if (z || (!this.trackIsAudioVideoFlags[i] && this.haveAudioVideoTracks)) {
                i++;
            }
        }
        return false;
    }

    private int getExtractedSamplesCount() {
        int i = 0;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            i += sampleQueue.getWriteIndex();
        }
        return i;
    }

    private long getLargestQueuedTimestampUs() {
        long j = Long.MIN_VALUE;
        for (SampleQueue sampleQueue : this.sampleQueues) {
            j = Math.max(j, sampleQueue.getLargestQueuedTimestampUs());
        }
        return j;
    }

    private boolean isPendingReset() {
        return this.pendingResetPositionUs != C.TIME_UNSET;
    }

    private static boolean isLoadableExceptionFatal(IOException iOException) {
        return iOException instanceof UnrecognizedInputFormatException;
    }

    private void notifyLoadError(final IOException iOException) {
        Handler handler2 = this.eventHandler;
        if (handler2 != null && this.eventListener != null) {
            handler2.post(new Runnable() {
                /* class com.oculus.video.extractor.source.OculusExtractorMediaPeriod.AnonymousClass3 */

                public void run() {
                    OculusExtractorMediaPeriod.this.eventListener.onLoadError(iOException);
                }
            });
        }
    }

    private final class SampleStreamImpl implements SampleStream {
        private final int track;

        public SampleStreamImpl(int i) {
            this.track = i;
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public boolean isReady() {
            return OculusExtractorMediaPeriod.this.isReady(this.track);
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public void maybeThrowError() throws IOException {
            OculusExtractorMediaPeriod.this.maybeThrowError();
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            return OculusExtractorMediaPeriod.this.readData(this.track, formatHolder, decoderInputBuffer, z);
        }

        @Override // com.oculus.android.exoplayer2.source.SampleStream
        public int skipData(long j) {
            return OculusExtractorMediaPeriod.this.skipData(this.track, j);
        }
    }

    public class ExtractingLoadable implements Loader.Loadable {
        private final DataSource dataSource;
        private final ExtractorHolder extractorHolder;
        private long length = -1;
        private volatile boolean loadCanceled;
        private final ConditionVariable loadCondition;
        private boolean pendingExtractorSeek = true;
        private final PositionHolder positionHolder = new PositionHolder();
        private long seekTimeUs;
        private final Uri uri;

        public ExtractingLoadable(Uri uri2, DataSource dataSource2, ExtractorHolder extractorHolder2, ConditionVariable conditionVariable) {
            this.uri = (Uri) Assertions.checkNotNull(uri2);
            this.dataSource = (DataSource) Assertions.checkNotNull(dataSource2);
            this.extractorHolder = (ExtractorHolder) Assertions.checkNotNull(extractorHolder2);
            this.loadCondition = conditionVariable;
        }

        public void setLoadPosition(long j, long j2) {
            this.positionHolder.position = j;
            this.seekTimeUs = j2;
            this.pendingExtractorSeek = true;
        }

        @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
        public void cancelLoad() {
            this.loadCanceled = true;
        }

        @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
        public boolean isLoadCanceled() {
            return this.loadCanceled;
        }

        @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
        public void load() throws IOException, InterruptedException {
            Throwable th;
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                DefaultExtractorInput defaultExtractorInput = null;
                try {
                    if (this.extractorHolder.shouldBypassDataSource()) {
                        long j = this.positionHolder.position;
                        Extractor selectExtractor = this.extractorHolder.selectExtractor(null, this.uri);
                        if (this.pendingExtractorSeek) {
                            selectExtractor.seek(j, this.seekTimeUs);
                            this.pendingExtractorSeek = false;
                        }
                        while (i == 0 && !this.loadCanceled) {
                            this.loadCondition.block();
                            i = selectExtractor.read(null, this.positionHolder);
                        }
                    } else {
                        long j2 = this.positionHolder.position;
                        this.length = this.dataSource.open(new DataSpec(this.uri, j2, -1, OculusExtractorMediaPeriod.this.customCacheKey));
                        if (this.length != -1) {
                            this.length += j2;
                        }
                        DefaultExtractorInput defaultExtractorInput2 = new DefaultExtractorInput(this.dataSource, j2, this.length);
                        try {
                            Extractor selectExtractor2 = this.extractorHolder.selectExtractor(defaultExtractorInput2, this.dataSource.getUri());
                            if (this.pendingExtractorSeek) {
                                selectExtractor2.seek(j2, this.seekTimeUs);
                                this.pendingExtractorSeek = false;
                            }
                            while (i == 0 && !this.loadCanceled) {
                                this.loadCondition.block();
                                i = selectExtractor2.read(defaultExtractorInput2, this.positionHolder);
                                if (defaultExtractorInput2.getPosition() > OculusExtractorMediaPeriod.this.continueLoadingCheckIntervalBytes + j2) {
                                    j2 = defaultExtractorInput2.getPosition();
                                    this.loadCondition.close();
                                    OculusExtractorMediaPeriod.this.handler.post(OculusExtractorMediaPeriod.this.onContinueLoadingRequestedRunnable);
                                }
                            }
                            defaultExtractorInput = defaultExtractorInput2;
                        } catch (Throwable th2) {
                            th = th2;
                            defaultExtractorInput = defaultExtractorInput2;
                            if (!(i == 1 || defaultExtractorInput == null)) {
                                this.positionHolder.position = defaultExtractorInput.getPosition();
                            }
                            Util.closeQuietly(this.dataSource);
                            throw th;
                        }
                    }
                    if (i == 1) {
                        i = 0;
                    } else if (defaultExtractorInput != null) {
                        this.positionHolder.position = defaultExtractorInput.getPosition();
                    }
                    Util.closeQuietly(this.dataSource);
                } catch (Throwable th3) {
                    th = th3;
                    this.positionHolder.position = defaultExtractorInput.getPosition();
                    Util.closeQuietly(this.dataSource);
                    throw th;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class ExtractorHolder {
        private final Context context;
        private Extractor extractor;
        private final ExtractorOutput extractorOutput;
        private final Extractor[] extractors;

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private boolean shouldBypassDataSource() {
            Extractor extractor2 = this.extractor;
            return extractor2 != null && (extractor2 instanceof OculusFrameworkSourceExtractor);
        }

        public ExtractorHolder(Context context2, Extractor[] extractorArr, ExtractorOutput extractorOutput2) {
            this.context = context2;
            this.extractors = extractorArr;
            this.extractorOutput = extractorOutput2;
        }

        public Extractor selectExtractor(ExtractorInput extractorInput, Uri uri) throws IOException, InterruptedException {
            boolean z;
            Extractor extractor2 = this.extractor;
            if (extractor2 != null) {
                return extractor2;
            }
            Extractor[] extractorArr = this.extractors;
            int length = extractorArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Extractor extractor3 = extractorArr[i];
                try {
                    if (extractor3 instanceof OculusFrameworkSourceExtractor) {
                        z = ((OculusFrameworkSourceExtractor) extractor3).sniff(this.context, uri);
                    } else {
                        z = extractor3.sniff(extractorInput);
                    }
                    if (z) {
                        this.extractor = extractor3;
                        if (extractorInput != null) {
                            extractorInput.resetPeekPosition();
                        }
                    } else {
                        if (extractorInput == null) {
                            i++;
                        }
                        extractorInput.resetPeekPosition();
                        i++;
                    }
                } catch (EOFException unused) {
                    if (extractorInput == null) {
                    }
                } catch (Throwable th) {
                    if (extractorInput != null) {
                        extractorInput.resetPeekPosition();
                    }
                    throw th;
                }
            }
            Extractor extractor4 = this.extractor;
            if (extractor4 != null) {
                extractor4.init(this.extractorOutput);
                return this.extractor;
            }
            throw new UnrecognizedInputFormatException("None of the available extractors (" + Util.getCommaDelimitedSimpleClassNames(this.extractors) + ") could read the stream.", uri);
        }

        public void release() {
            Extractor extractor2 = this.extractor;
            if (extractor2 != null) {
                extractor2.release();
                this.extractor = null;
            }
        }
    }
}
