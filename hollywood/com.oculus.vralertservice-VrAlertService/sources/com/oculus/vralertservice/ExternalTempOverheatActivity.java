package com.oculus.vralertservice;

import android.app.Activity;
import android.os.Bundle;
import com.oculus.vralertservice.AlertApplication;
import oculus.internal.ui.VrAlertDialog;

public class ExternalTempOverheatActivity extends Activity {
    private VrAlertDialog mDialog;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDialog = VrAlertDialogs.getExtTempDialog(this, new Runnable() {
            /* class com.oculus.vralertservice.$$Lambda$ExternalTempOverheatActivity$IbG_r0Ef2RlP1xEWcGixw9AB1hc */

            public final void run() {
                ExternalTempOverheatActivity.this.lambda$onCreate$0$ExternalTempOverheatActivity();
            }
        });
        this.mDialog.getWindow().setType(2009);
        this.mDialog.create();
        AlertApplication.setCurrentDialog(AlertApplication.AlertLevel.EXTERNAL_TEMPERATURE, this.mDialog);
        finish();
    }

    public /* synthetic */ void lambda$onCreate$0$ExternalTempOverheatActivity() {
        AlertApplication.clearAlert(AlertApplication.AlertLevel.NONE, this.mDialog);
    }
}
