package X;

import android.net.Uri;
import android.text.TextUtils;

public enum ZG {
    ASSISTANT("systemux://assistant"),
    AUI_INTERNAL_SETTINGS("systemux://aui-internal-settings"),
    AUI_PROFILE("systemux://aui-profile"),
    AUI_SOCIAL("systemux://aui-social"),
    AUI_CHATS("systemux://aui-chats"),
    AUI_MESSENGER("systemux://aui-messenger"),
    AUI_TABLET_NONE("systemux://aui-tablet-none"),
    AVATAR_EDITOR("systemux://avatareditor"),
    BLOCKANDREPORT("systemux://blockandreport"),
    BLUETOOTH("systemux://bluetooth"),
    BUG_REPORT("systemux://bug_report"),
    CASTING_TWILIGHT_UPSELL("systemux://dialog/casting-twilight-upsell-send-email"),
    CAMERA_ROLL("systemux://cameraroll"),
    CAPTIVE_WIFI_PORTAL("systemux://captive-wifi-portal"),
    DATE_TIME_SETTINGS("systemux://datetime"),
    DEFAULT_BROWSER("systemux://browser"),
    DEVICE_BATTERY("systemux://devicebattery"),
    ENTERPRISE_KEYPAD("systemux://enterprise_keypad"),
    ENTERPRISE_SETTINGS("systemux://enterprise_settings"),
    EVENTS("systemux://events"),
    FIRST_TIME_NUX("systemux://first-time-nux"),
    FITNESS_TRACKER("systemux://fitness-tracker"),
    FRIENDS("systemux://friends"),
    GALLERY("systemux://gallery"),
    GAMING_ACTIVITY("systemux://gaming-activity"),
    HAND_TRACKING_NUX("systemux://hand-tracking-nux"),
    HOME("systemux://home"),
    INSTALLER("systemux://installer"),
    INVITE_FRIENDS("systemux://invite_friends"),
    INVITE_TO_APP("systemux://invite_to_app"),
    LAUNCH_IAP("systemux://launch_iap"),
    LIBRARY("systemux://library"),
    LIVESTREAMING("systemux://livestreaming"),
    LOCKPATTERN("systemux://unlockpattern"),
    MEDIA_PREVIEW("systemux://media_preview"),
    MESSAGES("systemux://messages"),
    NOTIFICATIONS("systemux://notifications"),
    NUX("systemux://newuserexperience"),
    PARTIES("systemux://parties"),
    PAUSE("systemux://pause"),
    PRESENCE_INVITE("systemux://presence_invite"),
    PROFILE("systemux://profile"),
    REMOTE_INSTALL_TWILIGHT_UPSELL_SEND_EMAIL("systemux://dialog/remote-install-twilight-upsell-send-email"),
    REMOTE_SYSTEM_ACTION_REDIRECT("systemux://remote_system_action_redirect"),
    SEARCH("systemux://search"),
    SETTINGS("systemux://settings"),
    SHARE_MEDIA("systemux://share_media"),
    SHARE_SHEET("systemux://share_sheet"),
    SHARE_SHEET_V2("systemux://share_sheet_v2"),
    SHARING("systemux://sharing"),
    SOCIAL("systemux://social"),
    SOCIAL_ADD_FRIENDS("systemux://social_add_friends"),
    SOCIAL_REQUESTS("systemux://social_requests"),
    STORAGE_MANAGER("systemux://storage_manager"),
    STORE("systemux://store"),
    TV("systemux://tv"),
    TWILIGHT_DOWNLOAD_EMAIL_FAILED_TO_SEND("systemux://dialog/twilight-download-email-failed-to-send"),
    TWILIGHT_DOWNLOAD_EMAIL_SENDING("systemux://dialog/twilight-download-email-sending"),
    TWILIGHT_DOWNLOAD_EMAIL_SENT("systemux://dialog/twilight-download-email-sent"),
    UNINSTALLER("systemux://uninstaller"),
    USER_ADMIN("systemux://user_admin"),
    USER_BLOCK("systemux://user_block"),
    USER_FRIEND_REQUEST("systemux://user_friend_request"),
    USER_PROFILE("systemux://user_profile"),
    USER_REPORT("systemux://user_report"),
    USER_UNBLOCK("systemux://user_unblock"),
    WIFI("systemux://wifi"),
    NONE("systemux://dialog/none"),
    APP_DOWNLOAD_FAILURE_LOW_STORAGE("systemux://dialog/app-download-failure-low-storage"),
    APP_MODE_INCOMPATIBLE("systemux://dialog/app_mode_incompat"),
    APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED("systemux://dialog/app_launch_blocked_controller_required"),
    APP_LAUNCH_BLOCKED_HANDS_REQUIRED("systemux://dialog/app_launch_blocked_hands_required"),
    CONTROLLER_PAIRING("systemux://dialog/controller-pairing"),
    ENTERPRISE_CERTIFICATE_EXPIRED("systemux://dialog/enterprise-certificate-expired"),
    ENTERPRISE_CERTIFICATE_EXPIRING_WARNING("systemux://dialog/enterprise-certificate-expiring-warning"),
    ENTERPRISE_GUARDIAN_DISABLED_WARNING("systemux://dialog/enterprise-guardian-disabled-warning"),
    ENTERPRISE_KIOSK_NOT_INSTALLED("systemux://dialog/enterprise-kiosk-not-installed"),
    EXIT_TO_HOME("systemux://dialog/exit"),
    GUARDIAN_DIALOG("systemux://guardian/setup"),
    GUARDIAN_DIALOG_ROOMSCALE_URI("systemux://guardian/roomscale-setup"),
    GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI("systemux://guardian/switch-roomscale"),
    GUARDIAN_DIALOG_STATIONARY_URI("systemux://guardian/stationary-setup"),
    GUARDIAN_ADJUST_DIALOG("systemux://dialog/guardian-adjust"),
    GUARDIAN_ADJUST_SETUP("systemux://guardian/adjust-setup"),
    GUARDIAN_LEFT_HAND_DIALOG("systemux://dialog/guardian_left_hand"),
    GUARDIAN_RIGHT_HAND_DIALOG("systemux://dialog/guardian_right_hand"),
    GUARDIAN_PASSTHROUGH_ON_DEMAND("systemux://guardian/passthrough-on-demand"),
    GUARDIAN_ADJUST_FLOOR("systemux://guardian/guardian-adjust-floor"),
    IPD_ADJUST("systemux://dialog/ipd-adjust"),
    KEYBOARD("systemux://keyboard"),
    LAUNCH_CHECK_APP_DEGRADED("systemux://dialog/launch-check-app-degraded"),
    LAUNCH_CHECK_APP_DISABLED("systemux://dialog/launch-check-app-disabled"),
    LAUNCH_CHECK_APP_UPDATE("systemux://dialog/launch-check-app-update"),
    LAUNCH_CHECK_CLOUD_STORAGE("systemux://dialog/launch-check-cloud-storage"),
    LAUNCH_CHECK_REQUIRES_6DOF("systemux://dialog/launch-check-requires-6dof"),
    LAUNCH_CHECK_REQUIRES_AVATAR("systemux://dialog/launch-check-requires-avatar"),
    LAUNCH_CHECK_REQUIRES_CONTROLLERS("systemux://dialog/launch-check-requires-controllers"),
    LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE("systemux://dialog/launch-check-requires-exclusive-microphone"),
    LAUNCH_CHECK_REQUIRES_HANDS("systemux://dialog/launch-check-requires-hands"),
    LOCAL_STREAM_PRIVACY_CHECK_DIALOG("systemux://dialog/local-stream-privacy-check"),
    LOCAL_STREAM_START_FROM_DEVICE_DIALOG("systemux://dialog/local-stream-start-from-device"),
    LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG("systemux://dialog/local-stream-stop-from-device"),
    LOGIN_REQUIRED("systemux://dialog/login-required"),
    OCULUS_LINK_DISCONNECTED("systemux://dialog/oculus_link_disconnected"),
    OTA_BLOCKING("systemux://dialog/ota-blocking"),
    OTA_BLOCKING2("systemux://dialog/ota-blocking2"),
    OTA_BLOCKING_PREPARE_DEVICE("systemux://dialog/ota-blocking-prepare-device"),
    PANEL_REORIENT("systemux://dialog/panel-reorient"),
    PT_ONDEMAND_NUX_DIALOG("systemux://dialog/pt-ondemand-nux"),
    QUEST_VIEW_RECENTER("systemux://dialog/quest-view-recenter"),
    QUEST_SHOW_TASKBAR("systemux://dialog/quest-show-taskbar"),
    QUIT_AND_LAUNCH("systemux://dialog/quit-and-launch"),
    SOCIAL_JOIN_PARTY("systemux://dialog/social-join-party"),
    SOCIAL_CREATE_PARTY_PREVIEW("systemux://dialog/create-party-preview"),
    SOCIAL_PARTY_DIALOG("systemux://dialog/social-party"),
    SOCIAL_PROFILE_DIALOG("systemux://dialog/social-profile"),
    SOCIAL_RECEIVE_INVITE_DIALOG("systemux://dialog/social-receive-invite"),
    SOCIAL_SEND_INVITE_DIALOG("systemux://dialog/social-send-invite"),
    SYSTEM_FAILURE_MESSAGE("systemux://dialog/system-failure"),
    TEST_DIALOG("systemux://dialog/test-dialog"),
    TRACKING_MODE_3DOF_INCOMPAT_DIALOG("systemux://dialog/tracking-mode-3dof-incompat"),
    TRACKING_OFF_DIALOG("systemux://dialog/tracking-off"),
    UNOFFICIAL_APP_INSTALLED_DIALOG("systemux://dialog/unofficial-app-installed"),
    UNOFFICIAL_APP_LAUNCHED_DIALOG("systemux://dialog/unofficial-app-launched");
    
    public static final String PREFIX = "systemux://";
    public final String mPath;

    /* access modifiers changed from: public */
    ZG(String str) {
        if (!str.startsWith(PREFIX)) {
            C0139Dd.A0O("SystemUXRoute", "Incorrect prefix for path: %s", str);
        }
        this.mPath = str;
    }

    public static ZG fromPath(String str) {
        ZG[] values = values();
        for (ZG zg : values) {
            if (zg.path().equals(str)) {
                return zg;
            }
        }
        return null;
    }

    public String path() {
        return this.mPath;
    }

    public String toString() {
        return AnonymousClass08.A06(name(), "(", path(), ")");
    }

    public boolean isSamePath(Uri uri) {
        Uri A00 = C0209Jx.A00(this.mPath);
        return TextUtils.equals(A00.getScheme(), uri.getScheme()) && TextUtils.equals(A00.getAuthority(), uri.getAuthority()) && TextUtils.equals(A00.getPath(), uri.getPath());
    }

    public boolean isSamePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isSamePath(C0209Jx.A00(str));
    }
}
