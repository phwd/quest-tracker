package com.oculus.vrcast.googlecast;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.vrcast.CastDevice;
import com.oculus.vrcast.OVRMediaServiceIntentHelper;
import com.oculus.vrcast.VrCastDeviceStore;
import com.oculus.vrcast.googlecast.net.auth.GoogleCastCA;
import com.oculus.vrcast.telemetry.VrCastTelemetry;
import java.io.IOException;
import java.net.InetAddress;

public class GoogleCastConnectionHelper {
    private static final String CALLER_CONTEXT_PEER = "peer";
    private static final String MWS_HOSTNAME = "localhost";
    private static final int MWS_PORT = 8890;
    private static final String MWS_URL = String.format("http://%s:%d/", MWS_HOSTNAME, Integer.valueOf((int) MWS_PORT));
    private static final String TAG = "GoogleCastConnectionHelper";
    private final Context mContext;
    private final Handler mErrorHandler = new Handler(Looper.myLooper());
    private final GoogleCastCA mGoogleCastCA;
    private GoogleCastMWSBridge mGoogleCastMWSBridge;
    private GoogleCastParams mParams;
    private final VrCastTelemetry mTelemetry;
    private final VrCastDeviceStore mVrCastDeviceStore;

    public GoogleCastConnectionHelper(Context context, VrCastTelemetry vrCastTelemetry) {
        this.mContext = context;
        this.mVrCastDeviceStore = VrCastDeviceStore.get(context);
        this.mGoogleCastCA = new GoogleCastCA(context);
        this.mTelemetry = vrCastTelemetry;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00de  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0106  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void connect(final com.oculus.vrcast.googlecast.GoogleCastDevice r25) {
        /*
        // Method dump skipped, instructions count: 372
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrcast.googlecast.GoogleCastConnectionHelper.connect(com.oculus.vrcast.googlecast.GoogleCastDevice):void");
    }

    public void disconnect() {
        if (this.mGoogleCastMWSBridge == null) {
            Log.w(TAG, "Tried to stop casting, but there is no active cast");
            return;
        }
        this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.DISCONNECTING);
        OVRMediaServiceIntentHelper.sendStopCastMwsIntent(this.mContext, GoogleCastConnectionHelper.class.getSimpleName());
        try {
            this.mGoogleCastMWSBridge.close();
        } catch (IOException e) {
            Log.w(TAG, "Failed to close GoogleCastMWSBridge", e);
        }
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        InetAddress address = this.mGoogleCastMWSBridge.getDeviceAddress().getAddress();
        if (!this.mParams.disableTdls) {
            Log.i(TAG, "Tearing down TDLS link to " + address);
            wifiManager.setTdlsEnabled(address, false);
        }
        this.mGoogleCastMWSBridge = null;
        this.mVrCastDeviceStore.updateActiveCastDeviceState(CastDevice.State.FOUND, true);
    }

    public void sendError(GoogleCastDevice googleCastDevice, int i, String str) {
        this.mVrCastDeviceStore.sendError(i, null);
        this.mTelemetry.onGoogleCastConnectionError(googleCastDevice, i, str);
    }
}
