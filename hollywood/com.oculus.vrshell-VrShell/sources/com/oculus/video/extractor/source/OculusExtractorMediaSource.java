package com.oculus.video.extractor.source;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.extractor.ExtractorsFactory;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.SinglePeriodTimeline;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.video.extractor.source.OculusExtractorMediaPeriod;
import java.io.IOException;

public class OculusExtractorMediaSource implements MediaSource, OculusExtractorMediaPeriod.Listener {
    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_LIVE = 6;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_ON_DEMAND = 3;
    public static final int MIN_RETRY_COUNT_DEFAULT_FOR_MEDIA = -1;
    private Context context;
    private final int continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource.Factory dataSourceFactory;
    private final Handler eventHandler;
    private final EventListener eventListener;
    private final ExtractorsFactory extractorsFactory;
    private final int minLoadableRetryCount;
    private final Timeline.Period period;
    private MediaSource.Listener sourceListener;
    private long timelineDurationUs;
    private boolean timelineIsSeekable;
    private final Uri uri;

    public interface EventListener {
        void onLoadError(IOException iOException);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public OculusExtractorMediaSource(Context context2, Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, Handler handler, EventListener eventListener2) {
        this(uri2, factory, extractorsFactory2, handler, eventListener2, (String) null);
        this.context = context2;
    }

    public OculusExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, Handler handler, EventListener eventListener2) {
        this(uri2, factory, extractorsFactory2, handler, eventListener2, (String) null);
    }

    public OculusExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, Handler handler, EventListener eventListener2, String str) {
        this(uri2, factory, extractorsFactory2, -1, handler, eventListener2, str, 1048576);
    }

    public OculusExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, int i, Handler handler, EventListener eventListener2, String str, int i2) {
        this.context = null;
        this.uri = uri2;
        this.dataSourceFactory = factory;
        this.extractorsFactory = extractorsFactory2;
        this.minLoadableRetryCount = i;
        this.eventHandler = handler;
        this.eventListener = eventListener2;
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i2;
        this.period = new Timeline.Period();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.sourceListener = listener;
        notifySourceInfoRefreshed(C.TIME_UNSET, false);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new OculusExtractorMediaPeriod(this.context, this.uri, this.dataSourceFactory.createDataSource(), this.extractorsFactory.createExtractors(), this.minLoadableRetryCount, this.eventHandler, this.eventListener, this, allocator, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((OculusExtractorMediaPeriod) mediaPeriod).release();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        this.sourceListener = null;
    }

    @Override // com.oculus.video.extractor.source.OculusExtractorMediaPeriod.Listener
    public void onSourceInfoRefreshed(long j, boolean z) {
        if (j == C.TIME_UNSET) {
            j = this.timelineDurationUs;
        }
        if (this.timelineDurationUs != j || this.timelineIsSeekable != z) {
            if (this.timelineDurationUs == C.TIME_UNSET || j != C.TIME_UNSET) {
                notifySourceInfoRefreshed(j, z);
            }
        }
    }

    private void notifySourceInfoRefreshed(long j, boolean z) {
        this.timelineDurationUs = j;
        this.timelineIsSeekable = z;
        this.sourceListener.onSourceInfoRefreshed(this, new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false), null);
    }
}
