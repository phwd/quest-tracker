package com.oculus.panelapp.dialog.commonsystemdialogs.localstream;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Nullable;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.R;
import com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogList;
import com.oculus.systemdialog.DialogListItem;
import com.oculus.systemdialog.DialogListItemImageType;
import com.oculus.systemdialog.DialogListType;
import com.oculus.vrcast.VrCastController;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public final class LocalStreamStopDialog extends CommonDialog {
    private static final String BACK_ACTION = "back";
    private static final String CANCEL_ACTION = "cancel";
    private static final int DEFAULT_DIALOG_CONFIGURATION_DELAY_MILLISECONDS = 50;
    private static final String STOP_ACTION = "stop";
    private static final String TAG = LoggingUtil.tag(LocalStreamStopDialog.class);
    private final Context mContext;
    @Nullable
    private Timer mDefaultDialogConfigurationTimer;
    private boolean mHasSetDialogConfiguration = false;
    private final Resources mResources;
    @Nullable
    private MainThreadVrCastController mVrCastController;

    public LocalStreamStopDialog(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
        this.mVrCastController = new MainThreadVrCastController(new VrCastController.VrCastCallback() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass1 */

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onDevicesFound(List<VrCastController.VrShellCastDevice> list) {
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onRemoteServiceDied() {
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onDeviceStateUpdated(VrCastController.VrShellCastDevice vrShellCastDevice) {
                LocalStreamStopDialog.this.setDialogConfiguration(vrShellCastDevice);
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onBindServiceSucceeded(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
                LocalStreamStopDialog.this.setDialogConfiguration(vrShellCastDevice);
            }

            @Override // com.oculus.vrcast.VrCastController.VrCastCallback
            public void onBindServiceFailed() {
                Log.w(LocalStreamStopDialog.TAG, "onBindServiceFailed(): Setting dialog configuration with no device.");
                LocalStreamStopDialog.this.setDialogConfiguration(null);
            }
        }, context);
        this.mDefaultDialogConfigurationTimer = new Timer();
        this.mDefaultDialogConfigurationTimer.schedule(new TimerTask() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass2 */

            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$LocalStreamStopDialog$2$eBtmdImvGzImId7uktirBCyAoI */

                    public final void run() {
                        LocalStreamStopDialog.AnonymousClass2.this.lambda$run$10$LocalStreamStopDialog$2();
                    }
                });
            }

            public /* synthetic */ void lambda$run$10$LocalStreamStopDialog$2() {
                if (!LocalStreamStopDialog.this.mHasSetDialogConfiguration) {
                    Log.w(LocalStreamStopDialog.TAG, "Dialog configuration has not been set; setting default.");
                    LocalStreamStopDialog.this.setDialogConfiguration(null);
                }
            }
        }, 50);
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setDialogConfiguration(@Nullable VrCastController.VrShellCastDevice vrShellCastDevice) {
        boolean z = true;
        this.mHasSetDialogConfiguration = true;
        clearDefaultDialogConfigurationTimer();
        boolean z2 = vrShellCastDevice != null && isConnecting(vrShellCastDevice);
        if (vrShellCastDevice == null || !shouldShowDeviceDetails(vrShellCastDevice)) {
            z = false;
        }
        DialogDefinitionCustom dialogDefinitionCustom = new DialogDefinitionCustom(CommonSystemDialog.LOCAL_STREAM_STOP.getDialogId(), this.mResources.getString(R.string.local_stream_dialog_title), this.mResources.getString(z2 ? R.string.local_stream_stop_dialog_description_connecting : R.string.local_stream_stop_dialog_description));
        if (z) {
            DialogList dialogList = new DialogList(DialogListType.NO_SELECT);
            dialogList.addListItem(new DialogListItem("casting_device", vrShellCastDevice.name, LocalStreamUtils.getDeviceTypeText(vrShellCastDevice, this.mResources), LocalStreamUtils.getIconAssetPathForDevice(vrShellCastDevice), DialogListItemImageType.GLYPH));
            dialogDefinitionCustom.setList(dialogList);
        }
        DialogButtonText dialogButtonText = new DialogButtonText(STOP_ACTION, this.mResources.getString(R.string.local_stream_stop_dialog_button_stop));
        dialogButtonText.setDisabled(z2);
        dialogDefinitionCustom.setPrimaryButton(dialogButtonText);
        dialogDefinitionCustom.setSecondaryButton(new DialogButtonText("cancel", this.mResources.getString(R.string.local_stream_dialog_button_close)));
        dialogDefinitionCustom.setControllerBackButton(new DialogButton("back"));
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog$3  reason: invalid class name */
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
                com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State = r0
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CASTING     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTING_TO_PEER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTION_INITIATED     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.CONNECTION_SUCCESS     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0040 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.STARTING_SESSION     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x004b }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.FOUND     // Catch:{ NoSuchFieldError -> 0x004b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004b }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004b }
            L_0x004b:
                int[] r0 = com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State     // Catch:{ NoSuchFieldError -> 0x0056 }
                com.oculus.vrcast.VrCastController$VrShellCastDevice$State r1 = com.oculus.vrcast.VrCastController.VrShellCastDevice.State.DISCONNECTING     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.panelapp.dialog.commonsystemdialogs.localstream.LocalStreamStopDialog.AnonymousClass3.<clinit>():void");
        }
    }

    private boolean shouldShowDeviceDetails(VrCastController.VrShellCastDevice vrShellCastDevice) {
        int i = AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State[vrShellCastDevice.state.ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
    }

    private boolean isConnecting(VrCastController.VrShellCastDevice vrShellCastDevice) {
        int i = AnonymousClass3.$SwitchMap$com$oculus$vrcast$VrCastController$VrShellCastDevice$State[vrShellCastDevice.state.ordinal()];
        return i == 2 || i == 3 || i == 4 || i == 5;
    }

    private void clearDefaultDialogConfigurationTimer() {
        Timer timer = this.mDefaultDialogConfigurationTimer;
        if (timer != null) {
            timer.cancel();
            this.mDefaultDialogConfigurationTimer = null;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (str.equals(STOP_ACTION)) {
            LocalStreamUtils.broadcastLocalStreamStopIntent(this.mContext);
        }
    }
}
