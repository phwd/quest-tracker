package com.oculus.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public final class ClippingMediaSource extends CompositeMediaSource<Void> {
    private IllegalClippingException clippingError;
    private final boolean enableInitialDiscontinuity;
    private final long endUs;
    private final ArrayList<ClippingMediaPeriod> mediaPeriods;
    private final MediaSource mediaSource;
    private MediaSource.Listener sourceListener;
    private final long startUs;

    public static final class IllegalClippingException extends IOException {
        public static final int REASON_INVALID_PERIOD_COUNT = 0;
        public static final int REASON_NOT_SEEKABLE_TO_START = 2;
        public static final int REASON_PERIOD_OFFSET_IN_WINDOW = 1;
        public static final int REASON_START_EXCEEDS_END = 3;
        public final int reason;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalClippingException(int i) {
            this.reason = i;
        }
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j, long j2) {
        this(mediaSource2, j, j2, true);
    }

    public ClippingMediaSource(MediaSource mediaSource2, long j, long j2, boolean z) {
        Assertions.checkArgument(j >= 0);
        this.mediaSource = (MediaSource) Assertions.checkNotNull(mediaSource2);
        this.startUs = j;
        this.endUs = j2;
        this.enableInitialDiscontinuity = z;
        this.mediaPeriods = new ArrayList<>();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        super.prepareSource(exoPlayer, z, listener);
        this.sourceListener = listener;
        prepareChildSource(null, this.mediaSource);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalClippingException illegalClippingException = this.clippingError;
        if (illegalClippingException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalClippingException;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        ClippingMediaPeriod clippingMediaPeriod = new ClippingMediaPeriod(this.mediaSource.createPeriod(mediaPeriodId, allocator), this.enableInitialDiscontinuity);
        this.mediaPeriods.add(clippingMediaPeriod);
        clippingMediaPeriod.setClipping(this.startUs, this.endUs);
        return clippingMediaPeriod;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        Assertions.checkState(this.mediaPeriods.remove(mediaPeriod));
        this.mediaSource.releasePeriod(((ClippingMediaPeriod) mediaPeriod).mediaPeriod);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void releaseSource() {
        super.releaseSource();
        this.clippingError = null;
        this.sourceListener = null;
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Void r7, MediaSource mediaSource2, Timeline timeline, @Nullable Object obj) {
        if (this.clippingError == null) {
            try {
                this.sourceListener.onSourceInfoRefreshed(this, new ClippingTimeline(timeline, this.startUs, this.endUs), obj);
                int size = this.mediaPeriods.size();
                for (int i = 0; i < size; i++) {
                    this.mediaPeriods.get(i).setClipping(this.startUs, this.endUs);
                }
            } catch (IllegalClippingException e) {
                this.clippingError = e;
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class ClippingTimeline extends ForwardingTimeline {
        private final long endUs;
        private final long startUs;

        public ClippingTimeline(Timeline timeline, long j, long j2) throws IllegalClippingException {
            super(timeline);
            if (timeline.getPeriodCount() != 1) {
                throw new IllegalClippingException(0);
            } else if (timeline.getPeriod(0, new Timeline.Period()).getPositionInWindowUs() == 0) {
                Timeline.Window window = timeline.getWindow(0, new Timeline.Window(), false);
                j2 = j2 == Long.MIN_VALUE ? window.durationUs : j2;
                if (window.durationUs != C.TIME_UNSET) {
                    j2 = j2 > window.durationUs ? window.durationUs : j2;
                    if (j != 0 && !window.isSeekable) {
                        throw new IllegalClippingException(2);
                    } else if (j > j2) {
                        throw new IllegalClippingException(3);
                    }
                }
                this.startUs = j;
                this.endUs = j2;
            } else {
                throw new IllegalClippingException(1);
            }
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            long j2;
            Timeline.Window window2 = this.timeline.getWindow(0, window, z, j);
            long j3 = this.endUs;
            window2.durationUs = j3 != C.TIME_UNSET ? j3 - this.startUs : -9223372036854775807L;
            if (window2.defaultPositionUs != C.TIME_UNSET) {
                window2.defaultPositionUs = Math.max(window2.defaultPositionUs, this.startUs);
                if (this.endUs == C.TIME_UNSET) {
                    j2 = window2.defaultPositionUs;
                } else {
                    j2 = Math.min(window2.defaultPositionUs, this.endUs);
                }
                window2.defaultPositionUs = j2;
                window2.defaultPositionUs -= this.startUs;
            }
            long usToMs = C.usToMs(this.startUs);
            if (window2.presentationStartTimeMs != C.TIME_UNSET) {
                window2.presentationStartTimeMs += usToMs;
            }
            if (window2.windowStartTimeMs != C.TIME_UNSET) {
                window2.windowStartTimeMs += usToMs;
            }
            return window2;
        }

        @Override // com.oculus.android.exoplayer2.Timeline, com.oculus.android.exoplayer2.source.ForwardingTimeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            Timeline.Period period2 = this.timeline.getPeriod(0, period, z);
            long j = this.endUs;
            long j2 = C.TIME_UNSET;
            if (j != C.TIME_UNSET) {
                j2 = j - this.startUs;
            }
            period2.durationUs = j2;
            return period2;
        }
    }
}
