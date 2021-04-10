package com.oculus.gamingactivity;

import com.oculus.device.NetworkHeaders;
import com.oculus.device.UserAgentBuilder;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.DialogsModuleImpl;
import com.oculus.modules.LibraryModuleImpl;
import com.oculus.modules.ToasterModuleImpl;
import com.oculus.panellib.ReactVRApplication;

public class PanelApplication extends ReactVRApplication {
    private static final String TAG = "GamingActivityPanelApplication";

    public PanelApplication() {
        super("release");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.ReactVRApplication
    public void createModules() {
        NetworkHeaders.init(this, UserAgentBuilder.newBuilder(), "OculusGamingActivity");
        createModule(DeviceEnvironmentModuleImpl.class);
        createModule(DialogsModuleImpl.class);
        createModule(LibraryModuleImpl.class);
        createModule(ToasterModuleImpl.class);
    }
}
