package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames6 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(7, "queue_uninstall_requests_enabled");
        BooleanNames.put(15, "enable_untrusted_binary_launch_warning");
        BooleanNames.put(23, "claim_activation_bundles");
        BooleanNames.put(31, "is_chromecast_for_all_receivers_enabled");
        BooleanNames.put(39, "is_go_referrals_enabled");
        BooleanNames.put(47, "is_payment_speedbump_enabled");
        BooleanNames.put(55, "is_retail_demo_user");
        BooleanNames.put(63, "oculus_ota_user_control_allowed");
        BooleanNames.put(71, "should_messages_badge");
        BooleanNames.put(79, "show_feed_prime_video_v2");
        BooleanNames.put(87, "stranger_messaging_enabled");
        BooleanNames.put(95, "twilight_can_access_setting_voice_log");
        BooleanNames.put(103, "is_event_user_list_enabled");
        BooleanNames.put(111, "is_add_on_content_improvement_enabled");
        BooleanNames.put(119, "is_system_action_remote_launch_enabled");
        BooleanNames.put(127, "use_improved_fb_embed");
        BooleanNames.put(135, "casting_upsell_feed_tooltip_enabled");
        BooleanNames.put(143, "vr_camera_dev");
        BooleanNames.put(151, "should_show_invites_entry_point");
        BooleanNames.put(159, "should_show_search_recommendation_tags");
        BooleanNames.put(167, "should_notif_screen_render_as_tab");
        BooleanNames.put(175, "is_messenger_social_nux_enabled");
        BooleanNames.put(183, "oculus_twilight_qpl_funnel_test_mode");
        BooleanNames.put(191, "should_paginate_search_shelves");
        BooleanNames.put(199, "show_latest_activity_profile");
        BooleanNames.put(207, "is_trusted_user");
        LongNames.put(0, "doze_delay_initial_period_minutes");
        LongNames.put(8, "concurrent_download_max");
        LongNames.put(16, "library_update_period_ms");
        LongNames.put(24, "devpost_tile_content_height");
        DoubleNames.put(2, "double3");
        StringNames.put(1, "string2");
        StringNames.put(9, "top_level_tabs");
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
