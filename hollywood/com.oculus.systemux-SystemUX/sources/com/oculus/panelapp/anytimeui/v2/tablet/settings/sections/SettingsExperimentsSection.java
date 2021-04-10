package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.library.model.App;
import com.oculus.library.model.Image;
import com.oculus.os.PreferencesManager;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsInfoBox;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsList;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule;
import com.oculus.panelapp.dialog.commonsystemdialogs.social.SocialCreatePartyPreviewDialog;
import com.oculus.panelapp.social.SocialBundleConstants;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogHeroImage;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.userserver.api.OculusUserManager;
import com.oculus.userserver.api.user.OculusUser;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.HorizonUtil;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Supplier;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsExperimentsSection extends SettingsSection {
    private static final String ASSISTANT_KEY = "assistant_wakeword_enabled";
    private static final String ASSISTANT_URI = "https://support.oculus.com/2106270456509705/";
    private static final String ASSISTANT_WAKEWORD_ROUTE = "systemux://assistant/client/wakeword?enabled=";
    private static final String COUCH_CREATION_DIALOG = "settings_couch_creation_enable_tracking_modal";
    private static final String DEFAULT_POSITION_KEY = "default";
    private static final String DESK_CREATION_DIALOG = "settings_desk_creation_enable_tracking_modal";
    private static final String EXPERIMENTAL_120HZ_REFRESH_RATE_KEY = "experimental_120hz_support_enabled";
    private static final String FITNESS_PACKAGE_NAME = "com.oculus.fitnesstracker";
    private static final String FITNESS_TRACKER_OVERLAY = "fitness_tracker_overlay_enable_dialog";
    private static final String FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "force_application_focus_awareness";
    private static final int FORCE_APPLICATION_FORCE_AWARENESS_DEFAULT = 0;
    private static final String MULTIAPP_SETTING_DIALOG = "settings_multiapp_setting_reboot_device";
    private static final String MULTI_USER_BETA_ENABLED_PREF_KEY = "multi_user_beta_enabled_key";
    private static final String MULTI_USER_CANNOT_DISABLE_DIALOG = "system_utilities_experimental_section_multiuser_beta_cannot_disable_too_many_users";
    private static final boolean OVERLAY_BROWSER_EXPERIMENT_DEFAULT = false;
    private static final String OVERLAY_BROWSER_EXPERIMENT_KEY = "debug_overlay_browser_experiment";
    private static final String OVERLAY_DEFAULT_POSITION = "top";
    private static final Map<String, Integer> OVERLAY_POSITIONS = new HashMap<String, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsExperimentsSection.AnonymousClass3 */

        {
            put("top", Integer.valueOf(R.string.settings_overlay_position_top_text));
            put("bottom", Integer.valueOf(R.string.settings_overlay_position_bottom_text));
        }
    };
    private static final String RESET_EXPERIMENTS_DIALOG = "settings_experimental_reset_all";
    private static final String SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY = "system_force_application_focus_awareness";
    private static final String SYSTEM_THEME_DIALOG = "settings_system_theme_setting_reboot_device";
    private static final Map<Integer, Integer> SYSTEM_THEME_OPTIONS = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsExperimentsSection.AnonymousClass1 */

        {
            put(2, Integer.valueOf(R.string.settings_system_theme_dark_text));
            put(1, Integer.valueOf(R.string.settings_system_theme_light_text));
        }
    };
    private static final String TAG = LoggingUtil.tag(SettingsExperimentsSection.class);
    private static final int USER_PREFS_NONE = 0;
    private static final int USER_PREFS_OPT_IN = 1;
    private static final int USER_PREFS_OPT_OUT = 2;
    private static final Map<Integer, Integer> mPointerFilterSettings = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsExperimentsSection.AnonymousClass2 */

        {
            put(0, Integer.valueOf(R.string.settings_pointer_filter_default_text));
            put(1, Integer.valueOf(R.string.settings_pointer_filter_smooth_text));
            put(2, Integer.valueOf(R.string.settings_pointer_filter_performant_text));
            put(3, Integer.valueOf(R.string.settings_pointer_filter_tight_text));
        }
    };
    private final Context mContext;
    private OculusUser mCurrentUser;
    private boolean mIsCouchCreationQueued = false;
    private boolean mIsDeskCreationQueued = false;
    private boolean mIsMultiUserBetaEnabled = false;
    private boolean mIsStageActive = false;
    private boolean mIsTrackingEnabled = false;
    private boolean mMoreThanOneUser = false;
    private OculusUserManager mOculusUserManager;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final PowerManager mPowerManager;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private int mQuickbootItemIndex = -1;
    private final Runnable mRefreshView;
    private List<BaseSettingsItem.Builder> mSettings;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private int mStationaryGuardianIndex = -1;
    private boolean mStationaryGuardianV2Enabled = false;

    public SettingsExperimentsSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getString(R.string.anytime_tablet_settings_nav_experiments_v2), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mPowerManager = (PowerManager) this.mContext.getSystemService("power");
        this.mRefreshView = runnable;
        this.mSettings = new ArrayList();
        AndroidSettingsViewModel acquireAndroidSettingsViewModel = anytimeUIAndroidPanelAppV2.acquireAndroidSettingsViewModel();
        this.mSettings.add(new SettingsInfoBox.Builder(anytimeUIAndroidPanelAppV2).withInfo(R.string.settings_experiments_disclaimer_text));
        addResetAllButton();
        this.mSettings.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_experiments_header_text));
        addSystemThemeDropdown(acquireAndroidSettingsViewModel);
        addForceOverlaysToggle();
        addOverlayBrowserExperimentToggle();
        addQuickbootToggle();
        addStationaryGuardianToggle();
        addFitnessTracker(acquireAndroidSettingsViewModel);
        addAssistantWakeWordToggle();
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 10);
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(10, new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$Lh7yBDfICdlm_FYGle0DHVsx6p0 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsExperimentsSection.lambda$Lh7yBDfICdlm_FYGle0DHVsx6p0(SettingsExperimentsSection.this, f);
            }
        });
        addCouchCreationButton();
        addDeskCreationButton();
        addIntrusionDetectionSection();
        addPassthroughKeyboardSection();
        addBluetoothPairingButton();
        addMultiApp();
        addMultiUser();
        addPointerFilter();
        addTrackedKeyboardSection();
        addBluetoothMouseSection();
        add120hzExperimentalToggle();
        addSettingsItems(this.mSettings);
        fetchMultiUserPrefs();
    }

    private void addResetAllButton() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("reset_experiments_to_default").withTitle(R.string.settings_reset_experiments_item_title).withSubtitle(R.string.settings_reset_experiments_item_subtitle).withIcon(R.drawable.oc_icon_experiments_filled_24_d2d2d2).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_reset_experiments_button_text).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$mC5sIEvyLFV5FJ9CNt24ceEYvTU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsExperimentsSection.this.lambda$addResetAllButton$369$SettingsExperimentsSection();
            }
        })));
    }

    /* access modifiers changed from: private */
    /* renamed from: resetAllExperiments */
    public void lambda$addResetAllButton$369$SettingsExperimentsSection() {
        DialogDefinitionCustom rebootDialog = getRebootDialog(RESET_EXPERIMENTS_DIALOG);
        rebootDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$j6bKvNwGdaZdiFh5BIzaOuooQ0 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsExperimentsSection.this.lambda$resetAllExperiments$370$SettingsExperimentsSection(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(rebootDialog);
    }

    public /* synthetic */ boolean lambda$resetAllExperiments$370$SettingsExperimentsSection(DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        this.mSettingsManager.setBoolean(SettingsManager.CONTROLLER_LASER_BEAM_EFFECTS, false);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.MULTIAPP_PUBLIC) && !this.mPanelApp.isGKEnabled(Gatekeeper.MULTIAPP_ROLLOUT)) {
            this.mSettingsManager.setInt(SettingsManager.MULTI_APP_OPT_IN, 0);
        }
        if (this.mPanelApp.isGKEnabled(Gatekeeper.POINTER_POSE_FILTER_TUNING)) {
            this.mSettingsManager.setInt(SettingsManager.POINTER_FILTER_SELECTION, 0);
        }
        if (this.mMoreThanOneUser) {
            showCannotDisableWarning();
        } else {
            this.mPreferencesManager.set(MULTI_USER_BETA_ENABLED_PREF_KEY, false);
        }
        this.mPreferencesManager.set(EXPERIMENTAL_120HZ_REFRESH_RATE_KEY, false);
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 21, 0.0f);
        this.mPreferencesManager.set(FORCE_APPLICATION_FOCUS_AWARENESS_KEY, 0);
        this.mPreferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, 0);
        this.mPreferencesManager.set(OVERLAY_BROWSER_EXPERIMENT_KEY, false);
        if (this.mPanelApp.isGKEnabled(Gatekeeper.SYSTEM_THEME_SETTING)) {
            setSystemTheme(2);
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CLEAR_SURFACES, "");
        this.mPowerManager.reboot(null);
        return true;
    }

    private void addForceOverlaysToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("force_overlays").withTitle(R.string.settings_force_overlays_item_title).withSubtitle(R.string.settings_force_overlays_item_subtitle).withIcon(R.drawable.oc_icon_universal_menu_filled_24_d2d2d2).withGatekeepers(Gatekeeper.FORCE_OVERLAYS).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$10BWZBKAzowbX3VxmSy80DEGGc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addForceOverlaysToggle$371$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$hgzcf9d0dIRBx74gCWYKd15dA0 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addForceOverlaysToggle$372$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addForceOverlaysToggle$371$SettingsExperimentsSection() {
        Pair<Boolean, Integer> integer = this.mPreferencesManager.getInteger(FORCE_APPLICATION_FOCUS_AWARENESS_KEY);
        if (!((Boolean) integer.first).booleanValue()) {
            return false;
        }
        if (((Integer) integer.second).intValue() == 1) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ void lambda$addForceOverlaysToggle$372$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mPreferencesManager.set(FORCE_APPLICATION_FOCUS_AWARENESS_KEY, z ? 1 : 0);
        this.mPreferencesManager.set(SYSTEM_FORCE_APPLICATION_FOCUS_AWARENESS_KEY, (int) z);
    }

    private void addOverlayBrowserExperimentToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("browser_in_overlay").withTitle(R.string.settings_overlay_browser_experiment_title).withSubtitle(R.string.settings_overlay_browser_experiment_subtitle).withIcon(R.drawable.oc_icon_universal_menu_filled_24_d2d2d2).withGatekeepers(Gatekeeper.OVERLAY_BROWSER_EXPERIMENT).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$FJ4obQdCLx2C4LnPTCIGwGqYGJs */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addOverlayBrowserExperimentToggle$373$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$ByIsaU1bopjvBT5TqDXe7V0jh98 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addOverlayBrowserExperimentToggle$374$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addOverlayBrowserExperimentToggle$373$SettingsExperimentsSection() {
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean(FORCE_APPLICATION_FOCUS_AWARENESS_KEY);
        if (((Boolean) pair.first).booleanValue()) {
            return ((Boolean) pair.second).booleanValue();
        }
        return false;
    }

    public /* synthetic */ void lambda$addOverlayBrowserExperimentToggle$374$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mPreferencesManager.set(OVERLAY_BROWSER_EXPERIMENT_KEY, z);
    }

    private void addSystemThemeDropdown(AndroidSettingsViewModel androidSettingsViewModel) {
        int systemTheme = getSystemTheme();
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("system-theme").withTitle(R.string.settings_system_theme_item_title).withGatekeepers(Gatekeeper.SYSTEM_THEME_SETTING).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(androidSettingsViewModel).withItems(SYSTEM_THEME_OPTIONS.keySet().toArray(new Integer[SYSTEM_THEME_OPTIONS.keySet().size()])).withSelectedItem(Integer.valueOf(systemTheme)).withTitleMap(SYSTEM_THEME_OPTIONS).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$bZ2Vx3P7EzBupDTl8bDEwvFXi8w */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsExperimentsSection.lambda$bZ2Vx3P7EzBupDTl8bDEwvFXi8w(SettingsExperimentsSection.this, ((Integer) obj).intValue());
            }
        })));
    }

    private static int coerceUiMode(int i) {
        if (i == 1 || i == 2) {
            return i;
        }
        String str = TAG;
        Log.d(str, "Unsupported system ui mode: " + i);
        return 2;
    }

    private int getSystemTheme() {
        try {
            int nightMode = ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).getNightMode();
            String str = TAG;
            Log.d(str, "Got system ui mode: " + nightMode);
            return coerceUiMode(nightMode);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Failed to get system ui mode settings from the OS: " + e.getMessage());
            return 2;
        }
    }

    private boolean setSystemTheme(int i) {
        try {
            String str = TAG;
            Log.d(str, "Setting system ui mode: " + i);
            ((UiModeManager) this.mContext.getSystemService(UiModeManager.class)).setNightMode(coerceUiMode(i));
            return true;
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Failed to set system ui mode setting: " + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: private */
    public boolean confirmSystemTheme(int i) {
        if (i == getSystemTheme()) {
            return true;
        }
        DialogDefinitionCustom rebootDialog = getRebootDialog(SYSTEM_THEME_DIALOG);
        rebootDialog.setDialogResultHandler(new DialogResultHandler(i) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$oPMsUb2iSsFVCxcnrG41aUqaWJo */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsExperimentsSection.this.lambda$confirmSystemTheme$375$SettingsExperimentsSection(this.f$1, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(rebootDialog);
        return false;
    }

    public /* synthetic */ boolean lambda$confirmSystemTheme$375$SettingsExperimentsSection(int i, DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        setSystemTheme(i);
        this.mPowerManager.reboot(null);
        return true;
    }

    private void addQuickbootToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("quickboot").withTitle(R.string.settings_quickboot_item_title).withSubtitle(R.string.settings_quickboot_item_subtitle).withIcon(R.drawable.oc_icon_pass_through_filled_24_d2d2d2).withGatekeepers(Gatekeeper.QUICKBOOT).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$DfOJr9ewgYYiwUozA8PBeBsOjII */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addQuickbootToggle$376$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$u6C6juIweuiwJTnhCqBaU1SQhgw */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addQuickbootToggle$377$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
        this.mQuickbootItemIndex = this.mSettings.size() - 1;
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(23, new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$fUaIOPG8Gqt_aykPMJfkRN8hvC8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsExperimentsSection.lambda$fUaIOPG8Gqt_aykPMJfkRN8hvC8(SettingsExperimentsSection.this, f);
            }
        });
    }

    public /* synthetic */ boolean lambda$addQuickbootToggle$376$SettingsExperimentsSection() {
        return GuardianModule.getGuardianConfigValue(this.mPanelApp, 23);
    }

    public /* synthetic */ void lambda$addQuickbootToggle$377$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 23, z ? 1.0f : 0.0f);
    }

    /* access modifiers changed from: private */
    public void modifyQuickbootToggle(float f) {
        boolean z = ((double) f) == 1.0d;
        SettingsList settingsList = getSettingsList();
        if (this.mQuickbootItemIndex != -1 && settingsList != null) {
            List<BaseSettingsItem> settingsItems = settingsList.getSettingsItems();
            if (this.mQuickbootItemIndex < settingsItems.size()) {
                ((SettingsToggleActionType) ((SettingsItem) settingsItems.get(this.mQuickbootItemIndex)).getActionType()).asyncSetValue(z);
            }
        }
    }

    private void addFitnessTracker(AndroidSettingsViewModel androidSettingsViewModel) {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("fitness_tracker_overlay_enable").withTitle(R.string.settings_oculus_move_overlay_item_title).withSubtitle(R.string.settings_oculus_move_overlay_item_subtitle).withIcon(R.drawable.oc_icon_widget_filled_24_d2d2d2).withGatekeepers(Gatekeeper.FITNESS_TRACKER).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$oAE9ZCn_yOruLRA3buLDamhoCSA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addFitnessTracker$378$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$97sCm7SQSveqUncUrXZluSaYPyA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addFitnessTracker$379$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
        String overlayPosition = getOverlayPosition();
        List<BaseSettingsItem.Builder> list = this.mSettings;
        SettingsItem.Builder withVisibilityCondition = new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("fitness_tracker_overlay_position").withTitle(R.string.settings_overlay_position_item_title).withSubtitle(R.string.settings_overlay_position_item_subtitle).withIcon(R.drawable.oc_icon_move_filled_24_d2d2d2).withGatekeepers(Gatekeeper.FITNESS_TRACKER).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$W6hMgGQcwH_ahvHpPfuy48OceFM */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsExperimentsSection.this.lambda$addFitnessTracker$380$SettingsExperimentsSection();
            }
        });
        SettingsDropdownActionType.Builder withItems = new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(androidSettingsViewModel).withItems(OVERLAY_POSITIONS.keySet().toArray(new String[OVERLAY_POSITIONS.keySet().size()]));
        if (overlayPosition == null) {
            overlayPosition = OVERLAY_DEFAULT_POSITION;
        }
        list.add(withVisibilityCondition.withSettingsActionType(withItems.withSelectedItem(overlayPosition).withTitleMap(OVERLAY_POSITIONS).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$jHyu6kexXrZQ_QfK9F0DsHl2lnA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsExperimentsSection.this.lambda$addFitnessTracker$381$SettingsExperimentsSection((String) obj);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addFitnessTracker$378$SettingsExperimentsSection() {
        if (!this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_ENABLED, false) || !this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_OVERLAY_ENABLED, false)) {
            return false;
        }
        return true;
    }

    public /* synthetic */ Boolean lambda$addFitnessTracker$380$SettingsExperimentsSection() {
        boolean z = false;
        if (this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_ENABLED, false) && this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_OVERLAY_ENABLED, false)) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public /* synthetic */ boolean lambda$addFitnessTracker$381$SettingsExperimentsSection(String str) {
        confirmSelectedPosition(str);
        return true;
    }

    private void confirmSelectedPosition(String str) {
        if (str != null) {
            this.mSettingsManager.setString(SettingsManager.FITNESS_TRACKING_OVERLAY_POSITION, getUpdatedOverlayPosition(str));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: onToggleFitnessTracker */
    public void lambda$addFitnessTracker$379$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        if (!this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_ENABLED, false)) {
            DialogDefinitionCustom fitnessOverlayDialog = getFitnessOverlayDialog();
            fitnessOverlayDialog.setDialogResultHandler(new DialogResultHandler(settingsToggleActionType) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$efJE8hbTAGpyfEA6LKA9snOM2u8 */
                private final /* synthetic */ SettingsToggleActionType f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsExperimentsSection.this.lambda$onToggleFitnessTracker$382$SettingsExperimentsSection(this.f$1, dialogResult);
                }
            });
            this.mPanelApp.getDialogManager().showDialog(fitnessOverlayDialog);
        }
        boolean z2 = !this.mSettingsManager.getBoolean(SettingsManager.FITNESS_TRACKING_OVERLAY_ENABLED, false);
        if (z2 && getOverlayPosition() == null) {
            this.mSettingsManager.setString(SettingsManager.FITNESS_TRACKING_OVERLAY_POSITION, getUpdatedOverlayPosition("top"));
        }
        this.mSettingsManager.setBoolean(SettingsManager.FITNESS_TRACKING_OVERLAY_ENABLED, z2);
        this.mRefreshView.run();
    }

    public /* synthetic */ boolean lambda$onToggleFitnessTracker$382$SettingsExperimentsSection(SettingsToggleActionType settingsToggleActionType, DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            this.mPanelApp.actionNavigate(SystemUXRoute.FITNESS_TRACKER, "/settings");
            return true;
        }
        settingsToggleActionType.refreshToggle();
        return true;
    }

    private String getOverlayPosition() {
        String string = this.mSettingsManager.getString(SettingsManager.FITNESS_TRACKING_OVERLAY_POSITION, "");
        if (string == null || TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONObject(string).getString(DEFAULT_POSITION_KEY);
        } catch (JSONException unused) {
            Log.e(TAG, "JSONException in getOverlayPosition");
            return "";
        }
    }

    private String getUpdatedOverlayPosition(String str) {
        JSONObject jSONObject;
        String string = this.mSettingsManager.getString(SettingsManager.FITNESS_TRACKING_OVERLAY_POSITION, "");
        try {
            if (TextUtils.isEmpty(string)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(string);
            }
            jSONObject.put(DEFAULT_POSITION_KEY, str);
            return jSONObject.toString();
        } catch (JSONException unused) {
            Log.e(TAG, "JSONException in getUpdatedOverlayPosition");
            return "";
        }
    }

    private DialogDefinitionCustom getFitnessOverlayDialog() {
        Resources resources = this.mContext.getResources();
        App application = HorizonUtil.getApplication(this.mContext, "com.oculus.fitnesstracker");
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(FITNESS_TRACKER_OVERLAY, resources.getString(R.string.settings_oculus_move_item_title), resources.getString(R.string.settings_oculus_move_item_subtitle));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_oculus_move_dialog_cancel)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_oculus_move_dialog_confirm)));
        if (application != null) {
            dialogDefinitionCustom.setHeroImage(new DialogHeroImage(application.images.get(Image.ImageName.SOURCE_MAIN).uri, 1.778f, SocialCreatePartyPreviewDialog.HERO_IMAGE_BACKGROUND_COLOR));
        }
        return dialogDefinitionCustom;
    }

    private void add120hzExperimentalToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("120hz-refresh-rate").withTitle(R.string.settings_120hz_refresh_rate_item_title).withSubtitle(R.string.settings_120hz_refresh_rate_item_subtitle).withIcon(R.drawable.oc_icon_frequency_filled_24_d2d2d2).withGatekeepers(Gatekeeper.EXPERIMENTAL_120HZ_REFRESH_RATE).withVisibilityCondition($$Lambda$fa5zXtkftiutCc_S9u0ycE1vZl0.INSTANCE).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$HxGDRXooWUC2VnHhbfhl6ukA9AI */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$add120hzExperimentalToggle$383$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$xuYzYDmZr1r_qAi4ZK_ViwMme2E */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$add120hzExperimentalToggle$384$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$add120hzExperimentalToggle$383$SettingsExperimentsSection() {
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean(EXPERIMENTAL_120HZ_REFRESH_RATE_KEY);
        return ((Boolean) pair.first).booleanValue() && ((Boolean) pair.second).booleanValue();
    }

    public /* synthetic */ void lambda$add120hzExperimentalToggle$384$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mPreferencesManager.set(EXPERIMENTAL_120HZ_REFRESH_RATE_KEY, z);
        settingsToggleActionType.refreshToggle();
    }

    private void addAssistantWakeWordToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("assistant_wakeword_enabled").withTitle(R.string.settings_voice_assistant_item_title).withSubtitle(R.string.settings_voice_assistant_item_subtitle).withSubtitleUri(ASSISTANT_URI).withIcon(R.drawable.oc_icon_voice_assistant_filled_24_d2d2d2).withGatekeepers(Gatekeeper.ASSISTANT_WAKEWORD).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$6c3WzqzSfKBbnOPSgRdErvpRmdY */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addAssistantWakeWordToggle$385$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$PrViiyKbngm7XSW1PeODEqA3HM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addAssistantWakeWordToggle$386$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addAssistantWakeWordToggle$385$SettingsExperimentsSection() {
        return this.mSettingsManager.getBoolean("assistant_wakeword_enabled", false);
    }

    public /* synthetic */ void lambda$addAssistantWakeWordToggle$386$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mSettingsManager.setBoolean("assistant_wakeword_enabled", z);
        AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
        StringBuilder sb = new StringBuilder();
        sb.append(ASSISTANT_WAKEWORD_ROUTE);
        sb.append(z ? SocialBundleConstants.FB_UPSELL_MUST_INTERACT : "false");
        anytimeUIAndroidPanelAppV2.actionNavigate(sb.toString(), "");
        settingsToggleActionType.refreshToggle();
    }

    private void addCouchCreationButton() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("couch_creation").withTitle(R.string.settings_couch_creation_item_title).withSubtitle(R.string.settings_couch_creation_item_subtitle).withIcon(R.drawable.oc_icon_couch_filled_24_d2d2d2).withGatekeepers(Gatekeeper.COUCH_CREATION).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_couch_creation_button_text).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$SLGwNHFeST9JIdSCFdOZHHycnU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsExperimentsSection.this.lambda$addCouchCreationButton$388$SettingsExperimentsSection();
            }
        })));
    }

    public /* synthetic */ void lambda$addCouchCreationButton$388$SettingsExperimentsSection() {
        if (this.mIsTrackingEnabled) {
            this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CREATE_SURFACE, "");
            return;
        }
        DialogDefinitionCustom enableTrackingDialog = getEnableTrackingDialog(COUCH_CREATION_DIALOG);
        enableTrackingDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$PKkEj_U1pcVevXYtvDIevSdk8 */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsExperimentsSection.this.lambda$null$387$SettingsExperimentsSection(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(enableTrackingDialog);
    }

    public /* synthetic */ boolean lambda$null$387$SettingsExperimentsSection(DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            GuardianModule.setGuardianConfigValue(this.mPanelApp, 10, 0.0f);
            this.mIsCouchCreationQueued = true;
        }
        return true;
    }

    private void addDeskCreationButton() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("desk_creation").withTitle(R.string.settings_desk_creation_item_title).withSubtitle(R.string.settings_desk_creation_item_subtitle).withIcon(R.drawable.oc_icon_table_filled_24_d2d2d2).withGatekeepers(Gatekeeper.DESK_CREATION).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_desk_creation_button_text).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$klYSYXEvDEgKxoo9K4_e_9bN6Js */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsExperimentsSection.this.lambda$addDeskCreationButton$390$SettingsExperimentsSection();
            }
        })));
    }

    public /* synthetic */ void lambda$addDeskCreationButton$390$SettingsExperimentsSection() {
        if (this.mIsTrackingEnabled) {
            this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CREATE_DESK, "");
            return;
        }
        DialogDefinitionCustom enableTrackingDialog = getEnableTrackingDialog(DESK_CREATION_DIALOG);
        enableTrackingDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$sXyy79p_VHJbM7gLRrV1LqDC7A */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsExperimentsSection.this.lambda$null$389$SettingsExperimentsSection(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(enableTrackingDialog);
    }

    public /* synthetic */ boolean lambda$null$389$SettingsExperimentsSection(DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            GuardianModule.setGuardianConfigValue(this.mPanelApp, 10, 0.0f);
            this.mIsDeskCreationQueued = true;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void modifyTrackingEnabled(float f) {
        this.mIsTrackingEnabled = f == 0.0f;
        if (this.mIsTrackingEnabled && this.mIsCouchCreationQueued) {
            this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CREATE_SURFACE, "");
            this.mIsCouchCreationQueued = false;
        }
        if (this.mIsTrackingEnabled && this.mIsDeskCreationQueued) {
            this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CREATE_DESK, "");
            this.mIsDeskCreationQueued = false;
        }
    }

    private DialogDefinitionCustom getEnableTrackingDialog(String str) {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(str, resources.getString(R.string.settings_surface_creation_dialog_title), resources.getString(R.string.settings_surface_creation_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_surface_creation_dialog_continue)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_surface_creation_dialog_confirm)));
        return dialogDefinitionCustom;
    }

    private void addStationaryGuardianToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("stationary-guardian-v2").withTitle(R.string.settings_stationary_boundary_passthrough_item_title).withSubtitle(R.string.settings_stationary_boundary_passthrough_item_subtitle).withIcon(R.drawable.oc_icon_stationary_filled_24_d2d2d2).withGatekeepers(Gatekeeper.STATIONARY_GUARDIAN_V2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$9Ig7m2vDutKoKwz_bXQB9P94534 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addStationaryGuardianToggle$391$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$dVuwUb4wf2nujCQYx_3qf60SJ8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addStationaryGuardianToggle$392$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
        this.mStationaryGuardianIndex = this.mSettings.size() - 1;
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(21, new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$46oFBb9XFNiBob8PE8IEi4p4uGo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsExperimentsSection.lambda$46oFBb9XFNiBob8PE8IEi4p4uGo(SettingsExperimentsSection.this, f);
            }
        });
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(22, new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$mhEAbeSCem9_ibGE9heTS8xgSTs */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsExperimentsSection.lambda$mhEAbeSCem9_ibGE9heTS8xgSTs(SettingsExperimentsSection.this, f);
            }
        });
    }

    public /* synthetic */ boolean lambda$addStationaryGuardianToggle$391$SettingsExperimentsSection() {
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 21);
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 22);
        return false;
    }

    public /* synthetic */ void lambda$addStationaryGuardianToggle$392$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        float f = 1.0f;
        if (!this.mIsStageActive) {
            if (z) {
                this.mPanelApp.actionNavigate(SystemUXRoute.STATIONARY_GUARDIAN_V2_NUX, "");
            }
            AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2 = this.mPanelApp;
            if (!z) {
                f = 0.0f;
            }
            GuardianModule.setGuardianConfigValue(anytimeUIAndroidPanelAppV2, 21, f);
            return;
        }
        if (z) {
            this.mPanelApp.actionNavigate(SystemUXRoute.STATIONARY_GUARDIAN_V2_NUX_USING_ROOMSCALE, "");
        } else {
            AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV22 = this.mPanelApp;
            if (!z) {
                f = 0.0f;
            }
            GuardianModule.setGuardianConfigValue(anytimeUIAndroidPanelAppV22, 21, f);
        }
        settingsToggleActionType.refreshToggle();
    }

    /* access modifiers changed from: private */
    public void modifyIsStageActive(float f) {
        this.mIsStageActive = ((double) f) == 1.0d;
    }

    /* access modifiers changed from: private */
    public void modifyStationaryGuardianToggle(float f) {
        this.mStationaryGuardianV2Enabled = ((double) f) == 1.0d;
        SettingsList settingsList = getSettingsList();
        if (this.mStationaryGuardianIndex != -1 && settingsList != null) {
            List<BaseSettingsItem> settingsItems = settingsList.getSettingsItems();
            if (this.mStationaryGuardianIndex < settingsItems.size()) {
                ((SettingsToggleActionType) ((SettingsItem) settingsItems.get(this.mStationaryGuardianIndex)).getActionType()).asyncSetValue(this.mStationaryGuardianV2Enabled);
            }
        }
    }

    private void addPassthroughKeyboardSection() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("passthrough_keyboard_subsection").withTitle(R.string.settings_passthrough_keyboard_section_item_title).withSubtitle(R.string.settings_passthrough_keyboard_section_item_subtitle).withIcon(R.drawable.oc_icon_keyboard_filled_24_d2d2d2).withGatekeepers(Gatekeeper.PASSTHROUGH_PORTAL).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_PASSTHROUGH_PORTAL_URI).isInternal(true)));
    }

    private void addBluetoothPairingButton() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("bluetooth_pairing").withTitle(R.string.settings_bluetooth_pairing_title).withSubtitle(R.string.settings_bluetooth_pairing_subtitle).withIcon(R.drawable.oc_icon_bluetooth_filled_24_d2d2d2).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_bluetooth_pairing_button_text).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$djmtu6fpsCKJC07q02Va19tPxTQ */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsExperimentsSection.this.lambda$addBluetoothPairingButton$393$SettingsExperimentsSection();
            }
        })));
    }

    public /* synthetic */ void lambda$addBluetoothPairingButton$393$SettingsExperimentsSection() {
        this.mPanelApp.actionNavigate(SystemUXRoute.BLUETOOTH, "");
    }

    private void addMultiApp() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("multi-app").withTitle(R.string.settings_multi_tasking_title).withSubtitle(R.string.settings_multi_tasking_subtitle).withIcon(R.drawable.oc_icon_multi_browser_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$CWsjmOXO2kjnHBGKprepnWY0xg */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsExperimentsSection.this.lambda$addMultiApp$394$SettingsExperimentsSection();
            }
        }).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$ZOZocG__7x_OBfu1P7uEjmCsO_M */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addMultiApp$395$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$7H7aEMz5f_cCsgNDU0r0yTbizjY */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addMultiApp$396$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ Boolean lambda$addMultiApp$394$SettingsExperimentsSection() {
        return Boolean.valueOf(this.mPanelApp.isGKEnabled(Gatekeeper.MULTIAPP_PUBLIC) && !this.mPanelApp.isGKEnabled(Gatekeeper.MULTIAPP_ROLLOUT));
    }

    public /* synthetic */ boolean lambda$addMultiApp$395$SettingsExperimentsSection() {
        return convertMultiAppPrefToBoolean(this.mSettingsManager.getInt(SettingsManager.MULTI_APP_OPT_IN, 0));
    }

    private void fetchMultiUserPrefs() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$yuK3ujkGpZL8dFXJMoVu7qstmAU */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SettingsExperimentsSection.this.lambda$fetchMultiUserPrefs$398$SettingsExperimentsSection();
            }
        });
    }

    public /* synthetic */ Object lambda$fetchMultiUserPrefs$398$SettingsExperimentsSection() throws Exception {
        try {
            this.mOculusUserManager = new OculusUserManager(this.mContext);
            this.mCurrentUser = this.mOculusUserManager.getSelf();
            OculusUserManager oculusUserManager = this.mOculusUserManager;
            this.mIsMultiUserBetaEnabled = OculusUserManager.isMultiUserEnabled();
            boolean z = true;
            if (this.mOculusUserManager.getUsers().size() <= 1) {
                z = false;
            }
            this.mMoreThanOneUser = z;
            UiThreadExecutor.getInstance().execute(new Runnable() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$dRO4Q0RGTAg1e5nvkM7JT8uK28 */

                public final void run() {
                    SettingsExperimentsSection.this.lambda$null$397$SettingsExperimentsSection();
                }
            });
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error initializing OculusUserManager", e);
            return null;
        }
    }

    public /* synthetic */ void lambda$null$397$SettingsExperimentsSection() {
        this.mRefreshView.run();
    }

    private void addMultiUser() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("multi_user").withTitle(R.string.settings_multi_user_beta_title).withSubtitle(R.string.settings_multi_user_beta_subtitle).withIcon(R.drawable.oc_icon_friends_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$1pd9IcgTbJtzgm9Ofj6W9D9uo */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsExperimentsSection.this.lambda$addMultiUser$399$SettingsExperimentsSection();
            }
        }).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$bZQ9VhfPpm1BfNwkTVDLghlNmMo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsExperimentsSection.this.lambda$addMultiUser$400$SettingsExperimentsSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$vpnEQjSbBJLkre4iGgIK3dTrFc8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsExperimentsSection.this.lambda$addMultiUser$401$SettingsExperimentsSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ Boolean lambda$addMultiUser$399$SettingsExperimentsSection() {
        try {
            return Boolean.valueOf(this.mCurrentUser != null && this.mCurrentUser.getUserId() == 0 && this.mIsMultiUserBetaEnabled);
        } catch (Exception e) {
            Log.e(TAG, "Error getting multi user toggle visibility", e);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: handleMultiUserChange */
    public void lambda$addMultiUser$401$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        if (!this.mMoreThanOneUser || !lambda$addMultiUser$400$SettingsExperimentsSection()) {
            this.mPreferencesManager.set(MULTI_USER_BETA_ENABLED_PREF_KEY, z);
            return;
        }
        showCannotDisableWarning();
        settingsToggleActionType.refreshToggle();
    }

    /* access modifiers changed from: private */
    /* renamed from: isMultiUserEnabled */
    public boolean lambda$addMultiUser$400$SettingsExperimentsSection() {
        Pair<Boolean, Boolean> pair = this.mPreferencesManager.getBoolean(MULTI_USER_BETA_ENABLED_PREF_KEY);
        if (((Boolean) pair.first).booleanValue()) {
            return ((Boolean) pair.second).booleanValue();
        }
        return false;
    }

    private void showCannotDisableWarning() {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(MULTI_USER_CANNOT_DISABLE_DIALOG, resources.getString(R.string.settings_multi_user_cannot_disable_dialog_title), resources.getString(R.string.settings_multi_user_cannot_disable_dialog_body));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("close", resources.getString(R.string.settings_multi_user_cannot_disable_dialog_dismiss)));
        this.mPanelApp.getDialogManager().showDialog(dialogDefinitionCustom);
    }

    private boolean convertMultiAppPrefToBoolean(int i) {
        if (i == 1) {
            return true;
        }
        if (!this.mPanelApp.isGKEnabled(Gatekeeper.MULTIAPP_ROLLOUT) || i == 2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: confirmMultiAppChange */
    public void lambda$addMultiApp$396$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        DialogDefinitionCustom rebootDialog = getRebootDialog(MULTIAPP_SETTING_DIALOG);
        rebootDialog.setDialogResultHandler(new DialogResultHandler(z, settingsToggleActionType) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$Zt2eKwj8kFBGVzrnMgn_A1lj2xw */
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ SettingsToggleActionType f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsExperimentsSection.this.lambda$confirmMultiAppChange$402$SettingsExperimentsSection(this.f$1, this.f$2, dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(rebootDialog);
    }

    public /* synthetic */ boolean lambda$confirmMultiAppChange$402$SettingsExperimentsSection(boolean z, SettingsToggleActionType settingsToggleActionType, DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            this.mSettingsManager.setInt(SettingsManager.MULTI_APP_OPT_IN, z ? 1 : 2);
            this.mPowerManager.reboot(null);
        } else {
            settingsToggleActionType.refreshToggle();
        }
        return true;
    }

    private DialogDefinitionCustom getRebootDialog(String str) {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(str, resources.getString(R.string.settings_confirm_reboot_dialog_title), resources.getString(R.string.settings_confirm_reboot_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_confirm_reboot_dialog_cancel_button)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_confirm_reboot_dialog_restart_button)));
        return dialogDefinitionCustom;
    }

    private void addPointerFilter() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(SettingsManager.POINTER_FILTER_SELECTION).withTitle(R.string.settings_pointer_filter_item_title).withGatekeepers(Gatekeeper.POINTER_POSE_FILTER_TUNING).withSettingsActionType(new SettingsDropdownActionType.Builder().withItems(mPointerFilterSettings.keySet().toArray(new Integer[mPointerFilterSettings.keySet().size()])).withSelectedItem(Integer.valueOf(this.mSettingsManager.getInt(SettingsManager.POINTER_FILTER_SELECTION, 0))).withTitleMap(mPointerFilterSettings).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsExperimentsSection$8IpQB1pIgIWyc5pNoSXb7bUMUcQ */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsExperimentsSection.this.lambda$addPointerFilter$403$SettingsExperimentsSection((Integer) obj);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addPointerFilter$403$SettingsExperimentsSection(Integer num) {
        this.mSettingsManager.setInt(SettingsManager.POINTER_FILTER_SELECTION, num.intValue());
        return true;
    }

    private void addTrackedKeyboardSection() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("tracked-keyboard").withTitle(R.string.settings_tracked_keyboard_section_item_title).withSubtitle(R.string.settings_tracked_keyboard_section_item_subtitle).withIcon(R.drawable.oc_icon_keyboard_filled_24_d2d2d2).withGatekeepers(Gatekeeper.TRACKED_KEYBOARD_SECTION).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_TRACKED_KEYBOARD_URI).isInternal(true)));
    }

    private void addBluetoothMouseSection() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("bluetooth_mouse_subsection").withTitle(R.string.settings_bluetooth_mouse_section_item_title).withSubtitle(R.string.settings_bluetooth_mouse_section_item_subtitle).withIcon(R.drawable.oc_icon_mouse_filled_24_d2d2d2).withGatekeepers(Gatekeeper.BLUETOOTH_MOUSE_SECTION).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_BLUETOOTH_MOUSE_URI).isInternal(true)));
    }

    private void addIntrusionDetectionSection() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("intrusion_detection").withTitle(R.string.settings_intrusion_detection_section_item_title).withSubtitle(R.string.settings_intrusion_detection_section_item_subtitle).withIcon(R.drawable.oc_icon_guardian_intrusion_detection_filled_24_d2d2d2).withGatekeepers(Gatekeeper.INTRUSION_DETECTION).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_INTRUSION_DETECTION_URI).isInternal(true)));
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
