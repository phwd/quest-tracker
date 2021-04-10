package com.oculus.android.exoplayer2.trackselection;

import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.util.Assertions;

public final class FixedTrackSelection extends BaseTrackSelection {
    private final Object data;
    private final int reason;

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int getSelectedIndex() {
        return 0;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public void updateSelectedTrack(long j, long j2, long j3) {
    }

    public static final class Factory implements TrackSelection.Factory {
        private final Object data;
        private final int reason;

        public Factory() {
            this.reason = 0;
            this.data = null;
        }

        public Factory(int i, Object obj) {
            this.reason = i;
            this.data = obj;
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection.Factory
        public FixedTrackSelection createTrackSelection(TrackGroup trackGroup, int... iArr) {
            boolean z = true;
            if (iArr.length != 1) {
                z = false;
            }
            Assertions.checkArgument(z);
            return new FixedTrackSelection(trackGroup, iArr[0], this.reason, this.data);
        }
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i) {
        this(trackGroup, i, 0, null);
    }

    public FixedTrackSelection(TrackGroup trackGroup, int i, int i2, Object obj) {
        super(trackGroup, i);
        this.reason = i2;
        this.data = obj;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public int getSelectionReason() {
        return this.reason;
    }

    @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
    public Object getSelectionData() {
        return this.data;
    }
}
