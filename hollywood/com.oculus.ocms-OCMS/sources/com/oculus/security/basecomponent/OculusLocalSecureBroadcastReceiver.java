package com.oculus.security.basecomponent;

import android.content.Context;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.secure.logger.IntentLogger;

public abstract class OculusLocalSecureBroadcastReceiver extends OculusSecureBroadcastReceiverBase {
    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase, com.facebook.secure.receiver.SecureBroadcastReceiver
    public /* bridge */ /* synthetic */ IntentLogger getIntentLogger() {
        return super.getIntentLogger();
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public /* bridge */ /* synthetic */ void setIntentFilter(IntentFilter intentFilter) {
        super.setIntentFilter(intentFilter);
    }

    public OculusLocalSecureBroadcastReceiver(String... strArr) {
        super(strArr);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void registerReceiver(Context context) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this, getIntentFilter());
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void unregisterReceiver(Context context) {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
    }
}
