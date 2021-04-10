package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.res.Resources;
import android.util.Pair;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.os.PreferencesManager;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.AndroidSettingsViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDescriptiveText;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SettingsDeveloperSection extends SettingsSection {
    private static final String GUARDIAN_TOGGLE_DIALOG = "settings_guardian_toggle";
    private static final String HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY = "hand_tracking_override_frequency";
    private static final Map<Integer, Integer> mHandTrackingOverrideValues = new TreeMap<Integer, Integer>() {
        /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsDeveloperSection.AnonymousClass1 */

        {
            put(0, Integer.valueOf(R.string.settings_hand_tracking_override_none_text));
            put(1, Integer.valueOf(R.string.settings_hand_tracking_override_low_text));
            put(2, Integer.valueOf(R.string.settings_hand_tracking_override_high_text));
        }
    };
    private final Context mContext;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private final PreferencesManager mPreferencesManager = new PreferencesManager();
    private final SettingsManager mSettingsManager = new SettingsManager();

    public SettingsDeveloperSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getResources().getString(R.string.anytime_tablet_settings_nav_developer_v2), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        AndroidSettingsViewModel acquireAndroidSettingsViewModel = this.mPanelApp.acquireAndroidSettingsViewModel();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_guardian_heading_text));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("guardian_toggle").withTitle(R.string.settings_guardian_item_title).withSubtitle(R.string.settings_guardian_item_subtitle).withIcon(R.drawable.oc_icon_guardian_filled_24_d2d2d2).withSettingsActionType(new SettingsToggleActionType.Builder().withGetValue(new SettingsToggleActionType.ToggleGetValueHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeveloperSection$VJl3YfD07HWi8gBPEnvVXfERAqA */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleGetValueHandler
            public final boolean get() {
                return SettingsDeveloperSection.this.lambda$new$346$SettingsDeveloperSection();
            }
        }).withSetValue(new SettingsToggleActionType.ToggleSetValueHandler(anytimeUIAndroidPanelAppV2) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeveloperSection$L6lZMGgLUnYpK5xUtCxXdoGgCs */
            private final /* synthetic */ AnytimeUIAndroidPanelAppV2 f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsToggleActionType.ToggleSetValueHandler
            public final void set(boolean z, SettingsToggleActionType settingsToggleActionType) {
                SettingsDeveloperSection.this.lambda$new$347$SettingsDeveloperSection(this.f$1, z, settingsToggleActionType);
            }
        })));
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_mtp_dialog_heading_text).withGatekeepers(Gatekeeper.CONFIGURABLE_MTP_DIALOG));
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("mtp_dialog_toggle").withTitle(R.string.settings_mtp_dialog_item_title).withSubtitle(R.string.settings_mtp_dialog_item_subtitle).withIcon(R.drawable.oc_icon_link_filled_24_d2d2d2).withGatekeepers(Gatekeeper.CONFIGURABLE_MTP_DIALOG).withSettingsActionType(new SettingsToggleActionType.Builder().withSettingsManagerKey(SettingsManager.MTP_DIALOG_ENABLED)));
        arrayList.add(new SettingsDescriptiveText.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withHeader(R.string.settings_hand_tracking_override_heading_text).withGatekeepers(Gatekeeper.HAND_TRACKING_FREQUENCY));
        int i = 0;
        Pair<Boolean, Integer> integer = this.mPreferencesManager.getInteger(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY);
        arrayList.add(new SettingsItem.Builder(this.mContext, anytimeUIAndroidPanelAppV2).withSettingName("hand_tracking_frequency_override").withTitle(R.string.settings_hand_tracking_override_item_title).withSubtitle(R.string.settings_hand_tracking_override_item_subtitle).withIcon(R.drawable.oc_icon_hand_tracking_filled_24_d2d2d2).withGatekeepers(Gatekeeper.HAND_TRACKING_FREQUENCY).withSettingsActionType(new SettingsDropdownActionType.Builder().withSelectorVisibilityHandler(acquireAndroidSettingsViewModel).withItems(mHandTrackingOverrideValues.keySet().toArray(new Integer[mHandTrackingOverrideValues.keySet().size()])).withSelectedItem(((Boolean) integer.first).booleanValue() ? (Integer) integer.second : i).withTitleMap(mHandTrackingOverrideValues).withOnSelectHandler(new SettingsDropdownActionType.OnSelectHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeveloperSection$Pfi8spc7nRJodoytGY3H9IH9hSg */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsDropdownActionType.OnSelectHandler
            public final boolean onSelect(Object obj) {
                return SettingsDeveloperSection.this.lambda$new$348$SettingsDeveloperSection((Integer) obj);
            }
        })));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ boolean lambda$new$346$SettingsDeveloperSection() {
        return !this.mSettingsManager.getBoolean(SettingsManager.GUARDIAN_PAUSED, false);
    }

    public /* synthetic */ boolean lambda$new$348$SettingsDeveloperSection(Integer num) {
        return this.mPreferencesManager.set(HAND_TRACKING_OVERRIDE_FREQUENCY_PREF_KEY, num.intValue());
    }

    /* access modifiers changed from: private */
    /* renamed from: confirmGuardianChange */
    public void lambda$new$347$SettingsDeveloperSection(AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, boolean z, SettingsToggleActionType settingsToggleActionType) {
        if (this.mSettingsManager.getBoolean(SettingsManager.GUARDIAN_PAUSED, false)) {
            this.mSettingsManager.setBoolean(SettingsManager.GUARDIAN_PAUSED, false);
            return;
        }
        DialogDefinitionCustom guardianOffDialog = getGuardianOffDialog();
        guardianOffDialog.setDialogResultHandler(new DialogResultHandler(settingsToggleActionType) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsDeveloperSection$JU2kI1ATsbyRpDxK33HAUNGAFzY */
            private final /* synthetic */ SettingsToggleActionType f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.systemdialog.DialogResultHandler
            public final boolean handleDialogResult(DialogResult dialogResult) {
                return SettingsDeveloperSection.this.lambda$confirmGuardianChange$349$SettingsDeveloperSection(this.f$1, dialogResult);
            }
        });
        anytimeUIAndroidPanelAppV2.getDialogManager().showDialog(guardianOffDialog);
    }

    public /* synthetic */ boolean lambda$confirmGuardianChange$349$SettingsDeveloperSection(SettingsToggleActionType settingsToggleActionType, DialogResult dialogResult) {
        if (CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            this.mSettingsManager.setBoolean(SettingsManager.GUARDIAN_PAUSED, true);
        } else {
            settingsToggleActionType.refreshToggle();
        }
        return true;
    }

    private DialogDefinitionCustom getGuardianOffDialog() {
        Resources resources = this.mContext.getResources();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(GUARDIAN_TOGGLE_DIALOG, resources.getString(R.string.settings_guardian_disable_dialog_title), resources.getString(R.string.settings_guardian_disable_dialog_body));
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_guardian_disable_dialog_cancel)));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_guardian_disable_dialog_acknowledge)));
        return dialogDefinitionCustom;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
        this.mPanelApp.releaseAndroidSettingsViewModel();
    }
}
