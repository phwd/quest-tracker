package com.oculus.android.exoplayer2.source.ads;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.ForwardingTimeline;
import com.oculus.android.exoplayer2.util.Assertions;

/* access modifiers changed from: package-private */
public final class SinglePeriodAdTimeline extends ForwardingTimeline {
    private final AdPlaybackState adPlaybackState;

    public SinglePeriodAdTimeline(Timeline timeline, AdPlaybackState adPlaybackState2) {
        super(timeline);
        boolean z = false;
        Assertions.checkState(timeline.getPeriodCount() == 1);
        Assertions.checkState(timeline.getWindowCount() == 1 ? true : z);
        this.adPlaybackState = adPlaybackState2;
    }

    @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
    public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
        this.timeline.getPeriod(i, period, z);
        period.set(period.id, period.uid, period.windowIndex, period.durationUs, period.getPositionInWindowUs(), this.adPlaybackState);
        return period;
    }

    @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
    public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
        Timeline.Window window2 = super.getWindow(i, window, z, j);
        if (window2.durationUs == C.TIME_UNSET) {
            window2.durationUs = this.adPlaybackState.contentDurationUs;
        }
        return window2;
    }
}
