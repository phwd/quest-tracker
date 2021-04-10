package com.oculus.video;

import android.text.TextUtils;
import com.oculus.android.exoplayer2.util.Assertions;
import com.oculus.video.audio.AudioChannelLayout;
import com.oculus.video.projection.ProjectionType;
import java.util.Arrays;
import java.util.HashMap;

public final class Settings {
    private static final int MAX_INITIAL_BITRATE_OCULUS = 12582912;
    private static final String TAG = "Settings";
    private static final String[] VIDEO_SOURCE_WHITELISTED_PROVIDERS = {"facebook"};
    private float abrBandwidthFraction;
    private int abrMaxDurationForQualityDecreaseMs;
    private int abrMaxInitialBitrate;
    private int abrMinDurationForQualityIncreaseMs;
    private int abrMinTimeBetweenQualityReevaluationMs;
    private int abrMinTimeBetweenQualitySwitchMs;
    private final HashMap<String, Feature> featureNameMap = new HashMap<>();
    private final HashMap<Feature, Boolean> featureStateMap = new HashMap<>();

    public boolean useFrameDropBasedTrackSelection(long j, float f) {
        return false;
    }

    public boolean useVideoDirectDecoder() {
        return true;
    }

    /* access modifiers changed from: private */
    public enum Feature {
        ENABLE_AUDIO_VIRTUALIZATION("enableAudioVirtualization", false),
        ENABLE_DATA_CACHE("enableDataCache", false),
        ENABLE_FAST_SEEK("enableFastSeek", false),
        ENABLE_FAST_SEEK_ON_DASH_LOAD("enableFastSeekOnDashLoad", false),
        ENABLE_OZ_ABR_CONFIG("enableOzABRConfig", false),
        ENABLE_EARLY_FRAME_RELEASE("enableEarlyFrameRelease", false),
        ENABLE_ABR4VBR("enableABR4VBR", false),
        ENABLE_SMOOTH_TRACK_SELECTION_OVERRIDE("enableSmoothTrackSelectionOverride", false),
        DISABLE_ANALYTICS_VIDEO_SOURCE_REDACTION("disableAnalyticsVideoSourceRedaction", false);
        
        public final boolean defaultValue;
        public final String name;

        private Feature(String str, boolean z) {
            this.name = str;
            this.defaultValue = z;
        }
    }

    Settings() {
        Feature[] values = Feature.values();
        for (Feature feature : values) {
            this.featureNameMap.put(feature.name, feature);
        }
        Feature[] values2 = Feature.values();
        for (Feature feature2 : values2) {
            this.featureStateMap.put(feature2, Boolean.valueOf(feature2.defaultValue));
        }
        configureABRParams(Feature.ENABLE_OZ_ABR_CONFIG.defaultValue);
    }

    private boolean isActive(Feature feature) {
        Assertions.checkState(this.featureStateMap.containsKey(feature));
        return this.featureStateMap.get(feature).booleanValue();
    }

    private void configureABRParams(boolean z) {
        if (z) {
            this.abrMaxInitialBitrate = 48234496;
            this.abrMinDurationForQualityIncreaseMs = 0;
            this.abrMaxDurationForQualityDecreaseMs = 11000;
            this.abrBandwidthFraction = 0.5f;
            this.abrMinTimeBetweenQualityReevaluationMs = 1000;
            this.abrMinTimeBetweenQualitySwitchMs = 5000;
            return;
        }
        this.abrMaxInitialBitrate = MAX_INITIAL_BITRATE_OCULUS;
        this.abrMinDurationForQualityIncreaseMs = 10000;
        this.abrMaxDurationForQualityDecreaseMs = 25000;
        this.abrBandwidthFraction = 0.75f;
        this.abrMinTimeBetweenQualityReevaluationMs = -1;
        this.abrMinTimeBetweenQualitySwitchMs = -1;
    }

    public void configure(String str, boolean z) {
        Feature feature = this.featureNameMap.get(str);
        if (feature != null) {
            this.featureStateMap.put(feature, Boolean.valueOf(z));
        }
        if (feature == Feature.ENABLE_OZ_ABR_CONFIG) {
            configureABRParams(z);
        }
    }

    public int getMaxInitialBitrate(long j) {
        return j > 0 ? (int) j : this.abrMaxInitialBitrate;
    }

    public int getMinDurationForQualityIncreaseMs() {
        return this.abrMinDurationForQualityIncreaseMs;
    }

    public int getMaxDurationForQualityDecreaseMs() {
        return this.abrMaxDurationForQualityDecreaseMs;
    }

    public float getTrackSelectionBandwidthFraction() {
        return this.abrBandwidthFraction;
    }

    public long getMinTimeBetweenQualityReevaluationMs() {
        return (long) this.abrMinTimeBetweenQualityReevaluationMs;
    }

    public long getMinTimeBetweenQualitySwitchMs() {
        return (long) this.abrMinTimeBetweenQualitySwitchMs;
    }

    public boolean redactAnalyticsVideoSource(String str) {
        if (Arrays.asList(VIDEO_SOURCE_WHITELISTED_PROVIDERS).contains(str.toLowerCase())) {
            return false;
        }
        return !isActive(Feature.DISABLE_ANALYTICS_VIDEO_SOURCE_REDACTION);
    }

    public boolean useDataCache(Video video) {
        if (video == null) {
            return false;
        }
        String videoUrl = video.getVideoUrl();
        if ((!TextUtils.isEmpty(videoUrl) && videoUrl.startsWith("http")) || video.isLiveMedia() || video.isSyncMedia() || !isActive(Feature.ENABLE_DATA_CACHE)) {
            return false;
        }
        return true;
    }

    /* renamed from: com.oculus.video.Settings$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$video$audio$AudioChannelLayout = new int[AudioChannelLayout.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.video.audio.AudioChannelLayout[] r0 = com.oculus.video.audio.AudioChannelLayout.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.video.Settings.AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout = r0
                int[] r0 = com.oculus.video.Settings.AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.video.audio.AudioChannelLayout r1 = com.oculus.video.audio.AudioChannelLayout.MONO     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.video.Settings.AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.video.audio.AudioChannelLayout r1 = com.oculus.video.audio.AudioChannelLayout.STEREO     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.video.Settings.AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.video.audio.AudioChannelLayout r1 = com.oculus.video.audio.AudioChannelLayout.S5_1     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.video.Settings.AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.video.audio.AudioChannelLayout r1 = com.oculus.video.audio.AudioChannelLayout.S7_1     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.video.Settings.AnonymousClass1.<clinit>():void");
        }
    }

    public boolean useAudioSpatialization(Video video) {
        if (video == null) {
            return false;
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$video$audio$AudioChannelLayout[video.getAudioChannelLayout().ordinal()];
        boolean z = true;
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            return true;
        }
        boolean z2 = video.getProjectionType() == ProjectionType.RECT;
        if (video.getProjectionType() != ProjectionType.EQUIRECTANGULAR || video.getFovX() > 180.0d) {
            z = false;
        }
        if (z2 || z) {
            return isActive(Feature.ENABLE_AUDIO_VIRTUALIZATION);
        }
        return false;
    }

    public boolean useFastSeek(boolean z) {
        return isActive(Feature.ENABLE_FAST_SEEK) && !z;
    }

    public boolean useFastSeekOnDashLoad(boolean z) {
        return isActive(Feature.ENABLE_FAST_SEEK_ON_DASH_LOAD) && !z;
    }

    public boolean useEarlyFrameRelease() {
        return isActive(Feature.ENABLE_EARLY_FRAME_RELEASE);
    }

    public boolean useABR4VBR() {
        return isActive(Feature.ENABLE_ABR4VBR);
    }

    public boolean useSmoothTrackSelectionOverride() {
        return isActive(Feature.ENABLE_SMOOTH_TRACK_SELECTION_OVERRIDE);
    }
}
