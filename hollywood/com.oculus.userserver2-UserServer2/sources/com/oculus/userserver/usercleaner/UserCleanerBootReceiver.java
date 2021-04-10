package com.oculus.userserver.usercleaner;

import X.Ol;
import X.Om;
import com.oculus.security.basecomponent.OculusSystemSecureBroadcastReceiver;

public class UserCleanerBootReceiver extends OculusSystemSecureBroadcastReceiver implements Ol {
    public static final String TAG = "UserCleanerBootReceiver";
    public Om _UL_mInjectionContext;

    public UserCleanerBootReceiver() {
        super("android.intent.action.LOCKED_BOOT_COMPLETED");
    }
}
