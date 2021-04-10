package com.oculus.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.ShuffleOrder;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.util.Assertions;

public final class LoopingMediaSource extends CompositeMediaSource<Void> {
    private int childPeriodCount;
    private final MediaSource childSource;
    private MediaSource.Listener listener;
    private final int loopCount;

    public LoopingMediaSource(MediaSource mediaSource) {
        this(mediaSource, Integer.MAX_VALUE);
    }

    public LoopingMediaSource(MediaSource mediaSource, int i) {
        Assertions.checkArgument(i > 0);
        this.childSource = mediaSource;
        this.loopCount = i;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        super.prepareSource(exoPlayer, z, listener2);
        this.listener = listener2;
        prepareChildSource(null, this.childSource);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        if (this.loopCount != Integer.MAX_VALUE) {
            return this.childSource.createPeriod(mediaPeriodId.copyWithPeriodIndex(mediaPeriodId.periodIndex % this.childPeriodCount), allocator);
        }
        return this.childSource.createPeriod(mediaPeriodId, allocator);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        this.childSource.releasePeriod(mediaPeriod);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void releaseSource() {
        super.releaseSource();
        this.listener = null;
        this.childPeriodCount = 0;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Void r1, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        this.childPeriodCount = timeline.getPeriodCount();
        int i = this.loopCount;
        this.listener.onSourceInfoRefreshed(this, i != Integer.MAX_VALUE ? new LoopingTimeline(timeline, i) : new InfinitelyLoopingTimeline(timeline), obj);
    }

    /* access modifiers changed from: private */
    public static final class LoopingTimeline extends AbstractConcatenatedTimeline {
        private final int childPeriodCount;
        private final Timeline childTimeline;
        private final int childWindowCount;
        private final int loopCount;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LoopingTimeline(Timeline timeline, int i) {
            super(false, new ShuffleOrder.UnshuffledShuffleOrder(i));
            boolean z = false;
            this.childTimeline = timeline;
            this.childPeriodCount = timeline.getPeriodCount();
            this.childWindowCount = timeline.getWindowCount();
            this.loopCount = i;
            int i2 = this.childPeriodCount;
            if (i2 > 0) {
                Assertions.checkState(i <= Integer.MAX_VALUE / i2 ? true : z, "LoopingMediaSource contains too many periods");
            }
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getWindowCount() {
            return this.childWindowCount * this.loopCount;
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.childPeriodCount * this.loopCount;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByPeriodIndex(int i) {
            return i / this.childPeriodCount;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByWindowIndex(int i) {
            return i / this.childWindowCount;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getChildIndexByChildUid(Object obj) {
            if (!(obj instanceof Integer)) {
                return -1;
            }
            return ((Integer) obj).intValue();
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Timeline getTimelineByChildIndex(int i) {
            return this.childTimeline;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstPeriodIndexByChildIndex(int i) {
            return i * this.childPeriodCount;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public int getFirstWindowIndexByChildIndex(int i) {
            return i * this.childWindowCount;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.AbstractConcatenatedTimeline
        public Object getChildUidByChildIndex(int i) {
            return Integer.valueOf(i);
        }
    }

    /* access modifiers changed from: private */
    public static final class InfinitelyLoopingTimeline extends ForwardingTimeline {
        public InfinitelyLoopingTimeline(Timeline timeline) {
            super(timeline);
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public int getNextWindowIndex(int i, int i2, boolean z) {
            int nextWindowIndex = this.timeline.getNextWindowIndex(i, i2, z);
            return nextWindowIndex == -1 ? getFirstWindowIndex(z) : nextWindowIndex;
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public int getPreviousWindowIndex(int i, int i2, boolean z) {
            int previousWindowIndex = this.timeline.getPreviousWindowIndex(i, i2, z);
            return previousWindowIndex == -1 ? getLastWindowIndex(z) : previousWindowIndex;
        }
    }
}
