package com.oculus.common.gatekeepers;

import X.AnonymousClass006;
import java.util.ArrayList;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class Gatekeeper extends Enum<Gatekeeper> {
    public static final /* synthetic */ Gatekeeper[] $VALUES;
    public static final Gatekeeper ACTIVE_CALL_BAR_MICROPHONE_CONTROLS = new Gatekeeper("ACTIVE_CALL_BAR_MICROPHONE_CONTROLS", 0, "oculus_systemux:oculus_mobile_active_call_bar_microphone_controls");
    public static final Gatekeeper ACTIVE_CALL_BAR_VOLUME_CONTROLS = new Gatekeeper("ACTIVE_CALL_BAR_VOLUME_CONTROLS", 1, "oculus_systemux:oculus_mobile_active_call_bar_volume_controls");
    public static final Gatekeeper AIRLINK_ENABLED = new Gatekeeper("AIRLINK_ENABLED", 2, "oculus_systemux:oculus_enable_al");
    public static final Gatekeeper ASSISTANT_DOUBLE_TAP_SETTING = new Gatekeeper("ASSISTANT_DOUBLE_TAP_SETTING", 3, "oculus_systemux:assistant_oculus_doubletap_setting");
    public static final Gatekeeper ASSISTANT_IN_APP_VOICE_COMMANDS = new Gatekeeper("ASSISTANT_IN_APP_VOICE_COMMANDS", 4, "oculus_systemux:in_app_assistant_on_oculus");
    public static final Gatekeeper ASSISTANT_NATIVE_VOICE_ACTIVITY = new Gatekeeper("ASSISTANT_NATIVE_VOICE_ACTIVITY", 5, "oculus_systemux:oc_native_voice_activity_log_page");
    public static final Gatekeeper ASSISTANT_TTS_SELECTION = new Gatekeeper("ASSISTANT_TTS_SELECTION", 6, "oculus_systemux:oc_assistant_tts_selection");
    public static final Gatekeeper ASSISTANT_VIEW_COMMANDS_ON_OCULUS = new Gatekeeper("ASSISTANT_VIEW_COMMANDS_ON_OCULUS", 7, "oculus_systemux:assistant_view_commands_on_oculus");
    public static final Gatekeeper ASSISTANT_WAKEWORD = new Gatekeeper("ASSISTANT_WAKEWORD", 8, "oculus_systemux:oc_assistant_wakeword_enabled");
    public static final Gatekeeper AUI_ASSISTANT = new Gatekeeper("AUI_ASSISTANT", 10, "oculus_systemux:assistant_on_oculus");
    public static final Gatekeeper AUI_ASSISTANT_QUICK_ACTIONS_COLOR = new Gatekeeper("AUI_ASSISTANT_QUICK_ACTIONS_COLOR", 9, "oculus_systemux:oc_assistant_quick_actions_color");
    public static final Gatekeeper AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG;
    public static final Gatekeeper AUI_HOME_INVITE_BUTTON = new Gatekeeper("AUI_HOME_INVITE_BUTTON", 12, "oculus_systemux:oculus_mobile_pause_invite_enabled");
    public static final Gatekeeper AUI_NEW_BAR_VIEW = new Gatekeeper("AUI_NEW_BAR_VIEW", 17, "oculus_systemux:oculus_mobile_auiv2_new_bar_view");
    public static final Gatekeeper AUI_OC_PROFILE_PARTY_BUTTON = new Gatekeeper("AUI_OC_PROFILE_PARTY_BUTTON", 13, "oculus_systemux:oculus_aui_oc_profile_party_button");
    public static final Gatekeeper AUI_ONBOARDING_TUTORIAL = new Gatekeeper("AUI_ONBOARDING_TUTORIAL", 14, "oculus_systemux:oculus_mobile_auiv2_onboarding_tutorial");
    public static final Gatekeeper AUI_PARTY_FB_UPSELL = new Gatekeeper("AUI_PARTY_FB_UPSELL", 15, "oculus_systemux:oculus_social_party_fb_upsell");
    public static final Gatekeeper AUI_PROFILE_MESSENGER_ACTIONS;
    public static final Gatekeeper AUI_V2_EVENTS_PANEL_ENTRY = new Gatekeeper("AUI_V2_EVENTS_PANEL_ENTRY", 18, "oculus_systemux:oculus_social_panel_events_only");
    public static final Gatekeeper AUI_V2_FB_PROFILE_PARTY_BUTTON = new Gatekeeper("AUI_V2_FB_PROFILE_PARTY_BUTTON", 19, "oculus_systemux:oculus_aui2_fb_profile_party_button");
    public static final Gatekeeper AUI_V2_FB_PROFILE_PRESENCE = new Gatekeeper("AUI_V2_FB_PROFILE_PRESENCE", 20, "oculus_systemux:oculus_aui2_fb_profile_presence");
    public static final Gatekeeper AUI_V2_MESSENGER;
    public static final Gatekeeper AUI_V2_MESSENGER_ICON = new Gatekeeper("AUI_V2_MESSENGER_ICON", 22, "oculus_systemux:oc_aui_messenger_entry_icon");
    public static final Gatekeeper AUI_V2_QP_KILLSWITCH = new Gatekeeper("AUI_V2_QP_KILLSWITCH", 23, "oculus_systemux:oculus_mobile_aui_v2_qp_killswitch");
    public static final Gatekeeper AUI_V2_SHOW_PYMK = new Gatekeeper("AUI_V2_SHOW_PYMK", 24, "oculus_systemux:oculus_vrshell_anytimeui_v2_show_pymks");
    public static final Gatekeeper BLUETOOTH_MOUSE_SECTION = new Gatekeeper("BLUETOOTH_MOUSE_SECTION", 25, "oculus_shared_systemshell:oculus_vrshell_mouse_input");
    public static final Gatekeeper CAST_WWW_ENABLE_MICROPHONE;
    public static final Gatekeeper CONFIGURABLE_MTP_DIALOG = new Gatekeeper("CONFIGURABLE_MTP_DIALOG", 27, "oculus_systemux:oculus_configurable_mtp_dialog");
    public static final Gatekeeper COUCH_CREATION = new Gatekeeper("COUCH_CREATION", 28, "oculus_systemux:oculus_mobile_guardian_surface_creation_gk");
    public static final Gatekeeper DESK_CREATION = new Gatekeeper("DESK_CREATION", 30, "oculus_systemux:oculus_mobile_guardian_mr_desk_gk");
    public static final Gatekeeper DEVICECONFIG_ENABLED = new Gatekeeper("DEVICECONFIG_ENABLED", 29, "oculus_systemux:oculus_systemux_deviceconfig_enabled");
    public static final Gatekeeper DUC_LIBRARY_UI_NOTICE = new Gatekeeper("DUC_LIBRARY_UI_NOTICE", 31, "oculus_systemux:oculus_duc_library_ui_notice_mobile");
    public static final Gatekeeper EXPERIMENTAL_120HZ_REFRESH_RATE = new Gatekeeper("EXPERIMENTAL_120HZ_REFRESH_RATE", 32, "oculus_systemux:oculus_experimental_120hz_support");
    public static final Gatekeeper FILE_MANAGER = new Gatekeeper("FILE_MANAGER", 33, "oculus_systemux:oculus_mobile_file_manager");
    public static final Gatekeeper FITNESS_TRACKER = new Gatekeeper("FITNESS_TRACKER", 34, "oculus_systemux:oculus_fitness_tracking_dogfooding");
    public static final Gatekeeper FONT_SCALING_GK = new Gatekeeper("FONT_SCALING_GK", 35, "oculus_systemux:oculus_ocui_font_scaling");
    public static final Gatekeeper FORCE_OVERLAYS = new Gatekeeper("FORCE_OVERLAYS", 36, "oculus_shared_systemshell:oculus_vrshell_force_focus_awareness_experiment");
    public static final Gatekeeper HAND_TRACKING_FREQUENCY = new Gatekeeper("HAND_TRACKING_FREQUENCY", 37, "oculus_systemux:oculus_mobile_setting_hand_tracking_frequency");
    public static final Gatekeeper INTRUSION_DETECTION = new Gatekeeper("INTRUSION_DETECTION", 38, "oculus_systemux:oculus_intrusion_detection");
    public static final Gatekeeper IS_TRUSTED_USER;
    public static final Gatekeeper KEYBOARD_FEDERATED_LEARNING = new Gatekeeper("KEYBOARD_FEDERATED_LEARNING", 42, "oculus_systemux:oc_assistant_keyboard_fl");
    public static final Gatekeeper KEYBOARD_SETTINGS_SECTION = new Gatekeeper("KEYBOARD_SETTINGS_SECTION", 40, "oculus_systemux:oc_keyboard_settings_section");
    public static final Gatekeeper LIBRARY_APPS_DISK_CACHE = new Gatekeeper("LIBRARY_APPS_DISK_CACHE", 43, "oculus_systemux:oculus_mobile_library_apps_disk_cache");
    public static final Gatekeeper LIBRARY_PROTOTYPE_ORGANIZATION_ID;
    public static final Gatekeeper LIBRARY_STATE_AUTO_RESET = new Gatekeeper("LIBRARY_STATE_AUTO_RESET", 45, "oculus_systemux:oculus_mobile_library_state_auto_reset");
    public static final Gatekeeper MULTIAPP_PUBLIC = new Gatekeeper("MULTIAPP_PUBLIC", 46, "oculus_systemux:oculus_vrshell_multiapp_public");
    public static final Gatekeeper MULTIAPP_ROLLOUT = new Gatekeeper("MULTIAPP_ROLLOUT", 47, "oculus_systemux:oculus_vrshell_multiapp_rollout");
    public static final Gatekeeper NEW_SHARESHEET_AND_MAX_VIEW = new Gatekeeper("NEW_SHARESHEET_AND_MAX_VIEW", 48, "oculus_systemux:oc_social_h1_2020_mobile_sharing_updates");
    public static final Gatekeeper NIGHT_DISPLAY_SECTION_SLIDER = new Gatekeeper("NIGHT_DISPLAY_SECTION_SLIDER", 49, "oculus_systemux:oculus_mobile_settings_night_shift_slider");
    public static final Gatekeeper NOTIFICATIONS_FEED_REFRESH = new Gatekeeper("NOTIFICATIONS_FEED_REFRESH", 50, "oculus_systemux:oculus_notifications_feed_refresh");
    public static final Gatekeeper NOTIFICATIONS_FEED_REFRESH_DELAY;
    public static final Gatekeeper NOTIFICATIONS_LAUNCH_DESTINATIONS = new Gatekeeper("NOTIFICATIONS_LAUNCH_DESTINATIONS", 52, "oculus_systemux:oculus_notifications_launch_destination");
    public static final Gatekeeper OCULUS_AUI_ROSTER_ICONS;
    public static final Gatekeeper OCULUS_CHATS = new Gatekeeper("OCULUS_CHATS", 53, "oculus_systemux:oculus_messenger");
    public static final Gatekeeper OCULUS_DEVELOPER = new Gatekeeper("OCULUS_DEVELOPER", 54, "oculus_systemux:oculus_developer");
    public static final Gatekeeper OCULUS_PHONE_NOTIFICATIONS = new Gatekeeper("OCULUS_PHONE_NOTIFICATIONS", 56, "oculus_systemux:oculus_phone_notifications");
    public static final Gatekeeper OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY = new Gatekeeper("OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY", 55, "oculus_systemux:oculus_travel_together_async_flow_only");
    public static final Gatekeeper OVERLAY_BROWSER_EXPERIMENT = new Gatekeeper("OVERLAY_BROWSER_EXPERIMENT", 57, "oculus_systemux:oculus_vrshell_overlay_browser_experiment");
    public static final Gatekeeper PARTIES_PER_PERSON_MUTE = new Gatekeeper("PARTIES_PER_PERSON_MUTE", 58, "oculus_systemux:oculus_party_per_person_mute");
    public static final Gatekeeper PASSTHROUGH_PORTAL = new Gatekeeper("PASSTHROUGH_PORTAL", 59, "oculus_systemux:oculus_mobile_passthrough_portal");
    public static final Gatekeeper PHONE_NOTIFICATIONS_FEED_TAB_ENABLED;
    public static final Gatekeeper POINTER_POSE_FILTER_TUNING = new Gatekeeper("POINTER_POSE_FILTER_TUNING", 61, "oculus_systemux:oculus_mobile_pointer_pose_filter_tuning");
    public static final Gatekeeper PROFILE_SHARING = new Gatekeeper("PROFILE_SHARING", 62, "oculus_systemux:oculus_quest_profile_sharing");
    public static final Gatekeeper QUEST_DOGFOODING_UPDATE = new Gatekeeper("QUEST_DOGFOODING_UPDATE", 63, "oculus_systemux:oculus_quest_dogfooding_update");
    public static final Gatekeeper QUEST_INFINITE_OFFICE_PLATFORM = new Gatekeeper("QUEST_INFINITE_OFFICE_PLATFORM", 64, "oculus_systemux:oculus_quest_infinite_office_platform");
    public static final Gatekeeper QUICKBOOT = new Gatekeeper("QUICKBOOT", 65, "oculus_systemux:oculus_quest_passthrough_background_quickboot");
    public static final Gatekeeper QUICK_MESSAGE_DIALOG = new Gatekeeper("QUICK_MESSAGE_DIALOG", 66, "oculus_systemux:oculus_messenger_quick_chat_dialog");
    public static final Gatekeeper QUICK_SETTINGS_TABLET = new Gatekeeper("QUICK_SETTINGS_TABLET", 67, "oculus_shared_systemshell:oculus_mobile_auiv2_quick_settings_tablet");
    public static final Gatekeeper REALITY_TUNER = new Gatekeeper("REALITY_TUNER", 68, "oculus_systemux:oculus_guardian_reality_tuner");
    public static final Gatekeeper SETTINGS_ASSISTANT_JSON_UNBLOCK = new Gatekeeper("SETTINGS_ASSISTANT_JSON_UNBLOCK", 71, "oculus_systemux:oculus_mobile_unblock_settings_json");
    public static final Gatekeeper SETTINGS_ENVIRONMENT_TYPE_SELECTOR;
    public static final Gatekeeper SETTINGS_INPUT_HAPTICS_AMPLITUDE = new Gatekeeper("SETTINGS_INPUT_HAPTICS_AMPLITUDE", 73, "oculus_systemux:oculus_mobile_settings_input_haptics_amplitude");
    public static final Gatekeeper SETTINGS_NEW_NOTIFICATIONS_SOUND = new Gatekeeper("SETTINGS_NEW_NOTIFICATIONS_SOUND", 74, "oculus_systemux:oculus_new_notifications_sound");
    public static final Gatekeeper SETTINGS_NOTIFS_USER_CONTROL = new Gatekeeper("SETTINGS_NOTIFS_USER_CONTROL", 75, "oculus_systemux:oc_notifs_nuc_xr_device");
    public static final Gatekeeper SETTINGS_SWAP_SYSTEM_BTN_HANDEDNESS_GK = new Gatekeeper("SETTINGS_SWAP_SYSTEM_BTN_HANDEDNESS_GK", 76, "oculus_systemux:oculus_mobile_settings_swap_system_btn_handedness");
    public static final Gatekeeper SHARE_PARTY_BUTTON = new Gatekeeper("SHARE_PARTY_BUTTON", 69, "oculus_systemux:oculus_share_party_button");
    public static final Gatekeeper SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY;
    public static final Gatekeeper SOCIAL_DESTINATION_INVITE_ENABLED;
    public static final Gatekeeper SOCIAL_NEW_PARTIES_PANEL_APP;
    public static final Gatekeeper SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI;
    public static final Gatekeeper SOCIAL_PLATFORM_CONTENT_PROVIDER = new Gatekeeper("SOCIAL_PLATFORM_CONTENT_PROVIDER", 70, "oculus_systemux:oculus_social_platform_content_provider");
    public static final Gatekeeper SOCIAL_SKIP_DESTINATION_STEP;
    public static final Gatekeeper STATIONARY_GUARDIAN_V2 = new Gatekeeper("STATIONARY_GUARDIAN_V2", 83, "oculus_systemux:oculus_stationary_guardian_v2");
    public static final Gatekeeper SYSTEM_THEME_SETTING;
    public static final String TAG = "Gatekeeper";
    public static final Gatekeeper TRACKED_KEYBOARD_SECTION = new Gatekeeper("TRACKED_KEYBOARD_SECTION", 41, "oculus_systemux:oculus_vr_keyboard_enabled");
    public static final Gatekeeper TRUSTED_USER = new Gatekeeper("TRUSTED_USER", 85, "oculus_systemux:oculus_is_trusted_user");
    public static final Gatekeeper VIEW_FULL_PROFILE_ENABLED;
    public String mConfigName;
    public final String mConfigParamName;
    public final GKFlag mGKFlag;
    public String mParamName;

    static {
        GKFlag gKFlag = GKFlag.USE_DEVICE_CONFIG_ONLY;
        AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG = new Gatekeeper("AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG", 11, "oculus_casting:aui_enable_local_cast_network_check_dialog", gKFlag);
        AUI_PROFILE_MESSENGER_ACTIONS = new Gatekeeper("AUI_PROFILE_MESSENGER_ACTIONS", 16, "oculus_sysux_social:aui_profile_messenger_actions", gKFlag);
        AUI_V2_MESSENGER = new Gatekeeper("AUI_V2_MESSENGER", 21, "oculus_sysux_social:oculus_vrshell_anytimeui_v2_messenger", gKFlag);
        CAST_WWW_ENABLE_MICROPHONE = new Gatekeeper("CAST_WWW_ENABLE_MICROPHONE", 26, "oculus_casting:cast_www_enable_microphone", gKFlag);
        IS_TRUSTED_USER = new Gatekeeper("IS_TRUSTED_USER", 39, "oculus_mobile_trusted_user:is_trusted_user", gKFlag);
        LIBRARY_PROTOTYPE_ORGANIZATION_ID = new Gatekeeper("LIBRARY_PROTOTYPE_ORGANIZATION_ID", 44, "oculus_mobile_library_protype_filter_organization:organization_id", gKFlag);
        NOTIFICATIONS_FEED_REFRESH_DELAY = new Gatekeeper("NOTIFICATIONS_FEED_REFRESH_DELAY", 51, "oculus_notifications:vr_feed_reload_delay", gKFlag);
        PHONE_NOTIFICATIONS_FEED_TAB_ENABLED = new Gatekeeper("PHONE_NOTIFICATIONS_FEED_TAB_ENABLED", 60, "oculus_systemux_phone_notifications:feed_tab_enabled", gKFlag);
        SETTINGS_ENVIRONMENT_TYPE_SELECTOR = new Gatekeeper("SETTINGS_ENVIRONMENT_TYPE_SELECTOR", 72, "oculus_systemux:oculus_mobile_settings_use_test_environments", gKFlag);
        SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY = new Gatekeeper("SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY", 77, "oculus_social_platform_deviceconfig:oculus_integrity_remove_recording_overlay", gKFlag);
        SOCIAL_DESTINATION_INVITE_ENABLED = new Gatekeeper("SOCIAL_DESTINATION_INVITE_ENABLED", 78, "oculus_social_platform_deviceconfig:oculus_mobile_pause_invite_enabled", gKFlag);
        SOCIAL_NEW_PARTIES_PANEL_APP = new Gatekeeper("SOCIAL_NEW_PARTIES_PANEL_APP", 79, "oculus_social_platform_deviceconfig:oculus_party_new_parties_panel_app", gKFlag);
        SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI = new Gatekeeper("SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI", 80, "oculus_social_platform_deviceconfig:oculus_party_invite_from_destination_ui", gKFlag);
        SOCIAL_SKIP_DESTINATION_STEP = new Gatekeeper("SOCIAL_SKIP_DESTINATION_STEP", 81, "oculus_social_platform_deviceconfig:oculus_gtvr_skip_destination_step", gKFlag);
        OCULUS_AUI_ROSTER_ICONS = new Gatekeeper("OCULUS_AUI_ROSTER_ICONS", 82, "oculus_social_platform_deviceconfig:oculus_vrshell_anytimeui_inviteroster_icons", gKFlag);
        SYSTEM_THEME_SETTING = new Gatekeeper("SYSTEM_THEME_SETTING", 84, "oculus_systemux:oculus_ocui_system_theme_setting", gKFlag);
        Gatekeeper gatekeeper = new Gatekeeper("VIEW_FULL_PROFILE_ENABLED", 86, "oculus_systemux:oculus_show_far_field_profile");
        VIEW_FULL_PROFILE_ENABLED = gatekeeper;
        Gatekeeper[] gatekeeperArr = new Gatekeeper[87];
        System.arraycopy(new Gatekeeper[]{ACTIVE_CALL_BAR_MICROPHONE_CONTROLS, ACTIVE_CALL_BAR_VOLUME_CONTROLS, AIRLINK_ENABLED, ASSISTANT_DOUBLE_TAP_SETTING, ASSISTANT_IN_APP_VOICE_COMMANDS, ASSISTANT_NATIVE_VOICE_ACTIVITY, ASSISTANT_TTS_SELECTION, ASSISTANT_VIEW_COMMANDS_ON_OCULUS, ASSISTANT_WAKEWORD, AUI_ASSISTANT_QUICK_ACTIONS_COLOR, AUI_ASSISTANT, AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG, AUI_HOME_INVITE_BUTTON, AUI_OC_PROFILE_PARTY_BUTTON, AUI_ONBOARDING_TUTORIAL, AUI_PARTY_FB_UPSELL, AUI_PROFILE_MESSENGER_ACTIONS, AUI_NEW_BAR_VIEW, AUI_V2_EVENTS_PANEL_ENTRY, AUI_V2_FB_PROFILE_PARTY_BUTTON, AUI_V2_FB_PROFILE_PRESENCE, AUI_V2_MESSENGER, AUI_V2_MESSENGER_ICON, AUI_V2_QP_KILLSWITCH, AUI_V2_SHOW_PYMK, BLUETOOTH_MOUSE_SECTION, CAST_WWW_ENABLE_MICROPHONE}, 0, gatekeeperArr, 0, 27);
        System.arraycopy(new Gatekeeper[]{CONFIGURABLE_MTP_DIALOG, COUCH_CREATION, DEVICECONFIG_ENABLED, DESK_CREATION, DUC_LIBRARY_UI_NOTICE, EXPERIMENTAL_120HZ_REFRESH_RATE, FILE_MANAGER, FITNESS_TRACKER, FONT_SCALING_GK, FORCE_OVERLAYS, HAND_TRACKING_FREQUENCY, INTRUSION_DETECTION, IS_TRUSTED_USER, KEYBOARD_SETTINGS_SECTION, TRACKED_KEYBOARD_SECTION, KEYBOARD_FEDERATED_LEARNING, LIBRARY_APPS_DISK_CACHE, LIBRARY_PROTOTYPE_ORGANIZATION_ID, LIBRARY_STATE_AUTO_RESET, MULTIAPP_PUBLIC, MULTIAPP_ROLLOUT, NEW_SHARESHEET_AND_MAX_VIEW, NIGHT_DISPLAY_SECTION_SLIDER, NOTIFICATIONS_FEED_REFRESH, NOTIFICATIONS_FEED_REFRESH_DELAY, NOTIFICATIONS_LAUNCH_DESTINATIONS, OCULUS_CHATS}, 0, gatekeeperArr, 27, 27);
        System.arraycopy(new Gatekeeper[]{OCULUS_DEVELOPER, OCULUS_TRAVEL_TOGETHER_ASYNC_FLOW_ONLY, OCULUS_PHONE_NOTIFICATIONS, OVERLAY_BROWSER_EXPERIMENT, PARTIES_PER_PERSON_MUTE, PASSTHROUGH_PORTAL, PHONE_NOTIFICATIONS_FEED_TAB_ENABLED, POINTER_POSE_FILTER_TUNING, PROFILE_SHARING, QUEST_DOGFOODING_UPDATE, QUEST_INFINITE_OFFICE_PLATFORM, QUICKBOOT, QUICK_MESSAGE_DIALOG, QUICK_SETTINGS_TABLET, REALITY_TUNER, SHARE_PARTY_BUTTON, SOCIAL_PLATFORM_CONTENT_PROVIDER, SETTINGS_ASSISTANT_JSON_UNBLOCK, SETTINGS_ENVIRONMENT_TYPE_SELECTOR, SETTINGS_INPUT_HAPTICS_AMPLITUDE, SETTINGS_NEW_NOTIFICATIONS_SOUND, SETTINGS_NOTIFS_USER_CONTROL, SETTINGS_SWAP_SYSTEM_BTN_HANDEDNESS_GK, SOCIAL_ABUSE_REPORT_REMOVE_OVERLAY, SOCIAL_DESTINATION_INVITE_ENABLED, SOCIAL_NEW_PARTIES_PANEL_APP, SOCIAL_PARTY_INVITE_FROM_DESTINATION_UI}, 0, gatekeeperArr, 54, 27);
        System.arraycopy(new Gatekeeper[]{SOCIAL_SKIP_DESTINATION_STEP, OCULUS_AUI_ROSTER_ICONS, STATIONARY_GUARDIAN_V2, SYSTEM_THEME_SETTING, TRUSTED_USER, gatekeeper}, 0, gatekeeperArr, 81, 6);
        $VALUES = gatekeeperArr;
    }

    public static ArrayList<String> getGatekeepers() {
        ArrayList<String> arrayList = new ArrayList<>();
        Gatekeeper[] values = values();
        for (Gatekeeper gatekeeper : values) {
            if (gatekeeper.mGKFlag != GKFlag.USE_DEVICE_CONFIG_ONLY) {
                arrayList.add(AnonymousClass006.A07(gatekeeper.toString(), " 0"));
            }
        }
        return arrayList;
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

    public static Gatekeeper valueOf(String str) {
        return (Gatekeeper) Enum.valueOf(Gatekeeper.class, str);
    }

    public static Gatekeeper[] values() {
        return (Gatekeeper[]) $VALUES.clone();
    }

    public boolean isSharedConfigParam() {
        return "oculus_shared_systemshell".equals(this.mConfigName);
    }

    public boolean shouldUseDeviceConfig() {
        if (this.mGKFlag == GKFlag.USE_DEVICE_CONFIG_ONLY) {
            return true;
        }
        return false;
    }

    public String toDeviceConfigString() {
        return this.mConfigParamName;
    }

    public String toString() {
        return this.mParamName;
    }

    public Gatekeeper(String str, int i, String str2) {
        this.mConfigParamName = str2;
        this.mGKFlag = GKFlag.USE_SYSTEM_SHELL_GK;
        splitConfigParamName();
    }

    public Gatekeeper(String str, int i, String str2, GKFlag gKFlag) {
        this.mConfigParamName = str2;
        this.mGKFlag = gKFlag;
        splitConfigParamName();
    }
}
