package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer;
import com.oculus.systemdialog.CommonSystemDialog;

/* access modifiers changed from: package-private */
public abstract class ControllerPairingDialogStep implements ControllerPairer.ControllerTypeProvider, ControllerPairer.ControllerPairerListener {
    private static final String TAG = LoggingUtil.tag(ControllerPairingDialogStep.class);
    private final ControllerPairer mPairer = new ControllerPairer(this, this);

    /* access modifiers changed from: protected */
    public abstract String getDialogIdInfix();

    protected ControllerPairingDialogStep() {
    }

    public void onStartStep() {
        this.mPairer.startPairing();
    }

    public void onFinishStep() {
        this.mPairer.stopPairing();
    }

    public void onAction(String str) {
        String str2 = TAG;
        Log.w(str2, "Ignored controller pairing dialog action: " + str);
    }

    public String getFailureDialogId() {
        return CommonSystemDialog.CONTROLLER_PAIRING.getDialogId() + getDialogIdInfix() + ControllerPairingConstants.CONTROLLER_PAIRING_DIALOG_ID_FAILURE_SUFFIX;
    }

    public String getSuccessDialogId() {
        return CommonSystemDialog.CONTROLLER_PAIRING.getDialogId() + getDialogIdInfix() + ControllerPairingConstants.CONTROLLER_PAIRING_DIALOG_ID_SUCCESS_SUFFIX;
    }
}
