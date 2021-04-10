package com.oculus.tablet.navigation;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import java.util.Locale;

public final class TabletDeepLinkingUriUtil {
    public static final String ANDROID_SETTINGS_QUERY_SECTION_KEY = "section";
    public static final String AUI_ANDROID_SETTINGS_URI = "/android_settings";
    private static final String AUI_CHATS_LOADED_URI_PREFIX = "/chats_loaded";
    private static final String AUI_CHATS_LOADING_URI_PREFIX = "/chats_loading";
    public static final String AUI_CHATS_URI = "/chats";
    public static final String AUI_FB_PROFILE_URI = "/fb";
    public static final String AUI_INTERNAL_SETTINGS_GENERAL_URI = "/general";
    private static final String AUI_LIBRARY_LOADED_URI_PREFIX = "/library_loaded";
    private static final String AUI_LIBRARY_LOADING_URI_PREFIX = "/library_loading";
    public static final String AUI_LIBRARY_URI = "/library";
    private static final String AUI_MESSENGER_LOADED_URI_PREFIX = "/messenger_loaded";
    private static final String AUI_MESSENGER_LOADING_URI_PREFIX = "/messenger_loading";
    public static final String AUI_NOTIFICATIONS_URI = "/notifications";
    private static final String AUI_PARTIES_LOADED_URI_PREFIX = "/parties_loaded";
    private static final String AUI_PARTIES_LOADING_URI_PREFIX = "/parties_loading";
    public static final String AUI_PAUSE_URI = "/pause";
    private static final String AUI_PEOPLE_FB_LOADED_URI_PREFIX = "/people_fb_loaded";
    private static final String AUI_PEOPLE_FB_LOADING_URI_PREFIX = "/people_fb_loading";
    private static final String AUI_PEOPLE_LOADED_URI_PREFIX = "/people_loaded";
    private static final String AUI_PEOPLE_LOADING_URI_PREFIX = "/people_loading";
    public static final String AUI_PROFILE_URI = "/profile";
    public static final String AUI_QUICK_SETTINGS_URI = "/quick_settings";
    public static final String AUI_SELF_VR_PROFILE_URI = "/self_vr";
    private static final String AUI_SETTINGS_LOADED_URI_PREFIX = "/settings_loaded";
    private static final String AUI_SETTINGS_LOADING_URI_PREFIX = "/settings_loading";
    public static final String AUI_SHARING_URI = "/sharing";
    public static final String AUI_SOCIAL_END_PARTY_URI = "/social/parties/end";
    public static final String AUI_SOCIAL_FRIENDS_URI = "/social_friends";
    private static final String AUI_SOCIAL_LOADED_URI_PREFIX = "/social_tablet_loaded";
    private static final String AUI_SOCIAL_LOADING_URI_PREFIX = "/social_tablet_loading";
    public static final String AUI_SOCIAL_PARTIES_URI = "/social/parties";
    public static final String AUI_SOCIAL_PARTY_VIEW_URI = "/social/party_view";
    private static final String AUI_SOCIAL_REAUTH_LOADED_URI_PREFIX = "/social_reauth_loaded";
    private static final String AUI_SOCIAL_REAUTH_LOADING_URI_PREFIX = "/social_reauth_loading";
    private static final String AUI_SOCIAL_SETTINGS_LOADED_URI_PREFIX = "/social_settings_loaded";
    private static final String AUI_SOCIAL_SETTINGS_LOADING_URI_PREFIX = "/social_settings_loading";
    public static final String AUI_SOCIAL_START_PARTY_URI = "/social/parties/start";
    public static final String AUI_SOCIAL_URI = "/social";
    private static final String AUI_TABLET_LOADED_URI_PREFIX = "/tablet_loaded";
    private static final String AUI_TABLET_LOADING_URI_PREFIX = "/tablet_loading";
    public static final String AUI_TABLET_NONE_URI = "/tablet_none";
    public static final String AUI_VR_PROFILE_URI = "/vr";
    public static final String LIBRARY_ALL_URI = "/library/all";
    public static final String LIBRARY_DEMOS_URI = "/library/demos";
    public static final String LIBRARY_INSTALLED_URI = "/library/installed";
    public static final String LIBRARY_NOT_INSTALLED_URI = "/library/not_installed";
    public static final String LIBRARY_OCULUS_APPS_URI = "/library/oculus_apps";
    public static final String LIBRARY_PROTOTYPES_URI = "/library/prototypes";
    public static final String LIBRARY_SHARED_URI = "/library/shared";
    public static final String LIBRARY_TUTORIALS_URI = "/library/tutorials";
    public static final String LIBRARY_UNKNOWN_SOURCES_URI = "/library/unknown_sources";
    public static final String LIBRARY_UPDATES_URI = "/library/updates";
    public static final String NONE_QUERY_STATE_CHANGE_REASON_KEY = "state_change_reason";
    public static final String SETTINGS_ABOUT_URI = "/about";
    public static final String SETTINGS_ACCOUNTS_URI = "/accounts";
    public static final String SETTINGS_APPLICATIONS_URI = "/applications";
    public static final String SETTINGS_ASSISTANT_URI = "/assistant";
    public static final String SETTINGS_BLUETOOTH_MOUSE_URI = "/bluetooth_mouse";
    public static final String SETTINGS_CONTROLLERS_URI = "/controllers";
    private static final String SETTINGS_DEEP_LINK_URI_PREFIX = "/settings";
    public static final String SETTINGS_DEVELOPER_URI = "/developer";
    public static final String SETTINGS_DEVICE_URI = "/device";
    public static final String SETTINGS_ENVIRONMENT_URI = "/environment";
    public static final String SETTINGS_EXPERIMENTS_URI = "/experiments";
    public static final String SETTINGS_GUARDIAN_URI = "/guardian";
    public static final String SETTINGS_INFINITE_OFFICE_PLATFORM_URI = "/infinite_office_platform";
    public static final String SETTINGS_INTRUSION_DETECTION_URI = "/intrusion_detection";
    public static final String SETTINGS_KEYBOARD_URI = "/keyboard";
    public static final String SETTINGS_NOTIFICATIONS_URI = "/notifications";
    public static final String SETTINGS_PASSTHROUGH_PORTAL_URI = "/passthrough_portal";
    public static final String SETTINGS_PERMISSIONS_URI = "/permissions";
    public static final String SETTINGS_PHONE_NOTIFICATIONS_URI = "/phone_notifications";
    public static final String SETTINGS_POWER_URI = "/power";
    public static final String SETTINGS_PRIVACY_URI = "/privacy_settings";
    public static final String SETTINGS_STORAGE_URI = "/storage";
    public static final String SETTINGS_TRACKED_KEYBOARD_URI = "/tracked_keyboard";
    public static final String SETTINGS_VOICE_ACTIVITY = "/voice_activity";
    private static final String TAG = LoggingUtil.tag(TabletDeepLinkingUriUtil.class);

    public static boolean isTabletLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_TABLET_LOADED_URI_PREFIX);
    }

    public static boolean isTabletLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_TABLET_LOADING_URI_PREFIX);
    }

    public static boolean isLibraryTabletDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_LIBRARY_URI);
    }

    public static boolean isAndroidSettingsTabletDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_ANDROID_SETTINGS_URI);
    }

    public static boolean isNoneTabletDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_TABLET_NONE_URI);
    }

    public static boolean isChatsTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_CHATS_LOADING_URI_PREFIX);
    }

    public static boolean isChatsTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_CHATS_LOADED_URI_PREFIX);
    }

    public static boolean isLibraryStandaloneLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_LIBRARY_LOADING_URI_PREFIX);
    }

    public static boolean isLibraryStandaloneLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_LIBRARY_LOADED_URI_PREFIX);
    }

    public static boolean isMessengerTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_MESSENGER_LOADING_URI_PREFIX);
    }

    public static boolean isMessengerTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_MESSENGER_LOADED_URI_PREFIX);
    }

    public static boolean isPartiesTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PARTIES_LOADING_URI_PREFIX);
    }

    public static boolean isPartiesTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PARTIES_LOADED_URI_PREFIX);
    }

    public static boolean isPeopleTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PEOPLE_LOADING_URI_PREFIX);
    }

    public static boolean isPeopleTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PEOPLE_LOADED_URI_PREFIX);
    }

    public static boolean isPeopleFBTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PEOPLE_FB_LOADING_URI_PREFIX);
    }

    public static boolean isPeopleFBTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PEOPLE_FB_LOADED_URI_PREFIX);
    }

    public static String getSettingsTabletAppNavigationUri(String str) {
        int indexOf = str.indexOf("?");
        if (isSettingsTabletAppLoadingDeepLinkUri(str)) {
            if (indexOf <= 0) {
                indexOf = str.length();
            }
            return str.substring(26, indexOf).toLowerCase(Locale.ROOT);
        } else if (!isSettingsTabletAppLoadedDeepLinkUri(str)) {
            return "";
        } else {
            if (indexOf <= 0) {
                indexOf = str.length();
            }
            return str.substring(25, indexOf).toLowerCase(Locale.ROOT);
        }
    }

    public static String getAndroidSettingsSectionUri(String str) {
        String queryParameter = Uri.parse(str).getQueryParameter(ANDROID_SETTINGS_QUERY_SECTION_KEY);
        if (TextUtils.isEmpty(queryParameter)) {
            return AUI_QUICK_SETTINGS_URI;
        }
        return "/" + queryParameter;
    }

    public static String getTabletHidingReason(String str) {
        return Uri.parse(str).getQueryParameter(NONE_QUERY_STATE_CHANGE_REASON_KEY);
    }

    public static boolean isProfileTabletDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PROFILE_URI) || deeplinkMatchIgnoreCase(str, AUI_FB_PROFILE_URI) || deeplinkMatchIgnoreCase(str, AUI_VR_PROFILE_URI) || deeplinkMatchIgnoreCase(str, AUI_SELF_VR_PROFILE_URI);
    }

    public static boolean isFBProfileTabletDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_FB_PROFILE_URI);
    }

    public static boolean isVRProfileTabletDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_VR_PROFILE_URI);
    }

    public static boolean isSettingsTabletAppDeepLinkUri(String str) {
        return isSettingsTabletAppLoadingDeepLinkUri(str) || isSettingsTabletAppLoadedDeepLinkUri(str);
    }

    public static boolean isSettingsTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SETTINGS_LOADING_URI_PREFIX);
    }

    public static boolean isSettingsTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SETTINGS_LOADED_URI_PREFIX);
    }

    public static boolean isSocialTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_LOADING_URI_PREFIX);
    }

    public static boolean isSocialTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_LOADED_URI_PREFIX);
    }

    public static boolean isSocialReauthTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_REAUTH_LOADING_URI_PREFIX);
    }

    public static boolean isSocialReauthTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_REAUTH_LOADED_URI_PREFIX);
    }

    public static boolean isSocialSettingsTabletAppLoadingDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_SETTINGS_LOADING_URI_PREFIX);
    }

    public static boolean isSocialSettingsTabletAppLoadedDeepLinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_SETTINGS_LOADED_URI_PREFIX);
    }

    public static boolean isInternalSettingsGeneralDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_INTERNAL_SETTINGS_GENERAL_URI);
    }

    public static boolean isNotificationsDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, "/notifications");
    }

    public static boolean isPauseDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_PAUSE_URI);
    }

    public static boolean isSharingDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SHARING_URI);
    }

    public static boolean isSocialDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_SOCIAL_URI) || deeplinkMatchIgnoreCase(str, AUI_SOCIAL_FRIENDS_URI);
    }

    public static boolean isQuickSettingsDeeplinkUri(String str) {
        return deeplinkMatchIgnoreCase(str, AUI_QUICK_SETTINGS_URI);
    }

    public static boolean deeplinkMatchIgnoreCase(String str, String str2) {
        if (str == null) {
            Log.e(TAG, "Should not be called with null deep link uri.");
            return false;
        }
        String lowerCase = str.toLowerCase(Locale.ROOT);
        String lowerCase2 = str2.toLowerCase(Locale.ROOT);
        if (!lowerCase.equals(lowerCase2)) {
            if (!lowerCase.startsWith(lowerCase2 + "/")) {
                if (lowerCase.startsWith(lowerCase2 + "?")) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }
}
