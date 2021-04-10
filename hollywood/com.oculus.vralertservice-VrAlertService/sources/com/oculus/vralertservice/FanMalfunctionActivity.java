package com.oculus.vralertservice;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import com.oculus.vralertservice.AlertApplication;
import oculus.internal.ui.VrAlertDialog;

public class FanMalfunctionActivity extends Activity {
    private VrAlertDialog mDialog;
    private final Handler mHandler = new Handler();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mDialog = VrAlertDialogs.getFanMalfunctionDialog(this, new Runnable() {
            /* class com.oculus.vralertservice.$$Lambda$FanMalfunctionActivity$aGv8PyFyP9GEiscwlk_0XVGVAYQ */

            public final void run() {
                FanMalfunctionActivity.this.lambda$onCreate$0$FanMalfunctionActivity();
            }
        });
        this.mDialog.getWindow().setType(2009);
        this.mDialog.create();
        Button button = this.mDialog.getButton(-1);
        button.setVisibility(4);
        if (AlertApplication.setCurrentDialog(AlertApplication.AlertLevel.FAN_MALFUNCTION, this.mDialog)) {
            this.mHandler.postDelayed(new Runnable(button) {
                /* class com.oculus.vralertservice.$$Lambda$FanMalfunctionActivity$dYxDiTkPKCCPvOXKk_GtlxwmhY0 */
                private final /* synthetic */ Button f$0;

                {
                    this.f$0 = r1;
                }

                public final void run() {
                    this.f$0.setVisibility(0);
                }
            }, 10000);
        }
        finish();
    }

    public /* synthetic */ void lambda$onCreate$0$FanMalfunctionActivity() {
        AlertApplication.clearAlert(AlertApplication.AlertLevel.NONE, this.mDialog);
    }
}
