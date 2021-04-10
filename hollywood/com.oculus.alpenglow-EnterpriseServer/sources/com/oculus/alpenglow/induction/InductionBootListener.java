package com.oculus.alpenglow.induction;

import X.C04980iE;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import com.facebook.ultralight.Dependencies;
import com.oculus.alpenglow.boot.BootListener;

@Dependencies({})
public class InductionBootListener implements BootListener {
    public static final String TAG = "EnterpriseServer.InductionBootListener";

    @Override // com.oculus.alpenglow.boot.BootListener
    public final void A5u(Context context) {
        InductionService.A00(context);
        NetworkRequest build = new NetworkRequest.Builder().addCapability(12).addCapability(13).build();
        C04980iE r1 = new C04980iE(new ComponentName(context, InductionBroadcastReceiver.class));
        r1.A09 = InductionBroadcastReceiver.ACTION;
        PendingIntent A03 = r1.A03(context, 1);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            connectivityManager.registerNetworkCallback(build, A03);
        }
    }
}
