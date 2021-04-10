package com.oculus.android.exoplayer2.source.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.Timeline;
import com.oculus.android.exoplayer2.source.CompositeMediaSource;
import com.oculus.android.exoplayer2.source.DeferredMediaPeriod;
import com.oculus.android.exoplayer2.source.ExtractorMediaSource;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.ads.AdsLoader;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AdsMediaSource extends CompositeMediaSource<MediaSource.MediaPeriodId> {
    private static final String TAG = "AdsMediaSource";
    private long[][] adDurationsUs;
    private MediaSource[][] adGroupMediaSources;
    private final MediaSourceFactory adMediaSourceFactory;
    private AdPlaybackState adPlaybackState;
    private final ViewGroup adUiViewGroup;
    private final AdsLoader adsLoader;
    private ComponentListener componentListener;
    private Object contentManifest;
    private final MediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Map<MediaSource, List<DeferredMediaPeriod>> deferredMediaPeriodByAdMediaSource;
    @Nullable
    private final Handler eventHandler;
    @Nullable
    private final EventListener eventListener;
    private MediaSource.Listener listener;
    private final Handler mainHandler;
    private final Timeline.Period period;

    public interface EventListener extends MediaSourceEventListener {
        void onAdClicked();

        void onAdLoadError(IOException iOException);

        void onAdTapped();

        void onInternalAdLoadError(RuntimeException runtimeException);
    }

    public interface MediaSourceFactory {
        MediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener);

        int[] getSupportedTypes();
    }

    public AdsMediaSource(MediaSource mediaSource, DataSource.Factory factory, AdsLoader adsLoader2, ViewGroup viewGroup) {
        this(mediaSource, factory, adsLoader2, viewGroup, (Handler) null, (EventListener) null);
    }

    public AdsMediaSource(MediaSource mediaSource, DataSource.Factory factory, AdsLoader adsLoader2, ViewGroup viewGroup, @Nullable Handler handler, @Nullable EventListener eventListener2) {
        this(mediaSource, new ExtractorMediaSource.Factory(factory), adsLoader2, viewGroup, handler, eventListener2);
    }

    public AdsMediaSource(MediaSource mediaSource, MediaSourceFactory mediaSourceFactory, AdsLoader adsLoader2, ViewGroup viewGroup, @Nullable Handler handler, @Nullable EventListener eventListener2) {
        this.contentMediaSource = mediaSource;
        this.adMediaSourceFactory = mediaSourceFactory;
        this.adsLoader = adsLoader2;
        this.adUiViewGroup = viewGroup;
        this.eventHandler = handler;
        this.eventListener = eventListener2;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.deferredMediaPeriodByAdMediaSource = new HashMap();
        this.period = new Timeline.Period();
        this.adGroupMediaSources = new MediaSource[0][];
        this.adDurationsUs = new long[0][];
        adsLoader2.setSupportedContentTypes(mediaSourceFactory.getSupportedTypes());
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void prepareSource(final ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener2) {
        super.prepareSource(exoPlayer, z, listener2);
        Assertions.checkArgument(z);
        final ComponentListener componentListener2 = new ComponentListener();
        this.listener = listener2;
        this.componentListener = componentListener2;
        prepareChildSource(new MediaSource.MediaPeriodId(0), this.contentMediaSource);
        this.mainHandler.post(new Runnable() {
            /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.AnonymousClass1 */

            public void run() {
                AdsMediaSource.this.adsLoader.attachPlayer(exoPlayer, componentListener2, AdsMediaSource.this.adUiViewGroup);
            }
        });
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        if (this.adPlaybackState.adGroupCount <= 0 || !mediaPeriodId.isAd()) {
            DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(this.contentMediaSource, mediaPeriodId, allocator);
            deferredMediaPeriod.createPeriod();
            return deferredMediaPeriod;
        }
        int i = mediaPeriodId.adGroupIndex;
        int i2 = mediaPeriodId.adIndexInAdGroup;
        if (this.adGroupMediaSources[i].length <= i2) {
            MediaSource createMediaSource = this.adMediaSourceFactory.createMediaSource(this.adPlaybackState.adGroups[mediaPeriodId.adGroupIndex].uris[mediaPeriodId.adIndexInAdGroup], this.eventHandler, this.eventListener);
            int length = this.adGroupMediaSources[mediaPeriodId.adGroupIndex].length;
            if (i2 >= length) {
                int i3 = i2 + 1;
                MediaSource[][] mediaSourceArr = this.adGroupMediaSources;
                mediaSourceArr[i] = (MediaSource[]) Arrays.copyOf(mediaSourceArr[i], i3);
                long[][] jArr = this.adDurationsUs;
                jArr[i] = Arrays.copyOf(jArr[i], i3);
                Arrays.fill(this.adDurationsUs[i], length, i3, (long) C.TIME_UNSET);
            }
            this.adGroupMediaSources[i][i2] = createMediaSource;
            this.deferredMediaPeriodByAdMediaSource.put(createMediaSource, new ArrayList());
            prepareChildSource(mediaPeriodId, createMediaSource);
        }
        MediaSource mediaSource = this.adGroupMediaSources[i][i2];
        DeferredMediaPeriod deferredMediaPeriod2 = new DeferredMediaPeriod(mediaSource, new MediaSource.MediaPeriodId(0, mediaPeriodId.windowSequenceNumber), allocator);
        deferredMediaPeriod2.setPrepareErrorListener(new AdPrepareErrorListener(i, i2));
        List<DeferredMediaPeriod> list = this.deferredMediaPeriodByAdMediaSource.get(mediaSource);
        if (list == null) {
            deferredMediaPeriod2.createPeriod();
        } else {
            list.add(deferredMediaPeriod2);
        }
        return deferredMediaPeriod2;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        DeferredMediaPeriod deferredMediaPeriod = (DeferredMediaPeriod) mediaPeriod;
        List<DeferredMediaPeriod> list = this.deferredMediaPeriodByAdMediaSource.get(deferredMediaPeriod.mediaSource);
        if (list != null) {
            list.remove(deferredMediaPeriod);
        }
        deferredMediaPeriod.releasePeriod();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource, com.oculus.android.exoplayer2.source.CompositeMediaSource
    public void releaseSource() {
        super.releaseSource();
        this.componentListener.release();
        this.componentListener = null;
        this.deferredMediaPeriodByAdMediaSource.clear();
        this.contentTimeline = null;
        this.contentManifest = null;
        this.adPlaybackState = null;
        this.adGroupMediaSources = new MediaSource[0][];
        this.adDurationsUs = new long[0][];
        this.listener = null;
        this.mainHandler.post(new Runnable() {
            /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.AnonymousClass2 */

            public void run() {
                AdsMediaSource.this.adsLoader.detachPlayer();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(MediaSource.MediaPeriodId mediaPeriodId, MediaSource mediaSource, Timeline timeline, @Nullable Object obj) {
        if (mediaPeriodId.isAd()) {
            onAdSourceInfoRefreshed(mediaSource, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, timeline);
        } else {
            onContentSourceInfoRefreshed(timeline, obj);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onAdPlaybackState(AdPlaybackState adPlaybackState2) {
        if (this.adPlaybackState == null) {
            this.adGroupMediaSources = new MediaSource[adPlaybackState2.adGroupCount][];
            Arrays.fill(this.adGroupMediaSources, new MediaSource[0]);
            this.adDurationsUs = new long[adPlaybackState2.adGroupCount][];
            Arrays.fill(this.adDurationsUs, new long[0]);
        }
        this.adPlaybackState = adPlaybackState2;
        maybeUpdateSourceInfo();
    }

    private void onContentSourceInfoRefreshed(Timeline timeline, Object obj) {
        this.contentTimeline = timeline;
        this.contentManifest = obj;
        maybeUpdateSourceInfo();
    }

    private void onAdSourceInfoRefreshed(MediaSource mediaSource, int i, int i2, Timeline timeline) {
        boolean z = true;
        if (timeline.getPeriodCount() != 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.adDurationsUs[i][i2] = timeline.getPeriod(0, this.period).getDurationUs();
        if (this.deferredMediaPeriodByAdMediaSource.containsKey(mediaSource)) {
            List<DeferredMediaPeriod> list = this.deferredMediaPeriodByAdMediaSource.get(mediaSource);
            for (int i3 = 0; i3 < list.size(); i3++) {
                list.get(i3).createPeriod();
            }
            this.deferredMediaPeriodByAdMediaSource.remove(mediaSource);
        }
        maybeUpdateSourceInfo();
    }

    private void maybeUpdateSourceInfo() {
        AdPlaybackState adPlaybackState2 = this.adPlaybackState;
        if (adPlaybackState2 != null && this.contentTimeline != null) {
            this.adPlaybackState = adPlaybackState2.withAdDurationsUs(this.adDurationsUs);
            this.listener.onSourceInfoRefreshed(this, this.adPlaybackState.adGroupCount == 0 ? this.contentTimeline : new SinglePeriodAdTimeline(this.contentTimeline, this.adPlaybackState), this.contentManifest);
        }
    }

    private final class ComponentListener implements AdsLoader.EventListener {
        private final Handler playerHandler = new Handler();
        private volatile boolean released;

        public ComponentListener() {
        }

        public void release() {
            this.released = true;
            this.playerHandler.removeCallbacksAndMessages(null);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdPlaybackState(final AdPlaybackState adPlaybackState) {
            if (!this.released) {
                this.playerHandler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.ComponentListener.AnonymousClass1 */

                    public void run() {
                        if (!ComponentListener.this.released) {
                            AdsMediaSource.this.onAdPlaybackState(adPlaybackState);
                        }
                    }
                });
            }
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdClicked() {
            if (!this.released && AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                AdsMediaSource.this.eventHandler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.ComponentListener.AnonymousClass2 */

                    public void run() {
                        if (!ComponentListener.this.released) {
                            AdsMediaSource.this.eventListener.onAdClicked();
                        }
                    }
                });
            }
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdTapped() {
            if (!this.released && AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                AdsMediaSource.this.eventHandler.post(new Runnable() {
                    /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.ComponentListener.AnonymousClass3 */

                    public void run() {
                        if (!ComponentListener.this.released) {
                            AdsMediaSource.this.eventListener.onAdTapped();
                        }
                    }
                });
            }
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onAdLoadError(final IOException iOException) {
            if (!this.released) {
                Log.w(AdsMediaSource.TAG, "Ad load error", iOException);
                if (AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                    AdsMediaSource.this.eventHandler.post(new Runnable() {
                        /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.ComponentListener.AnonymousClass4 */

                        public void run() {
                            if (!ComponentListener.this.released) {
                                AdsMediaSource.this.eventListener.onAdLoadError(iOException);
                            }
                        }
                    });
                }
            }
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsLoader.EventListener
        public void onInternalAdLoadError(final RuntimeException runtimeException) {
            if (!this.released) {
                Log.w(AdsMediaSource.TAG, "Internal ad load error", runtimeException);
                if (AdsMediaSource.this.eventHandler != null && AdsMediaSource.this.eventListener != null) {
                    AdsMediaSource.this.eventHandler.post(new Runnable() {
                        /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.ComponentListener.AnonymousClass5 */

                        public void run() {
                            if (!ComponentListener.this.released) {
                                AdsMediaSource.this.eventListener.onInternalAdLoadError(runtimeException);
                            }
                        }
                    });
                }
            }
        }
    }

    private final class AdPrepareErrorListener implements DeferredMediaPeriod.PrepareErrorListener {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;

        public AdPrepareErrorListener(int i, int i2) {
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
        }

        @Override // com.oculus.android.exoplayer2.source.DeferredMediaPeriod.PrepareErrorListener
        public void onPrepareError(final IOException iOException) {
            AdsMediaSource.this.mainHandler.post(new Runnable() {
                /* class com.oculus.android.exoplayer2.source.ads.AdsMediaSource.AdPrepareErrorListener.AnonymousClass1 */

                public void run() {
                    AdsMediaSource.this.adsLoader.handlePrepareError(AdPrepareErrorListener.this.adGroupIndex, AdPrepareErrorListener.this.adIndexInAdGroup, iOException);
                }
            });
        }
    }
}
