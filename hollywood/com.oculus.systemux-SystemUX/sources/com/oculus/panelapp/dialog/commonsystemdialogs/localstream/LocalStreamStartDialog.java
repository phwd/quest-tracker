package com.oculus.panelapp.dialog.commonsystemdialogs.localstream;

import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.os.UserManager;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.deviceconfig.DeviceConfigHelper;
import com.oculus.common.gatekeepers.Gatekeeper;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.enterprise.EnterpriseServer;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.panelapp.dialog.R;
import com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListTitle;
import com.oculus.systemdialog.DialogListType;
import com.oculus.vrcast.VrCastController;
import com.oculus.vrshell.SystemUXRoute;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class LocalStreamStartDialog extends CommonDialog {
    private static final String BACK_ACTION = "back";
    private static final String BROWSER_CAST_LIST_ITEM_ID = "browser_cast";
    private static final String CLOSE_ACTION = "close";
    private static final int DEFAULT_DIALOG_CONFIGURATION_DELAY_MILLISECONDS = 50;
    private static final String ENTERPRISE_CASTING_GK = "arvr_enterprise_gk_casting_hw";
    private static final String NEXT_ACTION = "next";
    private static final String OK_ACTION = "ok";
    private static final String PHONE_CAST_LIST_ITEM_ID = "phone_cast";
    private static final String TAG = LoggingUtil.tag(LocalStreamStartDialog.class);
    @Nullable
    private List<VrCastController.VrShellCastDevice> mAvailableCastingDevices;
    private final Context mContext;
    @Nullable
    private VrCastController.VrShellCastDevice mCurrentCastingDevice;
    @Nullable
    private Timer mDefaultDialogConfigurationTimer;
    private boolean mHasSetDialogConfiguration = false;
    private final boolean mIsBrowserCastingEnabled;
    private boolean mIsCastingDeviceSelected = false;
    private final boolean mIsPhoneCastingEnabled;
    private final DialogPanelApp mPanelApp;
    private final Resources mResources;
    @Nullable
    private MainThreadVrCastController mVrCastController;

    public LocalStreamStartDialog(Context context, DialogPanelApp dialogPanelApp) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mPanelApp = dialogPanelApp;
        UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        boolean z = true;
        boolean z2 = userManager == null || userManager.isSystemUser();
        boolean isInEnterpriseMode = EnterpriseServer.isInEnterpriseMode(this.mContext);
        boolean isDynamicConfigSettingEnabled = isDynamicConfigSettingEnabled(ENTERPRISE_CASTING_GK);
        this.mIsPhoneCastingEnabled = z2 && !isInEnterpriseMode;
        if (!z2 || (isInEnterpriseMode && !isDynamicConfigSettingEnabled)) {
            z = false;
        }
        this.mIsBrowserCastingEnabled = z;
        if (!DeviceConfigHelper.getBoolean(this.mContext, Gatekeeper.AUI_ENABLE_LOCAL_CAST_NETWORK_CHECK_DIALOG) || isWifiConnectionAvailable()) {
            initializeVrCastController();
            initializeDefaultDialogConfigurationTimer();
            return;
        }
        setNoInternetDialogConfiguration();
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onClose() {
        MainThreadVrCastController mainThreadVrCastController = this.mVrCastController;
        if (mainThreadVrCastController != null) {
            mainThreadVrCastController.unbind();
            this.mVrCastController = null;
        }
        clearDefaultDialogConfigurationTimer();
    }

    private void initializeVrCastController() {
        this.mVrCastController = new MainThreadVrCastController(new VrCastController.VrCastCallback() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass1 */

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onRemoteServiceDied() {
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onDeviceStateUpdated(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
                if (LocalStreamStartDialog.this.isConnectionStart(vrShellCastDevice)) {
                    LocalStreamStartDialog.this.mPanelApp.launch(SystemUXRoute.LOCAL_STREAM_STOP_FROM_DEVICE_DIALOG);
                }
                LocalStreamStartDialog.this.mCurrentCastingDevice = vrShellCastDevice;
                if (LocalStreamStartDialog.this.mCurrentCastingDevice != null) {
                    return;
                }
                if (LocalStreamStartDialog.this.mVrCastController != null) {
                    LocalStreamStartDialog.this.mVrCastController.startDiscovery();
                } else {
                    Log.i(LocalStreamStartDialog.TAG, "Cast controller no longer available, casting dialog has been closed.");
                }
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onDevicesFound(List<VrCastController.VrShellCastDevice> list) {
                LocalStreamStartDialog.this.mAvailableCastingDevices = list;
                LocalStreamStartDialog.this.setDialogConfiguration();
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onBindServiceSucceeded(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
                onDeviceStateUpdated(vrShellCastDevice);
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onBindServiceFailed() {
                Log.w(LocalStreamStartDialog.TAG, "onBindServiceFailed(): Setting dialog configuration with no devices.");
                LocalStreamStartDialog.this.setDialogConfiguration();
            }
        }, this.mContext);
    }

    private void initializeDefaultDialogConfigurationTimer() {
        this.mDefaultDialogConfigurationTimer = new Timer();
        this.mDefaultDialogConfigurationTimer.schedule(new TimerTask() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass2 */

            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$LocalStreamStartDialog$2$xzSdXedXyLbA8ZT2D3rPkNZXWc */

                    public final void run() {
                        LocalStreamStartDialog.AnonymousClass2.this.lambda$run$4$LocalStreamStartDialog$2();
                    }
                });
            }

            public /* synthetic */ void lambda$run$4$LocalStreamStartDialog$2() {
                if (!LocalStreamStartDialog.this.mHasSetDialogConfiguration) {
                    Log.w(LocalStreamStartDialog.TAG, "Dialog configuration has not been set; setting default.");
                    LocalStreamStartDialog.this.setDialogConfiguration();
                }
            }
        }, 50);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDialogConfiguration() {
        this.mHasSetDialogConfiguration = true;
        clearDefaultDialogConfigurationTimer();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.LOCAL_STREAM_START.getDialogId(), this.mResources.getString(R.string.local_stream_start_dialog_title), this.mResources.getString(R.string.local_stream_start_dialog_description));
        dialogDefinitionCustom.setList(getDialogList());
        DialogButtonText dialogButtonText = new DialogButtonText(NEXT_ACTION, this.mResources.getString(R.string.local_stream_dialog_button_next));
        dialogButtonText.setDisabled(true ^ this.mIsCastingDeviceSelected);
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("close", this.mResources.getString(R.string.local_stream_dialog_button_close)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("back"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    private DialogList getDialogList() {
        String str;
        String str2;
        DialogList dialogList = new DialogList(DialogListType.SINGLE_SELECT);
        dialogList.setTitle(new DialogListTitle(this.mResources.getString(R.string.local_stream_start_dialog_list_title), true));
        dialogList.setShouldSendSelectionChangeAction(true);
        if (this.mIsPhoneCastingEnabled) {
            dialogList.addListItem(new DialogListItem(PHONE_CAST_LIST_ITEM_ID, this.mResources.getString(R.string.local_stream_start_dialog_device_type_phone), null, "apk://com.oculus.systemux/assets/ic_mobile.png", DialogListItemImageType.GLYPH));
        }
        if (this.mIsBrowserCastingEnabled) {
            boolean isInEnterpriseMode = EnterpriseServer.isInEnterpriseMode(this.mContext);
            if (isInEnterpriseMode) {
                str = this.mResources.getString(R.string.local_stream_start_dialog_device_type_computer_mobile_browser);
            } else {
                str = this.mResources.getString(R.string.local_stream_start_dialog_device_type_browser);
            }
            if (isInEnterpriseMode) {
                str2 = this.mResources.getString(R.string.local_stream_start_dialog_device_type_browser_description_enterprise);
            } else {
                str2 = this.mResources.getString(R.string.local_stream_start_dialog_device_type_browser_description);
            }
            dialogList.addListItem(new DialogListItem(BROWSER_CAST_LIST_ITEM_ID, str, str2, isInEnterpriseMode ? "apk://com.oculus.systemux/assets/ic_browser.png" : "apk://com.oculus.systemux/assets/ic_computer.png", DialogListItemImageType.GLYPH));
        }
        List<VrCastController.VrShellCastDevice> list = this.mAvailableCastingDevices;
        if (list != null) {
            for (VrCastController.VrShellCastDevice vrShellCastDevice : list) {
                dialogList.addListItem(new DialogListItem(vrShellCastDevice.id, vrShellCastDevice.name, LocalStreamUtils.getDeviceTypeText(vrShellCastDevice, this.mResources), LocalStreamUtils.getIconAssetPathForDevice(vrShellCastDevice), DialogListItemImageType.GLYPH));
            }
        }
        return dialogList;
    }

    private void setNoInternetDialogConfiguration() {
        this.mHasSetDialogConfiguration = true;
        clearDefaultDialogConfigurationTimer();
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.LOCAL_STREAM_START.getDialogId() + "_no_internet", this.mResources.getString(R.string.local_stream_no_internet_dialog_title), this.mResources.getString(R.string.local_stream_no_internet_dialog_description));
        dialogDefinitionCustom.setPrimaryButton(new DialogButtonText(OK_ACTION, this.mResources.getString(R.string.local_stream_no_internet_dialog_confirm_button_text)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("back"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State = new int[VrCastController.VrShellCastDevice.State.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0040 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State[] r0 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State = r0
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTING_TO_PEER     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTION_INITIATED     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTION_SUCCESS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.STARTING_SESSION     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.FOUND     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CASTING     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.DISCONNECTING     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.AnonymousClass3.<clinit>():void");
        }
    }

    private boolean isConnecting(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
        if (vrShellCastDevice == null) {
            return false;
        }
        int i = AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State[vrShellCastDevice.state.ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    private boolean isWifiConnectionAvailable() {
        if (!((WifiManager) this.mContext.getSystemService("wifi")).isWifiEnabled()) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities == null || !networkCapabilities.hasCapability(16) || !networkCapabilities.hasTransport(1)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean isConnectionStart(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
        return this.mCurrentCastingDevice == null && isConnecting(vrShellCastDevice);
    }

    private void clearDefaultDialogConfigurationTimer() {
        Timer timer = this.mDefaultDialogConfigurationTimer;
        if (timer != null) {
            timer.cancel();
            this.mDefaultDialogConfigurationTimer = null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003a, code lost:
        if (r6.equals(com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.PHONE_CAST_LIST_ITEM_ID) != false) goto L_0x003e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void handleNextAction(java.lang.String[] r6) {
        /*
            r5 = this;
            int r0 = r6.length
            if (r0 != 0) goto L_0x000b
            java.lang.String r6 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.TAG
            java.lang.String r0 = "No casting option selected."
            android.util.Log.e(r6, r0)
            goto L_0x0053
        L_0x000b:
            int r0 = r6.length
            r1 = 1
            if (r0 <= r1) goto L_0x0017
            java.lang.String r6 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.TAG
            java.lang.String r0 = "At most one casting option can be selected."
            android.util.Log.e(r6, r0)
            goto L_0x0053
        L_0x0017:
            r0 = 0
            r6 = r6[r0]
            r2 = -1
            int r3 = r6.hashCode()
            r4 = -1824445584(0xffffffff93412b70, float:-2.4381441E-27)
            if (r3 == r4) goto L_0x0034
            r0 = 1752089078(0x686ec1f6, float:4.5100047E24)
            if (r3 == r0) goto L_0x002a
            goto L_0x003d
        L_0x002a:
            java.lang.String r0 = "browser_cast"
            boolean r0 = r6.equals(r0)
            if (r0 == 0) goto L_0x003d
            r0 = r1
            goto L_0x003e
        L_0x0034:
            java.lang.String r3 = "phone_cast"
            boolean r3 = r6.equals(r3)
            if (r3 == 0) goto L_0x003d
            goto L_0x003e
        L_0x003d:
            r0 = r2
        L_0x003e:
            if (r0 == 0) goto L_0x004e
            if (r0 == r1) goto L_0x0048
            android.content.Context r0 = r5.mContext
            com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.startCastingToDevice(r0, r6)
            goto L_0x0053
        L_0x0048:
            android.content.Context r6 = r5.mContext
            com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.startCastingToBrowser(r6)
            goto L_0x0053
        L_0x004e:
            android.content.Context r6 = r5.mContext
            com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamUtils.startCastingToTwilight(r6)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.handleNextAction(java.lang.String[]):void");
    }

    private void handleListSelectionChange(String[] strArr) {
        boolean z = strArr.length > 0;
        if (this.mIsCastingDeviceSelected != z) {
            this.mIsCastingDeviceSelected = z;
            setDialogConfiguration();
        }
    }

    public boolean isDynamicConfigSettingEnabled(String str) {
        DialogPanelApp dialogPanelApp = this.mPanelApp;
        return Boolean.parseBoolean(dialogPanelApp.getEnvironmentArg("_oc_dynamic_config:" + str));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002e  */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAction(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 3377907(0x338af3, float:4.733456E-39)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 280301534(0x10b50fde, float:7.141636E-29)
            if (r0 == r1) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "listSelectionChange"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = r2
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "next"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0024
            r4 = 0
            goto L_0x0025
        L_0x0024:
            r4 = -1
        L_0x0025:
            if (r4 == 0) goto L_0x002e
            if (r4 == r2) goto L_0x002a
            goto L_0x0031
        L_0x002a:
            r3.handleListSelectionChange(r5)
            goto L_0x0031
        L_0x002e:
            r3.handleNextAction(r5)
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStartDialog.onAction(java.lang.String, java.lang.String[]):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    public boolean shouldSendActionToShell(String str, String[] strArr) {
        return !str.equals(DialogList.DIALOG_LIST_SELECTION_CHANGE_ACTION);
    }
}
