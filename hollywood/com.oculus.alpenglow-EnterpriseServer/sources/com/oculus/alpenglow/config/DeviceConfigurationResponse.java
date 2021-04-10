package com.oculus.alpenglow.config;

import com.google.gson.annotations.SerializedName;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMOculusOSDeviceAdmin;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMUnknownSourcesSetting;
import com.oculus.alpenglow.http.annotations.Exclude;
import com.oculus.os.enterprise.AUICapability;
import com.oculus.os.enterprise.GuardianMode;
import com.oculus.os.enterprise.HandTracking;
import com.oculus.os.enterprise.HomeButtonBehaviour;
import java.util.Objects;
import javax.annotation.Nullable;

public class DeviceConfigurationResponse {
    @Nullable
    public Data data;

    public static class ApplicationAsset {
        @SerializedName("content_hash_256")
        @Nullable
        public String contentHash256;
        @SerializedName("download_uri")
        @Nullable
        public String downloadUri;
        @Nullable
        public String filename;
        public KeyValuePair[] headers = new KeyValuePair[0];
        @Nullable
        public String id;
        @SerializedName("last_updated")
        public long lastUpdated;
        @SerializedName("server_type")
        @Nullable
        public ServerType serverType;
        @SerializedName("time_created")
        public long timeCreated;
    }

    public static class ApplicationAssetEdge {
        public ApplicationAsset[] nodes = new ApplicationAsset[0];
    }

    public enum ApplicationType {
        ANDROID_APK,
        ANDROID_MDM
    }

    public enum CrashReportingState {
        OFF,
        ON
    }

    public static class Data {
        @Nullable
        public Me me;
    }

    public static class DeviceConfig {
        @SerializedName("config_version")
        @Nullable
        public int configVersion;
        @SerializedName("device_remote_apps")
        @Nullable
        public RemoteAppsEdge deviceRemoteApps;
        @SerializedName("dynamic_config")
        @Nullable
        public final DynamicConfig dynamicConfig;
        @SerializedName("enable_adb")
        @Nullable
        public boolean enableAdb;
        @SerializedName("guardian_mode")
        public GuardianMode guardianMode = GuardianMode.ENABLED;
        @SerializedName("last_updated")
        @Nullable
        public long lastUpdated;
        @SerializedName("network_config")
        @Nullable
        public NetworkConfig networkConfig;
        @SerializedName("os_config")
        @Nullable
        public OSConfig osConfig;
        @SerializedName("shell_modes")
        @Nullable
        public ShellModeEdge shellModes;
    }

    public static class HwmDevice {
        @Nullable
        public String id;
        @SerializedName("is_managed")
        public boolean isManaged;
        @SerializedName("management_info")
        @Nullable
        public final ManagementInfo managementInfo;
        @SerializedName("owner_name")
        @Nullable
        public String ownerName;
        @Nullable
        public String serial;
    }

    public static class KeyValuePair {
        @Nullable
        public String key;
        @Nullable
        public String value;
    }

    public static class LockScreen {
        @SerializedName("unlock_pin")
        @Nullable
        public final String unlockPin;
        @SerializedName("unlock_type")
        public LockScreenPinType unlockType = LockScreenPinType.NONE;

        public final boolean equals(@Nullable Object obj) {
            String str;
            if (!(obj instanceof LockScreen)) {
                return false;
            }
            LockScreen lockScreen = (LockScreen) obj;
            if (this.unlockType != lockScreen.unlockType) {
                return false;
            }
            String str2 = this.unlockPin;
            if ((str2 != null || lockScreen.unlockPin != null) && (str2 == null || (str = lockScreen.unlockPin) == null || !Objects.equals(str2, str))) {
                return false;
            }
            return true;
        }
    }

    public enum LockScreenPinType {
        NONE,
        PASSWORD,
        PATTERN
    }

    public static class ManagementInfo {
        @SerializedName("device_config")
        @Nullable
        public final DeviceConfig deviceConfig;
        @SerializedName("device_name")
        @Nullable
        public String deviceName;
        @SerializedName("group_name")
        @Nullable
        public String groupName;
        @Nullable
        public String id;
        @Nullable
        public Subscription subscription;
    }

    public static class Me {
        @SerializedName("hwm_device")
        @Nullable
        public HwmDevice hwmDevice;
    }

    public static class NetworkConfig {
        @SerializedName("wifi_networks")
        @Nullable
        public WifiNetworkEdge wifiNetworks;
    }

    public static class OSConfig {
        @SerializedName("auto_ota")
        public boolean autoOta;
        @SerializedName("crash_reporting_state")
        public CrashReportingState crashReportingState = CrashReportingState.ON;
        @SerializedName("device_admin")
        public GraphQLHWMOculusOSDeviceAdmin deviceAdmin = GraphQLHWMOculusOSDeviceAdmin.NONE;
        @SerializedName("hand_tracking_state")
        public HandTracking handTracking = HandTracking.DISABLED;
        @Nullable
        public String id;
        @Nullable
        public String locale;
        @SerializedName("lock_screen")
        @Exclude(read = false, write = true)
        @Nullable
        public LockScreen lockScreen;
        @SerializedName("os_version")
        @Nullable
        public String osVersion;
        @SerializedName("telemetry_state")
        @Nullable
        public String telemetryState;
        @Nullable
        public String timezone;
        @SerializedName("unknown_sources")
        @Nullable
        public GraphQLHWMUnknownSourcesSetting unknownSources;
        @SerializedName("update_rule")
        @Nullable
        public String updateRule;
        @SerializedName("update_time_windows")
        public UpdateTimeWindow[] updateTimeWindows = new UpdateTimeWindow[0];
    }

    public static class RemoteApp {
        @SerializedName("application_binary")
        @Nullable
        public ApplicationAsset applicationBinary;
        @SerializedName("application_metadata")
        public KeyValuePair[] applicationMetadata = new KeyValuePair[0];
        @SerializedName("external_resource_files")
        @Nullable
        public ApplicationAssetEdge externalResourceFiles;
        @Nullable
        public String id;
        @SerializedName("last_updated")
        public long lastUpdated;
        @Nullable
        public String name;
        @SerializedName("remote_application_type")
        @Nullable
        public ApplicationType remoteApplicationType;
        @SerializedName("time_created")
        public long timeCreated;
    }

    public static class RemoteAppsEdge {
        public RemoteApp[] nodes = new RemoteApp[0];
    }

    public enum ServerType {
        HTTP
    }

    public static class ShellMode {
        @SerializedName("aui_capabilities")
        public AUICapability[] auiCapabilities = new AUICapability[0];
        @SerializedName("default_app")
        @Nullable
        public String defaultApp;
        @SerializedName("home_button_behavior")
        public HomeButtonBehaviour homeButtonBehaviour = HomeButtonBehaviour.UNRESTRICTED;
        @SerializedName("launcher_apps")
        @Nullable
        public String[] launcherApps;
        @Nullable
        public String name;
        @SerializedName("unlock_pin")
        @Nullable
        public String unlockPin;
    }

    public static class ShellModeEdge {
        @Nullable
        public ShellMode[] nodes;
    }

    public static class Subscription {
        @SerializedName("expiry_date")
        public long expiryDate;
        @SerializedName("server_timestamp")
        @Nullable
        public long serverTimestamp;
        @SerializedName("subscription_features")
        @Nullable
        public SubscriptionFeature[] subscriptionFeatures;
        @SerializedName("subscription_standing")
        @Nullable
        public SubscriptionStanding subscriptionStanding;
    }

    public enum SubscriptionFeature {
        ARVR_ENTERPRISE_MVP
    }

    public enum SubscriptionStanding {
        GOOD_STANDING,
        BLACKLISTED
    }

    public static class UpdateTimeWindow {
        @SerializedName("days_of_week")
        public String[] daysOfWeek = new String[0];
        @SerializedName("window_end")
        public int windowEnd = 0;
        @SerializedName("window_start")
        public int windowStart = 0;
    }

    public static class WifiNetwork {
        @Nullable
        public String credential;
        @SerializedName("network_type")
        @Nullable
        public WifiNetworkType networkType;
        @Nullable
        public String ssid;
    }

    public static class WifiNetworkEdge {
        @Exclude(read = false, write = true)
        public WifiNetwork[] nodes = new WifiNetwork[0];
    }

    public enum WifiNetworkType {
        WPA2
    }
}
