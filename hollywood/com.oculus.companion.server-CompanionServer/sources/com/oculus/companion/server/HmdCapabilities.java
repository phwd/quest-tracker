package com.oculus.companion.server;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import com.oculus.companion.server.Protocol$HmdCapabilitiesResponse;
import com.oculus.os.SettingsManager;
import oculus.internal.Gatekeeper;

public class HmdCapabilities {
    private static final String TAG = "HmdCapabilities";
    private static final Gatekeeper sChromecastingGK = new Gatekeeper(4);
    private final Protocol$HmdCapabilitiesResponse mCapabilities;

    public HmdCapabilities(Context context) {
        Protocol$HmdCapabilitiesResponse.Builder newBuilder = Protocol$HmdCapabilitiesResponse.newBuilder();
        newBuilder.setChromecastAvailable(sChromecastingGK.isEnabled());
        newBuilder.setMultiUserAvailable(new SettingsManager().getBoolean("multi_user_enabled", false));
        newBuilder.setManualOtaVersion(getManualOtaVersion(context));
        newBuilder.setChromecastNativeReceiverAvailable(true);
        newBuilder.setTourGuideV2Available(new Gatekeeper(14).isEnabled());
        this.mCapabilities = (Protocol$HmdCapabilitiesResponse) newBuilder.build();
    }

    public Protocol$HmdCapabilitiesResponse getMessage() {
        return this.mCapabilities;
    }

    public String toString() {
        return "====== HmdCapabilities ======\nchromecast_available: " + this.mCapabilities.getChromecastAvailable() + "\nmulti_user_available: " + this.mCapabilities.getMultiUserAvailable() + "\nmanual_ota_version: " + this.mCapabilities.getManualOtaVersion() + "\nchromecast_native_receiver_available: " + this.mCapabilities.getChromecastNativeReceiverAvailable() + "\ntour_guide_v2_available: " + this.mCapabilities.getTourGuideV2Available() + "\n=============================";
    }

    private static int getManualOtaVersion(Context context) {
        int i = 0;
        try {
            if (context.getPackageManager().getPackageInfo("com.oculus.updater", 0).versionCode > 145163104) {
                i = 1;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Could not find com.oculus.updater", e);
        }
        return Math.min(1, i);
    }
}
