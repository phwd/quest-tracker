package com.oculus.appmanager.uninstaller.receivers;

import com.facebook.inject.AbstractComponentProvider;

public class UninstallBroadcastReceiverAutoProvider extends AbstractComponentProvider<UninstallBroadcastReceiver> {
    public void inject(UninstallBroadcastReceiver uninstallBroadcastReceiver) {
        UninstallBroadcastReceiver._UL_staticInjectMe(this, uninstallBroadcastReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof UninstallBroadcastReceiverAutoProvider;
    }
}
