package com.oculus.vrshell.config;

public class PanelAppConfiguration {
    private final boolean allowImeComposition;
    private final boolean allowMultiInstance;
    private final boolean clearOnAppLaunch;
    private final String initialCooperativeState;
    private final boolean isWellKnownPanelType;
    public final long lastUpdateTime;
    private final int[] layerOptions;
    private final boolean needsActivityToken;
    private final String packageName;
    private final int packageUid;
    private final String serviceName;
    private final boolean supportsLayers;
    private final boolean supportsMultiApp;
    private final String[] surfaceNames;
    private final boolean validService;
    private final boolean verifiedUid;
    private final String versionCode;
    private final String versionName;

    private PanelAppConfiguration(Builder builder) {
        this.packageName = builder.nestedPackageName;
        this.packageUid = builder.nestedPackageUid;
        this.serviceName = builder.nestedServiceName;
        this.versionName = builder.nestedVersionName;
        this.versionCode = builder.nestedVersionCode;
        this.initialCooperativeState = builder.nestedInitialCooperativeState;
        this.surfaceNames = builder.nestedSurfaceNames;
        this.layerOptions = builder.nestedLayerOptions;
        this.allowMultiInstance = builder.nestedAllowMultiInstance;
        this.allowImeComposition = builder.nestedAllowImeComposition;
        this.supportsLayers = builder.nestedSupportsLayers;
        this.validService = builder.nestedValidService;
        this.verifiedUid = builder.nestedVerifiedUid;
        this.isWellKnownPanelType = builder.nestedIsWellKnownPanelType;
        this.needsActivityToken = builder.nestedNeedsActivityToken;
        this.supportsMultiApp = builder.nestedSupportsMultiApp;
        this.lastUpdateTime = builder.nestedLastUpdateTime;
        this.clearOnAppLaunch = builder.nestedClearOnAppLaunch;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public int getPackageUid() {
        return this.packageUid;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String getVersionCode() {
        return this.versionCode;
    }

    public String[] getSurfaceNames() {
        return this.surfaceNames;
    }

    public boolean isAllowMultiInstance() {
        return this.allowMultiInstance;
    }

    public boolean isAllowImeComposition() {
        return this.allowImeComposition;
    }

    public boolean isSupportsLayers() {
        return this.supportsLayers;
    }

    public boolean isValidService() {
        return this.validService;
    }

    public boolean isVerifiedUid() {
        return this.verifiedUid;
    }

    public boolean isWellKnownPanelType() {
        return this.isWellKnownPanelType;
    }

    public boolean isNeedsActivityToken() {
        return this.needsActivityToken;
    }

    public boolean isMultiAppModeSupported() {
        return this.supportsMultiApp;
    }

    public int[] getLayerOptions() {
        return this.layerOptions;
    }

    public boolean shouldClearOnAppLaunch() {
        return this.clearOnAppLaunch;
    }

    public static class Builder {
        private boolean nestedAllowImeComposition;
        private boolean nestedAllowMultiInstance;
        private boolean nestedClearOnAppLaunch;
        private String nestedInitialCooperativeState;
        private boolean nestedIsWellKnownPanelType;
        private long nestedLastUpdateTime;
        private int[] nestedLayerOptions;
        private boolean nestedNeedsActivityToken;
        private String nestedPackageName;
        private int nestedPackageUid;
        private String nestedServiceName;
        private boolean nestedSupportsLayers;
        private boolean nestedSupportsMultiApp;
        private String[] nestedSurfaceNames;
        private boolean nestedValidService;
        private boolean nestedVerifiedUid;
        private String nestedVersionCode;
        private String nestedVersionName;

        public Builder withPackageName(String str) {
            this.nestedPackageName = str;
            return this;
        }

        public Builder withPackageUid(int i) {
            this.nestedPackageUid = i;
            return this;
        }

        public Builder withServiceName(String str) {
            this.nestedServiceName = str;
            return this;
        }

        public Builder withVersionName(String str) {
            this.nestedVersionName = str;
            return this;
        }

        public Builder withVersionCode(String str) {
            this.nestedVersionCode = str;
            return this;
        }

        public Builder withInitialCooperativeState(String str) {
            this.nestedInitialCooperativeState = str;
            return this;
        }

        public Builder withSurfaceNames(String[] strArr) {
            this.nestedSurfaceNames = strArr;
            return this;
        }

        public Builder withLayerOptions(int[] iArr) {
            this.nestedLayerOptions = iArr;
            return this;
        }

        public Builder withAllowMultiInstance(boolean z) {
            this.nestedAllowMultiInstance = z;
            return this;
        }

        public Builder withAllowImeComposition(boolean z) {
            this.nestedAllowImeComposition = z;
            return this;
        }

        public Builder withSupportsLayers(boolean z) {
            this.nestedSupportsLayers = z;
            return this;
        }

        public Builder withValidService(boolean z) {
            this.nestedValidService = z;
            return this;
        }

        public Builder withVerifiedUid(boolean z) {
            this.nestedVerifiedUid = z;
            return this;
        }

        public Builder withIsWellKnownPanelType(boolean z) {
            this.nestedIsWellKnownPanelType = z;
            return this;
        }

        public Builder withNeedsActivityToken(boolean z) {
            this.nestedNeedsActivityToken = z;
            return this;
        }

        public Builder withSupportsMultiApp(boolean z) {
            this.nestedSupportsMultiApp = z;
            return this;
        }

        public Builder withLastUpdateTime(long j) {
            this.nestedLastUpdateTime = j;
            return this;
        }

        public Builder withClearOnAppLaunch(boolean z) {
            this.nestedClearOnAppLaunch = z;
            return this;
        }

        public PanelAppConfiguration build() {
            return new PanelAppConfiguration(this);
        }
    }
}
