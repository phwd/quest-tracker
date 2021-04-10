package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.res.Resources;
import android.util.SparseIntArray;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsList;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.vrshell.SystemUXRoute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsGuardianSection extends SettingsSection {
    private static final String CLEAR_GUARDIAN_HISTORY = "settings_clear_guardian_history";
    private static final int DEFAULT_BOUNDARY_COLOR = 3;
    private static final int NOT_FOUND = -1;
    private static final float SIMPLE_GUARDIAN_CONFIG = 0.0f;
    private static final Map<Integer, Integer> mBoundaryColorIcons = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsGuardianSection.AnonymousClass2 */

        {
            put(1, Integer.valueOf(R.drawable.oc_icon_color_icon_yellow_filled_24_d2d2d2));
            put(2, Integer.valueOf(R.drawable.oc_icon_color_icon_purple_filled_24_d2d2d2));
            put(3, Integer.valueOf(R.drawable.oc_icon_color_icon_teal_filled_24_d2d2d2));
        }
    };
    private static final Map<Integer, Integer> mBoundaryColors = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsGuardianSection.AnonymousClass1 */

        {
            put(1, Integer.valueOf(R.string.settings_guardian_customization_yellow));
            put(2, Integer.valueOf(R.string.settings_guardian_customization_purple));
            put(3, Integer.valueOf(R.string.settings_guardian_customization_blue));
        }
    };
    private static final Map<Integer, Integer> mGuardianConfig = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsGuardianSection.AnonymousClass3 */

        {
            put(0, Integer.valueOf(R.string.settings_roomscale_config_simple_option));
            put(1, Integer.valueOf(R.string.settings_roomscale_config_advanced_option));
        }
    };
    private GuardianModule.GuardianConfigUpdateListener mAdvancedHMDDistConfigListener;
    private GuardianModule.GuardianConfigUpdateListener mAdvancedHMDVelocityDistConfigListener;
    private GuardianModule.GuardianConfigUpdateListener mAdvancedHandDistConfigListener;
    private GuardianModule.GuardianConfigUpdateListener mAdvancedHandVelocityDistConfigListener;
    private final Context mContext;
    private GuardianModule.GuardianConfigUpdateListener mCustomizationGuardianConfigUpdateListener;
    private GuardianModule.GuardianConfigUpdateListener mGlanceableBoundaryGuardianConfigUpdateListener;
    private GuardianModule.GuardianConfigUpdateListener mGuardianConfigSelectionUpdateListener;
    private boolean mHasSeenPassthroughOnDemandNux = false;
    private GuardianModule.GuardianConfigUpdateListener mIntrusionDetectionGuardianConfigUpdateListener;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final Runnable mRefreshView;
    private List<BaseSettingsItem.Builder> mSettings;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private GuardianModule.GuardianConfigUpdateListener mSimpleGuardianConfigListener;
    private final SparseIntArray mTypeToSetting = new SparseIntArray();

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsGuardianSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.anytime_tablet_settings_nav_guardian_v2), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        this.mSettings = new ArrayList();
        addAdjustGuardianNav();
        addSetFloorLevelNav();
        addGuardianCustomizationDropdown();
        addPassthroughOnDemandToggle();
        addClearHistoryButton();
        this.mSettings.add(new SettingsDescriptiveText.Builder(this.mContext, this.mPanelApp).withHeader(R.string.settings_roomscale_heading_text));
        addGlanceableBoundaryToggle();
        addGuardianSensitivityDropdown();
        addSimpleGuardianSlider();
        advancedGuardianSlider("headset_distance_sensitivity", 1, R.string.settings_roomscale_config_headset_distance_title, R.string.settings_roomscale_config_headset_distance_subtitle, this.mAdvancedHMDDistConfigListener);
        advancedGuardianSlider("controller_distance_sensitivity", 2, R.string.settings_roomscale_config_controller_distance_title, R.string.settings_roomscale_config_controller_distance_subtitle, this.mAdvancedHandDistConfigListener);
        advancedGuardianSlider("headset_speed_sensitivity", 6, R.string.settings_roomscale_config_headset_speed_title, R.string.settings_roomscale_config_headset_speed_subtitle, this.mAdvancedHMDVelocityDistConfigListener);
        advancedGuardianSlider("controller_speed_sensitivity", 7, R.string.settings_roomscale_config_controller_speed_title, R.string.settings_roomscale_config_controller_speed_subtitle, this.mAdvancedHandVelocityDistConfigListener);
        addSettingsItems(this.mSettings);
    }

    private void addAdjustGuardianNav() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("sys_utils_redesign_menu_guardian_adjust_guardian").withTitle(R.string.settings_adjust_guardian_item_title).withIcon(R.drawable.oc_icon_guardian_filled_24_d2d2d2).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.GUARDIAN_ADJUST_SETUP, "")));
    }

    private void addSetFloorLevelNav() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("sys_utils_redesign_menu_guardian_adjust_floor").withTitle(R.string.settings_set_floor_level_item_title).withIcon(R.drawable.oc_icon_set_floor_filled_24_d2d2d2).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(this.mPanelApp, SystemUXRoute.GUARDIAN_ADJUST_FLOOR, "")));
    }

    private void addGuardianCustomizationDropdown() {
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 17);
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("guardian_customization_boundary_color_config").withTitle(R.string.settings_guardian_customization_item_title).withSubtitle(R.string.settings_guardian_customization_item_subtitle).withIcon(R.drawable.oc_icon_color_filled_24_d2d2d2).withSettingsActionType(new SettingsDropdownActionType.Builder().withItems(mBoundaryColors.keySet().toArray(new Integer[mBoundaryColors.keySet().size()])).withIconMap(mBoundaryColorIcons).withSelectedItem(3).withTitleMap(mBoundaryColors).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$RlsA58_HySIe1tVOlRoOg00h9Q */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsGuardianSection.this.lambda$addGuardianCustomizationDropdown$404$SettingsGuardianSection((Integer) obj);
            }
        })));
        this.mTypeToSetting.append(17, this.mSettings.size() - 1);
        this.mCustomizationGuardianConfigUpdateListener = new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$klwj91Hox23yByxEx6m1rXXs */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsGuardianSection.this.lambda$addGuardianCustomizationDropdown$405$SettingsGuardianSection(f);
            }
        };
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(17, this.mCustomizationGuardianConfigUpdateListener);
    }

    public /* synthetic */ boolean lambda$addGuardianCustomizationDropdown$404$SettingsGuardianSection(Integer num) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 17, (float) num.intValue());
        return true;
    }

    public /* synthetic */ void lambda$addGuardianCustomizationDropdown$405$SettingsGuardianSection(float f) {
        modifyGuardianDropdownValue(17, f);
    }

    private void addClearHistoryButton() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("guardian_clear_history").withTitle(R.string.settings_guardian_clear_history_item_title).withSubtitle(R.string.settings_guardian_clear_history_item_subtitle).withIcon(R.drawable.oc_icon_trash_filled_24_d2d2d2).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_guardian_clear_history_button_title).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$86Ek3KKPBIBnLrMGVf8ZAVNg1No */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsGuardianSection.this.lambda$addClearHistoryButton$406$SettingsGuardianSection();
            }
        })));
    }

    private void addPassthroughOnDemandToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("passthrough_on_demand").withTitle(R.string.settings_passthrough_shortcut_title).withSubtitle(R.string.settings_passthrough_shortcut_subtitle).withIcon(R.drawable.oc_icon_pass_through_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$biOcuQnTPicdK7edveIi1x5mo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsGuardianSection.this.lambda$addPassthroughOnDemandToggle$407$SettingsGuardianSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$SdyzrMF7CkcQZ9p98frYvoP_JVk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsGuardianSection.this.lambda$addPassthroughOnDemandToggle$408$SettingsGuardianSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addPassthroughOnDemandToggle$407$SettingsGuardianSection() {
        boolean z = this.mSettingsManager.getBoolean(SettingsManager.PASSTHROUGH_ON_DEMAND_ENABLED, false);
        if (z) {
            this.mHasSeenPassthroughOnDemandNux = true;
        }
        return z;
    }

    public /* synthetic */ void lambda$addPassthroughOnDemandToggle$408$SettingsGuardianSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        this.mSettingsManager.setBoolean(SettingsManager.PASSTHROUGH_ON_DEMAND_ENABLED, z);
        if (z && !this.mHasSeenPassthroughOnDemandNux) {
            this.mPanelApp.actionNavigate(SystemUXRoute.PT_ONDEMAND_NUX_DIALOG, "");
            this.mHasSeenPassthroughOnDemandNux = true;
        }
    }

    private void addGlanceableBoundaryToggle() {
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("guardian_enable_glanceable_bounds").withTitle(R.string.settings_glanceable_boundary_item_title).withSubtitle(R.string.settings_glanceable_boundary_item_subtitle).withIcon(R.drawable.oc_icon_see_floor_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$a2Az1Ibcww9ALHVrAdKND28bGkg */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsGuardianSection.this.lambda$addGlanceableBoundaryToggle$409$SettingsGuardianSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$cgPNNLXqBELwdz4orcykj0bMVQ */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsGuardianSection.this.lambda$addGlanceableBoundaryToggle$410$SettingsGuardianSection(z, settingsToggleActionType);
            }
        })));
        this.mTypeToSetting.append(16, this.mSettings.size() - 1);
        this.mGlanceableBoundaryGuardianConfigUpdateListener = new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$vQnDQ6PUOe2VdzzOcqx9eL3Ia5U */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsGuardianSection.this.lambda$addGlanceableBoundaryToggle$411$SettingsGuardianSection(f);
            }
        };
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(16, this.mGlanceableBoundaryGuardianConfigUpdateListener);
    }

    public /* synthetic */ boolean lambda$addGlanceableBoundaryToggle$409$SettingsGuardianSection() {
        return GuardianModule.getGuardianConfigValue(this.mPanelApp, 16);
    }

    public /* synthetic */ void lambda$addGlanceableBoundaryToggle$410$SettingsGuardianSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 16, (float) (z ? 1.0d : 0.0d));
    }

    public /* synthetic */ void lambda$addGlanceableBoundaryToggle$411$SettingsGuardianSection(float f) {
        modifyGuardianToggleValue(16, f);
    }

    private void addGuardianSensitivityDropdown() {
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 8);
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("guardian_roomscale_config").withTitle(R.string.settings_roomscale_config_simple_title).withSubtitle(R.string.settings_roomscale_config_simple_subtitle).withIcon(R.drawable.oc_icon_roomscale_filled_24_d2d2d2).withSettingsActionType(new SettingsDropdownActionType.Builder().withItems(mGuardianConfig.keySet().toArray(new Integer[mGuardianConfig.keySet().size()])).withSelectedItem(0).withTitleMap(mGuardianConfig).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$QBaWa2BzW1do6ratLxmn2lnNUlA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsGuardianSection.this.lambda$addGuardianSensitivityDropdown$412$SettingsGuardianSection((Integer) obj);
            }
        })));
        this.mTypeToSetting.append(8, this.mSettings.size() - 1);
        this.mGuardianConfigSelectionUpdateListener = new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$KSdfNI_6IqubuMRpkOMpJ0qTm34 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsGuardianSection.this.lambda$addGuardianSensitivityDropdown$413$SettingsGuardianSection(f);
            }
        };
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(8, this.mGuardianConfigSelectionUpdateListener);
    }

    public /* synthetic */ boolean lambda$addGuardianSensitivityDropdown$412$SettingsGuardianSection(Integer num) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 8, (float) num.intValue());
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 8);
        return true;
    }

    public /* synthetic */ void lambda$addGuardianSensitivityDropdown$413$SettingsGuardianSection(float f) {
        modifyGuardianDropdownValue(8, f);
    }

    private void addSimpleGuardianSlider() {
        GuardianModule.getGuardianConfigValue(this.mPanelApp, 13);
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName("simple_guardian_sensitivity").withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(0).withMaxLabel(R.string.settings_sensitivity_item_slider_max_title).withMinLabel(R.string.settings_sensitivity_item_slider_min_title).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$DC0OLCxle4gGkwGpO4nogBB4AdU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsGuardianSection.this.lambda$addSimpleGuardianSlider$414$SettingsGuardianSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$EEHenzBPOTeujL0ECPDcgAmP3Qk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsGuardianSection.this.lambda$addSimpleGuardianSlider$415$SettingsGuardianSection(i);
            }
        })));
        this.mTypeToSetting.append(13, this.mSettings.size() - 1);
        this.mSimpleGuardianConfigListener = new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$x5InnkgXlNqBBttXVnSCxnpATac */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsGuardianSection.this.lambda$addSimpleGuardianSlider$416$SettingsGuardianSection(f);
            }
        };
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(13, this.mSimpleGuardianConfigListener);
    }

    public /* synthetic */ void lambda$addSimpleGuardianSlider$414$SettingsGuardianSection(int i) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 13, ((float) i) / 100.0f);
    }

    public /* synthetic */ void lambda$addSimpleGuardianSlider$415$SettingsGuardianSection(int i) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, 13, ((float) i) / 100.0f);
    }

    public /* synthetic */ void lambda$addSimpleGuardianSlider$416$SettingsGuardianSection(float f) {
        lambda$advancedGuardianSlider$419$SettingsGuardianSection(13, f);
    }

    private void advancedGuardianSlider(String str, int i, int i2, int i3, GuardianModule.GuardianConfigUpdateListener guardianConfigUpdateListener) {
        GuardianModule.getGuardianConfigValue(this.mPanelApp, i);
        this.mSettings.add(new SettingsItem.Builder(this.mContext, this.mPanelApp).withSettingName(str).withTitle(i2).withSubtitle(i3).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(0).withMaxLabel(R.string.settings_sensitivity_item_slider_max_title).withMinLabel(R.string.settings_sensitivity_item_slider_min_title).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler(i) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$4PUM7BpCG9_26EFaBHDDGffSZE */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsGuardianSection.this.lambda$advancedGuardianSlider$417$SettingsGuardianSection(this.f$1, i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler(i) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$We7jEpBSBLt9fzsvjQh1CXiP4 */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsGuardianSection.this.lambda$advancedGuardianSlider$418$SettingsGuardianSection(this.f$1, i);
            }
        })));
        this.mTypeToSetting.append(i, this.mSettings.size() - 1);
        this.mPanelApp.getGuardianModule().addGuardianUpdateListener(i, new GuardianModule.GuardianConfigUpdateListener(i) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$WH_KwEt5HIp_yyAJsyicyzv5MhI */
            private final /* synthetic */ int f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsGuardianSection.this.lambda$advancedGuardianSlider$419$SettingsGuardianSection(this.f$1, f);
            }
        });
    }

    public /* synthetic */ void lambda$advancedGuardianSlider$417$SettingsGuardianSection(int i, int i2) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, i, ((float) i2) / 100.0f);
    }

    public /* synthetic */ void lambda$advancedGuardianSlider$418$SettingsGuardianSection(int i, int i2) {
        GuardianModule.setGuardianConfigValue(this.mPanelApp, i, ((float) i2) / 100.0f);
    }

    /* access modifiers changed from: private */
    /* renamed from: confirmClearHistory */
    public void lambda$addClearHistoryButton$406$SettingsGuardianSection() {
        DialogDefinitionCustom clearHistoryDialog = getClearHistoryDialog();
        clearHistoryDialog.setDialogResultHandler(new DialogResultHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsGuardianSection$JIqXIF3ItIm3sh82rvKTUl51dY */

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsGuardianSection.this.lambda$confirmClearHistory$420$SettingsGuardianSection(dialogResult);
            }
        });
        this.mPanelApp.getDialogManager().showDialog(clearHistoryDialog);
    }

    public /* synthetic */ boolean lambda$confirmClearHistory$420$SettingsGuardianSection(DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        this.mPanelApp.actionNavigate(SystemUXRoute.GUARDIAN_CLEAR_HISTORY, "");
        return true;
    }

    private DialogDefinitionCustom getClearHistoryDialog() {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CLEAR_GUARDIAN_HISTORY, resources.getString(R.string.settings_guardian_clear_history_dialog_title), resources.getString(R.string.settings_guardian_clear_history_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_guardian_disable_dialog_cancel)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_guardian_disable_dialog_acknowledge)));
        return dialogDefinitionCustom;
    }

    public void modifyGuardianToggleValue(int i, float f) {
        SettingsToggleActionType settingsToggleActionType = (SettingsToggleActionType) fetchActionType(i);
        if (settingsToggleActionType != null) {
            settingsToggleActionType.asyncSetValue(((double) f) == 1.0d);
        }
    }

    public void modifyGuardianDropdownValue(int i, float f) {
        SettingsDropdownActionType settingsDropdownActionType = (SettingsDropdownActionType) fetchActionType(i);
        if (i == 17 && !mBoundaryColorIcons.containsKey(Integer.valueOf((int) f))) {
            f = 3.0f;
        }
        if (settingsDropdownActionType != null) {
            settingsDropdownActionType.setCurrentItem(Integer.valueOf((int) f));
        }
        if (i == 8) {
            displayGuardianSliders(f == 0.0f);
        }
    }

    private void displayGuardianSliders(boolean z) {
        fetchSettingsItem(13).setCurrentVisibility(z);
        fetchSettingsItem(1).setCurrentVisibility(!z);
        fetchSettingsItem(2).setCurrentVisibility(!z);
        fetchSettingsItem(6).setCurrentVisibility(!z);
        fetchSettingsItem(7).setCurrentVisibility(!z);
        this.mRefreshView.run();
    }

    /* renamed from: modifyGuardianSliderValue */
    public void lambda$advancedGuardianSlider$419$SettingsGuardianSection(int i, float f) {
        SettingsSliderActionType settingsSliderActionType = (SettingsSliderActionType) fetchActionType(i);
        if (settingsSliderActionType != null) {
            settingsSliderActionType.setValue((int) (f * 100.0f));
        }
    }

    private BaseSettingsActionType fetchActionType(int i) {
        SettingsItem settingsItem = (SettingsItem) fetchSettingsItem(i);
        if (settingsItem != null) {
            return settingsItem.getActionType();
        }
        return null;
    }

    private BaseSettingsItem fetchSettingsItem(int i) {
        int i2 = this.mTypeToSetting.get(i, -1);
        SettingsList settingsList = getSettingsList();
        if (i2 == -1 || settingsList == null) {
            return null;
        }
        List<BaseSettingsItem> settingsItems = settingsList.getSettingsItems();
        if (i2 < settingsItems.size()) {
            return settingsItems.get(i2);
        }
        return null;
    }
}
