package com.facebook.mobileconfig;

import com.facebook.mobileconfig.specifier.MobileConfigSpecifierUtil;
import com.facebook.ultralight.UL;
import com.oculus.userserver.api.sharing.SharingManagerContract;
import java.util.HashMap;
import java.util.Map;

public class MobileConfigParamNames4 {
    private static Map<Integer, String> BooleanNames = new HashMap();
    private static Map<Integer, String> DoubleNames = new HashMap();
    private static Map<Integer, String> LongNames = new HashMap();
    private static Map<Integer, String> StringNames = new HashMap();

    static {
        BooleanNames.put(5, "disable_download_queue_standalone");
        BooleanNames.put(13, "enable_persistent_patches");
        BooleanNames.put(21, "can_view_post_purchase_referral_link");
        BooleanNames.put(29, "install_after_purchase");
        BooleanNames.put(37, "is_discovery_tab_enabled");
        BooleanNames.put(45, "is_oculus_twilight_quest_headset_pin_screen_enabled");
        BooleanNames.put(53, "is_remote_launch_enabled");
        BooleanNames.put(61, "oculus_client_manage_rcs");
        BooleanNames.put(69, "require_fb_link_social_bundle");
        BooleanNames.put(77, "show_disco_pdp");
        BooleanNames.put(85, "show_store_pdp_dlc");
        BooleanNames.put(93, "oculus_twilight_store_installed_some_buy_button");
        BooleanNames.put(101, "is_tour_guide_v2_enabled");
        BooleanNames.put(109, "can_see_referral_button_in_casting");
        BooleanNames.put(Integer.valueOf((int) UL.id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID), "is_software_referrals_enabled");
        BooleanNames.put(125, "is_oculus_twilight_shortened_hollywood_nux_enabled");
        BooleanNames.put(133, "is_people_tab_revamp_enabled");
        BooleanNames.put(141, "show_pagination_dots");
        BooleanNames.put(149, "demo_mode_gk_enabled");
        BooleanNames.put(157, "enable_turbomodules");
        BooleanNames.put(165, "should_show_search_top_searches");
        BooleanNames.put(173, "app_subfeed_tiles_are_circular");
        BooleanNames.put(181, "dont_rate_limit_logging");
        BooleanNames.put(189, "oc_engagement_h2_2020_shipped_targeting_gk");
        BooleanNames.put(197, "dev_posts_ufi");
        BooleanNames.put(205, SharingManagerContract.ARGUMENT_IS_ENABLED);
        LongNames.put(6, "auto_update_interval_ms");
        LongNames.put(14, "consistency_verifying_state_timeout_mins");
        LongNames.put(22, "saved_subfeed_id");
        DoubleNames.put(0, "double1");
        StringNames.put(7, "min_hmd_build_number_2d_party_join");
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
