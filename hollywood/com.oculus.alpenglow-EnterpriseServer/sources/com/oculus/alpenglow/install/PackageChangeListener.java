package com.oculus.alpenglow.install;

import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;

public class PackageChangeListener extends OculusPublicBroadcastReceiver implements AnonymousClass0R6 {
    public static final String TAG = "EnterpriseServer.PackageChangeListener";
    public AnonymousClass0R7 _UL_mInjectionContext;

    public PackageChangeListener() {
        super("android.intent.action.PACKAGE_FULLY_REMOVED");
    }
}
