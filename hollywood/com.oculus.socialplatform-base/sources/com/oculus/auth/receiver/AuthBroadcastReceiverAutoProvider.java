package com.oculus.auth.receiver;

import X.AnonymousClass0VK;

public class AuthBroadcastReceiverAutoProvider extends AnonymousClass0VK<AuthBroadcastReceiver> {
    public boolean equals(Object obj) {
        return obj instanceof AuthBroadcastReceiverAutoProvider;
    }

    public void inject(AuthBroadcastReceiver authBroadcastReceiver) {
        AuthBroadcastReceiver._UL_staticInjectMe(this, authBroadcastReceiver);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        AuthBroadcastReceiver._UL_staticInjectMe(this, (AuthBroadcastReceiver) obj);
    }
}
