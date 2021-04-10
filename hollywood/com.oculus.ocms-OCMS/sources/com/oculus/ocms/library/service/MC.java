package com.oculus.ocms.library.service;

import com.facebook.mobileconfig.MobileConfigConfigs;
import com.facebook.mobileconfig.MobileConfigParams0;
import com.facebook.mobileconfig.MobileConfigParams1;
import com.facebook.mobileconfig.MobileConfigParams2;
import com.facebook.mobileconfig.MobileConfigParams3;

public final class MC {

    public static class oculus_library {
        public static final int __CONFIG__ = MobileConfigConfigs.oculus_library;
        public static final long auto_update_allow_while_in_use = MobileConfigParams1.oculus_library_auto_update_allow_while_in_use;
        public static final long auto_update_interval_ms = MobileConfigParams0.oculus_library_auto_update_interval_ms;
        public static final long auto_update_max_retries = MobileConfigParams1.oculus_library_auto_update_max_retries;
        public static final long auto_update_min_battery_percent = MobileConfigParams2.oculus_library_auto_update_min_battery_percent;
        public static final long concurrent_download_max = MobileConfigParams3.oculus_library_concurrent_download_max;
        public static final long consistency_deleting_state_timeout_mins = MobileConfigParams1.oculus_library_consistency_deleting_state_timeout_mins;
        public static final long consistency_downloaded_state_timeout_mins = MobileConfigParams0.oculus_library_consistency_downloaded_state_timeout_mins;
        public static final long consistency_installing_state_timeout_mins = MobileConfigParams0.oculus_library_consistency_installing_state_timeout_mins;
        public static final long consistency_uninstalling_state_timeout_mins = MobileConfigParams3.oculus_library_consistency_uninstalling_state_timeout_mins;
        public static final long consistency_verified_state_timeout_mins = MobileConfigParams3.oculus_library_consistency_verified_state_timeout_mins;
        public static final long consistency_verifying_state_timeout_mins = MobileConfigParams0.oculus_library_consistency_verifying_state_timeout_mins;
        public static final long disable_download_queue_standalone = MobileConfigParams3.oculus_library_disable_download_queue_standalone;
        public static final long enable_install_integrity_check = MobileConfigParams0.oculus_library_enable_install_integrity_check;
        public static final long enable_licensemanager = MobileConfigParams2.oculus_library_enable_licensemanager;
        public static final long enable_persistent_patches = MobileConfigParams0.oculus_library_enable_persistent_patches;
        public static final long enable_untrusted_binary_launch_block = MobileConfigParams0.oculus_library_enable_untrusted_binary_launch_block;
        public static final long enable_untrusted_binary_launch_warning = MobileConfigParams3.oculus_library_enable_untrusted_binary_launch_warning;
        public static final long entitlements_v2_enabled = MobileConfigParams3.oculus_library_entitlements_v2_enabled;
        public static final long library_update_period_ms = MobileConfigParams0.oculus_library_library_update_period_ms;
        public static final long oculus_ocms_ds_library_cache_refresh = MobileConfigParams3.oculus_library_oculus_ocms_ds_library_cache_refresh;
        public static final long patch_updates_enabled = MobileConfigParams1.oculus_library_patch_updates_enabled;
        public static final long prioritize_dlc_installs = MobileConfigParams2.oculus_library_prioritize_dlc_installs;
        public static final long queue_uninstall_requests_enabled = MobileConfigParams3.oculus_library_queue_uninstall_requests_enabled;
        public static final long shiba_attack_artificial_version_limit = MobileConfigParams0.oculus_library_shiba_attack_artificial_version_limit;
        public static final long shiba_attack_prevention = MobileConfigParams1.oculus_library_shiba_attack_prevention;
        public static final long shiba_whitelisted_artificial_version_limit = MobileConfigParams2.oculus_library_shiba_whitelisted_artificial_version_limit;
        public static final long shiba_whitelisted_pkg = MobileConfigParams3.oculus_library_shiba_whitelisted_pkg;
    }
}
