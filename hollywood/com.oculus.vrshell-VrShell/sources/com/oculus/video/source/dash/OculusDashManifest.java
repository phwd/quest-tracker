package com.oculus.video.source.dash;

import android.net.Uri;
import com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.Period;
import com.oculus.android.exoplayer2.source.dash.manifest.Representation;
import com.oculus.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.projection.ImageStereoMode;
import com.oculus.video.projection.ProjectionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OculusDashManifest extends DashManifest {
    public final AudioChannelLayout audioChannelLayout;
    public final Map<String, Double> fbVqmMap;
    public final Map<String, ProjectionType> projectionMap;
    public final ProjectionType projectionType;
    public final ImageStereoMode stereoMode;

    /* access modifiers changed from: package-private */
    public static class QualityPoint {
        public final double bitrate;
        public final double quality;
        public final Representation representation;

        QualityPoint(Representation representation2, Map<String, Double> map) {
            this.representation = representation2;
            this.quality = getSsimDb(map.get(representation2.format.id).doubleValue());
            this.bitrate = (double) representation2.format.bitrate;
        }

        private static double getSsimDb(double d) {
            if (d >= 1.0d) {
                return Double.MAX_VALUE;
            }
            if (d < 0.0d) {
                return 0.0d;
            }
            return Math.log10(1.0d / (1.0d - d)) * 10.0d;
        }

        /* access modifiers changed from: package-private */
        public double getSlopeTo(QualityPoint qualityPoint) {
            if (qualityPoint == null) {
                return Double.MAX_VALUE;
            }
            double d = qualityPoint.bitrate;
            double d2 = this.bitrate;
            if (d == d2) {
                return Double.MAX_VALUE;
            }
            return (qualityPoint.quality - this.quality) / (d - d2);
        }
    }

    public OculusDashManifest(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, UtcTimingElement utcTimingElement, Uri uri, List<Period> list, ProjectionType projectionType2, AudioChannelLayout audioChannelLayout2, ImageStereoMode imageStereoMode, Map<String, Double> map, Map<String, ProjectionType> map2) {
        super(j, j2, j3, z, j4, j5, j6, j7, utcTimingElement, uri, list);
        this.projectionType = projectionType2;
        this.audioChannelLayout = audioChannelLayout2;
        this.stereoMode = imageStereoMode;
        Map<String, Double> map3 = map;
        this.fbVqmMap = !isValidFbVqmMap(map3) ? null : map3;
        this.projectionMap = map2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isValidFbVqmMap(java.util.Map<java.lang.String, java.lang.Double> r10) {
        /*
        // Method dump skipped, instructions count: 166
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifest.isValidFbVqmMap(java.util.Map):boolean");
    }

    public final OculusDashManifest generateHighQualityBlend(OculusDashManifest oculusDashManifest) {
        List<AdaptationSet> blendAdaptationSets;
        HashMap hashMap;
        HashMap hashMap2;
        if (getPeriodCount() != 1 || oculusDashManifest.getPeriodCount() != 1 || this.dynamic || oculusDashManifest.dynamic || this.durationMs != oculusDashManifest.durationMs || this.stereoMode != oculusDashManifest.stereoMode) {
            return this;
        }
        Period period = getPeriod(0);
        Period period2 = oculusDashManifest.getPeriod(0);
        if (period.adaptationSets.size() != period2.adaptationSets.size() || (blendAdaptationSets = blendAdaptationSets(this.fbVqmMap, oculusDashManifest.fbVqmMap, period.adaptationSets, period2.adaptationSets)) == null) {
            return this;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Period(period.id, period.startMs, blendAdaptationSets, period.eventStreams));
        if (this.fbVqmMap != null) {
            HashMap hashMap3 = new HashMap();
            hashMap3.putAll(this.fbVqmMap);
            hashMap3.putAll(oculusDashManifest.fbVqmMap);
            hashMap = hashMap3;
        } else {
            hashMap = null;
        }
        if (this.projectionMap != null) {
            HashMap hashMap4 = new HashMap();
            hashMap4.putAll(this.projectionMap);
            hashMap4.putAll(oculusDashManifest.projectionMap);
            hashMap2 = hashMap4;
        } else {
            hashMap2 = null;
        }
        return new OculusDashManifest(this.availabilityStartTimeMs, this.durationMs, this.minBufferTimeMs, this.dynamic, this.minUpdatePeriodMs, this.timeShiftBufferDepthMs, this.suggestedPresentationDelayMs, this.publishTimeMs, this.utcTiming, this.location, arrayList, ProjectionType.MIXED360, this.audioChannelLayout, this.stereoMode, hashMap, hashMap2);
    }

    private static List<AdaptationSet> blendAdaptationSets(Map<String, Double> map, Map<String, Double> map2, List<AdaptationSet> list, List<AdaptationSet> list2) {
        AdaptationSet adaptationSet;
        List<Representation> blendRepresentations;
        ArrayList arrayList = new ArrayList();
        AdaptationSet adaptationSet2 = null;
        for (AdaptationSet adaptationSet3 : list2) {
            if (adaptationSet3.type == 2) {
                if (adaptationSet2 != null) {
                    return null;
                }
                adaptationSet2 = adaptationSet3;
            }
        }
        if (adaptationSet2 == null) {
            return null;
        }
        AdaptationSet adaptationSet4 = null;
        for (AdaptationSet adaptationSet5 : list) {
            if (adaptationSet5.type != 2) {
                adaptationSet5 = adaptationSet4;
                adaptationSet = adaptationSet5;
            } else if (adaptationSet4 != null || (blendRepresentations = blendRepresentations(map, map2, adaptationSet5.representations, adaptationSet2.representations)) == null) {
                return null;
            } else {
                adaptationSet = new AdaptationSet(adaptationSet5.id, adaptationSet5.type, blendRepresentations, adaptationSet5.accessibilityDescriptors, adaptationSet5.supplementalProperties);
            }
            arrayList.add(adaptationSet);
            adaptationSet4 = adaptationSet5;
        }
        if (adaptationSet4 == null) {
            return null;
        }
        return arrayList;
    }

    private static List<Representation> blendRepresentations(Map<String, Double> map, Map<String, Double> map2, List<Representation> list, List<Representation> list2) {
        List<Representation> highQualityBlendRepresentations = highQualityBlendRepresentations(map, map2, list, list2);
        if (highQualityBlendRepresentations != null) {
            return highQualityBlendRepresentations;
        }
        return highResolutionBlendRepresentations(list, list2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> highResolutionBlendRepresentations(java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> r8, java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> r9) {
        /*
        // Method dump skipped, instructions count: 234
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifest.highResolutionBlendRepresentations(java.util.List, java.util.List):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0152, code lost:
        if (r1.bitrate > r3.bitrate) goto L_0x012e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> highQualityBlendRepresentations(java.util.Map<java.lang.String, java.lang.Double> r9, java.util.Map<java.lang.String, java.lang.Double> r10, java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> r11, java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Representation> r12) {
        /*
        // Method dump skipped, instructions count: 444
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifest.highQualityBlendRepresentations(java.util.Map, java.util.Map, java.util.List, java.util.List):java.util.List");
    }
}
