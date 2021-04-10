package com.oculus.panelapp.dogfood;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.panels.views.ShellProgressBarView;

public class DogfoodPanelBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = LoggingUtil.tag(DogfoodPanelBroadcastReceiver.class);
    private final ShellProgressBarView barView = ((ShellProgressBarView) this.panelAppView.findViewById(R.id.dogfood_ota_update_progress_bar));
    private final DogfoodView panelAppView;

    public DogfoodPanelBroadcastReceiver(DogfoodView dogfoodView) {
        this.panelAppView = dogfoodView;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() == null) {
            Log.w(TAG, "Received an intent with no action.");
            return;
        }
        String action = intent.getAction();
        char c = 65535;
        if (action.hashCode() == 2118554582 && action.equals(OTAUpdateHelper.ACTION_UPDATER_STATE_NOTIFICATION)) {
            c = 0;
        }
        if (c != 0) {
            String str = TAG;
            Log.w(str, "Received unknown action: " + intent.getAction());
            return;
        }
        updateDogfoodPanel(intent.getExtras(), context);
    }

    private void updateDogfoodPanel(Bundle bundle, Context context) {
        if (bundle == null) {
            Log.d(TAG, "Ignoring OTA update because there are no extras.");
            return;
        }
        String string = bundle.getString("state");
        Log.d(TAG, String.format("Handling OTA state notification %s.", string));
        TextView textView = (TextView) this.panelAppView.findViewById(R.id.dogfood_update_panel_text);
        if (OTAUpdateHelper.STATE_NOTIFICATION_NO_UPDATES_AVAILABLE.equals(string)) {
            textView.setText(context.getString(R.string.dogfood_no_update_ota_text));
            this.panelAppView.findViewById(R.id.dogfood_no_update_button).setVisibility(0);
            this.barView.setProgressRatio(1.0f);
        } else if (!OTAUpdateHelper.STATE_NOTIFICATION_CHECKING_FOR_UDPATES.equals(string)) {
            if (OTAUpdateHelper.STATE_NOTIFICATION_WAITING_FOR_REBOOT.equals(string)) {
                this.barView.setProgressRatio(1.0f);
                textView.setText(context.getString(R.string.dogfood_reboot_request));
                this.panelAppView.findViewById(R.id.dogfood_no_update_button).setVisibility(0);
                return;
            }
            float f = bundle.getFloat("progress", this.barView.getProgressRatio());
            textView.setText(context.getString(R.string.dogfood_update_ota_text));
            if (f < 0.0f || f > 1.0f) {
                String str = TAG;
                Log.w(str, "Ignoring OTA update out of bounds progress of " + f);
                return;
            }
            String str2 = TAG;
            Log.d(str2, "Setting OTA update progress to " + f);
            this.barView.setProgressRatio(f);
        }
    }
}
