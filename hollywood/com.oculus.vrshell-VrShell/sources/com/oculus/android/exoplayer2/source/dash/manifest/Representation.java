package com.oculus.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.source.dash.DashSegmentIndex;
import com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase;
import java.util.Collections;
import java.util.List;

public abstract class Representation {
    public static final long REVISION_ID_DEFAULT = -1;
    public final String baseUrl;
    public final String contentId;
    public final Format format;
    public final List<Descriptor> inbandEventStreams;
    private final RangedUri initializationUri;
    public final long presentationTimeOffsetUs;
    public final long revisionId;

    public abstract String getCacheKey();

    public abstract DashSegmentIndex getIndex();

    public abstract RangedUri getIndexUri();

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase) {
        return newInstance(str, j, format2, str2, segmentBase, null);
    }

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<Descriptor> list) {
        return newInstance(str, j, format2, str2, segmentBase, list, null);
    }

    public static Representation newInstance(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<Descriptor> list, String str3) {
        if (segmentBase instanceof SegmentBase.SingleSegmentBase) {
            return new SingleSegmentRepresentation(str, j, format2, str2, (SegmentBase.SingleSegmentBase) segmentBase, list, str3, -1);
        }
        if (segmentBase instanceof SegmentBase.MultiSegmentBase) {
            return new MultiSegmentRepresentation(str, j, format2, str2, (SegmentBase.MultiSegmentBase) segmentBase, list);
        }
        throw new IllegalArgumentException("segmentBase must be of type SingleSegmentBase or MultiSegmentBase");
    }

    private Representation(String str, long j, Format format2, String str2, SegmentBase segmentBase, List<Descriptor> list) {
        List<Descriptor> list2;
        this.contentId = str;
        this.revisionId = j;
        this.format = format2;
        this.baseUrl = str2;
        if (list == null) {
            list2 = Collections.emptyList();
        } else {
            list2 = Collections.unmodifiableList(list);
        }
        this.inbandEventStreams = list2;
        this.initializationUri = segmentBase.getInitialization(this);
        this.presentationTimeOffsetUs = segmentBase.getPresentationTimeOffsetUs();
    }

    public RangedUri getInitializationUri() {
        return this.initializationUri;
    }

    public static class SingleSegmentRepresentation extends Representation {
        private final String cacheKey;
        public final long contentLength;
        private final RangedUri indexUri;
        private final SingleSegmentIndex segmentIndex;
        public final Uri uri;

        public static SingleSegmentRepresentation newInstance(String str, long j, Format format, String str2, long j2, long j3, long j4, long j5, List<Descriptor> list, String str3, long j6) {
            return new SingleSegmentRepresentation(str, j, format, str2, new SegmentBase.SingleSegmentBase(new RangedUri(null, j2, (j3 - j2) + 1), 1, 0, j4, (j5 - j4) + 1), list, str3, j6);
        }

        public SingleSegmentRepresentation(String str, long j, Format format, String str2, SegmentBase.SingleSegmentBase singleSegmentBase, List<Descriptor> list, String str3, long j2) {
            super(str, j, format, str2, singleSegmentBase, list);
            String str4;
            this.uri = Uri.parse(str2);
            this.indexUri = singleSegmentBase.getIndex();
            SingleSegmentIndex singleSegmentIndex = null;
            if (str3 != null) {
                str4 = str3;
            } else if (str != null) {
                str4 = str + "." + format.id + "." + j;
            } else {
                str4 = null;
            }
            this.cacheKey = str4;
            this.contentLength = j2;
            this.segmentIndex = this.indexUri == null ? new SingleSegmentIndex(new RangedUri(null, 0, j2)) : singleSegmentIndex;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return this.indexUri;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this.segmentIndex;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public String getCacheKey() {
            return this.cacheKey;
        }
    }

    public static class MultiSegmentRepresentation extends Representation implements DashSegmentIndex {
        private final SegmentBase.MultiSegmentBase segmentBase;

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public String getCacheKey() {
            return null;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public DashSegmentIndex getIndex() {
            return this;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.manifest.Representation
        public RangedUri getIndexUri() {
            return null;
        }

        public MultiSegmentRepresentation(String str, long j, Format format, String str2, SegmentBase.MultiSegmentBase multiSegmentBase, List<Descriptor> list) {
            super(str, j, format, str2, multiSegmentBase, list);
            this.segmentBase = multiSegmentBase;
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public RangedUri getSegmentUrl(long j) {
            return this.segmentBase.getSegmentUrl(this, j);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public long getSegmentNum(long j, long j2) {
            return this.segmentBase.getSegmentNum(j, j2);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public long getTimeUs(long j) {
            return this.segmentBase.getSegmentTimeUs(j);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public long getDurationUs(long j, long j2) {
            return this.segmentBase.getSegmentDurationUs(j, j2);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public long getFirstSegmentNum() {
            return this.segmentBase.getFirstSegmentNum();
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public int getSegmentCount(long j) {
            return this.segmentBase.getSegmentCount(j);
        }

        @Override // com.oculus.android.exoplayer2.source.dash.DashSegmentIndex
        public boolean isExplicit() {
            return this.segmentBase.isExplicit();
        }
    }
}
