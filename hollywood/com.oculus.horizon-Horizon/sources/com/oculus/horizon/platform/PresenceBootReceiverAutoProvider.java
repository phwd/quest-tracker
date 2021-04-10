package com.oculus.horizon.platform;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class PresenceBootReceiverAutoProvider extends AnonymousClass0J9<PresenceBootReceiver> {
    public boolean equals(Object obj) {
        return obj instanceof PresenceBootReceiverAutoProvider;
    }

    public void inject(PresenceBootReceiver presenceBootReceiver) {
        PresenceBootReceiver._UL_staticInjectMe((AbstractC06640p5) this, presenceBootReceiver);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        PresenceBootReceiver._UL_staticInjectMe((AbstractC06640p5) this, (PresenceBootReceiver) obj);
    }
}
