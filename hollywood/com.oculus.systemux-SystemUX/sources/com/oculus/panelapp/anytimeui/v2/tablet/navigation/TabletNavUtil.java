package com.oculus.panelapp.anytimeui.v2.tablet.navigation;

import android.content.res.Resources;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.ocui.OCSideNavItem;
import com.oculus.os.PreferencesManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsViewModel;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.vrshell.notifications.NotificationUri;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class TabletNavUtil {
    private static final String MULTI_USER_BETA_ENABLED_PREF_KEY = "multi_user_beta_enabled_key";
    private static final String TAG = LoggingUtil.tag(TabletNavUtil.class);
    String[] keys;
    private final Map<TabletNav, Map<TabletNavClickListener, WeakReference<TabletNavClickListener>>> mClickListeners = new HashMap();
    private Handler mHandler = new Handler();
    private final Map<TabletNav, List<OCSideNavItem>> mNavItemsMap = new HashMap();
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    PreferencesManager mPref = new PreferencesManager();
    private PreferencesListener mPreferencesListener = new PreferencesListener(this, null);
    private Resources mResources;
    private final Map<TabletNav, Map<TabletNavSelectListener, WeakReference<TabletNavSelectListener>>> mSelectListeners = new HashMap();
    private final Map<TabletNav, OCSideNavItem> mSelectedNavItemMap = new HashMap();
    private final Map<TabletNav, Map<TabletNavItemsUpdateListener, WeakReference<TabletNavItemsUpdateListener>>> mUpdateListeners = new HashMap();
    private boolean multiUserBetaEnabled;

    @FunctionalInterface
    public interface TabletNavClickListener {
        void onClick(OCSideNavItem oCSideNavItem);
    }

    @FunctionalInterface
    public interface TabletNavItemsUpdateListener {
        void onUpdate();
    }

    @FunctionalInterface
    public interface TabletNavSelectListener {
        void onSelect(OCSideNavItem oCSideNavItem);
    }

    private boolean isBetaReleasesEnabled() {
        return true;
    }

    private boolean isBluetoothPairingEnabled() {
        return true;
    }

    public TabletNavUtil(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Resources resources) {
        this.multiUserBetaEnabled = ((Boolean) this.mPref.getBoolean(MULTI_USER_BETA_ENABLED_PREF_KEY).first).booleanValue() ? ((Boolean) this.mPref.getBoolean(MULTI_USER_BETA_ENABLED_PREF_KEY).second).booleanValue() : false;
        this.keys = new String[]{MULTI_USER_BETA_ENABLED_PREF_KEY};
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mResources = resources;
        initSideNavItems(TabletNav.INTERNAL_SETTINGS);
        initSideNavItems(TabletNav.NOTIFICATIONS);
        initSideNavItems(TabletNav.SETTINGS);
        this.mPref.setListener(this.mPreferencesListener, this.keys);
    }

    public void destroy() {
        this.mPref.clearListener();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav = new int[TabletNav.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav = r0
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.INTERNAL_SETTINGS     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.NOTIFICATIONS     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav r1 = com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNav.SETTINGS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.anytimeui.v2.tablet.navigation.TabletNavUtil.AnonymousClass1.<clinit>():void");
        }
    }

    private void initSideNavItems(TabletNav tabletNav) {
        ArrayList arrayList = new ArrayList();
        int i = AnonymousClass1.$SwitchMap$com$oculus$panelapp$anytimeui$v2$tablet$navigation$TabletNav[tabletNav.ordinal()];
        if (i == 1) {
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_internal_settings_general_v2), TabletDeepLinkingUriUtil.AUI_INTERNAL_SETTINGS_GENERAL_URI, R.id.side_nav_internal_settings_general));
        } else if (i == 2) {
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_all), NotificationUri.ALL, R.id.side_nav_notifications_all));
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_apps), "apps", R.id.side_nav_notifications_apps));
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_social), "social", R.id.side_nav_notifications_social));
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_store), NotificationUri.STORE, R.id.side_nav_notifications_store));
            if (shouldShowPhoneNotificationsTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_phone), NotificationUri.PHONE, R.id.side_nav_notifications_phone));
            }
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_notifications_device), NotificationUri.DEVICE, R.id.side_nav_notifications_device));
        } else if (i == 3) {
            arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_quick_settings_v2), TabletDeepLinkingUriUtil.AUI_QUICK_SETTINGS_URI, R.id.side_nav_settings_quick_settings));
            arrayList.add(getSettingsDeviceTab());
            if (shouldShowSettingsInfiniteOfficePlatformTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_infinite_office_platform), TabletDeepLinkingUriUtil.SETTINGS_INFINITE_OFFICE_PLATFORM_URI, R.id.side_nav_settings_infinite_office_platform));
            }
            if (shouldShowSettingsGuardianTab()) {
                arrayList.add(getSettingsGuardianTab());
            }
            arrayList.add(getSettingsStorageTab());
            if (shouldShowSettingsNotificationsTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_notifications_v2), "/notifications", R.id.side_nav_settings_notifications));
            }
            if (shouldShowSettingsApplicationsTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_applications_v2), TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI, R.id.side_nav_settings_applications));
            }
            if (shouldShowSettingsAccountsTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_accounts_v2), TabletDeepLinkingUriUtil.SETTINGS_ACCOUNTS_URI, R.id.side_nav_settings_accounts));
            }
            if (shouldShowSettingsVirtualEnvironmentTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_virtual_environment_v2), TabletDeepLinkingUriUtil.SETTINGS_ENVIRONMENT_URI, R.id.side_nav_settings_virtual_environment));
            }
            if (shouldShowSettingsExperimentTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_experiments_v2), TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI, R.id.side_nav_settings_experiments));
            }
            if (shouldShowSettingsDeveloperTab()) {
                arrayList.add(new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_developer_v2), TabletDeepLinkingUriUtil.SETTINGS_DEVELOPER_URI, R.id.side_nav_settings_developer));
            }
            arrayList.add(getSettingsAboutTab());
        } else {
            throw new IllegalArgumentException("refreshNavItems does not handle: " + tabletNav);
        }
        OCSideNavItem selectedNavItem = getSelectedNavItem(tabletNav);
        if (selectedNavItem == null) {
            selectedNavItem = (OCSideNavItem) arrayList.get(0);
        }
        int intValue = selectedNavItem.getViewId().intValue();
        this.mSelectedNavItemMap.put(tabletNav, (OCSideNavItem) arrayList.stream().filter(new Predicate(intValue) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$TabletNavUtil$kslYotdojmtfLhQCo11_UbqvVG8 */
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return TabletNavUtil.lambda$initSideNavItems$62(this.f$0, (OCSideNavItem) obj);
            }
        }).findFirst().orElse(arrayList.get(0)));
        this.mNavItemsMap.put(tabletNav, arrayList);
    }

    static /* synthetic */ boolean lambda$initSideNavItems$62(int i, OCSideNavItem oCSideNavItem) {
        return oCSideNavItem.getViewId().intValue() == i;
    }

    public OCSideNavItem getSelectedNavItem(TabletNav tabletNav) {
        return this.mSelectedNavItemMap.get(tabletNav);
    }

    public List<OCSideNavItem> getNavItems(TabletNav tabletNav) {
        List<OCSideNavItem> list = this.mNavItemsMap.get(tabletNav);
        if (list != null) {
            return list;
        }
        throw new IllegalArgumentException("getNavItems does not handle: " + tabletNav);
    }

    public List<OCSideNavItem> reloadNavItems(TabletNav tabletNav) {
        initSideNavItems(tabletNav);
        return getNavItems(tabletNav);
    }

    private boolean shouldShowPhoneNotificationsTab() {
        return this.mPanelApp.isGKEnabled(Gatekeeper.PHONE_NOTIFICATIONS_FEED_TAB_ENABLED);
    }

    public boolean isSettingsTabEnabledForEnterprise() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mPanelApp.getSystemUXConfig().isEnterpriseAdminModeEnabled;
    }

    private OCSideNavItem getSettingsDeviceTab() {
        OCSideNavItem oCSideNavItem = new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_device_v2), TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI, R.id.side_nav_settings_device);
        oCSideNavItem.setEnabled(isSettingsTabEnabledForEnterprise());
        return oCSideNavItem;
    }

    private boolean shouldShowSettingsGuardianTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode || this.mPanelApp.isGuardianEnabled();
    }

    private boolean isGuardianTabEnabled() {
        boolean guardianTabEnabled = this.mPanelApp.acquireSettingsViewModel().getGuardianTabEnabled();
        this.mPanelApp.releaseSettingsViewModel();
        return guardianTabEnabled;
    }

    private OCSideNavItem getSettingsGuardianTab() {
        OCSideNavItem oCSideNavItem = new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_guardian_v2), TabletDeepLinkingUriUtil.SETTINGS_GUARDIAN_URI, R.id.side_nav_settings_guardian);
        oCSideNavItem.setEnabled(isGuardianTabEnabled());
        return oCSideNavItem;
    }

    private OCSideNavItem getSettingsStorageTab() {
        OCSideNavItem oCSideNavItem = new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_storage_v2), TabletDeepLinkingUriUtil.SETTINGS_STORAGE_URI, R.id.side_nav_settings_storage);
        oCSideNavItem.setEnabled(isSettingsTabEnabledForEnterprise());
        return oCSideNavItem;
    }

    private OCSideNavItem getSettingsAboutTab() {
        OCSideNavItem oCSideNavItem = new OCSideNavItem(this.mResources.getString(R.string.anytime_tablet_settings_nav_about_v2), TabletDeepLinkingUriUtil.SETTINGS_ABOUT_URI, R.id.side_nav_settings_about);
        oCSideNavItem.setEnabled(isSettingsTabEnabledForEnterprise());
        return oCSideNavItem;
    }

    private boolean shouldShowSettingsNotificationsTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    private boolean shouldShowSettingsApplicationsTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    private boolean shouldShowSettingsAccountsTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && OculusUserManager.isMultiUserEnabled() && this.multiUserBetaEnabled;
    }

    private boolean shouldShowSettingsVirtualEnvironmentTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    private boolean shouldShowSettingsDeveloperTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && this.mPanelApp.isGKEnabled(Gatekeeper.OCULUS_DEVELOPER);
    }

    private boolean shouldShowSettingsInfiniteOfficePlatformTab() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode && this.mPanelApp.isGKEnabled(Gatekeeper.QUEST_INFINITE_OFFICE_PLATFORM);
    }

    private boolean shouldShowSettingsExperimentTab() {
        if (this.mPanelApp.getSystemUXConfig().isEnterpriseMode) {
            return false;
        }
        if (isBluetoothPairingEnabled() || isBetaReleasesEnabled() || isPassthroughOndemandEnabled()) {
            return true;
        }
        return false;
    }

    private boolean isPassthroughOndemandEnabled() {
        return !this.mPanelApp.getSystemUXConfig().isEnterpriseMode;
    }

    public void onDeeplinkUri(String str) {
        if (!handleSettingsDeeplinkUri(str) && TabletDeepLinkingUriUtil.AUI_INTERNAL_SETTINGS_GENERAL_URI.equals(str)) {
            selectSideNavItem(TabletNav.INTERNAL_SETTINGS, getNavItems(TabletNav.INTERNAL_SETTINGS).get(0).getViewId().intValue());
        }
    }

    private boolean handleSettingsDeeplinkUri(String str) {
        if (TabletDeepLinkingUriUtil.isSettingsTabletAppDeepLinkUri(str)) {
            str = TabletDeepLinkingUriUtil.getSettingsTabletAppNavigationUri(str);
        } else if (TabletDeepLinkingUriUtil.isAndroidSettingsTabletDeepLinkUri(str)) {
            str = TabletDeepLinkingUriUtil.getAndroidSettingsSectionUri(str);
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1576566932:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_STORAGE_URI)) {
                    c = '\b';
                    break;
                }
                break;
            case -1389342475:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_PERMISSIONS_URI)) {
                    c = '\f';
                    break;
                }
                break;
            case -1188160235:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_ACCOUNTS_URI)) {
                    c = '\r';
                    break;
                }
                break;
            case -1014064871:
                if (str.equals("/notifications")) {
                    c = '\t';
                    break;
                }
                break;
            case -968098453:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_VOICE_ACTIVITY)) {
                    c = 3;
                    break;
                }
                break;
            case -873529209:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_EXPERIMENTS_URI)) {
                    c = 15;
                    break;
                }
                break;
            case -745609400:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_PHONE_NOTIFICATIONS_URI)) {
                    c = '\n';
                    break;
                }
                break;
            case -726881157:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_DEVELOPER_URI)) {
                    c = 16;
                    break;
                }
                break;
            case -581035328:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_GUARDIAN_URI)) {
                    c = 7;
                    break;
                }
                break;
            case 32167634:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_APPLICATIONS_URI)) {
                    c = 11;
                    break;
                }
                break;
            case 57371848:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_CONTROLLERS_URI)) {
                    c = 4;
                    break;
                }
                break;
            case 783628175:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_ASSISTANT_URI)) {
                    c = 2;
                    break;
                }
                break;
            case 1438181566:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_ABOUT_URI)) {
                    c = 17;
                    break;
                }
                break;
            case 1452428854:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_POWER_URI)) {
                    c = 6;
                    break;
                }
                break;
            case 1452725526:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_KEYBOARD_URI)) {
                    c = 5;
                    break;
                }
                break;
            case 1686015620:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_ENVIRONMENT_URI)) {
                    c = 14;
                    break;
                }
                break;
            case 1710793764:
                if (str.equals(TabletDeepLinkingUriUtil.AUI_QUICK_SETTINGS_URI)) {
                    c = 0;
                    break;
                }
                break;
            case 1722810181:
                if (str.equals(TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_quick_settings);
                return true;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_device);
                return true;
            case 7:
                SettingsViewModel acquireSettingsViewModel = this.mPanelApp.acquireSettingsViewModel();
                if (acquireSettingsViewModel.getGuardianPaused() || acquireSettingsViewModel.getGuardianDisabled() || acquireSettingsViewModel.getIsTrackingIn3DOFMode()) {
                    selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_device);
                } else {
                    selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_guardian);
                }
                this.mPanelApp.releaseSettingsViewModel();
                return true;
            case '\b':
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_storage);
                return true;
            case '\t':
            case '\n':
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_notifications);
                return true;
            case 11:
            case '\f':
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_applications);
                return true;
            case '\r':
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_accounts);
                return true;
            case 14:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_virtual_environment);
                return true;
            case 15:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_experiments);
                return true;
            case 16:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_developer);
                return true;
            case 17:
                selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_about);
                return true;
            default:
                return false;
        }
    }

    /* access modifiers changed from: private */
    public class PreferencesListener implements PreferencesManager.PreferencesListener {
        @Override // com.oculus.os.PreferencesManager.PreferencesListener
        public void onConnectionEstablished() {
        }

        @Override // com.oculus.os.PreferencesManager.PreferencesListener
        public void onConnectionLost() {
        }

        private PreferencesListener() {
        }

        /* synthetic */ PreferencesListener(TabletNavUtil tabletNavUtil, AnonymousClass1 r2) {
            this();
        }

        @Override // com.oculus.os.PreferencesManager.PreferencesListener
        public void onChanged(String[] strArr) {
            TabletNavUtil.this.mHandler.post(new Runnable(strArr) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$TabletNavUtil$PreferencesListener$XCdAe6R_wzZvp8xYFCHK2a7qLVY */
                private final /* synthetic */ String[] f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    TabletNavUtil.PreferencesListener.this.lambda$onChanged$63$TabletNavUtil$PreferencesListener(this.f$1);
                }
            });
        }

        public /* synthetic */ void lambda$onChanged$63$TabletNavUtil$PreferencesListener(String[] strArr) {
            Log.i(TabletNavUtil.TAG, String.format("PreferencesListener::onChanged(%s)", Arrays.toString(strArr)));
            Pair<Boolean, Boolean> pair = TabletNavUtil.this.mPref.getBoolean(TabletNavUtil.MULTI_USER_BETA_ENABLED_PREF_KEY);
            if (((Boolean) pair.first).booleanValue()) {
                boolean booleanValue = ((Boolean) pair.second).booleanValue();
                if (booleanValue != TabletNavUtil.this.multiUserBetaEnabled) {
                    Log.i(TabletNavUtil.TAG, String.format("Multi user beta enabled value has changed from %s to %s.", Boolean.valueOf(TabletNavUtil.this.multiUserBetaEnabled), Boolean.valueOf(booleanValue)));
                    TabletNavUtil.this.multiUserBetaEnabled = booleanValue;
                    TabletNavUtil.this.notifyUpdateListeners(TabletNav.SETTINGS);
                    TabletNavUtil.this.selectSideNavItem(TabletNav.SETTINGS, R.id.side_nav_settings_experiments);
                    return;
                }
                return;
            }
            Log.e(TabletNavUtil.TAG, "Unable to successfully retrieve preference key");
        }
    }

    public void clickSideNavItem(TabletNav tabletNav, OCSideNavItem oCSideNavItem) {
        notifyClickListeners(tabletNav, oCSideNavItem);
    }

    public void selectSideNavItem(TabletNav tabletNav, int i) {
        List<OCSideNavItem> list = this.mNavItemsMap.get(tabletNav);
        if (list != null) {
            Optional<OCSideNavItem> findFirst = list.stream().filter(new Predicate(i) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.navigation.$$Lambda$TabletNavUtil$03WM84TfOQ8Pf28cUwwPBMEZ1U */
                private final /* synthetic */ int f$0;

                {
                    this.f$0 = r1;
                }

                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return TabletNavUtil.lambda$selectSideNavItem$64(this.f$0, (OCSideNavItem) obj);
                }
            }).findFirst();
            if (findFirst.isPresent()) {
                Log.i(TAG, String.format("selectSideNavItem(%s, %s)", tabletNav.toString(), findFirst.toString()));
                this.mSelectedNavItemMap.put(tabletNav, findFirst.get());
                notifySelectListeners(tabletNav, findFirst.get());
            }
        }
    }

    static /* synthetic */ boolean lambda$selectSideNavItem$64(int i, OCSideNavItem oCSideNavItem) {
        return oCSideNavItem.getViewId().intValue() == i;
    }

    public void addClickListener(TabletNav tabletNav, TabletNavClickListener tabletNavClickListener) {
        if (!this.mClickListeners.containsKey(tabletNav)) {
            this.mClickListeners.put(tabletNav, new HashMap());
        }
        Map<TabletNavClickListener, WeakReference<TabletNavClickListener>> map = this.mClickListeners.get(tabletNav);
        if (map == null) {
            Log.e(TAG, "Unexpected null click listeners.");
        } else {
            map.put(tabletNavClickListener, new WeakReference<>(tabletNavClickListener));
        }
    }

    public void addSelectListener(TabletNav tabletNav, TabletNavSelectListener tabletNavSelectListener) {
        if (!this.mSelectListeners.containsKey(tabletNav)) {
            this.mSelectListeners.put(tabletNav, new HashMap());
        }
        Map<TabletNavSelectListener, WeakReference<TabletNavSelectListener>> map = this.mSelectListeners.get(tabletNav);
        if (map == null) {
            Log.e(TAG, "Unexpected null select listeners.");
        } else {
            map.put(tabletNavSelectListener, new WeakReference<>(tabletNavSelectListener));
        }
    }

    public void addUpdateListener(TabletNav tabletNav, TabletNavItemsUpdateListener tabletNavItemsUpdateListener) {
        if (!this.mUpdateListeners.containsKey(tabletNav)) {
            this.mUpdateListeners.put(tabletNav, new HashMap());
        }
        Map<TabletNavItemsUpdateListener, WeakReference<TabletNavItemsUpdateListener>> map = this.mUpdateListeners.get(tabletNav);
        if (map == null) {
            Log.e(TAG, "Unexpected null update listeners.");
        } else {
            map.put(tabletNavItemsUpdateListener, new WeakReference<>(tabletNavItemsUpdateListener));
        }
    }

    public void removeClickListener(TabletNavClickListener tabletNavClickListener) {
        for (Map<TabletNavClickListener, WeakReference<TabletNavClickListener>> map : this.mClickListeners.values()) {
            map.remove(tabletNavClickListener);
        }
    }

    public void removeSelectListener(TabletNavSelectListener tabletNavSelectListener) {
        for (Map<TabletNavSelectListener, WeakReference<TabletNavSelectListener>> map : this.mSelectListeners.values()) {
            map.remove(tabletNavSelectListener);
        }
    }

    public void removeUpdateListener(TabletNavItemsUpdateListener tabletNavItemsUpdateListener) {
        for (Map<TabletNavItemsUpdateListener, WeakReference<TabletNavItemsUpdateListener>> map : this.mUpdateListeners.values()) {
            map.remove(tabletNavItemsUpdateListener);
        }
    }

    private void notifyClickListeners(TabletNav tabletNav, OCSideNavItem oCSideNavItem) {
        Map<TabletNavClickListener, WeakReference<TabletNavClickListener>> map = this.mClickListeners.get(tabletNav);
        if (map != null) {
            for (WeakReference<TabletNavClickListener> weakReference : map.values()) {
                weakReference.get().onClick(oCSideNavItem);
            }
        }
    }

    private void notifySelectListeners(TabletNav tabletNav, OCSideNavItem oCSideNavItem) {
        Map<TabletNavSelectListener, WeakReference<TabletNavSelectListener>> map = this.mSelectListeners.get(tabletNav);
        if (map != null) {
            for (WeakReference<TabletNavSelectListener> weakReference : map.values()) {
                weakReference.get().onSelect(oCSideNavItem);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyUpdateListeners(TabletNav tabletNav) {
        Map<TabletNavItemsUpdateListener, WeakReference<TabletNavItemsUpdateListener>> map = this.mUpdateListeners.get(tabletNav);
        if (map != null) {
            for (WeakReference<TabletNavItemsUpdateListener> weakReference : map.values()) {
                weakReference.get().onUpdate();
            }
        }
    }
}
