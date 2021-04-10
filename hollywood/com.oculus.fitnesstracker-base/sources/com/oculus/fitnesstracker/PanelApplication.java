package com.oculus.fitnesstracker;

import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.DeviceConfigModuleImpl;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.FitnessDataModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.SettingsManagerModuleImpl;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.ReactVRApplication;
import com.oculus.panellib.SystraceBlock;

public class PanelApplication extends ReactVRApplication {
    private static final String SERVICES_PROCESS = "com.oculus.fitnesstracker:FitnessServices";
    private static final String TAG = "FitnessTrackerPanelApplication";

    public PanelApplication() {
        super(BuildConfig.BUILD_TYPE);
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void onCreate() {
        super.onCreate();
        if (isServicesProcess()) {
            FitnessWidgetRunner.getInstance(getApplicationContext());
        }
    }

    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        UserAgentBuilder newBuilder = UserAgentBuilder.newBuilder();
        SystraceBlock systraceBlock = new SystraceBlock("NetworkHeaders.init");
        try {
            String packageName = getPackageName();
            AppDetails appDetails = AppDetails.get(this, packageName);
            NetworkHeaders.sUserAgent = newBuilder.build$2e2641d1(System.getProperty("http.agent"), "OculusFitnessTracker", appDetails.versionName, packageName, String.valueOf(appDetails.versionCode), DeviceEnvironmentModuleImpl.getDeviceLocale(this));
            systraceBlock.close();
            createModule(DeviceConfigModuleImpl.class);
            createModule(DeviceEnvironmentModuleImpl.class);
            createModule(DialogsModuleImpl.class);
            createModule(FitnessDataModuleImpl.class);
            createModule(LibraryModuleImpl.class);
            createModule(SettingsManagerModuleImpl.class);
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    private boolean isServicesProcess() {
        return getProcessName(getApplicationContext()).equals(SERVICES_PROCESS);
    }
}
