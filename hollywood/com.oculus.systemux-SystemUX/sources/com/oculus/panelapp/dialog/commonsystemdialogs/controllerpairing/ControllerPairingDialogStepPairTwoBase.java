package com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import com.oculus.panelapp.dialog.R;
import com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer;
import com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingRemainingTimeCounter;
import com.oculus.systemdialog.DialogButtonText;
import com.oculus.systemdialog.DialogDefinitionCustom;
import com.oculus.systemdialog.DialogIcon;
import com.oculus.systemdialog.DialogInformationBox;

/* access modifiers changed from: package-private */
public abstract class ControllerPairingDialogStepPairTwoBase extends ControllerPairingDialogStep {
    protected final Context mContext;
    protected final Handler mHandler = new Handler();
    protected final ControllerPairingDialogStepHost mHost;
    private final ControllerPairingRemainingTimeCounter mRemainingTimeCounter = new ControllerPairingRemainingTimeCounter(new ControllerPairingRemainingTimeCounter.RemainingTimeListener() {
        /* class com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepPairTwoBase.AnonymousClass1 */

        @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingRemainingTimeCounter.RemainingTimeListener
        public void onRemainingTime(long j) {
            ControllerPairingDialogStepPairTwoBase.this.updateDialogConfiguration(j);
        }
    });

    /* access modifiers changed from: protected */
    public abstract DialogDefinitionCustom getDialogConfiguration(long j);

    protected ControllerPairingDialogStepPairTwoBase(Context context, ControllerPairingDialogStepHost controllerPairingDialogStepHost) {
        this.mContext = context;
        this.mHost = controllerPairingDialogStepHost;
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStep
    public void onStartStep() {
        super.onStartStep();
        updateDialogConfiguration(30000);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateDialogConfiguration(long j) {
        this.mHost.setDialogConfiguration(getDialogConfiguration(j));
    }

    /* access modifiers changed from: protected */
    public void setSuccessDialogConfiguration(int i, ControllerPairer.PairedControllerFirmwareStatus pairedControllerFirmwareStatus, boolean z) {
        String str;
        String str2;
        Resources resources = this.mContext.getResources();
        String str3 = ControllerPairingDialogActions.PAIR_TWO_RIGHT;
        if (i == 0) {
            str2 = resources.getString(R.string.controller_pairing_success_right_title);
            if (ControllerPairer.PairedControllerFirmwareStatus.UPDATE_REQUIRED == pairedControllerFirmwareStatus) {
                str = resources.getString(R.string.controller_pairing_success_right_info_firmware_update_required);
            } else {
                str = resources.getString(R.string.controller_pairing_success_right_info);
            }
            str3 = ControllerPairingDialogActions.PAIR_TWO_LEFT;
        } else {
            str2 = resources.getString(R.string.controller_pairing_success_left_title);
            if (ControllerPairer.PairedControllerFirmwareStatus.UPDATE_REQUIRED == pairedControllerFirmwareStatus) {
                str = resources.getString(R.string.controller_pairing_success_left_info_firmware_update_required);
            } else {
                str = resources.getString(R.string.controller_pairing_success_left_info);
            }
        }
        DialogDefinitionCustom informationBox = new DialogDefinitionCustom(getSuccessDialogId(), str2, null).setInformationBox(new DialogInformationBox(str, DialogIcon.InformationBox.CHECK_ALT));
        if (z) {
            informationBox.setSecondaryButton(new DialogButtonText("close", resources.getString(R.string.controller_pairing_success_done))).setPrimaryButton(new DialogButtonText(str3, resources.getString(R.string.controller_pairing_success_pair_next)));
        } else {
            this.mHandler.postDelayed(new Runnable() {
                /* class com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairingDialogStepPairTwoBase.AnonymousClass2 */

                public void run() {
                    ControllerPairingDialogStepPairTwoBase.this.mHost.closeDialog();
                }
            }, 3000);
        }
        this.mHost.setDialogConfiguration(informationBox);
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerPairerListener
    public void onPairingStart(long j) {
        this.mRemainingTimeCounter.start(j);
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerPairerListener
    public void onPairingSuccess(int i, ControllerPairer.PairedControllerFirmwareStatus pairedControllerFirmwareStatus) {
        this.mRemainingTimeCounter.stop();
    }

    @Override // com.oculus.panelapp.dialog.commonsystemdialogs.controllerpairing.ControllerPairer.ControllerPairerListener
    public void onPairingFailure() {
        this.mRemainingTimeCounter.stop();
    }
}
