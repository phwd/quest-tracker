package com.oculus.appmanager.installer.boot;

import com.facebook.secure.receiver.DynamicSecureBroadcastReceiver;

public class InstallerServiceBootReceiver extends DynamicSecureBroadcastReceiver {
    public InstallerServiceBootReceiver() {
        super("android.intent.action.BOOT_COMPLETED", new BootCompletedAction());
    }
}
