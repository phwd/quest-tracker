package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAboutSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAccountsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAppPermissionsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsApplicationsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsAssistantSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsBluetoothMouseSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsControllersSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeveloperSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsEnvironmentSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsExperimentsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsGuardianSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsIntrusionDetectionSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsKeyboardSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsNotificationUserCategorySection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsNotificationsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPassthroughPortalSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPermissionGroupSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPermissionsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPhoneNotificationsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsPowerSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsTrackedKeyboardSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsVoiceActivitySection;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;

public class SettingsSectionUtil {
    private static final String APPLICATION_PACKAGE_QUERY_PARAM = "package";
    private static final String NOTIFICATION_CATEGORY_QUERY_PARAM = "category";
    private static final String PERMISSION_GROUP_QUERY_PARAM = "group";
    private static final String SETTING_QUERY_PARAM = "setting";
    private static final String TAG = LoggingUtil.tag(SettingsSectionUtil.class);

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static SettingsSection getSectionFromDeepLink(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, String str, Runnable runnable) {
        char c;
        String androidSettingsSectionUri = TabletDeepLinkingUriUtil.getAndroidSettingsSectionUri(str);
        switch (androidSettingsSectionUri.hashCode()) {
            case -1389342475:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_PERMISSIONS_URI)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -1188160235:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_ACCOUNTS_URI)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1014064871:
                if (androidSettingsSectionUri.equals("/notifications")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -968098453:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_VOICE_ACTIVITY)) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -873529209:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI)) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -745609400:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_PHONE_NOTIFICATIONS_URI)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -726881157:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_DEVELOPER_URI)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -581035328:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_GUARDIAN_URI)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 32167634:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 57371848:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_CONTROLLERS_URI)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 156623813:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_BLUETOOTH_MOUSE_URI)) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case 637708262:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_PASSTHROUGH_PORTAL_URI)) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case 783628175:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_ASSISTANT_URI)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1011881950:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_INTRUSION_DETECTION_URI)) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case 1438181566:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_ABOUT_URI)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1452428854:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_POWER_URI)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1452725526:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_KEYBOARD_URI)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1615396747:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_TRACKED_KEYBOARD_URI)) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case 1686015620:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_ENVIRONMENT_URI)) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case 1722810181:
                if (androidSettingsSectionUri.equals(TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return new SettingsAccountsSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 1:
                String queryParameter = Uri.parse(str).getQueryParameter("package");
                if (TextUtils.isEmpty(queryParameter)) {
                    return new SettingsApplicationsSection(context, anytimeUIAndroidPanelAppV2, runnable);
                }
                return new SettingsAppPermissionsSection(context, anytimeUIAndroidPanelAppV2, queryParameter);
            case 2:
                return new SettingsAssistantSection(context, anytimeUIAndroidPanelAppV2);
            case 3:
                return new SettingsDeveloperSection(context, anytimeUIAndroidPanelAppV2);
            case 4:
                return new SettingsDeviceSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 5:
                return new SettingsControllersSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 6:
                return new SettingsPowerSection(context, anytimeUIAndroidPanelAppV2);
            case 7:
                return new SettingsKeyboardSection(context, anytimeUIAndroidPanelAppV2);
            case '\b':
                return new SettingsAboutSection(context, anytimeUIAndroidPanelAppV2);
            case '\t':
                SettingsViewModel acquireSettingsViewModel = anytimeUIAndroidPanelAppV2.acquireSettingsViewModel();
                if (acquireSettingsViewModel.getGuardianPaused() || acquireSettingsViewModel.getGuardianDisabled() || acquireSettingsViewModel.getIsTrackingIn3DOFMode()) {
                    anytimeUIAndroidPanelAppV2.releaseSettingsViewModel();
                    return new SettingsDeviceSection(context, anytimeUIAndroidPanelAppV2, runnable);
                }
                anytimeUIAndroidPanelAppV2.releaseSettingsViewModel();
                return new SettingsGuardianSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case '\n':
                String queryParameter2 = Uri.parse(str).getQueryParameter(PERMISSION_GROUP_QUERY_PARAM);
                if (TextUtils.isEmpty(queryParameter2)) {
                    return new SettingsPermissionsSection(context, anytimeUIAndroidPanelAppV2, runnable);
                }
                return new SettingsPermissionGroupSection(context, anytimeUIAndroidPanelAppV2, queryParameter2, runnable);
            case 11:
                return new SettingsPhoneNotificationsSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case '\f':
                String queryParameter3 = Uri.parse(str).getQueryParameter(NOTIFICATION_CATEGORY_QUERY_PARAM);
                if (!anytimeUIAndroidPanelAppV2.isGKEnabled(Gatekeeper.SETTINGS_NOTIFS_USER_CONTROL) || TextUtils.isEmpty(queryParameter3)) {
                    return new SettingsNotificationsSection(context, anytimeUIAndroidPanelAppV2, runnable);
                }
                return new SettingsNotificationUserCategorySection(context, anytimeUIAndroidPanelAppV2, runnable, queryParameter3);
            case '\r':
                return new SettingsExperimentsSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 14:
                return new SettingsVoiceActivitySection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 15:
                return new SettingsTrackedKeyboardSection(context, anytimeUIAndroidPanelAppV2);
            case 16:
                return new SettingsPassthroughPortalSection(context, anytimeUIAndroidPanelAppV2);
            case 17:
                return new SettingsBluetoothMouseSection(context, anytimeUIAndroidPanelAppV2);
            case 18:
                return new SettingsEnvironmentSection(context, anytimeUIAndroidPanelAppV2, runnable);
            case 19:
                return new SettingsIntrusionDetectionSection(context, anytimeUIAndroidPanelAppV2, runnable);
            default:
                String str2 = TAG;
                Log.e(str2, "Invalid section: " + androidSettingsSectionUri);
                return null;
        }
    }

    public static String grabSectionFromUri(String str) {
        return Uri.parse(str).getQueryParameter(TabletDeepLinkingUriUtil.ANDROID_SETTINGS_QUERY_SECTION_KEY);
    }

    public static String grabSettingFromUri(String str) {
        return Uri.parse(str).getQueryParameter(SETTING_QUERY_PARAM);
    }
}
