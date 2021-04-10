package com.oculus.common.gatekeepers;

import java.util.ArrayList;

public enum Gatekeeper {
    ACTIVE_CALL_BAR_ENABLED("oculus_active_call_bar_mc:active_call_bar_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    ASSISTANT_DOUBLE_TAP_SETTING("oculus_systemux:assistant_oculus_doubletap_setting"),
    ASSISTANT_IN_APP_VOICE_COMMANDS("oculus_systemux:in_app_assistant_on_oculus"),
    ASSISTANT_NATIVE_VOICE_ACTIVITY("oculus_systemux:oc_native_voice_activity_log_page"),
    ASSISTANT_TTS_SELECTION("oculus_systemux:oc_assistant_tts_selection"),
    ASSISTANT_VIEW_COMMANDS_ON_OCULUS("oculus_systemux:assistant_view_commands_on_oculus"),
    ASSISTANT_WAKEWORD("oculus_systemux:oc_assistant_wakeword_enabled"),
    AUI_ASSISTANT_QUICK_ACTIONS_COLOR("oculus_systemux:oc_assistant_quick_actions_color"),
    AUI_ASSISTANT("oculus_systemux:assistant_on_oculus"),
    AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG("oculus_casting:aui_enable_local_cast_network_check_dialog", GKFlag.USE_DEVICE_CONFIG_ONLY),
    AUI_HOME_INVITE_BUTTON("oculus_systemux:oculus_mobile_pause_invite_enabled"),
    AUI_OC_PROFILE_PARTY_BUTTON("oculus_systemux:oculus_aui_oc_profile_party_button"),
    AUI_ONBOARDING_TUTORIAL("oculus_systemux:oculus_mobile_auiv2_onboarding_tutorial"),
    AUI_PARTY_FB_UPSELL("oculus_systemux:oculus_social_party_fb_upsell"),
    AUI_PROFILE_MESSENGER_ACTIONS("oculus_sysux_social:aui_profile_messenger_actions", GKFlag.USE_DEVICE_CONFIG_ONLY),
    AUI_STANDALONE_APPS("oculus_shared_systemshell:oculus_vrshell_aui_standalone_apps_v2"),
    AUI_V2_ACTIVE_CALL_BAR("oculus_systemux:oculus_mobile_auiv2_active_call_bar"),
    AUI_V2_ACTIVE_CALL_BAR_ENHANCED("oculus_systemux:oculus_mobile_auiv2_active_call_bar_enhanced"),
    AUI_V2_ACTIVE_CALL_BAR_BUTTONS("oculus_systemux:oculus_mobile_auiv2_active_call_bar_buttons"),
    AUI_V2_EVENTS_PANEL_ENTRY("oculus_systemux:oculus_social_panel_events_only"),
    AUI_V2_FB_PROFILE_PARTY_BUTTON("oculus_systemux:oculus_aui2_fb_profile_party_button"),
    AUI_V2_FB_PROFILE_PRESENCE("oculus_systemux:oculus_aui2_fb_profile_presence"),
    AUI_V2_MESSENGER("oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger", GKFlag.USE_DEVICE_CONFIG_ONLY),
    AUI_V2_MESSENGER_ICON("oculus_systemux:oc_aui_messenger_entry_icon"),
    AUI_V2_QP_KILLSWITCH("oculus_systemux:oculus_mobile_aui_v2_qp_killswitch"),
    AUI_V2_SHOW_PYMK("oculus_shared_systemshell:oculus_vrshell_anytimeui_v2_show_pymks"),
    AUI_V2_STORE_OP_BAR_LABELS_ENABLED("oculus_systemux:oculus_mobile_store_exp_v23_bar_label_enabled"),
    AUI_V2_STORE_OP_GET_MORE_APPS("oculus_systemux:oculus_mobile_store_exp_v23_get_more_apps_enabled"),
    AUI_V2_STORE_OP_GK_GROUP_1("oculus_systemux:oculus_mobile_auiv2_store_op_group_1"),
    AUI_V2_STORE_OP_GK_GROUP_2("oculus_systemux:oculus_mobile_auiv2_store_op_group_2"),
    AUI_V2_STORE_OP_GK_GROUP_3("oculus_systemux:oculus_mobile_auiv2_store_op_group_3"),
    AUI_V2_STORE_OP_GK_GROUP_4("oculus_systemux:oculus_mobile_auiv2_store_op_group_4"),
    AUI_V2_STORE_OP_GK_GROUP_5("oculus_systemux:oculus_mobile_auiv2_store_op_group_5"),
    AUI_V2_STORE_OP_LIBRARY_HEADER_COLORED_TILES("oculus_systemux:oculus_mobile_store_exp_v23_1p_apps_pinned_colored"),
    AUI_V2_STORE_OP_STORE_ALWAYS_FIRST_APP_TILE("oculus_systemux:oculus_mobile_store_exp_v23_store_always_first_app"),
    AUI_V2_STORE_OP_STORE_IN_APP_TILES("oculus_systemux:oculus_mobile_store_exp_v23_store_in_app_tiles"),
    AUI_V2_STORE_OP_STORE_IN_BAR_POSITION_LEFT("oculus_systemux:oculus_mobile_store_exp_v23_store_in_bar_left"),
    AUI_V2_STORE_OP_STORE_IN_BAR_POSITION_RIGHT("oculus_systemux:oculus_mobile_store_exp_v23_store_in_bar_right"),
    AUI_V2_STORE_OP_SYSTEM_APPS_AS_TILES("oculus_systemux:oculus_mobile_store_exp_v23_system_apps_tiles"),
    BLUETOOTH_MOUSE_SECTION("oculus_shared_systemshell:oculus_vrshell_mouse_input"),
    CONFIGURABLE_MTP_DIALOG("oculus_systemux:oculus_configurable_mtp_dialog"),
    COUCH_CREATION("oculus_systemux:oculus_mobile_guardian_surface_creation_gk"),
    DEVICECONFIG_ENABLED("oculus_systemux:oculus_systemux_deviceconfig_enabled"),
    DESK_CREATION("oculus_systemux:oculus_mobile_guardian_mr_desk_gk"),
    DUC_LIBRARY_UI_NOTICE("oculus_systemux:oculus_duc_library_ui_notice_mobile"),
    EXPERIMENTAL_120HZ_REFRESH_RATE("oculus_systemux:oculus_experimental_120hz_support"),
    FITNESS_TRACKER("oculus_systemux:oculus_fitness_tracking_dogfooding"),
    FONT_SCALING_GK("oculus_systemux:oculus_ocui_font_scaling"),
    FORCE_OVERLAYS("oculus_shared_systemshell:oculus_vrshell_force_focus_awareness_experiment"),
    HAND_TRACKING_FREQUENCY("oculus_systemux:oculus_mobile_setting_hand_tracking_frequency"),
    INTRUSION_DETECTION("oculus_systemux:oculus_intrusion_detection"),
    IS_TRUSTED_USER("oculus_mobile_trusted_user:is_trusted_user", GKFlag.USE_DEVICE_CONFIG_ONLY),
    KEYBOARD_SETTINGS_SECTION("oculus_systemux:oc_keyboard_settings_section"),
    TRACKED_KEYBOARD_SECTION("oculus_systemux:oculus_vr_keyboard_enabled"),
    KEYBOARD_FEDERATED_LEARNING("oculus_systemux:oc_assistant_keyboard_fl"),
    LIBRARY_APPS_DISK_CACHE("oculus_systemux:oculus_mobile_library_apps_disk_cache"),
    LIBRARY_PROTOTYPE_ORGANIZATION_ID("oculus_mobile_library_protype_filter_organization:organization_id", GKFlag.USE_DEVICE_CONFIG_ONLY),
    LIBRARY_STATE_AUTO_RESET("oculus_systemux:oculus_mobile_library_state_auto_reset"),
    MULTIAPP_PUBLIC("oculus_shared_systemshell:oculus_vrshell_multiapp_public"),
    MULTIAPP_ROLLOUT("oculus_shared_systemshell:oculus_vrshell_multiapp_rollout"),
    NEW_SHARESHEET_AND_MAX_VIEW("oculus_systemux:oc_social_h1_2020_mobile_sharing_updates"),
    NIGHT_DISPLAY_SECTION_SLIDER("oculus_systemux:oculus_mobile_settings_night_shift_slider"),
    NOTIFICATIONS_FEED_REFRESH("oculus_systemux:oculus_notifications_feed_refresh"),
    NOTIFICATIONS_FEED_REFRESH_DELAY("oculus_notifications:vr_feed_reload_delay", GKFlag.USE_DEVICE_CONFIG_ONLY),
    NOTIFICATIONS_LAUNCH_DESTINATIONS("oculus_systemux:oculus_notifications_launch_destination"),
    OCULUS_CHATS("oculus_systemux:oculus_messenger"),
    OCULUS_DEVELOPER("oculus_systemux:oculus_developer"),
    OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY("oculus_systemux:oculus_travel_together_async_flow_only"),
    OCULUS_PHONE_NOTIFICATIONS("oculus_systemux:oculus_phone_notifications"),
    OVERLAY_BROWSER_EXPERIMENT("oculus_systemux:oculus_vrshell_overlay_browser_experiment"),
    PARTIES_PER_PERSON_MUTE("oculus_systemux:oculus_party_per_person_mute"),
    PASSTHROUGH_PORTAL("oculus_systemux:oculus_mobile_passthrough_portal"),
    PHONE_NOTIFICATIONS_FEED_TAB_ENABLED("oculus_systemux_phone_notifications:feed_tab_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    POINTER_POSE_FILTER_TUNING("oculus_systemux:oculus_mobile_pointer_pose_filter_tuning"),
    PROFILE_SHARING("oculus_systemux:oculus_quest_profile_sharing"),
    QUEST_DOGFOODING_UPDATE("oculus_systemux:oculus_quest_dogfooding_update"),
    QUEST_INFINITE_OFFICE_PLATFORM("oculus_systemux:oculus_quest_infinite_office_platform"),
    QUICKBOOT("oculus_systemux:oculus_quest_passthrough_background_quickboot"),
    QUICK_MESSAGE_DIALOG("oculus_systemux:oculus_messenger_quick_chat_dialog"),
    REALITY_TUNER("oculus_systemux:oculus_guardian_reality_tuner"),
    SHARE_PARTY_BUTTON("oculus_systemux:oculus_share_party_button"),
    SOCIAL_PLATFORM_CONTENT_PROVIDER("oculus_systemux:oculus_social_platform_content_provider"),
    SETTINGS_INPUT_HAPTICS_AMPLITUDE("oculus_systemux:oculus_mobile_settings_input_haptics_amplitude"),
    SETTINGS_NEW_NOTIFICATIONS_SOUND("oculus_systemux:oculus_new_notifications_sound"),
    SETTINGS_NOTIFS_USER_CONTROL("oculus_systemux:oc_notifs_nuc_xr_device"),
    SETTINGS_SWAP_SYSTEM_BTN_HANDEDNESS_GK("oculus_systemux:oculus_mobile_settings_swap_system_btn_handedness"),
    SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY("oculus_social_platform_deviceconfig:oculus_integrity_remove_recording_overlay", GKFlag.USE_DEVICE_CONFIG_ONLY),
    SOCIAL_DESTINATION_INVITE_ENABLED("oculus_social_platform_deviceconfig:oculus_mobile_pause_invite_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    SOCIAL_NEW_PARTIES_PANEL_APP("oculus_social_platform_deviceconfig:oculus_party_new_parties_panel_app", GKFlag.USE_DEVICE_CONFIG_ONLY),
    SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI("oculus_social_platform_deviceconfig:oculus_party_invite_from_destination_ui", GKFlag.USE_DEVICE_CONFIG_ONLY),
    SOCIAL_SKIP_DESTINATION_STEP("oculus_social_platform_deviceconfig:oculus_gtvr_skip_destination_step", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STATIONARY_GUARDIAN_V2("oculus_systemux:oculus_stationary_guardian_v2"),
    STORE_OPTIONALITY_BAR_LABELS_ENABLED("oculus_store_optionality_mc:bar_button_labels_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GET_MORE_APPS("oculus_store_optionality_mc:get_more_apps_button_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GROUP_1("oculus_store_optionality_mc:group_1", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GROUP_2("oculus_store_optionality_mc:group_2", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GROUP_3("oculus_store_optionality_mc:group_3", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GROUP_4("oculus_store_optionality_mc:group_4", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_GROUP_5("oculus_store_optionality_mc:group_5", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_LIBRARY_HEADER_COLORED_TILES("oculus_store_optionality_mc:library_header_colored_tiles_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_STORE_ALWAYS_FIRST_APP_TILE("oculus_store_optionality_mc:store_always_first_app_tile_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_STORE_IN_APP_TILES("oculus_store_optionality_mc:store_present_in_app_tiles_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_STORE_IN_BAR_POSITION_LEFT("oculus_store_optionality_mc:store_in_bar_position_left_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_STORE_IN_BAR_POSITION_RIGHT("oculus_store_optionality_mc:store_in_bar_position_right_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    STORE_OPTIONALITY_SYSTEM_APPS_AS_TILES("oculus_store_optionality_mc:system_apps_as_tiles_enabled", GKFlag.USE_DEVICE_CONFIG_ONLY),
    SYSTEM_THEME_SETTING("oculus_systemux:oculus_ocui_system_theme_setting"),
    TRUSTED_USER("oculus_systemux:oculus_is_trusted_user");
    
    private static final String TAG = "Gatekeeper";
    private String mConfigName;
    private final String mConfigParamName;
    private final GKFlag mGKFlag;
    private String mParamName;

    private Gatekeeper(String str) {
        this.mConfigParamName = str;
        this.mGKFlag = GKFlag.USE_SYSTEM_SHELL_GK;
        splitConfigParamName();
    }

    private Gatekeeper(String str, GKFlag gKFlag) {
        this.mConfigParamName = str;
        this.mGKFlag = gKFlag;
        splitConfigParamName();
    }

    private void splitConfigParamName() {
        String[] split = this.mConfigParamName.split(":", 2);
        if (split.length >= 2) {
            this.mConfigName = split[0];
            this.mParamName = split[1];
            return;
        }
        throw new IllegalArgumentException(String.format("Unexpected configParamName: \"%s\". Expect in format \"configName:paramName\".", this.mConfigParamName));
    }

    public String toString() {
        return this.mParamName;
    }

    public String toDeviceConfigString() {
        return this.mConfigParamName;
    }

    public boolean isSharedConfigParam() {
        return "oculus_shared_systemshell".equals(this.mConfigName);
    }

    public boolean shouldUseDeviceConfig() {
        return this.mGKFlag == GKFlag.USE_DEVICE_CONFIG_ONLY;
    }

    public static ArrayList<String> getGatekeepers() {
        ArrayList<String> arrayList = new ArrayList<>();
        Gatekeeper[] values = values();
        for (Gatekeeper gatekeeper : values) {
            if (gatekeeper.mGKFlag != GKFlag.USE_DEVICE_CONFIG_ONLY) {
                arrayList.add(gatekeeper.toString() + " 0");
            }
        }
        return arrayList;
    }
}
