package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.dialog.CommonDialog;
import com.oculus.panelapp.dialog.DialogPanelApp;
import com.oculus.systemdialog.DialogButton;
import com.oculus.systemdialog.DialogDefinitionCustom;

public final class ControllerPairingDialog extends CommonDialog implements ControllerPairingDialogStepHost {
    private static final String TAG = LoggingUtil.tag(ControllerPairingDialog.class);
    private final Context mContext;
    private final DialogPanelApp mDialogPanelApp;
    private final SettingsManager mSettingsManager = new SettingsManager();
    private ControllerPairingDialogStep mStep;

    public ControllerPairingDialog(Context context, DialogPanelApp dialogPanelApp) {
        Log.d(TAG, "Creating dialog");
        this.mContext = context;
        this.mDialogPanelApp = dialogPanelApp;
        setShouldUsePassthrough(true);
        this.mStep = new ControllerPairingDialogStepPairTwo(this.mContext, this, this.mSettingsManager);
        this.mStep.onStartStep();
        Log.d(TAG, "Created dialog");
    }

    private void startNextDialogStep(ControllerPairingDialogStep controllerPairingDialogStep) {
        this.mStep.onFinishStep();
        this.mStep = controllerPairingDialogStep;
        this.mStep.onStartStep();
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onAction(String str, String[] strArr) {
        if (str.equals(ControllerPairingDialogActions.PAIR_TWO)) {
            startNextDialogStep(new ControllerPairingDialogStepPairTwo(this.mContext, this, this.mSettingsManager));
        } else if (str.equals(ControllerPairingDialogActions.PAIR_TWO_LEFT)) {
            startNextDialogStep(new ControllerPairingDialogStepPairTwoLeft(this.mContext, this, this.mSettingsManager));
        } else if (str.equals(ControllerPairingDialogActions.PAIR_TWO_RIGHT)) {
            startNextDialogStep(new ControllerPairingDialogStepPairTwoRight(this.mContext, this, this.mSettingsManager));
        } else if (!str.equals("close")) {
            String str2 = TAG;
            Log.d(str2, "Controller pairing action ignored, allowing step to handle: " + str);
            this.mStep.onAction(str);
        }
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepHost
    public void setDialogConfiguration(DialogDefinitionCustom dialogDefinitionCustom) {
        setPendingDialogConfiguration(dialogDefinitionCustom);
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepHost
    public void closeDialog() {
        setPendingSyntheticButtonClick(new DialogButton("close"));
    }

    @Override // com.oculus.panelapp.dialog.CommonDialog
    public void onClose() {
        this.mStep.onFinishStep();
    }
}
