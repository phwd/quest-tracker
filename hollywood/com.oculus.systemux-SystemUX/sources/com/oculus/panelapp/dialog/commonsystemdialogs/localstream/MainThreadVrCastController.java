package com.oculus.panelapp.dialog.commonsystemdialogs.localstream;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrcast.VrCastController;
import java.util.List;

public class MainThreadVrCastController extends VrCastController.VrCastCallback {
    private static final String TAG = LoggingUtil.tag(MainThreadVrCastController.class);
    private final VrCastController.VrCastCallback mCallback;
    private final VrCastController mCastController;
    private final Handler mHandler = new Handler(Looper.getMainLooper());

    public MainThreadVrCastController(VrCastController.VrCastCallback vrCastCallback, Context context) {
        this.mCallback = vrCastCallback;
        this.mCastController = new VrCastController(context, this);
    }

    public void unbind() {
        this.mCastController.unbindVrCastService();
    }

    public void startDiscovery() {
        this.mCastController.startDiscovery();
    }

    @Override // com.oculus.vrcast.VrCastController.VrCastCallback
    public void onDevicesFound(List<VrCastController.VrShellCastDevice> list) {
        Log.d(TAG, String.format("onDevicesFound(%s)", list));
        this.mHandler.post(new Runnable(list) {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$MainThreadVrCastController$kX1XDcjk579jFujyp9dyVKL4p0 */
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainThreadVrCastController.this.lambda$onDevicesFound$5$MainThreadVrCastController(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onDevicesFound$5$MainThreadVrCastController(List list) {
        this.mCallback.onDevicesFound(list);
    }

    @Override // com.oculus.vrcast.VrCastController.VrCastCallback
    public void onDeviceStateUpdated(VrCastController.VrShellCastDevice vrShellCastDevice) {
        Log.d(TAG, String.format("onDeviceStateUpdated(%s)", vrShellCastDevice));
        this.mHandler.post(new Runnable(vrShellCastDevice) {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$MainThreadVrCastController$xDPOmSa8jXJ8WUzJIV4G0_Y4Y8 */
            private final /* synthetic */ VrCastController.VrShellCastDevice f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainThreadVrCastController.this.lambda$onDeviceStateUpdated$6$MainThreadVrCastController(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onDeviceStateUpdated$6$MainThreadVrCastController(VrCastController.VrShellCastDevice vrShellCastDevice) {
        this.mCallback.onDeviceStateUpdated(vrShellCastDevice);
    }

    @Override // com.oculus.vrcast.VrCastController.VrCastCallback
    public void onBindServiceSucceeded(VrCastController.VrShellCastDevice vrShellCastDevice) {
        Log.d(TAG, String.format("onBindServiceSucceeded(%s)", vrShellCastDevice));
        this.mHandler.post(new Runnable(vrShellCastDevice) {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$MainThreadVrCastController$NY4hI3BuEF98a4vXWNVULU3uSIM */
            private final /* synthetic */ VrCastController.VrShellCastDevice f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MainThreadVrCastController.this.lambda$onBindServiceSucceeded$7$MainThreadVrCastController(this.f$1);
            }
        });
    }

    public /* synthetic */ void lambda$onBindServiceSucceeded$7$MainThreadVrCastController(VrCastController.VrShellCastDevice vrShellCastDevice) {
        this.mCallback.onBindServiceSucceeded(vrShellCastDevice);
    }

    @Override // com.oculus.vrcast.VrCastController.VrCastCallback
    public void onBindServiceFailed() {
        Log.d(TAG, "onBindServiceFailed()");
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$MainThreadVrCastController$cvg3emQdbvm7t_ySR1eVRj55xc */

            public final void run() {
                MainThreadVrCastController.this.lambda$onBindServiceFailed$8$MainThreadVrCastController();
            }
        });
    }

    public /* synthetic */ void lambda$onBindServiceFailed$8$MainThreadVrCastController() {
        this.mCallback.onBindServiceFailed();
    }

    @Override // com.oculus.vrcast.VrCastController.VrCastCallback
    public void onRemoteServiceDied() {
        Log.d(TAG, "onRemoteServiceDied()");
        this.mHandler.post(new Runnable() {
            /* class com.oculus.panelapp.dialog.commonsystemdialogs.localstream.$$Lambda$MainThreadVrCastController$TpueV7XoQGMXEORN211fHoACqJ8 */

            public final void run() {
                MainThreadVrCastController.this.lambda$onRemoteServiceDied$9$MainThreadVrCastController();
            }
        });
    }

    public /* synthetic */ void lambda$onRemoteServiceDied$9$MainThreadVrCastController() {
        this.mCallback.onRemoteServiceDied();
    }
}
