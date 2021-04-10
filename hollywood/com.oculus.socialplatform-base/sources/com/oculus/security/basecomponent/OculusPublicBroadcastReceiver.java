package com.oculus.security.basecomponent;

import android.content.Context;

public abstract class OculusPublicBroadcastReceiver extends OculusSecureBroadcastReceiverBase {
    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void registerReceiver(Context context) {
        context.registerReceiver(this, getIntentFilter());
    }

    public OculusPublicBroadcastReceiver(String... strArr) {
        super(strArr);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }
}
