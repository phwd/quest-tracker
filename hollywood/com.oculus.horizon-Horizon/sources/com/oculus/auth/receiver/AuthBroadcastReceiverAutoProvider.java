package com.oculus.auth.receiver;

import X.AnonymousClass0J9;

public class AuthBroadcastReceiverAutoProvider extends AnonymousClass0J9<AuthBroadcastReceiver> {
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
