package com.oculus.security.basecomponent;

import X.AnonymousClass0B0;
import android.content.Context;

public abstract class OculusLocalSecureBroadcastReceiver extends OculusSecureBroadcastReceiverBase {
    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void registerReceiver(Context context) {
        AnonymousClass0B0.A00(context).A02(this, getIntentFilter());
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void unregisterReceiver(Context context) {
        AnonymousClass0B0.A00(context).A01(this);
    }

    public OculusLocalSecureBroadcastReceiver(String... strArr) {
        super(strArr);
    }
}
