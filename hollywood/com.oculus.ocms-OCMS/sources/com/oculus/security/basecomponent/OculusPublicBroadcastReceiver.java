package com.oculus.security.basecomponent;

import android.content.Context;
import android.content.IntentFilter;
import com.facebook.secure.logger.IntentLogger;

public abstract class OculusPublicBroadcastReceiver extends OculusSecureBroadcastReceiverBase {
    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase, com.facebook.secure.receiver.SecureBroadcastReceiver
    public /* bridge */ /* synthetic */ IntentLogger getIntentLogger() {
        return super.getIntentLogger();
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public /* bridge */ /* synthetic */ void setIntentFilter(IntentFilter intentFilter) {
        super.setIntentFilter(intentFilter);
    }

    public OculusPublicBroadcastReceiver(String... strArr) {
        super(strArr);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void registerReceiver(Context context) {
        context.registerReceiver(this, getIntentFilter());
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void unregisterReceiver(Context context) {
        context.unregisterReceiver(this);
    }
}
