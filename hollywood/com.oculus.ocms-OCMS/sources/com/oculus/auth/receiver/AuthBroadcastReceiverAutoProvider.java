package com.oculus.auth.receiver;

import com.facebook.inject.AbstractComponentProvider;

public class AuthBroadcastReceiverAutoProvider extends AbstractComponentProvider<AuthBroadcastReceiver> {
    public void inject(AuthBroadcastReceiver authBroadcastReceiver) {
        AuthBroadcastReceiver._UL_staticInjectMe(this, authBroadcastReceiver);
    }

    public boolean equals(Object obj) {
        return obj instanceof AuthBroadcastReceiverAutoProvider;
    }
}
