package com.oculus.alpenglow.induction;

import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0LT;
import android.content.Context;
import android.content.Intent;

public class InductionBroadcastReceiver extends AnonymousClass0LT {
    public static final String ACTION = "ConnectivityCallback";
    public static final String TAG = "EnterpriseServer.InductionBroadcastReceiver";

    public InductionBroadcastReceiver() {
        super(ACTION, new InductionActionReceiver());
    }

    public static class InductionActionReceiver implements AbstractC04990iH {
        @Override // X.AbstractC04990iH
        public final void onReceive(Context context, Intent intent, AbstractC05000iI r3) {
            InductionService.A00(context);
        }
    }
}
