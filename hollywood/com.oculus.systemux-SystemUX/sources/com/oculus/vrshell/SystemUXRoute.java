package com.oculus.vrshell;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Objects;

public enum SystemUXRoute {
    ALL_EVENTS("systemux://all_events"),
    ASSISTANT("systemux://assistant"),
    AUI_INTERNAL_SETTINGS("systemux://aui-internal-settings"),
    AUI_CHATS("systemux://aui-chats"),
    AUI_MESSENGER("systemux://aui-messenger"),
    AUI_PARTIES("systemux://aui-parties"),
    AUI_PEOPLE("systemux://aui-people"),
    AUI_PEOPLE_FB("systemux://aui-people-fb"),
    AUI_PROFILE("systemux://aui-profile"),
    AUI_SOCIAL("systemux://aui-social"),
    AUI_SOCIAL_REAUTH("systemux://aui-social-reauth"),
    AUI_SOCIAL_V2("systemux://aui-social-v2"),
    AUI_SOCIAL_SETTINGS("systemux://aui-social-settings"),
    AUI_TABLET_NONE("systemux://aui-tablet-none"),
    AVATAR_EDITOR("systemux://avatareditor"),
    BLOCKANDREPORT("systemux://blockandreport"),
    BLUETOOTH("systemux://bluetooth"),
    BUG_REPORT("systemux://bug_report"),
    CAMERA_ROLL("systemux://cameraroll"),
    CAPTIVE_WIFI_PORTAL("systemux://captive-wifi-portal"),
    DATE_TIME_SETTINGS("systemux://datetime"),
    DEFAULT_BROWSER("systemux://browser"),
    DEVICE_BATTERY("systemux://devicebattery"),
    EVENTS("systemux://events"),
    FBCONNECT("systemux://fb-connect"),
    FRIENDS("systemux://friends"),
    GAMING_ACTIVITY("systemux://gaming-activity"),
    GROUP_LAUNCH_APP_SELECTOR("systemux://group_launch_app_selector"),
    GROUP_LAUNCH_DESTINATION_SELECTOR("systemux://group_launch_destination_selector"),
    HAND_TRACKING_NUX("systemux://hand-tracking-nux"),
    HEALTH_AND_SAFETY("systemux://health_and_safety_video"),
    HOME("systemux://home"),
    HOME_FIRST_TIME_NUX("systemux://home-first-time-nux"),
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
    PARTIES("systemux://parties"),
    PAUSE("systemux://pause"),
    PERMISSIONS("systemux://permissions"),
    PRESENCE_INVITE("systemux://presence_invite"),
    PROFILE("systemux://profile"),
    PROFILE_PHOTO_EDITOR("systemux://profile_photo_editor"),
    QUICK_MESSAGE("systemux://quick_message"),
    REMOTE_SYSTEM_ACTION_REDIRECT("systemux://remote_system_action_redirect"),
    SEARCH("systemux://search"),
    SETTINGS("systemux://settings"),
    SHARE_MEDIA("systemux://share_media"),
    SHARE_SHEET("systemux://share_sheet"),
    SHARE_SHEET_V2("systemux://share_sheet_v2"),
    SHARING("systemux://sharing"),
    SOCIAL("systemux://social"),
    SOCIAL_ADD_FRIENDS("systemux://social_add_friends"),
    SOCIAL_OVERLAY_PANEL("systemux://social_overlay_panel"),
    SOCIAL_REQUESTS("systemux://social_requests"),
    STORAGE_MANAGER("systemux://storage_manager"),
    STORE("systemux://store"),
    TV("systemux://tv"),
    UNINSTALLER("systemux://uninstaller"),
    UNLOCK_PATTERN("systemux://unlockpattern"),
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
    FACEBOOK_BLOCK("systemux://dialog/facebook-block"),
    FIRST_TIME_NUX("systemux://first-time-nux"),
    FITNESS_TRACKER("systemux://fitness-tracker"),
    GUARDIAN_DIALOG("systemux://guardian/setup"),
    GUARDIAN_DIALOG_ROOMSCALE_URI("systemux://guardian/roomscale-setup"),
    GUARDIAN_DIALOG_SWITCH_ROOMSCALE_URI("systemux://guardian/switch-roomscale"),
    GUARDIAN_DIALOG_STATIONARY_URI("systemux://guardian/stationary-setup"),
    GUARDIAN_ADJUST_DIALOG("systemux://dialog/guardian-adjust"),
    GUARDIAN_ADJUST_FLOOR("systemux://guardian/guardian-adjust-floor"),
    GUARDIAN_ADJUST_SETUP("systemux://guardian/adjust-setup"),
    GUARDIAN_CLEAR_HISTORY("systemux://guardian/guardian-clear-history"),
    GUARDIAN_CLEAR_SURFACES("systemux://guardian/guardian-clear-surfaces"),
    GUARDIAN_CREATE_SURFACE("systemux://guardian/guardian-create-surface"),
    GUARDIAN_CREATE_DESK("systemux://guardian/guardian-create-desk"),
    INTRUSION_DETECTION_NUX("systemux://dialog/intrusion-detection-nux"),
    IPD_ADJUST("systemux://dialog/ipd-adjust"),
    KEYBOARD("systemux://keyboard"),
    LAUNCH_CHECK_APP_DEGRADED("systemux://dialog/launch-check-app-degraded"),
    LAUNCH_CHECK_APP_DISABLED("systemux://dialog/launch-check-app-disabled"),
    LAUNCH_CHECK_APP_UPDATE("systemux://dialog/launch-check-app-update"),
    LAUNCH_CHECK_BLOCK_DESK("systemux://dialog/launch-check-block-desk"),
    LAUNCH_CHECK_CLOUD_STORAGE("systemux://dialog/launch-check-cloud-storage"),
    LAUNCH_CHECK_REQUIRES_6DOF("systemux://dialog/launch-check-requires-6dof"),
    LAUNCH_CHECK_REQUIRES_AVATAR("systemux://dialog/launch-check-requires-avatar"),
    LAUNCH_CHECK_REQUIRES_CONTROLLERS("systemux://dialog/launch-check-requires-controllers"),
    LAUNCH_CHECK_REQUIRES_EXCLUSIVE_MICROPHONE("systemux://dialog/launch-check-requires-exclusive-microphone"),
    LAUNCH_CHECK_REQUIRES_HANDS("systemux://dialog/launch-check-requires-hands"),
    LOCAL_STREAM_PRIVACY_CHECK_DIALOG("systemux://dialog/local-stream-privacy-check"),
    LOCAL_STREAM_START_FROM_DEVICE_DIALOG("systemux://dialog/local-stream-start-from-device"),
    LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG("systemux://dialog/local-stream-stop-from-device"),
    LOCAL_STREAM_TO_BROWSER_DIALOG("systemux://dialog/local-stream-to-browser-dialog"),
    LOGIN("systemux://login"),
    MESSENGER_INTEGRITY("systemux://dialog/messenger-integrity"),
    OCULUS_LINK_AVAILABLE("systemux://dialog/oculus_link_available"),
    OCULUS_LINK_DISCONNECTED("systemux://dialog/oculus_link_disconnected"),
    PANEL_REORIENT("systemux://dialog/panel-reorient"),
    PASSTHROUGH_PORTAL("systemux://guardian/passthrough-portal"),
    PT_ONDEMAND_NUX_DIALOG("systemux://dialog/pt-ondemand-nux"),
    QUEST_VIEW_RECENTER("systemux://dialog/quest-view-recenter"),
    QUEST_SHOW_TASKBAR("systemux://dialog/quest-show-taskbar"),
    QUIT_AND_LAUNCH("systemux://dialog/quit-and-launch"),
    SOCIAL_JOIN_PARTY("systemux://dialog/social-join-party"),
    SOCIAL_CONFIRM_JOIN_PARTY("systemux://dialog/social-confirm-join-party"),
    SOCIAL_CREATE_PARTY_PREVIEW("systemux://dialog/create-party-preview"),
    SOCIAL_CREATE_VR_INVITE_DIALOG("systemux://dialog/create-vr-invite"),
    JOIN_PARTY_DIALOG("systemux://social-join-party"),
    SOCIAL_PARTY_PRIVACY("systemux://dialog/social-party-privacy"),
    SOCIAL_RECEIVE_INVITE_DIALOG("systemux://dialog/social-receive-invite"),
    STATIONARY_GUARDIAN_V2_NUX("systemux://dialog/stationary-guardian-v2-nux"),
    STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE("systemux://dialog/stationary-guardian-v2-nux-using-roomscale"),
    SYSTEM_FAILURE_MESSAGE("systemux://dialog/system-failure"),
    TRACKING_MODE_3DOF_INCOMPAT_DIALOG("systemux://dialog/tracking-mode-3dof-incompat"),
    TRACKING_OFF_DIALOG("systemux://dialog/tracking-off"),
    UNIVERSAL_SHARE_SHEET("systemux://universal-share-sheet"),
    UNOFFICIAL_APP_INSTALLED_DIALOG("systemux://dialog/unofficial-app-installed"),
    UNOFFICIAL_APP_LAUNCHED_DIALOG("systemux://dialog/unofficial-app-launched");
    
    public static final String PREFIX = "systemux://";
    private final String mPath;

    private SystemUXRoute(String str) {
        if (!str.startsWith(PREFIX)) {
            String tag = LoggingUtil.tag(SystemUXRoute.class);
            Log.e(tag, "Incorrect prefix for path: " + str);
        }
        this.mPath = str;
    }

    public String path() {
        return this.mPath;
    }

    @Nullable
    public static SystemUXRoute fromPath(String str) {
        if (str == null) {
            return null;
        }
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
        return null;
    }

    public boolean isSamePath(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return isSamePath(Uri.parse(str));
    }

    public boolean isSamePath(Uri uri) {
        Uri parse = Uri.parse(this.mPath);
        return Objects.equals(parse.getScheme(), uri.getScheme()) && Objects.equals(parse.getAuthority(), uri.getAuthority()) && Objects.equals(parse.getPath(), uri.getPath());
    }

    public String toString() {
        return String.format("%s(%s)", name(), path());
    }
}
