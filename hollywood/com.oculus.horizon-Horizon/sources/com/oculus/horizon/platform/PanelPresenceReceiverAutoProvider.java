package com.oculus.horizon.platform;

import X.AbstractC06640p5;
import X.AnonymousClass0J9;

public class PanelPresenceReceiverAutoProvider extends AnonymousClass0J9<PanelPresenceReceiver> {
    public boolean equals(Object obj) {
        return obj instanceof PanelPresenceReceiverAutoProvider;
    }

    public void inject(PanelPresenceReceiver panelPresenceReceiver) {
        PanelPresenceReceiver._UL_staticInjectMe((AbstractC06640p5) this, panelPresenceReceiver);
    }

    public /* bridge */ /* synthetic */ void inject(Object obj) {
        PanelPresenceReceiver._UL_staticInjectMe((AbstractC06640p5) this, (PanelPresenceReceiver) obj);
    }
}
