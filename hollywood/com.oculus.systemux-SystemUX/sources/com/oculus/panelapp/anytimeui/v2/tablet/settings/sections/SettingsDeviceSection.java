package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.PowerManager;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.certificatepinning.FbCertificatePinnerFactory;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.devicelocale.DeviceLocale;
import com.oculus.devicelocale.DeviceLocaleUtil;
import com.oculus.os.NightShiftController;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsList;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.device.DeviceLocaleGraphQLUtil;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.CompanionServerHelper;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCommon;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.PackageUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import okhttp3.OkHttpClient;

public class SettingsDeviceSection extends SettingsSection {
    private static final int AUTO_TRACKING_FREQUENCY = -1;
    private static final int DEFAULT_ELECTRIC_GRID_LINE_FREQUENCY = -1;
    public static final String DISCONNECT_REASON_PC_NOT_DETECTED = "pc_not_detected";
    private static final int FIFTY_TRACKING_FREQUENCY = 50;
    private static final String REBOOT_HEADSET_DIALOG = "anytime_tablet_reboot_headset";
    private static final int SIXTY_TRACKING_FREQUENCY = 60;
    private static final Map<Integer, Integer> mFontSizes = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection.AnonymousClass2 */

        {
            put(-1, Integer.valueOf(R.string.settings_font_size_small_text));
            put(0, Integer.valueOf(R.string.settings_font_size_medium_text));
            put(1, Integer.valueOf(R.string.settings_font_size_large_text));
            put(2, Integer.valueOf(R.string.settings_font_size_extra_large_text));
            put(3, Integer.valueOf(R.string.settings_font_size_largest_text));
        }
    };
    private static final Map<Integer, Integer> mFrequencies = new HashMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeviceSection.AnonymousClass1 */

        {
            put(-1, Integer.valueOf(R.string.settings_auto_tracking_frequency_text));
            put(50, Integer.valueOf(R.string.settings_50hz_tracking_frequency_text));
            put(60, Integer.valueOf(R.string.settings_60hz_tracking_frequency_text));
        }
    };
    private final Context mContext;
    private OkHttpClient mOkHttpClient;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final PowerManager mPowerManager;
    private final Runnable mRefreshView;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private final StatusViewModel mStatusViewModel;
    private int mTrackingIndex = -1;
    private StatusViewModel.WifiStateListener mWifiStateListener;

    public enum UnlockPattern {
        SET,
        NOT_SET
    }

    public SettingsDeviceSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(R.string.anytime_tablet_settings_nav_device_v2), null);
        SettingsNavigationActionType.Builder builder;
        int i = -1;
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mRefreshView = runnable;
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        AndroidSettingsViewModel acquireAndroidSettingsViewModel = anytimeUIAndroidPanelAppV2.acquireAndroidSettingsViewModel();
        this.mStatusViewModel = anytimeUIAndroidPanelAppV2.acquireStatusViewModel();
        initializeOkHttpClient();
        NightShiftController nightShiftController = new NightShiftController(context);
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("hands_and_controllers_subsection").withTitle(anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode ? R.string.settings_controllers_section_item_title : R.string.settings_hands_and_controllers_section_item_title).withSubtitle(anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode ? R.string.settings_controllers_section_item_subtitle : R.string.settings_hands_and_controllers_section_item_subtitle).withIcon(anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode ? R.drawable.oc_icon_touch_2_right_filled_24_d2d2d2 : R.drawable.oc_icon_hand_tracking_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_CONTROLLERS_URI).isInternal(true)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("night_shift").withTitle(R.string.anytime_tablet_settings_nightshift_tooltip).withSubtitle(R.string.settings_night_display_subtitle).withIcon(R.drawable.oc_icon_night_mode_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$xxYnDiVpwnIXa8BcaOw_Bp2J4 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return NightShiftController.this.isActivated();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(nightShiftController, anytimeUIAndroidPanelAppV2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$S4kybFKA81oXYCfIrisQEzenn0A */
            private final /* synthetic */ NightShiftController f$1;
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsDeviceSection.this.lambda$new$351$SettingsDeviceSection(this.f$1, this.f$2, z, settingsToggleActionType);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("night_shift_temperature").withTitle(R.string.settings_night_display_intensity_item_title).withShowInEnterprise(true).withGatekeepers(Gatekeeper.NIGHT_DISPLAY_SECTION_SLIDER).withIndented(true).withSettingsActionType(new SettingsSliderActionType.Builder(context).withValue(-nightShiftController.getColorTemperature()).withMinIcon(R.drawable.oc_icon_minimize_filled_24_d2d2d2).withMaxIcon(R.drawable.oc_icon_add_filled_24_d2d2d2).withMinValue(-nightShiftController.getMaximumColorTemperature()).withMaxValue(-nightShiftController.getMinimumColorTemperature()).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$yNfDQnxgW6mxcfSayYO6_pkSFI */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                NightShiftController.this.setColorTemperature(-i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$cOBH_tMGTDANzd8G898Ux5fvJYU */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                NightShiftController.this.setColorTemperature(-i);
            }
        })).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$yNGHTvJ4QrQHhbnZWbc8joZheA */

            @Override // java.util.function.Supplier
            public final Object get() {
                return Boolean.valueOf(NightShiftController.this.isActivated());
            }
        }));
        SettingsItem.Builder withOnInitialize = new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("wifi_subsection").withTitle(R.string.settings_wifi_item_title).withOnInitialize(new SettingsItem.InitializeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$m2YsUn7Q4stsl32Cmu_WYqeXmgw */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem.InitializeHandler
            public final void run(SettingsItem settingsItem) {
                SettingsDeviceSection.this.initializeWifiListener(settingsItem);
            }
        });
        StatusViewModel statusViewModel = this.mStatusViewModel;
        statusViewModel.getClass();
        arrayList.add(withOnInitialize.withDynamicSubtitle(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$Dwll9y8EPHL2yqlgcMnR1s9sGQc */

            @Override // java.util.function.Supplier
            public final Object get() {
                return StatusViewModel.this.getWifiButtonCTA();
            }
        }).withIcon(R.drawable.oc_icon_wifi_on_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.WIFI, "")));
        SettingsItem.Builder withShowInEnterprise = new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("remote_rendering").withTitle(R.string.settings_link_item_title).withSubtitle(R.string.settings_link_item_subtitle).withIcon(R.drawable.oc_icon_computer_filled_24_d2d2d2).withShowInEnterprise(true);
        if (AnytimeUIAndroidPanelAppV2.shouldShowOculusLinkButton()) {
            builder = new SettingsNavigationActionType.Builder().withStringRoute(anytimeUIAndroidPanelAppV2, PackageUtil.PACKAGE_NAME_XRSTREAMING_CLIENT, "");
        } else {
            builder = new SettingsNavigationActionType.Builder().withSystemDialog(anytimeUIAndroidPanelAppV2, getLinkDisconnectedDialog(DISCONNECT_REASON_PC_NOT_DETECTED));
        }
        arrayList.add(withShowInEnterprise.withSettingsActionType(builder));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("privacy").withTitle(R.string.settings_privacy_item_title).withIcon(R.drawable.oc_icon_privacy_filled_24_d2d2d2).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SOCIAL, TabletDeepLinkingUriUtil.SETTINGS_PRIVACY_URI)));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("assistant_subsection").withTitle(R.string.settings_assistant_section_item_title).withSubtitle(R.string.settings_assistant_section_item_subtitle).withIcon(R.drawable.oc_icon_voice_assistant_filled_24_d2d2d2).withGatekeepers(Gatekeeper.AUI_ASSISTANT).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_ASSISTANT_URI).isInternal(true)));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("keyboard_subsection").withTitle(R.string.settings_keyboard_section_item_title).withSubtitle(R.string.settings_keyboard_section_item_subtitle).withIcon(R.drawable.oc_icon_keyboard_filled_24_d2d2d2).withGatekeepers(Gatekeeper.KEYBOARD_SETTINGS_SECTION).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_KEYBOARD_URI).isInternal(true)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("device_user_management_unlockpattern").withTitle(R.string.settings_unlock_pattern_item_title).withSubtitle(R.string.settings_unlock_pattern_item_subtitle).withIcon(R.drawable.oc_icon_unlock_pattern_filled_24_d2d2d2).withSettingsActionType(new SettingsComplexButtonActionType.Builder().withInitializeState(new SettingsComplexButtonActionType.OnInitializeState(context) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$UN0KOVhSgFoWngOAKGKh1EKgFco */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.OnInitializeState
            public final void run(SettingsComplexButtonActionType settingsComplexButtonActionType) {
                CompanionServerHelper.isPinSet(this.f$0, new CompanionServerHelper.OnIsPinSet() {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$VXaBGbIZs2quGr8nO_2sP5d9JCY */

                    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.util.CompanionServerHelper.OnIsPinSet
                    public final void onResult(boolean z) {
                        SettingsDeviceSection.lambda$null$355(SettingsComplexButtonActionType.this, z);
                    }
                });
            }
        }).withOnClickHandler(new SettingsComplexButtonActionType.OnButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$6dG2kbCFq223nqxsNK7a9vhb6Vk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.OnButtonClickHandler
            public final void onClick(Object obj, SettingsComplexButtonActionType.UpdateStateHandler updateStateHandler) {
                SettingsDeviceSection.lambda$new$357(AnytimeUIAndroidPanelAppV2.this, (SettingsDeviceSection.UnlockPattern) obj, updateStateHandler);
            }
        }).withTitle(new SettingsComplexButtonActionType.GetTitleHandler(context) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$u1gijixcTngbf8RyYf3I2haRYdk */
            private final /* synthetic */ Context f$0;

            {
                this.f$0 = r1;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.GetTitleHandler
            public final String get(Object obj) {
                return SettingsDeviceSection.lambda$new$358(this.f$0, (SettingsDeviceSection.UnlockPattern) obj);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("microphone").withTitle(R.string.settings_microphone_item_title).withIcon(R.drawable.oc_icon_microphone_off_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.MIC_MUTED)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("video_capture_indicator").withTitle(R.string.settings_capture_indicator_item_title).withSubtitle(R.string.settings_capture_indicator_item_subtitle).withIcon(R.drawable.oc_icon_video_capture_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.CAPTURE_INDICATOR_ENABLED)));
        Integer valueOf = Integer.valueOf(this.mSettingsManager.getInt(SettingsManager.FONT_SIZE, 0));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withTitle(SettingsManager.FONT_SIZE).withTitle(R.string.settings_font_size_item_title).withSubtitle(R.string.settings_font_size_item_subtitle).withIcon(R.drawable.oc_icon_font_scaling_filled_24_d2d2d2).withShowInEnterprise(true).withGatekeepers(Gatekeeper.FONT_SCALING_GK).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(acquireAndroidSettingsViewModel).withItems(mFontSizes.keySet().toArray(new Integer[mFontSizes.keySet().size()])).withSelectedItem(valueOf).withTitleMap(mFontSizes).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler(anytimeUIAndroidPanelAppV2, valueOf) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$nK6BoilWhzQE1G3SoP7g9_Bv_jM */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ Integer f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsDeviceSection.this.lambda$new$359$SettingsDeviceSection(this.f$1, this.f$2, (Integer) obj);
            }
        })));
        HashMap hashMap = new HashMap();
        DeviceLocale[] values = DeviceLocale.values();
        for (DeviceLocale deviceLocale : values) {
            hashMap.put(deviceLocale, Integer.valueOf(deviceLocale.getDisplayNameId()));
        }
        DeviceLocale deviceLocale2 = DeviceLocaleUtil.getDeviceLocale(this.mContext);
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("settings_language").withTitle(R.string.settings_language_item_title).withIcon(R.drawable.oc_icon_world_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(acquireAndroidSettingsViewModel).withItems(DeviceLocale.values()).withSelectedItem(deviceLocale2).withTitleMap(hashMap).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler(anytimeUIAndroidPanelAppV2, deviceLocale2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$GMHvCBLENCC2jf0q7sGX5A_Z31Y */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ DeviceLocale f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsDeviceSection.this.lambda$new$360$SettingsDeviceSection(this.f$1, this.f$2, (DeviceLocale) obj);
            }
        })));
        Integer valueOf2 = Integer.valueOf(this.mSettingsManager.getInt(SettingsManager.ELECTRIC_GRID_LINE_FREQUENCY, -1));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("tracking_frequency").withTitle(R.string.settings_tracking_frequency_item_title).withSubtitle(R.string.settings_tracking_frequency_item_subtitle).withIcon(R.drawable.oc_icon_frequency_filled_24_d2d2d2).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(acquireAndroidSettingsViewModel).withItems(mFrequencies.keySet().toArray(new Integer[mFrequencies.keySet().size()])).withSelectedItem(Integer.valueOf(mFrequencies.containsKey(valueOf2) ? valueOf2.intValue() : i)).withTitleMap(mFrequencies).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler(anytimeUIAndroidPanelAppV2, valueOf2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$DbiqXfwzqRpL6ApGxnMhlDmxIQ */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
            private final /* synthetic */ Integer f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsDeviceSection.this.lambda$new$361$SettingsDeviceSection(this.f$1, this.f$2, (Integer) obj);
            }
        })));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("guardian_tracking").withTitle(R.string.settings_tracking_section_item_title).withSubtitle(R.string.settings_tracking_item_subtitle).withIcon(R.drawable.oc_icon_guardian_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$HcTClPnNr8Y4NpBrT_O8MneX0Lk */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return GuardianModule.getGuardianConfigValue(AnytimeUIAndroidPanelAppV2.this, 10);
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$tJ21saFPZYTpamrLGZuEtzCBCA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsDeviceSection.lambda$new$363(AnytimeUIAndroidPanelAppV2.this, z, settingsToggleActionType);
            }
        })));
        this.mTrackingIndex = arrayList.size() - 1;
        anytimeUIAndroidPanelAppV2.getGuardianModule().addGuardianUpdateListener(10, new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$r69IDAYn_hqEggZrnoqqGUhsu0I */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsDeviceSection.this.modifyTrackingToggle(f);
            }
        });
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("power_subsection").withTitle(R.string.settings_power_section_item_title).withSubtitle(R.string.settings_power_section_item_subtitle).withIcon(R.drawable.oc_icon_power_filled_24_d2d2d2).withShowInEnterprise(true).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.SETTINGS, TabletDeepLinkingUriUtil.SETTINGS_POWER_URI).isInternal(true)));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$351$SettingsDeviceSection(NightShiftController nightShiftController, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, boolean z, SettingsToggleActionType settingsToggleActionType) {
        nightShiftController.setActivated(z);
        if (anytimeUIAndroidPanelAppV2.isGKEnabled(Gatekeeper.NIGHT_DISPLAY_SECTION_SLIDER)) {
            this.mRefreshView.run();
        }
    }

    static /* synthetic */ void lambda$null$355(SettingsComplexButtonActionType settingsComplexButtonActionType, boolean z) {
        settingsComplexButtonActionType.updateState(z ? UnlockPattern.SET : UnlockPattern.NOT_SET);
    }

    static /* synthetic */ void lambda$new$357(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, UnlockPattern unlockPattern, SettingsComplexButtonActionType.UpdateStateHandler updateStateHandler) {
        SystemUXRoute systemUXRoute = SystemUXRoute.UNLOCK_PATTERN;
        Object[] objArr = new Object[1];
        objArr[0] = unlockPattern == UnlockPattern.SET ? "clear_pattern" : "set_pattern";
        anytimeUIAndroidPanelAppV2.actionNavigate(systemUXRoute, String.format("&flow=%s", objArr));
    }

    static /* synthetic */ String lambda$new$358(Context context, UnlockPattern unlockPattern) {
        return context.getResources().getString(unlockPattern == UnlockPattern.SET ? R.string.settings_unlock_pattern_item_button_remove : R.string.settings_unlock_pattern_item_button_set);
    }

    public /* synthetic */ boolean lambda$new$359$SettingsDeviceSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Integer num, Integer num2) {
        confirmFontChange(anytimeUIAndroidPanelAppV2, num, num2, this.mSettingsManager, this.mPowerManager);
        return false;
    }

    public /* synthetic */ boolean lambda$new$360$SettingsDeviceSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, DeviceLocale deviceLocale, DeviceLocale deviceLocale2) {
        confirmLanguageChange(anytimeUIAndroidPanelAppV2, this.mOkHttpClient, deviceLocale, deviceLocale2);
        return false;
    }

    public /* synthetic */ boolean lambda$new$361$SettingsDeviceSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Integer num, Integer num2) {
        confirmFrequencyChange(anytimeUIAndroidPanelAppV2, num, num2, this.mSettingsManager, this.mPowerManager);
        return false;
    }

    static /* synthetic */ void lambda$new$363(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, boolean z, SettingsToggleActionType settingsToggleActionType) {
        GuardianModule.setGuardianConfigValue(anytimeUIAndroidPanelAppV2, 10, z ? 0.0f : 2.0f);
        GuardianModule.getGuardianConfigValue(anytimeUIAndroidPanelAppV2, 10);
    }

    /* access modifiers changed from: private */
    public void modifyTrackingToggle(float f) {
        boolean z = ((double) f) != 1.0d;
        SettingsList settingsList = getSettingsList();
        if (this.mTrackingIndex != -1 && settingsList != null) {
            List<BaseSettingsItem> settingsItems = settingsList.getSettingsItems();
            if (this.mTrackingIndex < settingsItems.size()) {
                ((SettingsToggleActionType) ((SettingsItem) settingsItems.get(this.mTrackingIndex)).getActionType()).asyncSetValue(z);
            }
        }
    }

    /* access modifiers changed from: private */
    public void initializeWifiListener(SettingsItem settingsItem) {
        if (this.mWifiStateListener == null) {
            this.mWifiStateListener = new StatusViewModel.WifiStateListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$jJEsRr_kBDd6jvAuRwlTFaobzfA */

                @Override // com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel.WifiStateListener
                public final void onWifiStateChanged() {
                    SettingsItem.this.notifyPropertyChanged(BR.subtitle);
                }
            };
            this.mStatusViewModel.addWifiStateListener(this.mWifiStateListener);
        }
    }

    private void initializeOkHttpClient() {
        this.mOkHttpClient = new OkHttpClient.Builder().connectTimeout(6, TimeUnit.SECONDS).certificatePinner(FbCertificatePinnerFactory.create(Build.TIME)).build();
    }

    @VisibleForTesting
    public static void confirmLanguageChange(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, OkHttpClient okHttpClient, DeviceLocale deviceLocale, DeviceLocale deviceLocale2) {
        if (deviceLocale != deviceLocale2) {
            DialogDefinitionCustom rebootDialog = getRebootDialog(anytimeUIAndroidPanelAppV2.getContext());
            rebootDialog.setDialogResultHandler(new DialogResultHandler(anytimeUIAndroidPanelAppV2, deviceLocale2) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$jqJ8lXwH_pkz0HyYe8QMnoehcc */
                private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;
                private final /* synthetic */ DeviceLocale f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsDeviceSection.lambda$confirmLanguageChange$365(OkHttpClient.this, this.f$1, this.f$2, dialogResult);
                }
            });
            anytimeUIAndroidPanelAppV2.getDialogManager().showDialog(rebootDialog);
        }
    }

    static /* synthetic */ boolean lambda$confirmLanguageChange$365(OkHttpClient okHttpClient, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, DeviceLocale deviceLocale, DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        DeviceLocaleGraphQLUtil.setLocale(okHttpClient, anytimeUIAndroidPanelAppV2.getAccessToken(), deviceLocale);
        DeviceLocaleUtil.setDeviceLocale(anytimeUIAndroidPanelAppV2.getContext(), deviceLocale);
        return true;
    }

    @VisibleForTesting
    public static void confirmFrequencyChange(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Integer num, Integer num2, SettingsManager settingsManager, PowerManager powerManager) {
        if (num != num2) {
            DialogDefinitionCustom rebootDialog = getRebootDialog(anytimeUIAndroidPanelAppV2.getContext());
            rebootDialog.setDialogResultHandler(new DialogResultHandler(num2, powerManager) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$UaZWNnRr0h3qrj_jaII25L7ZFlI */
                private final /* synthetic */ Integer f$1;
                private final /* synthetic */ PowerManager f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsDeviceSection.lambda$confirmFrequencyChange$366(SettingsManager.this, this.f$1, this.f$2, dialogResult);
                }
            });
            anytimeUIAndroidPanelAppV2.getDialogManager().showDialog(rebootDialog);
        }
    }

    static /* synthetic */ boolean lambda$confirmFrequencyChange$366(SettingsManager settingsManager, Integer num, PowerManager powerManager, DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        settingsManager.setInt(SettingsManager.ELECTRIC_GRID_LINE_FREQUENCY, num.intValue());
        powerManager.reboot(null);
        return true;
    }

    @VisibleForTesting
    public static void confirmFontChange(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Integer num, Integer num2, SettingsManager settingsManager, PowerManager powerManager) {
        if (num != num2) {
            DialogDefinitionCustom rebootDialog = getRebootDialog(anytimeUIAndroidPanelAppV2.getContext());
            rebootDialog.setDialogResultHandler(new DialogResultHandler(num2, powerManager) {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$vP_45aq86ot7VBiiBOcKZ3KdR8U */
                private final /* synthetic */ Integer f$1;
                private final /* synthetic */ PowerManager f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                @Override // com.oculus.systemdialog.DialogResultHandler
                public final boolean handleDialogResult(DialogResult dialogResult) {
                    return SettingsDeviceSection.lambda$confirmFontChange$367(SettingsManager.this, this.f$1, this.f$2, dialogResult);
                }
            });
            anytimeUIAndroidPanelAppV2.getDialogManager().showDialog(rebootDialog);
        }
    }

    static /* synthetic */ boolean lambda$confirmFontChange$367(SettingsManager settingsManager, Integer num, PowerManager powerManager, DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        settingsManager.setInt(SettingsManager.FONT_SIZE, num.intValue());
        powerManager.reboot(null);
        return true;
    }

    private static DialogDefinitionCustom getRebootDialog(Context context) {
        Resources resources = context.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(REBOOT_HEADSET_DIALOG, resources.getString(R.string.settings_confirm_reboot_dialog_title), resources.getString(R.string.settings_confirm_reboot_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_confirm_reboot_dialog_cancel_button)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_confirm_reboot_dialog_restart_button)));
        return dialogDefinitionCustom;
    }

    public static DialogDefinitionCommon getLinkDisconnectedDialog(String str) {
        DialogDefinitionCommon dialogDefinitionCommon = new DialogDefinitionCommon(CommonSystemDialog.OCULUS_LINK_DISCONNECTED);
        dialogDefinitionCommon.setParameter("disconnect_reason", str);
        return dialogDefinitionCommon;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        StatusViewModel.WifiStateListener wifiStateListener = this.mWifiStateListener;
        if (wifiStateListener != null) {
            this.mStatusViewModel.removeWifiStateListener(wifiStateListener);
        }
        this.mPanelApp.getGuardianModule().removeUpdateListener(new GuardianModule.GuardianConfigUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeviceSection$r69IDAYn_hqEggZrnoqqGUhsu0I */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.GuardianModule.GuardianConfigUpdateListener
            public final void onUpdate(float f) {
                SettingsDeviceSection.this.modifyTrackingToggle(f);
            }
        });
        this.mPanelApp.releaseAndroidSettingsViewModel();
        this.mPanelApp.releaseStatusViewModel();
    }
}
