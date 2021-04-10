package com.oculus.horizon.platform;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import com.facebook.ultralight.Eager;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import javax.inject.Inject;

public class PanelPresenceReceiver extends OculusPublicBroadcastReceiver {
    public static final String ACTION = "com.oculus.horizon.SET_NEW_PANEL_ACTIVE";
    @Inject
    @Eager
    public PresenceManager mPresenceManager;

    public PanelPresenceReceiver() {
        super(ACTION);
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r0, PanelPresenceReceiver panelPresenceReceiver) {
        panelPresenceReceiver.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r0);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), this);
        this.mPresenceManager.panelApplicationChanged(intent.getStringExtra("package_name"), intent.getStringExtra("service_name"));
    }
}
