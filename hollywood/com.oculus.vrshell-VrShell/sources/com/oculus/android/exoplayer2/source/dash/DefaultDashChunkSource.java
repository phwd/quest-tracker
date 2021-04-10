package com.oculus.android.exoplayer2.source.dash;

import android.os.SystemClock;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.extractor.ChunkIndex;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.SeekMap;
import com.oculus.android.exoplayer2.extractor.TrackOutput;
import com.oculus.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.oculus.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.oculus.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.oculus.android.exoplayer2.source.BehindLiveWindowException;
import com.oculus.android.exoplayer2.source.chunk.Chunk;
import com.oculus.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.oculus.android.exoplayer2.source.chunk.ChunkHolder;
import com.oculus.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.oculus.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.oculus.android.exoplayer2.source.chunk.InitializationChunk;
import com.oculus.android.exoplayer2.source.chunk.MediaChunk;
import com.oculus.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.oculus.android.exoplayer2.source.dash.DashChunkSource;
import com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.RangedUri;
import com.oculus.android.exoplayer2.source.dash.manifest.Representation;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.HttpDataSource;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultDashChunkSource implements DashChunkSource {
    private final int[] adaptationSetIndices;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private long liveEdgeTimeUs = C.TIME_UNSET;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    @Nullable
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    protected final TrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        public Factory(DataSource.Factory factory, int i) {
            this.dataSourceFactory = factory;
            this.maxSegmentsPerLoad = i;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, long j, boolean z, boolean z2, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, trackSelection, i2, this.dataSourceFactory.createDataSource(), j, this.maxSegmentsPerLoad, z, z2, playerTrackEmsgHandler);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection2, int i2, DataSource dataSource2, long j, int i3, boolean z, boolean z2, @Nullable PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2) {
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.adaptationSetIndices = iArr;
        this.trackSelection = trackSelection2;
        this.trackType = i2;
        this.dataSource = dataSource2;
        this.periodIndex = i;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler2;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[trackSelection2.length()];
        for (int i4 = 0; i4 < this.representationHolders.length; i4++) {
            this.representationHolders[i4] = new RepresentationHolder(periodDurationUs, i2, representations.get(trackSelection2.getIndexInTrackGroup(i4)), z, z2, playerTrackEmsgHandler2);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        RepresentationHolder[] representationHolderArr = this.representationHolders;
        for (RepresentationHolder representationHolder : representationHolderArr) {
            if (representationHolder.segmentIndex != null) {
                long segmentNum = representationHolder.getSegmentNum(j);
                long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(segmentNum);
                return Util.resolveSeekPositionUs(j, seekParameters, segmentStartTimeUs, (segmentStartTimeUs >= j || segmentNum >= ((long) (representationHolder.getSegmentCount() + -1))) ? segmentStartTimeUs : representationHolder.getSegmentStartTimeUs(segmentNum + 1));
            }
        }
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.dash.DashChunkSource
    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = this.manifest.getPeriodDurationUs(this.periodIndex);
            ArrayList<Representation> representations = getRepresentations();
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                this.representationHolders[i2].updateRepresentation(periodDurationUs, representations.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            this.manifestLoaderErrorThrower.maybeThrowError();
            return;
        }
        throw iOException;
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public int getPreferredQueueSize(long j, List<? extends MediaChunk> list) {
        if (this.fatalError != null || this.trackSelection.length() < 2) {
            return list.size();
        }
        return this.trackSelection.evaluateQueueSize(j, list);
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public void getNextChunk(MediaChunk mediaChunk, long j, long j2, ChunkHolder chunkHolder) {
        long j3;
        long j4;
        if (this.fatalError == null) {
            long j5 = j2 - j;
            long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j);
            long msToUs = C.msToUs(this.manifest.availabilityStartTimeMs) + C.msToUs(this.manifest.getPeriod(this.periodIndex).startMs) + j2;
            PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
            if (playerTrackEmsgHandler2 == null || !playerTrackEmsgHandler2.maybeRefreshManifestBeforeLoadingNextChunk(msToUs)) {
                this.trackSelection.updateSelectedTrack(j, j5, resolveTimeToLiveEdgeUs);
                RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.getSelectedIndex()];
                if (representationHolder.extractorWrapper != null) {
                    Representation representation = representationHolder.representation;
                    RangedUri initializationUri = representationHolder.extractorWrapper.getSampleFormats() == null ? representation.getInitializationUri() : null;
                    RangedUri indexUri = representationHolder.segmentIndex == null ? representation.getIndexUri() : null;
                    if (!(initializationUri == null && indexUri == null)) {
                        chunkHolder.chunk = newInitializationChunk(representationHolder, this.dataSource, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), initializationUri, indexUri);
                        return;
                    }
                }
                int segmentCount = representationHolder.getSegmentCount();
                boolean z = false;
                if (segmentCount == 0) {
                    if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                        z = true;
                    }
                    chunkHolder.endOfStream = z;
                    return;
                }
                long firstSegmentNum = representationHolder.getFirstSegmentNum();
                if (segmentCount == -1) {
                    long nowUnixTimeUs = (getNowUnixTimeUs() - C.msToUs(this.manifest.availabilityStartTimeMs)) - C.msToUs(this.manifest.getPeriod(this.periodIndex).startMs);
                    if (this.manifest.timeShiftBufferDepthMs != C.TIME_UNSET) {
                        firstSegmentNum = Math.max(firstSegmentNum, representationHolder.getSegmentNum(nowUnixTimeUs - C.msToUs(this.manifest.timeShiftBufferDepthMs)));
                    }
                    j3 = representationHolder.getSegmentNum(nowUnixTimeUs);
                } else {
                    j3 = ((long) segmentCount) + firstSegmentNum;
                }
                long j6 = j3 - 1;
                updateLiveEdgeTimeUs(representationHolder, j6);
                if (mediaChunk == null) {
                    j4 = Util.constrainValue(representationHolder.getSegmentNum(j2), firstSegmentNum, j6);
                } else {
                    j4 = mediaChunk.getNextChunkIndex();
                    if (j4 < firstSegmentNum) {
                        this.fatalError = new BehindLiveWindowException();
                        return;
                    }
                }
                int i = (j4 > j6 ? 1 : (j4 == j6 ? 0 : -1));
                if (i > 0 || (this.missingLastSegment && i >= 0)) {
                    if (!this.manifest.dynamic || this.periodIndex < this.manifest.getPeriodCount() - 1) {
                        z = true;
                    }
                    chunkHolder.endOfStream = z;
                    return;
                }
                chunkHolder.chunk = newMediaChunk(representationHolder, this.dataSource, this.trackType, this.trackSelection.getSelectedFormat(), this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), j4, (int) Math.min((long) this.maxSegmentsPerLoad, (j6 - j4) + 1));
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        SeekMap seekMap;
        if (chunk instanceof InitializationChunk) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat)];
            if (representationHolder.segmentIndex == null && (seekMap = representationHolder.extractorWrapper.getSeekMap()) != null) {
                representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) seekMap);
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler2 != null) {
            playerTrackEmsgHandler2.onChunkLoadCompleted(chunk);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean z, Exception exc) {
        RepresentationHolder representationHolder;
        int segmentCount;
        if (!z) {
            return false;
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler2 = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler2 != null && playerTrackEmsgHandler2.maybeRefreshManifestOnLoadingError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (exc instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) exc).responseCode == 404 && (segmentCount = (representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)]).getSegmentCount()) != -1 && segmentCount != 0) {
            if (((MediaChunk) chunk).getNextChunkIndex() > (representationHolder.getFirstSegmentNum() + ((long) segmentCount)) - 1) {
                this.missingLastSegment = true;
                return true;
            }
        }
        TrackSelection trackSelection2 = this.trackSelection;
        return ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection2, trackSelection2.indexOf(chunk.trackFormat), exc);
    }

    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i).representations);
        }
        return arrayList;
    }

    private void updateLiveEdgeTimeUs(RepresentationHolder representationHolder, long j) {
        this.liveEdgeTimeUs = this.manifest.dynamic ? representationHolder.getSegmentEndTimeUs(j) : C.TIME_UNSET;
    }

    private long getNowUnixTimeUs() {
        long currentTimeMillis;
        if (this.elapsedRealtimeOffsetMs != 0) {
            currentTimeMillis = SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs;
        } else {
            currentTimeMillis = System.currentTimeMillis();
        }
        return currentTimeMillis * 1000;
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (this.manifest.dynamic && this.liveEdgeTimeUs != C.TIME_UNSET) {
            return this.liveEdgeTimeUs - j;
        }
        return C.TIME_UNSET;
    }

    protected static Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource2, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2) {
        String str = representationHolder.representation.baseUrl;
        if (rangedUri != null && (rangedUri2 = rangedUri.attemptMerge(rangedUri2, str)) == null) {
            rangedUri2 = rangedUri;
        }
        return new InitializationChunk(dataSource2, new DataSpec(rangedUri2.resolveUri(str), rangedUri2.start, rangedUri2.length, representationHolder.representation.getCacheKey()), format, i, obj, representationHolder.extractorWrapper);
    }

    protected static Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource2, int i, Format format, int i2, Object obj, long j, int i3) {
        Representation representation = representationHolder.representation;
        long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(j);
        RangedUri segmentUrl = representationHolder.getSegmentUrl(j);
        String str = representation.baseUrl;
        if (representationHolder.extractorWrapper == null) {
            return new SingleSampleMediaChunk(dataSource2, new DataSpec(segmentUrl.resolveUri(str), segmentUrl.start, segmentUrl.length, representation.getCacheKey()), format, i2, obj, segmentStartTimeUs, representationHolder.getSegmentEndTimeUs(j), j, i, format);
        }
        int i4 = 1;
        RangedUri rangedUri = segmentUrl;
        int i5 = 1;
        while (i4 < i3) {
            RangedUri attemptMerge = rangedUri.attemptMerge(representationHolder.getSegmentUrl(((long) i4) + j), str);
            if (attemptMerge == null) {
                break;
            }
            i5++;
            i4++;
            rangedUri = attemptMerge;
        }
        return new ContainerMediaChunk(dataSource2, new DataSpec(rangedUri.resolveUri(str), rangedUri.start, rangedUri.length, representation.getCacheKey()), format, i2, obj, segmentStartTimeUs, representationHolder.getSegmentEndTimeUs((((long) i5) + j) - 1), j, i5, -representation.presentationTimeOffsetUs, representationHolder.extractorWrapper);
    }

    /* access modifiers changed from: protected */
    public static final class RepresentationHolder {
        final ChunkExtractorWrapper extractorWrapper;
        private long periodDurationUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private long segmentNumShift;

        RepresentationHolder(long j, int i, Representation representation2, boolean z, boolean z2, TrackOutput trackOutput) {
            Extractor extractor;
            List list;
            this.periodDurationUs = j;
            this.representation = representation2;
            String str = representation2.format.containerMimeType;
            if (mimeTypeIsRawText(str)) {
                this.extractorWrapper = null;
            } else {
                if (MimeTypes.APPLICATION_RAWCC.equals(str)) {
                    extractor = new RawCcExtractor(representation2.format);
                } else if (mimeTypeIsWebm(str)) {
                    extractor = new MatroskaExtractor(1);
                } else {
                    int i2 = z ? 4 : 0;
                    if (z2) {
                        list = Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null));
                    } else {
                        list = Collections.emptyList();
                    }
                    extractor = new FragmentedMp4Extractor(i2, null, null, null, list, trackOutput);
                }
                this.extractorWrapper = new ChunkExtractorWrapper(extractor, i, representation2.format);
            }
            this.segmentIndex = representation2.getIndex();
        }

        /* access modifiers changed from: package-private */
        public void updateRepresentation(long j, Representation representation2) throws BehindLiveWindowException {
            int segmentCount;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation2.getIndex();
            this.periodDurationUs = j;
            this.representation = representation2;
            if (index != null) {
                this.segmentIndex = index2;
                if (index.isExplicit() && (segmentCount = index.getSegmentCount(this.periodDurationUs)) != 0) {
                    long firstSegmentNum = (index.getFirstSegmentNum() + ((long) segmentCount)) - 1;
                    long timeUs = index.getTimeUs(firstSegmentNum) + index.getDurationUs(firstSegmentNum, this.periodDurationUs);
                    long firstSegmentNum2 = index2.getFirstSegmentNum();
                    long timeUs2 = index2.getTimeUs(firstSegmentNum2);
                    int i = (timeUs > timeUs2 ? 1 : (timeUs == timeUs2 ? 0 : -1));
                    if (i == 0) {
                        this.segmentNumShift += (firstSegmentNum + 1) - firstSegmentNum2;
                    } else if (i >= 0) {
                        this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum2;
                    } else {
                        throw new BehindLiveWindowException();
                    }
                }
            }
        }

        public long getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(long j) {
            return this.segmentIndex.getTimeUs(j - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(long j) {
            return getSegmentStartTimeUs(j) + this.segmentIndex.getDurationUs(j - this.segmentNumShift, this.periodDurationUs);
        }

        public long getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(long j) {
            return this.segmentIndex.getSegmentUrl(j - this.segmentNumShift);
        }

        private static boolean mimeTypeIsWebm(String str) {
            return str.startsWith(MimeTypes.VIDEO_WEBM) || str.startsWith(MimeTypes.AUDIO_WEBM) || str.startsWith(MimeTypes.APPLICATION_WEBM);
        }

        private static boolean mimeTypeIsRawText(String str) {
            return MimeTypes.isText(str) || MimeTypes.APPLICATION_TTML.equals(str);
        }
    }
}
