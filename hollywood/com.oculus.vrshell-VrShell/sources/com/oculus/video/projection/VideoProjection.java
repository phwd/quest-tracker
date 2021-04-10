package com.oculus.video.projection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.oculus.android.exoplayer2.Format;
import com.oculus.video.source.dash.OculusDashManifest;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class VideoProjection {
    public static final String METADATA_SOURCE_API = "API";
    public static final String METADATA_SOURCE_FB = "FB";
    public static final String METADATA_SOURCE_V1 = "V1";
    public static final String METADATA_SOURCE_V2 = "V2";
    private static final String STEREO_MODE_LEFT_RIGHT = "left-right";
    private static final String STEREO_MODE_MONO = "mono";
    private static final String STEREO_MODE_TOP_BOTTOM = "top-bottom";
    private static final String TAG = "VideoProjection";
    private static final String XML_TAG_CROPPED_HEIGHT = "GSpherical:CroppedAreaImageHeightPixels";
    private static final String XML_TAG_CROPPED_WIDTH = "GSpherical:CroppedAreaImageWidthPixels";
    private static final String XML_TAG_FULL_PANO_HEIGHT = "GSpherical:FullPanoHeightPixels";
    private static final String XML_TAG_FULL_PANO_WIDTH = "GSpherical:FullPanoWidthPixels";
    private static final String XML_TAG_PROJECTION_TYPE = "GSpherical:ProjectionType";
    private static final String XML_TAG_STEREO_MODE = "GSpherical:StereoMode";
    private final boolean isFullRect3D;
    private final String metadataSource;
    @NonNull
    private final ProjectionData projectionData;
    private final ImageStereoMode stereoMode;

    public static VideoProjection selectPreferred(VideoProjection videoProjection, VideoProjection videoProjection2) {
        String[] strArr = {METADATA_SOURCE_V2, METADATA_SOURCE_V1, METADATA_SOURCE_FB, METADATA_SOURCE_API};
        for (String str : strArr) {
            if (videoProjection2 != null && str.equals(videoProjection2.metadataSource)) {
                return videoProjection2;
            }
            if (videoProjection != null && str.equals(videoProjection.metadataSource)) {
                return videoProjection;
            }
        }
        return videoProjection2;
    }

    @NonNull
    public static VideoProjection sanitizeAPIProjectionMetadata(@NonNull VideoProjection videoProjection, float f, int i) {
        if (!METADATA_SOURCE_API.equals(videoProjection.metadataSource)) {
            return videoProjection;
        }
        ProjectionType projectionType = videoProjection.getProjectionType();
        ProjectionData projectionData2 = videoProjection.projectionData;
        ImageStereoMode imageStereoMode = videoProjection.stereoMode;
        int i2 = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ProjectionType[projectionType.ordinal()];
        boolean z = true;
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            return new VideoProjection(METADATA_SOURCE_API, new DefaultProjectionData(projectionType), imageStereoMode == ImageStereoMode.SM_2D ? ImageStereoMode.SM_2D : ImageStereoMode.SM_TOP_BOTTOM_3D, false);
        }
        if (i2 == 4) {
            new VideoProjection(METADATA_SOURCE_API, new DefaultProjectionData(projectionType), imageStereoMode == ImageStereoMode.SM_2D ? ImageStereoMode.SM_2D : ImageStereoMode.SM_LEFT_RIGHT_3D, false);
        }
        if (f <= 0.0f) {
            return videoProjection;
        }
        if (i % 180 == 90) {
            f = 1.0f / f;
        }
        if (projectionType == ProjectionType.RECT && imageStereoMode != ImageStereoMode.SM_2D && !videoProjection.isFullRect3D) {
            boolean z2 = f > 2.37037f;
            boolean z3 = f < 1.185185f;
            int i3 = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode[imageStereoMode.ordinal()];
            if (i3 == 1 || i3 == 2) {
                return new VideoProjection(METADATA_SOURCE_API, projectionData2, imageStereoMode, z2);
            }
            if (i3 == 3 || i3 == 4) {
                return new VideoProjection(METADATA_SOURCE_API, projectionData2, imageStereoMode, z3);
            }
        }
        if (!((projectionType == ProjectionType.EQUIRECTANGULAR && Math.abs(projectionData2.leftFovProperties.fovXDegrees - 180.0f) < Math.ulp(180.0f) && Math.abs(projectionData2.leftFovProperties.fovYDegrees - 180.0f) < Math.ulp(180.0f)) || projectionType == ProjectionType.VR180 || projectionType == ProjectionType.FISHEYE)) {
            z = false;
        }
        if (!z || imageStereoMode == ImageStereoMode.SM_2D) {
            return videoProjection;
        }
        if (f <= 1.1f || imageStereoMode == ImageStereoMode.SM_RIGHT_LEFT_3D) {
            return (1.0f / f <= 1.1f || imageStereoMode == ImageStereoMode.SM_BOTTOM_TOP_3D) ? videoProjection : new VideoProjection(METADATA_SOURCE_API, projectionData2, ImageStereoMode.SM_TOP_BOTTOM_3D, false);
        }
        return new VideoProjection(METADATA_SOURCE_API, projectionData2, ImageStereoMode.SM_LEFT_RIGHT_3D, false);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.video.projection.VideoProjection$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$projection$ImageStereoMode = new int[ImageStereoMode.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$projection$ProjectionType = new int[ProjectionType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x005e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0068 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0072 */
        static {
            /*
            // Method dump skipped, instructions count: 125
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.projection.VideoProjection.AnonymousClass1.<clinit>():void");
        }
    }

    @Nullable
    public static VideoProjection fromFBProjection(OculusDashManifest oculusDashManifest) {
        if (oculusDashManifest == null || oculusDashManifest.projectionType == null || oculusDashManifest.projectionType == ProjectionType.UNKNOWN) {
            return null;
        }
        return new VideoProjection(oculusDashManifest.projectionType, oculusDashManifest.stereoMode == ImageStereoMode.UNKNOWN ? ImageStereoMode.SM_2D : oculusDashManifest.stereoMode, false);
    }

    private VideoProjection(ProjectionType projectionType, ImageStereoMode imageStereoMode, boolean z) {
        this.metadataSource = METADATA_SOURCE_FB;
        this.projectionData = new DefaultProjectionData(projectionType);
        this.stereoMode = imageStereoMode;
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode[imageStereoMode.ordinal()];
        boolean z2 = false;
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            if (z && this.projectionData.projectionType == ProjectionType.RECT) {
                z2 = true;
            }
            this.isFullRect3D = z2;
            return;
        }
        this.isFullRect3D = false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public VideoProjection(org.json.JSONObject r12) throws org.json.JSONException {
        /*
        // Method dump skipped, instructions count: 390
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.projection.VideoProjection.<init>(org.json.JSONObject):void");
    }

    @Nullable
    public static VideoProjection fromSphericalV2(Format format) {
        try {
            VideoProjection videoProjection = new VideoProjection(format);
            if (videoProjection.getProjectionType() == ProjectionType.UNKNOWN) {
                return null;
            }
            return videoProjection;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    private VideoProjection(Format format) throws IllegalArgumentException {
        this.metadataSource = METADATA_SOURCE_V2;
        this.projectionData = new Mp4ProjectionData();
        validateFormat(format);
        this.stereoMode = ImageStereoMode.fromExoPlayer(format.stereoMode);
        this.isFullRect3D = false;
        try {
            this.projectionData.parse(format.projectionData);
        } catch (Exception e) {
            if (e instanceof IllegalArgumentException) {
                throw ((IllegalArgumentException) e);
            }
            throw new IllegalArgumentException("Unable to parse parse projection data: " + e.getMessage());
        }
    }

    private static String getTagValue(Document document, String str, String str2) {
        String textContent;
        NodeList elementsByTagName = document.getElementsByTagName(str);
        return (elementsByTagName.getLength() <= 0 || (textContent = elementsByTagName.item(0).getTextContent()) == null) ? str2 : textContent.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0068 A[Catch:{ Exception -> 0x00c3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e A[Catch:{ Exception -> 0x00c3 }] */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.oculus.video.projection.VideoProjection fromSphericalV1Xml(java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 219
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.projection.VideoProjection.fromSphericalV1Xml(java.lang.String):com.oculus.video.projection.VideoProjection");
    }

    private VideoProjection(@NonNull String str, @NonNull ProjectionData projectionData2, @NonNull ImageStereoMode imageStereoMode, boolean z) {
        this.metadataSource = str;
        this.projectionData = projectionData2;
        this.stereoMode = imageStereoMode;
        this.isFullRect3D = z;
    }

    private void validateFormat(Format format) throws IllegalArgumentException {
        if (format == null) {
            throw new IllegalArgumentException("Invalid format");
        } else if (format.projectionData == null) {
            throw new IllegalArgumentException("Invalid projection data");
        } else if (format.stereoMode < 0) {
            throw new IllegalArgumentException("Invalid stereo mode");
        }
    }

    public JSONObject toJSONObject() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ProjectionMetadataSource", this.metadataSource);
            if (this.stereoMode != ImageStereoMode.UNKNOWN) {
                jSONObject.put("StereoMode", getStereoModeString());
            }
            JSONObject jSONObject2 = this.projectionData.toJSONObject();
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    public String getMetadataSource() {
        return this.metadataSource;
    }

    public ImageStereoMode getStereoMode() {
        return this.stereoMode;
    }

    public String getStereoModeString() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode[this.stereoMode.ordinal()];
        if (i == 1) {
            return this.isFullRect3D ? "3DLRF" : "3DLR";
        }
        if (i == 2) {
            return this.isFullRect3D ? "3DRLF" : "3DRL";
        }
        if (i == 3) {
            return this.isFullRect3D ? "3DTBF" : "3DTB";
        }
        if (i != 4) {
            return "2D";
        }
        return this.isFullRect3D ? "3DBTF" : "3DBT";
    }

    @NonNull
    public ProjectionData getProjectionData() {
        return this.projectionData;
    }

    public ProjectionType getProjectionType() {
        return this.projectionData.projectionType;
    }
}
