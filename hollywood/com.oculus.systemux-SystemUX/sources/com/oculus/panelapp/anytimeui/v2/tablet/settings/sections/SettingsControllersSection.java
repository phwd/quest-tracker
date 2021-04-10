package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.nuxpreferencesapi.OVRNuxPreferences;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.BaseSettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.ControllerStateModule;
import com.oculus.tablet.navigation.TabletDeepLinkingUriUtil;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.ThreadExecutor;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class SettingsControllersSection extends SettingsSection {
    private static final String AUTOTRANSITION_SETTING_NAME = "autotransition_hands_controllers";
    private static final int BUTTON_MAPPING_DEFAULT = 0;
    private static final int BUTTON_MAPPING_LOCKOUT_DISABLED = 2;
    private static final int BUTTON_MAPPING_LONG_PRESS = 1;
    private static final String CONTROLLER_CONNECTED = "CONNECTED";
    private static final int INPUT_HAPTICS_AMPLITUDE_DEFAULT_VALUE = 50;
    private static final String INPUT_HAPTICS_AMPLITUDE_SETTING_NAME = "input_haptics_amplitude";
    private static final String LEARN_MORE_URI = "https://support.oculus.com/535510833906841";
    private static final long NUX_PREFS_FETCH_BACKOFF = 10;
    private static final String SWAP_SYSTEM_BUTTON_HANDEDNESS_SETTING_NAME = "swap_system_button_handedness";
    private static final String TAG = LoggingUtil.tag(SettingsControllersSection.class);
    private final Context mContext;
    private final ControllerStateModule mControllerStateModule;
    private boolean mIsEnterprise;
    private boolean mPrimaryControllerConnected;
    private final Runnable mRefreshView;
    private boolean mSecondaryControllerConnected;
    private final SettingsManager mSettingsManager;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SettingsControllersSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, Runnable runnable) {
        super(context.getResources().getString(anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode ? R.string.settings_controllers_section_item_title : R.string.settings_hands_and_controllers_section_item_title), TabletDeepLinkingUriUtil.SETTINGS_DEVICE_URI);
        this.mSettingsManager = new SettingsManager();
        this.mControllerStateModule = new ControllerStateModule();
        this.mIsEnterprise = false;
        this.mPrimaryControllerConnected = false;
        this.mSecondaryControllerConnected = false;
        this.mContext = context;
        this.mRefreshView = runnable;
        this.mIsEnterprise = anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode;
        ArrayList arrayList = new ArrayList();
        addSwitchButtonHandednessToggle(anytimeUIAndroidPanelAppV2, arrayList);
        addHandsSettings(anytimeUIAndroidPanelAppV2, arrayList);
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_controllers_heading_text));
        if (this.mIsEnterprise) {
            add6DoFControllersPairingItem(anytimeUIAndroidPanelAppV2, arrayList);
        }
        addFastMovementToggle(anytimeUIAndroidPanelAppV2, arrayList);
        addLongPressToggle(anytimeUIAndroidPanelAppV2, arrayList);
        addVibrationIntensitySlider(anytimeUIAndroidPanelAppV2, arrayList);
        addSettingsItems(arrayList);
    }

    private void addSwitchButtonHandednessToggle(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("swap_system_button_handedness").withTitle(R.string.settings_button_handedness_item_title).withShowInEnterprise(true).withSubtitle(anytimeUIAndroidPanelAppV2.getSystemUXConfig().isEnterpriseMode ? R.string.settings_button_handedness_controllers_item_subtitle : R.string.settings_button_handedness_hands_item_subtitle).withGatekeepers(Gatekeeper.SETTINGS_SWAP_SYSTEM_BTN_HANDEDNESS_GK).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey("swap_system_button_handedness")));
    }

    private void addHandsSettings(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        list.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_hands_heading_text));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("hand_tracking").withTitle(R.string.settings_hand_tracking_item_title).withSubtitle(R.string.settings_hand_tracking_item_subtitle_with_link).withSubtitleUri(LEARN_MORE_URI).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$9hYdfOmvaNM6RyhaBWkHVdiZso0 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsControllersSection.this.lambda$addHandsSettings$327$SettingsControllersSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(anytimeUIAndroidPanelAppV2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$m29JzGNocmdZERkhdgQ0oaHA */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsControllersSection.this.lambda$addHandsSettings$328$SettingsControllersSection(this.f$1, z, settingsToggleActionType);
            }
        })));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("hand_tracking_transition").withTitle(R.string.settings_autotransition_item_title).withSubtitle(R.string.settings_autotransition_item_subtitle).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$glLKaMHqhSfEdqHTzzLdqQ7TcZU */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$addHandsSettings$329$SettingsControllersSection();
            }
        }).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey("autotransition_hands_controllers")));
    }

    public /* synthetic */ boolean lambda$addHandsSettings$327$SettingsControllersSection() {
        return this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, false);
    }

    public /* synthetic */ void lambda$addHandsSettings$328$SettingsControllersSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, boolean z, SettingsToggleActionType settingsToggleActionType) {
        boolean z2 = this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, false);
        boolean z3 = this.mSettingsManager.getBoolean("autotransition_hands_controllers", false);
        if (z2 || !z3) {
            this.mSettingsManager.setBoolean(SettingsManager.HAND_TRACKING_ENABLED, !z2);
        }
        this.mSettingsManager.setBoolean(SettingsManager.HAND_TRACKING_OPT_IN, !z2);
        if (!z2 && shouldShowNux()) {
            anytimeUIAndroidPanelAppV2.actionNavigate(SystemUXRoute.HAND_TRACKING_NUX, "");
        }
        this.mRefreshView.run();
    }

    public /* synthetic */ Boolean lambda$addHandsSettings$329$SettingsControllersSection() {
        return Boolean.valueOf(this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, false));
    }

    private void addFastMovementToggle(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("oculus_button_fast_movement").withTitle(R.string.settings_fast_movement_item_title).withSubtitle(R.string.settings_fast_movement_item_subtitle).withShowInEnterprise(true).withVisibilityCondition($$Lambda$MzRavGNgSGP6g9nbyIOZTklUg0.INSTANCE).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$VBbCAcN9wednAlFv6psHlUj858 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsControllersSection.this.lambda$addFastMovementToggle$330$SettingsControllersSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$bD2b4iqcMVoF_Ff4TBskI9iL28 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsControllersSection.this.lambda$addFastMovementToggle$331$SettingsControllersSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addFastMovementToggle$330$SettingsControllersSection() {
        if ((this.mSettingsManager.getInt(SettingsManager.OCULUS_BUTTON_MAPPING, 0) & 2) != 2) {
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$addFastMovementToggle$331$SettingsControllersSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        int i = this.mSettingsManager.getInt(SettingsManager.OCULUS_BUTTON_MAPPING, 0);
        this.mSettingsManager.setInt(SettingsManager.OCULUS_BUTTON_MAPPING, z ? i & -3 : i | 2);
    }

    private void addLongPressToggle(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("oculus_button_long_press").withTitle(R.string.settings_long_press_item_title).withSubtitle(R.string.settings_long_press_item_subtitle).withShowInEnterprise(true).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$UdtOenr4QCwY4aPNAlF1Y3OOUA0 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsControllersSection.this.lambda$addLongPressToggle$332$SettingsControllersSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$rKEcKJ8HJGXh64_4M84NyIWXSiE */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsControllersSection.this.lambda$addLongPressToggle$333$SettingsControllersSection(z, settingsToggleActionType);
            }
        })));
    }

    public /* synthetic */ boolean lambda$addLongPressToggle$332$SettingsControllersSection() {
        if ((this.mSettingsManager.getInt(SettingsManager.OCULUS_BUTTON_MAPPING, 0) & 1) == 1) {
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$addLongPressToggle$333$SettingsControllersSection(boolean z, SettingsToggleActionType settingsToggleActionType) {
        int i = this.mSettingsManager.getInt(SettingsManager.OCULUS_BUTTON_MAPPING, 0);
        this.mSettingsManager.setInt(SettingsManager.OCULUS_BUTTON_MAPPING, z ? i | 1 : i & -2);
    }

    private void addVibrationIntensitySlider(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("input_haptics_amplitude").withTitle(R.string.settings_haptics_item_title).withGatekeepers(Gatekeeper.SETTINGS_INPUT_HAPTICS_AMPLITUDE).withSettingsActionType(new SettingsSliderActionType.Builder(this.mContext).withValue(this.mSettingsManager.getInt("input_haptics_amplitude", 50)).withMinLabel(R.string.settings_haptics_item_slider_min_title).withMaxLabel(R.string.settings_haptics_item_slider_max_title).withOnDrag(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$EtUL4Mx7U9P2_OJTcrkO7v__l8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsControllersSection.this.lambda$addVibrationIntensitySlider$334$SettingsControllersSection(i);
            }
        }).withOnRelease(new SettingsSliderActionType.OnValueChangeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$nDOToEAWUhzLDVJ5bz67YUEMrB8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsSliderActionType.OnValueChangeHandler
            public final void onValueChange(int i) {
                SettingsControllersSection.this.lambda$addVibrationIntensitySlider$335$SettingsControllersSection(i);
            }
        })));
    }

    public /* synthetic */ void lambda$addVibrationIntensitySlider$334$SettingsControllersSection(int i) {
        this.mSettingsManager.setInt("input_haptics_amplitude", i);
    }

    public /* synthetic */ void lambda$addVibrationIntensitySlider$335$SettingsControllersSection(int i) {
        this.mSettingsManager.setInt("input_haptics_amplitude", i);
    }

    private boolean shouldShowNux() {
        OVRNuxPreferences.ClientPreferenceData clientPreferenceData = OVRNuxPreferences.getClientPreferenceData(this.mContext.getApplicationContext());
        if (clientPreferenceData != null && clientPreferenceData.userID != null) {
            return !clientPreferenceData.hasSeenHandTrackingNux;
        }
        Log.e(TAG, "Exception while getting preferences");
        return true;
    }

    private void add6DoFControllersPairingItem(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, List<BaseSettingsItem.Builder> list) {
        getControllerStatuses();
        list.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).visibilityFetcher(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$9wf8qGT60TLq28YFlC8P13xr8tM */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$add6DoFControllersPairingItem$336$SettingsControllersSection();
            }
        }).withShowInEnterprise(true).withBody(R.string.settings_controllers_pairing_group_title));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("controller_pairing").withTitle(R.string.settings_controllers_pairing_two_of_two_found).withIcon(R.drawable.oc_icon_touch_2_right_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$lZ2EdHYg4B8bVmVMRJSdu5K6LYo */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$add6DoFControllersPairingItem$337$SettingsControllersSection();
            }
        }).withShowInEnterprise(true).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_controllers_pairing_pair_new).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$cevI7Wcui_qXFSg3BzhwtztNR8 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsControllersSection.lambda$add6DoFControllersPairingItem$338(AnytimeUIAndroidPanelAppV2.this);
            }
        })));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("controller_pairing").withTitle(R.string.settings_controllers_pairing_one_of_two_found).withSubtitle(R.string.settings_controllers_pairing_right_nearby).withIcon(R.drawable.oc_icon_touch_2_right_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$bMLMAgHOBTsbmrv8g7Y5XZo4I */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$add6DoFControllersPairingItem$339$SettingsControllersSection();
            }
        }).withShowInEnterprise(true).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_controllers_pairing_pair).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$ihaApAIVFbQra9fAm0SFJwBdYOE */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsControllersSection.lambda$add6DoFControllersPairingItem$340(AnytimeUIAndroidPanelAppV2.this);
            }
        })));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("controller_pairing").withTitle(R.string.settings_controllers_pairing_one_of_two_found).withSubtitle(R.string.settings_controllers_pairing_left_nearby).withIcon(R.drawable.oc_icon_touch_2_left_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$CDgJ_N6GCHk81yp7kvkppXLqe0 */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$add6DoFControllersPairingItem$341$SettingsControllersSection();
            }
        }).withShowInEnterprise(true).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_controllers_pairing_pair).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$rpDclgqsq_eiCozQ6Ryu7mttbQ */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsControllersSection.lambda$add6DoFControllersPairingItem$342(AnytimeUIAndroidPanelAppV2.this);
            }
        })));
        list.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("controller_pairing").withTitle(R.string.settings_controllers_pairing_zero_of_two_found).withIcon(R.drawable.oc_icon_touch_2_left_filled_24_d2d2d2).withVisibilityCondition(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$ziH0Y6TRgnOmY4GcqGo6qBGTlmE */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsControllersSection.this.lambda$add6DoFControllersPairingItem$343$SettingsControllersSection();
            }
        }).withShowInEnterprise(true).withSettingsActionType(new SettingsButtonActionType.Builder(this.mContext).withTitle(R.string.settings_controllers_pairing_begin_pairing).withOnClickHandler(new SettingsButtonActionType.ButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$RXi0n22Te_d8JWiEgvpYZwEI7bw */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsButtonActionType.ButtonClickHandler
            public final void onClick() {
                SettingsControllersSection.lambda$add6DoFControllersPairingItem$344(AnytimeUIAndroidPanelAppV2.this);
            }
        })));
        this.mControllerStateModule.addControllerStatusListener(new ControllerStateModule.ControllerStateUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$2_Evm26AqXIpclLaXLYMpJCs0tc */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.ControllerStateModule.ControllerStateUpdateListener
            public final void onUpdate(String str, String str2) {
                SettingsControllersSection.this.modifyControllerStatus(str, str2);
            }
        });
    }

    public /* synthetic */ Boolean lambda$add6DoFControllersPairingItem$336$SettingsControllersSection() {
        return Boolean.valueOf(this.mIsEnterprise);
    }

    public /* synthetic */ Boolean lambda$add6DoFControllersPairingItem$337$SettingsControllersSection() {
        return Boolean.valueOf(this.mIsEnterprise && this.mPrimaryControllerConnected && this.mSecondaryControllerConnected);
    }

    public /* synthetic */ Boolean lambda$add6DoFControllersPairingItem$339$SettingsControllersSection() {
        return Boolean.valueOf(this.mIsEnterprise && this.mPrimaryControllerConnected && !this.mSecondaryControllerConnected);
    }

    public /* synthetic */ Boolean lambda$add6DoFControllersPairingItem$341$SettingsControllersSection() {
        return Boolean.valueOf(this.mIsEnterprise && !this.mPrimaryControllerConnected && this.mSecondaryControllerConnected);
    }

    public /* synthetic */ Boolean lambda$add6DoFControllersPairingItem$343$SettingsControllersSection() {
        return Boolean.valueOf(this.mIsEnterprise && !this.mPrimaryControllerConnected && !this.mSecondaryControllerConnected);
    }

    public void modifyControllerStatus(String str, String str2) {
        if (str.equals("primary")) {
            this.mPrimaryControllerConnected = CONTROLLER_CONNECTED.equals(str2);
        } else {
            this.mSecondaryControllerConnected = CONTROLLER_CONNECTED.equals(str2);
        }
        UiThreadExecutor.getInstance().execute(this.mRefreshView);
    }

    private void getControllerStatuses() {
        ThreadExecutor.getInstance().execute(new Callable() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsControllersSection$tSwmU9EVv0OgejBtaHnG0y_g4Kg */

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return SettingsControllersSection.this.lambda$getControllerStatuses$345$SettingsControllersSection();
            }
        });
    }

    public /* synthetic */ Object lambda$getControllerStatuses$345$SettingsControllersSection() throws Exception {
        try {
            Pair<String, String> controllerStatuses = this.mControllerStateModule.getControllerStatuses();
            if (controllerStatuses != null) {
                this.mPrimaryControllerConnected = CONTROLLER_CONNECTED.equals(controllerStatuses.first);
                this.mSecondaryControllerConnected = CONTROLLER_CONNECTED.equals(controllerStatuses.second);
            }
            UiThreadExecutor.getInstance().execute(this.mRefreshView);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error fetching Controller statuses", e);
            return null;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        if (this.mIsEnterprise) {
            this.mControllerStateModule.removeControllerStatusListener(new ControllerStateModule.ControllerStateUpdateListener() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$2_Evm26AqXIpclLaXLYMpJCs0tc */

                @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.modules.ControllerStateModule.ControllerStateUpdateListener
                public final void onUpdate(String str, String str2) {
                    SettingsControllersSection.this.modifyControllerStatus(str, str2);
                }
            });
        }
    }
}
