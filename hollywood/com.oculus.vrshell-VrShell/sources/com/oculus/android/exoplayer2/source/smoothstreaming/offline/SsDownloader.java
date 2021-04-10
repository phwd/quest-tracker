package com.oculus.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import com.oculus.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.oculus.android.exoplayer2.offline.SegmentDownloader;
import com.oculus.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.oculus.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.oculus.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.oculus.android.exoplayer2.source.smoothstreaming.manifest.TrackKey;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import com.oculus.android.exoplayer2.upstream.ParsingLoadable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SsDownloader extends SegmentDownloader<SsManifest, TrackKey> {
    public SsDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(SsUtil.fixManifestUri(uri), downloaderConstructorHelper);
    }

    @Override // com.oculus.android.exoplayer2.offline.SegmentDownloader
    public TrackKey[] getAllRepresentationKeys() throws IOException {
        ArrayList arrayList = new ArrayList();
        SsManifest ssManifest = (SsManifest) getManifest();
        for (int i = 0; i < ssManifest.streamElements.length; i++) {
            SsManifest.StreamElement streamElement = ssManifest.streamElements[i];
            for (int i2 = 0; i2 < streamElement.formats.length; i2++) {
                arrayList.add(new TrackKey(i, i2));
            }
        }
        return (TrackKey[]) arrayList.toArray(new TrackKey[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.offline.SegmentDownloader
    public SsManifest getManifest(DataSource dataSource, Uri uri) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, 4, new SsManifestParser());
        parsingLoadable.load();
        return (SsManifest) parsingLoadable.getResult();
    }

    /* access modifiers changed from: protected */
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, SsManifest ssManifest, TrackKey[] trackKeyArr, boolean z) throws InterruptedException, IOException {
        ArrayList arrayList = new ArrayList();
        for (TrackKey trackKey : trackKeyArr) {
            SsManifest.StreamElement streamElement = ssManifest.streamElements[trackKey.streamElementIndex];
            for (int i = 0; i < streamElement.chunkCount; i++) {
                arrayList.add(new SegmentDownloader.Segment(streamElement.getStartTimeUs(i), new DataSpec(streamElement.buildRequestUri(trackKey.trackIndex, i))));
            }
        }
        return arrayList;
    }
}
