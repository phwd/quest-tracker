package com.oculus.android.exoplayer2.source;

import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.upstream.Allocator;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class MergingMediaSource extends CompositeMediaSource<Integer> {
    private static final int PERIOD_COUNT_UNSET = -1;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private MediaSource.Listener listener;
    private final MediaSource[] mediaSources;
    private IllegalMergeException mergeError;
    private final ArrayList<MediaSource> pendingTimelineSources;
    private int periodCount;
    private Object primaryManifest;
    private Timeline primaryTimeline;

    public static final class IllegalMergeException extends IOException {
        public static final int REASON_PERIOD_COUNT_MISMATCH = 0;
        public final int reason;

        @Retention(RetentionPolicy.SOURCE)
        public @interface Reason {
        }

        public IllegalMergeException(int i) {
            this.reason = i;
        }
    }

    public MergingMediaSource(MediaSource... mediaSourceArr) {
        this(new DefaultCompositeSequenceableLoaderFactory(), mediaSourceArr);
    }

    public MergingMediaSource(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, MediaSource... mediaSourceArr) {
        this.mediaSources = mediaSourceArr;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.pendingTimelineSources = new ArrayList<>(Arrays.asList(mediaSourceArr));
        this.periodCount = -1;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        super.prepareSource(exoPlayer, z, listener2);
        this.listener = listener2;
        for (int i = 0; i < this.mediaSources.length; i++) {
            prepareChildSource(Integer.valueOf(i), this.mediaSources[i]);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        IllegalMergeException illegalMergeException = this.mergeError;
        if (illegalMergeException == null) {
            super.maybeThrowSourceInfoRefreshError();
            return;
        }
        throw illegalMergeException;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        MediaPeriod[] mediaPeriodArr = new MediaPeriod[this.mediaSources.length];
        for (int i = 0; i < mediaPeriodArr.length; i++) {
            mediaPeriodArr[i] = this.mediaSources[i].createPeriod(mediaPeriodId, allocator);
        }
        return new MergingMediaPeriod(this.compositeSequenceableLoaderFactory, mediaPeriodArr);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        MergingMediaPeriod mergingMediaPeriod = (MergingMediaPeriod) mediaPeriod;
        int i = 0;
        while (true) {
            MediaSource[] mediaSourceArr = this.mediaSources;
            if (i < mediaSourceArr.length) {
                mediaSourceArr[i].releasePeriod(mergingMediaPeriod.periods[i]);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void releaseSource() {
        super.releaseSource();
        this.listener = null;
        this.primaryTimeline = null;
        this.primaryManifest = null;
        this.periodCount = -1;
        this.mergeError = null;
        this.pendingTimelineSources.clear();
        Collections.addAll(this.pendingTimelineSources, this.mediaSources);
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(Integer num, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        if (this.mergeError == null) {
            this.mergeError = checkTimelineMerges(timeline);
        }
        if (this.mergeError == null) {
            this.pendingTimelineSources.remove(mediaSource);
            if (mediaSource == this.mediaSources[0]) {
                this.primaryTimeline = timeline;
                this.primaryManifest = obj;
            }
            if (this.pendingTimelineSources.isEmpty()) {
                this.listener.onSourceInfoRefreshed(this, this.primaryTimeline, this.primaryManifest);
            }
        }
    }

    private IllegalMergeException checkTimelineMerges(Timeline timeline) {
        if (this.periodCount == -1) {
            this.periodCount = timeline.getPeriodCount();
            return null;
        } else if (timeline.getPeriodCount() != this.periodCount) {
            return new IllegalMergeException(0);
        } else {
            return null;
        }
    }
}
