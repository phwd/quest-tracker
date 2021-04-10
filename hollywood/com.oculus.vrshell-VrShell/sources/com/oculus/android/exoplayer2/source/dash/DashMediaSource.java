package com.oculus.android.exoplayer2.source.dash;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.ExoPlayerLibraryInfo;
import com.oculus.android.exoplayer2.ParserException;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.ads.AdsMediaSource;
import com.oculus.android.exoplayer2.source.dash.DashChunkSource;
import com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.oculus.android.exoplayer2.source.dash.manifest.Period;
import com.oculus.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.Loader;
import com.oculus.android.exoplayer2.upstream.LoaderErrorThrower;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.Util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DashMediaSource implements MediaSource {
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_FIXED_MS = 30000;
    public static final long DEFAULT_LIVE_PRESENTATION_DELAY_PREFER_MANIFEST_MS = -1;
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private static final long MIN_LIVE_DEFAULT_START_POSITION_US = 5000000;
    private static final int NOTIFY_MANIFEST_INTERVAL_MS = 5000;
    private static final String TAG = "DashMediaSource";
    private final DashChunkSource.Factory chunkSourceFactory;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private DataSource dataSource;
    private boolean dynamicMediaPresentationEnded;
    private long elapsedRealtimeOffsetMs;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private long expiredManifestPublishTimeUs;
    private int firstPeriodId;
    private Handler handler;
    private Uri initialManifestUri;
    private final long livePresentationDelayMs;
    private Loader loader;
    private DashManifest manifest;
    private final ManifestCallback manifestCallback;
    private final DataSource.Factory manifestDataSourceFactory;
    private IOException manifestFatalError;
    private long manifestLoadEndTimestampMs;
    private final LoaderErrorThrower manifestLoadErrorThrower;
    private boolean manifestLoadPending;
    private long manifestLoadStartTimestampMs;
    private final ParsingLoadable.Parser<? extends DashManifest> manifestParser;
    private Uri manifestUri;
    private final Object manifestUriLock;
    private final int minLoadableRetryCount;
    private final SparseArray<DashMediaPeriod> periodsById;
    private final PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback;
    private final Runnable refreshManifestRunnable;
    private final boolean sideloadedManifest;
    private final Runnable simulateManifestRefreshRunnable;
    private MediaSource.Listener sourceListener;
    private int staleManifestReloadAttempt;

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.dash");
    }

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private final DashChunkSource.Factory chunkSourceFactory;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        private boolean isCreateCalled;
        private long livePresentationDelayMs = -1;
        @Nullable
        private final DataSource.Factory manifestDataSourceFactory;
        @Nullable
        private ParsingLoadable.Parser<? extends DashManifest> manifestParser;
        private int minLoadableRetryCount = 3;

        public Factory(DashChunkSource.Factory factory, @Nullable DataSource.Factory factory2) {
            this.chunkSourceFactory = (DashChunkSource.Factory) Assertions.checkNotNull(factory);
            this.manifestDataSourceFactory = factory2;
        }

        public Factory setMinLoadableRetryCount(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i;
            return this;
        }

        public Factory setLivePresentationDelayMs(long j) {
            Assertions.checkState(!this.isCreateCalled);
            this.livePresentationDelayMs = j;
            return this;
        }

        public Factory setManifestParser(ParsingLoadable.Parser<? extends DashManifest> parser) {
            Assertions.checkState(!this.isCreateCalled);
            this.manifestParser = (ParsingLoadable.Parser) Assertions.checkNotNull(parser);
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory2);
            return this;
        }

        public DashMediaSource createMediaSource(DashManifest dashManifest, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            Assertions.checkArgument(!dashManifest.dynamic);
            this.isCreateCalled = true;
            return new DashMediaSource(dashManifest, null, null, null, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, handler, mediaSourceEventListener);
        }

        public DashMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, (Handler) null, (MediaSourceEventListener) null);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public DashMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.manifestParser == null) {
                this.manifestParser = new DashManifestParser();
            }
            return new DashMediaSource(null, (Uri) Assertions.checkNotNull(uri), this.manifestDataSourceFactory, this.manifestParser, this.chunkSourceFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, this.livePresentationDelayMs, handler, mediaSourceEventListener);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{0};
        }
    }

    @Deprecated
    public DashMediaSource(DashManifest dashManifest, DashChunkSource.Factory factory, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(dashManifest, factory, 3, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(DashManifest dashManifest, DashChunkSource.Factory factory, int i, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(dashManifest, null, null, null, factory, new DefaultCompositeSequenceableLoaderFactory(), i, -1, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(Uri uri, DataSource.Factory factory, DashChunkSource.Factory factory2, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, factory2, 3, -1, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(Uri uri, DataSource.Factory factory, DashChunkSource.Factory factory2, int i, long j, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, new DashManifestParser(), factory2, i, j, handler2, mediaSourceEventListener);
    }

    @Deprecated
    public DashMediaSource(Uri uri, DataSource.Factory factory, ParsingLoadable.Parser<? extends DashManifest> parser, DashChunkSource.Factory factory2, int i, long j, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this(null, uri, factory, parser, factory2, new DefaultCompositeSequenceableLoaderFactory(), i, j, handler2, mediaSourceEventListener);
    }

    private DashMediaSource(DashManifest dashManifest, Uri uri, DataSource.Factory factory, ParsingLoadable.Parser<? extends DashManifest> parser, DashChunkSource.Factory factory2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, int i, long j, Handler handler2, MediaSourceEventListener mediaSourceEventListener) {
        this.initialManifestUri = uri;
        this.manifest = dashManifest;
        this.manifestUri = uri;
        this.manifestDataSourceFactory = factory;
        this.manifestParser = parser;
        this.chunkSourceFactory = factory2;
        this.minLoadableRetryCount = i;
        this.livePresentationDelayMs = j;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.sideloadedManifest = dashManifest != null;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler2, mediaSourceEventListener);
        this.manifestUriLock = new Object();
        this.periodsById = new SparseArray<>();
        this.playerEmsgCallback = new DefaultPlayerEmsgCallback();
        this.expiredManifestPublishTimeUs = C.TIME_UNSET;
        if (this.sideloadedManifest) {
            Assertions.checkState(!dashManifest.dynamic);
            this.manifestCallback = null;
            this.refreshManifestRunnable = null;
            this.simulateManifestRefreshRunnable = null;
            this.manifestLoadErrorThrower = new LoaderErrorThrower.Dummy();
            return;
        }
        this.manifestCallback = new ManifestCallback();
        this.manifestLoadErrorThrower = new ManifestLoadErrorThrower();
        this.refreshManifestRunnable = new Runnable() {
            /* class com.oculus.android.exoplayer2.source.dash.DashMediaSource.AnonymousClass1 */

            public void run() {
                DashMediaSource.this.startLoadingManifest();
            }
        };
        this.simulateManifestRefreshRunnable = new Runnable() {
            /* class com.oculus.android.exoplayer2.source.dash.DashMediaSource.AnonymousClass2 */

            public void run() {
                DashMediaSource.this.processManifest(false);
            }
        };
    }

    public void replaceManifestUri(Uri uri) {
        synchronized (this.manifestUriLock) {
            this.manifestUri = uri;
            this.initialManifestUri = uri;
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.sourceListener = listener;
        if (this.sideloadedManifest) {
            processManifest(false);
            return;
        }
        this.dataSource = this.manifestDataSourceFactory.createDataSource();
        this.loader = new Loader("Loader:DashMediaSource");
        this.handler = new Handler();
        startLoadingManifest();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.manifestLoadErrorThrower.maybeThrowError();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        int i = mediaPeriodId.periodIndex;
        DashMediaPeriod dashMediaPeriod = new DashMediaPeriod(this.firstPeriodId + i, this.manifest, i, this.chunkSourceFactory, this.minLoadableRetryCount, this.eventDispatcher.copyWithMediaTimeOffsetMs(this.manifest.getPeriod(i).startMs), this.elapsedRealtimeOffsetMs, this.manifestLoadErrorThrower, allocator, this.compositeSequenceableLoaderFactory, this.playerEmsgCallback);
        this.periodsById.put(dashMediaPeriod.id, dashMediaPeriod);
        return dashMediaPeriod;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        DashMediaPeriod dashMediaPeriod = (DashMediaPeriod) mediaPeriod;
        dashMediaPeriod.release();
        this.periodsById.remove(dashMediaPeriod.id);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        this.manifestLoadPending = false;
        this.dataSource = null;
        Loader loader2 = this.loader;
        if (loader2 != null) {
            loader2.release();
            this.loader = null;
        }
        this.manifestLoadStartTimestampMs = 0;
        this.manifestLoadEndTimestampMs = 0;
        this.manifest = this.sideloadedManifest ? this.manifest : null;
        this.manifestUri = this.initialManifestUri;
        this.manifestFatalError = null;
        Handler handler2 = this.handler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.handler = null;
        }
        this.elapsedRealtimeOffsetMs = 0;
        this.staleManifestReloadAttempt = 0;
        this.expiredManifestPublishTimeUs = C.TIME_UNSET;
        this.dynamicMediaPresentationEnded = false;
        this.firstPeriodId = 0;
        this.periodsById.clear();
    }

    /* access modifiers changed from: package-private */
    public void onDashManifestRefreshRequested() {
        this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
        startLoadingManifest();
    }

    /* access modifiers changed from: package-private */
    public void onDashLiveMediaPresentationEndSignalEncountered() {
        this.dynamicMediaPresentationEnded = true;
    }

    /* access modifiers changed from: package-private */
    public void onDashManifestPublishTimeExpired(long j) {
        long j2 = this.expiredManifestPublishTimeUs;
        if (j2 == C.TIME_UNSET || j2 < j) {
            this.expiredManifestPublishTimeUs = j;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onManifestLoadCompleted(com.oculus.android.exoplayer2.upstream.ParsingLoadable<com.oculus.android.exoplayer2.source.dash.manifest.DashManifest> r11, long r12, long r14) {
        /*
        // Method dump skipped, instructions count: 242
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.source.dash.DashMediaSource.onManifestLoadCompleted(com.oculus.android.exoplayer2.upstream.ParsingLoadable, long, long):void");
    }

    /* access modifiers changed from: package-private */
    public int onManifestLoadError(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof ParserException;
        this.eventDispatcher.loadError(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded(), iOException, z);
        return z ? 3 : 0;
    }

    /* access modifiers changed from: package-private */
    public void onUtcTimestampLoadCompleted(ParsingLoadable<Long> parsingLoadable, long j, long j2) {
        this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded());
        onUtcTimestampResolved(parsingLoadable.getResult().longValue() - j);
    }

    /* access modifiers changed from: package-private */
    public int onUtcTimestampLoadError(ParsingLoadable<Long> parsingLoadable, long j, long j2, IOException iOException) {
        this.eventDispatcher.loadError(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded(), iOException, true);
        onUtcTimestampResolutionError(iOException);
        return 2;
    }

    /* access modifiers changed from: package-private */
    public void onLoadCanceled(ParsingLoadable<?> parsingLoadable, long j, long j2) {
        this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, parsingLoadable.type, j, j2, parsingLoadable.bytesLoaded());
    }

    private void resolveUtcTimingElement(UtcTimingElement utcTimingElement) {
        String str = utcTimingElement.schemeIdUri;
        if (Util.areEqual(str, "urn:mpeg:dash:utc:direct:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:direct:2012")) {
            resolveUtcTimingElementDirect(utcTimingElement);
        } else if (Util.areEqual(str, "urn:mpeg:dash:utc:http-iso:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:http-iso:2012")) {
            resolveUtcTimingElementHttp(utcTimingElement, new Iso8601Parser());
        } else if (Util.areEqual(str, "urn:mpeg:dash:utc:http-xsdate:2014") || Util.areEqual(str, "urn:mpeg:dash:utc:http-xsdate:2012")) {
            resolveUtcTimingElementHttp(utcTimingElement, new XsDateTimeParser());
        } else {
            onUtcTimestampResolutionError(new IOException("Unsupported UTC timing scheme"));
        }
    }

    private void resolveUtcTimingElementDirect(UtcTimingElement utcTimingElement) {
        try {
            onUtcTimestampResolved(Util.parseXsDateTime(utcTimingElement.value) - this.manifestLoadEndTimestampMs);
        } catch (ParserException e) {
            onUtcTimestampResolutionError(e);
        }
    }

    private void resolveUtcTimingElementHttp(UtcTimingElement utcTimingElement, ParsingLoadable.Parser<Long> parser) {
        startLoading(new ParsingLoadable(this.dataSource, Uri.parse(utcTimingElement.value), 5, parser), new UtcTimestampCallback(), 1);
    }

    private void onUtcTimestampResolved(long j) {
        this.elapsedRealtimeOffsetMs = j;
        processManifest(true);
    }

    private void onUtcTimestampResolutionError(IOException iOException) {
        Log.e(TAG, "Failed to resolve UtcTiming element.", iOException);
        processManifest(true);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void processManifest(boolean z) {
        boolean z2;
        long j;
        long j2;
        for (int i = 0; i < this.periodsById.size(); i++) {
            int keyAt = this.periodsById.keyAt(i);
            if (keyAt >= this.firstPeriodId) {
                this.periodsById.valueAt(i).updateManifest(this.manifest, keyAt - this.firstPeriodId);
            }
        }
        int periodCount = this.manifest.getPeriodCount() - 1;
        PeriodSeekInfo createPeriodSeekInfo = PeriodSeekInfo.createPeriodSeekInfo(this.manifest.getPeriod(0), this.manifest.getPeriodDurationUs(0));
        PeriodSeekInfo createPeriodSeekInfo2 = PeriodSeekInfo.createPeriodSeekInfo(this.manifest.getPeriod(periodCount), this.manifest.getPeriodDurationUs(periodCount));
        long j3 = createPeriodSeekInfo.availableStartTimeUs;
        long j4 = createPeriodSeekInfo2.availableEndTimeUs;
        if (!this.manifest.dynamic || createPeriodSeekInfo2.isIndexExplicit) {
            z2 = false;
        } else {
            j4 = Math.min((getNowUnixTimeUs() - C.msToUs(this.manifest.availabilityStartTimeMs)) - C.msToUs(this.manifest.getPeriod(periodCount).startMs), j4);
            if (this.manifest.timeShiftBufferDepthMs != C.TIME_UNSET) {
                long msToUs = j4 - C.msToUs(this.manifest.timeShiftBufferDepthMs);
                while (msToUs < 0 && periodCount > 0) {
                    periodCount--;
                    msToUs += this.manifest.getPeriodDurationUs(periodCount);
                }
                if (periodCount == 0) {
                    j2 = Math.max(j3, msToUs);
                } else {
                    j2 = this.manifest.getPeriodDurationUs(0);
                }
                j3 = j2;
            }
            z2 = true;
        }
        long j5 = j4 - j3;
        for (int i2 = 0; i2 < this.manifest.getPeriodCount() - 1; i2++) {
            j5 += this.manifest.getPeriodDurationUs(i2);
        }
        if (this.manifest.dynamic) {
            long j6 = this.livePresentationDelayMs;
            if (j6 == -1) {
                j6 = this.manifest.suggestedPresentationDelayMs != C.TIME_UNSET ? this.manifest.suggestedPresentationDelayMs : 30000;
            }
            long msToUs2 = j5 - C.msToUs(j6);
            if (msToUs2 < MIN_LIVE_DEFAULT_START_POSITION_US) {
                msToUs2 = Math.min((long) MIN_LIVE_DEFAULT_START_POSITION_US, j5 / 2);
            }
            j = msToUs2;
        } else {
            j = 0;
        }
        this.sourceListener.onSourceInfoRefreshed(this, new DashTimeline(this.manifest.availabilityStartTimeMs, this.manifest.availabilityStartTimeMs + this.manifest.getPeriod(0).startMs + C.usToMs(j3), this.firstPeriodId, j3, j5, j, this.manifest), this.manifest);
        if (!this.sideloadedManifest) {
            this.handler.removeCallbacks(this.simulateManifestRefreshRunnable);
            if (z2) {
                this.handler.postDelayed(this.simulateManifestRefreshRunnable, 5000);
            }
            if (this.manifestLoadPending) {
                startLoadingManifest();
            } else if (z && this.manifest.dynamic) {
                long j7 = this.manifest.minUpdatePeriodMs;
                if (j7 == 0) {
                    j7 = 5000;
                }
                scheduleManifestRefresh(Math.max(0L, (this.manifestLoadStartTimestampMs + j7) - SystemClock.elapsedRealtime()));
            }
        }
    }

    private void scheduleManifestRefresh(long j) {
        this.handler.postDelayed(this.refreshManifestRunnable, j);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void startLoadingManifest() {
        Uri uri;
        this.handler.removeCallbacks(this.refreshManifestRunnable);
        if (this.loader.isLoading()) {
            this.manifestLoadPending = true;
            return;
        }
        synchronized (this.manifestUriLock) {
            uri = this.manifestUri;
        }
        this.manifestLoadPending = false;
        startLoading(new ParsingLoadable(this.dataSource, uri, 4, this.manifestParser), this.manifestCallback, this.minLoadableRetryCount);
    }

    private long getManifestLoadRetryDelayMillis() {
        return (long) Math.min((this.staleManifestReloadAttempt - 1) * 1000, 5000);
    }

    private <T> void startLoading(ParsingLoadable<T> parsingLoadable, Loader.Callback<ParsingLoadable<T>> callback, int i) {
        this.eventDispatcher.loadStarted(parsingLoadable.dataSpec, parsingLoadable.type, this.loader.startLoading(parsingLoadable, callback, i));
    }

    private long getNowUnixTimeUs() {
        if (this.elapsedRealtimeOffsetMs != 0) {
            return C.msToUs(SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs);
        }
        return C.msToUs(System.currentTimeMillis());
    }

    /* access modifiers changed from: private */
    public static final class PeriodSeekInfo {
        public final long availableEndTimeUs;
        public final long availableStartTimeUs;
        public final boolean isIndexExplicit;

        public static PeriodSeekInfo createPeriodSeekInfo(Period period, long j) {
            int i;
            int size = period.adaptationSets.size();
            int i2 = 0;
            boolean z = false;
            long j2 = Long.MAX_VALUE;
            long j3 = 0;
            int i3 = 0;
            boolean z2 = false;
            while (i3 < size) {
                DashSegmentIndex index = period.adaptationSets.get(i3).representations.get(i2).getIndex();
                if (index == null) {
                    return new PeriodSeekInfo(true, 0, j);
                }
                z |= index.isExplicit();
                int segmentCount = index.getSegmentCount(j);
                if (segmentCount == 0) {
                    z2 = true;
                    i = i3;
                    j3 = 0;
                    j2 = 0;
                } else if (!z2) {
                    long firstSegmentNum = index.getFirstSegmentNum();
                    i = i3;
                    long max = Math.max(j3, index.getTimeUs(firstSegmentNum));
                    if (segmentCount != -1) {
                        long j4 = (firstSegmentNum + ((long) segmentCount)) - 1;
                        j2 = Math.min(j2, index.getTimeUs(j4) + index.getDurationUs(j4, j));
                    }
                    j3 = max;
                } else {
                    i = i3;
                }
                i3 = i + 1;
                i2 = 0;
            }
            return new PeriodSeekInfo(z, j3, j2);
        }

        private PeriodSeekInfo(boolean z, long j, long j2) {
            this.isIndexExplicit = z;
            this.availableStartTimeUs = j;
            this.availableEndTimeUs = j2;
        }
    }

    /* access modifiers changed from: private */
    public static final class DashTimeline extends Timeline {
        private final int firstPeriodId;
        private final DashManifest manifest;
        private final long offsetInFirstPeriodUs;
        private final long presentationStartTimeMs;
        private final long windowDefaultStartPositionUs;
        private final long windowDurationUs;
        private final long windowStartTimeMs;

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getWindowCount() {
            return 1;
        }

        public DashTimeline(long j, long j2, int i, long j3, long j4, long j5, DashManifest dashManifest) {
            this.presentationStartTimeMs = j;
            this.windowStartTimeMs = j2;
            this.firstPeriodId = i;
            this.offsetInFirstPeriodUs = j3;
            this.windowDurationUs = j4;
            this.windowDefaultStartPositionUs = j5;
            this.manifest = dashManifest;
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getPeriodCount() {
            return this.manifest.getPeriodCount();
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            Assertions.checkIndex(i, 0, this.manifest.getPeriodCount());
            Integer num = null;
            String str = z ? this.manifest.getPeriod(i).id : null;
            if (z) {
                num = Integer.valueOf(this.firstPeriodId + Assertions.checkIndex(i, 0, this.manifest.getPeriodCount()));
            }
            return period.set(str, num, 0, this.manifest.getPeriodDurationUs(i), C.msToUs(this.manifest.getPeriod(i).startMs - this.manifest.getPeriod(0).startMs) - this.offsetInFirstPeriodUs);
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public Timeline.Window getWindow(int i, Timeline.Window window, boolean z, long j) {
            Assertions.checkIndex(i, 0, 1);
            return window.set(null, this.presentationStartTimeMs, this.windowStartTimeMs, true, this.manifest.dynamic, getAdjustedWindowDefaultStartPositionUs(j), this.windowDurationUs, 0, this.manifest.getPeriodCount() - 1, this.offsetInFirstPeriodUs);
        }

        @Override // com.oculus.android.exoplayer2.Timeline
        public int getIndexOfPeriod(Object obj) {
            int intValue;
            int i;
            if ((obj instanceof Integer) && (intValue = ((Integer) obj).intValue()) >= (i = this.firstPeriodId) && intValue < i + getPeriodCount()) {
                return intValue - this.firstPeriodId;
            }
            return -1;
        }

        private long getAdjustedWindowDefaultStartPositionUs(long j) {
            DashSegmentIndex index;
            long j2 = this.windowDefaultStartPositionUs;
            if (!this.manifest.dynamic) {
                return j2;
            }
            if (j > 0) {
                j2 += j;
                if (j2 > this.windowDurationUs) {
                    return C.TIME_UNSET;
                }
            }
            long periodDurationUs = this.manifest.getPeriodDurationUs(0);
            long j3 = this.offsetInFirstPeriodUs + j2;
            int i = 0;
            while (i < this.manifest.getPeriodCount() - 1 && j3 >= periodDurationUs) {
                j3 -= periodDurationUs;
                i++;
                periodDurationUs = this.manifest.getPeriodDurationUs(i);
            }
            Period period = this.manifest.getPeriod(i);
            int adaptationSetIndex = period.getAdaptationSetIndex(2);
            return (adaptationSetIndex == -1 || (index = period.adaptationSets.get(adaptationSetIndex).representations.get(0).getIndex()) == null || index.getSegmentCount(periodDurationUs) == 0) ? j2 : (j2 + index.getTimeUs(index.getSegmentNum(j3, periodDurationUs))) - j3;
        }
    }

    private final class DefaultPlayerEmsgCallback implements PlayerEmsgHandler.PlayerEmsgCallback {
        private DefaultPlayerEmsgCallback() {
        }

        @Override // com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerEmsgCallback
        public void onDashManifestRefreshRequested() {
            DashMediaSource.this.onDashManifestRefreshRequested();
        }

        @Override // com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerEmsgCallback
        public void onDashManifestPublishTimeExpired(long j) {
            DashMediaSource.this.onDashManifestPublishTimeExpired(j);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.PlayerEmsgHandler.PlayerEmsgCallback
        public void onDashLiveMediaPresentationEndSignalEncountered() {
            DashMediaSource.this.onDashLiveMediaPresentationEndSignalEncountered();
        }
    }

    /* access modifiers changed from: private */
    public final class ManifestCallback implements Loader.Callback<ParsingLoadable<DashManifest>> {
        private ManifestCallback() {
        }

        public void onLoadCompleted(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2) {
            DashMediaSource.this.onManifestLoadCompleted(parsingLoadable, j, j2);
        }

        public void onLoadCanceled(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, boolean z) {
            DashMediaSource.this.onLoadCanceled(parsingLoadable, j, j2);
        }

        public int onLoadError(ParsingLoadable<DashManifest> parsingLoadable, long j, long j2, IOException iOException) {
            return DashMediaSource.this.onManifestLoadError(parsingLoadable, j, j2, iOException);
        }
    }

    /* access modifiers changed from: private */
    public final class UtcTimestampCallback implements Loader.Callback<ParsingLoadable<Long>> {
        private UtcTimestampCallback() {
        }

        public void onLoadCompleted(ParsingLoadable<Long> parsingLoadable, long j, long j2) {
            DashMediaSource.this.onUtcTimestampLoadCompleted(parsingLoadable, j, j2);
        }

        public void onLoadCanceled(ParsingLoadable<Long> parsingLoadable, long j, long j2, boolean z) {
            DashMediaSource.this.onLoadCanceled(parsingLoadable, j, j2);
        }

        public int onLoadError(ParsingLoadable<Long> parsingLoadable, long j, long j2, IOException iOException) {
            return DashMediaSource.this.onUtcTimestampLoadError(parsingLoadable, j, j2, iOException);
        }
    }

    /* access modifiers changed from: private */
    public static final class XsDateTimeParser implements ParsingLoadable.Parser<Long> {
        private XsDateTimeParser() {
        }

        @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser
        public Long parse(Uri uri, InputStream inputStream) throws IOException {
            return Long.valueOf(Util.parseXsDateTime(new BufferedReader(new InputStreamReader(inputStream)).readLine()));
        }
    }

    /* access modifiers changed from: package-private */
    public static final class Iso8601Parser implements ParsingLoadable.Parser<Long> {
        private static final Pattern TIMESTAMP_WITH_TIMEZONE_PATTERN = Pattern.compile("(.+?)(Z|((\\+|-|−)(\\d\\d)(:?(\\d\\d))?))");

        Iso8601Parser() {
        }

        @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser
        public Long parse(Uri uri, InputStream inputStream) throws IOException {
            String readLine = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(C.UTF8_NAME))).readLine();
            try {
                Matcher matcher = TIMESTAMP_WITH_TIMEZONE_PATTERN.matcher(readLine);
                if (matcher.matches()) {
                    String group = matcher.group(1);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    long time = simpleDateFormat.parse(group).getTime();
                    if (!"Z".equals(matcher.group(2))) {
                        long j = "+".equals(matcher.group(4)) ? 1 : -1;
                        long parseLong = Long.parseLong(matcher.group(5));
                        String group2 = matcher.group(7);
                        time -= j * ((((parseLong * 60) + (TextUtils.isEmpty(group2) ? 0 : Long.parseLong(group2))) * 60) * 1000);
                    }
                    return Long.valueOf(time);
                }
                throw new ParserException("Couldn't parse timestamp: " + readLine);
            } catch (ParseException e) {
                throw new ParserException(e);
            }
        }
    }

    final class ManifestLoadErrorThrower implements LoaderErrorThrower {
        ManifestLoadErrorThrower() {
        }

        @Override // com.oculus.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError() throws IOException {
            DashMediaSource.this.loader.maybeThrowError();
            maybeThrowManifestError();
        }

        @Override // com.oculus.android.exoplayer2.upstream.LoaderErrorThrower
        public void maybeThrowError(int i) throws IOException {
            DashMediaSource.this.loader.maybeThrowError(i);
            maybeThrowManifestError();
        }

        private void maybeThrowManifestError() throws IOException {
            if (DashMediaSource.this.manifestFatalError != null) {
                throw DashMediaSource.this.manifestFatalError;
            }
        }
    }
}
