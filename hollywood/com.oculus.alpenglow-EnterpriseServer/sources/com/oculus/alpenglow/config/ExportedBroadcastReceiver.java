package com.oculus.alpenglow.config;

import X.AbstractC04990iH;
import X.AbstractC05000iI;
import X.AnonymousClass0LT;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R6;
import X.AnonymousClass0R7;
import android.content.Context;
import android.content.Intent;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ExportedBroadcastReceiver extends AnonymousClass0LT {
    public static final String TAG = "EnterpriseServer.ExportedBroadcastReceiver";

    public static class FetchConfigurationActionReceiver implements AbstractC04990iH, AnonymousClass0R6 {
        public AnonymousClass0R7 _UL_mInjectionContext;

        @Override // X.AbstractC04990iH
        public final void onReceive(Context context, Intent intent, AbstractC05000iI r6) {
            if (FetchConfigurationAction.ACTION_FETCH_CONFIGURATION.equals(intent.getAction())) {
                AnonymousClass0NK.A01(ExportedBroadcastReceiver.TAG, "fetching configuration");
                AnonymousClass0R7 r2 = new AnonymousClass0R7(1, AnonymousClass0Lh.get(context));
                this._UL_mInjectionContext = r2;
                ((FetchConfigurationAction) AnonymousClass0Lh.A03(0, 50, r2)).A01();
            }
        }
    }

    public ExportedBroadcastReceiver() {
        super(FetchConfigurationAction.ACTION_FETCH_CONFIGURATION, new FetchConfigurationActionReceiver());
    }
}
