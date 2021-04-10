package com.oculus.panelapp.anytimeui.v2.tablet.settings.sections;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.library.baseAdapters.BR;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.osupdaterapi.OsUpdater;
import com.oculus.osupdaterapi.UpdaterOtaAvailability;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.panelapp.anytimeui.v2.AnytimeUIAndroidPanelAppV2;
import com.oculus.panelapp.anytimeui.v2.bar.status.StatusViewModel;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsNavigationActionType;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.accounts.AccountsDialogHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.settings.util.SoftwareUpdateRequirement;
import com.oculus.systemdialog.CommonSystemDialogActions;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogManager;
import com.oculus.systemdialog.DialogResult;
import com.oculus.systemdialog.DialogResultHandler;
import com.oculus.vrshell.SystemUXRoute;
import com.oculus.vrshell.util.UiThreadExecutor;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Supplier;

public class SettingsAboutSection extends SettingsSection {
    private static final String ENVIRONMENT_ARG_SHELL_VERSION_CODE = "_oc_shell_version_code";
    private static final String ENVIRONMENT_ARG_SHELL_VERSION_NAME = "_oc_shell_version_name";
    @VisibleForTesting
    public static final String INSTALL_UPDATE_DIALOG_ID = "settings_os_update_install_now";
    private static final String PRIVACY_POLICY_LINK = "https://www.oculus.com/legal/privacy-policy/";
    private static final float REQUIRED_BATTERY_PERCENTAGE = 0.5f;
    @VisibleForTesting
    public static final long REQUIRED_STORAGE_BYTES = 1048576;
    private static final String SUPPORT_LINK = "https://support.oculus.com/";
    private static final String TAG = LoggingUtil.tag(SettingsAboutSection.class);
    private static final String TERMS_OF_SERVICE_LINK = "https://www.oculus.com/legal/terms-of-service/";
    private final Context mContext;
    private final OsUpdater mOsUpdater;
    private final AnytimeUIAndroidPanelAppV2 mPanelApp;
    private UpdaterOtaAvailability mUpdaterOtaAvailability;

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.SettingsSection
    public void destroy() {
    }

    public SettingsAboutSection(Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2) {
        super(context.getResources().getString(R.string.anytime_tablet_settings_nav_about_v2), null);
        this.mContext = context;
        this.mPanelApp = anytimeUIAndroidPanelAppV2;
        this.mOsUpdater = new OsUpdater(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("software_update").withTitle(R.string.settings_about_section_software_update).withDynamicSubtitle(new Supplier() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$fgH0dTahLARTGoPFHimRrgSfDHI */

            @Override // java.util.function.Supplier
            public final Object get() {
                return SettingsAboutSection.lambda$fgH0dTahLARTGoPFHimRrgSfDHI(SettingsAboutSection.this);
            }
        }).withOnInitialize(new SettingsItem.InitializeHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$DMWmYeM2Lw2prQVrqkjBksFeY90 */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsItem.InitializeHandler
            public final void run(SettingsItem settingsItem) {
                SettingsAboutSection.this.lambda$new$288$SettingsAboutSection(settingsItem);
            }
        }).withSettingsActionType(new SettingsComplexButtonActionType.Builder().withInitializeState(new SettingsComplexButtonActionType.OnInitializeState() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$DqBsGr8JFl_NPJrSF3M1SaTrqOM */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.OnInitializeState
            public final void run(SettingsComplexButtonActionType settingsComplexButtonActionType) {
                SettingsAboutSection.this.lambda$new$291$SettingsAboutSection(settingsComplexButtonActionType);
            }
        }).withTitle(new SettingsComplexButtonActionType.GetTitleHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$uId70FkSlkrjXNnZ2eRM8vl6wEY */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.GetTitleHandler
            public final String get(Object obj) {
                return SettingsAboutSection.lambda$uId70FkSlkrjXNnZ2eRM8vl6wEY(SettingsAboutSection.this, (UpdaterOtaAvailability) obj);
            }
        }).withEnabled(new SettingsComplexButtonActionType.GetEnabledHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$6RrLeYxMq6uocT1B40wm_2MUJVo */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.GetEnabledHandler
            public final boolean get(Object obj) {
                return SettingsAboutSection.lambda$6RrLeYxMq6uocT1B40wm_2MUJVo(SettingsAboutSection.this, (UpdaterOtaAvailability) obj);
            }
        }).withLoadingDisabled(this.mPanelApp.isInOverlayMode()).withOnClickHandler(new SettingsComplexButtonActionType.OnButtonClickHandler() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$4m5OJCCd0biVzKa6WKvfoMct8TI */

            @Override // com.oculus.panelapp.anytimeui.v2.tablet.settings.SettingsComplexButtonActionType.OnButtonClickHandler
            public final void onClick(Object obj, SettingsComplexButtonActionType.UpdateStateHandler updateStateHandler) {
                SettingsAboutSection.this.lambda$new$292$SettingsAboutSection((UpdaterOtaAvailability) obj, updateStateHandler);
            }
        })));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withTitle(R.string.settings_about_section_sysux_version_title).withShowInEnterprise(true).withSubtitle(getSysUXApkVersion(context)));
        SettingsItem.Builder withShowInEnterprise = new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withTitle(R.string.settings_about_section_runtime_version_title).withShowInEnterprise(true);
        arrayList.add(withShowInEnterprise.withSubtitle(anytimeUIAndroidPanelAppV2.getEnvironmentArg(ENVIRONMENT_ARG_SHELL_VERSION_NAME) + "." + anytimeUIAndroidPanelAppV2.getEnvironmentArg(ENVIRONMENT_ARG_SHELL_VERSION_CODE)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withTitle(R.string.settings_about_section_os_version_title).withShowInEnterprise(true).withSubtitle(Build.DISPLAY));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withTitle(R.string.settings_about_section_mac_address_title).withShowInEnterprise(true).withSubtitle(getMACAddress(context)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("bug_report").withTitle(R.string.settings_about_section_bug_report_title).withSubtitle(R.string.settings_about_section_bug_report_subtitle).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.BUG_REPORT, "")));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName(AccountsDialogHelper.LIBRARY_SHARING_ERROR_DIALOG_SUPPORT_BUTTON).withTitle(R.string.settings_about_section_support_title).withSubtitle(R.string.settings_about_section_support_uri).withSettingsActionType(new SettingsNavigationActionType.Builder().withLink(anytimeUIAndroidPanelAppV2, SUPPORT_LINK)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("terms_of_service").withTitle(R.string.settings_about_section_terms_of_service_title).withSubtitle(R.string.settings_about_section_terms_of_service_uri).withSettingsActionType(new SettingsNavigationActionType.Builder().withLink(anytimeUIAndroidPanelAppV2, TERMS_OF_SERVICE_LINK)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("privacy_policy").withTitle(R.string.settings_about_section_privacy_policy_title).withSubtitle(R.string.settings_about_section_privacy_policy_uri).withSettingsActionType(new SettingsNavigationActionType.Builder().withLink(anytimeUIAndroidPanelAppV2, PRIVACY_POLICY_LINK)));
        arrayList.add(new SettingsItem.Builder(context, anytimeUIAndroidPanelAppV2).withSettingName("health_and_safety_video").withTitle(R.string.settings_about_section_healthy_and_safety_title).withSettingsActionType(new SettingsNavigationActionType.Builder().withSystemUXRoute(anytimeUIAndroidPanelAppV2, SystemUXRoute.HEALTH_AND_SAFETY, "")));
        addSettingsItems(arrayList);
    }

    public /* synthetic */ void lambda$new$288$SettingsAboutSection(SettingsItem settingsItem) {
        this.mOsUpdater.checkIfUpdatesAreAvailable(false, null, new OsUpdater.UpdaterOtaAvailabilityCallback(settingsItem) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$G9UUl4JZwYkU5_DgTol_krex7b8 */
            private final /* synthetic */ SettingsItem f$1;

            {
                this.f$1 = r2;
            }

            @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
            public final void onReceive(UpdaterOtaAvailability updaterOtaAvailability) {
                SettingsAboutSection.this.lambda$null$287$SettingsAboutSection(this.f$1, updaterOtaAvailability);
            }
        });
    }

    public /* synthetic */ void lambda$null$287$SettingsAboutSection(SettingsItem settingsItem, UpdaterOtaAvailability updaterOtaAvailability) {
        UiThreadExecutor.getInstance().execute(new Runnable(updaterOtaAvailability, settingsItem) {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$29Nx3TerAvR3Y2e9cUuj3BHy5ks */
            private final /* synthetic */ UpdaterOtaAvailability f$1;
            private final /* synthetic */ SettingsItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void run() {
                SettingsAboutSection.this.lambda$null$286$SettingsAboutSection(this.f$1, this.f$2);
            }
        });
    }

    public /* synthetic */ void lambda$null$286$SettingsAboutSection(UpdaterOtaAvailability updaterOtaAvailability, SettingsItem settingsItem) {
        this.mUpdaterOtaAvailability = updaterOtaAvailability;
        settingsItem.notifyPropertyChanged(BR.subtitle);
    }

    public /* synthetic */ void lambda$new$291$SettingsAboutSection(SettingsComplexButtonActionType settingsComplexButtonActionType) {
        if (!this.mPanelApp.isInOverlayMode()) {
            this.mOsUpdater.checkIfUpdatesAreAvailable(false, null, new OsUpdater.UpdaterOtaAvailabilityCallback() {
                /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$bYfZ9j9DT1iMvnxVvswGOzsc0mg */

                @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
                public final void onReceive(UpdaterOtaAvailability updaterOtaAvailability) {
                    SettingsAboutSection.lambda$null$290(SettingsComplexButtonActionType.this, updaterOtaAvailability);
                }
            });
        }
    }

    public /* synthetic */ void lambda$new$292$SettingsAboutSection(UpdaterOtaAvailability updaterOtaAvailability, SettingsComplexButtonActionType.UpdateStateHandler updateStateHandler) {
        onSoftwareOTAUpdateButtonClick(updaterOtaAvailability.mUpdaterState == OsUpdater.UpdaterState.STATE_WAITING_FOR_REBOOT, Boolean.TRUE.equals(updaterOtaAvailability.mAreUpdatesAvailable), updateStateHandler, this.mContext, this.mPanelApp, this.mOsUpdater);
    }

    /* access modifiers changed from: private */
    public String getSoftwareUpdateSubtitle() {
        Resources resources = this.mContext.getResources();
        if (this.mPanelApp.isInOverlayMode()) {
            return resources.getString(R.string.settings_about_section_quit_app_subtitle);
        }
        if (this.mUpdaterOtaAvailability == null) {
            return resources.getString(R.string.settings_about_section_checking_for_update_subtitle);
        }
        if (Boolean.TRUE.equals(this.mUpdaterOtaAvailability.mAreUpdatesAvailable)) {
            return resources.getString(R.string.settings_about_section_update_version_subtitle, this.mUpdaterOtaAvailability.mTargetVersion);
        }
        return resources.getString(R.string.settings_about_section_current_version_subtitle, this.mUpdaterOtaAvailability.mCurrentOSVersion);
    }

    private String getSysUXApkVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.oculus.systemux", 0);
            return packageInfo.versionName + "." + packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Unable to find package version", e);
            return "0.0.0";
        }
    }

    private String getMACAddress(Context context) {
        Log.d(TAG, "Getting MAC address");
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress != null) {
                        StringBuilder sb = new StringBuilder();
                        int length = hardwareAddress.length;
                        for (int i = 0; i < length; i++) {
                            sb.append(String.format("%02X:", Byte.valueOf(hardwareAddress[i])));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        return sb.toString();
                    }
                    throw new Exception("Failed to get MAC address. No hardware address");
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting MAC address", e);
        }
        return context.getResources().getString(R.string.settings_about_section_mac_address_unknown);
    }

    /* access modifiers changed from: private */
    public String getSoftwareOTAUpdateButtonTitle(UpdaterOtaAvailability updaterOtaAvailability) {
        Resources resources = this.mContext.getResources();
        if (this.mPanelApp.isInOverlayMode()) {
            return resources.getString(R.string.settings_about_section_check_for_updates_button);
        }
        if (updaterOtaAvailability == null) {
            return "";
        }
        int i = AnonymousClass1.$SwitchMap$com$oculus$osupdaterapi$OsUpdater$UpdaterState[updaterOtaAvailability.mUpdaterState.ordinal()];
        if (i == 1) {
            return resources.getString(R.string.settings_about_section_software_update_button_downloading);
        }
        if (i == 2) {
            return resources.getString(R.string.settings_about_section_software_update_button_wifi);
        }
        if (i == 3) {
            return resources.getString(R.string.settings_about_section_software_update_button_reboot);
        }
        if (Boolean.TRUE.equals(updaterOtaAvailability.mAreUpdatesAvailable)) {
            return resources.getString(R.string.settings_about_section_software_update_button_update_now);
        }
        return resources.getString(R.string.settings_about_section_software_update_button_no_updates);
    }

    /* access modifiers changed from: private */
    public boolean getSoftwareOTAUpdateButtonEnabled(UpdaterOtaAvailability updaterOtaAvailability) {
        if (updaterOtaAvailability == null) {
            return false;
        }
        switch (updaterOtaAvailability.mUpdaterState) {
            case STATE_UPDATE_IN_PROGRESS:
            case STATE_WIFI_DISABLED:
            case STATE_NOT_ALLOWED_BY_SYSTEM:
            case STATE_OTA_DISABLED_BY_USER:
            case STATE_UNKNOWN:
            case STATE_DEVICE_NOT_CONFIGURED_FOR_AB_UPDATES:
                return false;
            case STATE_WAITING_FOR_REBOOT:
                return true;
            default:
                return Boolean.TRUE.equals(updaterOtaAvailability.mAreUpdatesAvailable);
        }
    }

    @VisibleForTesting
    public static void onSoftwareOTAUpdateButtonClick(boolean z, boolean z2, SettingsComplexButtonActionType.UpdateStateHandler<UpdaterOtaAvailability> updateStateHandler, Context context, AnytimeUIAndroidPanelAppV2 anytimeUIAndroidPanelAppV2, OsUpdater osUpdater) {
        Resources resources = context.getResources();
        if (z) {
            osUpdater.rebootAndApplyUpdate();
        } else if (z2) {
            DialogManager dialogManager = anytimeUIAndroidPanelAppV2.getDialogManager();
            StatusViewModel acquireStatusViewModel = anytimeUIAndroidPanelAppV2.acquireStatusViewModel();
            float batteryLevel = ((float) acquireStatusViewModel.getBatteryLevel()) / ((float) acquireStatusViewModel.getBatteryScale());
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (!acquireStatusViewModel.isDeviceBatteryCharging()) {
                dialogManager.showDialog(getSoftwareUpdateRequirementDialog(resources, SoftwareUpdateRequirement.BATTERY_PLUGGED_IN));
            } else if (batteryLevel < 0.5f) {
                dialogManager.showDialog(getSoftwareUpdateRequirementDialog(resources, SoftwareUpdateRequirement.INSUFFICIENT_BATTERY));
            } else if (statFs.getAvailableBytes() < 1048576) {
                dialogManager.showDialog(getSoftwareUpdateRequirementDialog(resources, SoftwareUpdateRequirement.INSUFFICIENT_STORAGE));
            } else {
                DialogDefinitionCustom installConfirmationDialog = getInstallConfirmationDialog(resources);
                installConfirmationDialog.setDialogResultHandler(new DialogResultHandler(updateStateHandler) {
                    /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$Fn7lWPpbmNbxoph4rekJixCSMqI */
                    private final /* synthetic */ SettingsComplexButtonActionType.UpdateStateHandler f$1;

                    {
                        this.f$1 = r2;
                    }

                    @Override // com.oculus.systemdialog.DialogResultHandler
                    public final boolean handleDialogResult(DialogResult dialogResult) {
                        return SettingsAboutSection.lambda$onSoftwareOTAUpdateButtonClick$295(OsUpdater.this, this.f$1, dialogResult);
                    }
                });
                dialogManager.showDialog(installConfirmationDialog);
            }
            anytimeUIAndroidPanelAppV2.releaseStatusViewModel();
        }
    }

    static /* synthetic */ boolean lambda$onSoftwareOTAUpdateButtonClick$295(OsUpdater osUpdater, SettingsComplexButtonActionType.UpdateStateHandler updateStateHandler, DialogResult dialogResult) {
        if (!CommonSystemDialogActions.CONTINUE.equals(dialogResult.getDialogAction())) {
            return true;
        }
        osUpdater.downloadUpdateIfAvailable(false, null, new OsUpdater.UpdaterOtaAvailabilityCallback() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.settings.sections.$$Lambda$SettingsAboutSection$3X0uQEvuXUaurqUS9qT4w98ujrE */

            @Override // com.oculus.osupdaterapi.OsUpdater.UpdaterOtaAvailabilityCallback
            public final void onReceive(UpdaterOtaAvailability updaterOtaAvailability) {
                SettingsAboutSection.lambda$null$294(SettingsComplexButtonActionType.UpdateStateHandler.this, updaterOtaAvailability);
            }
        }, null);
        return true;
    }

    private static DialogDefinitionCustom getSoftwareUpdateRequirementDialog(Resources resources, SoftwareUpdateRequirement softwareUpdateRequirement) {
        String str;
        String dialogId = softwareUpdateRequirement.getDialogId();
        String string = resources.getString(softwareUpdateRequirement.getTitle());
        if (softwareUpdateRequirement == SoftwareUpdateRequirement.INSUFFICIENT_BATTERY) {
            str = resources.getString(softwareUpdateRequirement.getBody(), 50);
        } else {
            str = resources.getString(softwareUpdateRequirement.getBody());
        }
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(dialogId, string, str);
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_about_section_software_update_dismiss)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("cancel"));
        return dialogDefinitionCustom;
    }

    private static DialogDefinitionCustom getInstallConfirmationDialog(Resources resources) {
        return new DialogDefinitionCustom(INSTALL_UPDATE_DIALOG_ID, resources.getString(R.string.settings_about_section_software_update_confim_title), resources.getString(R.string.settings_about_section_software_update_confim_body)).setPrimaryButton(new DialogButtonText(CommonSystemDialogActions.CONTINUE, resources.getString(R.string.settings_about_section_software_update_confim_install))).setSecondaryButton(new DialogButtonText("cancel", resources.getString(R.string.settings_about_section_software_update_confim_cancel))).setControllerBackButton(new DialogButton("cancel"));
    }
}
