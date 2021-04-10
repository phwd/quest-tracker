package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.dialog.R;
import com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer;
import com.oculus.systemdialog.CommonSystemDialog;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;
import com.oculus.systemdialog.DialogVideo;
import com.oculus.vrshell.util.DeviceProperties;

/* access modifiers changed from: package-private */
public final class ControllerPairingDialogStepPairTwo extends ControllerPairingDialogStepPairTwoBase {
    private static final String TAG = LoggingUtil.tag(ControllerPairingDialogStepPairTwo.class);
    private int mLastTriedDeviceType = -1;
    private final SettingsManager mSettingsManager;

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStep
    public String getDialogIdInfix() {
        return ControllerPairingConstants.CONTROLLER_PAIRING_DIALOG_ID_PAIR_TWO_INFIX;
    }

    public ControllerPairingDialogStepPairTwo(Context context, ControllerPairingDialogStepHost controllerPairingDialogStepHost, SettingsManager settingsManager) {
        super(context, controllerPairingDialogStepHost);
        this.mSettingsManager = settingsManager;
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerTypeProvider
    public int getNextTypeToPair() {
        if (this.mLastTriedDeviceType != 0) {
            this.mLastTriedDeviceType = 0;
        } else {
            this.mLastTriedDeviceType = 1;
        }
        return this.mLastTriedDeviceType;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepPairTwoBase
    public DialogDefinitionCustom getDialogConfiguration(long j) {
        Resources resources = this.mContext.getResources();
        return new DialogDefinitionCustom(CommonSystemDialog.CONTROLLER_PAIRING.getDialogId() + ControllerPairingConstants.CONTROLLER_PAIRING_DIALOG_ID_PAIR_TWO_INFIX + ControllerPairingConstants.CONTROLLER_PAIRING_DIALOG_ID_PENDING_SUFFIX, resources.getString(R.string.controller_pairing_two_controllers_title), resources.getString(R.string.controller_pairing_two_controllers_description)).setVideo(new DialogVideo(DeviceProperties.platformResourceAPK() + ControllerPairingDialogVideos.PAIR_TWO_CONTROLLERS, 1.778f, ControllerPairingDialogVideos.BACKGROUND_COLOR)).setInformationBox(new DialogInformationBox(ControllerPairingUtil.getTimeoutMessage(this.mContext, j), DialogIcon.InformationBox.SPINNER)).setTertiaryButton(new DialogButtonText("close", resources.getString(R.string.controller_pairing_dialog_cancel)));
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepPairTwoBase, com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerPairerListener
    public void onPairingSuccess(int i, ControllerPairer.PairedControllerFirmwareStatus pairedControllerFirmwareStatus) {
        super.onPairingSuccess(i, pairedControllerFirmwareStatus);
        setSuccessDialogConfiguration(i, pairedControllerFirmwareStatus, true);
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepPairTwoBase, com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerPairerListener
    public void onPairingFailure() {
        super.onPairingFailure();
        this.mHost.setDialogConfiguration(ControllerPairingUtil.getFailureDialogConfiguration(this.mContext, this, ControllerPairingDialogActions.PAIR_TWO, this.mSettingsManager.getBoolean(SettingsManager.HAND_TRACKING_OPT_IN, false)));
    }
}
