package com.oculus.micservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

public class BootReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        context.startServiceAsUser(new Intent(context, MicService.class), UserHandle.SYSTEM);
    }
}
