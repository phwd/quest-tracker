package com.oculus.vrshell;

import X.AnonymousClass006;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Objects;

/* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
public final class SystemUXRoute extends Enum<SystemUXRoute> {
    public static final /* synthetic */ SystemUXRoute[] $VALUES;
    public static final SystemUXRoute ALL_EVENTS = new SystemUXRoute("ALL_EVENTS", 0, "systemux://all_events");
    public static final SystemUXRoute APP_DOWNLOAD_FAILURE_LOW_STORAGE = new SystemUXRoute("APP_DOWNLOAD_FAILURE_LOW_STORAGE", 74, "systemux://dialog/app-download-failure-low-storage");
    public static final SystemUXRoute APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED = new SystemUXRoute("APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED", 76, "systemux://dialog/app_launch_blocked_controller_required");
    public static final SystemUXRoute APP_LAUNCH_BLOCKED_HANDS_REQUIRED = new SystemUXRoute("APP_LAUNCH_BLOCKED_HANDS_REQUIRED", 77, "systemux://dialog/app_launch_blocked_hands_required");
    public static final SystemUXRoute APP_MODE_INCOMPATIBLE = new SystemUXRoute("APP_MODE_INCOMPATIBLE", 75, "systemux://dialog/app_mode_incompat");
    public static final SystemUXRoute ASSISTANT = new SystemUXRoute("ASSISTANT", 1, "systemux://assistant");
    public static final SystemUXRoute AUI_CHATS = new SystemUXRoute("AUI_CHATS", 3, "systemux://aui-chats");
    public static final SystemUXRoute AUI_INTERNAL_SETTINGS = new SystemUXRoute("AUI_INTERNAL_SETTINGS", 2, "systemux://aui-internal-settings");
    public static final SystemUXRoute AUI_MESSENGER = new SystemUXRoute("AUI_MESSENGER", 4, "systemux://aui-messenger");
    public static final SystemUXRoute AUI_PARTIES = new SystemUXRoute("AUI_PARTIES", 5, "systemux://aui-parties");
    public static final SystemUXRoute AUI_PEOPLE = new SystemUXRoute("AUI_PEOPLE", 6, "systemux://aui-people");
    public static final SystemUXRoute AUI_PEOPLE_FB = new SystemUXRoute("AUI_PEOPLE_FB", 7, "systemux://aui-people-fb");
    public static final SystemUXRoute AUI_PROFILE = new SystemUXRoute("AUI_PROFILE", 8, "systemux://aui-profile");
    public static final SystemUXRoute AUI_SOCIAL = new SystemUXRoute("AUI_SOCIAL", 9, "systemux://aui-social");
    public static final SystemUXRoute AUI_SOCIAL_REAUTH = new SystemUXRoute("AUI_SOCIAL_REAUTH", 10, "systemux://aui-social-reauth");
    public static final SystemUXRoute AUI_SOCIAL_SETTINGS = new SystemUXRoute("AUI_SOCIAL_SETTINGS", 12, "systemux://aui-social-settings");
    public static final SystemUXRoute AUI_SOCIAL_V2 = new SystemUXRoute("AUI_SOCIAL_V2", 11, "systemux://aui-social-v2");
    public static final SystemUXRoute AUI_TABLET_NONE = new SystemUXRoute("AUI_TABLET_NONE", 13, "systemux://aui-tablet-none");
    public static final SystemUXRoute AVATAR_EDITOR = new SystemUXRoute("AVATAR_EDITOR", 14, "systemux://avatareditor");
    public static final SystemUXRoute BLOCKANDREPORT = new SystemUXRoute("BLOCKANDREPORT", 15, "systemux://blockandreport");
    public static final SystemUXRoute BLUETOOTH = new SystemUXRoute("BLUETOOTH", 16, "systemux://bluetooth");
    public static final SystemUXRoute BUG_REPORT = new SystemUXRoute("BUG_REPORT", 17, "systemux://bug_report");
    public static final SystemUXRoute CAMERA_ROLL = new SystemUXRoute("CAMERA_ROLL", 18, "systemux://cameraroll");
    public static final SystemUXRoute CAPTIVE_WIFI_PORTAL = new SystemUXRoute("CAPTIVE_WIFI_PORTAL", 19, "systemux://captive-wifi-portal");
    public static final SystemUXRoute CONTROLLER_PAIRING = new SystemUXRoute("CONTROLLER_PAIRING", 78, "systemux://dialog/controller-pairing");
    public static final SystemUXRoute DATE_TIME_SETTINGS = new SystemUXRoute("DATE_TIME_SETTINGS", 20, "systemux://datetime");
    public static final SystemUXRoute DEFAULT_BROWSER = new SystemUXRoute("DEFAULT_BROWSER", 21, "systemux://browser");
    public static final SystemUXRoute DEVICE_BATTERY = new SystemUXRoute("DEVICE_BATTERY", 22, "systemux://devicebattery");
    public static final SystemUXRoute ENTERPRISE_CERTIFICATE_EXPIRED = new SystemUXRoute("ENTERPRISE_CERTIFICATE_EXPIRED", 79, "systemux://dialog/enterprise-certificate-expired");
    public static final SystemUXRoute ENTERPRISE_CERTIFICATE_EXPIRING_WARNING = new SystemUXRoute("ENTERPRISE_CERTIFICATE_EXPIRING_WARNING", 80, "systemux://dialog/enterprise-certificate-expiring-warning");
    public static final SystemUXRoute ENTERPRISE_GUARDIAN_DISABLED_WARNING = new SystemUXRoute("ENTERPRISE_GUARDIAN_DISABLED_WARNING", 81, "systemux://dialog/enterprise-guardian-disabled-warning");
    public static final SystemUXRoute ENTERPRISE_KIOSK_NOT_INSTALLED = new SystemUXRoute("ENTERPRISE_KIOSK_NOT_INSTALLED", 82, "systemux://dialog/enterprise-kiosk-not-installed");
    public static final SystemUXRoute EVENTS = new SystemUXRoute("EVENTS", 23, "systemux://events");
    public static final SystemUXRoute FACEBOOK_BLOCK = new SystemUXRoute("FACEBOOK_BLOCK", 83, "systemux://dialog/facebook-block");
    public static final SystemUXRoute FBCONNECT = new SystemUXRoute("FBCONNECT", 24, "systemux://fb-connect");
    public static final SystemUXRoute FILE_MANAGER = new SystemUXRoute("FILE_MANAGER", 25, "systemux://file-manager");
    public static final SystemUXRoute FIRST_TIME_NUX = new SystemUXRoute("FIRST_TIME_NUX", 84, "systemux://first-time-nux");
    public static final SystemUXRoute FITNESS_TRACKER = new SystemUXRoute("FITNESS_TRACKER", 85, "systemux://fitness-tracker");
    public static final SystemUXRoute FRIENDS = new SystemUXRoute("FRIENDS", 26, "systemux://friends");
    public static final SystemUXRoute GAMING_ACTIVITY = new SystemUXRoute("GAMING_ACTIVITY", 27, "systemux://gaming-activity");
    public static final SystemUXRoute GROUP_LAUNCH_APP_SELECTOR = new SystemUXRoute("GROUP_LAUNCH_APP_SELECTOR", 28, "systemux://group_launch_app_selector");
    public static final SystemUXRoute GROUP_LAUNCH_DESTINATION_SELECTOR = new SystemUXRoute("GROUP_LAUNCH_DESTINATION_SELECTOR", 29, "systemux://group_launch_destination_selector");
    public static final SystemUXRoute GUARDIAN_ADJUST_DIALOG = new SystemUXRoute("GUARDIAN_ADJUST_DIALOG", 90, "systemux://dialog/guardian-adjust");
    public static final SystemUXRoute GUARDIAN_ADJUST_FLOOR = new SystemUXRoute("GUARDIAN_ADJUST_FLOOR", 91, "systemux://guardian/guardian-adjust-floor");
    public static final SystemUXRoute GUARDIAN_ADJUST_SETUP = new SystemUXRoute("GUARDIAN_ADJUST_SETUP", 92, "systemux://guardian/adjust-setup");
    public static final SystemUXRoute GUARDIAN_CLEAR_HISTORY = new SystemUXRoute("GUARDIAN_CLEAR_HISTORY", 93, "systemux://guardian/guardian-clear-history");
    public static final SystemUXRoute GUARDIAN_CLEAR_SURFACES = new SystemUXRoute("GUARDIAN_CLEAR_SURFACES", 94, "systemux://guardian/guardian-clear-surfaces");
    public static final SystemUXRoute GUARDIAN_CREATE_DESK = new SystemUXRoute("GUARDIAN_CREATE_DESK", 96, "systemux://guardian/guardian-create-desk");
    public static final SystemUXRoute GUARDIAN_CREATE_SURFACE = new SystemUXRoute("GUARDIAN_CREATE_SURFACE", 95, "systemux://guardian/guardian-create-surface");
    public static final SystemUXRoute GUARDIAN_DIALOG = new SystemUXRoute("GUARDIAN_DIALOG", 86, "systemux://guardian/setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_ROOMSCALE_URI = new SystemUXRoute("GUARDIAN_DIALOG_ROOMSCALE_URI", 87, "systemux://guardian/roomscale-setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_STATIONARY_URI = new SystemUXRoute("GUARDIAN_DIALOG_STATIONARY_URI", 89, "systemux://guardian/stationary-setup");
    public static final SystemUXRoute GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI = new SystemUXRoute("GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI", 88, "systemux://guardian/switch-roomscale");
    public static final SystemUXRoute HAND_TRACKING_NUX = new SystemUXRoute("HAND_TRACKING_NUX", 30, "systemux://hand-tracking-nux");
    public static final SystemUXRoute HEALTH_AND_SAFETY = new SystemUXRoute("HEALTH_AND_SAFETY", 31, "systemux://health_and_safety_video");
    public static final SystemUXRoute HOME = new SystemUXRoute("HOME", 32, "systemux://home");
    public static final SystemUXRoute INSTALLER = new SystemUXRoute("INSTALLER", 33, "systemux://installer");
    public static final SystemUXRoute INTRUSION_DETECTION_NUX = new SystemUXRoute("INTRUSION_DETECTION_NUX", 97, "systemux://dialog/intrusion-detection-nux");
    public static final SystemUXRoute INVITE_FRIENDS = new SystemUXRoute("INVITE_FRIENDS", 34, "systemux://invite_friends");
    public static final SystemUXRoute INVITE_TO_APP = new SystemUXRoute("INVITE_TO_APP", 35, "systemux://invite_to_app");
    public static final SystemUXRoute IPD_ADJUST = new SystemUXRoute("IPD_ADJUST", 98, "systemux://dialog/ipd-adjust");
    public static final SystemUXRoute JOIN_PARTY_DIALOG = new SystemUXRoute("JOIN_PARTY_DIALOG", 127, "systemux://social-join-party");
    public static final SystemUXRoute KEYBOARD = new SystemUXRoute("KEYBOARD", 99, "systemux://keyboard");
    public static final SystemUXRoute LAUNCH_CHECK_APP_DEGRADED = new SystemUXRoute("LAUNCH_CHECK_APP_DEGRADED", 100, "systemux://dialog/launch-check-app-degraded");
    public static final SystemUXRoute LAUNCH_CHECK_APP_DISABLED = new SystemUXRoute("LAUNCH_CHECK_APP_DISABLED", 101, "systemux://dialog/launch-check-app-disabled");
    public static final SystemUXRoute LAUNCH_CHECK_APP_UPDATE = new SystemUXRoute("LAUNCH_CHECK_APP_UPDATE", 102, "systemux://dialog/launch-check-app-update");
    public static final SystemUXRoute LAUNCH_CHECK_BLOCK_DESK = new SystemUXRoute("LAUNCH_CHECK_BLOCK_DESK", 103, "systemux://dialog/launch-check-block-desk");
    public static final SystemUXRoute LAUNCH_CHECK_CLOUD_STORAGE = new SystemUXRoute("LAUNCH_CHECK_CLOUD_STORAGE", 104, "systemux://dialog/launch-check-cloud-storage");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_6DOF = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_6DOF", 105, "systemux://dialog/launch-check-requires-6dof");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_AVATAR = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_AVATAR", 106, "systemux://dialog/launch-check-requires-avatar");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_CONTROLLERS = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_CONTROLLERS", 107, "systemux://dialog/launch-check-requires-controllers");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE", 108, "systemux://dialog/launch-check-requires-exclusive-microphone");
    public static final SystemUXRoute LAUNCH_CHECK_REQUIRES_HANDS = new SystemUXRoute("LAUNCH_CHECK_REQUIRES_HANDS", 109, "systemux://dialog/launch-check-requires-hands");
    public static final SystemUXRoute LAUNCH_IAP = new SystemUXRoute("LAUNCH_IAP", 36, "systemux://launch_iap");
    public static final SystemUXRoute LIBRARY = new SystemUXRoute("LIBRARY", 37, "systemux://library");
    public static final SystemUXRoute LIVESTREAMING = new SystemUXRoute("LIVESTREAMING", 38, "systemux://livestreaming");
    public static final SystemUXRoute LOCAL_STREAM_PRIVACY_CHECK_DIALOG = new SystemUXRoute("LOCAL_STREAM_PRIVACY_CHECK_DIALOG", 110, "systemux://dialog/local-stream-privacy-check");
    public static final SystemUXRoute LOCAL_STREAM_START_FROM_DEVICE_DIALOG = new SystemUXRoute("LOCAL_STREAM_START_FROM_DEVICE_DIALOG", 111, "systemux://dialog/local-stream-start-from-device");
    public static final SystemUXRoute LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG = new SystemUXRoute("LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG", 112, "systemux://dialog/local-stream-stop-from-device");
    public static final SystemUXRoute LOCAL_STREAM_TO_BROWSER_DIALOG = new SystemUXRoute("LOCAL_STREAM_TO_BROWSER_DIALOG", 113, "systemux://dialog/local-stream-to-browser-dialog");
    public static final SystemUXRoute LOCKPATTERN = new SystemUXRoute("LOCKPATTERN", 39, "systemux://unlockpattern");
    public static final SystemUXRoute LOGIN = new SystemUXRoute("LOGIN", 114, "systemux://login");
    public static final SystemUXRoute MEDIA_PREVIEW = new SystemUXRoute("MEDIA_PREVIEW", 40, "systemux://media_preview");
    public static final SystemUXRoute MESSAGES = new SystemUXRoute("MESSAGES", 41, "systemux://messages");
    public static final SystemUXRoute MESSENGER_INTEGRITY = new SystemUXRoute("MESSENGER_INTEGRITY", 115, "systemux://dialog/messenger-integrity");
    public static final SystemUXRoute NONE = new SystemUXRoute("NONE", 73, "systemux://dialog/none");
    public static final SystemUXRoute NOTIFICATIONS = new SystemUXRoute("NOTIFICATIONS", 42, "systemux://notifications");
    public static final SystemUXRoute OCULUS_LINK_AVAILABLE = new SystemUXRoute("OCULUS_LINK_AVAILABLE", 116, "systemux://dialog/oculus_link_available");
    public static final SystemUXRoute OCULUS_LINK_DISCONNECTED = new SystemUXRoute("OCULUS_LINK_DISCONNECTED", 117, "systemux://dialog/oculus_link_disconnected");
    public static final SystemUXRoute PANEL_REORIENT = new SystemUXRoute("PANEL_REORIENT", 118, "systemux://dialog/panel-reorient");
    public static final SystemUXRoute PARTIES = new SystemUXRoute("PARTIES", 43, "systemux://parties");
    public static final SystemUXRoute PASSTHROUGH_PORTAL = new SystemUXRoute("PASSTHROUGH_PORTAL", 119, "systemux://guardian/passthrough-portal");
    public static final SystemUXRoute PAUSE = new SystemUXRoute("PAUSE", 44, "systemux://pause");
    public static final SystemUXRoute PERMISSIONS = new SystemUXRoute("PERMISSIONS", 45, "systemux://permissions");
    public static final String PREFIX = "systemux://";
    public static final SystemUXRoute PRESENCE_INVITE = new SystemUXRoute("PRESENCE_INVITE", 46, "systemux://presence_invite");
    public static final SystemUXRoute PROFILE = new SystemUXRoute("PROFILE", 47, "systemux://profile");
    public static final SystemUXRoute PROFILE_PHOTO_EDITOR = new SystemUXRoute("PROFILE_PHOTO_EDITOR", 48, "systemux://profile_photo_editor");
    public static final SystemUXRoute PT_ONDEMAND_NUX_DIALOG = new SystemUXRoute("PT_ONDEMAND_NUX_DIALOG", 120, "systemux://dialog/pt-ondemand-nux");
    public static final SystemUXRoute QUICKBOOT_NUX = new SystemUXRoute("QUICKBOOT_NUX", 121, "systemux://dialog/quickboot-nux");
    public static final SystemUXRoute QUICK_MESSAGE = new SystemUXRoute("QUICK_MESSAGE", 49, "systemux://quick_message");
    public static final SystemUXRoute QUICK_SETTINGS = new SystemUXRoute("QUICK_SETTINGS", 50, "systemux://quick_settings");
    public static final SystemUXRoute QUIT_AND_LAUNCH = new SystemUXRoute("QUIT_AND_LAUNCH", 122, "systemux://dialog/quit-and-launch");
    public static final SystemUXRoute REMOTE_SYSTEM_ACTION_REDIRECT = new SystemUXRoute("REMOTE_SYSTEM_ACTION_REDIRECT", 51, "systemux://remote_system_action_redirect");
    public static final SystemUXRoute SEARCH = new SystemUXRoute("SEARCH", 52, "systemux://search");
    public static final SystemUXRoute SETTINGS = new SystemUXRoute("SETTINGS", 53, "systemux://settings");
    public static final SystemUXRoute SHARE_MEDIA = new SystemUXRoute("SHARE_MEDIA", 54, "systemux://share_media");
    public static final SystemUXRoute SHARE_SHEET = new SystemUXRoute("SHARE_SHEET", 55, "systemux://share_sheet");
    public static final SystemUXRoute SHARE_SHEET_V2 = new SystemUXRoute("SHARE_SHEET_V2", 56, "systemux://share_sheet_v2");
    public static final SystemUXRoute SHARING = new SystemUXRoute("SHARING", 57, "systemux://sharing");
    public static final SystemUXRoute SOCIAL = new SystemUXRoute("SOCIAL", 58, "systemux://social");
    public static final SystemUXRoute SOCIAL_ADD_FRIENDS = new SystemUXRoute("SOCIAL_ADD_FRIENDS", 59, "systemux://social_add_friends");
    public static final SystemUXRoute SOCIAL_CONFIRM_JOIN_PARTY = new SystemUXRoute("SOCIAL_CONFIRM_JOIN_PARTY", 124, "systemux://dialog/social-confirm-join-party");
    public static final SystemUXRoute SOCIAL_CREATE_PARTY_PREVIEW = new SystemUXRoute("SOCIAL_CREATE_PARTY_PREVIEW", 125, "systemux://dialog/create-party-preview");
    public static final SystemUXRoute SOCIAL_CREATE_VR_INVITE_DIALOG = new SystemUXRoute("SOCIAL_CREATE_VR_INVITE_DIALOG", 126, "systemux://dialog/create-vr-invite");
    public static final SystemUXRoute SOCIAL_JOIN_PARTY = new SystemUXRoute("SOCIAL_JOIN_PARTY", 123, "systemux://dialog/social-join-party");
    public static final SystemUXRoute SOCIAL_OVERLAY_PANEL = new SystemUXRoute("SOCIAL_OVERLAY_PANEL", 60, "systemux://social_overlay_panel");
    public static final SystemUXRoute SOCIAL_PARTY_PRIVACY = new SystemUXRoute("SOCIAL_PARTY_PRIVACY", 128, "systemux://dialog/social-party-privacy");
    public static final SystemUXRoute SOCIAL_RECEIVE_INVITE_DIALOG = new SystemUXRoute("SOCIAL_RECEIVE_INVITE_DIALOG", 129, "systemux://dialog/social-receive-invite");
    public static final SystemUXRoute SOCIAL_REQUESTS = new SystemUXRoute("SOCIAL_REQUESTS", 61, "systemux://social_requests");
    public static final SystemUXRoute STATIONARY_GUARDIAN_V2_NUX = new SystemUXRoute("STATIONARY_GUARDIAN_V2_NUX", 130, "systemux://dialog/stationary-guardian-v2-nux");
    public static final SystemUXRoute STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE = new SystemUXRoute("STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE", 131, "systemux://dialog/stationary-guardian-v2-nux-using-roomscale");
    public static final SystemUXRoute STORAGE_MANAGER = new SystemUXRoute("STORAGE_MANAGER", 62, "systemux://storage_manager");
    public static final SystemUXRoute STORE = new SystemUXRoute("STORE", 63, "systemux://store");
    public static final SystemUXRoute SYSTEM_FAILURE_MESSAGE = new SystemUXRoute("SYSTEM_FAILURE_MESSAGE", 132, "systemux://dialog/system-failure");
    public static final SystemUXRoute TRACKING_MODE_3DOF_INCOMPAT_DIALOG = new SystemUXRoute("TRACKING_MODE_3DOF_INCOMPAT_DIALOG", 133, "systemux://dialog/tracking-mode-3dof-incompat");
    public static final SystemUXRoute TRACKING_OFF_DIALOG = new SystemUXRoute("TRACKING_OFF_DIALOG", 134, "systemux://dialog/tracking-off");
    public static final SystemUXRoute TV = new SystemUXRoute("TV", 64, "systemux://tv");
    public static final SystemUXRoute UNINSTALLER = new SystemUXRoute("UNINSTALLER", 65, "systemux://uninstaller");
    public static final SystemUXRoute UNIVERSAL_SHARE_SHEET = new SystemUXRoute("UNIVERSAL_SHARE_SHEET", 135, "systemux://universal-share-sheet");
    public static final SystemUXRoute UNLOCK_PATTERN = new SystemUXRoute("UNLOCK_PATTERN", 66, "systemux://unlockpattern");
    public static final SystemUXRoute UNOFFICIAL_APP_INSTALLED_DIALOG = new SystemUXRoute("UNOFFICIAL_APP_INSTALLED_DIALOG", 136, "systemux://dialog/unofficial-app-installed");
    public static final SystemUXRoute UNOFFICIAL_APP_LAUNCHED_DIALOG;
    public static final SystemUXRoute USER_BLOCK = new SystemUXRoute("USER_BLOCK", 67, "systemux://user_block");
    public static final SystemUXRoute USER_FRIEND_REQUEST = new SystemUXRoute("USER_FRIEND_REQUEST", 68, "systemux://user_friend_request");
    public static final SystemUXRoute USER_PROFILE = new SystemUXRoute("USER_PROFILE", 69, "systemux://user_profile");
    public static final SystemUXRoute USER_REPORT = new SystemUXRoute("USER_REPORT", 70, "systemux://user_report");
    public static final SystemUXRoute USER_UNBLOCK = new SystemUXRoute("USER_UNBLOCK", 71, "systemux://user_unblock");
    public static final SystemUXRoute WIFI = new SystemUXRoute("WIFI", 72, "systemux://wifi");
    public final String mPath;

    static {
        SystemUXRoute systemUXRoute = new SystemUXRoute("UNOFFICIAL_APP_LAUNCHED_DIALOG", 137, "systemux://dialog/unofficial-app-launched");
        UNOFFICIAL_APP_LAUNCHED_DIALOG = systemUXRoute;
        SystemUXRoute[] systemUXRouteArr = new SystemUXRoute[138];
        System.arraycopy(new SystemUXRoute[]{ALL_EVENTS, ASSISTANT, AUI_INTERNAL_SETTINGS, AUI_CHATS, AUI_MESSENGER, AUI_PARTIES, AUI_PEOPLE, AUI_PEOPLE_FB, AUI_PROFILE, AUI_SOCIAL, AUI_SOCIAL_REAUTH, AUI_SOCIAL_V2, AUI_SOCIAL_SETTINGS, AUI_TABLET_NONE, AVATAR_EDITOR, BLOCKANDREPORT, BLUETOOTH, BUG_REPORT, CAMERA_ROLL, CAPTIVE_WIFI_PORTAL, DATE_TIME_SETTINGS, DEFAULT_BROWSER, DEVICE_BATTERY, EVENTS, FBCONNECT, FILE_MANAGER, FRIENDS}, 0, systemUXRouteArr, 0, 27);
        System.arraycopy(new SystemUXRoute[]{GAMING_ACTIVITY, GROUP_LAUNCH_APP_SELECTOR, GROUP_LAUNCH_DESTINATION_SELECTOR, HAND_TRACKING_NUX, HEALTH_AND_SAFETY, HOME, INSTALLER, INVITE_FRIENDS, INVITE_TO_APP, LAUNCH_IAP, LIBRARY, LIVESTREAMING, LOCKPATTERN, MEDIA_PREVIEW, MESSAGES, NOTIFICATIONS, PARTIES, PAUSE, PERMISSIONS, PRESENCE_INVITE, PROFILE, PROFILE_PHOTO_EDITOR, QUICK_MESSAGE, QUICK_SETTINGS, REMOTE_SYSTEM_ACTION_REDIRECT, SEARCH, SETTINGS}, 0, systemUXRouteArr, 27, 27);
        System.arraycopy(new SystemUXRoute[]{SHARE_MEDIA, SHARE_SHEET, SHARE_SHEET_V2, SHARING, SOCIAL, SOCIAL_ADD_FRIENDS, SOCIAL_OVERLAY_PANEL, SOCIAL_REQUESTS, STORAGE_MANAGER, STORE, TV, UNINSTALLER, UNLOCK_PATTERN, USER_BLOCK, USER_FRIEND_REQUEST, USER_PROFILE, USER_REPORT, USER_UNBLOCK, WIFI, NONE, APP_DOWNLOAD_FAILURE_LOW_STORAGE, APP_MODE_INCOMPATIBLE, APP_LAUNCH_BLOCKED_CONTROLLER_REQUIRED, APP_LAUNCH_BLOCKED_HANDS_REQUIRED, CONTROLLER_PAIRING, ENTERPRISE_CERTIFICATE_EXPIRED, ENTERPRISE_CERTIFICATE_EXPIRING_WARNING}, 0, systemUXRouteArr, 54, 27);
        System.arraycopy(new SystemUXRoute[]{ENTERPRISE_GUARDIAN_DISABLED_WARNING, ENTERPRISE_KIOSK_NOT_INSTALLED, FACEBOOK_BLOCK, FIRST_TIME_NUX, FITNESS_TRACKER, GUARDIAN_DIALOG, GUARDIAN_DIALOG_ROOMSCALE_URI, GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI, GUARDIAN_DIALOG_STATIONARY_URI, GUARDIAN_ADJUST_DIALOG, GUARDIAN_ADJUST_FLOOR, GUARDIAN_ADJUST_SETUP, GUARDIAN_CLEAR_HISTORY, GUARDIAN_CLEAR_SURFACES, GUARDIAN_CREATE_SURFACE, GUARDIAN_CREATE_DESK, INTRUSION_DETECTION_NUX, IPD_ADJUST, KEYBOARD, LAUNCH_CHECK_APP_DEGRADED, LAUNCH_CHECK_APP_DISABLED, LAUNCH_CHECK_APP_UPDATE, LAUNCH_CHECK_BLOCK_DESK, LAUNCH_CHECK_CLOUD_STORAGE, LAUNCH_CHECK_REQUIRES_6DOF, LAUNCH_CHECK_REQUIRES_AVATAR, LAUNCH_CHECK_REQUIRES_CONTROLLERS}, 0, systemUXRouteArr, 81, 27);
        System.arraycopy(new SystemUXRoute[]{LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE, LAUNCH_CHECK_REQUIRES_HANDS, LOCAL_STREAM_PRIVACY_CHECK_DIALOG, LOCAL_STREAM_START_FROM_DEVICE_DIALOG, LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG, LOCAL_STREAM_TO_BROWSER_DIALOG, LOGIN, MESSENGER_INTEGRITY, OCULUS_LINK_AVAILABLE, OCULUS_LINK_DISCONNECTED, PANEL_REORIENT, PASSTHROUGH_PORTAL, PT_ONDEMAND_NUX_DIALOG, QUICKBOOT_NUX, QUIT_AND_LAUNCH, SOCIAL_JOIN_PARTY, SOCIAL_CONFIRM_JOIN_PARTY, SOCIAL_CREATE_PARTY_PREVIEW, SOCIAL_CREATE_VR_INVITE_DIALOG, JOIN_PARTY_DIALOG, SOCIAL_PARTY_PRIVACY, SOCIAL_RECEIVE_INVITE_DIALOG, STATIONARY_GUARDIAN_V2_NUX, STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE, SYSTEM_FAILURE_MESSAGE, TRACKING_MODE_3DOF_INCOMPAT_DIALOG, TRACKING_OFF_DIALOG}, 0, systemUXRouteArr, 108, 27);
        System.arraycopy(new SystemUXRoute[]{UNIVERSAL_SHARE_SHEET, UNOFFICIAL_APP_INSTALLED_DIALOG, systemUXRoute}, 0, systemUXRouteArr, 135, 3);
        $VALUES = systemUXRouteArr;
    }

    @Nullable
    public static SystemUXRoute fromPath(String str) {
        if (str != null) {
            int indexOf = str.indexOf(63);
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
            SystemUXRoute[] values = values();
            for (SystemUXRoute systemUXRoute : values) {
                if (systemUXRoute.path().equals(str)) {
                    return systemUXRoute;
                }
            }
        }
        return null;
    }

    public static SystemUXRoute valueOf(String str) {
        return (SystemUXRoute) Enum.valueOf(SystemUXRoute.class, str);
    }

    public static SystemUXRoute[] values() {
        return (SystemUXRoute[]) $VALUES.clone();
    }

    public String path() {
        return this.mPath;
    }

    public SystemUXRoute(String str, int i, String str2) {
        if (!str2.startsWith("systemux://")) {
            Log.e(LoggingUtil.tag(SystemUXRoute.class), AnonymousClass006.A07("Incorrect prefix for path: ", str2));
        }
        this.mPath = str2;
    }

    public String toString() {
        return String.format("%s(%s)", name(), path());
    }

    public boolean isSamePath(Uri uri) {
        Uri parse = Uri.parse(this.mPath);
        return Objects.equals(parse.getScheme(), uri.getScheme()) && Objects.equals(parse.getAuthority(), uri.getAuthority()) && Objects.equals(parse.getPath(), uri.getPath());
    }

    public boolean isSamePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isSamePath(Uri.parse(str));
    }
}
