package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames2 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(3, "bool3");
        BooleanNames.put(11, "oculus_ocms_ds_library_cache_refresh");
        BooleanNames.put(19, "are_tab_titles_eliminated");
        BooleanNames.put(27, "enable_remote_launch_cta");
        BooleanNames.put(35, "is_create_pc_event_enabled");
        BooleanNames.put(43, "is_oculus_twilight_add_friends_nux_screen_enabled");
        BooleanNames.put(51, "is_referrals_enabled");
        BooleanNames.put(59, "is_twilight_monterey_store_visible");
        BooleanNames.put(67, "oculus_twilight_is_controller_bonding_skippable");
        BooleanNames.put(75, "show_credit_card_form_inline_account_nux");
        BooleanNames.put(83, "show_refund_policy_upsell_quest");
        BooleanNames.put(91, "twilight_mobileconfig_cache_is_corp_net");
        BooleanNames.put(99, "is_oculus_twilight_profile_setting_enabled");
        BooleanNames.put(107, "is_feed_query_native_prefetched");
        BooleanNames.put(115, "oculus_twilight_remote_launch_and_install_revamp");
        BooleanNames.put(123, "is_add_fb_friends_enabled");
        BooleanNames.put(131, "show_card_style_aoc_section_in_pdp");
        BooleanNames.put(139, "autoplay_app_trailer");
        BooleanNames.put(147, "is_store_app_gifting_enabled");
        BooleanNames.put(155, "is_purchase_requirements_flow_enabled");
        BooleanNames.put(163, "should_show_interest_picker_screen");
        BooleanNames.put(171, "should_show_rss_articles");
        BooleanNames.put(179, "is_markdown_enabled");
        BooleanNames.put(187, "show_related_apps");
        BooleanNames.put(195, "use_twilight_social_nux_add_friends_screen_redesign");
        BooleanNames.put(203, "oculus_hmd_device_nickname");
        LongNames.put(4, "int3");
        LongNames.put(12, "consistency_installing_state_timeout_mins");
        LongNames.put(20, "delay_startup_services_seconds");
        StringNames.put(5, "feed_image_sizes");
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
