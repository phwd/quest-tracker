package com.oculus.android.exoplayer2.source.dash.offline;

import android.net.Uri;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.extractor.ChunkIndex;
import com.oculus.android.exoplayer2.offline.DownloadException;
import com.oculus.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.oculus.android.exoplayer2.offline.SegmentDownloader;
import com.oculus.android.exoplayer2.source.dash.DashSegmentIndex;
import com.oculus.android.exoplayer2.source.dash.DashUtil;
import com.oculus.android.exoplayer2.source.dash.DashWrappingSegmentIndex;
import com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.Period;
import com.oculus.android.exoplayer2.source.dash.manifest.RangedUri;
import com.oculus.android.exoplayer2.source.dash.manifest.Representation;
import com.oculus.android.exoplayer2.source.dash.manifest.RepresentationKey;
import com.oculus.android.exoplayer2.upstream.DataSource;
import com.oculus.android.exoplayer2.upstream.DataSpec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class DashDownloader extends SegmentDownloader<DashManifest, RepresentationKey> {
    public DashDownloader(Uri uri, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(uri, downloaderConstructorHelper);
    }

    @Override // com.oculus.android.exoplayer2.offline.SegmentDownloader
    public RepresentationKey[] getAllRepresentationKeys() throws IOException {
        ArrayList arrayList = new ArrayList();
        DashManifest dashManifest = (DashManifest) getManifest();
        for (int i = 0; i < dashManifest.getPeriodCount(); i++) {
            List<AdaptationSet> list = dashManifest.getPeriod(i).adaptationSets;
            for (int i2 = 0; i2 < list.size(); i2++) {
                int size = list.get(i2).representations.size();
                for (int i3 = 0; i3 < size; i3++) {
                    arrayList.add(new RepresentationKey(i, i2, i3));
                }
            }
        }
        return (RepresentationKey[]) arrayList.toArray(new RepresentationKey[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.offline.SegmentDownloader
    public DashManifest getManifest(DataSource dataSource, Uri uri) throws IOException {
        return DashUtil.loadManifest(dataSource, uri);
    }

    /* access modifiers changed from: protected */
    public List<SegmentDownloader.Segment> getSegments(DataSource dataSource, DashManifest dashManifest, RepresentationKey[] representationKeyArr, boolean z) throws InterruptedException, IOException {
        DashManifest dashManifest2 = dashManifest;
        ArrayList arrayList = new ArrayList();
        int length = representationKeyArr.length;
        int i = 0;
        while (i < length) {
            RepresentationKey representationKey = representationKeyArr[i];
            try {
                DashSegmentIndex segmentIndex = getSegmentIndex(dataSource, dashManifest2, representationKey);
                if (segmentIndex != null) {
                    int segmentCount = segmentIndex.getSegmentCount(dashManifest2.getPeriodDurationUs(representationKey.periodIndex));
                    if (segmentCount != -1) {
                        Period period = dashManifest2.getPeriod(representationKey.periodIndex);
                        Representation representation = period.adaptationSets.get(representationKey.adaptationSetIndex).representations.get(representationKey.representationIndex);
                        long msToUs = C.msToUs(period.startMs);
                        String str = representation.baseUrl;
                        RangedUri initializationUri = representation.getInitializationUri();
                        if (initializationUri != null) {
                            addSegment(arrayList, msToUs, str, initializationUri);
                        }
                        RangedUri indexUri = representation.getIndexUri();
                        if (indexUri != null) {
                            addSegment(arrayList, msToUs, str, indexUri);
                        }
                        long firstSegmentNum = segmentIndex.getFirstSegmentNum();
                        for (long j = (((long) segmentCount) + firstSegmentNum) - 1; firstSegmentNum <= j; j = j) {
                            addSegment(arrayList, msToUs + segmentIndex.getTimeUs(firstSegmentNum), str, segmentIndex.getSegmentUrl(firstSegmentNum));
                            firstSegmentNum++;
                        }
                        i++;
                        dashManifest2 = dashManifest;
                    } else {
                        throw new DownloadException("Unbounded index for representation: " + representationKey);
                    }
                } else {
                    throw new DownloadException("No index for representation: " + representationKey);
                }
            } catch (IOException e) {
                if (!z) {
                    throw e;
                }
            }
        }
        return arrayList;
    }

    private DashSegmentIndex getSegmentIndex(DataSource dataSource, DashManifest dashManifest, RepresentationKey representationKey) throws IOException, InterruptedException {
        AdaptationSet adaptationSet = dashManifest.getPeriod(representationKey.periodIndex).adaptationSets.get(representationKey.adaptationSetIndex);
        Representation representation = adaptationSet.representations.get(representationKey.representationIndex);
        DashSegmentIndex index = representation.getIndex();
        if (index != null) {
            return index;
        }
        ChunkIndex loadChunkIndex = DashUtil.loadChunkIndex(dataSource, adaptationSet.type, representation);
        if (loadChunkIndex == null) {
            return null;
        }
        return new DashWrappingSegmentIndex(loadChunkIndex);
    }

    private static void addSegment(ArrayList<SegmentDownloader.Segment> arrayList, long j, String str, RangedUri rangedUri) {
        arrayList.add(new SegmentDownloader.Segment(j, new DataSpec(rangedUri.resolveUri(str), rangedUri.start, rangedUri.length, null)));
    }
}
