package com.oculus.appsafety;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        if (AppSafetyApplication.getSharedPreferences().getLong(MissingPackageInfoJobService.LAST_JOB_RUN, 0) == 0) {
            MissingPackageInfoJobService.scheduleJob(context);
        }
        SafetySignalCollectorJobService.scheduleJob(context);
    }
}
