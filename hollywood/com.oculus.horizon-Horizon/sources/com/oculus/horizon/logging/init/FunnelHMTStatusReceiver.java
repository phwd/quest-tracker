package com.oculus.horizon.logging.init;

import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.EventManager;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;

public class FunnelHMTStatusReceiver extends OculusPublicBroadcastReceiver implements AnonymousClass0QB {
    public static final String ACTION_HMT_CONNECT = "com.samsung.intent.action.HMT_CONNECTED";
    public static final String FUNNEL_ACTION_HMT_CONNECT = "hmt_connected";
    public AnonymousClass0QC _UL_mInjectionContext;

    public FunnelHMTStatusReceiver() {
        super(ACTION_HMT_CONNECT);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r8) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, AnonymousClass0J2.get(context));
        if (ACTION_HMT_CONNECT.equals(intent.getAction())) {
            ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A8F(FunnelContract.NAV_TO_VR_FUNNEL_NAME, FUNNEL_ACTION_HMT_CONNECT, null, null);
            ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A2Q(FunnelContract.NAV_TO_VR_FUNNEL_NAME);
        }
    }
}
