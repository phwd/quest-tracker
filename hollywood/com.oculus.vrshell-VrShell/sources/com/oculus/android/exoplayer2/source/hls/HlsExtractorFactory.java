package com.oculus.android.exoplayer2.source.hls;

import android.net.Uri;
import android.util.Pair;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.Extractor;
import com.oculus.android.exoplayer2.util.TimestampAdjuster;
import java.util.List;

public interface HlsExtractorFactory {
    public static final HlsExtractorFactory DEFAULT = new DefaultHlsExtractorFactory();

    Pair<Extractor, Boolean> createExtractor(Extractor extractor, Uri uri, Format format, List<Format> list, DrmInitData drmInitData, TimestampAdjuster timestampAdjuster);
}
