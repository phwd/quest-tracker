package com.oculus.android.exoplayer2.source.hls;

import android.net.Uri;
import android.os.SystemClock;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.BehindLiveWindowException;
import com.oculus.android.exoplayer2.source.TrackGroup;
import com.oculus.android.exoplayer2.source.chunk.Chunk;
import com.oculus.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.oculus.android.exoplayer2.source.chunk.DataChunk;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.oculus.android.exoplayer2.trackselection.BaseTrackSelection;
import com.oculus.android.exoplayer2.trackselection.TrackSelection;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.util.UriUtil;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/* access modifiers changed from: package-private */
public class HlsChunkSource {
    private final DataSource encryptionDataSource;
    private byte[] encryptionIv;
    private String encryptionIvString;
    private byte[] encryptionKey;
    private Uri encryptionKeyUri;
    private HlsMasterPlaylist.HlsUrl expectedPlaylistUrl;
    private final HlsExtractorFactory extractorFactory;
    private IOException fatalError;
    private boolean independentSegments;
    private boolean isTimestampMaster;
    private long liveEdgeTimeUs = C.TIME_UNSET;
    private final DataSource mediaDataSource;
    private final List<Format> muxedCaptionFormats;
    private final HlsPlaylistTracker playlistTracker;
    private byte[] scratchSpace;
    private final TimestampAdjusterProvider timestampAdjusterProvider;
    private final TrackGroup trackGroup;
    private TrackSelection trackSelection;
    private final HlsMasterPlaylist.HlsUrl[] variants;

    public static final class HlsChunkHolder {
        public Chunk chunk;
        public boolean endOfStream;
        public HlsMasterPlaylist.HlsUrl playlist;

        public HlsChunkHolder() {
            clear();
        }

        public void clear() {
            this.chunk = null;
            this.endOfStream = false;
            this.playlist = null;
        }
    }

    public HlsChunkSource(HlsExtractorFactory hlsExtractorFactory, HlsPlaylistTracker hlsPlaylistTracker, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, HlsDataSourceFactory hlsDataSourceFactory, TimestampAdjusterProvider timestampAdjusterProvider2, List<Format> list) {
        this.extractorFactory = hlsExtractorFactory;
        this.playlistTracker = hlsPlaylistTracker;
        this.variants = hlsUrlArr;
        this.timestampAdjusterProvider = timestampAdjusterProvider2;
        this.muxedCaptionFormats = list;
        Format[] formatArr = new Format[hlsUrlArr.length];
        int[] iArr = new int[hlsUrlArr.length];
        for (int i = 0; i < hlsUrlArr.length; i++) {
            formatArr[i] = hlsUrlArr[i].format;
            iArr[i] = i;
        }
        this.mediaDataSource = hlsDataSourceFactory.createDataSource(1);
        this.encryptionDataSource = hlsDataSourceFactory.createDataSource(3);
        this.trackGroup = new TrackGroup(formatArr);
        this.trackSelection = new InitializationTrackSelection(this.trackGroup, iArr);
    }

    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException == null) {
            HlsMasterPlaylist.HlsUrl hlsUrl = this.expectedPlaylistUrl;
            if (hlsUrl != null) {
                this.playlistTracker.maybeThrowPlaylistRefreshError(hlsUrl);
                return;
            }
            return;
        }
        throw iOException;
    }

    public TrackGroup getTrackGroup() {
        return this.trackGroup;
    }

    public void selectTracks(TrackSelection trackSelection2) {
        this.trackSelection = trackSelection2;
    }

    public TrackSelection getTrackSelection() {
        return this.trackSelection;
    }

    public void reset() {
        this.fatalError = null;
    }

    public void setIsTimestampMaster(boolean z) {
        this.isTimestampMaster = z;
    }

    public void getNextChunk(HlsMediaChunk hlsMediaChunk, long j, long j2, HlsChunkHolder hlsChunkHolder) {
        int i;
        long j3;
        HlsMasterPlaylist.HlsUrl hlsUrl;
        HlsMediaPlaylist hlsMediaPlaylist;
        long j4;
        if (hlsMediaChunk == null) {
            i = -1;
        } else {
            i = this.trackGroup.indexOf(hlsMediaChunk.trackFormat);
        }
        DataSpec dataSpec = null;
        this.expectedPlaylistUrl = null;
        long j5 = j2 - j;
        long resolveTimeToLiveEdgeUs = resolveTimeToLiveEdgeUs(j);
        if (hlsMediaChunk != null && !this.independentSegments) {
            long durationUs = hlsMediaChunk.getDurationUs();
            j5 = Math.max(0L, j5 - durationUs);
            if (resolveTimeToLiveEdgeUs != C.TIME_UNSET) {
                resolveTimeToLiveEdgeUs = Math.max(0L, resolveTimeToLiveEdgeUs - durationUs);
            }
        }
        this.trackSelection.updateSelectedTrack(j, j5, resolveTimeToLiveEdgeUs);
        int selectedIndexInTrackGroup = this.trackSelection.getSelectedIndexInTrackGroup();
        boolean z = false;
        boolean z2 = i != selectedIndexInTrackGroup;
        HlsMasterPlaylist.HlsUrl hlsUrl2 = this.variants[selectedIndexInTrackGroup];
        if (!this.playlistTracker.isSnapshotValid(hlsUrl2)) {
            hlsChunkHolder.playlist = hlsUrl2;
            this.expectedPlaylistUrl = hlsUrl2;
            return;
        }
        HlsMediaPlaylist playlistSnapshot = this.playlistTracker.getPlaylistSnapshot(hlsUrl2);
        this.independentSegments = playlistSnapshot.hasIndependentSegmentsTag;
        updateLiveEdgeTimeUs(playlistSnapshot);
        if (hlsMediaChunk == null || z2) {
            long j6 = (hlsMediaChunk == null || this.independentSegments) ? j2 : hlsMediaChunk.startTimeUs;
            if (playlistSnapshot.hasEndTag || j6 < playlistSnapshot.getEndTimeUs()) {
                List<HlsMediaPlaylist.Segment> list = playlistSnapshot.segments;
                Long valueOf = Long.valueOf(j6 - playlistSnapshot.startTimeUs);
                if (!this.playlistTracker.isLive() || hlsMediaChunk == null) {
                    z = true;
                }
                long binarySearchFloor = ((long) Util.binarySearchFloor((List) list, (Object) valueOf, true, z)) + playlistSnapshot.mediaSequence;
                if (binarySearchFloor >= playlistSnapshot.mediaSequence || hlsMediaChunk == null) {
                    i = selectedIndexInTrackGroup;
                    hlsMediaPlaylist = playlistSnapshot;
                    j4 = binarySearchFloor;
                } else {
                    hlsUrl2 = this.variants[i];
                    hlsMediaPlaylist = this.playlistTracker.getPlaylistSnapshot(hlsUrl2);
                    j4 = hlsMediaChunk.getNextChunkIndex();
                }
            } else {
                i = selectedIndexInTrackGroup;
                hlsMediaPlaylist = playlistSnapshot;
                j4 = playlistSnapshot.mediaSequence + ((long) playlistSnapshot.segments.size());
            }
            hlsUrl = hlsUrl2;
            j3 = j4;
        } else {
            j3 = hlsMediaChunk.getNextChunkIndex();
            hlsUrl = hlsUrl2;
            i = selectedIndexInTrackGroup;
            hlsMediaPlaylist = playlistSnapshot;
        }
        if (j3 < hlsMediaPlaylist.mediaSequence) {
            this.fatalError = new BehindLiveWindowException();
            return;
        }
        int i2 = (int) (j3 - hlsMediaPlaylist.mediaSequence);
        if (i2 < hlsMediaPlaylist.segments.size()) {
            HlsMediaPlaylist.Segment segment = hlsMediaPlaylist.segments.get(i2);
            if (segment.fullSegmentEncryptionKeyUri != null) {
                Uri resolveToUri = UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.fullSegmentEncryptionKeyUri);
                if (!resolveToUri.equals(this.encryptionKeyUri)) {
                    hlsChunkHolder.chunk = newEncryptionKeyChunk(resolveToUri, segment.encryptionIV, i, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData());
                    return;
                } else if (!Util.areEqual(segment.encryptionIV, this.encryptionIvString)) {
                    setEncryptionData(resolveToUri, segment.encryptionIV, this.encryptionKey);
                }
            } else {
                clearEncryptionData();
            }
            HlsMediaPlaylist.Segment segment2 = hlsMediaPlaylist.initializationSegment;
            if (segment2 != null) {
                dataSpec = new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment2.url), segment2.byterangeOffset, segment2.byterangeLength, null);
            }
            long j7 = hlsMediaPlaylist.startTimeUs + segment.relativeStartTimeUs;
            int i3 = hlsMediaPlaylist.discontinuitySequence + segment.relativeDiscontinuitySequence;
            hlsChunkHolder.chunk = new HlsMediaChunk(this.extractorFactory, this.mediaDataSource, new DataSpec(UriUtil.resolveToUri(hlsMediaPlaylist.baseUri, segment.url), segment.byterangeOffset, segment.byterangeLength, null), dataSpec, hlsUrl, this.muxedCaptionFormats, this.trackSelection.getSelectionReason(), this.trackSelection.getSelectionData(), j7, j7 + segment.durationUs, j3, i3, segment.hasGapTag, this.isTimestampMaster, this.timestampAdjusterProvider.getAdjuster(i3), hlsMediaChunk, hlsMediaPlaylist.drmInitData, this.encryptionKey, this.encryptionIv);
        } else if (hlsMediaPlaylist.hasEndTag) {
            hlsChunkHolder.endOfStream = true;
        } else {
            hlsChunkHolder.playlist = hlsUrl;
            this.expectedPlaylistUrl = hlsUrl;
        }
    }

    public void onChunkLoadCompleted(Chunk chunk) {
        if (chunk instanceof EncryptionKeyChunk) {
            EncryptionKeyChunk encryptionKeyChunk = (EncryptionKeyChunk) chunk;
            this.scratchSpace = encryptionKeyChunk.getDataHolder();
            setEncryptionData(encryptionKeyChunk.dataSpec.uri, encryptionKeyChunk.iv, encryptionKeyChunk.getResult());
        }
    }

    public boolean onChunkLoadError(Chunk chunk, boolean z, IOException iOException) {
        if (z) {
            TrackSelection trackSelection2 = this.trackSelection;
            if (ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection2, trackSelection2.indexOf(this.trackGroup.indexOf(chunk.trackFormat)), iOException)) {
                return true;
            }
        }
        return false;
    }

    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        int indexOf;
        int indexOf2 = this.trackGroup.indexOf(hlsUrl.format);
        if (indexOf2 != -1 && (indexOf = this.trackSelection.indexOf(indexOf2)) != -1) {
            this.trackSelection.blacklist(indexOf, j);
        }
    }

    private long resolveTimeToLiveEdgeUs(long j) {
        if (this.liveEdgeTimeUs != C.TIME_UNSET) {
            return this.liveEdgeTimeUs - j;
        }
        return C.TIME_UNSET;
    }

    private void updateLiveEdgeTimeUs(HlsMediaPlaylist hlsMediaPlaylist) {
        this.liveEdgeTimeUs = hlsMediaPlaylist.hasEndTag ? C.TIME_UNSET : hlsMediaPlaylist.getEndTimeUs();
    }

    private EncryptionKeyChunk newEncryptionKeyChunk(Uri uri, String str, int i, int i2, Object obj) {
        return new EncryptionKeyChunk(this.encryptionDataSource, new DataSpec(uri, 0, -1, null, 1), this.variants[i].format, i2, obj, this.scratchSpace, str);
    }

    private void setEncryptionData(Uri uri, String str, byte[] bArr) {
        byte[] byteArray = new BigInteger(Util.toLowerInvariant(str).startsWith("0x") ? str.substring(2) : str, 16).toByteArray();
        byte[] bArr2 = new byte[16];
        int length = byteArray.length > 16 ? byteArray.length - 16 : 0;
        System.arraycopy(byteArray, length, bArr2, (bArr2.length - byteArray.length) + length, byteArray.length - length);
        this.encryptionKeyUri = uri;
        this.encryptionKey = bArr;
        this.encryptionIvString = str;
        this.encryptionIv = bArr2;
    }

    private void clearEncryptionData() {
        this.encryptionKeyUri = null;
        this.encryptionKey = null;
        this.encryptionIvString = null;
        this.encryptionIv = null;
    }

    private static final class InitializationTrackSelection extends BaseTrackSelection {
        private int selectedIndex;

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
        public Object getSelectionData() {
            return null;
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
        public int getSelectionReason() {
            return 0;
        }

        public InitializationTrackSelection(TrackGroup trackGroup, int[] iArr) {
            super(trackGroup, iArr);
            this.selectedIndex = indexOf(trackGroup.getFormat(0));
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
        public void updateSelectedTrack(long j, long j2, long j3) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (isBlacklisted(this.selectedIndex, elapsedRealtime)) {
                for (int i = this.length - 1; i >= 0; i--) {
                    if (!isBlacklisted(i, elapsedRealtime)) {
                        this.selectedIndex = i;
                        return;
                    }
                }
                throw new IllegalStateException();
            }
        }

        @Override // com.oculus.android.exoplayer2.trackselection.TrackSelection
        public int getSelectedIndex() {
            return this.selectedIndex;
        }
    }

    /* access modifiers changed from: private */
    public static final class EncryptionKeyChunk extends DataChunk {
        public final String iv;
        private byte[] result;

        public EncryptionKeyChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, byte[] bArr, String str) {
            super(dataSource, dataSpec, 3, format, i, obj, bArr);
            this.iv = str;
        }

        /* access modifiers changed from: protected */
        @Override // com.oculus.android.exoplayer2.source.chunk.DataChunk
        public void consume(byte[] bArr, int i) throws IOException {
            this.result = Arrays.copyOf(bArr, i);
        }

        public byte[] getResult() {
            return this.result;
        }
    }
}
