package com.oculus.common.vrshell;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.C02980kg;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class SystemUXRoute extends Enum<SystemUXRoute> {
    public static final /* synthetic */ SystemUXRoute[] $VALUES;
    public static final SystemUXRoute APP_DOWNLOAD_FAILURE_LOW_STORAGE = new SystemUXRoute("APP_DOWNLOAD_FAILURE_LOW_STORAGE", 68, "systemux://dialog/app-download-failure-low-storage");
    public static final SystemUXRoute APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED = new SystemUXRoute("APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED", 70, "systemux://dialog/app_launch_blocked_controller_required");
    public static final SystemUXRoute APP_LAUNCH_BLOCKED_HANDS_REQUIRED = new SystemUXRoute("APP_LAUNCH_BLOCKED_HANDS_REQUIRED", 71, "systemux://dialog/app_launch_blocked_hands_required");
    public static final SystemUXRoute APP_MODE_INCOMPATIBLE = new SystemUXRoute("APP_MODE_INCOMPATIBLE", 69, "systemux://dialog/app_mode_incompat");
    public static final SystemUXRoute ASSISTANT = new SystemUXRoute("ASSISTANT", 0, "systemux://assistant");
    public static final SystemUXRoute AUI_CHATS = new SystemUXRoute("AUI_CHATS", 4, "systemux://aui-chats");
    public static final SystemUXRoute AUI_INTERNAL_SETTINGS = new SystemUXRoute("AUI_INTERNAL_SETTINGS", 1, "systemux://aui-internal-settings");
    public static final SystemUXRoute AUI_MESSENGER = new SystemUXRoute("AUI_MESSENGER", 5, "systemux://aui-messenger");
    public static final SystemUXRoute AUI_PROFILE = new SystemUXRoute("AUI_PROFILE", 2, "systemux://aui-profile");
    public static final SystemUXRoute AUI_SOCIAL = new SystemUXRoute("AUI_SOCIAL", 3, "systemux://aui-social");
    public static final SystemUXRoute AUI_TABLET_NONE = new SystemUXRoute("AUI_TABLET_NONE", 6, "systemux://aui-tablet-none");
    public static final SystemUXRoute AVATAR_EDITOR = new SystemUXRoute("AVATAR_EDITOR", 7, "systemux://avatareditor");
    public static final SystemUXRoute BLOCKANDREPORT = new SystemUXRoute("BLOCKANDREPORT", 8, "systemux://blockandreport");
    public static final SystemUXRoute BLUETOOTH = new SystemUXRoute("BLUETOOTH", 9, "systemux://bluetooth");
    public static final SystemUXRoute BUG_REPORT = new SystemUXRoute("BUG_REPORT", 10, "systemux://bug_report");
    public static final SystemUXRoute CAMERA_ROLL = new SystemUXRoute("CAMERA_ROLL", 12, "systemux://cameraroll");
    public static final SystemUXRoute CAPTIVE_WIFI_PORTAL = new SystemUXRoute("CAPTIVE_WIFI_PORTAL", 13, "systemux://captive-wifi-portal");
    public static final SystemUXRoute CASTING_TWILIGHT_UPSELL = new SystemUXRoute("CASTING_TWILIGHT_UPSELL", 11, "systemux://dialog/casting-twilight-upsell-send-email");
    public static final SystemUXRoute CONTROLLER_PAIRING = new SystemUXRoute("CONTROLLER_PAIRING", 72, "systemux://dialog/controller-pairing");
    public static final SystemUXRoute DATE_TIME_SETTINGS = new SystemUXRoute("DATE_TIME_SETTINGS", 14, "systemux://datetime");
    public static final SystemUXRoute DEFAULT_BROWSER = new SystemUXRoute("DEFAULT_BROWSER", 15, "systemux://browser");
    public static final SystemUXRoute DEVICE_BATTERY = new SystemUXRoute("DEVICE_BATTERY", 16, "systemux://devicebattery");
    public static final SystemUXRoute ENTERPRISE_CERTIFICATE_EXPIRED = new SystemUXRoute("ENTERPRISE_CERTIFICATE_EXPIRED", 73, "systemux://dialog/enterprise-certificate-expired");
    public static final SystemUXRoute ENTERPRISE_CERTIFICATE_EXPIRING_WARNING = new SystemUXRoute("ENTERPRISE_CERTIFICATE_EXPIRING_WARNING", 74, "systemux://dialog/enterprise-certificate-expiring-warning");
    public static final SystemUXRoute ENTERPRISE_GUARDIAN_DISABLED_WARNING = new SystemUXRoute("ENTERPRISE_GUARDIAN_DISABLED_WARNING", 75, "systemux://dialog/enterprise-guardian-disabled-warning");
    public static final SystemUXRoute ENTERPRISE_KEYPAD = new SystemUXRoute("ENTERPRISE_KEYPAD", 17, "systemux://enterprise_keypad");
    public static final SystemUXRoute ENTERPRISE_KIOSK_NOT_INSTALLED = new SystemUXRoute("ENTERPRISE_KIOSK_NOT_INSTALLED", 76, "systemux://dialog/enterprise-kiosk-not-installed");
    public static final SystemUXRoute ENTERPRISE_SETTINGS = new SystemUXRoute("ENTERPRISE_SETTINGS", 18, "systemux://enterprise_settings");
    public static final SystemUXRoute EVENTS = new SystemUXRoute("EVENTS", 19, "systemux://events");
    public static final SystemUXRoute EXIT_TO_HOME = new SystemUXRoute("EXIT_TO_HOME", 77, "systemux://dialog/exit");
    public static final SystemUXRoute FIRST_TIME_NUX = new SystemUXRoute("FIRST_TIME_NUX", 20, "systemux://first-time-nux");
    public static final SystemUXRoute FITNESS_TRACKER = new SystemUXRoute("FITNESS_TRACKER", 21, "systemux://fitness-tracker");
    public static final SystemUXRoute FRIENDS = new SystemUXRoute("FRIENDS", 22, "systemux://friends");
    public static final SystemUXRoute GALLERY = new SystemUXRoute("GALLERY", 23, "systemux://gallery");
    public static final SystemUXRoute GAMING_ACTIVITY = new SystemUXRoute("GAMING_ACTIVITY", 24, "systemux://gaming-activity");
    public static final SystemUXRoute GUARDIAN_ADJUST_DIALOG = new SystemUXRoute("GUARDIAN_ADJUST_DIALOG", 82, "systemux://dialog/guardian-adjust");
    public static final SystemUXRoute GUARDIAN_ADJUST_FLOOR = new SystemUXRoute("GUARDIAN_ADJUST_FLOOR", 87, "systemux://guardian/guardian-adjust-floor");
    public static final SystemUXRoute GUARDIAN_ADJUST_SETUP = new SystemUXRoute("GUARDIAN_ADJUST_SETUP", 83, "systemux://guardian/adjust-setup");
    public static final SystemUXRoute GUARDIAN_DIALOG = new SystemUXRoute("GUARDIAN_DIALOG", 78, "systemux://guardian/setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_ROOMSCALE_URI = new SystemUXRoute("GUARDIAN_DIALOG_ROOMSCALE_URI", 79, "systemux://guardian/roomscale-setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_STATIONARY_URI = new SystemUXRoute("GUARDIAN_DIALOG_STATIONARY_URI", 81, "systemux://guardian/stationary-setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI = new SystemUXRoute("GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI", 80, "systemux://guardian/switch-roomscale");
    public static final SystemUXRoute GUARDIAN_LEFT_HAND_DIALOG = new SystemUXRoute("GUARDIAN_LEFT_HAND_DIALOG", 84, "systemux://dialog/guardian_left_hand");
    public static final SystemUXRoute GUARDIAN_PASSTHROUGH_ON_DEMAND = new SystemUXRoute("GUARDIAN_PASSTHROUGH_ON_DEMAND", 86, "systemux://guardian/passthrough-on-demand");
    public static final SystemUXRoute GUARDIAN_RIGHT_HAND_DIALOG = new SystemUXRoute("GUARDIAN_RIGHT_HAND_DIALOG", 85, "systemux://dialog/guardian_right_hand");
    public static final SystemUXRoute HAND_TRACKING_NUX = new SystemUXRoute("HAND_TRACKING_NUX", 25, "systemux://hand-tracking-nux");
    public static final SystemUXRoute HOME = new SystemUXRoute("HOME", 26, "systemux://home");
    public static final SystemUXRoute INSTALLER = new SystemUXRoute("INSTALLER", 27, "systemux://installer");
    public static final SystemUXRoute INVITE_FRIENDS = new SystemUXRoute("INVITE_FRIENDS", 28, "systemux://invite_friends");
    public static final SystemUXRoute INVITE_TO_APP = new SystemUXRoute("INVITE_TO_APP", 29, "systemux://invite_to_app");
    public static final SystemUXRoute IPD_ADJUST = new SystemUXRoute("IPD_ADJUST", 88, "systemux://dialog/ipd-adjust");
    public static final SystemUXRoute KEYBOARD = new SystemUXRoute("KEYBOARD", 89, "systemux://keyboard");
    public static final SystemUXRoute LAUNCH_CHECK_APP_DEGRADED = new SystemUXRoute("LAUNCH_CHECK_APP_DEGRADED", 90, "systemux://dialog/launch-check-app-degraded");
    public static final SystemUXRoute LAUNCH_CHECK_APP_DISABLED = new SystemUXRoute("LAUNCH_CHECK_APP_DISABLED", 91, "systemux://dialog/launch-check-app-disabled");
    public static final SystemUXRoute LAUNCH_CHECK_APP_UPDATE = new SystemUXRoute("LAUNCH_CHECK_APP_UPDATE", 92, "systemux://dialog/launch-check-app-update");
    public static final SystemUXRoute LAUNCH_CHECK_CLOUD_STORAGE = new SystemUXRoute("LAUNCH_CHECK_CLOUD_STORAGE", 93, "systemux://dialog/launch-check-cloud-storage");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_6DOF = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_6DOF", 94, "systemux://dialog/launch-check-requires-6dof");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_AVATAR = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_AVATAR", 95, "systemux://dialog/launch-check-requires-avatar");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_CONTROLLERS = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_CONTROLLERS", 96, "systemux://dialog/launch-check-requires-controllers");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE", 97, "systemux://dialog/launch-check-requires-exclusive-microphone");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_HANDS = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_HANDS", 98, "systemux://dialog/launch-check-requires-hands");
    public static final SystemUXRoute LAUNCH_IAP = new SystemUXRoute("LAUNCH_IAP", 30, "systemux://launch_iap");
    public static final SystemUXRoute LIBRARY = new SystemUXRoute("LIBRARY", 31, "systemux://library");
    public static final SystemUXRoute LIVESTREAMING = new SystemUXRoute("LIVESTREAMING", 32, "systemux://livestreaming");
    public static final SystemUXRoute LOCAL_STREAM_PRIVACY_CHECK_DIALOG = new SystemUXRoute("LOCAL_STREAM_PRIVACY_CHECK_DIALOG", 99, "systemux://dialog/local-stream-privacy-check");
    public static final SystemUXRoute LOCAL_STREAM_START_FROM_DEVICE_DIALOG = new SystemUXRoute("LOCAL_STREAM_START_FROM_DEVICE_DIALOG", 100, "systemux://dialog/local-stream-start-from-device");
    public static final SystemUXRoute LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG = new SystemUXRoute("LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG", 101, "systemux://dialog/local-stream-stop-from-device");
    public static final SystemUXRoute LOCKPATTERN = new SystemUXRoute("LOCKPATTERN", 33, "systemux://unlockpattern");
    public static final SystemUXRoute LOGIN_REQUIRED = new SystemUXRoute("LOGIN_REQUIRED", 102, "systemux://dialog/login-required");
    public static final SystemUXRoute MEDIA_PREVIEW = new SystemUXRoute("MEDIA_PREVIEW", 34, "systemux://media_preview");
    public static final SystemUXRoute MESSAGES = new SystemUXRoute("MESSAGES", 35, "systemux://messages");
    public static final SystemUXRoute NONE = new SystemUXRoute("NONE", 67, "systemux://dialog/none");
    public static final SystemUXRoute NOTIFICATIONS = new SystemUXRoute("NOTIFICATIONS", 36, "systemux://notifications");
    public static final SystemUXRoute NUX = new SystemUXRoute("NUX", 37, "systemux://newuserexperience");
    public static final SystemUXRoute OCULUS_LINK_DISCONNECTED = new SystemUXRoute("OCULUS_LINK_DISCONNECTED", 103, "systemux://dialog/oculus_link_disconnected");
    public static final SystemUXRoute OTA_BLOCKING = new SystemUXRoute("OTA_BLOCKING", 104, "systemux://dialog/ota-blocking");
    public static final SystemUXRoute OTA_BLOCKING2 = new SystemUXRoute("OTA_BLOCKING2", 105, "systemux://dialog/ota-blocking2");
    public static final SystemUXRoute OTA_BLOCKING_PREPARE_DEVICE = new SystemUXRoute("OTA_BLOCKING_PREPARE_DEVICE", 106, "systemux://dialog/ota-blocking-prepare-device");
    public static final SystemUXRoute PANEL_REORIENT = new SystemUXRoute("PANEL_REORIENT", 107, "systemux://dialog/panel-reorient");
    public static final SystemUXRoute PARTIES = new SystemUXRoute("PARTIES", 38, "systemux://parties");
    public static final SystemUXRoute PAUSE = new SystemUXRoute("PAUSE", 39, "systemux://pause");
    public static final String PREFIX = "systemux://";
    public static final SystemUXRoute PRESENCE_INVITE = new SystemUXRoute("PRESENCE_INVITE", 40, "systemux://presence_invite");
    public static final SystemUXRoute PROFILE = new SystemUXRoute("PROFILE", 41, "systemux://profile");
    public static final SystemUXRoute PT_ONDEMAND_NUX_DIALOG = new SystemUXRoute("PT_ONDEMAND_NUX_DIALOG", 108, "systemux://dialog/pt-ondemand-nux");
    public static final SystemUXRoute QUEST_SHOW_TASKBAR = new SystemUXRoute("QUEST_SHOW_TASKBAR", 110, "systemux://dialog/quest-show-taskbar");
    public static final SystemUXRoute QUEST_VIEW_RECENTER = new SystemUXRoute("QUEST_VIEW_RECENTER", 109, "systemux://dialog/quest-view-recenter");
    public static final SystemUXRoute QUIT_AND_LAUNCH = new SystemUXRoute("QUIT_AND_LAUNCH", 111, "systemux://dialog/quit-and-launch");
    public static final SystemUXRoute REMOTE_INSTALL_TWILIGHT_UPSELL_SEND_EMAIL = new SystemUXRoute("REMOTE_INSTALL_TWILIGHT_UPSELL_SEND_EMAIL", 42, "systemux://dialog/remote-install-twilight-upsell-send-email");
    public static final SystemUXRoute REMOTE_SYSTEM_ACTION_REDIRECT = new SystemUXRoute("REMOTE_SYSTEM_ACTION_REDIRECT", 43, "systemux://remote_system_action_redirect");
    public static final SystemUXRoute SEARCH = new SystemUXRoute("SEARCH", 44, "systemux://search");
    public static final SystemUXRoute SETTINGS = new SystemUXRoute("SETTINGS", 45, "systemux://settings");
    public static final SystemUXRoute SHARE_MEDIA = new SystemUXRoute("SHARE_MEDIA", 46, "systemux://share_media");
    public static final SystemUXRoute SHARE_SHEET = new SystemUXRoute("SHARE_SHEET", 47, "systemux://share_sheet");
    public static final SystemUXRoute SHARE_SHEET_V2 = new SystemUXRoute("SHARE_SHEET_V2", 48, "systemux://share_sheet_v2");
    public static final SystemUXRoute SHARING = new SystemUXRoute("SHARING", 49, "systemux://sharing");
    public static final SystemUXRoute SOCIAL = new SystemUXRoute("SOCIAL", 50, "systemux://social");
    public static final SystemUXRoute SOCIAL_ADD_FRIENDS = new SystemUXRoute("SOCIAL_ADD_FRIENDS", 51, "systemux://social_add_friends");
    public static final SystemUXRoute SOCIAL_CREATE_PARTY_PREVIEW = new SystemUXRoute("SOCIAL_CREATE_PARTY_PREVIEW", 113, "systemux://dialog/create-party-preview");
    public static final SystemUXRoute SOCIAL_JOIN_PARTY = new SystemUXRoute("SOCIAL_JOIN_PARTY", 112, "systemux://dialog/social-join-party");
    public static final SystemUXRoute SOCIAL_PARTY_DIALOG = new SystemUXRoute("SOCIAL_PARTY_DIALOG", 114, "systemux://dialog/social-party");
    public static final SystemUXRoute SOCIAL_PROFILE_DIALOG = new SystemUXRoute("SOCIAL_PROFILE_DIALOG", 115, "systemux://dialog/social-profile");
    public static final SystemUXRoute SOCIAL_RECEIVE_INVITE_DIALOG = new SystemUXRoute("SOCIAL_RECEIVE_INVITE_DIALOG", 116, "systemux://dialog/social-receive-invite");
    public static final SystemUXRoute SOCIAL_REQUESTS = new SystemUXRoute("SOCIAL_REQUESTS", 52, "systemux://social_requests");
    public static final SystemUXRoute SOCIAL_SEND_INVITE_DIALOG = new SystemUXRoute("SOCIAL_SEND_INVITE_DIALOG", 117, "systemux://dialog/social-send-invite");
    public static final SystemUXRoute STORAGE_MANAGER = new SystemUXRoute("STORAGE_MANAGER", 53, "systemux://storage_manager");
    public static final SystemUXRoute STORE = new SystemUXRoute("STORE", 54, "systemux://store");
    public static final SystemUXRoute SYSTEM_FAILURE_MESSAGE = new SystemUXRoute("SYSTEM_FAILURE_MESSAGE", 118, "systemux://dialog/system-failure");
    public static final SystemUXRoute TEST_DIALOG = new SystemUXRoute("TEST_DIALOG", 119, "systemux://dialog/test-dialog");
    public static final SystemUXRoute TRACKING_MODE_3DOF_INCOMPAT_DIALOG = new SystemUXRoute("TRACKING_MODE_3DOF_INCOMPAT_DIALOG", 120, "systemux://dialog/tracking-mode-3dof-incompat");
    public static final SystemUXRoute TRACKING_OFF_DIALOG = new SystemUXRoute("TRACKING_OFF_DIALOG", 121, "systemux://dialog/tracking-off");
    public static final SystemUXRoute TV = new SystemUXRoute("TV", 55, "systemux://tv");
    public static final SystemUXRoute TWILIGHT_DOWNLOAD_EMAIL_FAILED_TO_SEND = new SystemUXRoute("TWILIGHT_DOWNLOAD_EMAIL_FAILED_TO_SEND", 56, "systemux://dialog/twilight-download-email-failed-to-send");
    public static final SystemUXRoute TWILIGHT_DOWNLOAD_EMAIL_SENDING = new SystemUXRoute("TWILIGHT_DOWNLOAD_EMAIL_SENDING", 57, "systemux://dialog/twilight-download-email-sending");
    public static final SystemUXRoute TWILIGHT_DOWNLOAD_EMAIL_SENT = new SystemUXRoute("TWILIGHT_DOWNLOAD_EMAIL_SENT", 58, "systemux://dialog/twilight-download-email-sent");
    public static final SystemUXRoute UNINSTALLER = new SystemUXRoute("UNINSTALLER", 59, "systemux://uninstaller");
    public static final SystemUXRoute UNOFFICIAL_APP_INSTALLED_DIALOG = new SystemUXRoute("UNOFFICIAL_APP_INSTALLED_DIALOG", 122, "systemux://dialog/unofficial-app-installed");
    public static final SystemUXRoute UNOFFICIAL_APP_LAUNCHED_DIALOG;
    public static final SystemUXRoute USER_ADMIN = new SystemUXRoute("USER_ADMIN", 60, "systemux://user_admin");
    public static final SystemUXRoute USER_BLOCK = new SystemUXRoute("USER_BLOCK", 61, "systemux://user_block");
    public static final SystemUXRoute USER_FRIEND_REQUEST = new SystemUXRoute("USER_FRIEND_REQUEST", 62, "systemux://user_friend_request");
    public static final SystemUXRoute USER_PROFILE = new SystemUXRoute("USER_PROFILE", 63, "systemux://user_profile");
    public static final SystemUXRoute USER_REPORT = new SystemUXRoute("USER_REPORT", 64, "systemux://user_report");
    public static final SystemUXRoute USER_UNBLOCK = new SystemUXRoute("USER_UNBLOCK", 65, "systemux://user_unblock");
    public static final SystemUXRoute WIFI = new SystemUXRoute("WIFI", 66, "systemux://wifi");
    public final String mPath;

    static {
        SystemUXRoute systemUXRoute = new SystemUXRoute("UNOFFICIAL_APP_LAUNCHED_DIALOG", 123, "systemux://dialog/unofficial-app-launched");
        UNOFFICIAL_APP_LAUNCHED_DIALOG = systemUXRoute;
        SystemUXRoute[] systemUXRouteArr = new SystemUXRoute[124];
        System.arraycopy(new SystemUXRoute[]{ASSISTANT, AUI_INTERNAL_SETTINGS, AUI_PROFILE, AUI_SOCIAL, AUI_CHATS, AUI_MESSENGER, AUI_TABLET_NONE, AVATAR_EDITOR, BLOCKANDREPORT, BLUETOOTH, BUG_REPORT, CASTING_TWILIGHT_UPSELL, CAMERA_ROLL, CAPTIVE_WIFI_PORTAL, DATE_TIME_SETTINGS, DEFAULT_BROWSER, DEVICE_BATTERY, ENTERPRISE_KEYPAD, ENTERPRISE_SETTINGS, EVENTS, FIRST_TIME_NUX, FITNESS_TRACKER, FRIENDS, GALLERY, GAMING_ACTIVITY, HAND_TRACKING_NUX, HOME}, 0, systemUXRouteArr, 0, 27);
        System.arraycopy(new SystemUXRoute[]{INSTALLER, INVITE_FRIENDS, INVITE_TO_APP, LAUNCH_IAP, LIBRARY, LIVESTREAMING, LOCKPATTERN, MEDIA_PREVIEW, MESSAGES, NOTIFICATIONS, NUX, PARTIES, PAUSE, PRESENCE_INVITE, PROFILE, REMOTE_INSTALL_TWILIGHT_UPSELL_SEND_EMAIL, REMOTE_SYSTEM_ACTION_REDIRECT, SEARCH, SETTINGS, SHARE_MEDIA, SHARE_SHEET, SHARE_SHEET_V2, SHARING, SOCIAL, SOCIAL_ADD_FRIENDS, SOCIAL_REQUESTS, STORAGE_MANAGER}, 0, systemUXRouteArr, 27, 27);
        System.arraycopy(new SystemUXRoute[]{STORE, TV, TWILIGHT_DOWNLOAD_EMAIL_FAILED_TO_SEND, TWILIGHT_DOWNLOAD_EMAIL_SENDING, TWILIGHT_DOWNLOAD_EMAIL_SENT, UNINSTALLER, USER_ADMIN, USER_BLOCK, USER_FRIEND_REQUEST, USER_PROFILE, USER_REPORT, USER_UNBLOCK, WIFI, NONE, APP_DOWNLOAD_FAILURE_LOW_STORAGE, APP_MODE_INCOMPATIBLE, APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED, APP_LAUNCH_BLOCKED_HANDS_REQUIRED, CONTROLLER_PAIRING, ENTERPRISE_CERTIFICATE_EXPIRED, ENTERPRISE_CERTIFICATE_EXPIRING_WARNING, ENTERPRISE_GUARDIAN_DISABLED_WARNING, ENTERPRISE_KIOSK_NOT_INSTALLED, EXIT_TO_HOME, GUARDIAN_DIALOG, GUARDIAN_DIALOG_ROOMSCALE_URI, GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI}, 0, systemUXRouteArr, 54, 27);
        System.arraycopy(new SystemUXRoute[]{GUARDIAN_DIALOG_STATIONARY_URI, GUARDIAN_ADJUST_DIALOG, GUARDIAN_ADJUST_SETUP, GUARDIAN_LEFT_HAND_DIALOG, GUARDIAN_RIGHT_HAND_DIALOG, GUARDIAN_PASSTHROUGH_ON_DEMAND, GUARDIAN_ADJUST_FLOOR, IPD_ADJUST, KEYBOARD, LAUNCH_CHECK_APP_DEGRADED, LAUNCH_CHECK_APP_DISABLED, LAUNCH_CHECK_APP_UPDATE, LAUNCH_CHECK_CLOUD_STORAGE, LAUNCH_CHECK_REQUIRES_6DOF, LAUNCH_CHECK_REQUIRES_AVATAR, LAUNCH_CHECK_REQUIRES_CONTROLLERS, LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE, LAUNCH_CHECK_REQUIRES_HANDS, LOCAL_STREAM_PRIVACY_CHECK_DIALOG, LOCAL_STREAM_START_FROM_DEVICE_DIALOG, LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG, LOGIN_REQUIRED, OCULUS_LINK_DISCONNECTED, OTA_BLOCKING, OTA_BLOCKING2, OTA_BLOCKING_PREPARE_DEVICE, PANEL_REORIENT}, 0, systemUXRouteArr, 81, 27);
        System.arraycopy(new SystemUXRoute[]{PT_ONDEMAND_NUX_DIALOG, QUEST_VIEW_RECENTER, QUEST_SHOW_TASKBAR, QUIT_AND_LAUNCH, SOCIAL_JOIN_PARTY, SOCIAL_CREATE_PARTY_PREVIEW, SOCIAL_PARTY_DIALOG, SOCIAL_PROFILE_DIALOG, SOCIAL_RECEIVE_INVITE_DIALOG, SOCIAL_SEND_INVITE_DIALOG, SYSTEM_FAILURE_MESSAGE, TEST_DIALOG, TRACKING_MODE_3DOF_INCOMPAT_DIALOG, TRACKING_OFF_DIALOG, UNOFFICIAL_APP_INSTALLED_DIALOG, systemUXRoute}, 0, systemUXRouteArr, 108, 16);
        $VALUES = systemUXRouteArr;
    }

    public static SystemUXRoute valueOf(String str) {
        return (SystemUXRoute) Enum.valueOf(SystemUXRoute.class, str);
    }

    public static SystemUXRoute[] values() {
        return (SystemUXRoute[]) $VALUES.clone();
    }

    public SystemUXRoute(String str, int i, String str2) {
        if (!str2.startsWith("systemux://")) {
            AnonymousClass0MD.A09("SystemUXRoute", "Incorrect prefix for path: %s", str2);
        }
        this.mPath = str2;
    }

    @Nullable
    public static SystemUXRoute fromPath(String str) {
        SystemUXRoute[] values = values();
        for (SystemUXRoute systemUXRoute : values) {
            if (systemUXRoute.path().equals(str)) {
                return systemUXRoute;
            }
        }
        return null;
    }

    public String path() {
        return this.mPath;
    }

    public String toString() {
        return AnonymousClass006.A0B(name(), "(", path(), ")");
    }

    public boolean isSamePath(Uri uri) {
        Uri A00 = C02980kg.A00(this.mPath);
        return TextUtils.equals(A00.getScheme(), uri.getScheme()) && TextUtils.equals(A00.getAuthority(), uri.getAuthority()) && TextUtils.equals(A00.getPath(), uri.getPath());
    }

    public boolean isSamePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isSamePath(C02980kg.A00(str));
    }
}
