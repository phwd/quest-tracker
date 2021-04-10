package com.oculus.video.source.dash;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Pair;
import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.drm.DrmInitData;
import com.oculus.android.exoplayer2.extractor.mp4.PsshAtomUtil;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.oculus.android.exoplayer2.source.dash.manifest.Descriptor;
import com.oculus.android.exoplayer2.source.dash.manifest.Period;
import com.oculus.android.exoplayer2.source.dash.manifest.Representation;
import com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.oculus.android.exoplayer2.source.dash.manifest.UtcTimingElement;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.XmlPullParserUtil;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.projection.ImageStereoMode;
import com.oculus.video.projection.ProjectionType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class OculusDashManifestParser extends DashManifestParser {
    private static final String AUDIO_CHANNEL_CONFIGURATION_ATTRIBUTE = "value";
    private static final String DASH_SCHEME_ID_URI = "urn:mpeg:dash:23003:3:audio_channel_configuration:2011";
    private static final String MPD_END_TAG = "</MPD>";
    private static final String TAG = "OculusDashManifestParser";
    private static final String VQM_DELIMITER_REGEX = "\\|";
    private AudioChannelLayout parsedAudioChannelLayout = AudioChannelLayout.UNKNOWN;
    private Map<String, Double> parsedFbVqmMap = null;
    private ProjectionType parsedProjection = ProjectionType.UNKNOWN;
    private Map<String, ProjectionType> parsedProjectionMap = null;
    private ImageStereoMode parsedStereoMode = ImageStereoMode.SM_2D;

    /* access modifiers changed from: private */
    public enum VqmVersion {
        CODEC_SSIM("codec_ssim"),
        CROSS_PROJECTION_SSIM360("cross_projection_ssim360"),
        SSIM360("ssim360"),
        FFMPEG_SSIM_FILTER("ffmpeg_filter_ssim");
        
        private final String value;

        private VqmVersion(String str) {
            this.value = str;
        }

        public String getValue() {
            return this.value;
        }
    }

    public OculusDashManifest parse(String str) throws IOException {
        String[] splitMixed360Manifest = splitMixed360Manifest(str);
        ArrayList<OculusDashManifest> arrayList = new ArrayList<>();
        for (String str2 : splitMixed360Manifest) {
            OculusDashManifest parse = parse(Uri.EMPTY, (InputStream) new ByteArrayInputStream(str2.getBytes(StandardCharsets.UTF_8)));
            Assertions.checkNotNull(parse);
            arrayList.add(parse);
        }
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() == 1) {
            return arrayList.get(0);
        }
        return getBlended360Manifest(arrayList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.video.source.dash.OculusDashManifestParser$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$projection$ProjectionType = new int[ProjectionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.video.projection.ProjectionType[] r0 = com.oculus.video.projection.ProjectionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.video.source.dash.OculusDashManifestParser.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ProjectionType = r0
                int[] r0 = com.oculus.video.source.dash.OculusDashManifestParser.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ProjectionType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.video.projection.ProjectionType r1 = com.oculus.video.projection.ProjectionType.EQUIRECTANGULAR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.video.source.dash.OculusDashManifestParser.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ProjectionType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.video.projection.ProjectionType r1 = com.oculus.video.projection.ProjectionType.BARREL     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifestParser.AnonymousClass1.<clinit>():void");
        }
    }

    private OculusDashManifest getBlended360Manifest(ArrayList<OculusDashManifest> arrayList) {
        Iterator<OculusDashManifest> it = arrayList.iterator();
        OculusDashManifest oculusDashManifest = null;
        OculusDashManifest oculusDashManifest2 = null;
        while (it.hasNext()) {
            OculusDashManifest next = it.next();
            int i = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ProjectionType[next.projectionType.ordinal()];
            if (i == 1) {
                oculusDashManifest2 = next;
            } else if (i == 2) {
                oculusDashManifest = next;
            }
        }
        if (oculusDashManifest == null) {
            return oculusDashManifest2;
        }
        if (oculusDashManifest2 == null) {
            return oculusDashManifest;
        }
        try {
            return oculusDashManifest2.generateHighQualityBlend(oculusDashManifest);
        } catch (Throwable th) {
            Log.d(TAG, "Exception blending manifests : " + th.getMessage());
            return oculusDashManifest2;
        }
    }

    private static String[] splitMixed360Manifest(String str) {
        String[] split = str.split(MPD_END_TAG);
        ArrayList arrayList = new ArrayList();
        for (String str2 : split) {
            String trim = str2.trim();
            if (trim.length() != 0) {
                arrayList.add(trim + MPD_END_TAG);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(str);
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* Return type fixed from 'com.oculus.video.source.dash.OculusDashManifest' to match base method */
    @Override // com.oculus.android.exoplayer2.upstream.ParsingLoadable.Parser, com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser, com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser
    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        this.parsedProjection = ProjectionType.UNKNOWN;
        this.parsedAudioChannelLayout = AudioChannelLayout.UNKNOWN;
        this.parsedStereoMode = ImageStereoMode.SM_2D;
        this.parsedFbVqmMap = null;
        this.parsedProjectionMap = null;
        return (OculusDashManifest) super.parse(uri, inputStream);
    }

    private void parseFBAdaptationSetAttribs(XmlPullParser xmlPullParser) {
        String parseString = parseString(xmlPullParser, "FBProjection", ProjectionType.UNKNOWN.videoLayout);
        if (!parseString.equalsIgnoreCase(ProjectionType.UNKNOWN.videoLayout)) {
            this.parsedProjection = ProjectionType.fromFBProjection(parseString);
            String parseString2 = parseString(xmlPullParser, "FBStereoMode", "");
            if (!TextUtils.isEmpty(parseString2) && !parseString2.equalsIgnoreCase("mono")) {
                if (parseString2.equalsIgnoreCase("top-bottom")) {
                    this.parsedStereoMode = ImageStereoMode.SM_TOP_BOTTOM_3D;
                } else if (parseString2.equalsIgnoreCase("left-right")) {
                    this.parsedStereoMode = ImageStereoMode.SM_LEFT_RIGHT_3D;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0201 A[LOOP:0: B:1:0x0064->B:56:0x0201, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01ce A[EDGE_INSN: B:57:0x01ce->B:50:0x01ce ?: BREAK  , SYNTHETIC] */
    @Override // com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r37, java.lang.String r38, com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase r39) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 528
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.lang.String, com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase):com.oculus.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0194 A[LOOP:0: B:23:0x00ac->B:65:0x0194, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0165 A[EDGE_INSN: B:66:0x0165->B:59:0x0165 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.oculus.video.source.dash.OculusDashManifestParser.FBRepresentationInfo parseFBRepresentation(org.xmlpull.v1.XmlPullParser r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, int r26, int r27, float r28, int r29, int r30, java.lang.String r31, int r32, java.util.List<com.oculus.android.exoplayer2.source.dash.manifest.Descriptor> r33, com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase r34) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        // Method dump skipped, instructions count: 413
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.source.dash.OculusDashManifestParser.parseFBRepresentation(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, int, java.util.List, com.oculus.android.exoplayer2.source.dash.manifest.SegmentBase):com.oculus.video.source.dash.OculusDashManifestParser$FBRepresentationInfo");
    }

    private Representation buildRepresentation(FBRepresentationInfo fBRepresentationInfo, String str, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format format = fBRepresentationInfo.format;
        ArrayList<DrmInitData.SchemeData> arrayList3 = fBRepresentationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            format = format.copyWithDrmInitData(new DrmInitData(arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = fBRepresentationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(str, -1, format, fBRepresentationInfo.baseUrl, fBRepresentationInfo.segmentBase, arrayList4);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser
    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        AudioChannelLayout audioChannelLayout;
        int i = -1;
        if (DASH_SCHEME_ID_URI.equals(parseString(xmlPullParser, "schemeIdUri", null))) {
            i = parseInt(xmlPullParser, AUDIO_CHANNEL_CONFIGURATION_ATTRIBUTE, -1);
        }
        do {
            xmlPullParser.next();
            if (this.parsedAudioChannelLayout == AudioChannelLayout.UNKNOWN) {
                String attributeValue = xmlPullParser.getAttributeValue(null, AUDIO_CHANNEL_CONFIGURATION_ATTRIBUTE);
                if (attributeValue == null) {
                    audioChannelLayout = AudioChannelLayout.UNKNOWN;
                } else {
                    audioChannelLayout = AudioChannelLayout.fromChannelConfiguration(attributeValue);
                }
                this.parsedAudioChannelLayout = audioChannelLayout;
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser
    public Pair<String, DrmInitData.SchemeData> parseContentProtection(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        DrmInitData.SchemeData schemeData = null;
        boolean equals = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95".equals(xmlPullParser.getAttributeValue(null, "schemeIdUri"));
        String attributeValue = xmlPullParser.getAttributeValue(null, AUDIO_CHANNEL_CONFIGURATION_ATTRIBUTE);
        byte[] bArr = null;
        UUID uuid = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (bArr == null && XmlPullParserUtil.isStartTag(xmlPullParser, "cenc:pssh") && xmlPullParser.next() == 4) {
                bArr = Base64.decode(xmlPullParser.getText(), 0);
                uuid = PsshAtomUtil.parseUuid(bArr);
                if (uuid == null) {
                    bArr = null;
                }
            } else if (bArr == null && equals && XmlPullParserUtil.isStartTag(xmlPullParser, "mspr:pro") && xmlPullParser.next() == 4) {
                bArr = PsshAtomUtil.buildPsshAtom(C.PLAYREADY_UUID, Base64.decode(xmlPullParser.getText(), 0));
                uuid = C.PLAYREADY_UUID;
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "widevine:license")) {
                String attributeValue2 = xmlPullParser.getAttributeValue(null, "robustness_level");
                z = attributeValue2 != null && attributeValue2.startsWith("HW");
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "ContentProtection"));
        if (uuid != null) {
            schemeData = new DrmInitData.SchemeData(uuid, MimeTypes.VIDEO_MP4, bArr, z);
        }
        return Pair.create(attributeValue, schemeData);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.source.dash.manifest.DashManifestParser
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, UtcTimingElement utcTimingElement, Uri uri, List<Period> list) {
        return new OculusDashManifest(j, j2, j3, z, j4, j5, j6, j7, utcTimingElement, uri, list, this.parsedProjection, this.parsedAudioChannelLayout, this.parsedStereoMode, this.parsedFbVqmMap, this.parsedProjectionMap);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    /* access modifiers changed from: private */
    public static class FBRepresentationInfo {
        final String baseUrl;
        final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        final Format format;
        final ArrayList<Descriptor> inbandEventStreams;
        final SegmentBase segmentBase;

        FBRepresentationInfo(Format format2, String str, SegmentBase segmentBase2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
            this.format = format2;
            this.baseUrl = str;
            this.segmentBase = segmentBase2;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
        }
    }
}
