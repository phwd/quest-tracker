package com.oculus.video.source.hls;

import android.util.Pair;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.DefaultExtractorInput;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.extractor.ExtractorInput;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.metadata.id3.Id3Decoder;
import com.oculus.android.exoplayer2.metadata.id3.PrivFrame;
import com.oculus.android.exoplayer2.source.chunk.MediaChunk;
import com.oculus.android.exoplayer2.source.hls.HlsExtractorFactory;
import com.oculus.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.TimestampAdjuster;
import com.oculus.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class HlsMediaChunk extends MediaChunk {
    private static final String PRIV_TIMESTAMP_FRAME_OWNER = "com.apple.streaming.transportStreamTimestamp";
    private static final AtomicInteger uidSource = new AtomicInteger();
    private int bytesLoaded;
    public final int discontinuitySequenceNumber;
    private final Extractor extractor;
    private final boolean hasGapTag;
    public final HlsMasterPlaylist.HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private boolean id3TimestampPeeked;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted = (this.dataSource instanceof Aes128DataSource);
    private final boolean isMasterTimestampSource;
    private final boolean isPackedAudioExtractor;
    private volatile boolean loadCanceled;
    private volatile boolean loadCompleted;
    private HlsSampleStreamWrapper output;
    private final boolean reusingExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(HlsExtractorFactory hlsExtractorFactory, DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsMasterPlaylist.HlsUrl hlsUrl2, List<Format> list, int i, Object obj, long j, long j2, long j3, int i2, boolean z, boolean z2, TimestampAdjuster timestampAdjuster2, HlsMediaChunk hlsMediaChunk, DrmInitData drmInitData, byte[] bArr, byte[] bArr2) {
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, hlsUrl2.format, i, obj, j, j2, j3);
        Extractor extractor2;
        DataSpec dataSpec3;
        ParsableByteArray parsableByteArray;
        this.discontinuitySequenceNumber = i2;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl2;
        this.isMasterTimestampSource = z2;
        this.timestampAdjuster = timestampAdjuster2;
        this.hasGapTag = z;
        boolean z3 = true;
        if (hlsMediaChunk != null) {
            this.shouldSpliceIn = hlsMediaChunk.hlsUrl != hlsUrl2;
            extractor2 = (hlsMediaChunk.discontinuitySequenceNumber != i2 || this.shouldSpliceIn) ? null : hlsMediaChunk.extractor;
            dataSpec3 = dataSpec;
        } else {
            this.shouldSpliceIn = false;
            dataSpec3 = dataSpec;
            extractor2 = null;
        }
        Pair<Extractor, Boolean> createExtractor = hlsExtractorFactory.createExtractor(extractor2, dataSpec3.uri, this.trackFormat, list, drmInitData, timestampAdjuster2);
        this.extractor = (Extractor) createExtractor.first;
        this.isPackedAudioExtractor = ((Boolean) createExtractor.second).booleanValue();
        this.reusingExtractor = this.extractor == extractor2;
        this.initLoadCompleted = (!this.reusingExtractor || dataSpec2 == null) ? false : z3;
        if (!this.isPackedAudioExtractor) {
            this.id3Decoder = null;
            this.id3Data = null;
        } else if (hlsMediaChunk == null || (parsableByteArray = hlsMediaChunk.id3Data) == null) {
            this.id3Decoder = new Id3Decoder();
            this.id3Data = new ParsableByteArray(10);
        } else {
            this.id3Decoder = hlsMediaChunk.id3Decoder;
            this.id3Data = parsableByteArray;
        }
        this.initDataSource = dataSource;
        this.uid = uidSource.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.output = hlsSampleStreamWrapper;
        hlsSampleStreamWrapper.init(this.uid, this.shouldSpliceIn, this.reusingExtractor);
        if (!this.reusingExtractor) {
            this.extractor.init(hlsSampleStreamWrapper);
        }
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.oculus.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return (long) this.bytesLoaded;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.oculus.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws IOException, InterruptedException {
        maybeLoadInitData();
        if (!this.loadCanceled) {
            if (!this.hasGapTag) {
                loadMedia();
            }
            this.loadCompleted = true;
        }
    }

    /* JADX INFO: finally extract failed */
    private void maybeLoadInitData() throws IOException, InterruptedException {
        DataSpec dataSpec;
        if (!this.initLoadCompleted && (dataSpec = this.initDataSpec) != null) {
            DataSpec subrange = dataSpec.subrange((long) this.initSegmentBytesLoaded);
            try {
                DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(this.initDataSource, subrange.absoluteStreamPosition, this.initDataSource.open(subrange));
                int i = 0;
                while (i == 0) {
                    try {
                        if (this.loadCanceled) {
                            break;
                        }
                        i = this.extractor.read(defaultExtractorInput, null);
                    } catch (Throwable th) {
                        this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
                        throw th;
                    }
                }
                this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
                Util.closeQuietly(this.dataSource);
                this.initLoadCompleted = true;
            } catch (Throwable th2) {
                Util.closeQuietly(this.dataSource);
                throw th2;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061 A[Catch:{ all -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0068 A[Catch:{ all -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006f A[Catch:{ all -> 0x00a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[SYNTHETIC, Splitter:B:26:0x0076] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadMedia() throws java.io.IOException, java.lang.InterruptedException {
        /*
        // Method dump skipped, instructions count: 169
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.hls.HlsMediaChunk.loadMedia():void");
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws IOException, InterruptedException {
        Metadata decode;
        extractorInput.resetPeekPosition();
        if (!extractorInput.peekFully(this.id3Data.data, 0, 10, true)) {
            return C.TIME_UNSET;
        }
        this.id3Data.reset(10);
        if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
            return C.TIME_UNSET;
        }
        this.id3Data.skipBytes(3);
        int readSynchSafeInt = this.id3Data.readSynchSafeInt();
        int i = readSynchSafeInt + 10;
        if (i > this.id3Data.capacity()) {
            byte[] bArr = this.id3Data.data;
            this.id3Data.reset(i);
            System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
        }
        if (!extractorInput.peekFully(this.id3Data.data, 10, readSynchSafeInt, true) || (decode = this.id3Decoder.decode(this.id3Data.data, readSynchSafeInt)) == null) {
            return C.TIME_UNSET;
        }
        int length = decode.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = decode.get(i2);
            if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                if (PRIV_TIMESTAMP_FRAME_OWNER.equals(privFrame.owner)) {
                    System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                    this.id3Data.reset(8);
                    return this.id3Data.readLong() & 8589934591L;
                }
            }
        }
        return C.TIME_UNSET;
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return bArr != null ? new Aes128DataSource(dataSource, bArr, bArr2) : dataSource;
    }
}
