package com.oculus.video.source.hls;

import android.net.Uri;
import android.os.Handler;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.ExoPlayer;
import com.oculus.android.exoplayer2.ExoPlayerLibraryInfo;
import com.oculus.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.DefaultCompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSource;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.SinglePeriodTimeline;
import com.oculus.android.exoplayer2.source.ads.AdsMediaSource;
import com.oculus.android.exoplayer2.source.hls.DefaultHlsDataSourceFactory;
import com.oculus.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.oculus.android.exoplayer2.source.hls.HlsExtractorFactory;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.video.source.hls.OculusHlsPlaylistTracker;
import java.io.IOException;
import java.util.IdentityHashMap;
import java.util.List;

public final class OculusHlsMediaSource implements MediaSource, OculusHlsPlaylistTracker.PrimaryPlaylistListener, OculusHlsPlaylistTracker.PlaylistEventListener {
    public static final int DEFAULT_MIN_LOADABLE_RETRY_COUNT = 3;
    private final boolean allowChunklessPreparation;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final HlsDataSourceFactory dataSourceFactory;
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final Uri manifestUri;
    private final int minLoadableRetryCount;
    private final ParsingLoadable.Parser<HlsPlaylist> playlistParser;
    private OculusHlsPlaylistTracker playlistTracker;
    private OculusHlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener;
    private MediaSource.Listener sourceListener;

    @Override // com.oculus.video.source.hls.OculusHlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
    }

    @Override // com.oculus.video.source.hls.OculusHlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged(IdentityHashMap<HlsMasterPlaylist.HlsUrl, OculusHlsPlaylistTracker.MediaPlaylistBundle> identityHashMap) {
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.hls");
    }

    public static final class Factory implements AdsMediaSource.MediaSourceFactory {
        private boolean allowChunklessPreparation;
        private CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
        private HlsExtractorFactory extractorFactory;
        private final HlsDataSourceFactory hlsDataSourceFactory;
        private boolean isCreateCalled;
        private int minLoadableRetryCount;
        @Nullable
        private ParsingLoadable.Parser<HlsPlaylist> playlistParser;

        public Factory(DataSource.Factory factory) {
            this(new DefaultHlsDataSourceFactory(factory));
        }

        public Factory(HlsDataSourceFactory hlsDataSourceFactory2) {
            this.hlsDataSourceFactory = (HlsDataSourceFactory) Assertions.checkNotNull(hlsDataSourceFactory2);
            this.extractorFactory = new OculusHlsExtractorFactory();
            this.minLoadableRetryCount = 3;
            this.compositeSequenceableLoaderFactory = new DefaultCompositeSequenceableLoaderFactory();
        }

        public Factory setExtractorFactory(HlsExtractorFactory hlsExtractorFactory) {
            Assertions.checkState(!this.isCreateCalled);
            this.extractorFactory = (HlsExtractorFactory) Assertions.checkNotNull(hlsExtractorFactory);
            return this;
        }

        public Factory setMinLoadableRetryCount(int i) {
            Assertions.checkState(!this.isCreateCalled);
            this.minLoadableRetryCount = i;
            return this;
        }

        public Factory setPlaylistParser(ParsingLoadable.Parser<HlsPlaylist> parser) {
            Assertions.checkState(!this.isCreateCalled);
            this.playlistParser = (ParsingLoadable.Parser) Assertions.checkNotNull(parser);
            return this;
        }

        public Factory setCompositeSequenceableLoaderFactory(CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2) {
            Assertions.checkState(!this.isCreateCalled);
            this.compositeSequenceableLoaderFactory = (CompositeSequenceableLoaderFactory) Assertions.checkNotNull(compositeSequenceableLoaderFactory2);
            return this;
        }

        public Factory setAllowChunklessPreparation(boolean z) {
            Assertions.checkState(!this.isCreateCalled);
            this.allowChunklessPreparation = z;
            return this;
        }

        public OculusHlsMediaSource createMediaSource(Uri uri) {
            return createMediaSource(uri, (Handler) null, (MediaSourceEventListener) null);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public OculusHlsMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener) {
            this.isCreateCalled = true;
            if (this.playlistParser == null) {
                this.playlistParser = new HlsPlaylistParser();
            }
            return new OculusHlsMediaSource(uri, this.hlsDataSourceFactory, this.extractorFactory, this.compositeSequenceableLoaderFactory, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.playlistParser, this.allowChunklessPreparation);
        }

        public OculusHlsMediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener mediaSourceEventListener, @Nullable OculusHlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener) {
            this.isCreateCalled = true;
            if (this.playlistParser == null) {
                this.playlistParser = new HlsPlaylistParser();
            }
            return new OculusHlsMediaSource(uri, this.hlsDataSourceFactory, this.extractorFactory, this.minLoadableRetryCount, handler, mediaSourceEventListener, this.playlistParser, primaryPlaylistListener);
        }

        @Override // com.oculus.android.exoplayer2.source.ads.AdsMediaSource.MediaSourceFactory
        public int[] getSupportedTypes() {
            return new int[]{2};
        }
    }

    @Deprecated
    public OculusHlsMediaSource(Uri uri, DataSource.Factory factory, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, factory, 3, handler, mediaSourceEventListener);
    }

    @Deprecated
    public OculusHlsMediaSource(Uri uri, DataSource.Factory factory, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener) {
        this(uri, new DefaultHlsDataSourceFactory(factory), new OculusHlsExtractorFactory(), i, handler, mediaSourceEventListener, new HlsPlaylistParser());
    }

    @Deprecated
    public OculusHlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener, ParsingLoadable.Parser<HlsPlaylist> parser, OculusHlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener2) {
        this(uri, hlsDataSourceFactory, hlsExtractorFactory, i, handler, mediaSourceEventListener, parser);
        this.primaryPlaylistListener = primaryPlaylistListener2;
    }

    @Deprecated
    public OculusHlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener, ParsingLoadable.Parser<HlsPlaylist> parser) {
        this(uri, hlsDataSourceFactory, hlsExtractorFactory, new DefaultCompositeSequenceableLoaderFactory(), i, handler, mediaSourceEventListener, parser, false);
    }

    private OculusHlsMediaSource(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, HlsExtractorFactory hlsExtractorFactory, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, int i, Handler handler, MediaSourceEventListener mediaSourceEventListener, ParsingLoadable.Parser<HlsPlaylist> parser, boolean z) {
        this.manifestUri = uri;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.extractorFactory = hlsExtractorFactory;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.minLoadableRetryCount = i;
        this.playlistParser = parser;
        this.allowChunklessPreparation = z;
        this.eventDispatcher = new MediaSourceEventListener.EventDispatcher(handler, mediaSourceEventListener);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void prepareSource(ExoPlayer exoPlayer, boolean z, MediaSource.Listener listener) {
        this.sourceListener = listener;
        this.playlistTracker = new OculusHlsPlaylistTracker(this.manifestUri, this.dataSourceFactory, this.eventDispatcher, this.minLoadableRetryCount, this, this.playlistParser);
        this.playlistTracker.start();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void maybeThrowSourceInfoRefreshError() throws IOException {
        this.playlistTracker.maybeThrowPrimaryPlaylistRefreshError();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator) {
        Assertions.checkArgument(mediaPeriodId.periodIndex == 0);
        return new OculusHlsMediaPeriod(this.extractorFactory, this.playlistTracker, this.dataSourceFactory, this.minLoadableRetryCount, this.eventDispatcher, allocator, this, this.compositeSequenceableLoaderFactory, this.allowChunklessPreparation);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((OculusHlsMediaPeriod) mediaPeriod).release();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaSource
    public void releaseSource() {
        OculusHlsPlaylistTracker oculusHlsPlaylistTracker = this.playlistTracker;
        if (oculusHlsPlaylistTracker != null) {
            oculusHlsPlaylistTracker.release();
            this.playlistTracker = null;
        }
        this.sourceListener = null;
    }

    @Override // com.oculus.video.source.hls.OculusHlsPlaylistTracker.PrimaryPlaylistListener
    public void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist, HlsMasterPlaylist.HlsUrl hlsUrl, HlsMasterPlaylist hlsMasterPlaylist) {
        SinglePeriodTimeline singlePeriodTimeline;
        long j;
        long j2;
        long j3;
        long j4 = hlsMediaPlaylist.hasProgramDateTime ? 0 : -9223372036854775807L;
        long usToMs = hlsMediaPlaylist.hasProgramDateTime ? C.usToMs(hlsMediaPlaylist.startTimeUs) : -9223372036854775807L;
        long j5 = hlsMediaPlaylist.startOffsetUs;
        if (this.playlistTracker.isLive()) {
            long j6 = hlsMediaPlaylist.hasEndTag ? hlsMediaPlaylist.startTimeUs + hlsMediaPlaylist.durationUs : -9223372036854775807L;
            List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
            if (j5 == C.TIME_UNSET) {
                if (hlsMediaPlaylist.playlistType != 2) {
                    int size = list.size() - 3;
                    int size2 = ((list.size() + 1) / 2) - 1;
                    if (!list.isEmpty()) {
                        j3 = list.get(Math.max(0, Math.min(size, size2))).relativeStartTimeUs;
                        j2 = j3;
                        j = j2;
                    }
                } else if (!list.isEmpty()) {
                    j3 = list.get(Math.max(0, list.size() - 3)).relativeStartTimeUs;
                    j2 = j3;
                    j = j2;
                }
                j2 = 0;
                j = j2;
            } else {
                j = j5;
            }
            singlePeriodTimeline = new SinglePeriodTimeline(j4, usToMs, j6, hlsMediaPlaylist.durationUs, hlsMediaPlaylist.startTimeUs, j, true, !hlsMediaPlaylist.hasEndTag);
        } else {
            singlePeriodTimeline = new SinglePeriodTimeline(j4, usToMs, hlsMediaPlaylist.startTimeUs + hlsMediaPlaylist.durationUs, hlsMediaPlaylist.durationUs, hlsMediaPlaylist.startTimeUs, j5 == C.TIME_UNSET ? 0 : j5, true, false);
        }
        this.sourceListener.onSourceInfoRefreshed(this, singlePeriodTimeline, new HlsManifest(this.playlistTracker.getMasterPlaylist(), hlsMediaPlaylist));
        OculusHlsPlaylistTracker.PrimaryPlaylistListener primaryPlaylistListener2 = this.primaryPlaylistListener;
        if (primaryPlaylistListener2 != null) {
            primaryPlaylistListener2.onPrimaryPlaylistRefreshed(hlsMediaPlaylist, hlsUrl, hlsMasterPlaylist);
        }
    }
}
