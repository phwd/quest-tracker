package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames5 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(6, "patch_updates_enabled");
        BooleanNames.put(14, "enable_untrusted_binary_launch_block");
        BooleanNames.put(22, "can_view_referrals_link_in_friends");
        BooleanNames.put(30, "is_change_profile_photo_displayed");
        BooleanNames.put(38, "is_gear_vr_nux_step_enabled");
        BooleanNames.put(46, "is_outbound_sharing_enabled");
        BooleanNames.put(54, "is_remote_launch_event_enabled");
        BooleanNames.put(62, "oculus_ota_fast_dogfooding");
        BooleanNames.put(70, "should_initialize_messenger_library");
        BooleanNames.put(78, "show_fake_headset_in_prod");
        BooleanNames.put(86, "should_see_tooltip_icon");
        BooleanNames.put(94, "can_view_messaging_attachment_deeplink");
        BooleanNames.put(102, "user_credit_balance_message_enabled");
        BooleanNames.put(110, "oculus_store_pin_alternatives_enabled");
        BooleanNames.put(118, "is_oculus_parties_2d_join_enabled");
        BooleanNames.put(126, "is_nux_unification_roadblock_enabled");
        BooleanNames.put(134, "is_friend_list_recent_activity_enabled");
        BooleanNames.put(142, "is_fb_linking_confirmation_enabled");
        BooleanNames.put(150, "is_raycast_experiment_shown");
        BooleanNames.put(158, "is_store_erc_enabled");
        BooleanNames.put(166, "enable_event_app_picker");
        BooleanNames.put(174, "oculus_feed_hyperloop_enabled");
        BooleanNames.put(182, "oculus_twilight_qpl_funnel_only");
        BooleanNames.put(190, "enable_billing_address_form");
        BooleanNames.put(198, "oculus_ota_user_control_allowed_check");
        BooleanNames.put(206, "horizon_enabled");
        LongNames.put(7, "auto_update_min_battery_percent");
        LongNames.put(15, "consistency_uninstalling_state_timeout_mins");
        LongNames.put(23, "challenges_tab_subfeed_id");
        DoubleNames.put(1, "double2");
        StringNames.put(0, "string1");
        StringNames.put(8, "min_hmd_build_number_system_action_remote_launch");
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
