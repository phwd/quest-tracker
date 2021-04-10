package com.oculus.userserver.managerservice;

import X.Ol;
import X.Om;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

public final class OculusUserBootReceiver extends OculusSystemSecureBroadcastReceiver implements Ol {
    public static final String TAG = "OculusUserBootReceiver";
    public Om _UL_mInjectionContext;

    public OculusUserBootReceiver() {
        super("android.intent.action.BOOT_COMPLETED");
    }
}
