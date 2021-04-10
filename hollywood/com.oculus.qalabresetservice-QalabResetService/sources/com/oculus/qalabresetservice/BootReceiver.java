package com.oculus.qalabresetservice;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.SystemProperties;
import android.os.UserHandle;

public class BootReceiver extends BroadcastReceiver {
    private static final String PROP_IN_QALAB = "ro.boot.in_qalab";

    public void onReceive(Context context, Intent intent) {
        if (SystemProperties.getBoolean(PROP_IN_QALAB, false)) {
            context.startServiceAsUser(new Intent(context, QalabResetService.class), UserHandle.SYSTEM);
        } else {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context.getPackageName(), QalabResetService.class.getName()), 2, 1);
        }
    }
}
