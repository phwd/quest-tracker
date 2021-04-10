package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames3 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(4, "bool4");
        BooleanNames.put(12, "enable_install_integrity_check");
        BooleanNames.put(20, "can_view_messaging_attachments");
        BooleanNames.put(28, "has_live_event_join_in_vr");
        BooleanNames.put(36, "is_create_public_event_enabled");
        BooleanNames.put(44, "is_oculus_twilight_groups_view_enabled");
        BooleanNames.put(52, "is_remote_keyboard_enabled");
        BooleanNames.put(60, "is_unlink_modal_enabled");
        BooleanNames.put(68, "ping_headset_on_install");
        BooleanNames.put(76, "show_credit_card_form_inline_quest_nux");
        BooleanNames.put(84, "show_related_items");
        BooleanNames.put(92, "show_store_pdp_external_review_for_v27_and_after");
        BooleanNames.put(100, "oculus_3ds2_test_flow");
        BooleanNames.put(108, "is_event_destinations_enabled");
        BooleanNames.put(116, "is_devices_screen_enabled");
        BooleanNames.put(124, "is_oculus_twilight_hollywood_nux_enabled");
        BooleanNames.put(132, "is_add_on_content_reviews_enabled");
        BooleanNames.put(140, "enable_phone_notifications");
        BooleanNames.put(148, "is_linked_profile_enabled");
        BooleanNames.put(156, "is_store_revamp_enabled");
        BooleanNames.put(164, "should_show_search_recent_searches");
        BooleanNames.put(172, "is_event_mutate_form_v2_enabled");
        BooleanNames.put(180, "should_use_full_markdown_rendering");
        BooleanNames.put(188, "oculus_holdout_quest_incentives");
        BooleanNames.put(196, "is_milestones_enabled");
        BooleanNames.put(204, "is_live_search_enabled");
        LongNames.put(5, "auto_update_max_retries");
        LongNames.put(13, "consistency_verified_state_timeout_mins");
        LongNames.put(21, "scrolled_event_content_offset");
        StringNames.put(6, "search_type_filters");
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
