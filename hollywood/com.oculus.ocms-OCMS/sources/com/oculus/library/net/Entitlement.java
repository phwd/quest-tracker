package com.oculus.library.net;

import com.google.common.base.Preconditions;
import com.oculus.http.common.Image;
import com.oculus.library.model.HeadTracking;
import com.oculus.library.model.InputDevice;
import java.util.List;
import javax.annotation.Nullable;

public class Entitlement {
    public String app_viewer_id;
    public String auto_update_mode;
    public List<String> can_access_feature_keys;
    public String category;
    public String comfort_rating;
    public Image cover_landscape_image;
    public Image cover_square_image;
    public String display_name;
    @Nullable
    public EnvironmentSource environment_source;
    public ApplicationGrouping grouping;
    public Image hero_image;
    public String id;
    public String internet_connection;
    public boolean is_concept;
    @Nullable
    public IsDemoOf is_demo_of;
    public boolean is_duc_non_compliant;
    public boolean is_test;
    public boolean is_tv_app;
    @Nullable
    public AndroidBinary latest_supported_binary;
    public String microphone_usage;
    public int min_recommended_version_code;
    public int min_required_version_code;
    public ApplicationOrganization organization;
    public String platform;
    public List<String> share_capabilities;
    public Image small_landscape_image;
    public List<InputDevice> supported_input_devices_list;
    public List<String> supported_player_modes;
    public String system_ui_as_overlay_mode;
    public Image thumbnail;

    public static class AndroidBinary {
        public boolean can_use_external_storage;
        public HeadTracking head_tracking;
        public String id;
        public String package_name;
        public List<String> permissions;
        public long size;
        public int target_android_sdk_version;
        public String version;
        public int version_code;
    }

    public static class ApplicationGrouping {
        public boolean cloud_file_is_enabled;
        public String id;
    }

    public static class ApplicationOrganization {
        public String id;
    }

    public static class EnvironmentSource {
        public String display_name;
        public String id;
    }

    public static class IsDemoOf {
        public String id;
    }

    public String getSquareImageUri() {
        Image image = this.cover_square_image;
        if (image == null) {
            image = this.cover_landscape_image;
        }
        return image.uri;
    }

    public boolean isAppSupportedOnCurrentDevice() {
        AndroidBinary androidBinary = this.latest_supported_binary;
        return (androidBinary == null || androidBinary.package_name == null) ? false : true;
    }

    public String getPackageName() {
        Preconditions.checkArgument(isAppSupportedOnCurrentDevice(), "Item %s:%sis not supported on this device.", this.display_name, this.id);
        return this.latest_supported_binary.package_name;
    }
}
