package com.oculus.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.EmptySampleStream;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.SequenceableLoader;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.oculus.android.exoplayer2.source.dash.DashChunkSource;
import com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.Descriptor;
import com.oculus.android.exoplayer2.source.dash.manifest.EventStream;
import com.oculus.android.exoplayer2.source.dash.manifest.Period;
import com.oculus.android.exoplayer2.source.dash.manifest.Representation;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;
import com.oculus.android.exoplayer2.util.MimeTypes;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>>, ChunkSampleStream.ReleaseCallback<DashChunkSource> {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final DashChunkSource.Factory chunkSourceFactory;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final long elapsedRealtimeOffset;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private EventSampleStream[] eventSampleStreams = new EventSampleStream[0];
    private List<EventStream> eventStreams;
    final int id;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams = newSampleStreamArray(0);
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerEmsgHandler.PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream = new IdentityHashMap<>();
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public DashMediaPeriod(int i, DashManifest dashManifest, int i2, DashChunkSource.Factory factory, int i3, MediaSourceEventListener.EventDispatcher eventDispatcher2, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback) {
        this.id = i;
        this.manifest = dashManifest;
        this.periodIndex = i2;
        this.chunkSourceFactory = factory;
        this.minLoadableRetryCount = i3;
        this.eventDispatcher = eventDispatcher2;
        this.elapsedRealtimeOffset = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.playerEmsgHandler = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator2);
        this.compositeSequenceableLoader = compositeSequenceableLoaderFactory2.createCompositeSequenceableLoader(this.sampleStreams);
        Period period = dashManifest.getPeriod(i2);
        this.eventStreams = period.eventStreams;
        Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups = buildTrackGroups(period.adaptationSets, this.eventStreams);
        this.trackGroups = (TrackGroupArray) buildTrackGroups.first;
        this.trackGroupInfos = (TrackGroupInfo[]) buildTrackGroups.second;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        this.manifest = dashManifest;
        this.periodIndex = i;
        this.playerEmsgHandler.updateManifest(dashManifest);
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSampleStream : chunkSampleStreamArr) {
                chunkSampleStream.getChunkSource().updateManifest(dashManifest, i);
            }
            this.callback.onContinueLoadingRequested(this);
        }
        this.eventStreams = dashManifest.getPeriod(i).eventStreams;
        EventSampleStream[] eventSampleStreamArr = this.eventSampleStreams;
        for (EventSampleStream eventSampleStream : eventSampleStreamArr) {
            Iterator<EventStream> it = this.eventStreams.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                EventStream next = it.next();
                if (next.id().equals(eventSampleStream.eventStreamId())) {
                    eventSampleStream.updateEventStream(next, dashManifest.dynamic);
                    break;
                }
            }
        }
    }

    public void release() {
        this.playerEmsgHandler.release();
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.release(this);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.ChunkSampleStream.ReleaseCallback
    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        PlayerEmsgHandler.PlayerTrackEmsgHandler remove = this.trackEmsgHandlerBySampleStream.remove(chunkSampleStream);
        if (remove != null) {
            remove.release();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        callback2.onPrepared(this);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        SparseArray<ChunkSampleStream<DashChunkSource>> sparseArray = new SparseArray<>();
        ArrayList arrayList = new ArrayList();
        selectPrimarySampleStreams(trackSelectionArr, zArr, sampleStreamArr, zArr2, j, sparseArray);
        selectEventSampleStreams(trackSelectionArr, zArr, sampleStreamArr, zArr2, arrayList);
        selectEmbeddedSampleStreams(trackSelectionArr, zArr, sampleStreamArr, zArr2, j, sparseArray);
        this.sampleStreams = newSampleStreamArray(sparseArray.size());
        int i = 0;
        while (true) {
            ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
            if (i < chunkSampleStreamArr.length) {
                chunkSampleStreamArr[i] = sparseArray.valueAt(i);
                i++;
            } else {
                this.eventSampleStreams = new EventSampleStream[arrayList.size()];
                arrayList.toArray(this.eventSampleStreams);
                this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.sampleStreams);
                return j;
            }
        }
    }

    private void selectPrimarySampleStreams(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j, SparseArray<ChunkSampleStream<DashChunkSource>> sparseArray) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (sampleStreamArr[i] instanceof ChunkSampleStream) {
                ChunkSampleStream<DashChunkSource> chunkSampleStream = (ChunkSampleStream) sampleStreamArr[i];
                if (trackSelectionArr[i] == null || !zArr[i]) {
                    chunkSampleStream.release(this);
                    sampleStreamArr[i] = null;
                } else {
                    sparseArray.put(this.trackGroups.indexOf(trackSelectionArr[i].getTrackGroup()), chunkSampleStream);
                }
            }
            if (sampleStreamArr[i] == null && trackSelectionArr[i] != null) {
                int indexOf = this.trackGroups.indexOf(trackSelectionArr[i].getTrackGroup());
                TrackGroupInfo trackGroupInfo = this.trackGroupInfos[indexOf];
                if (trackGroupInfo.trackGroupCategory == 0) {
                    ChunkSampleStream<DashChunkSource> buildSampleStream = buildSampleStream(trackGroupInfo, trackSelectionArr[i], j);
                    sparseArray.put(indexOf, buildSampleStream);
                    sampleStreamArr[i] = buildSampleStream;
                    zArr2[i] = true;
                }
            }
        }
    }

    private void selectEventSampleStreams(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, List<EventSampleStream> list) {
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (sampleStreamArr[i] instanceof EventSampleStream) {
                EventSampleStream eventSampleStream = (EventSampleStream) sampleStreamArr[i];
                if (trackSelectionArr[i] == null || !zArr[i]) {
                    sampleStreamArr[i] = null;
                } else {
                    list.add(eventSampleStream);
                }
            }
            if (sampleStreamArr[i] == null && trackSelectionArr[i] != null) {
                TrackGroupInfo trackGroupInfo = this.trackGroupInfos[this.trackGroups.indexOf(trackSelectionArr[i].getTrackGroup())];
                if (trackGroupInfo.trackGroupCategory == 2) {
                    EventSampleStream eventSampleStream2 = new EventSampleStream(this.eventStreams.get(trackGroupInfo.eventStreamGroupIndex), trackSelectionArr[i].getTrackGroup().getFormat(0), this.manifest.dynamic);
                    sampleStreamArr[i] = eventSampleStream2;
                    zArr2[i] = true;
                    list.add(eventSampleStream2);
                }
            }
        }
    }

    private void selectEmbeddedSampleStreams(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j, SparseArray<ChunkSampleStream<DashChunkSource>> sparseArray) {
        SampleStream sampleStream;
        for (int i = 0; i < trackSelectionArr.length; i++) {
            if (((sampleStreamArr[i] instanceof ChunkSampleStream.EmbeddedSampleStream) || (sampleStreamArr[i] instanceof EmptySampleStream)) && (trackSelectionArr[i] == null || !zArr[i])) {
                releaseIfEmbeddedSampleStream(sampleStreamArr[i]);
                sampleStreamArr[i] = null;
            }
            if (trackSelectionArr[i] != null) {
                TrackGroupInfo trackGroupInfo = this.trackGroupInfos[this.trackGroups.indexOf(trackSelectionArr[i].getTrackGroup())];
                if (trackGroupInfo.trackGroupCategory == 1) {
                    ChunkSampleStream<DashChunkSource> chunkSampleStream = sparseArray.get(trackGroupInfo.primaryTrackGroupIndex);
                    SampleStream sampleStream2 = sampleStreamArr[i];
                    if (!(chunkSampleStream == null ? sampleStream2 instanceof EmptySampleStream : (sampleStream2 instanceof ChunkSampleStream.EmbeddedSampleStream) && ((ChunkSampleStream.EmbeddedSampleStream) sampleStream2).parent == chunkSampleStream)) {
                        releaseIfEmbeddedSampleStream(sampleStream2);
                        if (chunkSampleStream == null) {
                            sampleStream = new EmptySampleStream();
                        } else {
                            sampleStream = chunkSampleStream.selectEmbeddedTrack(j, trackGroupInfo.trackType);
                        }
                        sampleStreamArr[i] = sampleStream;
                        zArr2[i] = true;
                    }
                }
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.discardBuffer(j, z);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        return this.compositeSequenceableLoader.continueLoading(j);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.seekToUs(j);
        }
        for (EventSampleStream eventSampleStream : this.eventSampleStreams) {
            eventSampleStream.seekToUs(j);
        }
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : chunkSampleStreamArr) {
            if (chunkSampleStream.primaryTrackType == 2) {
                return chunkSampleStream.getAdjustedSeekPositionUs(j, seekParameters);
            }
        }
        return j;
    }

    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(List<AdaptationSet> list, List<EventStream> list2) {
        int[][] groupedAdaptationSetIndices = getGroupedAdaptationSetIndices(list);
        int length = groupedAdaptationSetIndices.length;
        boolean[] zArr = new boolean[length];
        boolean[] zArr2 = new boolean[length];
        int identifyEmbeddedTracks = identifyEmbeddedTracks(length, list, groupedAdaptationSetIndices, zArr, zArr2) + length + list2.size();
        TrackGroup[] trackGroupArr = new TrackGroup[identifyEmbeddedTracks];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[identifyEmbeddedTracks];
        buildManifestEventTrackGroupInfos(list2, trackGroupArr, trackGroupInfoArr, buildPrimaryAndEmbeddedTrackGroupInfos(list, groupedAdaptationSetIndices, length, zArr, zArr2, trackGroupArr, trackGroupInfoArr));
        return Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
    }

    private static int[][] getGroupedAdaptationSetIndices(List<AdaptationSet> list) {
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i = 0; i < size; i++) {
            sparseIntArray.put(list.get(i).id, i);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr = new boolean[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (!zArr[i3]) {
                zArr[i3] = true;
                Descriptor findAdaptationSetSwitchingProperty = findAdaptationSetSwitchingProperty(list.get(i3).supplementalProperties);
                if (findAdaptationSetSwitchingProperty == null) {
                    iArr[i2] = new int[]{i3};
                    i2++;
                } else {
                    String[] split = findAdaptationSetSwitchingProperty.value.split(",");
                    int[] iArr2 = new int[(split.length + 1)];
                    iArr2[0] = i3;
                    int i4 = 0;
                    while (i4 < split.length) {
                        int i5 = sparseIntArray.get(Integer.parseInt(split[i4]));
                        zArr[i5] = true;
                        i4++;
                        iArr2[i4] = i5;
                    }
                    iArr[i2] = iArr2;
                    i2++;
                }
            }
        }
        return i2 < size ? (int[][]) Arrays.copyOf(iArr, i2) : iArr;
    }

    private static int identifyEmbeddedTracks(int i, List<AdaptationSet> list, int[][] iArr, boolean[] zArr, boolean[] zArr2) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (hasEventMessageTrack(list, iArr[i3])) {
                zArr[i3] = true;
                i2++;
            }
            if (hasCea608Track(list, iArr[i3])) {
                zArr2[i3] = true;
                i2++;
            }
        }
        return i2;
    }

    private static int buildPrimaryAndEmbeddedTrackGroupInfos(List<AdaptationSet> list, int[][] iArr, int i, boolean[] zArr, boolean[] zArr2, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr) {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int[] iArr2 = iArr[i4];
            ArrayList arrayList = new ArrayList();
            for (int i6 : iArr2) {
                arrayList.addAll(list.get(i6).representations);
            }
            Format[] formatArr = new Format[arrayList.size()];
            for (int i7 = 0; i7 < formatArr.length; i7++) {
                formatArr[i7] = ((Representation) arrayList.get(i7)).format;
            }
            AdaptationSet adaptationSet = list.get(iArr2[0]);
            int i8 = i5 + 1;
            if (zArr[i4]) {
                i2 = i8 + 1;
            } else {
                i2 = i8;
                i8 = -1;
            }
            if (zArr2[i4]) {
                i3 = i2 + 1;
            } else {
                i3 = i2;
                i2 = -1;
            }
            trackGroupArr[i5] = new TrackGroup(formatArr);
            trackGroupInfoArr[i5] = TrackGroupInfo.primaryTrack(adaptationSet.type, iArr2, i5, i8, i2);
            if (i8 != -1) {
                trackGroupArr[i8] = new TrackGroup(Format.createSampleFormat(adaptationSet.id + ":emsg", MimeTypes.APPLICATION_EMSG, null, -1, null));
                trackGroupInfoArr[i8] = TrackGroupInfo.embeddedEmsgTrack(iArr2, i5);
            }
            if (i2 != -1) {
                trackGroupArr[i2] = new TrackGroup(Format.createTextSampleFormat(adaptationSet.id + ":cea608", MimeTypes.APPLICATION_CEA608, 0, null));
                trackGroupInfoArr[i2] = TrackGroupInfo.embeddedCea608Track(iArr2, i5);
            }
            i4++;
            i5 = i3;
        }
        return i5;
    }

    private static void buildManifestEventTrackGroupInfos(List<EventStream> list, TrackGroup[] trackGroupArr, TrackGroupInfo[] trackGroupInfoArr, int i) {
        int i2 = i;
        int i3 = 0;
        while (i3 < list.size()) {
            trackGroupArr[i2] = new TrackGroup(Format.createSampleFormat(list.get(i3).id(), MimeTypes.APPLICATION_EMSG, null, -1, null));
            trackGroupInfoArr[i2] = TrackGroupInfo.mpdEventTrack(i3);
            i3++;
            i2++;
        }
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo, TrackSelection trackSelection, long j) {
        int i;
        int[] iArr = new int[2];
        Format[] formatArr = new Format[2];
        boolean z = trackGroupInfo.embeddedEventMessageTrackGroupIndex != -1;
        if (z) {
            formatArr[0] = this.trackGroups.get(trackGroupInfo.embeddedEventMessageTrackGroupIndex).getFormat(0);
            iArr[0] = 4;
            i = 1;
        } else {
            i = 0;
        }
        boolean z2 = trackGroupInfo.embeddedCea608TrackGroupIndex != -1;
        if (z2) {
            formatArr[i] = this.trackGroups.get(trackGroupInfo.embeddedCea608TrackGroupIndex).getFormat(0);
            iArr[i] = 3;
            i++;
        }
        if (i < iArr.length) {
            formatArr = (Format[]) Arrays.copyOf(formatArr, i);
            iArr = Arrays.copyOf(iArr, i);
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler newPlayerTrackEmsgHandler = (!this.manifest.dynamic || !z) ? null : this.playerEmsgHandler.newPlayerTrackEmsgHandler();
        ChunkSampleStream<DashChunkSource> chunkSampleStream = new ChunkSampleStream<>(trackGroupInfo.trackType, iArr, formatArr, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.periodIndex, trackGroupInfo.adaptationSetIndices, trackSelection, trackGroupInfo.trackType, this.elapsedRealtimeOffset, z, z2, newPlayerTrackEmsgHandler), this, this.allocator, j, this.minLoadableRetryCount, this.eventDispatcher);
        synchronized (this) {
            this.trackEmsgHandlerBySampleStream.put(chunkSampleStream, newPlayerTrackEmsgHandler);
        }
        return chunkSampleStream;
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(descriptor.schemeIdUri)) {
                return descriptor;
            }
        }
        return null;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Representation> list2 = list.get(i).representations;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!list2.get(i2).inbandEventStreams.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCea608Track(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Descriptor> list2 = list.get(i).accessibilityDescriptors;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if ("urn:scte:dash:cc:cea-608:2015".equals(list2.get(i2).schemeIdUri)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int i) {
        return new ChunkSampleStream[i];
    }

    private static void releaseIfEmbeddedSampleStream(SampleStream sampleStream) {
        if (sampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) {
            ((ChunkSampleStream.EmbeddedSampleStream) sampleStream).release();
        }
    }

    /* access modifiers changed from: private */
    public static final class TrackGroupInfo {
        private static final int CATEGORY_EMBEDDED = 1;
        private static final int CATEGORY_MANIFEST_EVENTS = 2;
        private static final int CATEGORY_PRIMARY = 0;
        public final int[] adaptationSetIndices;
        public final int embeddedCea608TrackGroupIndex;
        public final int embeddedEventMessageTrackGroupIndex;
        public final int eventStreamGroupIndex;
        public final int primaryTrackGroupIndex;
        public final int trackGroupCategory;
        public final int trackType;

        @Retention(RetentionPolicy.SOURCE)
        public @interface TrackGroupCategory {
        }

        public static TrackGroupInfo primaryTrack(int i, int[] iArr, int i2, int i3, int i4) {
            return new TrackGroupInfo(i, 0, iArr, i2, i3, i4, -1);
        }

        public static TrackGroupInfo embeddedEmsgTrack(int[] iArr, int i) {
            return new TrackGroupInfo(4, 1, iArr, i, -1, -1, -1);
        }

        public static TrackGroupInfo embeddedCea608Track(int[] iArr, int i) {
            return new TrackGroupInfo(3, 1, iArr, i, -1, -1, -1);
        }

        public static TrackGroupInfo mpdEventTrack(int i) {
            return new TrackGroupInfo(4, 2, null, -1, -1, -1, i);
        }

        private TrackGroupInfo(int i, int i2, int[] iArr, int i3, int i4, int i5, int i6) {
            this.trackType = i;
            this.adaptationSetIndices = iArr;
            this.trackGroupCategory = i2;
            this.primaryTrackGroupIndex = i3;
            this.embeddedEventMessageTrackGroupIndex = i4;
            this.embeddedCea608TrackGroupIndex = i5;
            this.eventStreamGroupIndex = i6;
        }
    }
}
