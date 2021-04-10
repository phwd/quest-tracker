package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class BroadcastReceiverWithIntentLoggingAutoProvider extends AbstractComponentProvider<BroadcastReceiverWithIntentLogging> {
    public void inject(BroadcastReceiverWithIntentLogging broadcastReceiverWithIntentLogging) {
        BroadcastReceiverWithIntentLogging._UL_staticInjectMe(this, broadcastReceiverWithIntentLogging);
    }

    public boolean equals(Object obj) {
        return obj instanceof BroadcastReceiverWithIntentLoggingAutoProvider;
    }
}
