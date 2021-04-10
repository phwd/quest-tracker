package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames7 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(0, "doze_delay_supported");
        BooleanNames.put(8, "auto_update_allow_while_in_use");
        BooleanNames.put(16, "shiba_attack_prevention");
        BooleanNames.put(24, "dogfood_background_nag");
        BooleanNames.put(32, "is_chromecast_monterey_enabled");
        BooleanNames.put(40, "is_messages_tab_enabled");
        BooleanNames.put(48, "is_quest_payment_speedbump_enabled");
        BooleanNames.put(56, "is_rift_referrals_enabled");
        BooleanNames.put(64, "oculus_store_disco_side_drawer_filtering_twilight");
        BooleanNames.put(72, "should_show_prepopulated_list");
        BooleanNames.put(80, "show_hide_option");
        BooleanNames.put(88, "twilight_always_true");
        BooleanNames.put(96, "twilight_can_access_setting_voice_log_v2");
        BooleanNames.put(104, "is_guest_invite_to_events_enabled");
        BooleanNames.put(112, "is_deprecate_autosync_enabled");
        BooleanNames.put(120, "developer_license_sync_enabled");
        BooleanNames.put(128, "show_featured_app_yt_embed");
        BooleanNames.put(136, "can_click_messaging_links");
        BooleanNames.put(144, "is_in_vr_social_nux_enabled");
        BooleanNames.put(152, "show_hollywood_in_device_picker");
        BooleanNames.put(160, "show_store_credit_countdown_timer_quest");
        BooleanNames.put(168, "should_show_welcome_message");
        BooleanNames.put(176, "prefetch_app_feeds_on_visible");
        BooleanNames.put(184, "is_account_setings_side_tray_enabled");
        BooleanNames.put(192, "enable_challenge_view_v2");
        BooleanNames.put(200, "hide_add_friends_screen_holdout");
        BooleanNames.put(208, "log_backend_exceptions");
        LongNames.put(1, "doze_delay_retrigger_period_minutes");
        LongNames.put(9, "shiba_attack_artificial_version_limit");
        LongNames.put(17, "genre_tags_max_number_of_lines");
        StringNames.put(2, "string3");
        StringNames.put(10, "referral_icon_name");
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
