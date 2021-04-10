package com.oculus.vrshell.config;

import android.content.Intent;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.vrshell.ShellApplication;
import com.oculus.vrshell.config.GatekeepersResultReceiver;
import java.util.ArrayList;

public class GatekeepersHandler implements GatekeepersResultReceiver.GatekeepersConfigHolder {
    private static final String SYSTEM_GATEKEEPERS_PACKAGE = "com.oculus.systemux";
    private static final String SYSTEM_GATEKEEPERS_RESULT_EXTRA = "RESULT_RECEIVER";
    private static final String SYSTEM_GATEKEEPERS_SERVICE = "com.oculus.systemux.SystemUXGatekeepersService";
    private static final String TAG = LoggingUtil.tag(GatekeepersHandler.class);
    private final GatekeepersConfiguration mGatekeepersConfiguration = new GatekeepersConfiguration();
    private final ShellApplication.IVrClient mVrClient;

    private static native void nativeRegisterGatekeepers(long j, String[] strArr);

    public GatekeepersHandler(ShellApplication.IVrClient iVrClient) {
        this.mVrClient = iVrClient;
    }

    @Override // com.oculus.vrshell.config.GatekeepersResultReceiver.GatekeepersConfigHolder
    public GatekeepersConfiguration getGatekeepersConfiguration() {
        return this.mGatekeepersConfiguration;
    }

    public void updateGatekeepers() {
        if (!this.mGatekeepersConfiguration.isValid()) {
            Log.d(TAG, "SystemUX SystemGK supported - requesting");
            requestGatekeepers("com.oculus.systemux.gatekeepers.aidl.MAIN", new GatekeepersResultReceiver.Callback() {
                /* class com.oculus.vrshell.config.GatekeepersHandler.AnonymousClass1 */

                @Override // com.oculus.vrshell.config.GatekeepersResultReceiver.Callback
                public void updateGatekeepersHolder(GatekeepersConfiguration gatekeepersConfiguration, ArrayList<String> arrayList) {
                    gatekeepersConfiguration.updateGatekeepers(arrayList);
                }
            });
        }
    }

    private void requestGatekeepers(String str, GatekeepersResultReceiver.Callback callback) {
        try {
            GatekeepersResultReceiver gatekeepersResultReceiver = new GatekeepersResultReceiver(this, str, callback);
            Intent intent = new Intent();
            intent.setAction(str);
            intent.setClassName("com.oculus.systemux", SYSTEM_GATEKEEPERS_SERVICE);
            intent.putExtra(SYSTEM_GATEKEEPERS_RESULT_EXTRA, gatekeepersResultReceiver.getCrossPackageReceiver());
            this.mVrClient.getApplicationContext().startService(intent);
            Log.d(TAG, "Gatekeepers intent is sent");
        } catch (Exception e) {
            Log.e(TAG, "Gatekeepers intent failed to start the service", e);
        }
    }

    @Override // com.oculus.vrshell.config.GatekeepersResultReceiver.GatekeepersConfigHolder
    public Object getSyncInstance() {
        return this.mGatekeepersConfiguration;
    }

    @Override // com.oculus.vrshell.config.GatekeepersResultReceiver.GatekeepersConfigHolder
    public void finalizeGatekeepersConfiguration() {
        synchronized (getSyncInstance()) {
            if (this.mGatekeepersConfiguration.isValid()) {
                nativeRegisterGatekeepers(this.mVrClient.getNativeAppPtr(), this.mGatekeepersConfiguration.getGatekeepers());
            }
        }
    }
}
