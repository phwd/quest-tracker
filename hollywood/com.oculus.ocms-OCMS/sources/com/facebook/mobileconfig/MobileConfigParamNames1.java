package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames1 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(2, "bool2");
        BooleanNames.put(10, "enable_licensemanager");
        BooleanNames.put(18, "allow_changing_name");
        BooleanNames.put(26, "downloads_manager");
        BooleanNames.put(34, "is_create_event_enabled");
        BooleanNames.put(42, "is_using_new_hmd_connection_module");
        BooleanNames.put(50, "is_questions_section_visible");
        BooleanNames.put(58, "is_trusted_user");
        BooleanNames.put(66, "oculus_twilight_autodetect_quest");
        BooleanNames.put(74, "show_credit_card_form_inline");
        BooleanNames.put(82, "show_refund_policy_upsell");
        BooleanNames.put(90, "twilight_log_mobileconfigs");
        BooleanNames.put(98, "is_user_sharing_enabled");
        BooleanNames.put(106, "is_hidden_app_grouping_list_enabled");
        BooleanNames.put(114, "social_bundle_server_strings_enabled");
        BooleanNames.put(122, "oculus_twilight_twui_input");
        BooleanNames.put(130, "is_override_megaphone_enabled");
        BooleanNames.put(138, "show_pymk_twilight_chat");
        BooleanNames.put(146, "show_link_fb_account_screen");
        BooleanNames.put(154, "show_share_button");
        BooleanNames.put(162, "show_referral_button");
        BooleanNames.put(170, "is_subscriptions_enabled");
        BooleanNames.put(178, "prefetch_subfeeds_on_visible");
        BooleanNames.put(186, "should_unblock_tags");
        BooleanNames.put(194, "is_reg_native_sso_enabled");
        BooleanNames.put(202, "should_show_fb_friends_section_in_friend_search_screen");
        LongNames.put(3, "int2");
        LongNames.put(11, "consistency_downloaded_state_timeout_mins");
        LongNames.put(19, "purchase_referral_upsell_days_between_views");
        StringNames.put(4, "shiba_whitelisted_artificial_version_limit");
        StringNames.put(12, "soft_check_status");
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
