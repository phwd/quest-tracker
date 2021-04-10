package X;

import android.content.pm.PackageManager;
import com.facebook.acra.AppComponentStats;

/* renamed from: X.Zn  reason: case insensitive filesystem */
public final class C0457Zn {
    public static final String[] A01 = {"is_demo_of", "platform", "app_media_capabilities", "play_modes", "category", "head_tracking", "grant_reason", "grant_time", "grant_expiration", "current_langauge_pack", "internet_connection", "trusted_binary_status", "is_concept", "is_tv_app", "is_test", "system_ui_as_overlay_mode", "hero_image", "auto_update_enabled", "application_organization_id", "duc_non_compliant", "access_feature_keys", "environment_source_id", "environment_source_display_name"};
    public static final String[] A02 = {"user_id", "item_id", AppComponentStats.ATTRIBUTE_NAME, "comfort_rating", "application_grouping_id", "image_source_square", "image_source_main", "image_source_tiny", "small_landscape_image", "package_name", "latest_version_code", "latest_version_name", "latest_target_sdk_version", "installation_size", "downloaded_size", "android_permissions", "installable_on_external_storage", "status", "cloud_storage_status", "supported_input_devices", "entitlement_hash", "app_scoped_user_id", "recent_activity", "unseen", "min_recommended_version_code", "min_required_version_code", "microphone_usage", "total_activity", "apk_full_size"};
    public final PackageManager A00;

    public C0457Zn(PackageManager packageManager) {
        this.A00 = packageManager;
    }
}
