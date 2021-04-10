package com.oculus.common.socialtablet.deviceconfig;

import com.oculus.common.deviceconfig.DeviceConfigMC;

public enum DeviceConfigSocialPlatformMC implements DeviceConfigMC {
    AUI_PEOPLE_TAB_SHOW_PEOPLE_NEARBY("oculus_sysux_social:aui_people_tab_show_people_nearby"),
    AUI_PEOPLE_TAB_SHOW_PYMK("oculus_sysux_social:aui_people_tab_show_pymk"),
    AUI_PEOPLE_TAB_PARTIES_KILL_SWITCH("oculus_sysux_social:aui_people_tab_parties_kill_switch"),
    AUI_PEOPLE_TAB_QUICK_MESSAGE_KILL_SWITCH("oculus_sysux_social:aui_people_tab_quick_message_kill_switch"),
    AUI_V2_MESSENGER_OC_MIGRATION("oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger_oc_migration"),
    CREATE_PARTY_FAR_FIELD_VIEW("oculus_social_platform_deviceconfig:oculus_far_field_android_create_party_view"),
    JOIN_PARTY_FAR_FIELD_VIEW("oculus_social_platform_deviceconfig:oculus_far_field_android_join_party_view"),
    MESSENGER_DISABLE_COMPOSER("oculus_social_platform_deviceconfig:oculus_messenger_disable_composer"),
    MESSENGER_PROFILE_SWITCHER_EDUCATION_TOOLTIP("oculus_social_platform_deviceconfig:messenger_profile_switcher_education_tooltip"),
    MESSENGER_VR_COMPOSER_DICTATION("oculus_social_platform_deviceconfig:messenger_vr_composer_dictation"),
    MESSENGER_VR_QUICK_REPLIES("oculus_social_platform_deviceconfig:messenger_vr_quick_replies"),
    MESSENGER_VR_START_PARTY_ENTRYPOINT("oculus_social_platform_deviceconfig:messenger_vr_start_party_entrypoint"),
    MESSENGER_VR_REACTIONS_READ("oculus_social_platform_deviceconfig:messenger_vr_reactions_read"),
    MESSENGER_VR_REACTIONS_READ_TOOLTIP("oculus_social_platform_deviceconfig:messenger_vr_reactions_read_tooltip"),
    MESSENGER_VR_REAUTH_REDUCE_NUX_FRICTION("oculus_social_platform_deviceconfig:oculus_messenger_reduce_nux_friction"),
    OCUI_SYSTEM_THEME_SETTING("oculus_social_platform_deviceconfig:oculus_ocui_system_theme_setting"),
    OCULUS_PARTIES_ENABLE_AUI_MIC_SWITCHER_CONTROLS("oculus_shared_social:oculus_parties_enable_aui_mic_switcher_controls"),
    OCULUS_PARTIES_INVITE_FROM_FB_ENTRYPOINT("oculus_social_platform_deviceconfig:oculus_parties_invite_from_fb_entrypoint"),
    OCULUS_PARTY_ANYONE_CAN_SET_DESTINATION("oculus_social_platform_deviceconfig:oculus_party_anyone_can_set_destination"),
    OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY("oculus_social_platform_deviceconfig:oculus_travel_together_async_flow_only"),
    MESSENGER_VR_ENABLE_OC_REPORT_FLOW("oculus_social_platform_deviceconfig:oculus_messenger_enable_report_flow"),
    MESSENGER_VR_ENABLE_FB_REPORT_FLOW("oculus_social_platform_deviceconfig:messenger_vr_enable_report_flow"),
    PARTY_PRIVACY_FAR_FIELD_VIEW("oculus_social_platform_deviceconfig:oculus_far_field_android_party_privacy_view"),
    AUI_PEOPLE_TAB_SHOW_FB_SEARCH("oculus_sysux_social:aui_people_tab_show_fb_search");
    
    public final String mName;

    @Override // com.oculus.common.deviceconfig.DeviceConfigMC
    public String toString() {
        return this.mName;
    }

    /* access modifiers changed from: public */
    DeviceConfigSocialPlatformMC(String str) {
        this.mName = str;
    }
}
