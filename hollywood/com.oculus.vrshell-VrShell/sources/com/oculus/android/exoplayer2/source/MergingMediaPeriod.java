package com.oculus.android.exoplayer2.source;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;

final class MergingMediaPeriod implements MediaPeriod, MediaPeriod.Callback {
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaPeriod[] enabledPeriods;
    private int pendingChildPrepareCount;
    public final MediaPeriod[] periods;
    private final IdentityHashMap<SampleStream, Integer> streamPeriodIndices = new IdentityHashMap<>();
    private TrackGroupArray trackGroups;

    public MergingMediaPeriod(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, MediaPeriod... mediaPeriodArr) {
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.periods = mediaPeriodArr;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        MediaPeriod[] mediaPeriodArr = this.periods;
        this.pendingChildPrepareCount = mediaPeriodArr.length;
        for (MediaPeriod mediaPeriod : mediaPeriodArr) {
            mediaPeriod.prepare(this, j);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (MediaPeriod mediaPeriod : this.periods) {
            mediaPeriod.maybeThrowPrepareError();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        int[] iArr = new int[trackSelectionArr.length];
        int[] iArr2 = new int[trackSelectionArr.length];
        for (int i = 0; i < trackSelectionArr.length; i++) {
            iArr[i] = sampleStreamArr[i] == null ? -1 : this.streamPeriodIndices.get(sampleStreamArr[i]).intValue();
            iArr2[i] = -1;
            if (trackSelectionArr[i] != null) {
                TrackGroup trackGroup = trackSelectionArr[i].getTrackGroup();
                int i2 = 0;
                while (true) {
                    MediaPeriod[] mediaPeriodArr = this.periods;
                    if (i2 >= mediaPeriodArr.length) {
                        break;
                    } else if (mediaPeriodArr[i2].getTrackGroups().indexOf(trackGroup) != -1) {
                        iArr2[i] = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        this.streamPeriodIndices.clear();
        SampleStream[] sampleStreamArr2 = new SampleStream[trackSelectionArr.length];
        SampleStream[] sampleStreamArr3 = new SampleStream[trackSelectionArr.length];
        TrackSelection[] trackSelectionArr2 = new TrackSelection[trackSelectionArr.length];
        ArrayList arrayList = new ArrayList(this.periods.length);
        long j2 = j;
        int i3 = 0;
        while (i3 < this.periods.length) {
            for (int i4 = 0; i4 < trackSelectionArr.length; i4++) {
                TrackSelection trackSelection = null;
                sampleStreamArr3[i4] = iArr[i4] == i3 ? sampleStreamArr[i4] : null;
                if (iArr2[i4] == i3) {
                    trackSelection = trackSelectionArr[i4];
                }
                trackSelectionArr2[i4] = trackSelection;
            }
            long selectTracks = this.periods[i3].selectTracks(trackSelectionArr2, zArr, sampleStreamArr3, zArr2, j2);
            if (i3 == 0) {
                j2 = selectTracks;
            } else if (selectTracks != j2) {
                throw new IllegalStateException("Children enabled at different positions");
            }
            boolean z = false;
            for (int i5 = 0; i5 < trackSelectionArr.length; i5++) {
                boolean z2 = true;
                if (iArr2[i5] == i3) {
                    Assertions.checkState(sampleStreamArr3[i5] != null);
                    sampleStreamArr2[i5] = sampleStreamArr3[i5];
                    this.streamPeriodIndices.put(sampleStreamArr3[i5], Integer.valueOf(i3));
                    z = true;
                } else if (iArr[i5] == i3) {
                    if (sampleStreamArr3[i5] != null) {
                        z2 = false;
                    }
                    Assertions.checkState(z2);
                }
            }
            if (z) {
                arrayList.add(this.periods[i3]);
            }
            i3++;
            arrayList = arrayList;
            trackSelectionArr2 = trackSelectionArr2;
        }
        System.arraycopy(sampleStreamArr2, 0, sampleStreamArr, 0, sampleStreamArr2.length);
        this.enabledPeriods = new MediaPeriod[arrayList.size()];
        arrayList.toArray(this.enabledPeriods);
        this.compositeSequenceableLoader = this.compositeSequenceableLoaderFactory.createCompositeSequenceableLoader(this.enabledPeriods);
        return j2;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (MediaPeriod mediaPeriod : this.enabledPeriods) {
            mediaPeriod.discardBuffer(j, z);
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

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        long readDiscontinuity = this.periods[0].readDiscontinuity();
        int i = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.periods;
            if (i >= mediaPeriodArr.length) {
                if (readDiscontinuity != C.TIME_UNSET) {
                    MediaPeriod[] mediaPeriodArr2 = this.enabledPeriods;
                    for (MediaPeriod mediaPeriod : mediaPeriodArr2) {
                        if (mediaPeriod != this.periods[0] && mediaPeriod.seekToUs(readDiscontinuity) != readDiscontinuity) {
                            throw new IllegalStateException("Children seeked to different positions");
                        }
                    }
                }
                return readDiscontinuity;
            } else if (mediaPeriodArr[i].readDiscontinuity() == C.TIME_UNSET) {
                i++;
            } else {
                throw new IllegalStateException("Child reported discontinuity");
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        long seekToUs = this.enabledPeriods[0].seekToUs(j);
        int i = 1;
        while (true) {
            MediaPeriod[] mediaPeriodArr = this.enabledPeriods;
            if (i >= mediaPeriodArr.length) {
                return seekToUs;
            }
            if (mediaPeriodArr[i].seekToUs(seekToUs) == seekToUs) {
                i++;
            } else {
                throw new IllegalStateException("Children seeked to different positions");
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return this.enabledPeriods[0].getAdjustedSeekPositionUs(j, seekParameters);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod.Callback
    public void onPrepared(MediaPeriod mediaPeriod) {
        int i = this.pendingChildPrepareCount - 1;
        this.pendingChildPrepareCount = i;
        if (i <= 0) {
            int i2 = 0;
            for (MediaPeriod mediaPeriod2 : this.periods) {
                i2 += mediaPeriod2.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i2];
            MediaPeriod[] mediaPeriodArr = this.periods;
            int length = mediaPeriodArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                TrackGroupArray trackGroups2 = mediaPeriodArr[i3].getTrackGroups();
                int i5 = trackGroups2.length;
                int i6 = i4;
                int i7 = 0;
                while (i7 < i5) {
                    trackGroupArr[i6] = trackGroups2.get(i7);
                    i7++;
                    i6++;
                }
                i3++;
                i4 = i6;
            }
            this.trackGroups = new TrackGroupArray(trackGroupArr);
            this.callback.onPrepared(this);
        }
    }

    public void onContinueLoadingRequested(MediaPeriod mediaPeriod) {
        if (this.trackGroups != null) {
            this.callback.onContinueLoadingRequested(this);
        }
    }
}
