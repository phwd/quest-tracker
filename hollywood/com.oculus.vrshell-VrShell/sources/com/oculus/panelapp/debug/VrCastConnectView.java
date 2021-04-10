package com.oculus.panelapp.debug;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.oculus.vrcast.VrCastController;

/* access modifiers changed from: package-private */
public class VrCastConnectView {
    private static final String TAG = "VrCastConnectView";
    private final VrCastManager mCastManager;
    private VrCastController.VrShellCastDevice mDevice;
    private final TextView mDeviceInfoText;
    private final TextView mDeviceStatusText;
    private final ProgressBar mProgressBar;
    private final View mView;

    public VrCastConnectView(Context context, VrCastManager vrCastManager, View view) {
        this.mCastManager = vrCastManager;
        this.mView = view;
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.vrcast_connect_progress);
        this.mDeviceInfoText = (TextView) view.findViewById(R.id.vrcast_device_info_text);
        this.mDeviceStatusText = (TextView) view.findViewById(R.id.vrcast_device_status_text);
        ((Button) view.findViewById(R.id.vrcast_cancel_button)).setOnClickListener(new View.OnClickListener() {
            /* class com.oculus.panelapp.debug.VrCastConnectView.AnonymousClass1 */

            public void onClick(View view) {
                VrCastConnectView.this.onCancelClicked();
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onCancelClicked() {
        VrCastController.VrShellCastDevice vrShellCastDevice = this.mDevice;
        if (vrShellCastDevice == null) {
            this.mView.setVisibility(8);
        } else {
            this.mCastManager.stopCast(vrShellCastDevice);
        }
    }

    public void onDeviceStateUpdated(VrCastController.VrShellCastDevice vrShellCastDevice) {
        this.mDevice = vrShellCastDevice;
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onDeviceStateUpdated() called with: device = ");
        sb.append(vrShellCastDevice != null ? vrShellCastDevice.toString() : null);
        Log.d(str, sb.toString());
        if (this.mDevice == null) {
            this.mView.setVisibility(8);
            return;
        }
        this.mView.setVisibility(0);
        if (this.mDevice.state == VrCastController.VrShellCastDevice.State.CASTING || this.mDevice.state == VrCastController.VrShellCastDevice.State.FOUND) {
            this.mProgressBar.setVisibility(8);
        } else {
            this.mProgressBar.setVisibility(0);
        }
        TextView textView = this.mDeviceInfoText;
        textView.setText("Device " + this.mDevice.name + " (" + this.mDevice.id + ")");
        TextView textView2 = this.mDeviceStatusText;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Status: ");
        sb2.append(this.mDevice.state.name());
        textView2.setText(sb2.toString());
    }
}
