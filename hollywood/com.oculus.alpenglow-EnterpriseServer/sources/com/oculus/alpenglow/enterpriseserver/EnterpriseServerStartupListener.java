package com.oculus.alpenglow.enterpriseserver;

import X.C04980iE;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import com.facebook.ultralight.Dependencies;
import com.oculus.alpenglow.config.ExportedBroadcastReceiver;
import com.oculus.alpenglow.config.FetchConfigurationAction;
import com.oculus.alpenglow.lifecycle.StartupListener;

@Dependencies({})
public class EnterpriseServerStartupListener implements StartupListener {
    public static final String TAG = "EnterpriseServer.EnterpriseServerStartupListener";

    @Override // com.oculus.alpenglow.lifecycle.StartupListener
    public final void A6b(Context context) {
        NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addCapability(13).build();
        C04980iE r1 = new C04980iE(new ComponentName(context, ExportedBroadcastReceiver.class));
        r1.A09 = FetchConfigurationAction.ACTION_FETCH_CONFIGURATION;
        ((ConnectivityManager) context.getSystemService("connectivity")).registerNetworkCallback(build, r1.A03(context, 0));
    }
}
