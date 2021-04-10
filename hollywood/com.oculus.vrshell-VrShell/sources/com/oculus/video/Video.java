package com.oculus.video;

import android.net.Uri;
import android.text.TextUtils;
import com.oculus.android.exoplayer2.source.dash.manifest.DashManifest;
import com.oculus.android.exoplayer2.util.Util;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.drm.DrmProxyResponseFormat;
import com.oculus.video.projection.ImageStereoMode;
import com.oculus.video.projection.ProjectionType;
import com.oculus.video.projection.VideoProjection;
import com.oculus.video.source.dash.OculusDashManifest;
import com.oculus.video.source.dash.OculusDashManifestParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Video extends JSONObject {
    private final DashManifest dashManifest;
    private VideoProjection projectionMetadata;
    private final Uri videoUri;

    public static Video createFromVideoUrl(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("VideoUrl", str);
        return new Video(jSONObject.toString());
    }

    public static Video createFromDashManifestXml(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("DashManifestXml", str);
        return new Video(jSONObject.toString());
    }

    public Video(String str) throws JSONException {
        super(str);
        String videoUrl = getVideoUrl();
        this.videoUri = Uri.parse(VideoPlayer_Android.isSideLoaded(videoUrl) ? Uri.encode(videoUrl, ":/") : videoUrl);
        OculusDashManifest oculusDashManifest = null;
        try {
            String dashManifestPlaylist = getDashManifestPlaylist();
            if (!TextUtils.isEmpty(dashManifestPlaylist)) {
                oculusDashManifest = new OculusDashManifestParser().parse(dashManifestPlaylist);
            }
        } catch (IOException unused) {
        }
        this.dashManifest = oculusDashManifest;
        this.projectionMetadata = new VideoProjection(this);
    }

    public Video setInitialWidth(int i) throws JSONException {
        put("InitialWidth", i);
        return this;
    }

    public Video setInitialHeight(int i) throws JSONException {
        put("InitialHeight", i);
        return this;
    }

    public Video setDuration(long j) throws JSONException {
        if (j < 0) {
            j = 0;
        }
        put("Duration", j);
        return this;
    }

    public Video setIsSeekable(boolean z) throws JSONException {
        put("IsSeekable", z);
        return this;
    }

    public Video setAudioMimeType(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        put("AudioMimeType", str);
        return this;
    }

    public Video setVideoMimeType(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        put("VideoMimeType", str);
        return this;
    }

    public Video setSubtitleMimeTypes(Collection collection) throws JSONException {
        if (collection != null && !collection.isEmpty()) {
            put("SubtitleMimeTypes", new JSONArray(collection));
        }
        return this;
    }

    public Video setAudioTracks(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        put("AudioTracks", jSONObject);
        return this;
    }

    public Video setSubtitleTracks(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        put("SubtitleTracks", jSONObject);
        return this;
    }

    public Video setVideoTracks(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return this;
        }
        put("VideoTracks", jSONObject);
        return this;
    }

    public Video setAudioChannelLayout(AudioChannelLayout audioChannelLayout) {
        try {
            put("AudioChannelLayout", audioChannelLayout.key);
        } catch (JSONException unused) {
        }
        return this;
    }

    public Video setFrameAspectRatio(float f) throws JSONException {
        put("FrameAspectRatio", (double) f);
        return this;
    }

    public Video setFrameRate(float f) throws JSONException {
        if (f <= 0.0f) {
            return this;
        }
        put("FrameRate", (double) f);
        return this;
    }

    public Video setFrameRotation(int i) throws JSONException {
        put("FrameRotation", i);
        return this;
    }

    public Video setCanUseExoPlayer(boolean z) throws JSONException {
        put("CanUseExoPlayer", z);
        return this;
    }

    public Video setVideoRendererName(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        put("VideoRendererName", str);
        return this;
    }

    public Video setAudioRendererName(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        put("AudioRendererName", str);
        return this;
    }

    public Video setHasCameraMotionData(boolean z) throws JSONException {
        put("HasCameraMotionData", z);
        return this;
    }

    private void clearProjectionMetadata() throws JSONException {
        remove("ProjectionMetadataSource");
        remove("Projection");
        remove("StereoMode");
        remove("FovX");
        remove("FovY");
        remove("Horizon");
        remove("Meridian");
        remove("RightFov");
        remove("Pose");
        remove("VR180MeshData");
    }

    public Video setProjectionMetadata(VideoProjection videoProjection) throws JSONException {
        JSONObject jSONObject;
        if (videoProjection == null) {
            return this;
        }
        if ((getChannel().equalsIgnoreCase("com.oculus.mediagallery") || getChannel().equalsIgnoreCase("com.oculus.cinema")) && VideoProjection.METADATA_SOURCE_V1.equals(videoProjection.getMetadataSource())) {
            return this;
        }
        if ((getOverrideProjectionMetadata() && !VideoProjection.METADATA_SOURCE_API.equals(videoProjection.getMetadataSource())) || VideoProjection.selectPreferred(this.projectionMetadata, videoProjection) == this.projectionMetadata || (jSONObject = videoProjection.toJSONObject()) == null) {
            return this;
        }
        clearProjectionMetadata();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            put(next, jSONObject.get(next));
        }
        this.projectionMetadata = videoProjection;
        return this;
    }

    private String getDashManifestPlaylist() {
        try {
            return getString("DashManifestXml");
        } catch (JSONException unused) {
            return "";
        }
    }

    public boolean getOverrideProjectionMetadata() {
        try {
            return getBoolean("OverrideProjectionMetadata");
        } catch (JSONException unused) {
            return false;
        }
    }

    public VideoProjection getProjectionMetadata() {
        return this.projectionMetadata;
    }

    public String getVideoId() {
        try {
            return getString("VideoId");
        } catch (JSONException unused) {
            return "";
        }
    }

    public String getProvider() {
        try {
            return getString("Provider");
        } catch (JSONException unused) {
            return "";
        }
    }

    public String getChannel() {
        try {
            return getString("Channel");
        } catch (JSONException unused) {
            return "";
        }
    }

    public int getInitialWidth() {
        try {
            return getInt("InitialWidth");
        } catch (JSONException unused) {
            return 0;
        }
    }

    public int getInitialHeight() {
        try {
            return getInt("InitialHeight");
        } catch (JSONException unused) {
            return 0;
        }
    }

    public long getDuration() {
        try {
            return getLong("Duration");
        } catch (JSONException unused) {
            return 0;
        }
    }

    public boolean getIsSeekable() {
        try {
            return getBoolean("IsSeekable");
        } catch (JSONException unused) {
            return true;
        }
    }

    public String getDrmSecurityLevel() {
        try {
            return getString("DrmSecurityLevel");
        } catch (JSONException unused) {
            return "";
        }
    }

    public DrmProxyResponseFormat getDrmResponseFormat() {
        try {
            return DrmProxyResponseFormat.getDrmProxyResponseFormatFromString(getString("DrmResponseFormat"));
        } catch (JSONException unused) {
            return DrmProxyResponseFormat.BYTECODE;
        }
    }

    public String getVideoUrl() {
        try {
            return getString("VideoUrl");
        } catch (JSONException unused) {
            return "";
        }
    }

    public Uri getVideoUri() {
        return this.videoUri;
    }

    public String getVideoFullPath() {
        String uri = getVideoUri().toString();
        String path = getVideoUri().getPath();
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        int indexOf = uri.indexOf(path);
        return indexOf < 0 ? path : uri.substring(indexOf);
    }

    public int getContentType() {
        if (getDashManifest() != null) {
            return 0;
        }
        return Util.inferContentType(getVideoUrl());
    }

    public String getDrmProxyUrl() {
        try {
            return getString("DrmProxyUrl");
        } catch (JSONException unused) {
            return "";
        }
    }

    public DashManifest getDashManifest() {
        return this.dashManifest;
    }

    public ProjectionType getProjectionType() {
        return this.projectionMetadata.getProjectionType();
    }

    public String getStereoMode() {
        try {
            return getString("StereoMode");
        } catch (JSONException unused) {
            return "";
        }
    }

    /* renamed from: com.oculus.video.Video$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$projection$ImageStereoMode = new int[ImageStereoMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|(3:11|12|14)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.video.projection.ImageStereoMode[] r0 = com.oculus.video.projection.ImageStereoMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode = r0
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.SM_LEFT_RIGHT_3D     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.SM_RIGHT_LEFT_3D     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.SM_TOP_BOTTOM_3D     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.SM_BOTTOM_TOP_3D     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.SM_2D     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.video.Video.AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.video.projection.ImageStereoMode r1 = com.oculus.video.projection.ImageStereoMode.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.Video.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean is3D() {
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$projection$ImageStereoMode[this.projectionMetadata.getStereoMode().ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    public double getFovX() {
        try {
            return getDouble("FovX");
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    public double getFovY() {
        try {
            return getDouble("FovY");
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    public boolean isSpatialAudioFocusEnabled() {
        try {
            return getBoolean("EnableFocus");
        } catch (JSONException unused) {
            return false;
        }
    }

    public double getSpatialAudioOffFocusLevel() {
        try {
            return getDouble("OffFocusLevel");
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    public double getSpatialAudioFocusWidthDegrees() {
        try {
            return getDouble("FocusWidthDegrees");
        } catch (JSONException unused) {
            return 0.0d;
        }
    }

    public JSONObject getAudioTracks() {
        try {
            return getJSONObject("AudioTracks");
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean hasSpatialAudio() {
        JSONObject audioTracks = getAudioTracks();
        if (audioTracks == null || audioTracks.length() <= 0) {
            return false;
        }
        try {
            if (audioTracks.getInt(audioTracks.keys().next()) > 2) {
                return true;
            }
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    public AudioChannelLayout getAudioChannelLayout() {
        try {
            return AudioChannelLayout.fromString(getString("AudioChannelLayout"));
        } catch (JSONException unused) {
            return AudioChannelLayout.UNKNOWN;
        }
    }

    public int getPrimaryAudioTrackIndex() {
        JSONObject audioTracks = getAudioTracks();
        if (audioTracks == null || audioTracks.length() <= 0) {
            return -1;
        }
        return Integer.parseInt(audioTracks.keys().next());
    }

    public JSONObject getSubtitleTracks() {
        try {
            return getJSONObject("SubtitleTracks");
        } catch (JSONException unused) {
            return null;
        }
    }

    public JSONObject getVideoTracks() {
        try {
            return getJSONObject("VideoTracks");
        } catch (JSONException unused) {
            return null;
        }
    }

    public Collection<String> getAvailableSubtitles() {
        ArrayList arrayList = new ArrayList();
        JSONObject subtitleTracks = getSubtitleTracks();
        if (subtitleTracks == null) {
            return arrayList;
        }
        Iterator<String> keys = subtitleTracks.keys();
        while (keys.hasNext()) {
            try {
                arrayList.add(subtitleTracks.getString(keys.next()));
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    public boolean hasSubtitle() {
        JSONObject subtitleTracks = getSubtitleTracks();
        return subtitleTracks != null && subtitleTracks.length() > 0;
    }

    public boolean canUseExoPlayer() {
        try {
            return getBoolean("CanUseExoPlayer");
        } catch (JSONException unused) {
            return true;
        }
    }

    public String getVideoRendererName() {
        try {
            return getString("VideoRendererName");
        } catch (JSONException unused) {
            return "";
        }
    }

    public String getAudioRendererName() {
        try {
            return getString("AudioRendererName");
        } catch (JSONException unused) {
            return "";
        }
    }

    public boolean hasCameraMotionData() {
        try {
            return getBoolean("HasCameraMotionData");
        } catch (JSONException unused) {
            return true;
        }
    }

    public JSONObject getHttpRequestHeaders() {
        try {
            return getJSONObject("HttpRequestHeaders");
        } catch (JSONException unused) {
            return null;
        }
    }

    public String getVideoMimeType() {
        try {
            return getString("VideoMimeType");
        } catch (JSONException unused) {
            return "";
        }
    }

    public String getAudioMimeType() {
        try {
            return getString("AudioMimeType");
        } catch (JSONException unused) {
            return "";
        }
    }

    public JSONArray getSubtitleMimeTypes() {
        try {
            return getJSONArray("SubtitleMimeTypes");
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean isSyncMedia() {
        try {
            return getBoolean("IsSyncMedia");
        } catch (JSONException unused) {
            return false;
        }
    }

    public boolean isLiveMedia() {
        try {
            return getBoolean("IsLiveMedia");
        } catch (JSONException unused) {
            return false;
        }
    }

    public int getFrameRotation() {
        try {
            return getInt("FrameRotation");
        } catch (JSONException unused) {
            return 0;
        }
    }

    public float getFrameAspectRatio() {
        try {
            return (float) getDouble("FrameAspectRatio");
        } catch (JSONException unused) {
            return 0.0f;
        }
    }

    public float getFrameRate() {
        try {
            return (float) getInt("FrameRate");
        } catch (JSONException unused) {
            return 0.0f;
        }
    }

    public String getAes128Key() {
        try {
            return getString("Aes128Key");
        } catch (JSONException unused) {
            return "";
        }
    }

    public String getAes128Iv() {
        try {
            return getString("Aes128Iv");
        } catch (JSONException unused) {
            return "";
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0061 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String extractSubtitleMimeType(java.lang.String r5) {
        /*
            r0 = 1
            if (r5 == 0) goto L_0x001f
            r1 = 46
            int r1 = r5.indexOf(r1)
            if (r1 <= 0) goto L_0x001f
            java.lang.String r1 = "."
            int r1 = r5.lastIndexOf(r1)
            int r1 = r1 + r0
            int r2 = r5.length()
            java.lang.String r5 = r5.substring(r1, r2)
            java.lang.String r5 = r5.toLowerCase()
            goto L_0x0021
        L_0x001f:
            java.lang.String r5 = ""
        L_0x0021:
            r1 = -1
            int r2 = r5.hashCode()
            r3 = 96897(0x17a81, float:1.35782E-40)
            r4 = 2
            if (r2 == r3) goto L_0x004b
            r3 = 114165(0x1bdf5, float:1.59979E-40)
            if (r2 == r3) goto L_0x0041
            r3 = 114177(0x1be01, float:1.59996E-40)
            if (r2 == r3) goto L_0x0037
            goto L_0x0055
        L_0x0037:
            java.lang.String r2 = "ssa"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x0055
            r5 = r4
            goto L_0x0056
        L_0x0041:
            java.lang.String r2 = "srt"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x0055
            r5 = 0
            goto L_0x0056
        L_0x004b:
            java.lang.String r2 = "ass"
            boolean r5 = r5.equals(r2)
            if (r5 == 0) goto L_0x0055
            r5 = r0
            goto L_0x0056
        L_0x0055:
            r5 = r1
        L_0x0056:
            if (r5 == 0) goto L_0x0061
            if (r5 == r0) goto L_0x005e
            if (r5 == r4) goto L_0x005e
            r5 = 0
            return r5
        L_0x005e:
            java.lang.String r5 = "text/x-ssa"
            return r5
        L_0x0061:
            java.lang.String r5 = "application/x-subrip"
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.Video.extractSubtitleMimeType(java.lang.String):java.lang.String");
    }
}
