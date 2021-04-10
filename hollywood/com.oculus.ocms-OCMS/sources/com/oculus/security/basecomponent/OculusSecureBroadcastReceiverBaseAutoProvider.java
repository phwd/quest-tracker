package com.oculus.security.basecomponent;

import com.facebook.inject.AbstractComponentProvider;

public class OculusSecureBroadcastReceiverBaseAutoProvider extends AbstractComponentProvider<OculusSecureBroadcastReceiverBase> {
    public void inject(OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase) {
        OculusSecureBroadcastReceiverBase._UL_staticInjectMe(this, oculusSecureBroadcastReceiverBase);
    }

    public boolean equals(Object obj) {
        return obj instanceof OculusSecureBroadcastReceiverBaseAutoProvider;
    }
}
