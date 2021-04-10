package com.oculus.library.database.contract;

import androidx.core.content.FileProvider;

public abstract class LibraryDBContract {
    public static final String ACCESS_FEATURE_KEYS_DELIMITER = ";";
    public static final String MEDIA_CAPABILITIES_DELIMITER = ";";
    public static final String PERMISSIONS_DELIMITER = ",";
    public static final String PLAY_MODES_DELIMITER = ",";
    public static final String SUPPORTED_INPUT_DEVICES_DELIMITER = ",";
    public static final String TABLE_NAME = "library";
    public static final long VERSION_NOT_INSTALLED = -2147483648L;

    public static abstract class Columns {
        public static Column ACCESS_FEATURE_KEYS = new Column("access_feature_keys", "TEXT");
        @Deprecated
        public static Column APK_FULL_SIZE = new Column("apk_full_size", "INTEGER");
        public static Column APPLICATION_GROUPING_ID = new Column("application_grouping_id", "TEXT");
        public static Column APPLICATION_ORGANIZATION_ID = new Column("application_organization_id", "TEXT");
        public static Column APP_MEDIA_CAPABILITIES = new Column("app_media_capabilities", "TEXT");
        public static Column APP_SCOPED_USER_ID = new Column("app_scoped_user_id", "TEXT");
        public static Column AUTO_UPDATE_ENABLED = new Column("auto_update_enabled", "INTEGER");
        public static Column CATEGORY = new Column("category", "TEXT");
        @Deprecated
        public static Column CHROMECAST_AUDIO_ENABLED = new Column("chromecast_audio_enabled", "INTEGER");
        public static Column CLOUD_STORAGE_STATUS = new Column("cloud_storage_status", "TEXT");
        public static Column COMFORT_RATING = new Column("comfort_rating", "TEXT");
        public static Column CURRENT_LANGUAGE_PACK = new Column("current_langauge_pack", "TEXT");
        public static Column DOWNLOADED_SIZE = new Column("downloaded_size", "INTEGER");
        public static Column DUC_NON_COMPLIANT = new Column("duc_non_compliant", "INTEGER");
        public static Column ENTITLEMENT_HASH = new Column("entitlement_hash", "TEXT");
        public static Column ENVIRONMENT_SOURCE_DISPLAY_NAME = new Column("environment_source_display_name", "TEXT");
        public static Column ENVIRONMENT_SOURCE_ID = new Column("environment_source_id", "TEXT");
        @Deprecated
        public static Column FAVORITE = new Column("favorite", "INTEGER");
        public static Column GRANT_EXPIRATION = new Column("grant_expiration", "INTEGER");
        public static Column GRANT_REASON = new Column("grant_reason", "TEXT");
        public static Column GRANT_TIME = new Column("grant_time", "INTEGER");
        public static Column HEAD_TRACKING = new Column("head_tracking", "TEXT");
        public static Column HERO_IMAGE = new Column("hero_image", "TEXT");
        public static Column IMAGE_SOURCE_MAIN = new Column("image_source_main", "TEXT");
        public static Column IMAGE_SOURCE_SQUARE = new Column("image_source_square", "TEXT");
        public static Column IMAGE_SOURCE_TINY = new Column("image_source_tiny", "TEXT");
        public static Column INSTALLABLE_ON_EXTERNAL_STORAGE = new Column("installable_on_external_storage", "INTEGER");
        public static Column INSTALLATION_SIZE = new Column("installation_size", "INTEGER");
        public static Column INTERNET_CONNECTION = new Column("internet_connection", "TEXT");
        public static Column IS_CONCEPT = new Column("is_concept", "INTEGER");
        public static Column IS_DEMO_OF = new Column("is_demo_of", "INTEGER");
        public static Column IS_TEST = new Column("is_test", "INTEGER");
        public static Column IS_TV_APP = new Column("is_tv_app", "INTEGER");
        public static Column ITEM_ID = new Column("item_id", "TEXT");
        public static Column LATEST_SDK_TARGET_VERSION = new Column("latest_target_sdk_version", "INTEGER");
        public static Column LATEST_VERSION_CODE = new Column("latest_version_code", "INTEGER");
        public static Column LATEST_VERSION_NAME = new Column("latest_version_name", "TEXT");
        @Deprecated
        public static Column LIVESTREAMING_STATUS = new Column("livestreaming_status", "TEXT");
        @Deprecated
        public static Column LIVESTREAMING_STATUS_AUDIO_HOOKING_ENABLED = new Column("livestreaming_status_audio_hooking", "INTEGER");
        public static Column MICROPHONE_USAGE = new Column("microphone_usage", "TEXT");
        public static Column MIN_RECOMMENDED_VERSION_CODE = new Column("min_recommended_version_code", "INTEGER");
        public static Column MIN_REQUIRED_VERSION_CODE = new Column("min_required_version_code", "INTEGER");
        public static Column NAME = new Column(FileProvider.ATTR_NAME, "TEXT");
        @Deprecated
        public static Column OBB_FULL_SIZE = new Column("obb_full_size", "INTEGER");
        public static Column PACKAGE_NAME = new Column("package_name", "TEXT");
        @Deprecated
        public static Column PATCH_DOWNLOAD_SIZE = new Column("patch_download_size", "INTEGER");
        @Deprecated
        public static Column PERMISSIONS = new Column("permissions", "TEXT");
        public static Column PERMISSIONS_V2 = new Column("android_permissions", "TEXT");
        public static Column PLAY_MODES = new Column("play_modes", "TEXT");
        public static Column RECENT_ACTIVITY = new Column("recent_activity", "INTEGER");
        public static Column SMALL_LANDSCAPE_IMAGE = new Column("small_landscape_image", "TEXT");
        public static Column STATUS = new Column("status", "TEXT");
        public static Column SUPPORTED_INPUT_DEVICES = new Column("supported_input_devices", "TEXT");
        public static Column SUPPORTED_PLATFORM = new Column("platform", "TEXT");
        public static Column SYSTEM_UI_AS_OVERLAY_MODE = new Column("system_ui_as_overlay_mode", "TEXT");
        public static Column TOTAL_ACTIVITY = new Column("total_activity", "INTEGER");
        public static Column TRUSTED_BINARY_STATUS = new Column("trusted_binary_status", "TEXT");
        public static Column UNSEEN = new Column("unseen", "INTEGER");
        public static Column USER_ID = new Column("user_id", "TEXT");
        @Deprecated
        public static Column USER_QUALITY_RATING = new Column("user_quality_rating", "INTEGER");
    }

    public static class Column {
        public final String name;
        public final String type;

        public Column(String str, String str2) {
            this.name = str;
            this.type = str2;
        }
    }
}
