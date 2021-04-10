package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames0 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(1, "bool1");
        BooleanNames.put(9, "prioritize_dlc_installs");
        BooleanNames.put(17, "entitlements_v2_enabled");
        BooleanNames.put(25, "dogfood_internal_app_redirect");
        BooleanNames.put(33, "is_chromecast_pacific_enabled");
        BooleanNames.put(41, "is_mobile_invr_remote_launch_enabled");
        BooleanNames.put(49, "is_quest_referrals_enabled");
        BooleanNames.put(57, "is_sso_enabled");
        BooleanNames.put(65, "oculus_store_is_disco_twilight_pdp_bundle_enabled");
        BooleanNames.put(73, "should_show_twilight_pymk");
        BooleanNames.put(81, "show_hide_shelf_in_feed");
        BooleanNames.put(89, "twilight_dogfooding_employees_v2");
        BooleanNames.put(97, "is_native_referral_sharing_enabled");
        BooleanNames.put(105, "oculus_metaport_mobile_wifi_hook");
        BooleanNames.put(113, "promo_codes_enabled");
        BooleanNames.put(121, "is_search_pymk_enabled");
        BooleanNames.put(129, "show_featured_app_fb_embed");
        BooleanNames.put(137, "is_casting_nux_enabled");
        BooleanNames.put(145, "is_facebook_discoverability_privacy_setting_enabled");
        BooleanNames.put(153, "is_wishlist_settings_tab_enabled");
        BooleanNames.put(161, "should_show_search_null_state");
        BooleanNames.put(169, "should_show_search_global_search");
        BooleanNames.put(177, "optimize_hi_res_image_load");
        BooleanNames.put(185, "fetch_next_page_early");
        BooleanNames.put(193, "enable_challenges_tab_in_social");
        BooleanNames.put(201, "ring_style_badging");
        LongNames.put(2, "int1");
        LongNames.put(10, "consistency_deleting_state_timeout_mins");
        LongNames.put(18, "pc_active_window_size");
        StringNames.put(3, "shiba_whitelisted_pkg");
        StringNames.put(11, "referrals_webview_link");
    }

    public static String getBooleanParamName(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        return BooleanNames.containsKey(Integer.valueOf(slotId)) ? BooleanNames.get(Integer.valueOf(slotId)) : "";
    }

    public static String getLongParamName(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        return LongNames.containsKey(Integer.valueOf(slotId)) ? LongNames.get(Integer.valueOf(slotId)) : "";
    }

    public static String getDoubleParamName(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        return DoubleNames.containsKey(Integer.valueOf(slotId)) ? DoubleNames.get(Integer.valueOf(slotId)) : "";
    }

    public static String getStringParamName(long j) {
        int slotId = MobileConfigSpecifierUtil.getSlotId(j);
        return StringNames.containsKey(Integer.valueOf(slotId)) ? StringNames.get(Integer.valueOf(slotId)) : "";
    }
}
