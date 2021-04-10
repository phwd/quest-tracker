package com.oculus.horizon.fbconnect;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class FBConnectReceiverAutoProvider extends AnonymousClass0J9<FBConnectReceiver> {
    public boolean equals(Object obj) {
        return obj instanceof FBConnectReceiverAutoProvider;
    }

    public void inject(FBConnectReceiver fBConnectReceiver) {
        FBConnectReceiver._UL_staticInjectMe((AbstractC06640p5) this, fBConnectReceiver);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        FBConnectReceiver._UL_staticInjectMe((AbstractC06640p5) this, (FBConnectReceiver) obj);
    }
}
