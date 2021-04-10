package com.oculus.android.exoplayer2.source;

import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.oculus.android.exoplayer2.extractor.ExtractorsFactory;
import com.oculus.android.exoplayer2.source.ExtractorMediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.ads.AdsMediaSource;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ExtractorMediaSource implements MediaSource, ExtractorMediaPeriod.Listener {
    public static final int DEFAULT_LOADING_CHECK_INTERVAL_BYTES = 1048576;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_LIVE = 6;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT_ON_DEMAND = 3;
    public static final int MIN_RETRY_COUNT_DEFAULT_FOR_MEDIA = -1;
    private final int continueLoadingCheckIntervalBytes;
    private final String customCacheKey;
    private final DataSource.Factory dataSourceFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final ExtractorsFactory extractorsFactory;
    private final int minLoadableRetryCount;
    private MediaSource.Listener sourceListener;
    private long timelineDurationUs;
    private boolean timelineIsSeekable;
    private final Uri uri;

    @Deprecated
    public interface EventListener {
        void onLoadError(IOException iOException);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private int continueLoadingCheckIntervalBytes = 1048576;
        @Nullable
        private String customCacheKey;
        private final DataSource.Factory dataSourceFactory;
        @Nullable
        private ExtractorsFactory extractorsFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount = -1;

        public Factory(DataSource.Factory factory) {
            this.dataSourceFactory = factory;
        }

        public Factory setExtractorsFactory(ExtractorsFactory extractorsFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.extractorsFactory = extractorsFactory2;
            return this;
        }

        public Factory setCustomCacheKey(String str) {
            Assertions.checkState(!this.isCreateCalled);
            this.customCacheKey = str;
            return this;
        }

        public Factory setMinLoadableRetryCount(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i;
            return this;
        }

        public Factory setContinueLoadingCheckIntervalBytes(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.continueLoadingCheckIntervalBytes = i;
            return this;
        }

        public ExtractorMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, (Handler) null, (MediaSourceEventListener) null);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public ExtractorMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.extractorsFactory == null) {
                this.extractorsFactory = new DefaultExtractorsFactory();
            }
            return new ExtractorMediaSource(uri, this.dataSourceFactory, this.extractorsFactory, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{3};
        }
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, Handler handler, EventListener eventListener) {
        this(uri2, factory, extractorsFactory2, handler, eventListener, null);
    }

    @Deprecated
    public ExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, Handler handler, EventListener eventListener, String str) {
        this(uri2, factory, extractorsFactory2, -1, handler, eventListener, str, 1048576);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated
    public ExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, int i, Handler handler, EventListener eventListener, String str, int i2) {
        this(uri2, factory, extractorsFactory2, i, handler, eventListener == null ? null : new EventListenerWrapper(eventListener), str, i2);
    }

    private ExtractorMediaSource(Uri uri2, DataSource.Factory factory, ExtractorsFactory extractorsFactory2, int i, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener, @Nullable String str, int i2) {
        this.uri = uri2;
        this.dataSourceFactory = factory;
        this.extractorsFactory = extractorsFactory2;
        this.minLoadableRetryCount = i;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler, mediaSourceEventListener);
        this.customCacheKey = str;
        this.continueLoadingCheckIntervalBytes = i2;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.sourceListener = listener;
        notifySourceInfoRefreshed(C.TIME_UNSET, false);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new ExtractorMediaPeriod(this.uri, this.dataSourceFactory.createDataSource(), this.extractorsFactory.createExtractors(), this.minLoadableRetryCount, this.eventDispatcher, this, allocator, this.customCacheKey, this.continueLoadingCheckIntervalBytes);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((ExtractorMediaPeriod) mediaPeriod).release();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        this.sourceListener = null;
    }

    @Override // com.oculus.android.exoplayer2.source.ExtractorMediaPeriod.Listener
    public void onSourceInfoRefreshed(long j, boolean z) {
        if (j == C.TIME_UNSET) {
            j = this.timelineDurationUs;
        }
        if (this.timelineDurationUs != j || this.timelineIsSeekable != z) {
            notifySourceInfoRefreshed(j, z);
        }
    }

    private void notifySourceInfoRefreshed(long j, boolean z) {
        this.timelineDurationUs = j;
        this.timelineIsSeekable = z;
        this.sourceListener.onSourceInfoRefreshed(this, new SinglePeriodTimeline(this.timelineDurationUs, this.timelineIsSeekable, false), null);
    }

    private static final class EventListenerWrapper implements MediaSourceEventListener {
        private final EventListener eventListener;

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onDownstreamFormatChanged(int i, Format format, int i2, Object obj, long j) {
        }

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCanceled(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        }

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadCompleted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5) {
        }

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadStarted(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3) {
        }

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onUpstreamDiscarded(int i, long j, long j2) {
        }

        public EventListenerWrapper(EventListener eventListener2) {
            this.eventListener = (EventListener) Assertions.checkNotNull(eventListener2);
        }

        @Override // com.oculus.android.exoplayer2.source.MediaSourceEventListener
        public void onLoadError(DataSpec dataSpec, int i, int i2, Format format, int i3, Object obj, long j, long j2, long j3, long j4, long j5, IOException iOException, boolean z) {
            this.eventListener.onLoadError(iOException);
        }
    }
}
