package com.oculus.panelapp.anytimeui.v2.tablet.common;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import javax.annotation.Nullable;

public enum Tablet {
    ANDROID_LIBRARY,
    ANDROID_SETTINGS,
    CHATS_LOADED,
    CHATS_LOADING,
    INTERNAL_SETTINGS_GENERAL,
    LIBRARY_STANDALONE_LOADED,
    LIBRARY_STANDALONE_LOADING,
    MESSENGER_LOADED,
    MESSENGER_LOADING,
    NONE,
    NOTIFICATIONS,
    PARTIES_LOADED,
    PARTIES_LOADING,
    PAUSE,
    PEOPLE_LOADED,
    PEOPLE_LOADING,
    PEOPLE_FB_LOADED,
    PEOPLE_FB_LOADING,
    PROFILE,
    SETTINGS,
    SETTINGS_LOADED,
    SETTINGS_LOADING,
    SHARING,
    SOCIAL,
    SOCIAL_LOADED,
    SOCIAL_LOADING,
    SOCIAL_REAUTH_LOADED,
    SOCIAL_REAUTH_LOADING,
    SOCIAL_SETTINGS_LOADED,
    SOCIAL_SETTINGS_LOADING,
    TABLET_LOADED,
    TABLET_LOADING;
    
    private static final String TAG = LoggingUtil.tag(Tablet.class);

    public boolean isInternal() {
        return this == INTERNAL_SETTINGS_GENERAL;
    }

    @Nullable
    public static Tablet getTabletForDeepLinkUri(String str) {
        if (str == null) {
            Log.e(TAG, "No tablet for null uri.");
            return null;
        } else if (TabletDeepLinkingUriUtil.isChatsTabletAppLoadingDeepLinkUri(str)) {
            return CHATS_LOADING;
        } else {
            if (TabletDeepLinkingUriUtil.isChatsTabletAppLoadedDeepLinkUri(str)) {
                return CHATS_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isLibraryStandaloneLoadedDeepLinkUri(str)) {
                return LIBRARY_STANDALONE_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isLibraryStandaloneLoadingDeepLinkUri(str)) {
                return LIBRARY_STANDALONE_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isMessengerTabletAppLoadingDeepLinkUri(str)) {
                return MESSENGER_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isMessengerTabletAppLoadedDeepLinkUri(str)) {
                return MESSENGER_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isPartiesTabletAppLoadingDeepLinkUri(str)) {
                return PARTIES_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isPartiesTabletAppLoadedDeepLinkUri(str)) {
                return PARTIES_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isPeopleTabletAppLoadingDeepLinkUri(str)) {
                return PEOPLE_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isPeopleTabletAppLoadedDeepLinkUri(str)) {
                return PEOPLE_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isPeopleFBTabletAppLoadingDeepLinkUri(str)) {
                return PEOPLE_FB_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isPeopleFBTabletAppLoadedDeepLinkUri(str)) {
                return PEOPLE_FB_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isSettingsTabletAppLoadingDeepLinkUri(str)) {
                return SETTINGS_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isSettingsTabletAppLoadedDeepLinkUri(str)) {
                return SETTINGS_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isSocialReauthTabletAppLoadingDeepLinkUri(str)) {
                return SOCIAL_REAUTH_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isSocialReauthTabletAppLoadedDeepLinkUri(str)) {
                return SOCIAL_REAUTH_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isSocialTabletAppLoadingDeepLinkUri(str)) {
                return SOCIAL_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isSocialTabletAppLoadedDeepLinkUri(str)) {
                return SOCIAL_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isSocialSettingsTabletAppLoadingDeepLinkUri(str)) {
                return SOCIAL_SETTINGS_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isSocialSettingsTabletAppLoadedDeepLinkUri(str)) {
                return SOCIAL_SETTINGS_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isTabletLoadedDeepLinkUri(str)) {
                return TABLET_LOADED;
            }
            if (TabletDeepLinkingUriUtil.isTabletLoadingDeepLinkUri(str)) {
                return TABLET_LOADING;
            }
            if (TabletDeepLinkingUriUtil.isLibraryTabletDeepLinkUri(str)) {
                return ANDROID_LIBRARY;
            }
            if (TabletDeepLinkingUriUtil.isAndroidSettingsTabletDeepLinkUri(str)) {
                return ANDROID_SETTINGS;
            }
            if (TabletDeepLinkingUriUtil.isInternalSettingsGeneralDeeplinkUri(str)) {
                return INTERNAL_SETTINGS_GENERAL;
            }
            if (TabletDeepLinkingUriUtil.isNoneTabletDeepLinkUri(str)) {
                return NONE;
            }
            if (TabletDeepLinkingUriUtil.isNotificationsDeeplinkUri(str)) {
                return NOTIFICATIONS;
            }
            if (TabletDeepLinkingUriUtil.isPauseDeeplinkUri(str)) {
                return PAUSE;
            }
            if (TabletDeepLinkingUriUtil.isProfileTabletDeeplinkUri(str)) {
                return PROFILE;
            }
            if (TabletDeepLinkingUriUtil.isSharingDeeplinkUri(str)) {
                return SHARING;
            }
            if (TabletDeepLinkingUriUtil.isSocialDeeplinkUri(str)) {
                return SOCIAL;
            }
            if (TabletDeepLinkingUriUtil.isQuickSettingsDeeplinkUri(str)) {
                return SETTINGS;
            }
            Log.e(TAG, String.format("Unable to find tablet for deep link uri: %s", str));
            return null;
        }
    }
}
