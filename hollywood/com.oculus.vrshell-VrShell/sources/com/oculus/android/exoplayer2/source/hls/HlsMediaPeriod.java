package com.oculus.android.exoplayer2.source.hls;

import android.os.Handler;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.SeekParameters;
import com.oculus.android.exoplayer2.source.CompositeSequenceableLoaderFactory;
import com.oculus.android.exoplayer2.source.MediaPeriod;
import com.oculus.android.exoplayer2.source.MediaSourceEventListener;
import com.oculus.android.exoplayer2.source.SampleStream;
import com.oculus.android.exoplayer2.source.SequenceableLoader;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.TrackGroupArray;
import com.oculus.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.oculus.android.exoplayer2.upstream.Allocator;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private final boolean allowChunklessPreparation;
    private MediaPeriod.Callback callback;
    private SequenceableLoader compositeSequenceableLoader;
    private final CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory;
    private final Handler continueLoadingHandler = new Handler();
    private final HlsDataSourceFactory dataSourceFactory;
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final MediaSourceEventListener.EventDispatcher eventDispatcher;
    private final HlsExtractorFactory extractorFactory;
    private final int minLoadableRetryCount;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private TrackGroupArray trackGroups;

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return C.TIME_UNSET;
    }

    public HlsMediaPeriod(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, int i, MediaSourceEventListener.EventDispatcher eventDispatcher2, Allocator allocator2, CompositeSequenceableLoaderFactory compositeSequenceableLoaderFactory2, boolean z) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.minLoadableRetryCount = i;
        this.eventDispatcher = eventDispatcher2;
        this.allocator = allocator2;
        this.compositeSequenceableLoaderFactory = compositeSequenceableLoaderFactory2;
        this.allowChunklessPreparation = z;
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        this.continueLoadingHandler.removeCallbacksAndMessages(null);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.release();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback2, long j) {
        this.callback = callback2;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.maybeThrowPrepareError();
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00ea, code lost:
        if (r11 != r8[0]) goto L_0x00ee;
     */
    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long selectTracks(com.oculus.android.exoplayer2.trackselection.TrackSelection[] r21, boolean[] r22, com.oculus.android.exoplayer2.source.SampleStream[] r23, boolean[] r24, long r25) {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.android.exoplayer2.source.hls.HlsMediaPeriod.selectTracks(com.oculus.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.oculus.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j, boolean z) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            hlsSampleStreamWrapper.discardBuffer(j, z);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public void reevaluateBuffer(long j) {
        this.compositeSequenceableLoader.reevaluateBuffer(j);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        return this.compositeSequenceableLoader.continueLoading(j);
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.compositeSequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod, com.oculus.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.compositeSequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.oculus.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean seekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j, false);
            int i = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i].seekToUs(j, seekToUs);
                i++;
            }
            if (seekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    @Override // com.oculus.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPrepared() {
        int i = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i;
        if (i <= 0) {
            int i2 = 0;
            for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
                i2 += hlsSampleStreamWrapper.getTrackGroups().length;
            }
            TrackGroup[] trackGroupArr = new TrackGroup[i2];
            HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.sampleStreamWrappers;
            int length = hlsSampleStreamWrapperArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                HlsSampleStreamWrapper hlsSampleStreamWrapper2 = hlsSampleStreamWrapperArr[i3];
                int i5 = hlsSampleStreamWrapper2.getTrackGroups().length;
                int i6 = i4;
                int i7 = 0;
                while (i7 < i5) {
                    trackGroupArr[i6] = hlsSampleStreamWrapper2.getTrackGroups().get(i7);
                    i7++;
                    i6++;
                }
                i3++;
                i4 = i6;
            }
            this.trackGroups = new TrackGroupArray(trackGroupArr);
            this.callback.onPrepared(this);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPlaylistRefreshRequired(HlsMasterPlaylist.HlsUrl hlsUrl) {
        this.playlistTracker.refreshPlaylist(hlsUrl);
    }

    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        if (this.trackGroups != null) {
            this.callback.onContinueLoadingRequested(this);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        continuePreparingOrLoading();
    }

    @Override // com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.onPlaylistBlacklisted(hlsUrl, j);
        }
        continuePreparingOrLoading();
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        HlsMasterPlaylist masterPlaylist = this.playlistTracker.getMasterPlaylist();
        List<HlsMasterPlaylist.HlsUrl> list = masterPlaylist.audios;
        List<HlsMasterPlaylist.HlsUrl> list2 = masterPlaylist.subtitles;
        int size = list.size() + 1 + list2.size();
        this.sampleStreamWrappers = new HlsSampleStreamWrapper[size];
        this.pendingPrepareCount = size;
        buildAndPrepareMainSampleStreamWrapper(masterPlaylist, j);
        char c = 0;
        int i = 1;
        int i2 = 0;
        while (i2 < list.size()) {
            HlsMasterPlaylist.HlsUrl hlsUrl = list.get(i2);
            HlsMasterPlaylist.HlsUrl[] hlsUrlArr = new HlsMasterPlaylist.HlsUrl[1];
            hlsUrlArr[c] = hlsUrl;
            HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(1, hlsUrlArr, null, Collections.emptyList(), j);
            int i3 = i + 1;
            this.sampleStreamWrappers[i] = buildSampleStreamWrapper;
            Format format = hlsUrl.format;
            if (!this.allowChunklessPreparation || format.codecs == null) {
                buildSampleStreamWrapper.continuePreparing();
            } else {
                buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup(hlsUrl.format)), 0);
            }
            i2++;
            i = i3;
            c = 0;
        }
        int i4 = 0;
        while (i4 < list2.size()) {
            HlsMasterPlaylist.HlsUrl hlsUrl2 = list2.get(i4);
            HlsSampleStreamWrapper buildSampleStreamWrapper2 = buildSampleStreamWrapper(3, new HlsMasterPlaylist.HlsUrl[]{hlsUrl2}, null, Collections.emptyList(), j);
            this.sampleStreamWrappers[i] = buildSampleStreamWrapper2;
            buildSampleStreamWrapper2.prepareWithMasterPlaylistInfo(new TrackGroupArray(new TrackGroup(hlsUrl2.format)), 0);
            i4++;
            i++;
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private void buildAndPrepareMainSampleStreamWrapper(HlsMasterPlaylist hlsMasterPlaylist, long j) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList(hlsMasterPlaylist.variants);
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        for (int i = 0; i < arrayList2.size(); i++) {
            HlsMasterPlaylist.HlsUrl hlsUrl = (HlsMasterPlaylist.HlsUrl) arrayList2.get(i);
            Format format = hlsUrl.format;
            if (format.height > 0 || Util.getCodecsOfType(format.codecs, 2) != null) {
                arrayList3.add(hlsUrl);
            } else if (Util.getCodecsOfType(format.codecs, 1) != null) {
                arrayList4.add(hlsUrl);
            }
        }
        if (!arrayList3.isEmpty()) {
            arrayList = arrayList3;
        } else {
            if (arrayList4.size() < arrayList2.size()) {
                arrayList2.removeAll(arrayList4);
            }
            arrayList = arrayList2;
        }
        Assertions.checkArgument(!arrayList.isEmpty());
        HlsMasterPlaylist.HlsUrl[] hlsUrlArr = (HlsMasterPlaylist.HlsUrl[]) arrayList.toArray(new HlsMasterPlaylist.HlsUrl[0]);
        String str = hlsUrlArr[0].format.codecs;
        HlsSampleStreamWrapper buildSampleStreamWrapper = buildSampleStreamWrapper(0, hlsUrlArr, hlsMasterPlaylist.muxedAudioFormat, hlsMasterPlaylist.muxedCaptionFormats, j);
        this.sampleStreamWrappers[0] = buildSampleStreamWrapper;
        if (!this.allowChunklessPreparation || str == null) {
            buildSampleStreamWrapper.setIsTimestampMaster(true);
            buildSampleStreamWrapper.continuePreparing();
            return;
        }
        boolean z = Util.getCodecsOfType(str, 2) != null;
        boolean z2 = Util.getCodecsOfType(str, 1) != null;
        ArrayList arrayList5 = new ArrayList();
        if (z) {
            Format[] formatArr = new Format[arrayList.size()];
            for (int i2 = 0; i2 < formatArr.length; i2++) {
                formatArr[i2] = deriveVideoFormat(hlsUrlArr[i2].format);
            }
            arrayList5.add(new TrackGroup(formatArr));
            if (z2 && (hlsMasterPlaylist.muxedAudioFormat != null || hlsMasterPlaylist.audios.isEmpty())) {
                arrayList5.add(new TrackGroup(deriveMuxedAudioFormat(hlsUrlArr[0].format, hlsMasterPlaylist.muxedAudioFormat, -1)));
            }
            List<Format> list = hlsMasterPlaylist.muxedCaptionFormats;
            if (list != null) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    arrayList5.add(new TrackGroup(list.get(i3)));
                }
            }
        } else if (z2) {
            Format[] formatArr2 = new Format[arrayList.size()];
            for (int i4 = 0; i4 < formatArr2.length; i4++) {
                Format format2 = hlsUrlArr[i4].format;
                formatArr2[i4] = deriveMuxedAudioFormat(format2, hlsMasterPlaylist.muxedAudioFormat, format2.bitrate);
            }
            arrayList5.add(new TrackGroup(formatArr2));
        } else {
            throw new IllegalArgumentException("Unexpected codecs attribute: " + str);
        }
        buildSampleStreamWrapper.prepareWithMasterPlaylistInfo(new TrackGroupArray((TrackGroup[]) arrayList5.toArray(new TrackGroup[0])), 0);
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int i, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, Format format, List<Format> list, long j) {
        return new HlsSampleStreamWrapper(i, this, new HlsChunkSource(this.extractorFactory, this.playlistTracker, hlsUrlArr, this.dataSourceFactory, this.timestampAdjusterProvider, list), this.allocator, j, format, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private void continuePreparingOrLoading() {
        if (this.trackGroups != null) {
            this.callback.onContinueLoadingRequested(this);
            return;
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
    }

    private static Format deriveVideoFormat(Format format) {
        String codecsOfType = Util.getCodecsOfType(format.codecs, 2);
        return Format.createVideoSampleFormat(format.id, MimeTypes.getMediaMimeType(codecsOfType), codecsOfType, format.bitrate, -1, format.width, format.height, format.frameRate, null, null);
    }

    private static Format deriveMuxedAudioFormat(Format format, Format format2, int i) {
        int i2;
        int i3;
        String str;
        String str2;
        if (format2 != null) {
            str = format2.codecs;
            i3 = format2.channelCount;
            i2 = format2.selectionFlags;
            str2 = format2.language;
        } else {
            str = Util.getCodecsOfType(format.codecs, 1);
            i3 = -1;
            i2 = 0;
            str2 = null;
        }
        return Format.createAudioSampleFormat(format.id, MimeTypes.getMediaMimeType(str), str, i, -1, i3, -1, null, null, i2, str2);
    }
}
